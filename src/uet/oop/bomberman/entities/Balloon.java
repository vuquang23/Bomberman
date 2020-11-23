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
    @Override
    public void update() {
        while (true) {
            int nextDir = ran.nextInt() % 4;
            if (nextDir < 0) nextDir += 4;
            int newX = this.x + xx[nextDir] * this.speed;
            int newY = this.y + yy[nextDir] * this.speed;
            if (canMove(newX, newY) == false) {
                continue;
            }
            this.setX(newX);
            this.setY(newY);
            curState += 1;
            curState %= 3;
            if (nextDir == 1) {
                this.dir = 1;
            }
            if (nextDir == 3) {
                this.dir = 3;
            }
            this.img = constImage.get(this.dir).get(this.curState);
            break;
        }
    }
}
