package visuals.gameModes;

import model.MemoryObject;
import model.ModeType;
import visuals.cubeFactories.BoxMaker;

import java.util.ArrayList;

/**

 The FXIGameController interface provides a set of methods to control and manage the game.
 */
public interface FXIGameController {

    /**

     Adds a BoxMaker object to the cubeList.
     @param cube the BoxMaker object to be added to the cubeList
     */
    void addToCubeList(BoxMaker cube);

    /**

     Clears the pair of cube IDs stored in the storage list.
     @param storage the storage list to be cleared
     */
    void clearPair(ArrayList<Integer> storage);

    /**

     Clears the storage list.
     */
    void clearStorage();

    /**

     Sets the game cubes to the given MemoryObjects.
     @param memoryObjects the list of MemoryObjects to be set as game cubes
     */
    void setCubesToGame(ArrayList<MemoryObject> memoryObjects);

    /**

     Called when the game is over. The parameter victory determines if the game was won or lost.
     @param victory true if the game was won, false if it was lost
     */
    void gameOver(boolean victory);

    /**

     Sets the ID of the active cube and updates the activeList accordingly.
     @param activeID the ID of the active cube
     */
    void setActiveID(int activeID);

    /**

     Compares the two selected cubes to see if they match.
     */
    void compareFoundMatch();

    /**

     Gets the time remaining in the game.
     @param i the time remaining in seconds
     */
    void getTime(int i);

    /**

     Sets up the game at the start.
     */
    void setStartGame();

    /**

     Sends the ID of the selected cube to the game engine.
     @param id the ID of the selected cube
     */
    void sendIdToEngine(int id);

    /**

     Starts a new game.
     */
    void newGame();

    /**

     Returns to the game menu.
     */
    void returnMenu();

    /**

     Sets the camera properties.
     */
    void setCamera();

    /**

     Sets the images for the game.
     */
    void setImages();

    /**

     Makes the specified cube glow.
     @param idToGlow the ID of the cube to be made to glow
     */
    void glowHint(int idToGlow);

    /**

     Updates the dynamic score.
     @param score the new score
     */
    void updateDynamicScore(int score);

    /**

     Starts the countdown timer.
     @param mode the mode type of the game
     */
    void countDown(ModeType mode);
}
