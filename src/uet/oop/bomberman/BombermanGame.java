package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import javafx.scene.input.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static List<Wall> stillObjects = new ArrayList<>();
    public static List<Flame> flames = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    static String path = System.getProperty("user.dir") + "/res/levels/";
    static Entity background = new Grass(0, 0, Sprite.grass.getFxImage());
    public static int bomberDirection = -1;
    public static Bomber player;
    public static boolean dropBomb = false;

    public static void main(String[] args) {
        loadAll();
        Application.launch(BombermanGame.class);
    }

    static void loadAll() {
        Balloon.load();
        Bomb.load();
        Bomber.load();
        Flame.load();
        Oneal.load();
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Control
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.UP) {
                bomberDirection = 0;
            }
            if (key.getCode() == KeyCode.RIGHT) {
                bomberDirection = 1;
            }
            if (key.getCode() == KeyCode.DOWN) {
                bomberDirection = 2;
            }
            if (key.getCode() == KeyCode.LEFT) {
                bomberDirection = 3;
            }
            if (key.getCode() == KeyCode.SPACE) {
                dropBomb = true; // drop bom
            }
        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            if (key.getCode() != KeyCode.SPACE) {
                bomberDirection = -1;
            }
            if (key.getCode() == KeyCode.SPACE) {
                dropBomb = false;
            }
        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            long last = 0;
            @Override
            public void handle(long l) {
                update(l);
                if(last == 0) {
                    last = l;
                    render();
                    return;
                }
                if (l - last > 1000000000 / 150) {
                    render();
                    last = l;
                }
            }
        };
        timer.start();

        try {
            createMap("Level1.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMap(String fileName) throws IOException {
        Scanner objReader = new Scanner(new File(path + fileName));
        int stage = objReader.nextInt();
        int m = objReader.nextInt();
        int n = objReader.nextInt();
        objReader.nextLine();
        for (int i = 0; i < m; i++) {
            String S = objReader.nextLine();
            for (int j = 0; j < n; j++) {
                Entity object;
                char c = S.charAt(j);
                if(Character.compare(c, '#') == 0) {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    Grass newGrass = new Grass(j, i, Sprite.grass.getFxImage());
                    newGrass.render(gc);

                    switch (c) {
                        case ('p'):
                            player = new Bomber(j, i, Sprite.player_right.getFxImage());
                            break;
                        case ('1'):
                            enemies.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                            break;
                        case ('2'):
                            enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                            break;
                        case ('*'):
                            bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case ('x'):
                            items.add(new Item(j, i, Sprite.portal.getFxImage(), 4));
                            bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case('s'):
                            items.add(new Item(j, i, Sprite.powerup_speed.getFxImage(), 3));
                            bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case ('f'):
                            items.add(new Item(j, i, Sprite.powerup_flames.getFxImage(), 2));
                            bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case ('b'):
                            items.add(new Item(j, i, Sprite.powerup_bombs.getFxImage(), 1));
                            bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                    }
                }
            }
        }
        stillObjects.forEach(g -> g.render(gc));
    }

    public void update(long now) { /// update bom -> flame -> brick -> enemy -> player
        for (int i = 0; i < bombs.size(); ++i) {
            Bomb b = bombs.get(i);
            background.setX(b.getX());
            background.setY(b.getY());
            background.render(gc);
//            if (b.isDeath()) {
//                b.setCurState(-1);
//            }
            if (1 < 2) {
                b.update(now);
                if (b.getCurState() == -1) {
                    bombs.remove(i);
                    --i;

                }

            }
        }


        for (int i = 0; i < flames.size(); ++i) {
            Flame f = flames.get(i);
            background.setX(f.getX());
            background.setY(f.getY());
            background.render(gc);
            if (1 < 2) {
                f.update();
                if (f.getCurState() == -1) {
                    flames.remove(i);
                    --i;
                }
            }
        }
        for (Brick e : bricks) {
            background.setX(e.getX());
            background.setY(e.getY());
            background.render(gc);
            e.update();
        }

        for (Enemy e : enemies) {
            background.setX(e.getX());
            background.setY(e.getY());
            background.render(gc);
            e.update();
        }

        background.setX(player.getX());
        background.setY(player.getY());
        background.render(gc);
        player.update(now);
    }

    public void render() {
        for(Bomb b : bombs) {
            b.render(gc);
        }
        for(Flame f : flames) {
            f.render(gc);
        }
        for(Brick e : bricks) {
            e.render(gc);
        }
        for(Enemy e : enemies) {
            e.render(gc);
        }
        player.render(gc);
    }
}
