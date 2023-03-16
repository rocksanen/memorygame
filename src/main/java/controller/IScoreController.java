package controller;

import model.ModeType;
import model.Score;

import java.util.ArrayList;

public interface IScoreController {


    void fetchScores(ModeType difficulty);

    ArrayList<String> getScores(ModeType difficulty);

    ArrayList<Score> getScoresRaw(ModeType difficulty);

    String formatScore(Score score);

    String formatScoreVerbose(Score score);

    void fetchPersonalScores();

    ArrayList<String> getPersonalScores(ModeType difficulty);

    ArrayList<Score> getUserScoresRaw(ModeType difficulty);

    int getTotalScore();

    int getNextScore();
}
