package de.craftyape.copypasta;

import javax.swing.*;

public class CopypastaApplication {

	CopypastaApplication(String[] args) {
		// Initialize by loading json if it exists, otherwise set default pasta[]
	}

	public void show() {
		// show UI
	}

	public static void main(final String[] args) {
		// ...
		SwingUtilities.invokeLater(() -> new CopypastaApplication(args).show());
	}

}
