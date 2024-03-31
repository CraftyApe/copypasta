package de.craftyape.copypasta.ui;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.services.FileService;
import de.craftyape.copypasta.ui.panels.ButtonPanel;
import de.craftyape.copypasta.ui.panels.ConfigPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final Logger LOG = LogManager.getLogger(MainFrame.class);
    private final JPanel mainPanel = new JPanel();
    private ButtonPanel buttonPanel;
    private ConfigPanel configPanel;

    private boolean switchToButtonPanel = false;
    private boolean switchToConfigPanel = false;

    private Pasta[] pastas;
    private final transient FileService fileService = new FileService();

    public MainFrame() {
        reloadPasta();
        initComponents();
        switchToButtonPanel = true;

        if (switchToButtonPanel) {
            switchToButtonPanel = false;
            setButtonScene();
        }
        if (switchToConfigPanel) {
            switchToConfigPanel = false;
            setConfigScene();
        }
    }


    private void initComponents() {
        JMenuItem load = new JMenuItem();
        JMenuItem save = new JMenuItem();
        JMenuItem exit = new JMenuItem();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();
        JPopupMenu.Separator menuSeparator = new JPopupMenu.Separator();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("CopyPasta");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1280, 1280)
        );

        // Set menu items
        menu.setText("File");

        load.setText("Load");
        load.addActionListener(e -> reloadPasta());

        save.setText("Save");
        save.addActionListener(e -> fileService.saveAllPasta(pastas));

        exit.setText("Exit");
        exit.addActionListener(e -> System.exit(0));

        menu.add(load);
        menu.add(save);
        menu.add(menuSeparator);
        menu.add(exit);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

    }

    private void reloadPasta() {
        pastas = fileService.loadAllPasta();
        mainPanel.repaint();
    }

    public void setButtonScene() {
        mainPanel.removeAll();
        buttonPanel = new ButtonPanel(pastas);
        buttonPanel.addListener(new SceneChangeListener() {
            @Override
            public void switchToConfigPanel() {
                buttonPanel.removeListener();
                switchToConfigPanel = true;
            }
        });
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(buttonPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void setConfigScene() {
        // blergh
    }

}
