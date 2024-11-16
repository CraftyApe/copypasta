package de.craftyape.copypasta.ui.panels;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.ui.SceneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParentPanel extends JPanel {

    protected transient SceneChangeListener sceneChangeListener;
    protected transient List<Pasta> pastas;

    protected final Font fontBold18 = new Font("Lucida Console", Font.BOLD, 18);
    protected final Font fontPlain12 = new Font("Lucida Console", Font.PLAIN, 14);
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
