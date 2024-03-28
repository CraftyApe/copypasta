package de.craftyape.copypasta.entities;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
public class Pasta {

    private Point position;
    @Setter
    private String title;
    @Setter
    private String text;

    public Pasta(int x, int y){
        this.position = new Point(x, y);
        this.title = "";
        this.text = "";
    }

    public Pasta(String title, String text, int x, int y) {
        this.position = new Point(x, y);
        this.title = title;
        this.text = text;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public int getX() {
        return getPosition().x;
    }

    public int getY() {
        return getPosition().y;
    }

}
