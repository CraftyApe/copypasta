package de.craftyape.copypasta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Pasta {

    private int position;
    private String title;
    private String text;

    public Pasta(int position){
        this.position = position;
        this.title = "[not set]";
        this.text = "";
    }
}
