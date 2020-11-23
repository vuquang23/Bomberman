package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

public class Bomber extends Entity {
    private int speed = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        // 0: up
        ArrayList <Image> up = new ArrayList<>();
        up.add(Sprite.player_up.getFxImage());
        up.add(Sprite.player_up_1.getFxImage());
        up.add(Sprite.player_up_2.getFxImage());
        this.addState(up);

        // 1: right
        ArrayList <Image> right = new ArrayList<>();
        right.add(Sprite.player_right.getFxImage());
        right.add(Sprite.player_right_1.getFxImage());
        right.add(Sprite.player_right_2.getFxImage());
        this.addState(right);

        // 2: down
        ArrayList <Image> down = new ArrayList<>();
        down.add(Sprite.player_down.getFxImage());
        down.add(Sprite.player_down_1.getFxImage());
        down.add(Sprite.player_down_2.getFxImage());
        this.addState(down);

        // 3: left:
        ArrayList <Image> left = new ArrayList<>();
        left.add(Sprite.player_left.getFxImage());
        left.add(Sprite.player_left_1.getFxImage());
        left.add(Sprite.player_left_2.getFxImage());
        this.addState(left);
    }

    public boolean canMove(int aX, int aY) {
        double newX = x + aX * speed;
        double newY = y + aY * speed;
        Rectangle2D rect = new Rectangle2D(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Wall wall : BombermanGame.stillObjects) {
            if (rect.intersects(wall.getX(), wall.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                return false;
            }
        }
        for (Brick X : BombermanGame.bricks) {
            if (rect.intersects(X.getX(), X.getY(), Sprite.SCALED_SIZE,Sprite.SCALED_SIZE)) {
                return false;
            }

        }

        return true;
    }

    @Override
    public void update() {
        if (BombermanGame.bomberDirection == -1) {
            curState = 0;
            setImg();
            return;
        }
        int aX = 0, aY = 0;
        switch (BombermanGame.bomberDirection) {
            case (0):
                --aY;
                break;
            case (1):
                ++aX;
                break;
            case (2):
                ++aY;
                break;
            case (3):
                --aX;
        }
        if (canMove(aX, aY) == true) {
            this.addX(aX * speed);
            this.addY(aY * speed);
        }
        if (BombermanGame.bomberDirection == dir) {
            curState = (curState + 1) % 3;
            setImg();
        } else {
            dir = BombermanGame.bomberDirection;
            curState = 0;
            setImg();
        }
    }
}
