package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends ParentPanel {

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
    LOG.info("{} - position: ({}, {})", pasta.getText(), pasta.getPosX(), pasta.getPosY());
}

}
