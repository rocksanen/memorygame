package controller;

import model.ModeType;
import model.Score;

import java.util.ArrayList;

public interface IControllerScoreToV {


    void fetchScores(ModeType difficulty);

    ArrayList<String> getScores(ModeType difficulty);

    String formatScore(Score score);

    void fetchPersonalScores();

    ArrayList<String> getPersonalScores(ModeType difficulty);

    int getTotalScore();

    int getNextScore();
}
