package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class ConfigPanel extends ParentPanel {


    public ConfigPanel(List<Pasta> pastas) {
        this.setLayout(new GridBagLayout());
        this.pastas = pastas;
        initComponents();
    }

    private void initComponents() {
        Dimension titleSize = new Dimension(180, 28);
        Dimension textSize = new Dimension(1100, 28);

        for (Pasta pasta : pastas) {

            // title field
            JTextField titleField = new JTextField(pasta.getTitle());
            titleField.setMinimumSize(titleSize);
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
            titleField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (titleField.getText().length() >= 20) {e.consume();}
                }
            });

            // text field
            JTextField textField = new JTextField(pasta.getText());
            textField.setMinimumSize(textSize);
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

            titleField.setMargin(new Insets(0,0,0,0));
            titleField.setHorizontalAlignment(SwingConstants.LEFT);
            titleField.setFont(fontPlain12);
            setConstraints(0, pasta.getPosition() - 1);
            gridBagConstraints.weightx = 0.2;
            gridBagConstraints.insets = new Insets(0,0,-2,0);
            add(titleField, gridBagConstraints);
            textField.setMargin(new Insets(0,0,0,0));
            textField.setHorizontalAlignment(SwingConstants.LEFT);
            textField.setFont(fontPlain12);
            setConstraints(1, pasta.getPosition() - 1);
            gridBagConstraints.weightx = 0.8;
            gridBagConstraints.insets = new Insets(0,0,-2,0);
            add(textField, gridBagConstraints);
        }

    }

}
