package uet.oop.bomberman.entities;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    protected int dir;
    protected int curState;
    protected Image background = Sprite.grass.getFxImage();

    public Entity( int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
        this.dir = 1;
        this.curState = 0;

    }
    public void addX(int val) {
        this.x += val;
    }
    public void addY(int val) {
        this.y += val;
    }

    public int getCurState() {
        return curState;
    }

    public void setImg(Image newImg) {
        img = newImg;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
