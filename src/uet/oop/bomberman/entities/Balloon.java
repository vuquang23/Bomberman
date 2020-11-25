package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class Balloon extends Enemy {
    public static ArrayList <ArrayList<Image> > constImage = new ArrayList<>();

    public static void load() {
        //up
        constImage.add(new ArrayList<Image>());
        //right
        constImage.add(new ArrayList<Image>());
        constImage.get(1).add(Sprite.balloom_right1.getFxImage());
        constImage.get(1).add(Sprite.balloom_right2.getFxImage());
        constImage.get(1).add(Sprite.balloom_right3.getFxImage());
        //down
        constImage.add(new ArrayList<Image>());
        //left
        constImage.add(new ArrayList<Image>());
        constImage.get(3).add(Sprite.balloom_left1.getFxImage());
        constImage.get(3).add(Sprite.balloom_left2.getFxImage());
        constImage.get(3).add(Sprite.balloom_left3.getFxImage());
    }

    public Balloon(int x, int y, Image img) {
        super( x, y, img);

    }
    static Random ran = new Random();

    @Override
    public void update(long l) {
        while (true) {
            if (this.x % 32 == 0 && this.y % 32 == 0) {
                this.dir = ran.nextInt() % 4;
                if (this.dir < 0) {
                    this.dir += 4;
                }
            }
            int newX = this.x + xx[this.dir] * this.speed;
            int newY = this.y + yy[this.dir] * this.speed;
            if (canMove(newX, newY) == false) {
                continue;
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
            break;
        }
    }
}
