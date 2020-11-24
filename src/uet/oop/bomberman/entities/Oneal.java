package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        constImage.add(new ArrayList<>());
        constImage.add(new ArrayList<>());
        constImage.get(1).add(Sprite.oneal_right1.getFxImage());
        constImage.get(1).add(Sprite.oneal_right2.getFxImage());
        constImage.get(1).add(Sprite.oneal_right3.getFxImage());
        constImage.add(new ArrayList<>());
        constImage.add(new ArrayList<>());
        constImage.get(3).add(Sprite.oneal_left1.getFxImage());
        constImage.get(3).add(Sprite.oneal_left2.getFxImage());
        constImage.get(3).add(Sprite.oneal_left3.getFxImage());
    }
    public int[][] minDis() {
        int n = Sprite.SCALED_SIZE * BombermanGame.WIDTH;
        int m = Sprite.SCALED_SIZE * BombermanGame.HEIGHT;
        int[][] d = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                d[i][j] = 100000000;
            }
        }
        d[BombermanGame.player.getX()][BombermanGame.player.getY()] = 0;
        int[] qX = new int[n * m];
        int[] qY = new int[n * m];
        int top = 0;
        int bot = 0;
        qX[bot] = BombermanGame.player.getX();
        qY[bot++] = BombermanGame.player.getY();
        while (top < bot) {
            int u = qX[top];
            int v = qY[top++];
            for (int i = 0; i < 4; ++i) {
                int x = u + xx[i];
                int y = v + yy[i];
                if (x < 0 || y < 0 || x >= n || y >= m || canMove(x, y) == false || d[x][y] != 100000000) {
                    continue;
                }
                d[x][y] = d[u][v] + 1;
                qX[bot] = x;
                qY[bot++] = y;
            }
        }
        return d;
    }
    static Random ran = new Random();
    @Override
    public void update() {
        int[][] d = minDis();
        int n = Sprite.SCALED_SIZE * BombermanGame.WIDTH;
        int m = Sprite.SCALED_SIZE * BombermanGame.HEIGHT;
        while (true) {
            int nextDir = 0;
            int curDis = 100000000;
            int newX = this.x;
            int newY = this.y;
            this.speed = ran.nextInt() % 6 + 1;
            if (this.speed <= 0) this.speed += 6;
            for (int i = 0; i < 4; ++i) {
                int tempX = this.x + this.speed * xx[i];
                int tempY = this.y + this.speed * yy[i];
                if (tempX < 0 || tempY < 0 || tempX >= n || tempY >= m) {
                    continue;
                }
                if (curDis > d[tempX][tempY]) {
                    curDis = d[tempX][tempY];
                    nextDir = i;
                    newX = tempX;
                    newY = tempY;
                }
            }
            if (curDis == 100000000) {
                if (this.x % 32 == 0 && this.y % 32 == 0) {
                    this.dir = ran.nextInt() % 4;
                    if (this.dir < 0) {
                        this.dir += 4;
                    }
                }
                newX = this.x + xx[this.dir] * this.speed;
                newY = this.y + yy[this.dir] * this.speed;
            }
            if (canMove(newX, newY) == false) {
                continue;
            }
            this.setX(newX);
            this.setY(newY);
            curState += 1;
            curState %= 3;
            if (this.dir == 1) {
                this.imgDir = 1;
            }
            if (this.dir == 3) {
                this.imgDir = 3;
            }
            this.img = constImage.get(this.imgDir).get(this.curState);
            break;
        }
    }
}
