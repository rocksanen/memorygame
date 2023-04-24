package controller;

import javafx.application.Platform;
import model.*;
import visuals.gameModes.FXIGameController;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameController implements IGameController {

    private final FXIGameController fxiGameController;
    private IEngine engine;

    public GameController(FXIGameController fxiGameController) {
        this.fxiGameController = fxiGameController;
    }

    /**
     * for unit tests only
     */
    private GameController() {
        this.fxiGameController = null;
    }

    @Override
    public void startGame(ModeType type) {
        this.engine = new Engine(type, this);
        this.engine.setMemoryObjects();
    }

    @Override
    public void sendIdToEngine(int id) {
        engine.addToComparing(id);
    }

    @Override
    public void clearStorage() {
        engine.clearStorage();
    }

    @Override
    public void sendReturnSignal() {
        engine.endGame();
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
            try {
                fxiGameController.setCubesToGame(memoryObjects);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void gameOver() {
        Platform.runLater(() -> {
            try {
                fxiGameController.gameOver();
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
    public void getTime() {

    }

    @Override
    public void setTimer(int i) {
        fxiGameController.getTime(i);
    }

    @Override
    public void getReturnSignal() {
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

    @Override
    public ModeType getDifficulty() {
        return engine.getType();
    }

    @Override
    public String getGrade() {
        return Grader.scoreGrader(engine.getTotalScore(), engine.getType());
    }

}