package controller;

import model.ModeType;

import java.util.ArrayList;

public interface IChartController {
    void fetchScores(ModeType difficulty);

    int getTotalScore();

    ArrayList<String> getScores(ModeType difficulty);
    void fetchUserScores();
    ArrayList<Number> getUserScores(ModeType difficulty);

    ArrayList<Number> getUserTime(ModeType difficulty);

    void logout();

    boolean isLoggedIn();
}
