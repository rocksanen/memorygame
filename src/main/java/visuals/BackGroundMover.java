package visuals;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BackGroundMover {


    private Timeline timeline;
    private final ImageView imageView;

    public BackGroundMover(ImageView imageView) {

        this.imageView = imageView;

    }

    public void Animate() {

        final double x = imageView.getLayoutX();
        final double y = imageView.getLayoutY();

        timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.layoutXProperty(),x),
                        new KeyValue(imageView.layoutYProperty(),y)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(imageView.layoutXProperty(),x),
                        new KeyValue(imageView.layoutYProperty(),y)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(imageView.layoutXProperty(),x + 10),
                        new KeyValue(imageView.layoutYProperty(),y + 3)),
                new KeyFrame(Duration.seconds(9),
                        new KeyValue(imageView.layoutXProperty(),x + 15),
                        new KeyValue(imageView.layoutYProperty(),y + 6)));

        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);


    }

    public void play() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    //bgm.stop();
    //bgm = null;




}
