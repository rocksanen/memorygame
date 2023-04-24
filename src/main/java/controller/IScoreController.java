package controller;

import model.ModeType;
import model.Score;

import java.util.ArrayList;

public interface IScoreController {
    /**
     * fetches scores form db, and stores them in the correct scoreboard
     *
     * @param difficulty the difficulty to fetch scores for
     */
    void fetchScores(ModeType difficulty);

    /**
     * returns the scores for the given difficulty formatted for gui
     *
     * @param difficulty the difficulty to get scores for
     * @return
     */
    ArrayList<String> getTopFiveScores(ModeType difficulty);

    /**
     * get raw Score-objects. use with temperance
     *
     * @param difficulty the difficulty to get scores for
     * @return the scores
     */
    ArrayList<Score> getScoresRaw(ModeType difficulty);

    /**
     * converts score-object to a string that will be displayed in GUI.
     *
     * @param score the score to format
     * @return the formatted score
     */
    String formatScore(Score score);

    /**
     * converts score-object to a string that will be displayed in GUI.
     *
     * @param score the score to format
     * @return the formatted score
     */
    String formatScoreVerbose(Score score);

    ArrayList<String> formatScores(ArrayList<Score> scores);

    /**
     * fetches the personal scores for the logged in user
     * and stores them in the correct scoreboard
     */
    void fetchPersonalScores();

    /**
     * returns the personal scores for the given difficulty formatted for gui
     *
     * @param difficulty the difficulty to get scores for
     * @return the personal scores for the given difficulty formatted for gui
     */
    ArrayList<String> getTopFivePersonalScores(ModeType difficulty);

    /**
     * get raw Score-objects. use with temperance
     *
     * @param difficulty the difficulty to get scores for
     * @return the scores
     */
    ArrayList<Score> getUserScoresRaw(ModeType difficulty);
}
