package visuals.effects.commonHovers;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import visuals.effects.menuEffects.IMenuLayoutEffects;

/**

 The Hovers class provides methods for handling hover events on JavaFX nodes.

 It contains a Glow object for applying a glow effect on the node being hovered over, and an IMenuLayoutEffects object for stopping an info blink effect.
 */
public class Hovers {

    private final Glow glow;
    private final IMenuLayoutEffects menuLayoutEffects;

    /**

     The constructor for the Hovers class.
     It initializes the glow effect and the IMenuLayoutEffects object.
     @param menuLayoutEffects The IMenuLayoutEffects object used to stop an info blink effect.
     */
    public Hovers(IMenuLayoutEffects menuLayoutEffects) {

        this.menuLayoutEffects = menuLayoutEffects;
        glow = new Glow();
        glow.setLevel(0.7);
    }

    /**

     The commonHoverOn method applies a glow effect on the node being hovered over.
     @param event The event that triggered the hover.
     */
    public void commonHoverOn(Event event) {

        Node source = (Node)event.getSource();
        source.setEffect(glow);
    }

    /**

     The commonHoverOff method removes the glow effect on the node being hovered over.
     It also stops an info blink effect if the node has the ID "info".
     @param event The event that triggered the hover off.
     */

    public void commonHoverOff(Event event) {

        Node source = (Node)event.getSource();
        source.setEffect(null);
        if (source.getId().equals("info")) menuLayoutEffects.stopInfoBlink();
    }
}
