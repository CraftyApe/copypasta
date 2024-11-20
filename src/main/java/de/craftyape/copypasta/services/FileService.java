package de.craftyape.copypasta.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.craftyape.copypasta.CopypastaApplication;
import de.craftyape.copypasta.entities.Pasta;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FileService {

    private Path filePath;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private void getFilePath() {

        String classPath = CopypastaApplication.class.getName().replace('.', '/') + ".class";
        URL classURL = CopypastaApplication.class.getClassLoader().getResource(classPath);

        String fileName = "pasta.json";
        if (classURL != null) {
            try {
                // Remove leading "/" and everything after main p
                String path = classURL.toURI().getPath().substring(1, classURL.toURI().getPath().indexOf("classes/"));
                filePath = Paths.get(path + fileName).toAbsolutePath();
            } catch (URISyntaxException e) {
                log.error("Error loading absolute file path, using working directory.", e);
            }
        }
        filePath = Paths.get(fileName);
    }

    public List<Pasta> loadAllPasta() {
        if (filePath == null) {
            getFilePath();
            log.info("Save path of pasta: {}", filePath);
        }

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
            Files.writeString(filePath, jsonArray);
        } catch (IOException e) {
            log.warn("Error while saving pasta!");
        }
    }
}
