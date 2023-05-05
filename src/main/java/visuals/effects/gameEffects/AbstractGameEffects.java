package visuals.effects.gameEffects;

import model.ModeType;
import visuals.Navigaattori;
import visuals.effects.menuEffects.IZoomOutEffects;
import visuals.effects.menuEffects.ZoomOutEffects;

import java.io.IOException;

/**

 The AbstractGameEffects class is an abstract class that implements the IGameEffects interface.

 It provides a default implementation of the changeToMenu method, which changes the scene to the menu screen using the Navigaattori singleton.

 The method also calls the gameZoomOut method of a ZoomOutEffects object depending on the ModeType argument.
 */
abstract class AbstractGameEffects implements IGameEffects {

    /**

     The changeToMenu method changes the scene to the menu screen using the Navigaattori singleton.

     It also calls the gameZoomOut method of a ZoomOutEffects object depending on the ModeType argument.

     @param type The ModeType of the game to zoom out from.
     */
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
