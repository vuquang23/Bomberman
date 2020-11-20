package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.Random;

public class Balloon extends Enemy {
    public Balloon(int x, int y, Image img) {
        super( x, y, img);
    }
    static Random ran = new Random();
    static int[] xx = {0, 0, -4, 4};
    static int[] yy = {-4, 4, 0, 0};
    @Override
    public void update() {
        int nextDir = ran.nextInt() % 4;
        if (nextDir < 0) nextDir += 4;
        this.addX(xx[nextDir]);
        this.addY(yy[nextDir]);
    }
}
