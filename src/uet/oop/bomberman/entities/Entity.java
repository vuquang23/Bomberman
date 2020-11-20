package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    ArrayList <ArrayList<Image> > constImage;
    protected int dir;
    protected int curState;

    public Entity( int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.dir = 0;
        this.curState = 0;
        constImage = new ArrayList<>();
    }

    public void addState(ArrayList <Image> state) {
        constImage.add(state);
    }

    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }
    public abstract void update();
}
