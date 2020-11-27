import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class test extends Application {

    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage stage) {
        String path = System.getProperty("user.dir") + "/res/sounds/Bomb_Set.wav";
       // String X = "/home/ngot23/Downloads/TrenTinhBanDuoiTinhYeu-MIN-6802163.mp3";
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(path)));
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Media media = new Media(new File(X).toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        stage.show();
    }
}
