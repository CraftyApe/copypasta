package de.craftyape.copypasta.utility;

import lombok.extern.slf4j.Slf4j;

import javax.swing.text.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

@Slf4j
// Custom Highlighter class to add spacing between lines
public class LineSpacingHighlighter implements Highlighter.HighlightPainter {

    private final int spacing;

    private LineSpacingHighlighter(int spacing) {
        this.spacing = spacing;
    }

    public static void setSpacing(JTextComponent component, int spacing) {
        DefaultHighlighter highlighter = new DefaultHighlighter();
        component.setHighlighter(highlighter);
        try {
            highlighter.addHighlight(0, component.getText().length(), new LineSpacingHighlighter(spacing));
        } catch (BadLocationException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void paint(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c) {
        Rectangle alloc = (bounds instanceof Rectangle rectangle) ? rectangle : bounds.getBounds();
        g.setColor(c.getBackground());
        g.fillRect(alloc.x, alloc.y + spacing, alloc.width, alloc.height);

        try {
            Rectangle2D rect0 = c.getUI().modelToView2D(c, offs0, Position.Bias.Forward);
            Rectangle2D rect1 = c.getUI().modelToView2D(c, offs1, Position.Bias.Forward);

            g.setColor(c.getBackground());
            g.fillRect((int) rect0.getX(), (int) rect0.getY() + spacing, (int) rect1.getWidth(), (int) rect1.getHeight() - (int) rect0.getHeight() + spacing);

            g.setColor(c.getForeground());
            int y = (int) rect0.getY();
            int lineHeight = c.getFontMetrics(c.getFont()).getHeight() + spacing;
            while (y <= rect1.getY()) {
                g.drawLine((int) rect0.getX(), y, (int) rect0.getX() + (int) rect0.getWidth(), y);
                y += lineHeight;
            }
        } catch (BadLocationException e) {
            log.error(e.getMessage(), e);
        }
    }
}