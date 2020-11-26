package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity {
    protected int speed;
    protected int imgDir;
    protected static int[] xx = {0, 1, 0, -1};
    protected static int[] yy = {-1, 0, 1, 0};
    protected static Random ran = new Random();
    List<Integer> randomDir = new ArrayList<Integer>();
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        this.speed = 1;
        this.imgDir = 1;
        for (int i = 0; i < 4; ++i) {
            randomDir.add(i);
        }
    }
    public boolean canMove(int newX, int newY) {
        Rectangle2D rect = new Rectangle2D(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        Rectangle2D initRect = new Rectangle2D(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
       for (Bomb bomb : BombermanGame.bombs) {
            if (initRect.intersects(bomb.getX(), bomb.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                continue;
            }
            if (rect.intersects(bomb.getX(), bomb.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE)) {
                return false;
            }
        }

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
