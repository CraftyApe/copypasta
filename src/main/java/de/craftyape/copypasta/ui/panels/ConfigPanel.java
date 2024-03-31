package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class ConfigPanel extends ParentPanel {


    public ConfigPanel(List<Pasta> pastas) {
        this.setLayout(new GridLayout(20, 1));
        this.pastas = pastas;
        initComponents();
    }

    private void initComponents() {
        Dimension titleSize = new Dimension(180, 10);
        Dimension textSize = new Dimension(1200, 20);

        for (Pasta pasta : pastas) {

            // title field
            JTextField titleField = new JTextField(pasta.getTitle());
            titleField.setPreferredSize(titleSize);
            titleField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    pasta.setTitle(titleField.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    pasta.setTitle(titleField.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    pasta.setTitle(titleField.getText());
                }
            });

            // text field
            JTextField textField = new JTextField(pasta.getText());
            textField.setPreferredSize(textSize);
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    pasta.setText(textField.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    pasta.setText(textField.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    pasta.setText(textField.getText());
                }
            });

            add(titleField, gridBagConstraints);
            add(textField, gridBagConstraints);
        }

    }

}
