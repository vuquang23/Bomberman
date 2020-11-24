package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super( x, y, img);
    }
    public static ArrayList <ArrayList<Image>> constImage = new ArrayList<>();

    @Override
    public void update() {

    }

}
