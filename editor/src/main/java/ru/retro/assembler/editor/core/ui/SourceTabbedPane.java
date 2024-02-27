package ru.retro.assembler.editor.core.ui;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.retro.assembler.editor.core.io.Source;
import ru.retro.assembler.editor.core.util.ResourceUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Maxim Gorin
 * Date: 25.02.2024
 */
@Slf4j
public class SourceTabbedPane extends JTabbedPane {
    private final List<Source> sourceList = new ArrayList<>(32);

    private Icon icon;

    @Getter
    private SourcePopupMenu sourcePopupMenu;

    public SourceTabbedPane() {
        initComponents();
    }

    private void initComponents() {
        if (icon == null) {
            try {
                icon = ResourceUtils.loadIcon("/icon16x16/text.png");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        sourcePopupMenu = new SourcePopupMenu();
        initListeners();
    }

    private void initListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    sourcePopupMenu.show(SourceTabbedPane.this, e.getX(), e.getY());
                }
            }
        });
    }

    public int indexOf(Source src) {
        if (src == null || src.getFile() == null) {
            return -1;
        }
        for (int i = 0; i < getTabCount(); i++) {
            final Source existedSrc = getSource(i);
            if (existedSrc.getFile().equals(src.getFile())) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(@NonNull final Source src) {
        if (sourceList.contains(src)) {
            return false;
        }
        final EditorPanel editorPanel = new EditorPanel(src.getTextArea());
        final int index = getTabCount();
        insertTab(src.getName(), icon, editorPanel, src.getName(), index);
        sourceList.add(src);
        setSelectedIndex(index);
        return true;
    }

    public boolean setSelected(@NonNull final Source src) {
        if (!sourceList.contains(src)) {
            return false;
        }
        this.setSelectedIndex(sourceList.indexOf(src));
        return true;
    }

    public boolean close(@NonNull final Source src) {
        if (!sourceList.contains(src)) {
            return false;
        }
        final int index = sourceList.indexOf(src);
        removeTabAt(index);
        sourceList.remove(index);
        return true;
    }

    public Source getSourceSelected() {
        return getSource(getSelectedIndex());
    }

    public Source getSource(int index) {
        return sourceList.get(index);
    }
}
