package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Item extends Entity {
//   1: bomb, 2: flame, 3: speed, 4: portal
    private int type;
    public Item(int x, int y, Image img, int type) {
        super(x, y, img);
        this.type = type;
    }

    @Override
    public void update() {

    }
}
