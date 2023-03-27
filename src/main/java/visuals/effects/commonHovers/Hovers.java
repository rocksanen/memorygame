package visuals.effects.commonHovers;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import visuals.effects.menuEffects.IMenuLayoutEffects;

public class Hovers {

    private final Glow glow;
    private final IMenuLayoutEffects menuLayoutEffects;
    public Hovers(IMenuLayoutEffects menuLayoutEffects) {

        this.menuLayoutEffects = menuLayoutEffects;
        glow = new Glow();
        glow.setLevel(0.7);
    }
    public void commonHoverOn(Event event) {

        Node source = (Node)event.getSource();
        source.setEffect(glow);
    }

    public void commonHoverOff(Event event) {

        Node source = (Node)event.getSource();
        source.setEffect(null);
        if (source.getId().equals("info")) menuLayoutEffects.stopInfoBlink();
    }
}
