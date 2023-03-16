package controller;

import model.*;
import visuals.gameModes.FXIGameController;
import visuals.menu.IMenu;

import java.util.ArrayList;

import static model.ModeType.*;

public class ScoreController implements IScoreController {

    private IEngine engine;


    public ScoreController() {

    }


    /**
     * ui? what for? 🦎
     */
    public ScoreController() {
        ui = null;
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

    @Override
    public ArrayList<Score> getScoresRaw(ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        switch (difficulty) {
            case EASY:
                return ws.getEasyScores().getScores();
            case MEDIUM:
                return ws.getMediumScores().getScores();
            case HARD:
                return ws.getHardScores().getScores();
            default:
                return null;
        }
    }

    /**
     * converts score-object to a string that will be displayed in GUI.
     *
     * @param score the score to format
     * @return the formatted score
     */
    @Override
    public String formatScore(Score score) {
        // change formatting as you wish
        String format = String.format("%-3.3s %4d", score.getUsername(), score.getPoints()).toUpperCase();
//        System.out.println(format);
        return format;
    }

    /**
     * converts score-object to a string that will be displayed in GUI.
     *
     * @param score the score to format
     * @return the formatted score
     */
    @Override
    public String formatScoreVerbose(Score score) {
        // change formatting as you wish
        String format = String.format("%-10.10s %4d", score.getUsername(), score.getPoints()).toUpperCase();
//        System.out.println(format);
        return format;
    }


    /**
     * fetches the personal scores for the logged in user
     * and stores them in the correct scoreboard
     */
    @Override
    public void fetchPersonalScores() {
        User.getInstance().isLoggedIn();
        if (User.getInstance().isLoggedIn() == false) {
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
     *
     * @param difficulty the difficulty to get scores for
     * @return the personal scores for the given difficulty formatted for gui
     */
    @Override
    public ArrayList<String> getPersonalScores(ModeType difficulty) {
        if (User.getInstance().isLoggedIn() == false) {
            System.out.println("not logged in!");
            return null;
        }
        User u = User.getInstance();
        ArrayList<String> scoreList = new ArrayList<>();
        switch (difficulty) {
            case EASY:
                for (Score s : u.getScores(EASY).getScores()) {
                    scoreList.add(formatScore(s));
                }
                break;
            case MEDIUM:
                for (Score s : u.getScores(EASY).getScores()) {
                    scoreList.add(formatScore(s));
                }
            case HARD:
                for (Score s : u.getScores(EASY).getScores()) {
                    scoreList.add(formatScore(s));
                }
            default:
                return null;
        }
//        System.out.println(scoreList);
        return scoreList;
    }


    @Override
    public ArrayList<Score> getUserScoresRaw(ModeType difficulty) {
        User u = User.getInstance();
        if (u.isLoggedIn() == false) {
            System.out.println("not logged in!");
            return null;
        }
        switch (difficulty) {
            case EASY:
                return u.getScores(EASY).getScores();
            case MEDIUM:
                return u.getScores(MEDIUM).getScores();
            case HARD:
                return u.getScores(HARD).getScores();
            default:
                return null;
        }
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
}
