package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.utility.LengthFilter;
import de.craftyape.copypasta.utility.LineSpacingHighlighter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;

@Getter
@Slf4j
public class ConfigPanel extends ParentPanel {

    int scrollIncrement;

    public ConfigPanel(List<Pasta> pastas) {
        this.setSize(new Dimension(super.getWidth(), 2 * super.getHeight()));
        this.setLayout(new GridBagLayout());
        this.pastas = pastas;
        initComponents();
    }

    private void initComponents() {

        for (Pasta pasta : pastas) {

            // title field
            JTextField titleField = new JTextField(pasta.getTitle());
            setCharacterLimit(titleField, 20);
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
            JTextArea textArea = new JTextArea(pasta.getText(), 2, 130);
            setCharacterLimit(textArea, 240);

            textArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    pasta.setText(textArea.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    pasta.setText(textArea.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    pasta.setText(textArea.getText());
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

            textArea.setMargin(new Insets(0, 4, 0, 4));
            textArea.setBorder(titleField.getBorder());
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            int lineSpacing = 2;
            LineSpacingHighlighter.setSpacing(textArea, lineSpacing);
            textArea.setAlignmentX(SwingConstants.LEFT);
            textArea.setAlignmentY(SwingConstants.CENTER);
            textArea.setFont(fontPlain12);
            textArea.setMinimumSize(new Dimension(this.getWidth() - titleField.getWidth(), 2 * textArea.getFont().getSize() + lineSpacing));
            textArea.setCaretPosition(0);
            setConstraints(1, pasta.getPosition() - 1);
            gridBagConstraints.weighty = 1d/30;
            gridBagConstraints.insets = new Insets(0, 0, -2, 0);
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            add(textArea, gridBagConstraints);
        }
        this.revalidate();
        this.repaint();
        EventQueue.invokeLater(() -> {
            this.scrollIncrement = this.getComponent(0).getFont().getSize()-5;
            JScrollPane parentScrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, this);
            if (parentScrollPane != null) {
                parentScrollPane.getVerticalScrollBar().setUnitIncrement(this.scrollIncrement);
            }
        });
    }

    private void setCharacterLimit(JTextComponent textComponent, int limit) {
        ((AbstractDocument) textComponent.getDocument()).setDocumentFilter(new LengthFilter(limit));
    }

}