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

public class ZoomOutEffects implements IZoomOutEffects{

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
