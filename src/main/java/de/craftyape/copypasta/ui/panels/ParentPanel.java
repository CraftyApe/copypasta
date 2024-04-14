package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.ui.SceneChangeListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParentPanel extends JPanel {
    protected transient SceneChangeListener sceneChangeListener;
    protected static final Logger LOG = LogManager.getLogger(ParentPanel.class.getName());
    protected transient List<Pasta> pastas;

    protected final Font fontBold18 = new Font("Tahoma", Font.BOLD, 18);
    protected final Font fontPlain12 = new Font("Tahoma", Font.PLAIN, 12);
    protected GridBagConstraints gridBagConstraints;

    public void addListener(SceneChangeListener l) {sceneChangeListener = l;}
    public void removeListener(){sceneChangeListener = null;}

    protected void setConstraints(int gridx, int gridy) {
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
    }
}
