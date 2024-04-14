package de.craftyape.copypasta;

import com.formdev.flatlaf.FlatDarculaLaf;
import de.craftyape.copypasta.ui.MainFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class CopypastaApplication {

	private static final JDialog dialog = new JDialog();
	private static final Logger LOG = LogManager.getLogger(CopypastaApplication.class);
    private static Image icon;

	public static void main(final String[] args) {

		FlatDarculaLaf.setup();

        setIcon();
		LOG.info("Icon {}.", icon != null ? "successfully loaded" : "could not be loaded");

		showStartupDialog();

		Thread.currentThread().setUncaughtExceptionHandler((e, t) -> {
			LOG.error("Error on startup! {}", t.getMessage());
			System.exit(0);
		});

		dialog.dispose();
        MainFrame mainframe = new MainFrame();
		mainframe.setSize(1280, 720);
		mainframe.setIconImage(icon);
		mainframe.setLocationRelativeTo(null);
		mainframe.setVisible(true);
		mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	private static void showStartupDialog() {
		JOptionPane jOptionPane = new JOptionPane("Starting...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

		dialog.setTitle("Start");
		dialog.setModal(true);
		dialog.setContentPane(jOptionPane);
		dialog.setIconImage(icon);
		dialog.setBackground(Color.GRAY);
		dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(() -> dialog.setVisible(true));
	}

	private static void setIcon() {
		try {
			icon = ImageIO.read(Objects.requireNonNull(CopypastaApplication.class
					.getClassLoader().getResource("icon.png")));
		} catch (IOException | NullPointerException e) {
			LOG.warn("{}: {}",e.getClass() , e.getMessage());
		}
    }

}
