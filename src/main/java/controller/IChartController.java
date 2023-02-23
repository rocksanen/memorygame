package controller;

import model.ModeType;

import java.util.ArrayList;

public interface IChartController {
    void fetchScores(ModeType difficulty);

    int getTotalScore();

    void test();

    ArrayList<String> getScores(ModeType difficulty);
    void fetchUserScores();
    ArrayList<Number> getUserScores(ModeType difficulty);
}
