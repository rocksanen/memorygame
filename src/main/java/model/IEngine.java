package model;

import java.util.ArrayList;

/**

 The IEngine interface defines the methods that should be implemented

 by the engine of the game to provide the functionality of the game.
 */
public interface IEngine {

    /**

     Randomly shuffles the memory objects.
     */
    void suffleObjects();

    /**

     Compares the two memory objects to check if they match.
     @param objectList The list of memory objects to be compared.
     */
    void compareObjects(ArrayList<MemoryObject> objectList);

    /**

     Sets the memory objects of the game.
     */
    void setMemoryObjects();

    /**

     Adds new memory objects to the memory object list.
     @param amount The number of memory objects to add.
     */
    void addMemoryObjectsToList(Integer amount);

    /**

     Adds the ID of the memory object to the list of objects being compared.
     @param i The ID of the memory object.
     */
    void addToComparing(int i);

    /**

     Clears the two memory objects being compared.
     @param memoryList The list of memory objects.
     */
    void clearPair(ArrayList<MemoryObject> memoryList);

    /**

     Sets the personal score of the player.
     */
    void setPersonalScore();

    /**

     Returns the total score of the player.
     @return The total score of the player.
     */
    int getTotalScore();

    /**

     Clears the storage of the game.
     */
    void clearStorage();

    /**

     Ends the game.
     */
    void endGame();

    /**

     Stops the timer of the game.
     */
    void stopTimer();

    /**

     Returns the hint for the game.
     @return The hint for the game.
     */
    int getHint();

    /**

     Returns the type of game mode.
     @return The type of game mode.
     */
    ModeType getType();

    /**

     Updates the dynamic score of the player.
     @param score The score to be added to the dynamic score.
     */
    void updateDynamicScore(int score);

    /**

     Starts the timer of the game.
     */
    void startTime();
}
