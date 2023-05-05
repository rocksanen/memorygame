package visuals.effects.gameEffects;

import model.ModeType;

/**

 Interface defining the effects that are used in the game.
 */
public interface IGameEffects {

    /**

     Method that displays the entrance animation.
     */
    void entrance();

    /**

     Method that removes the walls and returns to the menu.
     */
    void wallsOff();

    /**

     Method that changes to the specified game mode menu.
     @param type The type of game mode.
     */
    void changeToMenu(ModeType type);


}
