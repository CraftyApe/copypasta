package de.craftyape.copypasta.ui;

import de.craftyape.copypasta.entities.Pasta;
import de.craftyape.copypasta.services.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final Logger LOG = LogManager.getLogger(MainFrame.class);
    private final JPanel mainPanel = new JPanel();
    private JPanel configPanel;
    private Pasta[] pastas;
    private final transient FileService fileService = new FileService();

    public MainFrame() {
        reloadPasta();
        initComponents();
        for (Pasta pasta : pastas) {
            LOG.info(pasta.getText());
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

    interface SceneChangeListener {
        default void switchToButtonPanel() {throw new UnsupportedOperationException("Invalid panel change!");}
        default void switchToConfigPanel() {throw new UnsupportedOperationException("Invalid panel change!");}
    }

}
