package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>(); // contains brick, item, enemy
    public static List<Wall> stillObjects = new ArrayList<>();
    static String path = System.getProperty("user.dir") + "/res/levels/";
    static Entity background = new Grass(0, 0, Sprite.grass.getFxImage());
    public static int bomberDirection = -1;
    public static Bomber player;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
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
        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            bomberDirection = -1;
        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        Thread thr = new Thread();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                            object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                            entities.add(object);
                            break;
                        case ('2'):
                            object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            entities.add(object);
                            break;
                        case ('*'):
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            entities.add(object);
                            break;
                        case ('x'):
                            object = new Item(j, i, Sprite.portal.getFxImage(), 4);
                            entities.add(object);
                            entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case('s'):
                            object = new Item(j, i, Sprite.powerup_speed.getFxImage(), 3);
                            entities.add(object);
                            entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case ('f'):
                            object = new Item(j, i, Sprite.powerup_flames.getFxImage(), 2);
                            entities.add(object);
                            entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case ('b'):
                            object = new Item(j, i, Sprite.powerup_bombs.getFxImage(), 1);
                            entities.add(object);
                            entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                    }
                }
            }
        }
        stillObjects.forEach(g -> g.render(gc));
    }

    public void update() {
        for (Entity en : entities) {
            background.setX(en.getX());
            background.setY(en.getY());
            background.render(gc);
        }
        background.setX(player.getX());
        background.setY(player.getY());
        background.render(gc);
        for (Entity en : entities) {
            en.update();
            en.render(gc);
        }
        player.update();
        player.render(gc);
    }
}
