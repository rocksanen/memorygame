package visuals.audio;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.ModeType;

import java.io.File;

public class AudioMemory {

    private static AudioMemory instance;
    private final MediaPlayer easyPlayer;
    private final MediaPlayer mediumPlayer;
    private final MediaPlayer hardPlayer;
    private final MediaPlayer menuRetoSong;

    public static Boolean noIntro = false;

    private AudioMemory() {

        String easySong = "src/main/java/visuals/audio/audioFiles/easymusic.mp3";
        Media easyMedia = new Media(new File(easySong).toURI().toString());
        String mediumSong = "src/main/java/visuals/audio/audioFiles/mediummusic.mp3";
        Media mediumMedia = new Media(new File(mediumSong).toURI().toString());
        String hardSong = "src/main/java/visuals/audio/audioFiles/hardmusic.mp3";
        Media hardMedia = new Media(new File(hardSong).toURI().toString());
        String menuRetro = "src/main/java/visuals/audio/audioFiles/menuRetro.mp3";
        Media menuMedia = new Media(new File(menuRetro).toURI().toString());

        easyPlayer = new MediaPlayer(easyMedia);
        easyPlayer.setCycleCount(10);
        mediumPlayer = new MediaPlayer(mediumMedia);
        mediumPlayer.setCycleCount(10);
        hardPlayer = new MediaPlayer(hardMedia);
        hardPlayer.setCycleCount(10);
        menuRetoSong = new MediaPlayer(menuMedia);
        menuRetoSong.setCycleCount(10);

    }

    public static AudioMemory getInstance() {
        if (instance == null) {
            instance = new AudioMemory();
        }
        return instance;
    }

    public void playSong(ModeType type) {

        switch (type) {
            case MENU -> playTheSong(menuRetoSong);
            case EASY -> playTheSong(easyPlayer);
            case MEDIUM -> playTheSong(mediumPlayer);
            case HARD -> playTheSong(hardPlayer);
        }
    }

    public void stopSong(ModeType type) {

        switch (type) {
            case MENU -> stopTheSong(menuRetoSong);
            case EASY -> stopTheSong(easyPlayer);
            case MEDIUM -> stopTheSong(mediumPlayer);
            case HARD -> stopTheSong(hardPlayer);
        }
    }

    public void pauseSong(ModeType type) {

        switch (type) {
            case MENU -> menuRetoSong.play();
            case EASY -> easyPlayer.pause();
            case MEDIUM -> mediumPlayer.pause();
            case HARD -> hardPlayer.pause();
        }
    }

    private void playTheSong(MediaPlayer mediaPlayer) {

        mediaPlayer.setVolume(0);
        mediaPlayer.play();

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(mediaPlayer.volumeProperty(), 0))
        );
        fadeIn.play();

    }

    public MediaPlayer introSong() {

        return menuRetoSong;
    }

    public void playTheIntro() {

        menuRetoSong.setVolume(0);
        menuRetoSong.play();

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.seconds(5), new KeyValue(menuRetoSong.volumeProperty(), 0))
        );
        fadeIn.play();

    }

    public void stopTheIntro() {

        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(menuRetoSong.volumeProperty(), 0))
        );
        fadeOut.setOnFinished(event -> menuRetoSong.stop());
        fadeOut.play();


    }

    private void stopTheSong(MediaPlayer mediaPlayer) {

        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(mediaPlayer.volumeProperty(), 0))
        );
        fadeOut.setOnFinished(event -> mediaPlayer.stop());
        fadeOut.play();

    }
}
