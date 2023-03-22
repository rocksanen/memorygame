package visuals.effects.introEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import visuals.Effects;
import visuals.audio.AudioMemory;
import visuals.effects.gameEffects.BackGroundMover;
import visuals.effects.menuEffects.BurningSun;
import visuals.effects.menuEffects.MenuLayoutEffects;

public class IntroEffects{

    private final double japanStart = 0.6; //0.26
    private final double jungleStart = 0.29; // 0.2
    private final double redtreeStart = 0.75; //0.35
    private final Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    private final Rotate jungleZ = new Rotate(0, Rotate.Z_AXIS);
    public void intro(
            @NotNull Label first, @NotNull ImageView groupFour,
            @NotNull Pane logAndReg, @NotNull ImageView sun,
            @NotNull ImageView lightning, @NotNull ImageView blacksun,
            @NotNull ImageView easyFrame, @NotNull ImageView mediumFrame, @NotNull ImageView hardFrame,
            ImageView memomaze, Label textLoggedIn, ImageView loading,
            ImageView kotoku, ImageView tigerden, ImageView treeoflife, ImageView pergament,
            ImageView burningSun, AnchorPane startBlack,Pane gamePane,
            ImageView japan, ImageView jungle, ImageView redtree,
            ImageView miniEasy, ImageView miniMedium, ImageView miniHard, ImageView dirt) {


        logAndReg.setOpacity(0);
        easyFrame.setOpacity(0);
        mediumFrame.setOpacity(0);
        hardFrame.setOpacity(0);
        japan.setOpacity(0);
        jungle.setOpacity(0);
        redtree.setOpacity(0);
        miniEasy.setOpacity(0);
        miniMedium.setOpacity(0);
        miniHard.setOpacity(0);
        kotoku.setOpacity(0);
        tigerden.setOpacity(0);
        treeoflife.setOpacity(0);


        AudioMemory.noIntro = true;
        SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(1);
        Platform.runLater(() -> pergament.setEffect(sepiaTone));

        SepiaTone sunTone = new SepiaTone();
        sunTone.setLevel(0);
        Platform.runLater(() -> sun.setEffect(sunTone));

        Timeline timelySun = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(28.5))
        );


        timelySun.setOnFinished(actionEvent -> {

            Platform.runLater(() -> BurningSun.getInstance().burningSunMove(burningSun));

        });

        timelySun.play();


        BoxBlur fourblur = new BoxBlur();
        fourblur.setIterations(1);
        fourblur.setHeight(0);
        fourblur.setWidth(0);

        groupFour.setEffect(fourblur);

        final GaussianBlur sunblur = new GaussianBlur();
        sun.setEffect(sunblur);

        final Reflection reflection = new Reflection();
        reflection.setBottomOpacity(0.04);
        reflection.setTopOpacity(0.08);
        reflection.setTopOffset(0);
        reflection.setFraction(0);

        first.setEffect(reflection);

        EventHandler<ActionEvent> startAudio = arg0 -> AudioMemory.getInstance().playTheIntro();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,startAudio),
                new KeyFrame(Duration.seconds(3.5),
                        new KeyValue(first.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(5.8),
                        new KeyValue(first.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(7.05),
                        new KeyValue(lightning.opacityProperty(), 0)),                  ///Lightning start!
                new KeyFrame(Duration.seconds(7.15),
                        new KeyValue(reflection.fractionProperty(), 0)),
                new KeyFrame(Duration.seconds(7.18),
                        new KeyValue(reflection.fractionProperty(), reflection.getFraction() + 0.7)),
                new KeyFrame(Duration.seconds(7.25),
                        new KeyValue(lightning.opacityProperty(), 0.6)),
                new KeyFrame(Duration.seconds(13.5),
                        new KeyValue(lightning.opacityProperty(), 0)),                  ///Lightning end!
                new KeyFrame(Duration.seconds(13.7),
                        new KeyValue(first.opacityProperty(), 0),
                        new KeyValue(sun.opacityProperty(), 0),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth()),
                        new KeyValue(sun.rotateProperty(), sun.getRotate()),
                        new KeyValue(sun.scaleYProperty(), sun.getScaleY()),
                        new KeyValue(sun.layoutYProperty(), sun.getLayoutY())),
                new KeyFrame(Duration.seconds(14.8),
                        new KeyValue(groupFour.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(15.1),
                        new KeyValue(groupFour.opacityProperty(), 1),
                        new KeyValue(sun.opacityProperty(), 0.24),
                        new KeyValue(sun.layoutXProperty(), sun.getLayoutX())),
                new KeyFrame(Duration.seconds(16.8),
                        new KeyValue(fourblur.widthProperty(),0)),
                new KeyFrame(Duration.seconds(17),
                        new KeyValue(fourblur.widthProperty(),13)),
                new KeyFrame(Duration.seconds(17.2),
                        new KeyValue(fourblur.widthProperty(),0)),
                new KeyFrame(Duration.seconds(24.2),
                        new KeyValue(groupFour.opacityProperty(), 0),
                        new KeyValue(sun.opacityProperty(), 0.5),
                        new KeyValue(sunTone.levelProperty(), 0)),
                new KeyFrame(Duration.seconds(26.2),
                        new KeyValue(startBlack.opacityProperty(),1),
                        new KeyValue(sunblur.radiusProperty(),0),
                        new KeyValue(memomaze.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(30),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(31),
                        new KeyValue(memomaze.opacityProperty(),1),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(32),
                        new KeyValue(loading.opacityProperty(),0),
                        new KeyValue(sun.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(33),
                        new KeyValue(gamePane.opacityProperty(),0),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(34),
                        new KeyValue(loading.opacityProperty(),0),
                        new KeyValue(startBlack.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(35),
                        new KeyValue(gamePane.opacityProperty(),1),
                        new KeyValue(sunTone.levelProperty(), 0.9),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(36),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(36.4),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth() + 80),
                        new KeyValue(sun.layoutXProperty(),sun.getLayoutX() - 8),
                        new KeyValue(sunblur.radiusProperty(), 50),
                        new KeyValue(sun.rotateProperty(),sun.getRotate() - 8),
                        new KeyValue(sun.scaleYProperty(),sun.getScaleY() + 0.5),
                        new KeyValue(sun.layoutYProperty(),sun.getLayoutY() + 130)),
                new KeyFrame(Duration.seconds(37),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(38),
                        new KeyValue(memomaze.layoutYProperty(),memomaze.getLayoutY()),
                        new KeyValue(memomaze.opacityProperty(),1),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.2),
                        new KeyValue(miniEasy.opacityProperty(),0),                 // Easy start!!!!
                        new KeyValue(easyFrame.opacityProperty(),0),
                        new KeyValue(japan.opacityProperty(),0),
                        new KeyValue(memomaze.layoutYProperty(),memomaze.getLayoutY() - 80),
                        new KeyValue(memomaze.opacityProperty(),0),
                        new KeyValue(kotoku.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.4),
                        new KeyValue(miniEasy.opacityProperty(),1),                 // Easy on!!!
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(),japanStart),
                        new KeyValue(kotoku.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(40.4),
                        new KeyValue(miniMedium.opacityProperty(),0),               ////Medium start!!
                        new KeyValue(mediumFrame.opacityProperty(),0),
                        new KeyValue(jungle.opacityProperty(),0),
                        new KeyValue(tigerden.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.6),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(jungle.opacityProperty(),jungleStart),
                        new KeyValue(tigerden.opacityProperty(),1)),       //////Medium on  0.426
                new KeyFrame(Duration.seconds(40.6),
                        new KeyValue(miniHard.opacityProperty(), 0),             ////Hard start!!!
                        new KeyValue(hardFrame.opacityProperty(), 0),
                        new KeyValue(redtree.opacityProperty(), 0),
                        new KeyValue(treeoflife.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.8),                              //////Hard on 0.494
                        new KeyValue(miniHard.opacityProperty(), 1),
                        new KeyValue(hardFrame.opacityProperty(), 1),
                        new KeyValue(redtree.opacityProperty(), redtreeStart),
                        new KeyValue(treeoflife.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(40.8),
                        new KeyValue(logAndReg.opacityProperty(), 0),
                        new KeyValue(sepiaTone.levelProperty(), 1)),
                new KeyFrame(Duration.seconds(40.9),
                        new KeyValue(logAndReg.opacityProperty(), 1),
                        new KeyValue(sepiaTone.levelProperty(), 0))
        );



        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            timeline.stop();
            moveMenuBackground(pergament,dirt,redtree,jungle);
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
            groupFour.setDisable(true);
            groupFour.setVisible(false);
            textLoggedIn.setVisible(true);
        });
    }

    private void moveMenuBackground(ImageView pergament, ImageView dirt, ImageView redtree, ImageView jungle) {

        MenuLayoutEffects menuLayoutEffects = new MenuLayoutEffects();

        Platform.runLater(() -> menuLayoutEffects.setGlow(pergament));
        Platform.runLater(() -> menuLayoutEffects.moveDirt(dirt));
        Platform.runLater(() -> menuLayoutEffects.moveJungle(jungle));
        Platform.runLater(() -> menuLayoutEffects.moveRedTree(redtree));

        Timeline backMover = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX())),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX() + 0.4)));

        redtree.getTransforms().add(rotateZ);
        jungle.getTransforms().add(jungleZ);

        Timeline redLine = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(rotateZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(rotateZ.angleProperty(), 4)));

        Timeline jungleLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(jungleZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(20),
                        new KeyValue(jungleZ.angleProperty(), -1.2)));

        jungleLine.setAutoReverse(true);
        jungleLine.setCycleCount(Timeline.INDEFINITE);
        jungleLine.play();

        redLine.setAutoReverse(true);
        redLine.setCycleCount(Timeline.INDEFINITE);
        redLine.play();
        backMover.setAutoReverse(true);
        backMover.setCycleCount(Timeline.INDEFINITE);
        backMover.play();
        System.out.println(backMover.getCycleCount() + " cycle määrä");
    }
}
