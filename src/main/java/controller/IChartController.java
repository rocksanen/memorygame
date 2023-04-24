package controller;

import model.ModeType;

import java.util.ArrayList;

public interface IChartController {
    //void fetchScores(ModeType difficulty);

    //int getTotalScore();

    ArrayList<String> getWorldScores(ModeType difficulty);
    //void fetchUserScores();
    ArrayList<String> getUserScores(ModeType difficulty);

    ArrayList<Number> getUserTime(ModeType difficulty);

    //ArrayList<Date> getUserTimestamp(ModeType difficulty);

    //void logout();

    boolean isLoggedIn();
}
