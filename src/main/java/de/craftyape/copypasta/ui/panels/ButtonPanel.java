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
        this.setMaximumSize(new Dimension(1280, 720));
        this.setLayout(new GridLayout(5, 5));
        this.pastas = pastas;
        initComponents();
    }

    private void initComponents() {

        Dimension buttonSize = new Dimension(160, 120);

        for (Pasta pasta : pastas) {
            JButton jButton = new JButton(pasta.getTitle());
            jButton.setFont(fontBold18);
            jButton.addActionListener(e -> buttonClicked(pasta));
            jButton.setPreferredSize(buttonSize);
            jButton.setMaximumSize(buttonSize);
            setConstraints(pasta.getPosX(), pasta.getPosY());
            gridBagConstraints.weightx = .2;
            gridBagConstraints.weighty = .2;
            add(jButton, gridBagConstraints);
        }
    }

    private void buttonClicked(Pasta pasta) {
        clipboard.setContents(new StringSelection(pasta.getText()), null);
        LOG.info("{} - position: ({}, {})", pasta.getText(), pasta.getPosX(), pasta.getPosY());
    }

}
