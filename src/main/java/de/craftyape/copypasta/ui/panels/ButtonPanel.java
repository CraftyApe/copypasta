package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ButtonPanel extends ParentPanel {

    private final transient Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public ButtonPanel(Pasta[] pastas) {
        this.setLayout(new GridLayout(5, 5));
        this.pastas = pastas;
        initComponents();
    }

    private void initComponents() {

        Dimension buttonSize = new Dimension(240, 160);

        for (Pasta pasta : pastas) {
            JButton jButton = new JButton(pasta.getTitle());
            jButton.setFont(fontPlain24);
            jButton.addActionListener(e -> buttonClicked(pasta));
            jButton.setPreferredSize(buttonSize);
            setConstraints(pasta.getPosX(), pasta.getPosY());
            add(jButton, gridBagConstraints);
        }
    }

    private void buttonClicked(Pasta pasta) {
        clipboard.setContents(new StringSelection(pasta.getText()), null);
        LOG.info("{} - position: ({}, {})", pasta.getText(), pasta.getPosX(), pasta.getPosY());
    }

}
