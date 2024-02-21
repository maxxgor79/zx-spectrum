package ru.retro.assembler.editor.core.i18n;

import lombok.NonNull;

import java.util.ResourceBundle;

/**
 * @Author: Maxim Gorin
 * Date: 19.02.2024
 */
public final class Messages {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n.Messages");

    private Messages() {

    }

    public static final String OPEN_FILE = "open_file";

    public static final String SAVE_AS = "save_as";

    public static final String ASM_SOURCES = "asm_sources";

    public static final String HEADER_SOURCES = "header_sources";

    public static final String Z80_SOURCES = "z80_sources";

    public static final String FILE = "file";

    public static final String EDIT = "edit";

    public static final String BUILD = "build";

    public static final String TOOLS = "tools";

    public static final String HELP = "help";

    public static final String NEW_FILE = "new_file";

    public static final String SAVE_FILE = "save_file";

    public static final String COMPILE_FILE = "compile_file";

    public static final String RELOAD_ALL_FILES = "reload_all_files";

    public static final String ABOUT = "about";

    public static final String NEW = "new";

    public static final String OPEN = "open";

    public static final String SAVE = "save";

    public static final String SAVE_ALL = "save_all";

    public static final String CLOSE = "close";

    public static final String EXIT = "exit";

    public static final String OUTPUT = "output";

    public static final String COMPILE = "compile";

    public static final String UNDO = "undo";

    public static final String CUT = "cut";

    public static final String COPY = "copy";

    public static final String PASTE = "paste";

    public static final String FIND = "find";

    public static final String DELETE = "delete";

    public static final String PREFERENCES = "preferences";

    public static String get(@NonNull String s) {
        return BUNDLE.getString(s);
    }
}
