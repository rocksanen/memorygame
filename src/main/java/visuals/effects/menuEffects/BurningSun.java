package visuals.effects.menuEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/**

 The BurningSun class represents a singleton object that manages the movement of a given ImageView

 of a sun across the screen with a pre-defined path and time duration.
 */
public class BurningSun{

    /**

     The single instance of the BurningSun class.
     */
    private static BurningSun instance;
    /**

     The duration of the current animation timeline.
     */
    private static Duration duration;
    /**

     The timeline object that animates the movement of the burning sun image.
     */
    private Timeline burningSunLine;

    /**

     Returns the single instance of the BurningSun class.

     If the instance is null, it creates a new instance of BurningSun.

     @return the instance of the BurningSun class
     */
    public static BurningSun getInstance() {

        if(instance == null) {

            instance = new BurningSun();
        }
        return instance;
    }

    /**

     Animates the movement of a given ImageView of a burning sun

     across the screen with a pre-defined path and time duration.

     @param burningsun the ImageView of the burning sun to animate
     */
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


    /**

     Saves the current position of the animation timeline and stops the animation.
     */
    public void  savePosition() {

        duration = burningSunLine.getCurrentTime();
        burningSunLine.stop();
    }
}
