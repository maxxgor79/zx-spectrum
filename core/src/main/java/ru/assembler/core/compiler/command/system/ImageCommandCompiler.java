package ru.assembler.core.compiler.command.system;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import ru.assembler.core.compiler.CompilerApi;
import ru.assembler.core.settings.SettingsApi;
import ru.assembler.core.util.ImageUtils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class ImageCommandCompiler extends ResourceCommandCompiler {
    protected static final String[] NAMES = {"image", "img"};

    protected static final String BW_PREFIX = "bw_";

    protected static final String BITMAP_PREFIX = "bmp_";

    protected static final String RESOURCES_DIRECTORY = "resources";

    protected static final String DEFAULT_FORMAT = "png";

    protected static final String BIN_EXTENSION = "bin";

    protected SettingsApi settingsApi;

    public ImageCommandCompiler(@NonNull final SettingsApi settingsApi, @NonNull final CompilerApi compilerApi) {
        super(compilerApi);
        this.settingsApi = settingsApi;
    }

    @Override
    public String[] names() {
        return NAMES;
    }

    @Override
    protected byte[] loadResource(@NonNull String path) throws IOException {
        final File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        BufferedImage image = ImageIO.read(file);
        image = ImageUtils.toMonocrome(image); //without rescale
        saveImage(BW_PREFIX, file.getName(), image);
        final byte[] bitmap = ImageUtils.toBytes(image);
        saveResource(BITMAP_PREFIX, file.getName(), bitmap);
        return bitmap;
    }

    protected void saveImage(final String prefix, @NonNull final String name
            , @NonNull final BufferedImage image) throws IOException {
        final File dir = new File(settingsApi.getOutputDirectory(), RESOURCES_DIRECTORY);
        dir.mkdirs();
        final File file = new File(dir, prefix + name);
        try {
            String format = FilenameUtils.getExtension(name);
            if (format == null) {
                format = DEFAULT_FORMAT;
            }
            ImageIO.write(image, format, file);
        } catch (IllegalArgumentException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected void saveResource(final String prefix, @NonNull final String name, @NonNull final byte[] bitmap)
            throws IOException {
        final File dir = new File(settingsApi.getOutputDirectory(), RESOURCES_DIRECTORY);
        dir.mkdirs();
        final File file = new File(dir, prefix + FilenameUtils.getName(name) + "." + BIN_EXTENSION);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            IOUtils.write(bitmap, fos);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
