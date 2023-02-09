package controller;

import javafx.application.Platform;
import model.*;
import visuals.IGui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static model.ModeType.*;

public class Controller implements IControllerVtoE, IControllerEtoV, IControllerScoreToV {

    private final IGui ui;
    private IEngine engine;
    private Scoreboard scoreboard;
    private Scoreboard easyScores;
    private Scoreboard mediumScores;
    private Scoreboard hardScores;

    public Controller(IGui ui) {

        this.ui = ui;
    }

    @Override
    public void startEasyGame() {

        this.engine = new Engine(EASY, this);
        this.engine.setMemoryObjects();
    }

    @Override
    public void startMediumGame() {

        this.engine = new Engine(ModeType.MEDIUM,this);
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
    public void clearPair(ArrayList<Integer> storage){

        Platform.runLater(() ->ui.clearPair(storage));

    }

    @Override
    public void setEasyGame(ArrayList<MemoryObject> memoryObjects) {

        Platform.runLater(() -> {
            try {
                ui.setEasyGame(memoryObjects);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void setMediumGame(ArrayList<MemoryObject> memoryObjects) {

        Platform.runLater(() -> {
            try {
                ui.setMediumGame(memoryObjects);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public void setPersonalScore(ArrayList<String> personalScoreList) {

        Platform.runLater(() -> ui.setPersonalScores(personalScoreList));

    }

    @Override
    public void getWorldScore(ArrayList<String> worldScoreList) {

        Platform.runLater(() -> ui.getWorldScore(worldScoreList));

    }

    /**
     * fetches scores form db, and stores them in the correct scoreboard
     *
     * @param difficulty the difficulty to fetch scores for
     */
    public void fetchScores(ModeType difficulty) {
        Scoreboard scores;
        switch (difficulty) {
            case EASY:
                easyScores.fetchWorldScores(EASY);
                break;
            case MEDIUM:
                mediumScores.fetchWorldScores(MEDIUM);
                break;
            case HARD:
                hardScores.fetchWorldScores(HARD);
                break;
            default:
        }
    }

    /**
     * returns the scores for the given difficulty formatted for gui
     *
     * @param difficulty the difficulty to get scores for
     * @return
     */
    public ArrayList<String> getScores(ModeType difficulty) {
        Scoreboard scores;
        switch (difficulty) {
            case EASY:
                scores = easyScores;
                break;
            case MEDIUM:
                scores = mediumScores;
                break;
            case HARD:
                scores = hardScores;
                break;
            default:
                return null;
        }
        ArrayList<String> scoreList = new ArrayList<>();

        scores.getScores();
        for (Score s : scores.getScores()) {
            scoreList.add(s.getUsername() + " " + s.getPoints());
        }
        return scoreList;
    }

    /**
     * returns the total score
     *
     * @return the total score
     */
    public int getTotalScore() {
        return engine.getTotalScore();
    }

    /**
     * returns the score for the next correct guess
     *
     * @return the score for the next correct guess
     */
    public int getNextScore() {
        return engine.getNextScore();
    }
}
