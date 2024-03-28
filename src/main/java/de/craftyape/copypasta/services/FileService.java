package de.craftyape.copypasta.services;

import com.google.gson.Gson;
import de.craftyape.copypasta.entities.Pasta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    String filePath = "pasta.json";
    Gson gson = new Gson();
    public FileService() {
    }

    protected static final Logger log = LogManager.getLogger();

    public Pasta[] loadAllPasta() {
        try {
            return gson.fromJson(new FileReader(filePath), Pasta[].class);
        } catch (FileNotFoundException e) {
            log.warn("File not found!");
            return null;
        }
    }

    public String saveAllPasta(Pasta[] pastaArray) {
        try {
            gson.toJson(pastaArray, new FileWriter(filePath));
            return "Pasta saved.";
        } catch (IOException e) {
            log.warn("Error while saving pasta!");
            return "Error while saving pasta!";
        }
    }

}
