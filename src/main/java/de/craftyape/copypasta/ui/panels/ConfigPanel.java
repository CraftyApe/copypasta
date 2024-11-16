package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.utility.LengthFilter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.List;

@Getter
@Slf4j
public class ConfigPanel extends ParentPanel {

    int scrollIncrement;

    public ConfigPanel(List<Pasta> pastas) {
        this.setSize(super.getSize());
        this.setLayout(new GridBagLayout());
        this.pastas = pastas;
        initComponents();
    }

    private void initComponents() {

        for (Pasta pasta : pastas) {

            // title field
            JTextField titleField = new JTextField(pasta.getTitle(), 20);
            setCharacterLimit(titleField);
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
            // TODO: Give title fields double height, make text fields JTextArea:
            // JTextArea textArea = new JTextArea(10, 30);
            // textArea.setLineWrap(true);
            // textArea.setWrapStyleWord(true);

            // text field
            JTextField textField = new JTextField(pasta.getText(), 240);
            ((AbstractDocument) textField.getDocument()).setDocumentFilter(new LengthFilter(textField.getColumns()));
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

            // Sizing and positioning them
            titleField.setMargin(new Insets(5, 4, 5, 4));
            titleField.setHorizontalAlignment(SwingConstants.LEFT);
            titleField.setFont(fontPlain12);
            setConstraints(0, pasta.getPosition() - 1);
            gridBagConstraints.weighty = 1d/30;
            gridBagConstraints.insets = new Insets(0, 0, -2, 0);
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            add(titleField, gridBagConstraints);
            textField.setMargin(new Insets(0, 4, 0, 4));
            textField.setHorizontalAlignment(SwingConstants.LEFT);
            textField.setFont(fontPlain12);
            textField.setCaretPosition(0);
            setConstraints(1, pasta.getPosition() - 1);
            gridBagConstraints.weighty = 1d/30;
            gridBagConstraints.insets = new Insets(0, 0, -2, 0);
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            add(textField, gridBagConstraints);
        }
        this.revalidate();
        this.repaint();
        EventQueue.invokeLater(() -> {
            this.scrollIncrement = this.getComponent(0).getFont().getSize()-5;
            JScrollPane parentScrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, this);
            if (parentScrollPane != null) {
                parentScrollPane.getVerticalScrollBar().setUnitIncrement(this.scrollIncrement);
            }
            for (int i = 0; i < this.getComponents().length; i++) {
                if (i%2==0) {
                    JTextField titleField = (JTextField) this.getComponent(i);
                    JTextField textField = (JTextField) this.getComponent(i+1);
                    log.info("pasta: {}, title size: {}x{}, text size: {}x{}", titleField.getText(), titleField.getWidth(), titleField.getHeight(), textField.getWidth(), textField.getHeight());
                }
            }
        });
    }

    private void setCharacterLimit(JTextField jTextField) {
        ((AbstractDocument) jTextField.getDocument()).setDocumentFilter(new LengthFilter(jTextField.getColumns()));
    }

}
