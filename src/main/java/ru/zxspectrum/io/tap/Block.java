package ru.zxspectrum.io.tap;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.zxspectrum.io.LEDataInputStream;
import ru.zxspectrum.io.LEDataOutputStream;

import java.io.IOException;

@Slf4j
@ToString
public class Block implements TapElementReader {
    @Setter
    @Getter
    @NonNull
    private int blockLength;

    @Setter
    @Getter
    @NonNull
    private Flag flag;

    @Setter
    @Getter
    @NonNull
    private Header header;

    @Setter
    @Getter
    private byte[] data;

    @Setter
    @Getter
    @NonNull
    private byte[] headerlessData;


    @Override
    public void read(@NonNull LEDataInputStream dis) throws IOException {
        blockLength = dis.readUnsignedShort();
        int b = dis.readUnsignedByte();
        flag = Flag.findByCode(b);
        if (flag == null) {
            throw new IOException("Bad flag format b=" + b);
        }
        if (blockLength == 19) {
            if (flag == Flag.Header) {
                header = new Header();
                header.read(dis);
            }
            data = IOUtils.readFully(dis, header.getDataSize() + 4);
        }
        if (flag == Flag.Data) {
            headerlessData = IOUtils.readFully(dis, blockLength - 1);// - sizeof(code)
        }
    }

    public void write(@NonNull final LEDataOutputStream dos) throws IOException {
        dos.writeShort(blockLength);
        if (flag != null) {
            dos.writeByte(flag.getCode());
        }
        if (blockLength == 19 && flag == Flag.Header && header != null) {
            header.write(dos);
        }
        if (blockLength == 19 && data != null) {
            dos.write(data);
        }
        if (flag == Flag.Data && headerlessData != null) {
            dos.write(headerlessData);
        }
    }
}
