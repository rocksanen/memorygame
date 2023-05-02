package controller;

import model.*;

import java.util.ArrayList;

import static model.ModeType.*;

/**
 * ScoreController handles communication between the Scoreboards and the GUI
 */
public class ScoreController implements IScoreController {

    /**
     * Constructor for ScoreController
     */
    public ScoreController() {
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
            case EASY -> ws.getEasyScores().fetchWorldScores(EASY);
            case MEDIUM -> ws.getMediumScores().fetchWorldScores(MEDIUM);
            case HARD -> ws.getHardScores().fetchWorldScores(HARD);
            default -> {
            }
        }
    }


    /**
     * returns the scores for the given difficulty formatted for gui
     *
     * @param difficulty the difficulty to get scores for
     * @return
     */
    @Override
    public ArrayList<String> getTopFiveScores(ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        return switch (difficulty) {
            case EASY -> formatScores(ws.getEasyScores().getScores());
            case MEDIUM -> formatScores(ws.getMediumScores().getScores());
            case HARD -> formatScores(ws.getHardScores().getScores());
            default -> null;
        };
    }

    /**
     * get raw Score-objects. use with temperance
     *
     * @param difficulty the difficulty to get scores for
     * @return the scores
     */
    @Override
    public ArrayList<Score> getScoresRaw(ModeType difficulty) {

        WorldScores ws = WorldScores.getInstance();
        return switch (difficulty) {
            case EASY -> ws.getEasyScores().getScores();
            case MEDIUM -> ws.getMediumScores().getScores();
            case HARD -> ws.getHardScores().getScores();
            default -> null;
        };
    }

    /**
     * converts score-object to a string that will be displayed in GUI.
     *
     * @param score the score to format
     * @return the formatted score
     */
    @Override
    public String formatScore(Score score) {
        return String.format("%-3.3s %4d", score.getUsername(), score.getPoints()).toUpperCase();
    }

    /**
     * converts score-object to a string that will be displayed in GUI.
     *
     * @param score the score to format
     * @return the formatted score
     */
    @Override
    public String formatScoreVerbose(Score score) {
        return String.format("%-10.10s %4d", score.getUsername(), score.getPoints()).toUpperCase();
    }

    /**
     * Scores formatted for in game view. Ex: '2. EET 3000'
     * @param scores the scores to format
     * @return the formatted scores
     */
    @Override
    public ArrayList<String> formatScores(ArrayList<Score> scores) {
        ArrayList<String> scoreList = new ArrayList<>();
        // make sure the list is exactly 5 long
        for (int i = 0; i < 5; i++) {
            if (i < scores.size()) {
                scoreList.add(i + 1 + ". " + formatScore(scores.get(i)));
            } else {
                scoreList.add(" ");
            }
        }
        return scoreList;
    }


    /**
     * fetches the personal scores for the logged in user
     * and stores them in the correct scoreboard
     */
    @Override
    public void fetchPersonalScores() {
        User.getInstance().isLoggedIn();
        if (!User.getInstance().isLoggedIn()) {
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
    public ArrayList<String> getTopFivePersonalScores(ModeType difficulty) {
        User u = User.getInstance();
        if (!u.isLoggedIn()) {
            // return empty array
            return formatScores(new ArrayList<Score>());
        }
        return switch (difficulty) {
            case EASY -> formatScores(u.getScores(EASY).getScores());
            case MEDIUM -> formatScores(u.getScores(MEDIUM).getScores());
            case HARD -> formatScores(u.getScores(HARD).getScores());
            default -> null;
        };
    }


    @Override
    public ArrayList<Score> getUserScoresRaw(ModeType difficulty) {
        User u = User.getInstance();
        if (!u.isLoggedIn()) {
            return null;
        }
        return switch (difficulty) {
            case EASY -> u.getScores(EASY).getScores();
            case MEDIUM -> u.getScores(MEDIUM).getScores();
            case HARD -> u.getScores(HARD).getScores();
            default -> null;
        };
    }
}
