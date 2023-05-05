package controller;

import javafx.application.Platform;
import model.*;
import visuals.gameModes.FXIGameController;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * GameController handles communication between the Engine and the GUI
 */
public class GameController implements IGameController {

    /**
     * the GUI controller
     */
    private final FXIGameController fxiGameController;

    /**
     * the game engine
     */
    private IEngine engine;

    /**
     * Constructor for GameController
     *
     * @param fxiGameController the GUI controller
     */
    public GameController(FXIGameController fxiGameController) {
        this.fxiGameController = fxiGameController;
    }

    /**
     * starts the game
     *
     * @param type the difficulty of the game
     */
    @Override
    public void startGame(ModeType type) {
        this.engine = new Engine(type, this);
        this.engine.setMemoryObjects();
    }

    /**
     * sends the id of the clicked cube to the engine
     *
     * @param id the id of the clicked cube
     */
    @Override
    public void sendIdToEngine(int id) {
        engine.addToComparing(id);
    }

    /**

     Clears the storage of the engine
     */
    @Override
    public void clearStorage() {
        engine.clearStorage();
    }

    /**

     Stops the engine's timer
     */
    @Override
    public void killTimer() {
        engine.stopTimer();
    }

    /**

     Clears a pair of MemoryObjects from the game
     @param storage the storage containing the IDs of the cubes to clear
     */
    @Override
    public void clearPair(ArrayList<Integer> storage) {
        Platform.runLater(() -> fxiGameController.clearPair(storage));
    }

    /**

     Sets the MemoryObjects to the game board
     @param memoryObjects the list of MemoryObjects to set on the game board
     */
    @Override
    public void setGame(ArrayList<MemoryObject> memoryObjects) {

        Platform.runLater(() -> {
            fxiGameController.setCubesToGame(memoryObjects);
        });
    }

    /**
     * sends the game over signal to the GUI
     *
     * @param victory true if the player won, false if the player lost (timed out)
     */
    @Override
    public void gameOver(boolean victory) {
        Platform.runLater(() -> {
            try {
                fxiGameController.gameOver(victory);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**

     Sets the specified ID as active in the game controller.
     @param id the ID to set as active
     */
    @Override
    public void getActive(int id) {
        fxiGameController.setActiveID(id);
    }


    /**
     * Gets the timer time to update it
     * @param i - time
     */
    @Override
    public void setTimer(int i) {
        fxiGameController.getTime(i);
    }

    /**
     * Visualizes the hint from engine
     */
    @Override
    public void showHint() {
        fxiGameController.glowHint(engine.getHint());
    }


    /**

     Sends a success message to the controller indicating that two matching memory objects were found.
     Calls the controller's compareFoundMatch method.
     */
    @Override
    public void sendComparingSuccess() {
        fxiGameController.compareFoundMatch();
    }

    /**

     Returns the current score obtained by the player.
     @return the current score obtained by the player.
     */
    @Override
    public int getCurrentScore() {
        return engine.getTotalScore();
    }

    /**
     * returns the difficulty of the game
     *
     * @return the difficulty of the game
     */
    @Override
    public ModeType getDifficulty() {
        return engine.getType();
    }

    /**
     * returns the grade of the game
     *
     * @return the grade of the game
     */
    @Override
    public String getGrade() {
        return Grader.scoreGrader(engine.getTotalScore(), engine.getType());
    }

    /**

     Updates the dynamic score of the game.

     @param score the new dynamic score to be displayed
     */
    @Override
    public void updateDynamicScore(int score) {

        Platform.runLater(() -> fxiGameController.updateDynamicScore(score));
    }

    /**

     Starts the game timer.
     */
    @Override
    public void startTime() {

        engine.startTime();
    }

}