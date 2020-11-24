package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class Bomb extends Entity {
    public static int len;
    private int sta[] = {0, 1, 2, 1, 0};
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        this.len = 1;
        this.curState = -1;
        constImage.add(new ArrayList<Image>());
        constImage.get(0).add(Sprite.bomb.getFxImage());
        constImage.get(0).add(Sprite.bomb_1.getFxImage());
        constImage.get(0).add(Sprite.bomb_2.getFxImage());
    }
    private boolean canMove(int x, int y) {
        return false;
    }
    @Override
    public void update() {
        int x;
        int y;
        ++curState;
        if (curState == 5) {
            curState = -1;
            BombermanGame.flames.add(new Flame(this.x, this.y, Sprite.oneal_right1.getFxImage(), 0));
            for (int i = 1; i <= len; ++i) {
                x = this.x - i * Sprite.SCALED_SIZE;
                y = this.y;
                if (canMove(x, y) == false) {
                    continue;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x, y, Sprite.oneal_right1.getFxImage(), 2));
                }
                else {
                    BombermanGame.flames.add(new Flame(x, y, Sprite.oneal_right1.getFxImage(), 2));
                }
            }

            return;
        }
        this.img = constImage.get(0).get(sta[curState]);
    }
}
