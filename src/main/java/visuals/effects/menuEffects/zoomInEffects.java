package visuals.effects.menuEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;
import org.jetbrains.annotations.NotNull;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;
import visuals.menu.Menu;

import java.io.IOException;

public class zoomInEffects implements IZoomInEffects {

    private double zOffset;
    private double fovOffset;
    private double xOffset;
    private double yOffset;
    private ModeType type;

    private ImageView japan;
    private ImageView jungle;
    private ImageView redtree;
    private ImageView miniEasy;
    private ImageView miniMedium;
    private ImageView miniHard;
    private ImageView easyFrame;
    private ImageView mediumFrame;
    private ImageView hardFrame;
    private ImageView pergament;
    private AnchorPane menuAnkkuri;
    private AnchorPane startBlack;
    private GridPane cubeGrid;
    private ImageView gameBackGround;
    private Pane gamePane;

    private ImageView telkku;
    private Label labelLoggedIn;


    @Override
    public void setEssenceImages(ImageView japan, ImageView jungle, ImageView redtree) {

        this.japan = japan;
        this.jungle = jungle;
        this.redtree = redtree;

    }

    @Override
    public void setMiniImagesAndFrames(
            ImageView miniEasy, ImageView miniMedium, ImageView miniHard,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame) {

        this.miniEasy = miniEasy;
        this.miniMedium = miniMedium;
        this.miniHard = miniHard;
        this.easyFrame = easyFrame;
        this.mediumFrame = mediumFrame;
        this.hardFrame = hardFrame;
    }

    @Override
    public void setGeneralObjects(
            ImageView pergament, AnchorPane menuAnkkuri, AnchorPane startBlack,
            Pane gamePane,Label textLoggedIn, ImageView telkku) {

        this.pergament = pergament;
        this.menuAnkkuri = menuAnkkuri;
        this.startBlack = startBlack;
        this.gamePane = gamePane;
        this.labelLoggedIn = textLoggedIn;
        this.telkku = telkku;
    }


    @Override
    public void gameZoomIn(
            double zOffset, double fovOffset,
            double xOffset, double yOffset, @NotNull ModeType type, Menu menu) {


        this.zOffset = zOffset;
        this.fovOffset = fovOffset;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.type = type;
        double easyFinish = 0;
        double mediumFinish = 0;
        double hardFinish = 0;
        double easyFrameFinish = 0;
        double mediumFrameFinish = 0;
        double hardFrameFinish = 0;

        switch (type) {

            case EASY -> {
                miniEasy.setOpacity(1);
                easyFinish = 1;
                easyFrameFinish = 0;
            }
            case MEDIUM -> {
                miniMedium.setOpacity(1);
                mediumFinish = 1;
                mediumFrameFinish = 0;

            }
            case HARD -> {
                miniHard.setOpacity(1);
                hardFinish = 1;
                hardFrameFinish = 0;
            }
        }

        cameraZoomIn(menu);
        opacitiesIn(easyFinish, mediumFinish, hardFinish, easyFrameFinish, mediumFrameFinish, hardFrameFinish);
    }

    @Override
    public void cameraZoomIn(Menu menu) {

        Timeline timelineZoomIn = new Timeline(

                new KeyFrame(Duration.ZERO,
                        new KeyValue(Navigaattori.camera.translateZProperty(), 0),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), 25),
                        new KeyValue(Navigaattori.camera.translateXProperty(), 0),
                        new KeyValue(Navigaattori.camera.translateYProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(Navigaattori.camera.translateZProperty(), 0 + zOffset / 2),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), 25 + fovOffset / 2),
                        new KeyValue(Navigaattori.camera.translateXProperty(), 0 + xOffset / 2),
                        new KeyValue(Navigaattori.camera.translateYProperty(), 0 + yOffset / 2)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(Navigaattori.camera.translateZProperty(), 0 + zOffset),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), 25 + fovOffset),
                        new KeyValue(Navigaattori.camera.translateXProperty(), 0 + xOffset),
                        new KeyValue(Navigaattori.camera.translateYProperty(), 0 + yOffset)
                ),
                new KeyFrame(Duration.seconds(3))

        );
        timelineZoomIn.play();

        timelineZoomIn.setOnFinished(actionEvent -> {

            telkku.setOpacity(0);

            timelineZoomIn.stop();
            AudioMemory.getInstance().stopSong(ModeType.MENU);
            AudioMemory.getInstance().playSong(type);
            cameraZoomInEndings(menu);
        });

    }

    @Override
    public void cameraZoomInEndings(Menu menu) {

        //quickSwitchCamera(menu);
        menuAnkkuri.setMouseTransparent(true);
        try {
            Navigaattori.getInstance().changeScene(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void quickSwitchCamera(Menu menu) {

    }

    @Override
    public void quickSwitchCameraEndings(@NotNull Menu menu) {

    }

    @Override
    public void easyEntrance(Menu menu) {

    }

    @Override
    public void mediumEntrance(Menu menu) {

    }

    @Override
    public void hardEntrance(Menu menu) {

    }

    @Override
    public void zoomInFinalEndings(Menu menu) {

    }



    @Override
    public void opacitiesIn(
            double easyFinish, double mediumFinish, double hardFinish,
            double easeFrameFinish, double mediumFrameFinish, double hardFrameFinish) {



    }


}
