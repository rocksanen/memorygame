package controller;

import model.ModeType;
import model.Score;

import java.util.ArrayList;

public interface IScoreController {


    void fetchScores(ModeType difficulty);

    ArrayList<String> getTopFiveScores(ModeType difficulty);

    ArrayList<Score> getScoresRaw(ModeType difficulty);

    String formatScore(Score score);

    String formatScoreVerbose(Score score);

    ArrayList<String> formatScores(ArrayList<Score> scores);

    void fetchPersonalScores();

    ArrayList<String> getTopFivePersonalScores(ModeType difficulty);

    ArrayList<Score> getUserScoresRaw(ModeType difficulty);

    int getTotalScore();

}
