package visuals.effects.gameEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BackGroundMover {
    private Timeline timeline;
    private ImageView background;
    double startX;
    double startY;


    public BackGroundMover() {}

    public void animate(ImageView imageView) {

        startX = imageView.getLayoutX();
        startY = imageView.getLayoutY();
        background = imageView;

        timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(background.layoutXProperty(),startX),
                        new KeyValue(background.layoutYProperty(),startY)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(background.layoutXProperty(),startX),
                        new KeyValue(background.layoutYProperty(),startY)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(background.layoutXProperty(),startX + 10),
                        new KeyValue(background.layoutYProperty(),startY + 3)),
                new KeyFrame(Duration.seconds(9),
                        new KeyValue(background.layoutXProperty(),startX + 15),
                        new KeyValue(background.layoutYProperty(),startY + 6)));

        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void returnToPositionZero() {

        Timeline positionZero = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(background.layoutYProperty(), background.getLayoutX()),
                        new KeyValue(background.layoutYProperty(), background.getLayoutY())),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(background.layoutXProperty(), startX),
                        new KeyValue(background.layoutYProperty(), startY))
        );
        positionZero.play();

        positionZero.setOnFinished(actionEvent -> positionZero.stop());
    }

    public void play() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }


}
