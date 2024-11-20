package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.ui.SceneChangeListener;
import de.craftyape.copypasta.utility.FontUtils;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Slf4j
public class ParentPanel extends JPanel {

    protected transient SceneChangeListener sceneChangeListener;
    protected transient List<Pasta> pastas;

    protected static final String FONT_MEDIUM_PATH = "/fonts/NotoSansMono_SemiCondensed-SemiBold.ttf";
    protected static final String FONT_BOLD_PATH = "/fonts/NotoSansMono_SemiCondensed-Black.ttf";
    protected static final String FONT_FALLBACK = "Lucida Console";

    protected Font fontBold14;
    protected Font fontBold18;
    protected GridBagConstraints gridBagConstraints;

    public void addListener(SceneChangeListener l) {
        sceneChangeListener = l;
    }

    public void removeListener() {
        sceneChangeListener = null;
    }

    protected void setConstraints(int gridX, int gridY) {
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridX;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
    }

    protected Font getFontBold14() {
        if (fontBold14 == null) {
            loadCustomFonts();
        }
        return fontBold14 != null ? fontBold14 : new Font(FONT_FALLBACK, Font.BOLD, 14);
    }

    protected Font getFontBold18() {
        if (fontBold18 == null) {
            loadCustomFonts();
        }
        return fontBold18 != null ? fontBold18 : new Font(FONT_FALLBACK, Font.BOLD, 18);
    }

    protected void loadCustomFonts() {
        fontBold14 = FontUtils.loadFont(FONT_MEDIUM_PATH, 14);
        fontBold18 = FontUtils.loadFont(FONT_BOLD_PATH, 18);
    }
}