package visuals.effects.menuEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import model.ModeType;
import org.jetbrains.annotations.NotNull;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;

/**

 This class implements the IZoomOutEffects interface and provides functionality for zooming out the game camera.
 */
public class ZoomOutEffects implements IZoomOutEffects{

    /**

     Zooms out the game camera with the given parameters and stops the current playing song, plays the MENU song and calls the zoomOutCamera method.
     @param zOffset the offset value for the camera's z axis translation
     @param fovOffset the offset value for the camera's field of view
     @param xOffset the offset value for the camera's x axis translation
     @param yOffset the offset value for the camera's y axis translation
     @param type the type of the game mode
     */
    @Override
    public void gameZoomOut(double zOffset, double fovOffset, double xOffset, double yOffset, @NotNull ModeType type) {

        Navigaattori.camera.setTranslateZ(zOffset);
        Navigaattori.camera.setFieldOfView(fovOffset);
        Navigaattori.camera.setTranslateX(xOffset);
        Navigaattori.camera.setTranslateY(yOffset);
        Platform.runLater(() -> AudioMemory.getInstance().stopSong(type));
        Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.MENU));
        zoomOutCamera();
    }

    /**

     Creates a timeline for zooming out the game camera with a duration of 2.8 seconds and sets the initial and final key frames for camera translations and field of view.

     Plays the timeline and stops it when finished.
     */
    @Override
    public void zoomOutCamera() {

        Timeline timelineZoomOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(Navigaattori.camera.translateZProperty(), Navigaattori.camera.getTranslateZ()),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), Navigaattori.camera.getFieldOfView()),
                        new KeyValue(Navigaattori.camera.translateXProperty(), Navigaattori.camera.getTranslateX()),
                        new KeyValue(Navigaattori.camera.translateYProperty(), Navigaattori.camera.getTranslateY())
                ),
                new KeyFrame(Duration.seconds(1)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(Navigaattori.camera.translateZProperty(), 0),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), 25),
                        new KeyValue(Navigaattori.camera.translateXProperty(), 0),
                        new KeyValue(Navigaattori.camera.translateYProperty(), 0)
                )
        );

        timelineZoomOut.playFromStart();
        timelineZoomOut.setOnFinished(actionEvent -> timelineZoomOut.stop());
    }
}
