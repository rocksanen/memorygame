package controller;

import model.ModeType;

import java.util.ArrayList;

public interface IControllerScoreToV {

    void getWorldScore(ArrayList<String> worldScoreList);

    void fetchScores(ModeType difficulty);

    ArrayList<String> getScores(ModeType difficulty);

    ArrayList<String> getPersonalScores(ModeType difficulty);

    int getTotalScore();

    int getNextScore();
}
