package visuals.effects.menuEffects;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

/**

 The IMenuLayoutEffects interface defines methods for displaying and animating effects on image views used in the menu layout.
 */
public interface IMenuLayoutEffects {

    /**

     Displays information on three image views.
     @param a the first image view to display information on.
     @param b the second image view to display information on.
     @param c the third image view to display information on.
     */
    void displayInfoOn(ImageView a, ImageView b, ImageView c);
    /**

     Hides information displayed on three image views.
     @param a the first image view to hide information on.
     @param b the second image view to hide information on.
     @param c the third image view to hide information on.
     */
    void displayInfoOff(ImageView a, ImageView b, ImageView c);
    /**

     Moves an image view representing dirt.
     @param dirt the image view representing dirt to move.
     */
    void moveDirt(ImageView dirt);
    /**

     Moves an image view representing jungle.
     @param jungle the image view representing jungle to move.
     */
    void moveJungle(ImageView jungle);
    /**

     Moves an image view representing a red tree.
     @param redtree the image view representing a red tree to move.
     */
    void moveRedTree(ImageView redtree);
    /**

     Sets a glow effect on an image view.
     @param imageView the image view to set the glow effect on.
     @throws NullPointerException if imageView is null.
     */
    void setGlow(@NotNull ImageView imageView);
    /**

     Stops all running timelines used for animations.
     */
    void stopTimelines();
    /**

     Makes an information label blink.
     @param source the node to make blink.
     */
    void infoBlink(Node source);
    /**

     Stops the blinking of an information label.
     */
    void stopInfoBlink();
}
