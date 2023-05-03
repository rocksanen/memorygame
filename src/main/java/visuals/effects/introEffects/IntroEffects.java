package visuals.effects.introEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;

import java.io.IOException;

public class IntroEffects{

    private Timeline introLine;

    public void intro(
            Label first,ImageView groupFour, Pane logAndReg,ImageView sun,
            ImageView lightning, ImageView easyFrame,ImageView mediumFrame,
            ImageView hardFrame, ImageView memomaze,ImageView loading,
            ImageView kotoku, ImageView tigerden, ImageView treeoflife,
            ImageView pergament, AnchorPane startBlack,Pane gamePane, ImageView japan,
            ImageView jungle, ImageView redtree, ImageView miniEasy, ImageView miniMedium,
            ImageView miniHard, Pane leaderpane, ImageView info, AnchorPane bottom, Pane audioPane) {


        final SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(1);
        pergament.setEffect(sepiaTone);

        final SepiaTone sunTone = new SepiaTone();
        sunTone.setLevel(0);
        sun.setEffect(sunTone);

        final BoxBlur fourblur = new BoxBlur();
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

        //0.26
        double japanStart = 0.6;
        // 0.2
        double jungleStart = 0.29;
        //0.35
        double redtreeStart = 0.75;
        introLine = new Timeline(
                new KeyFrame(Duration.ZERO,startAudio),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(bottom.mouseTransparentProperty(),false),
                        new KeyValue(bottom.cursorProperty(), Cursor.HAND)),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(first.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(6.3),
                        new KeyValue(first.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(7.55),
                        new KeyValue(lightning.opacityProperty(), 0)),                  ///Lightning start!
                new KeyFrame(Duration.seconds(7.65),
                        new KeyValue(reflection.fractionProperty(), 0)),
                new KeyFrame(Duration.seconds(7.68),
                        new KeyValue(reflection.fractionProperty(), reflection.getFraction() + 0.7)),
                new KeyFrame(Duration.seconds(7.75),
                        new KeyValue(lightning.opacityProperty(), 0.6)),
                new KeyFrame(Duration.seconds(14),
                        new KeyValue(lightning.opacityProperty(), 0)),                  ///Lightning end!
                new KeyFrame(Duration.seconds(14.2),
                        new KeyValue(first.opacityProperty(), 0),
                        new KeyValue(sun.opacityProperty(), 0),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth()),
                        new KeyValue(sun.rotateProperty(), sun.getRotate()),
                        new KeyValue(sun.scaleYProperty(), sun.getScaleY()),
                        new KeyValue(sun.layoutYProperty(), sun.getLayoutY())),
                new KeyFrame(Duration.seconds(15.3),
                        new KeyValue(groupFour.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(15.6),
                        new KeyValue(groupFour.opacityProperty(), 1),
                        new KeyValue(sun.opacityProperty(), 0.24),
                        new KeyValue(sun.layoutXProperty(), sun.getLayoutX())),
                new KeyFrame(Duration.seconds(16.8),
                        new KeyValue(fourblur.widthProperty(),0)),
                new KeyFrame(Duration.seconds(17),
                        new KeyValue(fourblur.widthProperty(),13)),
                new KeyFrame(Duration.seconds(17.2),
                        new KeyValue(fourblur.widthProperty(),0)),
                new KeyFrame(Duration.seconds(24.7),
                        new KeyValue(groupFour.opacityProperty(), 0),
                        new KeyValue(sun.opacityProperty(), 0.5),
                        new KeyValue(sunTone.levelProperty(), 0)),
                new KeyFrame(Duration.seconds(26.7),
                        new KeyValue(startBlack.opacityProperty(),1),
                        new KeyValue(sunblur.radiusProperty(),0),
                        new KeyValue(memomaze.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(30.5),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(31.5),
                        new KeyValue(memomaze.opacityProperty(),1),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(32.5),
                        new KeyValue(loading.opacityProperty(),0),
                        new KeyValue(sun.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(33.5),
                        new KeyValue(gamePane.opacityProperty(),0),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(34.5),
                        new KeyValue(loading.opacityProperty(),0),
                        new KeyValue(startBlack.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(35.5),
                        new KeyValue(gamePane.opacityProperty(),1),
                        new KeyValue(sunTone.levelProperty(), 0.9),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(36.5),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(36.9),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth() + 80),
                        new KeyValue(sun.layoutXProperty(),sun.getLayoutX() - 8),
                        new KeyValue(sunblur.radiusProperty(), 50),
                        new KeyValue(sun.rotateProperty(),sun.getRotate() - 8),
                        new KeyValue(sun.scaleYProperty(),sun.getScaleY() + 0.5),
                        new KeyValue(sun.layoutYProperty(),sun.getLayoutY() + 130)),
                new KeyFrame(Duration.seconds(37.5),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(38.5),
                        new KeyValue(memomaze.layoutYProperty(),memomaze.getLayoutY()),
                        new KeyValue(memomaze.opacityProperty(),1),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.7),
                        new KeyValue(miniEasy.opacityProperty(),0),                 // Easy start!!!!
                        new KeyValue(easyFrame.opacityProperty(),0),
                        new KeyValue(japan.opacityProperty(),0),
                        new KeyValue(memomaze.layoutYProperty(),memomaze.getLayoutY() - 80),
                        new KeyValue(memomaze.opacityProperty(),0),
                        new KeyValue(kotoku.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.9),
                        new KeyValue(miniEasy.opacityProperty(),1),                 // Easy on!!!
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(), japanStart),
                        new KeyValue(kotoku.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(40.9),
                        new KeyValue(miniMedium.opacityProperty(),0),               ////Medium start!!
                        new KeyValue(mediumFrame.opacityProperty(),0),
                        new KeyValue(jungle.opacityProperty(),0),
                        new KeyValue(tigerden.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(41.1),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(jungle.opacityProperty(), jungleStart),
                        new KeyValue(tigerden.opacityProperty(),1)),       //////Medium on  0.426
                new KeyFrame(Duration.seconds(41.1),
                        new KeyValue(miniHard.opacityProperty(), 0),             ////Hard start!!!
                        new KeyValue(hardFrame.opacityProperty(), 0),
                        new KeyValue(redtree.opacityProperty(), 0),
                        new KeyValue(treeoflife.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(41.3),                              //////Hard on 0.494
                        new KeyValue(miniHard.opacityProperty(), 1),
                        new KeyValue(hardFrame.opacityProperty(), 1),
                        new KeyValue(redtree.opacityProperty(), redtreeStart),
                        new KeyValue(treeoflife.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(41.3),
                        new KeyValue(logAndReg.opacityProperty(), 0),
                        new KeyValue(sepiaTone.levelProperty(), 1),
                        new KeyValue(leaderpane.opacityProperty(),0),
                        new KeyValue(info.opacityProperty(),0),
                        new KeyValue(audioPane.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(41.4),
                        new KeyValue(logAndReg.opacityProperty(), 1),
                        new KeyValue(leaderpane.opacityProperty(),1),
                        new KeyValue(info.opacityProperty(),1),
                        new KeyValue(audioPane.opacityProperty(),1),
                        new KeyValue(sepiaTone.levelProperty(), 0)),
                new KeyFrame(Duration.seconds(42))
        );

        introLine.play();
        introLine.setOnFinished(actionEvent -> {

            introLine.stop();
            introLine = null;

            try {
                Navigaattori.getInstance().changeScene(ModeType.MENU);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void stopIntro() {
        introLine.stop();
    }
}
