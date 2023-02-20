package visuals;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.ModeType;
import visuals.audio.AudioMemory;

import java.util.ArrayList;

public class Effects {
    private static Effects instance;
    private final Timeline timelineBlur = new Timeline();
    private final Timeline timelineIN = new Timeline();
    private final Timeline timelineOUT = new Timeline();
    private final GaussianBlur gaussianBlur = new GaussianBlur();

    private Timeline glowLine;
    private ImageView inView = new ImageView();
    private ImageView outView = new ImageView();
    private final BackGroundMover backGroundMover = new BackGroundMover();

    public Effects() {
        initializeTimelines();
    }

    private void initializeTimelines() {

        timelineBlur.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(gaussianBlur.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(6), new KeyValue(gaussianBlur.radiusProperty(), 10.58))
        );

        timelineIN.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(inView.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(inView.opacityProperty(), 1))
        );

        timelineOUT.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(outView.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(outView.opacityProperty(), 0))
        );

    }
    public static Effects getInstance(){

        if(instance == null) {
            instance = new Effects();
        }
        return instance;
    }

    public void backGroundIn(ImageView imageView) {

        timelineOUT.stop();
        inView = imageView;
        timelineIN.play();
    }

    public void backGroundOut(ImageView imageView) {

        timelineOUT.stop();
        outView = imageView;
        timelineOUT.play();
    }

    public void backGroundBlurIn(ImageView imageView) {

        gaussianBlur.setRadius(0);
        imageView.setEffect(gaussianBlur);
        timelineBlur.stop();
        timelineBlur.setCycleCount(1);
        timelineBlur.play();
    }


    public void gameZoomOut(

            Pane gameMode, GridPane cubeGrid, PerspectiveCamera camera,
            AnchorPane startAnchor, ImageView gameBackGround, double zOffset,
            double fovOffset, double xOffset, double yOffset,ModeType type, ImageView pergamentti

    ) {

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
            camera.setTranslateZ(zOffset);
            camera.setFieldOfView(fovOffset);
            camera.setTranslateX(xOffset);
            camera.setTranslateY(yOffset);

            Visibilities.getInstance().offGame(gameBackGround,gameMode,pergamentti);

            Platform.runLater(() -> Gui.logAndReg.setVisible(true));

            AudioMemory.getInstance().stopSong(type);
            AudioMemory.getInstance().playSong(ModeType.MAIN);

            Timeline timelineZoomOut = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(camera.translateZProperty(), camera.getTranslateZ()),
                            new KeyValue(camera.fieldOfViewProperty(), camera.getFieldOfView()),
                            new KeyValue(camera.translateXProperty(), camera.getTranslateX()),
                            new KeyValue(camera.translateYProperty(), camera.getTranslateY())
                    ),
                    new KeyFrame(Duration.seconds(2),
                            new KeyValue(camera.translateZProperty(), 0),
                            new KeyValue(camera.fieldOfViewProperty(), 25),
                            new KeyValue(camera.translateXProperty(), 0),
                            new KeyValue(camera.translateYProperty(), 0)
                    )
            );

            timelineZoomOut.play();

            timelineZoomOut.setOnFinished(actionEvent1 -> {

                Platform.runLater(() -> startAnchor.setMouseTransparent(false));
            });
        });
    }

    public void gameZoomIn(

            PerspectiveCamera camera, AnchorPane startAnchor, ImageView imageView,
            double zOffset, double fovOffset, double xOffset,
            double yOffset, Runnable callback

    ) {

        final double z = camera.getTranslateZ();
        final double fov = camera.getFieldOfView();
        final double x = camera.getTranslateX();
        final double y = camera.getTranslateY();


        Timeline timelineZoom = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(camera.translateZProperty(), z),
                        new KeyValue(camera.fieldOfViewProperty(), fov),
                        new KeyValue(camera.translateXProperty(), x),
                        new KeyValue(camera.translateYProperty(), y)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(camera.translateZProperty(), z + zOffset),
                        new KeyValue(camera.fieldOfViewProperty(), fov + fovOffset),
                        new KeyValue(camera.translateXProperty(), x + xOffset),
                        new KeyValue(camera.translateYProperty(), y + yOffset)
                )
        );

        timelineZoom.play();
        timelineZoom.setOnFinished(actionEvent -> {

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(startAnchor.opacityProperty(), 0),
                            new KeyValue(camera.translateXProperty(), camera.getTranslateX()),
                            new KeyValue(camera.translateYProperty(), camera.getTranslateY()),
                            new KeyValue(camera.translateZProperty(), camera.getTranslateZ())
                    ),
                    new KeyFrame(Duration.seconds(0.0001),
                            new KeyValue(startAnchor.opacityProperty(), 1),
                            new KeyValue(camera.translateXProperty(), x),
                            new KeyValue(camera.translateYProperty(), y),
                            new KeyValue(camera.translateZProperty(), z)
                    )
            );
            startAnchor.setMouseTransparent(true);
            timeline.play();
            callback.run();
            timeline.setOnFinished(actionEvent1 -> {

                backGroundBlurIn(imageView);
                backGroundMover.animate(imageView);
                Platform.runLater(backGroundMover::play);
            });
        });
    }


    public void bringGameUp(
            AnchorPane startBlack, Label first, Label second,
            Pane gamePane, Pane logAndReg, ImageView sun,
            ImageView lightning, ImageView blacksun, ArrayList<ImageView> mtLista,
            ImageView miniEasy, ImageView miniMedium, ImageView miniHard,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame,
            ImageView japan, ImageView jungle, ImageView redtree) {

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
                new KeyFrame(Duration.seconds(35.102),
                        new KeyValue(mtLista.get(0).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(35.528),
                        new KeyValue(mtLista.get(0).opacityProperty(),1),
                        new KeyValue(mtLista.get(1).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(35.954),
                        new KeyValue(mtLista.get(1).opacityProperty(),1),
                        new KeyValue(mtLista.get(2).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(36),
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
                new KeyFrame(Duration.seconds(36.38),
                        new KeyValue(mtLista.get(2).opacityProperty(),1)),
                new KeyFrame(Duration.seconds(36.4),
                        new KeyValue(miniEasy.opacityProperty(),1),                 // Easy on!!!
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(),0.26),
                        new KeyValue(blacksun.opacityProperty(),0),
                        new KeyValue(mtLista.get(3).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(36.826),
                        new KeyValue(mtLista.get(3).opacityProperty(),1),
                        new KeyValue(mtLista.get(4).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(37),
                        new KeyValue(blacksun.layoutYProperty(),blacksun.getLayoutY() - 15)),
                new KeyFrame(Duration.seconds(37.252),
                        new KeyValue(mtLista.get(4).opacityProperty(),1),
                        new KeyValue(mtLista.get(5).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(37.678),
                        new KeyValue(mtLista.get(5).opacityProperty(),1),
                        new KeyValue(mtLista.get(6).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(38.104),
                        new KeyValue(mtLista.get(6).opacityProperty(),1),
                        new KeyValue(mtLista.get(7).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(38.2),
                        new KeyValue(miniMedium.opacityProperty(),0),               ////Medium start!!
                        new KeyValue(mediumFrame.opacityProperty(),0),
                        new KeyValue(jungle.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(38.52),
                        new KeyValue(mtLista.get(7).opacityProperty(),1)),
                new KeyFrame(Duration.seconds(38.53),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(jungle.opacityProperty(),0.2),
                        new KeyValue(mtLista.get(8).opacityProperty(),0)),       //////Medium on  0.426
                new KeyFrame(Duration.seconds(39.024),
                        new KeyValue(mtLista.get(8).opacityProperty(),1),
                        new KeyValue(mtLista.get(9).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(39.518),
                        new KeyValue(mtLista.get(9).opacityProperty(),1),
                        new KeyValue(mtLista.get(10).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.012),
                        new KeyValue(mtLista.get(10).opacityProperty(),1),
                        new KeyValue(mtLista.get(11).opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.5),
                        new KeyValue(mtLista.get(11).opacityProperty(),1),
                        new KeyValue(mtLista.get(12).opacityProperty(),0),
                        new KeyValue(miniHard.opacityProperty(),0),             ////Hard start!!!
                        new KeyValue(hardFrame.opacityProperty(),0),
                        new KeyValue(redtree.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(41),                              //////Hard on 0.494
                        new KeyValue(mtLista.get(12).opacityProperty(),1),
                        new KeyValue(miniHard.opacityProperty(),1),
                        new KeyValue(hardFrame.opacityProperty(),1),
                        new KeyValue(redtree.opacityProperty(),0.35)),
                new KeyFrame(Duration.seconds(42.5),
                        new KeyValue(logAndReg.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(44.5),
                        new KeyValue(logAndReg.opacityProperty(),1))

        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            startBlack.setMouseTransparent(true);

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
