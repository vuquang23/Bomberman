package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class Bomb extends Entity {
    public static int len;
    private int sta[] = {0, 1, 2, 1, 0};
    public static ArrayList <ArrayList<Image> > constImage = new ArrayList<>();
    long timeChange;

    public static void load() {
        constImage.add(new ArrayList<Image>());
        constImage.get(0).add(Sprite.bomb.getFxImage());
        constImage.get(0).add(Sprite.bomb_1.getFxImage());
        constImage.get(0).add(Sprite.bomb_2.getFxImage());
    }

    public Bomb(int x, int y, Image img, long now) {
        super(x, y, img);
        this.len = 2;
        this.curState = 0;
        timeChange = now;
    }

    private boolean canMove(int x, int y) {
        javafx.geometry.Rectangle2D rect = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
//        if (rect.intersects(BombermanGame.player.getX(), BombermanGame.player.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
//            BombermanGame.player.death = true;
//        }
        for (Enemy b : BombermanGame.enemies) {
            if (rect.intersects(b.getX(), b.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                b.death = true;
            }
        }
        for (Wall wall : BombermanGame.stillObjects) {
            if (rect.intersects(wall.getX(), wall.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                return false;
            }
        }
        for (Brick a : BombermanGame.bricks) {
            if (rect.intersects(a.getX(), a.getY(), Sprite.SCALED_SIZE,Sprite.SCALED_SIZE)) {
                a.death = true;
                return false;
            }
        }
        return true;
    }

    public void update(long now) {
        int x;
        int y;
        if (now - timeChange >= 400000000) {
            ++curState;
            timeChange = now;
        }
        if (curState == 5) {
            curState = -1;
            BombermanGame.flames.add(new Flame(this.x / Sprite.SCALED_SIZE, this.y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 0, now));
            for (int i = 1; i <= len; ++i) {
                x = this.x - i * Sprite.SCALED_SIZE;
                y = this.y;
                if (canMove(x, y) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 4, now));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 5, now));
                }
            }
            for (int i = 1; i <= len; ++i) {
                x = this.x + i * Sprite.SCALED_SIZE;
                y = this.y;
                if (canMove(x, y) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 6, now));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 5, now));
                }
            }
            for (int i = 1; i <= len; ++i) {
                x = this.x;
                y = this.y - i * Sprite.SCALED_SIZE;
                if (canMove(x, y) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 1, now));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 2, now));
                }
            }
            for (int i = 1; i <= len; ++i) {
                x = this.x;
                y = this.y + i * Sprite.SCALED_SIZE;
                if (canMove(x, y) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 3, now));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 2, now));
                }
            }
            return;
        }
        this.img = constImage.get(0).get(sta[curState]);
    }
}