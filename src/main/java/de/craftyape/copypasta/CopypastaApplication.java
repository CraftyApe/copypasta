package de.craftyape.copypasta;

import com.formdev.flatlaf.FlatDarculaLaf;
import de.craftyape.copypasta.ui.MainFrame;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class CopypastaApplication {

	private static final JDialog startUpDialog = new JDialog();
    private static Image icon;

	public static void main(final String[] args) {

        FlatDarculaLaf.setup();

        setIcon();
		log.info("Icon {}.", icon != null ? "successfully loaded" : "could not be loaded");

		showStartupDialog();

		Thread.currentThread().setUncaughtExceptionHandler((e, t) -> {
			log.error("Error on startup! {}", t.getMessage(), t);
			System.exit(0);
		});

        MainFrame mainframe = new MainFrame();
		mainframe.setSize(1280, 834);
		mainframe.setIconImage(icon);
		mainframe.setLocationRelativeTo(null);
		mainframe.setVisible(true);
		mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		startUpDialog.dispose();

		// bring to front
		mainframe.toFront();
		mainframe.requestFocus();
    }

	private static void showStartupDialog() {
		JOptionPane jOptionPane = new JOptionPane("Starting...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

		startUpDialog.setTitle("Start");
		startUpDialog.setModal(true);
		startUpDialog.setContentPane(jOptionPane);
		startUpDialog.setIconImage(icon);
		startUpDialog.setBackground(Color.GRAY);
		startUpDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		startUpDialog.pack();
		startUpDialog.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(() -> startUpDialog.setVisible(true));
	}

	private static void setIcon() {
		try {
			icon = ImageIO.read(Objects.requireNonNull(CopypastaApplication.class
					.getClassLoader().getResource("icon.png")));
		} catch (IOException | NullPointerException e) {
			log.warn("{}: {}",e.getClass() , e.getMessage());
		}
    }

}
