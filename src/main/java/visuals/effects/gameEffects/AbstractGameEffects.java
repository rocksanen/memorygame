package visuals.effects.gameEffects;

import model.ModeType;
import visuals.Navigaattori;
import visuals.effects.menuEffects.IZoomOutEffects;
import visuals.effects.menuEffects.ZoomOutEffects;

import java.io.IOException;

abstract class AbstractGameEffects implements IGameEffects {
    @Override
    public void changeToMenu(ModeType type) {

        IZoomOutEffects zoomOutEffects = new ZoomOutEffects();

        try {
            Navigaattori.getInstance().changeScene(ModeType.MENU);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (type) {

            case EASY -> zoomOutEffects.gameZoomOut(800, 35, -145.5, 14.5, ModeType.EASY);
            case MEDIUM -> zoomOutEffects.gameZoomOut(1071, 35, 117.2, -144.92, ModeType.MEDIUM);
            case HARD -> zoomOutEffects.gameZoomOut(1000.7, 35, 384.0, 14.5, ModeType.HARD);
        }
    }
}
