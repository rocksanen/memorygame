package visuals.effects.menuEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.ModeType;
import org.jetbrains.annotations.NotNull;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;
import java.io.IOException;

/**

 The class ZoomInEffects implements the interface IZoomInEffects and is responsible

 for zooming in the camera, fading in the images and playing background music

 for the game screen.
 */
public class ZoomInEffects implements IZoomInEffects {

    private double zOffset;
    private double fovOffset;
    private double xOffset;
    private double yOffset;
    private ModeType type;
    private ImageView japan;
    private ImageView jungle;
    private ImageView redtree;
    private ImageView miniEasy;
    private ImageView miniMedium;
    private ImageView miniHard;
    private ImageView easyFrame;
    private ImageView mediumFrame;
    private ImageView hardFrame;
    private ImageView pergament;

    /**

     Sets the essence images for the menu screen.
     @param japan An ImageView object for the Japanese essence image.
     @param jungle An ImageView object for the jungle essence image.
     @param redtree An ImageView object for the red tree essence image.
     */
    @Override
    public void setEssenceImages(ImageView japan, ImageView jungle, ImageView redtree) {

        this.japan = japan;
        this.jungle = jungle;
        this.redtree = redtree;
    }

    /**

     Sets the images and frames for the difficulty levels.
     @param miniEasy An ImageView object for the easy level image.
     @param miniMedium An ImageView object for the medium level image.
     @param miniHard An ImageView object for the hard level image.
     @param easyFrame An ImageView object for the easy level frame image.
     @param mediumFrame An ImageView object for the medium level frame image.
     @param hardFrame An ImageView object for the hard level frame image.
     */
    @Override
    public void setMiniImagesAndFrames(
            ImageView miniEasy, ImageView miniMedium, ImageView miniHard,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame) {

        this.miniEasy = miniEasy;
        this.miniMedium = miniMedium;
        this.miniHard = miniHard;
        this.easyFrame = easyFrame;
        this.mediumFrame = mediumFrame;
        this.hardFrame = hardFrame;
    }

    /**

     Sets the general objects for the menu screen.
     @param pergament An ImageView object for the parchment background image.
     */
    @Override
    public void setGeneralObjects(ImageView pergament) {this.pergament = pergament;}

    /**

     Initiates the game screen zoom in effect and fades in the level images
     and their frames based on the selected difficulty level.
     @param zOffset A double value for the z-axis offset.
     @param fovOffset A double value for the field of view offset.
     @param xOffset A double value for the x-axis offset.
     @param yOffset A double value for the y-axis offset.
     @param type A ModeType object representing the selected difficulty level.
     */
    @Override
    public void gameZoomIn(
            double zOffset, double fovOffset,
            double xOffset, double yOffset, @NotNull ModeType type) {

        this.zOffset = zOffset;
        this.fovOffset = fovOffset;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.type = type;
        double easyFinish = 0;
        double mediumFinish = 0;
        double hardFinish = 0;
        double easyFrameFinish = 0;
        double mediumFrameFinish = 0;
        double hardFrameFinish = 0;

        switch (type) {

            case EASY -> {
                miniEasy.setOpacity(1);
                easyFinish = 1;
                easyFrameFinish = 0;
            }
            case MEDIUM -> {
                miniMedium.setOpacity(1);
                mediumFinish = 1;
                mediumFrameFinish = 0;

            }
            case HARD -> {
                miniHard.setOpacity(1);
                hardFinish = 1;
                hardFrameFinish = 0;
            }
        }

        cameraZoomIn();
        opacitiesIn(easyFinish, mediumFinish, hardFinish, easyFrameFinish, mediumFrameFinish, hardFrameFinish);
    }

