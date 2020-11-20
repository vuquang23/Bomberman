package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import javax.xml.stream.XMLInputFactory;
import java.util.ArrayList;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    ArrayList <ArrayList<Image> > constImage;
    protected int dir;
    protected int curState;
    protected Image background = Sprite.grass.getFxImage();
    public Entity( int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
        this.dir = 0;
        this.curState = 0;
        constImage = new ArrayList<>();
    }
    public void addX(int val) {
        this.x += val;
        if (this.x > 1024) this.x = 1024;
        if (this.x < 32) this.x = 32;
    }
    public void addY(int val) {
        this.y += val;
        if (this.y > 1024) this.y = 1024;
        if (this.y < 32) this.y = 32;
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

    public void addState(ArrayList <Image> state) {
        constImage.add(state);
    }

    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, x, y);
    }
    public abstract void update();
}
