package controller;

import model.*;
import visuals.stats.IChartGUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static model.ModeType.*;

/**
 * The type Chart controller.
 */
public class ChartController implements IChartController{
    private final IChartGUI chartUi;
    private IEngine engine;

    /**
     * Instantiates a new Chart controller.
     *
     * @param chartUi the chart ui
     */
    public ChartController(IChartGUI chartUi) {
        this.chartUi = chartUi;
    }



    public ArrayList<String> dateSort(ArrayList<String> scoreList) {

        scoreList.sort((o1, o2) -> {

            DateFormat f;

            if (o1.split(" ")[1].length() == 10) {
                f = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            } else {
                f = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            }
            try {
                return f.parse(o1.split(" ")[1]).compareTo(f.parse(o2.split(" ")[1]));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        });
        return scoreList;
    }
    @Override
    public ArrayList<String> getWorldScores(ModeType difficulty) {


        ArrayList<String> scoreList = new ArrayList<>();
        ScoreController scoreController = new ScoreController();
        ArrayList<Score> rawScores = scoreController.getScoresRaw(difficulty);


        for(Score s : rawScores) {

            scoreList.add(s.getPoints() + " " + s.getTimestamp());
        }

        return dateSort(scoreList);
    }


    @Override
    public ArrayList<String> getUserScores(ModeType difficulty) {
        if (!this.isLoggedIn()) {
            System.out.println("not logged in!");
            return null;
        }

        ScoreController scoreController = new ScoreController();
        ArrayList<Score> rawScores = scoreController.getUserScoresRaw(difficulty);
        ArrayList<String> scoreList = new ArrayList<>();

        for(Score s : rawScores) {

            scoreList.add(s.getPoints() + " " + s.getTimestamp());
        }

        return dateSort(scoreList);
    }

    @Override
    public ArrayList<Number> getUserTime(ModeType difficulty) {
        if (!this.isLoggedIn()) {
            System.out.println("not logged in!");
            return null;
        }
        switch (difficulty) {
            case EASY -> User.getInstance().getScores(EASY);
            case MEDIUM -> User.getInstance().getScores(MEDIUM);
            case HARD -> User.getInstance().getScores(HARD);
            default -> {
                return null;
            }
        }

        ArrayList<Number> timeList = new ArrayList<>();

        for (Score s : User.getInstance().getScores(difficulty).getScores()) {
            timeList.add(s.getTime());
        }
        return timeList;
    }



//    @Override
//    public ArrayList<Date> getUserTimestamp(ModeType difficulty) {
//        return null;
//    }


    @Override
    public boolean isLoggedIn() {
        User user = User.getInstance();
        return user.isLoggedIn();
    }

}
