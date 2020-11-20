package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class Balloon extends Enemy {
    public Balloon(int x, int y, Image img) {
        super( x, y, img);
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
    static Random ran = new Random();
    static int[] xx = {0, 4, 0, -4};
    static int[] yy = {-4, 0, 4, 0};
    @Override
    public void update() {
        int nextDir = ran.nextInt() % 4;
        if (nextDir < 0) nextDir += 4;
        this.addX(xx[nextDir]);
        this.addY(yy[nextDir]);
        curState += 1;
        curState %= 3;
        if (nextDir == 1) {
            this.dir = 1;
        }
        if (nextDir == 3) {
            this.dir = 3;
        }
        this.img = constImage.get(this.dir).get(this.curState);
    }
}
