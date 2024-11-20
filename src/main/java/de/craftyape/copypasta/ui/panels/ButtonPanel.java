package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class ButtonPanel extends ParentPanel {

    private final transient Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public ButtonPanel(List<Pasta> pastas) {
        this.setMaximumSize(super.getSize());
        this.setLayout(new GridLayout(6, 5));
        this.pastas = pastas;
        initComponents();
    }

    private void initComponents() {

        for (Pasta pasta : pastas) {
            JButton jButton = new JButton(pasta.getTitle());
            jButton.setFont(getFontBold18());
            jButton.addActionListener(e -> buttonClicked(pasta));
            setConstraints(pasta.getPosX(), pasta.getPosY());
            gridBagConstraints.weightx = 1d/5;
            gridBagConstraints.weighty = 1d/6;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            add(jButton, gridBagConstraints);
        }
    }

    private void buttonClicked(Pasta pasta) {
        clipboard.setContents(new StringSelection(pasta.getText()), null);
    }
}
