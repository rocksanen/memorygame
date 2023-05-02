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

    @Override
    public void clearStorage() {
        engine.clearStorage();
    }

    @Override
    public void killTimer() {
        engine.stopTimer();
    }

    @Override
    public void clearPair(ArrayList<Integer> storage) {
        Platform.runLater(() -> fxiGameController.clearPair(storage));
    }

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

    @Override
    public void getActive(int id) {
        fxiGameController.setActiveID(id);
    }


    @Override
    public void setTimer(int i) {
        fxiGameController.getTime(i);
    }

    @Override
    public void showHint() {
        fxiGameController.glowHint(engine.getHint());
    }


    @Override
    public void sendComparingSuccess() {
        fxiGameController.compareFoundMatch();
    }

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

    @Override
    public void updateDynamicScore(int score) {

        Platform.runLater(() -> fxiGameController.updateDynamicScore(score));
    }

}