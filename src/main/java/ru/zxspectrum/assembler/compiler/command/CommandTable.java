package ru.zxspectrum.assembler.compiler.command;

import lombok.NonNull;
import ru.zxspectrum.assembler.compiler.CommandCompiler;
import ru.zxspectrum.assembler.compiler.CommandGroupCompiler;
import ru.zxspectrum.assembler.syntax.LexemSequence;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Maxim Gorin
 */
public class CommandTable {
    private final Map<LexemSequence, CommandGroupCompiler> commandMap = new HashMap<>();

    public CommandTable() {

    }

    public CommandGroupCompiler get(LexemSequence command) {
        if (command == null) {
            return null;
        }
        return commandMap.get(command);
    }

    public CommandCompiler put(@NonNull LexemSequence command, @NonNull CommandGroupCompiler commandGroupCompiler) {
        return commandMap.put(command, commandGroupCompiler);
    }

    public void putAll(@NonNull CommandTable t) {
        if (t == null) {
            return;
        }
        for (Map.Entry<LexemSequence, CommandGroupCompiler> entry : t.commandMap.entrySet()) {
            CommandGroupCompiler commandGroupCompiler = commandMap.get(entry.getKey());
            if (commandGroupCompiler != null) {
                commandGroupCompiler.add(entry.getValue());
            } else {
                commandMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean isEmpty() {
        return commandMap.isEmpty();
    }

    public void clear() {
        commandMap.clear();
    }
}