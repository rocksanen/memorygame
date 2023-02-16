package controller;

import model.ModeType;

import java.util.ArrayList;

public interface IControllerScoreToV {

    void getWorldScore(ArrayList<String> worldScoreList);

    void fetchScores(ModeType difficulty);

    ArrayList<String> getScores(ModeType difficulty);

    ArrayList<String> getPersonalScores();

    int getTotalScore();

    int getNextScore();
}
