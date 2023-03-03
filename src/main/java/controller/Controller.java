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
    public void clearPair(ArrayList<Integer> storage) {

        Platform.runLater(() -> ui.clearPair(storage));

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
    public void setHardGame(ArrayList<MemoryObject> memoryObjects) {

        Platform.runLater(() -> {
            try {
                ui.setHardGame(memoryObjects);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void gameOver() {
        Platform.runLater(() -> {
            try {
                ui.gameOver();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void getActive(int id) {
        ui.setActiveID(id);
    }

    @Override
    public void getTime() {

    }

    @Override
    public void setTimer(int i) {
        ui.getTime(i);
    }

    @Override
    public void getReturnSignal() {

    }

    /**
     * fetches scores form db, and stores them in the correct scoreboard
     *
     * @param difficulty the difficulty to fetch scores for
     */
    @Override
    public void fetchScores(ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        switch (difficulty) {
            case EASY:
                ws.getEasyScores().fetchWorldScores(EASY);
                break;
            case MEDIUM:
                ws.getMediumScores().fetchWorldScores(MEDIUM);
                break;
            case HARD:
                ws.getHardScores().fetchWorldScores(HARD);
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
    @Override
    public ArrayList<String> getScores(ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        ArrayList<String> scoreList = new ArrayList<>();
        switch (difficulty) {
            case EASY:
                for (Score s : ws.getEasyScores().getScores()) {
                    scoreList.add(formatScore(s));
                }
                break;
            case MEDIUM:
                for (Score s : ws.getMediumScores().getScores()) {
                    scoreList.add(formatScore(s));
                }
                break;
            case HARD:
                for (Score s : ws.getHardScores().getScores()) {
                    scoreList.add(formatScore(s));
                }
                break;
            default:
                return null;
        }
//        System.out.println(scoreList);
        return scoreList;
    }


    // change formatting as you wish
    /**
     * converts score-object to a string that will be displayed in GUI.
     * @param score the score to format
     * @return the formatted score
     */
    @Override
    public String formatScore(Score score) {
        String format = String.format("%-30s %-10d", score.getUsername(), score.getPoints());
        System.out.println(format);
        return format;
    }

    /**
     * fetches the personal scores for the logged in user
     * and stores them in the correct scoreboard
     */
    @Override
    public void fetchPersonalScores() {
        if (this.isLoggedIn() == false) {
            System.out.println("not logged in!");
            return;
        }

        User.getInstance().fetchScores(EASY);
        User.getInstance().fetchScores(MEDIUM);
        User.getInstance().fetchScores(HARD);

        return;
    }

    /**
     * returns the personal scores for the given difficulty formatted for gui
     * @param difficulty the difficulty to get scores for
     * @return the personal scores for the given difficulty formatted for gui
     */
    @Override
    public ArrayList<String> getPersonalScores(ModeType difficulty) {
        if (this.isLoggedIn() == false) {
            System.out.println("not logged in!");
            return null;
        }

        switch (difficulty) {
            case EASY:
                User.getInstance().getScores(EASY);
                break;
            case MEDIUM:
                User.getInstance().getScores(MEDIUM);
                break;
            case HARD:
                User.getInstance().getScores(HARD);
                break;
            default:
                return null;
        }

        ArrayList<String> scoreList = new ArrayList<>();

        for (Score s : User.getInstance().getScores(difficulty).getScores()) {
            scoreList.add(s.getUsername() + " " + s.getPoints());
        }
        System.out.println(scoreList);
        return scoreList;
    }


    /**
     * returns the total score
     *
     * @return the total score
     */
    @Override
    public int getTotalScore() {
        return engine.getTotalScore();
    }

    /**
     * returns the score for the next correct guess
     *
     * @return the score for the next correct guess
     */
    @Override
    public int getNextScore() {
        return engine.getNextScore();
    }

    /**
     * returns the score for the next wrong guess
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user was successfully logged in
     */
    @Override
    public boolean login(String username, String password) {
        User user = User.getInstance();
        return user.login(username, password);
    }

    /**
     * registers a new user
     * @param username the username of the new user
     * @param password the password of the new user
     * @return true if the user was successfully registered
     */
    @Override
    public boolean register(String username, String password) {
        User user = User.getInstance();
        return user.signup(username, password);
    }

    /**
     * logs out the user if logged in
     */
    @Override
    public void logout() {
        User user = User.getInstance();
        user.logout();
    }

    /**
     * returns true if the user is logged in
     * @return true if the user is logged in
     */
    @Override
    public boolean isLoggedIn() {
        User user = User.getInstance();
        return user.isLoggedIn();
    }

    /**
     * returns the username of the logged in user
     * @return the username of the logged in user
     */
    @Override
    public String getUsername() {
        User user = User.getInstance();
        return user.getUsername();
    }

    @Override
    public void sendComparingSuccess() {

        ui.compareFoundMatch();

    }
}
