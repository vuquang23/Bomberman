package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.ArrayList;

import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

public class Bomber extends Entity {
    private int speed = 4;
    private static int bombLimit = 2;
    public static ArrayList<ArrayList<Image>> constImage = new ArrayList<>();

    public static void load() {
        // 0: up
        ArrayList<Image> up = new ArrayList<>();
        up.add(Sprite.player_up.getFxImage());
        up.add(Sprite.player_up_1.getFxImage());
        up.add(Sprite.player_up_2.getFxImage());
        constImage.add(up);

        // 1: right
        ArrayList<Image> right = new ArrayList<>();
        right.add(Sprite.player_right.getFxImage());
        right.add(Sprite.player_right_1.getFxImage());
        right.add(Sprite.player_right_2.getFxImage());
        constImage.add(right);

        // 2: down
        ArrayList<Image> down = new ArrayList<>();
        down.add(Sprite.player_down.getFxImage());
        down.add(Sprite.player_down_1.getFxImage());
        down.add(Sprite.player_down_2.getFxImage());
        constImage.add(down);

        // 3: left:
        ArrayList<Image> left = new ArrayList<>();
        left.add(Sprite.player_left.getFxImage());
        left.add(Sprite.player_left_1.getFxImage());
        left.add(Sprite.player_left_2.getFxImage());
        constImage.add(left);
        ArrayList <Image> dead = new ArrayList<>();
        dead.add(Sprite.player_dead1.getFxImage());
        dead.add(Sprite.player_dead2.getFxImage());
        dead.add(Sprite.player_dead3.getFxImage());
        constImage.add(dead);
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    int dirX(int dir) {
        if (dir == 1) {
            return 1;
        }
        if (dir == 3) {
            return -1;
        }
        return 0;
    }

    int dirY(int dir) {
        if (dir == 0) {
            return -1;
        }
        if (dir == 2) {
            return 1;
        }
        return 0;
    }

    int moveX(int curDir, Entity entity) {
        if (curDir == 1 || curDir == 3) {
            return 0;
        }
        switch (curDir) {
            case (0):
                if (x < entity.getX()) {
                    return -1;
                }
                if (x > entity.getX()) {
                    return 1;
                }
                break;
            case (2):
                if (x < entity.getX()) {
                    return -1;
                }
                if (x > entity.getX()) {
                    return 1;
                }
                break;
        }
        return 0;
    }

    int moveY(int curDir, Entity entity) {
        if (curDir == 0 || curDir == 2) {
            return 0;
        }
        switch (curDir) {
            case (1):
                if (y > entity.getY()) {
                    return 1;
                }
                if (y < entity.getY()) {
                    return -1;
                }
                break;
            case (3):
                if (y > entity.getY()) {
                    return 1;
                }
                if (y < entity.getY()) {
                    return -1;
                }
                break;
        }
        return 0;
    }

    public int canMove(int curDir) {
        int aX = 0;
        int aY = 0;
        int newX = x + dirX(curDir) * speed;
        int newY = y + dirY(curDir) * speed;
        boolean meetBlock = false;
        Rectangle2D rect = new Rectangle2D(newX, newY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        Rectangle2D initRect = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
//      bomb
        for (Bomb bomb : BombermanGame.bombs) {
            if (rect.intersects(bomb.getX(), bomb.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE) == false) {
                continue;
            }
            if (initRect.intersects(bomb.getX(), bomb.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE) == true) {
                continue;
            }

            if (meetBlock == false) {
                meetBlock = true;
            }
            aX += moveX(curDir, bomb);
            aY += moveY(curDir, bomb);
        }
//      wall
        for (Wall wall : BombermanGame.stillObjects) {
            if (rect.intersects(wall.getX(), wall.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE) == false) {
                continue;
            }
            if (meetBlock == false) {
                meetBlock = true;
            }
            aX += moveX(curDir, wall);
            aY += moveY(curDir, wall);
        }
//      brick
        for (Brick brick : BombermanGame.bricks) {
            if (rect.intersects(brick.getX(), brick.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE) == false) {
                continue;
            }
            if (meetBlock == false) {
                meetBlock = true;
            }
            aX += moveX(curDir, brick);
            aY += moveY(curDir, brick);
        }

        if (meetBlock == false) { /// take bonus later
            x = newX;
            y = newY;
            return curDir;
        }
        if (aX == 0 && aY == 0) {
            return curDir;
        }
        x = x + aX * speed;
        y = y + aY * speed;
        if (aX == 1) {
            return 1;
        }
        if (aX == -1) {
            return 3;
        }
        if (aY == 1) {
            return 2;
        }
//        if (aY == -1) {
        return 0;
//        }
    }

    void dropBomb(long l) {
        System.out.println(BombermanGame.bombs.size());
        if (BombermanGame.bombs.size() == bombLimit) return;
        int xb = (x / 32);
        int yb = (y / 32);
        Bomb newBomb = new Bomb(xb, yb, Sprite.bomb.getFxImage(), l);
        BombermanGame.bombs.add(newBomb);
    }

    @Override
    public void update(long l) {
        if (this.isDeath()) {
            if (l >= timeChange) {
                timeChange += 400000000;
                ++curState;
                if (curState == 3) {
                    return;
                }
                this.img = constImage.get(4).get(curState);
            }
            return;
        }
        if (BombermanGame.dropBomb == true && BombermanGame.predropBomb == false) {
            //System.out.println("YES");
            dropBomb(l);
        }
        // SPACE lasts ... ns
        BombermanGame.predropBomb = BombermanGame.dropBomb;

        if (BombermanGame.bomberDirection == -1) {
            curState = 0;
            setImg(constImage.get(dir).get(curState));
            return;
        }
        int nxtDir = canMove(BombermanGame.bomberDirection);
        if (nxtDir == dir) {
            curState = (curState + 1) % 9;
            setImg(constImage.get(dir).get(curState / 3));
        } else {
            dir = nxtDir;
            curState = 0;
            setImg(constImage.get(dir).get(curState));
        }

    }
}
