package visuals;

import com.sun.jdi.VoidType;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
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
    ImageView japan;
    ImageView jungle;
    ImageView redtree;
    ImageView miniEasy;
    ImageView miniMedium;
    ImageView miniHard;
    ImageView easyFrame;
    ImageView mediumFrame;
    ImageView hardFrame;
    ImageView pergament;
    AnchorPane menuAnkkuri;
    AnchorPane startBlack;
    Pane gamePane;
    ArrayList<ImageView> mtLista;
    final double japanStart = 0.26;
    final double jungleStart = 0.2;
    final double redtreeStart = 0.35;
    private final BackGroundMover backGroundMover = new BackGroundMover();

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
            Pane gamePane, ArrayList<ImageView> mtLista) {

        this.pergament = pergament;
        this.menuAnkkuri = menuAnkkuri;
        this.startBlack = startBlack;
        this.gamePane = gamePane;
        this.mtLista = mtLista;


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

        switch (type) {

            case EASY -> miniEasy.setOpacity(1);
            case MEDIUM -> miniMedium.setOpacity(1);
            case HARD -> miniHard.setOpacity(1);
        }

        Platform.runLater(backGroundMover::stop);
        Platform.runLater(backGroundMover::returnToPositionZero);

        Timeline blurOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(cubeGrid.opacityProperty(),cubeGrid.getOpacity()),
                        new KeyValue(gaussianBlur.radiusProperty(), gaussianBlur.getRadius())),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(gaussianBlur.radiusProperty(),0),
                        new KeyValue(cubeGrid.opacityProperty(),0))
        );

        blurOut.play();

        blurOut.setOnFinished(actionEvent -> {

            Platform.runLater(() -> Visibilities.getInstance().offGameGrid(cubeGrid));
            Visibilities.getInstance().offGame(gameBackGround);

                Gui.camera.setTranslateZ(zOffset);
                Gui.camera.setFieldOfView(fovOffset);
                Gui.camera.setTranslateX(xOffset);
                Gui.camera.setTranslateY(yOffset);


            menuAnkkuri.setOpacity(1);

            Platform.runLater(() -> Gui.logAndReg.setVisible(true));

            Platform.runLater(() -> AudioMemory.getInstance().stopSong(type));
            Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.MAIN));

            Timeline timelineZoomOut = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(Gui.camera.translateZProperty(), Gui.camera.getTranslateZ()),
                            new KeyValue(Gui.camera.fieldOfViewProperty(), Gui.camera.getFieldOfView()),
                            new KeyValue(Gui.camera.translateXProperty(), Gui.camera.getTranslateX()),
                            new KeyValue(Gui.camera.translateYProperty(), Gui.camera.getTranslateY()),
                            new KeyValue(pergament.opacityProperty(),0),
                            new KeyValue(miniEasy.opacityProperty(),miniEasy.getOpacity()),
                            new KeyValue(miniMedium.opacityProperty(),miniMedium.getOpacity()),
                            new KeyValue(miniHard.opacityProperty(),miniHard.getOpacity()),
                            new KeyValue(japan.opacityProperty(),0),
                            new KeyValue(jungle.opacityProperty(),0),
                            new KeyValue(redtree.opacityProperty(),0)


                    ),
                    new KeyFrame(Duration.seconds(1.8),
                            new KeyValue(Gui.camera.translateZProperty(), 0),
                            new KeyValue(Gui.camera.fieldOfViewProperty(), 25),
                            new KeyValue(Gui.camera.translateXProperty(), 0),
                            new KeyValue(Gui.camera.translateYProperty(), 0),
                            new KeyValue(pergament.opacityProperty(),1),
                            new KeyValue(miniEasy.opacityProperty(),1),
                            new KeyValue(miniMedium.opacityProperty(),1),
                            new KeyValue(miniHard.opacityProperty(),1),
                            new KeyValue(japan.opacityProperty(),japanStart),
                            new KeyValue(jungle.opacityProperty(),jungleStart),
                            new KeyValue(redtree.opacityProperty(),redtreeStart)
                    )
            );

            timelineZoomOut.play();

            timelineZoomOut.setOnFinished(actionEvent1 -> {

                Platform.runLater(this::playGlow);
                Platform.runLater(() -> menuAnkkuri.setMouseTransparent(false));
            });
        });
    }

    public void gameZoomIn(

            ImageView gameBackGround,
            double zOffset, double fovOffset, double xOffset,
            double yOffset, ModeType type, Gui gui

    ) {

        final double z = Gui.camera.getTranslateZ();
        final double fov = Gui.camera.getFieldOfView();
        final double x = Gui.camera.getTranslateX();
        final double y = Gui.camera.getTranslateY();

        double easyFinish = 0;
        double mediumFinish = 0;
        double hardFinish = 0;

        switch (type) {

            case EASY -> {

                miniEasy.setOpacity(1);
                easyFinish = 1;

            }
            case MEDIUM -> {

                miniMedium.setOpacity(1);
                mediumFinish = 1;

            }
            case HARD -> {

                miniHard.setOpacity(1);
                hardFinish = 1;

            }
        }

            Timeline timelineZoom = new Timeline(

                    new KeyFrame(Duration.ZERO,
                            new KeyValue(Gui.camera.translateZProperty(), z),
                            new KeyValue(Gui.camera.fieldOfViewProperty(), fov),
                            new KeyValue(Gui.camera.translateXProperty(), x),
                            new KeyValue(Gui.camera.translateYProperty(), y),
                            new KeyValue(pergament.opacityProperty(),1),
                            new KeyValue(miniEasy.opacityProperty(),1),
                            new KeyValue(miniMedium.opacityProperty(),1),
                            new KeyValue(miniHard.opacityProperty(),1),
                            new KeyValue(japan.opacityProperty(),japanStart),
                            new KeyValue(jungle.opacityProperty(),jungleStart),
                            new KeyValue(redtree.opacityProperty(),redtreeStart)

                    ),
                    new KeyFrame(Duration.seconds(0.9),
                            new KeyValue(Gui.camera.translateZProperty(), z + zOffset/2),
                            new KeyValue(Gui.camera.fieldOfViewProperty(), fov + fovOffset/2),
                            new KeyValue(Gui.camera.translateXProperty(), x + xOffset/2),
                            new KeyValue(Gui.camera.translateYProperty(), y + yOffset/2)),
                    new KeyFrame(Duration.seconds(1.8),
                            new KeyValue(Gui.camera.translateZProperty(), z + zOffset),
                            new KeyValue(Gui.camera.fieldOfViewProperty(), fov + fovOffset),
                            new KeyValue(Gui.camera.translateXProperty(), x + xOffset),
                            new KeyValue(Gui.camera.translateYProperty(), y + yOffset),
                            new KeyValue(pergament.opacityProperty(),0),
                            new KeyValue(miniEasy.opacityProperty(),easyFinish),
                            new KeyValue(miniMedium.opacityProperty(),mediumFinish),
                            new KeyValue(miniHard.opacityProperty(),hardFinish),
                            new KeyValue(japan.opacityProperty(),0),
                            new KeyValue(jungle.opacityProperty(),0),
                            new KeyValue(redtree.opacityProperty(),0)
                    ),
                    new KeyFrame(Duration.seconds(2))

            );
            timelineZoom.play();


            timelineZoom.setOnFinished(actionEvent -> {

                timelineZoom.stop();

                Platform.runLater(() -> {
                    Timeline quickSwitch = new Timeline(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(menuAnkkuri.opacityProperty(), 1),
                                    new KeyValue(Gui.camera.translateXProperty(), Gui.camera.getTranslateX()),
                                    new KeyValue(Gui.camera.translateYProperty(), Gui.camera.getTranslateY()),
                                    new KeyValue(Gui.camera.translateZProperty(), Gui.camera.getTranslateZ())
                            ),
                            new KeyFrame(Duration.seconds(0.0001),
                                    new KeyValue(menuAnkkuri.opacityProperty(), 0),
                                    new KeyValue(Gui.camera.translateXProperty(), x),
                                    new KeyValue(Gui.camera.translateYProperty(), y),
                                    new KeyValue(Gui.camera.translateZProperty(), z)
                            )
                    );

                    menuAnkkuri.setMouseTransparent(true);
                    quickSwitch.play();
                    gui.startChoose(type);
                    quickSwitch.setOnFinished(actionEvent1 -> {
                        quickSwitch.stop();
                        Platform.runLater(() -> AudioMemory.getInstance().stopSong(ModeType.MAIN));
                        Platform.runLater(() -> AudioMemory.getInstance().playSong(type));
                        Platform.runLater(() -> backGroundMover.animate(gameBackGround));
                        Platform.runLater(() -> backGroundBlurIn(gameBackGround));
                        Platform.runLater(backGroundMover::play);
                        Gui.camera.setFieldOfView(1);
                    });
                });
            });
    }


    public void bringGameUp(
            Label first, Label second,
            Pane logAndReg, ImageView sun,
            ImageView lightning, ImageView blacksun,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame) {

        Glow textGlow = new Glow();
        textGlow.setLevel(0.6);
        second.setEffect(textGlow);

        GaussianBlur sunblur = new GaussianBlur();
        sun.setEffect(sunblur);

        Reflection reflection = new Reflection();
        reflection.setBottomOpacity(0.04);
        reflection.setTopOpacity(0.08);
        reflection.setTopOffset(0);
        reflection.setFraction(0);

        first.setEffect(reflection);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(7.2),
                        new KeyValue(first.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(11.45),
                        new KeyValue(first.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(11.65),
                        new KeyValue(lightning.opacityProperty(),0)),                  ///Lightning start!
                new KeyFrame(Duration.seconds(11.75),
                        new KeyValue(reflection.fractionProperty(),0)),
                new KeyFrame(Duration.seconds(11.85),
                        new KeyValue(lightning.opacityProperty(),0.5),
                        new KeyValue(reflection.fractionProperty(),reflection.getFraction() + 0.7)),
                new KeyFrame(Duration.seconds(14.5),
                        new KeyValue(lightning.opacityProperty(),0)),                  ///Lightning end!
                new KeyFrame(Duration.seconds(14.7),
                        new KeyValue(first.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(16.2),
                        new KeyValue(sun.opacityProperty(),0),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth()),
                        new KeyValue(sun.rotateProperty(),sun.getRotate()),
                        new KeyValue(sun.scaleYProperty(),sun.getScaleY()),
                        new KeyValue(sun.layoutYProperty(),sun.getLayoutY())),
                new KeyFrame(Duration.seconds(16.5),
                        new KeyValue(second.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(20.3),
                        new KeyValue(second.opacityProperty(),1),
                        new KeyValue(sun.opacityProperty(),0.14),
                        new KeyValue(sun.layoutXProperty(),sun.getLayoutX())),
                new KeyFrame(Duration.seconds(23),
                        new KeyValue(textGlow.levelProperty(),textGlow.getLevel())),
                new KeyFrame(Duration.seconds(23.8),
                        new KeyValue(textGlow.levelProperty(), textGlow.getLevel() + 0.4)),
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
                new KeyFrame(Duration.seconds(35.502),
                        new KeyValue(mtLista.get(0).opacityProperty(),0)),         // Map steps start!
                new KeyFrame(Duration.seconds(35.928),
                        new KeyValue(mtLista.get(0).opacityProperty(),1),
                        new KeyValue(mtLista.get(1).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(36.354),
                        new KeyValue(mtLista.get(1).opacityProperty(),1),
                        new KeyValue(mtLista.get(2).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(36.4),
                        new KeyValue(miniEasy.opacityProperty(),0),                 // Easy start!!!!
                        new KeyValue(easyFrame.opacityProperty(),0),
                        new KeyValue(japan.opacityProperty(),0),
                        new KeyValue(startBlack.opacityProperty(),0),
                        new KeyValue(sun.opacityProperty(),0),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth() + 80),
                        new KeyValue(sun.layoutXProperty(),sun.getLayoutX() - 8),
                        new KeyValue(sunblur.radiusProperty(), 50),
                        new KeyValue(sun.rotateProperty(),sun.getRotate() - 8),
                        new KeyValue(sun.scaleYProperty(),sun.getScaleY() + 0.5),
                        new KeyValue(sun.layoutYProperty(),sun.getLayoutY() + 130)),
                new KeyFrame(Duration.seconds(36.78),
                        new KeyValue(mtLista.get(2).opacityProperty(),1)),
                new KeyFrame(Duration.seconds(36.8),
                        new KeyValue(miniEasy.opacityProperty(),1),                 // Easy on!!!
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(),0.26),
                        new KeyValue(blacksun.opacityProperty(),0),
                        new KeyValue(mtLista.get(3).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(37.226),
                        new KeyValue(mtLista.get(3).opacityProperty(),1),
                        new KeyValue(mtLista.get(4).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(37.4),
                        new KeyValue(blacksun.layoutYProperty(),blacksun.getLayoutY() - 15)),
                new KeyFrame(Duration.seconds(37.652),
                        new KeyValue(mtLista.get(4).opacityProperty(),1),
                        new KeyValue(mtLista.get(5).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(38.078),
                        new KeyValue(mtLista.get(5).opacityProperty(),1),
                        new KeyValue(mtLista.get(6).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(38.504),
                        new KeyValue(mtLista.get(6).opacityProperty(),1),
                        new KeyValue(mtLista.get(7).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(38.7),
                        new KeyValue(miniMedium.opacityProperty(),0),               ////Medium start!!
                        new KeyValue(mediumFrame.opacityProperty(),0),
                        new KeyValue(jungle.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(39.02),
                        new KeyValue(mtLista.get(7).opacityProperty(),1)),
                new KeyFrame(Duration.seconds(39.03),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(jungle.opacityProperty(),0.2),
                        new KeyValue(mtLista.get(8).opacityProperty(),0)),       //////Medium on  0.426
                new KeyFrame(Duration.seconds(39.524),
                        new KeyValue(mtLista.get(8).opacityProperty(),1),
                        new KeyValue(mtLista.get(9).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.018),
                        new KeyValue(mtLista.get(9).opacityProperty(),1),
                        new KeyValue(mtLista.get(10).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.512),
                        new KeyValue(mtLista.get(10).opacityProperty(),1),
                        new KeyValue(mtLista.get(11).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(41),
                        new KeyValue(mtLista.get(11).opacityProperty(),1),
                        new KeyValue(mtLista.get(12).opacityProperty(),0),
                        new KeyValue(miniHard.opacityProperty(),0),             ////Hard start!!!
                        new KeyValue(hardFrame.opacityProperty(),0),
                        new KeyValue(redtree.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(41.5),                              //////Hard on 0.494
                        new KeyValue(mtLista.get(12).opacityProperty(),1),
                        new KeyValue(miniHard.opacityProperty(),1),
                        new KeyValue(hardFrame.opacityProperty(),1),
                        new KeyValue(redtree.opacityProperty(),0.35)),
                new KeyFrame(Duration.seconds(43),
                        new KeyValue(logAndReg.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(45),
                        new KeyValue(logAndReg.opacityProperty(),1))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            blacksun.setDisable(true);
            sun.setDisable(true);
            startBlack.setDisable(true);
            lightning.setDisable(true);
            first.setDisable(true);
            second.setDisable(true);
            timeline.stop();
        });
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
