package de.craftyape.copypasta.ui;

public interface SceneChangeListener {
    default void switchToButtonPanel() {throw new UnsupportedOperationException("Invalid panel change!");}
    default void switchToConfigPanel() {throw new UnsupportedOperationException("Invalid panel change!");}
}
