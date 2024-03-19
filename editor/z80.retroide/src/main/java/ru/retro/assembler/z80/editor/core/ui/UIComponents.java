package ru.retro.assembler.z80.editor.core.ui;

import lombok.NonNull;
import ru.retro.assembler.editor.core.io.BuildVersionReader;
import ru.retro.assembler.editor.core.ui.Controller;
import ru.retro.assembler.editor.core.ui.ModalDialog;
import ru.retro.assembler.editor.core.util.UIFactory;

/**
 * @Author: Maxim Gorin
 * Date: 18.03.2024
 */
public final class UIComponents {
    private UIComponents() {

    }

    public static UIFactory defaultUIFactory(@NonNull final BuildVersionReader buildVersionReader) {
        return controller -> {
            final AboutDialog dialog = new AboutDialog(controller.getMainWindow());
            dialog.setMajorVersion(controller.getSettings().getMajorVersion());
            dialog.setMinorVersion(controller.getSettings().getMinorVersion());
            dialog.setBuildVersion(buildVersionReader.getBuildVersion());
            dialog.setBuildDate(buildVersionReader.getBuildDate());
            return dialog;
        };
    }
}
