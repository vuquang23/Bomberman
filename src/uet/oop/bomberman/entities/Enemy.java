package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Enemy extends Entity {
    protected int speed;
    protected int imgDir;
    protected static int[] xx = {0, 1, 0, -1};
    protected static int[] yy = {-1, 0, 1, 0};
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        this.speed = 1;
        this.imgDir = 1;
    }
    public boolean canMove(int x, int y) {
        javafx.geometry.Rectangle2D rect = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Wall wall : BombermanGame.stillObjects) {
            if (rect.intersects(wall.getX(), wall.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                return false;
            }
        }
        for (Brick a : BombermanGame.bricks) {
            if (rect.intersects(a.getX(), a.getY(), Sprite.SCALED_SIZE,Sprite.SCALED_SIZE)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void update(long l) {

    }
}
