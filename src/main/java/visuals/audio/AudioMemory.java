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
    private final MediaPlayer leaderBoardPlayer;

    private AudioMemory() {

        String easySong = "src/main/java/visuals/audio/audioFiles/easymusic.mp3";
        Media easyMedia = new Media(new File(easySong).toURI().toString());
        String mediumSong = "src/main/java/visuals/audio/audioFiles/mediummusic.mp3";
        Media mediumMedia = new Media(new File(mediumSong).toURI().toString());
        String hardSong = "src/main/java/visuals/audio/audioFiles/hardmusic.mp3";
        Media hardMedia = new Media(new File(hardSong).toURI().toString());
        String menuRetro = "src/main/java/visuals/audio/audioFiles/menuRetro.mp3";
        Media menuMedia = new Media(new File(menuRetro).toURI().toString());
        String leaderBoardSong = "src/main/java/visuals/audio/audioFiles/leaderboardmusic.mp3";
        Media leaderBoardMedia = new Media(new File(leaderBoardSong).toURI().toString());

        easyPlayer = new MediaPlayer(easyMedia);
        easyPlayer.setCycleCount(10);
        mediumPlayer = new MediaPlayer(mediumMedia);
        mediumPlayer.setCycleCount(10);
        hardPlayer = new MediaPlayer(hardMedia);
        hardPlayer.setCycleCount(10);
        menuRetoSong = new MediaPlayer(menuMedia);
        menuRetoSong.setCycleCount(10);
        leaderBoardPlayer = new MediaPlayer(leaderBoardMedia);
        leaderBoardPlayer.setCycleCount(10);
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
            case LEADERBOARD -> playTheSong(leaderBoardPlayer);
        }
    }

    public void stopSong(ModeType type) {

        switch (type) {
            case MENU -> stopTheSong(menuRetoSong);
            case EASY -> stopTheSong(easyPlayer);
            case MEDIUM -> stopTheSong(mediumPlayer);
            case HARD -> stopTheSong(hardPlayer);
            case LEADERBOARD -> stopTheSong(leaderBoardPlayer);
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

    public void playTheIntro() {

        menuRetoSong.setVolume(0);
        menuRetoSong.play();

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.seconds(5), new KeyValue(menuRetoSong.volumeProperty(), 1))
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
