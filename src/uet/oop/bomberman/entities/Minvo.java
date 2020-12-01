package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Minvo extends Enemy {
    public static ArrayList <ArrayList<Image> > constImage = new ArrayList<>();
    public static void load() {
        //up
        constImage.add(new ArrayList<Image>());
        //right
        constImage.add(new ArrayList<Image>());
        constImage.get(1).add(Sprite.minvo_right1.getFxImage());
        constImage.get(1).add(Sprite.minvo_right2.getFxImage());
        constImage.get(1).add(Sprite.minvo_right3.getFxImage());
        //down
        constImage.add(new ArrayList<Image>());
        //left
        constImage.add(new ArrayList<Image>());
        constImage.get(3).add(Sprite.minvo_left1.getFxImage());
        constImage.get(3).add(Sprite.minvo_left2.getFxImage());
        constImage.get(3).add(Sprite.minvo_left3.getFxImage());
        /// dead
        constImage.add(new ArrayList<Image>());
        constImage.get(4).add(Sprite.minvo_dead.getFxImage());
        constImage.get(4).add(Sprite.mob_dead1.getFxImage());
        constImage.get(4).add(Sprite.mob_dead2.getFxImage());
        constImage.get(4).add(Sprite.mob_dead3.getFxImage());
    }

    public Minvo(int x, int y, Image img) {
        super( x, y, img);
        this.speed = 2;
    }
    static Random ran = new Random();

    @Override
    public void update(long l) {
        if (this.isDeath()) {
            if (l >= timeChange) {
                timeChange += 400000000;
                ++curState;
                if (curState == 4) {
                    return;
                }
                this.img = constImage.get(4).get(curState);
            }
            return;
        }
        javafx.geometry.Rectangle2D rect = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Flame f : BombermanGame.flames) {
            if (rect.intersects(f.getX(), f.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                this.setDeath(true);
                this.curState = -1;
                this.timeChange = l;
                return;
            }
        }
        boolean findBomber = false;
        int newX = this.x;
        int newY = this.y;
        for (int i = 0; i < 4; ++i) {
            int curX = this.x;
            int curY = this.y;
            while (canMove(curX, curY)) {
                javafx.geometry.Rectangle2D curRect = new Rectangle2D(curX, curY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
                if (curRect.intersects(BombermanGame.player.getX(), BombermanGame.player.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                    newX = this.x + this.speed * xx[i];
                    newY = this.y + this.speed * yy[i];
                    findBomber = true;
                    break;
                }
                curX += this.speed * xx[i];
                curY += this.speed * yy[i];
            }
            if (findBomber) {
                break;
            }
        }
        if (!findBomber) {
            Collections.shuffle(randomDir);
            if (this.x % 32 == 0 && this.y % 32 == 0) {
                this.dir = ran.nextInt() % 4;
                if (this.dir < 0) {
                    this.dir += 4;
                }
            }
            newX = this.x + xx[this.dir] * this.speed;
            newY = this.y + yy[this.dir] * this.speed;
            if (!canMove(newX, newY)) {
                for (int id = 0; id < 4; ++id) {
                    int i = randomDir.get(id).intValue();
                    newX = this.x + xx[i] * this.speed;
                    newY = this.y + yy[i] * this.speed;
                    if (canMove(newX, newY)) {
                        this.dir = i;
                        break;
                    }
                }
                if (!canMove(newX, newY)) {
                    newX = this.x;
                    newY = this.y;
                }
            }
        }
        this.setX(newX);
        this.setY(newY);
        curState += 1;
        curState %= 9;
        if (this.dir == 1) {
            this.imgDir = 1;
        }
        if (this.dir == 3) {
            this.imgDir = 3;
        }
        this.img = constImage.get(this.imgDir).get(this.curState / 3);
    }
}