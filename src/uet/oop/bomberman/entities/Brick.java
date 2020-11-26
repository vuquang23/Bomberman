package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super( x, y, img);
    }
    public static ArrayList <ArrayList<Image>> constImage = new ArrayList<>();
    public static void load() {
        constImage.add(new ArrayList<>());
        constImage.get(0).add(Sprite.brick_exploded.getFxImage());
        constImage.get(0).add(Sprite.brick_exploded1.getFxImage());
        constImage.get(0).add(Sprite.brick_exploded2.getFxImage());

    }
    @Override
    public void update(long l) {
        if (this.isDeath()) {
            if (l >= timeChange) {
                timeChange += 200000000;
                ++curState;
                if (curState == 3) {
                    return;
                }
                this.img = constImage.get(0).get(curState);
            }
            return;
        }
    }

}
