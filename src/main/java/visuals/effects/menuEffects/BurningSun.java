package visuals.effects.menuEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BurningSun{

    private Timeline burningSunLine;


    public void burningSunMove(ImageView burningsun) {

        burningsun.setOpacity(0.3);
        burningSunLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(burningsun.layoutXProperty(), -260),
                        new KeyValue(burningsun.layoutYProperty(), -59)),
                new KeyFrame(Duration.minutes(1.5),
                        new KeyValue(burningsun.layoutYProperty(), -59 - 80)),
                new KeyFrame(Duration.minutes(3),
                        new KeyValue(burningsun.layoutXProperty(), -260 + 1470),
                        new KeyValue(burningsun.layoutYProperty(), -59))
        );

        burningSunLine.playFromStart();
        burningSunLine.setOnFinished(actionEvent -> {

            burningsun.setLayoutX(-260);
            burningsun.setLayoutY(-59);
            burningSunLine.play();
        });
    }

    //public void playBuringSun() {burningSunMove();}
}
