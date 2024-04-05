package de.craftyape.copypasta.ui;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.services.FileService;
import de.craftyape.copypasta.ui.panels.ButtonPanel;
import de.craftyape.copypasta.ui.panels.ConfigPanel;
import de.craftyape.copypasta.ui.panels.ParentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private final JPanel mainPanel = new JPanel();
    private ButtonPanel buttonPanel;
    private CurrentPanel currentPanel;

    private boolean switchToButtonPanel = false;
    private boolean switchToConfigPanel = false;

    private List<Pasta> pastas;
    private final transient FileService fileService = new FileService();

    public MainFrame() {
        reloadPasta();
    }


    private void initComponents() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu();
        JMenuItem load = new JMenuItem();
        JMenuItem save = new JMenuItem();
        JPopupMenu.Separator menuSeparator = new JPopupMenu.Separator();
        JMenuItem exit = new JMenuItem();

        JMenu menuView = new JMenu();
        JMenuItem buttons = new JMenuItem();
        JMenuItem config = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("CopyPasta");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1280, 1280)
        );

    // Set menu items
        // File menu
        menuFile.setText("File");
        load.setText("Load");
        load.addActionListener(e -> reloadPasta());
        save.setText("Save");
        save.addActionListener(e -> fileService.saveAllPasta(pastas));
        exit.setText("Exit");
        exit.addActionListener(e -> System.exit(0));
        menuFile.add(load);
        menuFile.add(save);
        menuFile.add(menuSeparator);
        menuFile.add(exit);

        // View menu
        menuView.setText("View");
        buttons.setText("Buttons");
        buttons.addActionListener(e -> setButtonScene());
        config.setText("Configuration");
        config.addActionListener(e -> setConfigScene());
        if (currentPanel != CurrentPanel.BUTTONS) {menuView.add(buttons);}
        if (currentPanel != CurrentPanel.CONFIG) {menuView.add(config);}

        // set Menu bar
        menuBar.add(menuFile);
        menuBar.add(menuView);
        setJMenuBar(menuBar);

        // set Layout and show mainPanel
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
        initComponents();
        if (!switchToButtonPanel && !switchToConfigPanel) {switchToButtonPanel = true;}
        if (switchToButtonPanel) {
            switchToButtonPanel = false;
            setButtonScene();
        }
        if (switchToConfigPanel) {
            switchToConfigPanel = false;
            setConfigScene();
        }
    }

    public void setButtonScene() {
        buttonPanel = new ButtonPanel(pastas);
        buttonPanel.addListener(new SceneChangeListener() {
            @Override
            public void switchToConfigPanel() {
                buttonPanel.removeListener();
                switchToConfigPanel = true;
            }
        });
        refreshMainPanel(buttonPanel);
        currentPanel = CurrentPanel.BUTTONS;
    }

    public void setConfigScene() {
        mainPanel.removeAll();
        ConfigPanel configPanel = new ConfigPanel(pastas);
        refreshMainPanel(configPanel);
        currentPanel = CurrentPanel.CONFIG;
    }

    public void refreshMainPanel(ParentPanel panel) {
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
