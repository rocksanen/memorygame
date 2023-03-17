package visuals.effects.menuEffects;

import javafx.scene.image.ImageView;
import model.ModeType;
import org.jetbrains.annotations.NotNull;

public interface IZoomInEffects {

    void gameZoomIn(
            double zOffset, double fovOffset, double xOffset,
            double yOffset, @NotNull ModeType type);
    void cameraZoomIn();
    void cameraZoomInEndings();

    void setMiniImagesAndFrames(
            ImageView miniEasy, ImageView miniMedium, ImageView miniHard,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame
    );
    void setGeneralObjects(ImageView pergament);
    void opacitiesIn(
            double easyFinish, double mediumFinish, double hardFinish,
            double easeFrameFinish, double mediumFrameFinish, double hardFrameFinish
    );
    void setEssenceImages(ImageView japan, ImageView jungle, ImageView redtree);
}
