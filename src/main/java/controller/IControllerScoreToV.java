package controller;

import model.ModeType;

import java.util.ArrayList;

public interface IControllerScoreToV {


    void fetchScores(ModeType difficulty);

    ArrayList<String> getScores(ModeType difficulty);

    void fetchPersonalScores();

    ArrayList<String> getPersonalScores(ModeType difficulty);

    int getTotalScore();

    int getNextScore();
}
