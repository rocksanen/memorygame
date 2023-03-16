package controller;

import javafx.application.Platform;
import model.*;
import visuals.gameModes.FXIGameController;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static model.ModeType.*;

public class GameController implements IGameController {

    private final FXIGameController fxiGameController;
    private IEngine engine;

    public GameController(FXIGameController fxiGameController) {
        this.fxiGameController = fxiGameController;
    }

    @Override
    public void startEasyGame() {
        this.engine = new Engine(EASY, this);
        this.engine.setMemoryObjects();
    }

    @Override
    public void startMediumGame() {
        this.engine = new Engine(ModeType.MEDIUM, this);
        this.engine.setMemoryObjects();
    }

    @Override
    public void startHardGame() {
        this.engine = new Engine(ModeType.HARD, this);
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
    public void setEasyGame(ArrayList<MemoryObject> memoryObjects) {

        Platform.runLater(() -> {
            try {
                fxiGameController.setEasyGame(memoryObjects);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void setMediumGame(ArrayList<MemoryObject> memoryObjects) {

        Platform.runLater(() -> {
            try {
                fxiGameController.setMediumGame(memoryObjects);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void setHardGame(ArrayList<MemoryObject> memoryObjects) {

        Platform.runLater(() -> {
            try {
                fxiGameController.setHardGame(memoryObjects);
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
    public void sendComparingSuccess() {
        fxiGameController.compareFoundMatch();
    }
}