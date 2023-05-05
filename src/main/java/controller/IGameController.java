package controller;


import model.MemoryObject;
import model.ModeType;

import java.util.ArrayList;

/**

 The IGameController interface represents the controller in the Memory game MVC architecture.

 It defines the methods that the game engine will use to communicate with the controller.
 */
public interface IGameController {

    /**

     Starts the game with the specified game mode.
     @param type the type of game mode to be played
     */
    void startGame(ModeType type);

    /**

     Sends the ID of the selected MemoryObject to the game engine for comparison.
     @param id the ID of the selected MemoryObject
     */
    void sendIdToEngine(int id);

    /**

     Clears the storage used to store selected MemoryObjects.
     */
    void clearStorage();

    /**

     Stops the game timer.
     */
    void killTimer();

    /**

     Clears the pair of selected MemoryObjects from the game board.
     @param storage an ArrayList of the IDs of the selected MemoryObjects
     */
    void clearPair(ArrayList<Integer> storage);

    /**

     Sets the game board with the specified MemoryObjects.
     @param memoryObjects an ArrayList of the MemoryObjects to be placed on the game board
     */
    void setGame(ArrayList<MemoryObject> memoryObjects);

    /**

     Ends the game with the specified result.
     @param victory true if the game was won, false if the game was lost
     */
    void gameOver(boolean victory);

    /**

     Sets the ID of the currently active MemoryObject.
     @param id the ID of the currently active MemoryObject
     */
    void getActive(int id);

    /**

     Sets the time left on the game timer.
     @param i the time left in seconds
     */
    void setTimer(int i);

    /**

     Displays a hint to the player.
     */
    void showHint();

    /**

     Sends a message to the game engine that a successful match has been made.
     */
    void sendComparingSuccess();

    /**

     Returns the current score of the game.
     @return the current score of the game
     */
    int getCurrentScore();

    /**

     Returns the difficulty of the game mode.
     @return the difficulty of the game mode
     */
    ModeType getDifficulty();

    /**

     Returns the grade of the player based on their current score.
     @return the grade of the player
     */
    String getGrade();

    /**

     Updates the dynamic score display in the game view.
     @param score the new score to be displayed
     */
    void updateDynamicScore(int score);

    /**

     Starts the game timer.
     */
    void startTime();
}