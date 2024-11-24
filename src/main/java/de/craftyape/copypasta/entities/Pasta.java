package de.craftyape.copypasta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

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

    public int getPosX() {
        return (position - 1) % 5;
    }

    public int getPosY() {
        return (position - 1) / 5;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pasta other = (Pasta) o;
        return position == other.position && Objects.equals(title, other.title) && Objects.equals(text, other.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, title, text);
    }
}
