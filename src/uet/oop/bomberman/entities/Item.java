package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Item extends Entity {
    private int type;
    public Item(int x, int y, Image img, int type) {
        super(x, y, img);
        this.type = type;
    }

    @Override
    public void update() {

    }
}
