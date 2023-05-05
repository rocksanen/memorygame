package visuals.effects.menuEffects;

import model.ModeType;
import org.jetbrains.annotations.NotNull;

/**

 This interface provides methods to implement zoom-out effects in the game.
 */
public interface IZoomOutEffects {

    /**

     Performs zoom-out effect on the game.
     @param zOffset the amount of zoom offset.
     @param fovOffset the amount of field of view offset.
     @param xOffset the amount of X-axis offset.
     @param yOffset the amount of Y-axis offset.
     @param type the game mode type.
     */
    void gameZoomOut(double zOffset, double fovOffset, double xOffset, double yOffset, @NotNull ModeType type);
    /**

     Performs zoom-out effect on the camera.
     */
    void zoomOutCamera();
}
