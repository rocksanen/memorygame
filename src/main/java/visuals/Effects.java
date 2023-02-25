package visuals;

import com.sun.jdi.VoidType;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;
import visuals.audio.AudioMemory;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class Effects {
    private static Effects instance;
    private final Timeline timelineBlur = new Timeline();
    private final GaussianBlur gaussianBlur = new GaussianBlur();
    private Timeline glowLine;
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
    private ArrayList<ImageView> mtLista;
    private final double japanStart = 0.4; //0.26
    private final double jungleStart = 0.26; // 0.2
    private final double redtreeStart = 0.75; //0.35
    private ModeType type;
    private double zOffset;
    private double fovOffset;
    private double xOffset;
    private double yOffset;
    private ImageView burningsun;
    private final BackGroundMover backGroundMover = new BackGroundMover();

    private Timeline burningSunLine;
    public Effects() {
        initializeTimelines();
    }

    public void setEssenceImages(ImageView japan, ImageView jungle, ImageView redtree) {

        this.japan = japan;
        this.jungle = jungle;
        this.redtree = redtree;
    }

    public void setGeneralObjects(
            ImageView pergament, AnchorPane menuAnkkuri, AnchorPane startBlack,
            Pane gamePane, ArrayList<ImageView> mtLista, ImageView burningsun) {

        this.pergament = pergament;
        this.menuAnkkuri = menuAnkkuri;
        this.startBlack = startBlack;
        this.gamePane = gamePane;
        this.mtLista = mtLista;
        this.burningsun = burningsun;

    }

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

    private void initializeTimelines() {

        timelineBlur.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(gaussianBlur.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(6), new KeyValue(gaussianBlur.radiusProperty(), 10.58))
        );
    }
    public static Effects getInstance(){

        if(instance == null) {
            instance = new Effects();
        }
        return instance;
    }

    public void backGroundBlurIn(ImageView imageView) {

        gaussianBlur.setRadius(0);
        imageView.setEffect(gaussianBlur);
        timelineBlur.stop();
        timelineBlur.play();
    }


    public void gameZoomOut(

            GridPane cubeGrid,
            ImageView gameBackGround, double zOffset,
            double fovOffset, double xOffset, double yOffset,ModeType type
    ) {

        this.cubeGrid = cubeGrid;
        this.gameBackGround = gameBackGround;
        this.zOffset = zOffset;
        this.fovOffset = fovOffset;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.type = type;

        switch (type) {

            case EASY -> miniEasy.setOpacity(1);
            case MEDIUM -> miniMedium.setOpacity(1);
            case HARD -> miniHard.setOpacity(1);
        }

        Platform.runLater(backGroundMover::stop);
        Platform.runLater(backGroundMover::returnToPositionZero);
        zoomOutBlur();
    }

    private void zoomOutBlur() {

        Timeline blurOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(cubeGrid.opacityProperty(),cubeGrid.getOpacity()),
                        new KeyValue(gaussianBlur.radiusProperty(), gaussianBlur.getRadius())),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(gaussianBlur.radiusProperty(),0),
                        new KeyValue(cubeGrid.opacityProperty(),0))
        );

        blurOut.playFromStart();

        blurOut.setOnFinished(actionEvent -> {

            blurOut.stop();
            zoomOutBlurEndings();
        });
    }

    private void zoomOutCamera() {

        Timeline timelineZoomOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(Gui.camera.translateZProperty(), Gui.camera.getTranslateZ()),
                        new KeyValue(Gui.camera.fieldOfViewProperty(), Gui.camera.getFieldOfView()),
                        new KeyValue(Gui.camera.translateXProperty(), Gui.camera.getTranslateX()),
                        new KeyValue(Gui.camera.translateYProperty(), Gui.camera.getTranslateY())
                ),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(Gui.camera.translateZProperty(), 0),
                        new KeyValue(Gui.camera.fieldOfViewProperty(), 25),
                        new KeyValue(Gui.camera.translateXProperty(), 0),
                        new KeyValue(Gui.camera.translateYProperty(), 0)
                )
        );

        timelineZoomOut.playFromStart();
        zoomOutOpacities();

        timelineZoomOut.setOnFinished(actionEvent1 -> {

            timelineZoomOut.stop();
            zoomOutCameraEndings();
        });
    }
    private void zoomOutOpacities() {

        Timeline opacityOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(pergament.opacityProperty(),0),
                        new KeyValue(miniEasy.opacityProperty(),miniEasy.getOpacity()),
                        new KeyValue(miniMedium.opacityProperty(),miniMedium.getOpacity()),
                        new KeyValue(miniHard.opacityProperty(),miniHard.getOpacity()),
                        new KeyValue(japan.opacityProperty(),0),
                        new KeyValue(jungle.opacityProperty(),0),
                        new KeyValue(redtree.opacityProperty(),0),
                        new KeyValue(easyFrame.opacityProperty(),easyFrame.getOpacity()),
                        new KeyValue(mediumFrame.opacityProperty(),mediumFrame.getOpacity()),
                        new KeyValue(hardFrame.opacityProperty(),hardFrame.getOpacity())),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(pergament.opacityProperty(),1),
                        new KeyValue(miniEasy.opacityProperty(),1),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(miniHard.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(),japanStart),
                        new KeyValue(jungle.opacityProperty(),jungleStart),
                        new KeyValue(redtree.opacityProperty(),redtreeStart),
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(hardFrame.opacityProperty(),1))
        );

        opacityOut.playFromStart();
        opacityOut.setOnFinished(actionEvent -> {
            opacityOut.stop();
        });
    }

    private void zoomOutCameraEndings() {

        Platform.runLater(this::playGlow);
        Platform.runLater(() -> menuAnkkuri.setMouseTransparent(false));
    }
    private void zoomOutBlurEndings() {

        Platform.runLater(() -> Visibilities.getInstance().offGameGrid(cubeGrid));
        Visibilities.getInstance().offGame(gameBackGround);

        Gui.camera.setTranslateZ(zOffset);
        Gui.camera.setFieldOfView(fovOffset);
        Gui.camera.setTranslateX(xOffset);
        Gui.camera.setTranslateY(yOffset);

        menuAnkkuri.setOpacity(1);

        Platform.runLater(() -> Gui.logAndReg.setVisible(true));
        AudioMemory.getInstance().stopSong(type);
        AudioMemory.getInstance().playSong(ModeType.MAIN);

        zoomOutCamera();
        zoomOutOpacities();
    }

    public void gameZoomIn(

            ImageView gameBackGround,
            double zOffset, double fovOffset, double xOffset,
            double yOffset, ModeType type, Gui gui

    ) {

        this.zOffset = zOffset;
        this.fovOffset = fovOffset;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.gameBackGround = gameBackGround;
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
                easyFrameFinish = 1;
            }
            case MEDIUM -> {
                miniMedium.setOpacity(1);
                mediumFinish = 1;
                mediumFrameFinish = 1;
            }
            case HARD -> {
                miniHard.setOpacity(1);
                hardFinish = 1;
                hardFrameFinish = 1;
            }
        }

        cameraZoomIn(gui);
        opacitiesIn(easyFinish,mediumFinish,hardFinish, easyFrameFinish, mediumFrameFinish, hardFrameFinish);
    }


    private void cameraZoomIn(Gui gui) {


        Timeline timelineZoomIn = new Timeline(

                new KeyFrame(Duration.ZERO,
                        new KeyValue(Gui.camera.translateZProperty(), 0),
                        new KeyValue(Gui.camera.fieldOfViewProperty(), 25),
                        new KeyValue(Gui.camera.translateXProperty(), 0),
                        new KeyValue(Gui.camera.translateYProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(Gui.camera.translateZProperty(), 0 + zOffset/2),
                        new KeyValue(Gui.camera.fieldOfViewProperty(), 25 + fovOffset/2),
                        new KeyValue(Gui.camera.translateXProperty(), 0 + xOffset/2),
                        new KeyValue(Gui.camera.translateYProperty(), 0 + yOffset/2)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(Gui.camera.translateZProperty(), 0 + zOffset),
                        new KeyValue(Gui.camera.fieldOfViewProperty(), 25 + fovOffset),
                        new KeyValue(Gui.camera.translateXProperty(), 0 + xOffset),
                        new KeyValue(Gui.camera.translateYProperty(), 0 + yOffset)
                ),
                new KeyFrame(Duration.seconds(3))

        );
        timelineZoomIn.play();

        timelineZoomIn.setOnFinished(actionEvent -> {

            timelineZoomIn.stop();
            cameraZoomInEndings(gui);
        });
    }

    private void cameraZoomInEndings(Gui gui) {

        menuAnkkuri.setMouseTransparent(true);
        quickSwitchCamera(gui);
    }

    private void quickSwitchCamera(Gui gui) {

        Timeline quickSwitch = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(menuAnkkuri.opacityProperty(), 1),
                        new KeyValue(Gui.camera.translateXProperty(), Gui.camera.getTranslateX()),
                        new KeyValue(Gui.camera.translateYProperty(), Gui.camera.getTranslateY()),
                        new KeyValue(Gui.camera.translateZProperty(), Gui.camera.getTranslateZ())
                ),
                new KeyFrame(Duration.seconds(0.001),
                        new KeyValue(menuAnkkuri.opacityProperty(), 0),
                        new KeyValue(Gui.camera.translateXProperty(), 0),
                        new KeyValue(Gui.camera.translateYProperty(), 0),
                        new KeyValue(Gui.camera.translateZProperty(), 0)
                )
        );

        menuAnkkuri.setMouseTransparent(true);
        quickSwitch.play();

        quickSwitch.setOnFinished(actionEvent1 -> {

            quickSwitch.stop();
            quickSwitchCameraEndings(gui);
        });
    }

    private void quickSwitchCameraEndings(Gui gui) {

        Gui.camera.setFieldOfView(1);
        gui.startChoose(type);
        AudioMemory.getInstance().stopSong(ModeType.MAIN);
        AudioMemory.getInstance().playSong(type);
        Platform.runLater(() -> backGroundMover.animate(gameBackGround));
        Platform.runLater(backGroundMover::play);
        Platform.runLater(() -> backGroundBlurIn(gameBackGround));
    }

    private void opacitiesIn(
            double easyFinish, double mediumFinish, double hardFinish,
            double easeFrameFinish, double mediumFrameFinish, double hardFrameFinish ) {

        Timeline opacitiesIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(pergament.opacityProperty(),1),
                        new KeyValue(miniEasy.opacityProperty(),1),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(miniHard.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(),japanStart),
                        new KeyValue(jungle.opacityProperty(),jungleStart),
                        new KeyValue(redtree.opacityProperty(),redtreeStart),
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(hardFrame.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(pergament.opacityProperty(),0),
                        new KeyValue(miniEasy.opacityProperty(),easyFinish),
                        new KeyValue(miniMedium.opacityProperty(),mediumFinish),
                        new KeyValue(miniHard.opacityProperty(),hardFinish),
                        new KeyValue(japan.opacityProperty(),0),
                        new KeyValue(redtree.opacityProperty(),0),
                        new KeyValue(easyFrame.opacityProperty(),easeFrameFinish),
                        new KeyValue(mediumFrame.opacityProperty(),mediumFrameFinish),
                        new KeyValue(hardFrame.opacityProperty(),hardFrameFinish)),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(jungle.opacityProperty(),0))
        );

        opacitiesIn.playFromStart();
        opacitiesIn.setOnFinished(actionEvent -> {
            opacitiesIn.stop();
        });


    }


    public void bringGameUp(
            Label first, Label second,
            Pane logAndReg, ImageView sun,
            ImageView lightning, ImageView blacksun,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame) {


        Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.MENU));


        Timeline timelySun = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(31))
        );



        timelySun.setOnFinished(actionEvent -> {

            Platform.runLater(this::burningSunMove);

        });

        timelySun.play();


        final Glow textGlow = new Glow();
        textGlow.setLevel(0.6);
        second.setEffect(textGlow);

        final GaussianBlur sunblur = new GaussianBlur();
        sun.setEffect(sunblur);

        final Reflection reflection = new Reflection();
        reflection.setBottomOpacity(0.04);
        reflection.setTopOpacity(0.08);
        reflection.setTopOffset(0);
        reflection.setFraction(0);

        first.setEffect(reflection);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(3.5),
                        new KeyValue(first.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(5.8),
                        new KeyValue(first.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(7.05),
                        new KeyValue(lightning.opacityProperty(),0)),                  ///Lightning start!
                new KeyFrame(Duration.seconds(7.15),
                        new KeyValue(reflection.fractionProperty(),0)),
                new KeyFrame(Duration.seconds(7.18),
                        new KeyValue(reflection.fractionProperty(),reflection.getFraction() + 0.7)),
                new KeyFrame(Duration.seconds(7.25),
                        new KeyValue(lightning.opacityProperty(),0.6)),
                new KeyFrame(Duration.seconds(13.5),
                        new KeyValue(lightning.opacityProperty(),0)),                  ///Lightning end!
                new KeyFrame(Duration.seconds(13.7),
                        new KeyValue(first.opacityProperty(),0),
                        new KeyValue(sun.opacityProperty(),0),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth()),
                        new KeyValue(sun.rotateProperty(),sun.getRotate()),
                        new KeyValue(sun.scaleYProperty(),sun.getScaleY()),
                        new KeyValue(sun.layoutYProperty(),sun.getLayoutY())),
                new KeyFrame(Duration.seconds(14.5),
                        new KeyValue(second.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(second.opacityProperty(),1),
                        new KeyValue(sun.opacityProperty(),0.14),
                        new KeyValue(sun.layoutXProperty(),sun.getLayoutX())),
                new KeyFrame(Duration.seconds(24.2),
                        new KeyValue(second.opacityProperty(),0),
                        new KeyValue(sun.opacityProperty(),0.35)),
                new KeyFrame(Duration.seconds(26.2),
                        new KeyValue(startBlack.opacityProperty(),1),
                        new KeyValue(sunblur.radiusProperty(),0),
                        new KeyValue(blacksun.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(31),
                        new KeyValue(blacksun.opacityProperty(),0.6)),
                new KeyFrame(Duration.seconds(33),
                        new KeyValue(gamePane.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(35),
                        new KeyValue(blacksun.layoutYProperty(),blacksun.getLayoutY()),
                        new KeyValue(blacksun.opacityProperty(),0.5),
                        new KeyValue(gamePane.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(36.4),
                        new KeyValue(startBlack.opacityProperty(),0),
                        new KeyValue(sun.opacityProperty(),0),                   // 0
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth() + 80),
                        new KeyValue(sun.layoutXProperty(),sun.getLayoutX() - 8),
                        new KeyValue(sunblur.radiusProperty(), 50),
                        new KeyValue(sun.rotateProperty(),sun.getRotate() - 8),
                        new KeyValue(sun.scaleYProperty(),sun.getScaleY() + 0.5),
                        new KeyValue(sun.layoutYProperty(),sun.getLayoutY() + 130),
                        new KeyValue(blacksun.opacityProperty(),0),
                        new KeyValue(blacksun.layoutYProperty(),blacksun.getLayoutY() - 15)),
                new KeyFrame(Duration.seconds(40),
                        new KeyValue(miniEasy.opacityProperty(),0),                 // Easy start!!!!
                        new KeyValue(easyFrame.opacityProperty(),0),
                        new KeyValue(japan.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.2),
                        new KeyValue(miniEasy.opacityProperty(),1),                 // Easy on!!!
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(),japanStart)),
                new KeyFrame(Duration.seconds(40.2),
                        new KeyValue(miniMedium.opacityProperty(),0),               ////Medium start!!
                        new KeyValue(mediumFrame.opacityProperty(),0),
                        new KeyValue(jungle.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.4),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(jungle.opacityProperty(),jungleStart)),       //////Medium on  0.426
                new KeyFrame(Duration.seconds(40.4),
                        new KeyValue(miniHard.opacityProperty(),0),             ////Hard start!!!
                        new KeyValue(hardFrame.opacityProperty(),0),
                        new KeyValue(redtree.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.6),                              //////Hard on 0.494
                        new KeyValue(miniHard.opacityProperty(),1),
                        new KeyValue(hardFrame.opacityProperty(),1),
                        new KeyValue(redtree.opacityProperty(),redtreeStart)),
                new KeyFrame(Duration.seconds(40.6),
                        new KeyValue(logAndReg.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.7),
                        new KeyValue(logAndReg.opacityProperty(),1))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            blacksun.setDisable(true);
            blacksun.setVisible(false);
            sun.setDisable(true);
            sun.setVisible(false);
            startBlack.setDisable(true);
            startBlack.setVisible(false);
            lightning.setDisable(true);
            lightning.setVisible(false);
            first.setDisable(true);
            first.setVisible(false);
            second.setDisable(true);
            second.setVisible(false);
            timeline.stop();

        });
    }

    private void burningSunMove() {

        burningsun.setOpacity(0.3);
        burningSunLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(burningsun.layoutXProperty(),-270),
                        new KeyValue(burningsun.layoutYProperty(),-59)),
                new KeyFrame(Duration.minutes(1.5),
                        new KeyValue(burningsun.layoutYProperty(),-59 - 80)),
                new KeyFrame(Duration.minutes(3),
                        new KeyValue(burningsun.layoutXProperty(),-270 + 1460),
                        new KeyValue(burningsun.layoutYProperty(),-59))
        );

        burningSunLine.playFromStart();
        burningSunLine.setOnFinished(actionEvent -> {

            burningsun.setLayoutX(-270);
            burningsun.setLayoutY(-59);

            burningSunLine.play();
        });
    }

    public void playBuringSun() {

        burningSunMove();

    }

    public void stopBurningSun() {


        burningSunLine.stop();

    }

    public void setGlow(ImageView imageView) {

        Glow glow = new Glow();
        imageView.setEffect(glow);

        glowLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.levelProperty(),0)),
                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(glow.levelProperty(),0.07)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(glow.levelProperty(), 0.02)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(glow.levelProperty(),0.05)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(glow.levelProperty(),0.01)),
                new KeyFrame(Duration.seconds(1.1),
                        new KeyValue(glow.levelProperty(),0.08)),
                new KeyFrame(Duration.seconds(1.2),
                        new KeyValue(glow.levelProperty(),0.04)),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(glow.levelProperty(),0.06)),
                new KeyFrame(Duration.seconds(1.9),
                        new KeyValue(glow.levelProperty(),0.03))
        );

        glowLine.setAutoReverse(true);
        glowLine.setCycleCount(Timeline.INDEFINITE);
    }

    public void stopGlow() {

        glowLine.stop();
    }

    public void playGlow() {

        glowLine.play();
    }
}