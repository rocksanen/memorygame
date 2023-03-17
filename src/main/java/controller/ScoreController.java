package controller;

import model.*;

import java.util.ArrayList;

import static model.ModeType.*;

public class ScoreController implements IScoreController {

    private IEngine engine;


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
    public ArrayList<String> getTopFiveScores(ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        switch (difficulty) {
            case EASY:
                return formatScores(ws.getEasyScores().getScores());
            case MEDIUM:
                return formatScores(ws.getMediumScores().getScores());
            case HARD:
                return formatScores(ws.getHardScores().getScores());
            default:
                return null;
        }
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
        System.out.println(scoreList);
        return scoreList;
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
    public ArrayList<String> getTopFivePersonalScores(ModeType difficulty) {
        if (User.getInstance().isLoggedIn() == false) {
            System.out.println("not logged in!");
            // return empty array
            return formatScores(new ArrayList<Score>());
        }
        User u = User.getInstance();
        switch (difficulty) {
            case EASY:
                return formatScores(u.getScores(EASY).getScores());
            case MEDIUM:
                return formatScores(u.getScores(MEDIUM).getScores());
            case HARD:
                return formatScores(u.getScores(HARD).getScores());
            default:
                return null;
        }
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
