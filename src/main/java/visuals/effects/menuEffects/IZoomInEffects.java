package visuals.effects.menuEffects;

import javafx.scene.image.ImageView;
import model.ModeType;
import org.jetbrains.annotations.NotNull;

/**

 The {@code IZoomInEffects} interface defines the methods for performing zoom in effects in the game.
 */
public interface IZoomInEffects {

    /**

     Zooms in the game.
     @param zOffset the z-axis offset
     @param fovOffset the field of view offset
     @param xOffset the x-axis offset
     @param yOffset the y-axis offset
     @param type the mode type
     */
    void gameZoomIn(
            double zOffset, double fovOffset, double xOffset,
            double yOffset, @NotNull ModeType type);
    /**

     Zooms in the camera.
     */
    void cameraZoomIn();
    /**

     Zooms in the camera's endings.
     */
    void cameraZoomInEndings();

    /**

     Sets the mini images and frames for the zoom in effect.
     @param miniEasy the mini image for easy mode
     @param miniMedium the mini image for medium mode
     @param miniHard the mini image for hard mode
     @param easyFrame the frame for easy mode
     @param mediumFrame the frame for medium mode
     @param hardFrame the frame for hard mode
     */
    void setMiniImagesAndFrames(
            ImageView miniEasy, ImageView miniMedium, ImageView miniHard,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame
    );
    /**

     Sets the general objects for the zoom in effect.
     @param pergament the image of the parchment
     */
    void setGeneralObjects(ImageView pergament);
    /**

     Sets the opacities for the zoom in effect.
     @param easyFinish the opacity for easy mode finish
     @param mediumFinish the opacity for medium mode finish
     @param hardFinish the opacity for hard mode finish
     @param easeFrameFinish the opacity for the easy mode frame finish
     @param mediumFrameFinish the opacity for the medium mode frame finish
     @param hardFrameFinish the opacity for the hard mode frame finish
     */
    void opacitiesIn(
            double easyFinish, double mediumFinish, double hardFinish,
            double easeFrameFinish, double mediumFrameFinish, double hardFrameFinish
    );
    /**

     Sets the essence images for the zoom in effect.
     @param japan the image of japan
     @param jungle the image of jungle
     @param redtree the image of red tree
     */
    void setEssenceImages(ImageView japan, ImageView jungle, ImageView redtree);
}
