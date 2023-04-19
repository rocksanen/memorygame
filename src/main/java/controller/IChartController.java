package controller;

import model.ModeType;

import java.util.ArrayList;
import java.util.Date;

public interface IChartController {
    void fetchScores(ModeType difficulty);

    int getTotalScore();

    ArrayList<String> getScores(ModeType difficulty);
    void fetchUserScores();
    ArrayList<Number> getUserScores(ModeType difficulty);

    ArrayList<Number> getUserTime(ModeType difficulty);

    //ArrayList<Date> getUserTimestamp(ModeType difficulty);

    void logout();

    boolean isLoggedIn();
}
