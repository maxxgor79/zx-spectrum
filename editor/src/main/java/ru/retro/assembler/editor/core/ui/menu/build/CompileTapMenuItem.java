package ru.retro.assembler.editor.core.ui.menu.build;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.retro.assembler.editor.core.i18n.Messages;
import ru.retro.assembler.editor.core.io.Source;
import ru.retro.assembler.editor.core.ui.Controller;
import ru.retro.assembler.editor.core.util.CLIUtils;

import java.awt.event.ActionEvent;

@Slf4j
public class CompileTapMenuItem extends AbstractCompileMenuItem {
    public CompileTapMenuItem(@NonNull Controller controller) {
        super(controller, Messages.get(Messages.COMPILE_WAV), (char) 0, null, null);
    }

    @Override
    public int order() {
        return 2;
    }

    @Override
    public boolean hasSeparator() {
        return false;
    }

    @Override
    public void onAction(ActionEvent e) {
        log.info("Action compile into tap format");
        final Source selectedSource = controller.getMainWindow().getSourceTabbedPane().getSourceSelected();
        if (selectedSource == null) {
            return;
        }
        compile(selectedSource, CLIUtils.ARG_TAP);
    }
}
