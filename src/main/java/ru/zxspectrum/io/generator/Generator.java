package ru.zxspectrum.io.generator;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.zxspectrum.io.tap.TapData;

import java.io.File;
import java.io.IOException;

@Setter
@Getter
public abstract class Generator {
    @NonNull
    protected File file;

    protected int sampleRate;

    protected float volume = 1.0f;

    public abstract void generateWav(@NonNull Object data) throws IOException;
}
