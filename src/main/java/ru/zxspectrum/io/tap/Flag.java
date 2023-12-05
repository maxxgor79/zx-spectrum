package ru.zxspectrum.io.tap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public enum Flag {
    Header(0), Data(255);

    Flag(int code) {
        this.code = code;
    }

    @Setter
    @Getter
    private final int code;

    public static Flag findByCode(int code) {
        for (Flag flag : values()) {
            if (flag.code == code) {
                return flag;
            }
        }
        return null;
    }
}
