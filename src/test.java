import uet.oop.bomberman.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.input.KeyEvent;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test extends Application {

    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage stage) {
//        String path = System.getProperty("user.dir") + "/res/sounds/Bomb_Set.wav";
//       // String X = "/home/ngot23/Downloads/TrenTinhBanDuoiTinhYeu-MIN-6802163.mp3";
//        try {
//            Clip clip = AudioSystem.getClip();
//            clip.open(AudioSystem.getAudioInputStream(new File(path)));
//            clip.start();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        try {
////            Media media = new Media(new File(X).toURI().toString());
////            MediaPlayer mediaPlayer = new MediaPlayer(media);
////            mediaPlayer.play();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
        Canvas canvas;
        GraphicsContext gc;
        canvas = new Canvas(Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        String path = System.getProperty("user.dir") + "/res/";

        Image winImage;
        try {
            winImage = new Image(new FileInputStream(path + "image/winGame.jpg"));
            gc.drawImage(winImage, 0, 0, Sprite.SCALED_SIZE * 31, Sprite.SCALED_SIZE * 13);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
        stage.show();
    }
}
