package de.craftyape.copypasta.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.craftyape.copypasta.entities.Pasta;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FileService {

    Path filePath = Paths.get("pasta.json");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<Pasta> loadAllPasta() {
        try {
            return Arrays.asList(gson.fromJson(Files.readString(filePath, StandardCharsets.UTF_8), Pasta[].class));
        } catch (IOException e) {
            Pasta[] defaultPastas = new Pasta[30];
            for (int i = 0; i < defaultPastas.length; i++) {
                defaultPastas[i] = new Pasta(i + 1);
            }
            log.warn("File not found!");
            return Arrays.asList(defaultPastas);
        }
    }

    public void saveAllPasta(List<Pasta> pastas) {
        try {
            String jsonArray = gson.toJson(pastas);
            Files.write(filePath, jsonArray.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.warn("Error while saving pasta!");
        }
    }

}
