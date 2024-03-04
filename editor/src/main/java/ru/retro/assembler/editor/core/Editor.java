package ru.retro.assembler.editor.core;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import ru.retro.assembler.editor.core.i18n.Messages;
import ru.retro.assembler.editor.core.ui.Controller;
import ru.retro.assembler.editor.core.ui.MainWindow;
import ru.retro.assembler.editor.core.util.UIUtils;

import javax.swing.*;
import java.util.List;
import java.util.Locale;

/**
 * @Author: Maxim Gorin
 * Date: 19.02.2024
 */
@Slf4j
public class Editor {

    private static Controller controller;

    public static void main(String[] args) {
        final String language = UIUtils.toLanguage(Locale.getDefault());
        if (language == null || language.trim().isEmpty()) {
            Locale.setDefault(UIUtils.toLocale(Messages.get(Messages.ENGLISH)));
        }
        controller = new Controller(List.of(args));
        SwingUtilities.invokeLater(controller);
    }
}
