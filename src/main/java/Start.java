

import controller.ScoreController;
import javafx.scene.text.Font;
import model.ModeType;
import visuals.Navigaattori;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * Käynnistää simulaattorin 🚇
 *
 * @author Eetu Soronen
 * @version 1
 */
public class Start {

    /**
     * public static void main string args
     * kutsuu Visuals.MainApp.main(args) ja käynnistää simulaattorin
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {

        ImageCache.getInstance().addToMenuCache();
        ImageCache.getInstance().addToEasyCache();
        ImageCache.getInstance().addToMediumCache();
        ImageCache.getInstance().addToHardCache();
        ImageCache.getInstance().addToGameBackGroundCache();

        // load atari classic font to memory.
        Font.loadFont(Objects.requireNonNull(Start.class.getClassLoader().getResource("fonts/AtariClassic-gry3.ttf")).toExternalForm(), 14);


        Thread thread = new Thread(() -> {
            ScoreController sc = new ScoreController();
            sc.fetchScores(ModeType.EASY);
            sc.fetchScores(ModeType.MEDIUM);
            sc.fetchScores(ModeType.HARD);
        });
        thread.start();


        Navigaattori.main(args);
    }
}

