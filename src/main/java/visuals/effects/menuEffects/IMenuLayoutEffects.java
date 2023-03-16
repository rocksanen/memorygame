package visuals.effects.menuEffects;

import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

public interface IMenuLayoutEffects {

    void displayInfoOn(ImageView a, ImageView b, ImageView c);
    void displayInfoOff(ImageView a, ImageView b, ImageView c);
    void moveDirt(ImageView dirt);
    void moveJungle(ImageView jungle);
    void moveRedTree(ImageView redtree);
    void setGlow(@NotNull ImageView imageView);
}
