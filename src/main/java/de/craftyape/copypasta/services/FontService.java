package de.craftyape.copypasta.services;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FontService {

    private FontService() {}

    public static Font loadFont(String path, float size) {
        try (InputStream inputStream = FontService.class.getResourceAsStream(path)) {
            if (inputStream != null) {
                Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(size);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
                return font;
            } else {
                log.error("Font file not found at {}", path);
                return null;
            }
        } catch (IOException | FontFormatException e) {
            log.error("Error loading font: '{}'", path, e);
            return null;
        }
    }
}
