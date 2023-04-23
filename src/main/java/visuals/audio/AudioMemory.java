package visuals.audio;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.ModeType;

import java.io.File;
import java.util.Objects;

public class AudioMemory {

    private static AudioMemory instance;
    private final MediaPlayer easyPlayer;
    private final MediaPlayer mediumPlayer;
    private final MediaPlayer hardPlayer;
    private final MediaPlayer menuRetoSong;
    private final MediaPlayer leaderBoardPlayer;
    private MediaPlayer currentSong;

    public boolean isMuted = false;

    private ModeType currentMode;


    private AudioMemory() {

        String easySong = "/audioFiles/easymusic.mp3";
        Media easyMedia = new Media(Objects.requireNonNull(getClass().getResource(easySong)).toString());
        String mediumSong = "/audioFiles/mediummusic.mp3";
        Media mediumMedia = new Media(Objects.requireNonNull(getClass().getResource(mediumSong)).toString());
        String hardSong = "/audioFiles/hardmusic.mp3";
        Media hardMedia = new Media(Objects.requireNonNull(getClass().getResource(hardSong)).toString());
        String menuRetro = "/audioFiles/menuRetro.mp3";
        Media menuMedia = new Media(Objects.requireNonNull(getClass().getResource(menuRetro)).toString());
        String leaderBoardSong = "/audioFiles/leaderboardmusic.mp3";
        Media leaderBoardMedia = new Media(Objects.requireNonNull(getClass().getResource(leaderBoardSong)).toString());

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
        isMuted = false;
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

        if (type == null) {

            System.out.println("ERROR: ModeType is null");
            return;
        }

        switch (type) {

            case MENU -> stopTheSong(menuRetoSong);
            case EASY -> stopTheSong(easyPlayer);
            case MEDIUM -> stopTheSong(mediumPlayer);
            case HARD -> stopTheSong(hardPlayer);
            case LEADERBOARD -> stopTheSong(leaderBoardPlayer);
        }

        if (currentMode == type) {
            currentMode = null;
        }
    }


    public void toggleMute() {

        isMuted = !isMuted;
        currentSong.setVolume(isMuted ? 0 : 1);
    }


    public boolean isMuted() {
        return isMuted;
    }


    private void playTheSong(MediaPlayer mediaPlayer) {

        currentSong = mediaPlayer;
        currentSong.setVolume(0);
        currentSong.play();

        if (isMuted) {

            currentSong.play();

        } else {

            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.seconds(1), new KeyValue(currentSong.volumeProperty(), 1))
            );

            fadeIn.play();
        }
    }


    public void playTheIntro() {

        currentSong = menuRetoSong;
        currentSong.setVolume(0);
        currentSong.play();

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.seconds(5), new KeyValue(currentSong.volumeProperty(), 1))
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
