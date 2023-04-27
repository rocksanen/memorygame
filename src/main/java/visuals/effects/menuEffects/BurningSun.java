package visuals.effects.menuEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BurningSun{

    private static BurningSun instance;
    private static Duration duration;
    private Timeline burningSunLine;

    public static BurningSun getInstance() {

        if(instance == null) {

            instance = new BurningSun();
        }
        return instance;
    }

    public void burningSunMove(ImageView burningsun) {

        burningSunLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(burningsun.layoutXProperty(), -180),
                        new KeyValue(burningsun.layoutYProperty(), -59),
                        new KeyValue(burningsun.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(burningsun.opacityProperty(),0.3)),
                new KeyFrame(Duration.minutes(1.5),
                        new KeyValue(burningsun.layoutYProperty(), -59 - 80)),
                new KeyFrame(Duration.minutes(3),
                        new KeyValue(burningsun.layoutXProperty(), -260 + 1470),
                        new KeyValue(burningsun.layoutYProperty(), -59))
        );

        if(duration == null) {

            burningSunLine.playFromStart();
        }else {

            burningSunLine.playFrom(duration);
        }

        burningSunLine.setOnFinished(actionEvent -> {

            burningsun.setLayoutX(-260);
            burningsun.setLayoutY(-59);
            burningSunLine.play();
        });
    }


    public void  savePosition() {

        duration = burningSunLine.getCurrentTime();
        burningSunLine.stop();
    }
}
