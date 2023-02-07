package controller;

import javafx.application.Platform;
import model.*;
import visuals.IGui;

import java.util.ArrayList;

import static model.ModeType.*;

public class Controller implements IControllerVtoE, IControllerEtoV, IControllerScoreToV {

    private final IGui ui;
    private IEngine engine;

    private Scoreboard scoreboard;

    public Controller(IGui ui) {

        this.ui = ui;

    }

    @Override
    public void startEasyGame() {

        this.engine = new Engine(EASY, this);
        this.engine.setMemoryObjects();

    }

    @Override
    public void eB0(int i) {

        engine.addToComparing(i);

    }

    @Override
    public void eB1(int i) {

        engine.addToComparing(i);

    }

    @Override
    public void eB2(int i) {

        engine.addToComparing(i);

    }

    @Override
    public void eB3(int i) {

        engine.addToComparing(i);

    }

    @Override
    public void eB4(int i) {

        engine.addToComparing(i);

    }

    @Override
    public void eB5(int i) {

        engine.addToComparing(i);

    }

    @Override
    public void sendType(int id, int typeID) {

        ui.setTypeToLabel(id, typeID);

    }

    @Override
    public void clearPair(ArrayList<MemoryObject> memoryList) {

        Platform.runLater(() -> ui.clearPair(memoryList));

    }

    @Override
    public void setPersonalScore(ArrayList<String> personalScoreList) {

        Platform.runLater(() -> ui.setPersonalScores(personalScoreList));

    }

    @Override
    public void getWorldScore(ArrayList<String> worldScoreList) {

        Platform.runLater(() -> ui.getWorldScore(worldScoreList));

    }


    private Scoreboard easyScores;
    private Scoreboard mediumScores;
    private Scoreboard hardScores;

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
                return;
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
}
