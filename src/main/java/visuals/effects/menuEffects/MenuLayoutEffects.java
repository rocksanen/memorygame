package visuals.effects.menuEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

public class MenuLayoutEffects implements IMenuLayoutEffects{

    private final Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    private final Rotate jungleZ = new Rotate(0, Rotate.Z_AXIS);
    private Timeline dirtMover;
    private Timeline glowLine;
    private Timeline redLine;
    private Timeline jungleLine;
    private Timeline infoLine;

    @Override
    public void displayInfoOn(ImageView a, ImageView b, ImageView c) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(a.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(a.opacityProperty(),1),
                        new KeyValue(b.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(b.opacityProperty(),1),
                        new KeyValue(c.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(c.opacityProperty(),1))
        );

        timeline.playFromStart();
        timeline.setOnFinished(actionEvent -> timeline.stop());
    }

    @Override
    public void displayInfoOff(ImageView a, ImageView b, ImageView c) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(a.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(a.opacityProperty(),0),
                        new KeyValue(b.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(b.opacityProperty(),0),
                        new KeyValue(c.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(c.opacityProperty(),0))
        );

        timeline.playFromStart();
        timeline.setOnFinished(actionEvent -> timeline.stop());
    }

    @Override
    public void moveDirt(ImageView dirt) {

        dirtMover = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX())),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX() + 0.4)));

        dirtMover.setAutoReverse(true);
        dirtMover.setCycleCount(Timeline.INDEFINITE);
        dirtMover.play();
    }

    @Override
    public void moveJungle(ImageView jungle) {

        jungle.getTransforms().add(jungleZ);

        jungleLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(jungleZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(20),
                        new KeyValue(jungleZ.angleProperty(), -1.2)));

        jungleLine.setAutoReverse(true);
        jungleLine.setCycleCount(Timeline.INDEFINITE);
        jungleLine.play();
    }

    @Override
    public void moveRedTree(ImageView redtree) {

        redtree.getTransforms().add(rotateZ);

        redLine = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(rotateZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(rotateZ.angleProperty(), 4)));

        redLine.setAutoReverse(true);
        redLine.setCycleCount(Timeline.INDEFINITE);
        redLine.play();
    }

    @Override
    public void setGlow(@NotNull ImageView imageView) {

        Glow glow = new Glow();
        imageView.setEffect(glow);

        glowLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.levelProperty(), 0)),
                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(glow.levelProperty(), 0.07)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(glow.levelProperty(), 0.02)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(glow.levelProperty(), 0.05)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(glow.levelProperty(), 0.01)),
                new KeyFrame(Duration.seconds(1.1),
                        new KeyValue(glow.levelProperty(), 0.08)),
                new KeyFrame(Duration.seconds(1.2),
                        new KeyValue(glow.levelProperty(), 0.04)),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(glow.levelProperty(), 0.06)),
                new KeyFrame(Duration.seconds(1.9),
                        new KeyValue(glow.levelProperty(), 0.03))
        );

        glowLine.setAutoReverse(true);
        glowLine.setCycleCount(Timeline.INDEFINITE);
        glowLine.play();
    }

    @Override
    public void stopTimelines() {
        glowLine.stop();
        dirtMover.stop();
        jungleLine.stop();
        redLine.stop();
        infoLine.stop();
    }

    @Override
    public void infoBlink(Node source) {

        Glow glow = new Glow();
        glow.setLevel(0);
        source.setEffect(glow);

        infoLine = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(glow.levelProperty(),0)),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(glow.levelProperty(),1)),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(glow.levelProperty(),0)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(glow.levelProperty(),1)),
                new KeyFrame(Duration.seconds(6),
                        new KeyValue(glow.levelProperty(),0)),
                new KeyFrame(Duration.seconds(7),
                        new KeyValue(glow.levelProperty(),1)),
                new KeyFrame(Duration.seconds(8),
                        new KeyValue(glow.levelProperty(),0)),
                new KeyFrame(Duration.seconds(11))
        );

        infoLine.setCycleCount(Timeline.INDEFINITE);
        infoLine.playFromStart();
    }

    @Override
    public void stopInfoBlink() {
        infoLine.stop();
    }
}
