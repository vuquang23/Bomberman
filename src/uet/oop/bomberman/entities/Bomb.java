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

    public static void load() {
        constImage.add(new ArrayList<Image>());
        constImage.get(0).add(Sprite.bomb.getFxImage());
        constImage.get(0).add(Sprite.bomb_1.getFxImage());
        constImage.get(0).add(Sprite.bomb_2.getFxImage());
    }

    public Bomb(int x, int y, Image img, long timeChange) {
        super(x, y, img, timeChange);
        this.len = 2;
        this.curState = -1;
    }

    private boolean canMove(int x, int y, long l) {
        javafx.geometry.Rectangle2D rect = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        if (rect.intersects(BombermanGame.player.getX(), BombermanGame.player.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
            BombermanGame.player.death = true;
            BombermanGame.player.curState = -1;
            BombermanGame.player.timeChange = l;

        }
        for (Enemy b : BombermanGame.enemies) {
            if (rect.intersects(b.getX(), b.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                b.death = true;
                b.curState = -1;
                b.timeChange = l;
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
                a.curState = -1;
                a.timeChange = l;
                return false;
            }
        }
        return true;
    }
    @Override
    public void update(long l) {
        if (this.death) {
            return;
        }
        int x;
        int y;
        if (l >= this.timeChange) {
            ++curState;
            this.timeChange += 400000000;
        }

        if (curState == 5) {
            curState = -1;
            this.death = true;
            BombermanGame.flames.add(new Flame(this.x / Sprite.SCALED_SIZE, this.y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 0, l));
            for (int i = 1; i <= len; ++i) {
                x = this.x - i * Sprite.SCALED_SIZE;
                y = this.y;
                if (canMove(x, y, l) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 4, l));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 5, l));
                }
            }
            for (int i = 1; i <= len; ++i) {
                x = this.x + i * Sprite.SCALED_SIZE;
                y = this.y;
                if (canMove(x, y, l) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 6, l));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 5, l));
                }
            }
            for (int i = 1; i <= len; ++i) {
                x = this.x;
                y = this.y - i * Sprite.SCALED_SIZE;
                if (canMove(x, y, l) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 1, l));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 2, l));
                }
            }
            for (int i = 1; i <= len; ++i) {
                x = this.x;
                y = this.y + i * Sprite.SCALED_SIZE;
                if (canMove(x, y, l) == false) {
                    break;
                }
                if (i == len) {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 3, l));
                }
                else {
                    BombermanGame.flames.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.oneal_right1.getFxImage(), 2, l));
                }
            }
            return;
        }
        this.img = constImage.get(0).get(sta[curState]);
    }
}