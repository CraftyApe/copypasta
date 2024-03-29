package de.craftyape.copypasta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor
public class Pasta implements Serializable {

    private int position;
    private String title;
    private String text;

    public Pasta(int position){
        this.position = position;
        this.title = "[not set]";
        this.text = "Default pasta from button " + position;
    }
}
