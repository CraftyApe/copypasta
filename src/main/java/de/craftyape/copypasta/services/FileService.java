package de.craftyape.copypasta.services;

import com.google.gson.Gson;
import de.craftyape.copypasta.entities.Pasta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    Path filePath = Paths.get("pasta.json");
    Gson gson = new Gson();

    protected static final Logger log = LogManager.getLogger();

    public Pasta[] loadAllPasta() {
        try {
            return gson.fromJson(Files.readString(filePath, StandardCharsets.UTF_8), Pasta[].class);
        } catch (IOException e) {
            Pasta[] defaultPastas = new Pasta[20];
            for (int i = 0; i < 20; i++) {
                defaultPastas[i] = new Pasta(i + 1);
            }
            log.warn("File not found!");
            return defaultPastas;
        }
    }

    public void saveAllPasta(Pasta[] pastas) {
        try {
            String jsonArray = gson.toJson(pastas);
            Files.write(filePath, jsonArray.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.warn("Error while saving pasta!");
        }
    }

}
