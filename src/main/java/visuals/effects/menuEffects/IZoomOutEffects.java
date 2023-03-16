package visuals.effects.menuEffects;

import model.ModeType;
import org.jetbrains.annotations.NotNull;

public interface IZoomOutEffects {

    void gameZoomOut(double zOffset, double fovOffset, double xOffset, double yOffset, @NotNull ModeType type);
    void zoomOutCamera();
}