    /**

     This method overrides the cameraZoomIn() method and zooms in the camera.

     It creates a timeline with KeyFrames to change camera properties over time.

     Once the animation is finished, it stops the timeline, stops the current song,

     plays a new song, and calls the cameraZoomInEndings() method.
     */
    @Override
    public void cameraZoomIn() {

        Timeline timelineZoomIn = new Timeline(

                new KeyFrame(Duration.ZERO,
                        new KeyValue(Navigaattori.camera.translateZProperty(), 0),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), 25),
                        new KeyValue(Navigaattori.camera.translateXProperty(), 0),
                        new KeyValue(Navigaattori.camera.translateYProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(Navigaattori.camera.translateZProperty(), 0 + zOffset / 2),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), 25 + fovOffset / 2),
                        new KeyValue(Navigaattori.camera.translateXProperty(), 0 + xOffset / 2),
                        new KeyValue(Navigaattori.camera.translateYProperty(), 0 + yOffset / 2)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(Navigaattori.camera.translateZProperty(), 0 + zOffset),
                        new KeyValue(Navigaattori.camera.fieldOfViewProperty(), 25 + fovOffset),
                        new KeyValue(Navigaattori.camera.translateXProperty(), 0 + xOffset),
                        new KeyValue(Navigaattori.camera.translateYProperty(), 0 + yOffset)
                ),
                new KeyFrame(Duration.seconds(3))

        );
        timelineZoomIn.play();

        timelineZoomIn.setOnFinished(actionEvent -> {

            timelineZoomIn.stop();
            AudioMemory.getInstance().stopSong(ModeType.MENU);
            AudioMemory.getInstance().playSong(type);
            cameraZoomInEndings();
        });
    }

    /**

     This method overrides the cameraZoomInEndings() method.
     It changes the current scene by calling the changeScene() method in Navigaattori.
     If an error occurs while changing the scene, it throws a RuntimeException.
     */
    @Override
    public void cameraZoomInEndings() {

        try {
            Navigaattori.getInstance().changeScene(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**

     This method overrides the opacitiesIn() method.

     It creates a timeline with KeyFrames to change opacity values of different elements over time.

     Once the animation is finished, it stops the timeline.

     @param easyFinish The opacity value to set for miniEasy element at the end of the animation.

     @param mediumFinish The opacity value to set for miniMedium element at the end of the animation.

     @param hardFinish The opacity value to set for miniHard element at the end of the animation.

     @param easeFrameFinish The opacity value to set for easyFrame element at the end of the animation.

     @param mediumFrameFinish The opacity value to set for mediumFrame element at the end of the animation.

     @param hardFrameFinish The opacity value to set for hardFrame element at the end of the animation.
     */
    @Override
    public void opacitiesIn(
            double easyFinish, double mediumFinish, double hardFinish,
            double easeFrameFinish, double mediumFrameFinish, double hardFrameFinish) {

        double japanStart = 0.6;
        double jungleStart = 0.29;
        double redtreeStart = 0.75;

        Timeline opacitiesIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(pergament.opacityProperty(), 1),
                        new KeyValue(miniEasy.opacityProperty(), 1),
                        new KeyValue(miniMedium.opacityProperty(), 1),
                        new KeyValue(miniHard.opacityProperty(), 1),
                        new KeyValue(japan.opacityProperty(), japanStart),
                        new KeyValue(jungle.opacityProperty(), jungleStart),
                        new KeyValue(redtree.opacityProperty(), redtreeStart),
                        new KeyValue(easyFrame.opacityProperty(), 1),
                        new KeyValue(mediumFrame.opacityProperty(), 1),
                        new KeyValue(hardFrame.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(2.3),
                        new KeyValue(easyFrame.opacityProperty(), 1),
                        new KeyValue(mediumFrame.opacityProperty(), 1),
                        new KeyValue(hardFrame.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(2.5),
                        new KeyValue(easyFrame.opacityProperty(), 0),
                        new KeyValue(mediumFrame.opacityProperty(), 0),
                        new KeyValue(hardFrame.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(pergament.opacityProperty(), 0),
                        new KeyValue(miniEasy.opacityProperty(), easyFinish),
                        new KeyValue(miniMedium.opacityProperty(), mediumFinish),
                        new KeyValue(miniHard.opacityProperty(), hardFinish),
                        new KeyValue(japan.opacityProperty(), 0),
                        new KeyValue(redtree.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(jungle.opacityProperty(), 0.3))
        );

        opacitiesIn.playFromStart();
        opacitiesIn.setOnFinished(actionEvent -> opacitiesIn.stop());
    }
}
