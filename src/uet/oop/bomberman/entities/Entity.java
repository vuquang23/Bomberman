package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public abstract class Entity {
    protected double x;
    protected double y;
    protected Image img;
    ArrayList <ArrayList<Image> > constImage;
    protected int dir;
    protected int curState;
    protected Image background = Sprite.grass.getFxImage();
    public Entity( int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
        this.dir = 1;
        this.curState = 0;
        constImage = new ArrayList<>();
    }
    public void addX(double val) {
        this.x += val;
        if (this.x > 1024) this.x = 1024;
        if (this.x < 32) this.x = 32;
    }
    public void addY(double val) {
        this.y += val;
        if (this.y > 1024) this.y = 1024;
        if (this.y < 32) this.y = 32;
    }

    public void setImg() {
        img = constImage.get(dir).get(curState);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void addState(ArrayList <Image> state) {
        constImage.add(state);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
