package de.craftyape.copypasta.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
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
    private ConfigPanel configPanel;
    private CurrentPanel currentPanel;
    private CurrentLaf currentLaf;

    private List<Pasta> pastas;
    private final transient FileService fileService = new FileService();

    public MainFrame() {
        reloadPasta();
    }


    private void initComponents() {
        if (currentPanel == null) {
            currentPanel = CurrentPanel.BUTTONS;
        }

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu();
        JMenuItem load = new JMenuItem();
        JMenuItem save = new JMenuItem();
        JPopupMenu.Separator menuSeparator = new JPopupMenu.Separator();
        JMenuItem exit = new JMenuItem();

        JMenuItem lafToggle = new JMenuItem();
        JMenuItem menuSceneToggle = new JMenuItem();


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

        // Look-and-feel toogle
        if (currentLaf == null) {currentLaf = CurrentLaf.DARK;}
        if (currentLaf == CurrentLaf.DARK) {
            lafToggle.removeAll();
            lafToggle.setText("LIGHT MODE");
            lafToggle.addActionListener(e -> {
                FlatLightLaf.setup();
                currentLaf = CurrentLaf.LIGHT;
                reloadPasta();
            });
        }
        if (currentLaf == CurrentLaf.LIGHT) {
            lafToggle.removeAll();
            lafToggle.setText("DARK MODE");
            lafToggle.addActionListener(e -> {
                FlatDarculaLaf.setup();
                currentLaf = CurrentLaf.DARK;
                reloadPasta();
            });
        }

        // Scene toggle
        if (currentPanel == CurrentPanel.BUTTONS) {
            menuSceneToggle.removeAll();
            menuSceneToggle.setText("CONFIG");
            menuSceneToggle.addActionListener(e -> setConfigScene());
        }
        if (currentPanel == CurrentPanel.CONFIG) {
            menuSceneToggle.removeAll();
            menuSceneToggle.setText("BUTTONS");
            menuSceneToggle.addActionListener(e -> setButtonScene());
        }

        // set Menu bar
        menuBar.add(menuFile);
        menuBar.add(lafToggle);
        menuBar.add(menuSceneToggle);
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
        if (currentPanel == CurrentPanel.BUTTONS || currentPanel == null) {
            setButtonScene();
        }
        if (currentPanel == CurrentPanel.CONFIG) {
            setConfigScene();
        }
    }

    public void setButtonScene() {
        buttonPanel = new ButtonPanel(pastas);
        buttonPanel.addListener(new SceneChangeListener() {
            @Override
            public void switchToConfigPanel() {
                buttonPanel.removeListener();
                currentPanel = CurrentPanel.CONFIG;
            }
        });
        currentPanel = CurrentPanel.BUTTONS;
        initComponents();
        refreshMainPanel(buttonPanel);
    }

    public void setConfigScene() {
        configPanel = new ConfigPanel(pastas);
        configPanel.addListener(new SceneChangeListener() {
            @Override
            public void switchToButtonPanel() {
                configPanel.removeListener();
                currentPanel = CurrentPanel.BUTTONS;
            }
        });
        currentPanel = CurrentPanel.CONFIG;
        initComponents();
        refreshMainPanel(configPanel);
    }

    public void refreshMainPanel(ParentPanel panel) {
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
