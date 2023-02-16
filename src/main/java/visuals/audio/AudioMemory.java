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
    private final MediaPlayer mainPlayer;
    private final MediaPlayer easyPlayer;
    private final MediaPlayer mediumPlayer;
    private final MediaPlayer hardPlayer;

    private AudioMemory() {

        String mainSong = "src/main/java/visuals/audio/audioFiles/mainSong.mp3";
        Media mainMedia = new Media(new File(mainSong).toURI().toString());
        String easySong = "src/main/java/visuals/audio/audioFiles/easySong.mp3";
        Media easyMedia = new Media(new File(easySong).toURI().toString());
        String mediumSong = "src/main/java/visuals/audio/audioFiles/mediumSong.mp3";
        Media mediumMedia = new Media(new File(mediumSong).toURI().toString());
        String hardSong = "src/main/java/visuals/audio/audioFiles/hardSong.mp3";
        Media hardMedia = new Media(new File(hardSong).toURI().toString());

        mainPlayer = new MediaPlayer(mainMedia);
        mainPlayer.setCycleCount(10);
        easyPlayer = new MediaPlayer(easyMedia);
        easyPlayer.setCycleCount(10);
        mediumPlayer = new MediaPlayer(mediumMedia);
        mediumPlayer.setCycleCount(10);
        hardPlayer = new MediaPlayer(hardMedia);
        hardPlayer.setCycleCount(10);
    }

    public static AudioMemory getInstance() {
        if (instance == null) {
            instance = new AudioMemory();
        }
        return instance;
    }

    public void playSong(ModeType type) {

        switch (type) {
            case MAIN -> playTheSong(mainPlayer);
            case EASY -> playTheSong(easyPlayer);
            case MEDIUM -> playTheSong(mediumPlayer);
            case HARD -> playTheSong(hardPlayer);
        }
    }

    public void stopSong(ModeType type) {

        switch (type) {
            case MAIN -> stopTheSong(mainPlayer);
            case EASY -> stopTheSong(easyPlayer);
            case MEDIUM -> stopTheSong(mediumPlayer);
            case HARD -> stopTheSong(hardPlayer);
        }

    }

    public void pauseSong(ModeType type) {

        switch (type) {
            case MAIN -> mainPlayer.pause();
            case EASY -> easyPlayer.pause();
            case MEDIUM -> mediumPlayer.pause();
            case HARD -> hardPlayer.pause();
        }
    }

    private void playTheSong(MediaPlayer mediaPlayer) {

        mediaPlayer.setVolume(0);
        mediaPlayer.play();

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(mediaPlayer.volumeProperty(), 1))
        );
        fadeIn.play();

    }

    private void stopTheSong(MediaPlayer mediaPlayer) {

        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(mediaPlayer.volumeProperty(), 0))
        );
        fadeOut.setOnFinished(event -> mediaPlayer.stop());
        fadeOut.play();


    }

}
