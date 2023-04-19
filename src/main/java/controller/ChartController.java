package controller;

import model.*;
import visuals.stats.IChartGUI;

import java.util.ArrayList;
import java.util.Date;

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


    @Override
    public void fetchScores(ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        switch (difficulty) {
            case EASY:
                ws.getEasyScores().fetchWorldScores(EASY);
                break;
            case MEDIUM:
                ws.getMediumScores().fetchWorldScores(MEDIUM);
                break;
            case HARD:
                ws.getHardScores().fetchWorldScores(HARD);
                break;
            default:
        }
    }
    @Override
    public ArrayList<String> getScores(ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        ArrayList<String> scoreList = new ArrayList<>();
        switch (difficulty) {
            case EASY:
                for (Score s : ws.getEasyScores().getScores()) {
                    scoreList.add(s.getUsername() + " " + s.getPoints());
                }
                break;
            case MEDIUM:
                for (Score s : ws.getMediumScores().getScores()) {
                    scoreList.add(s.getUsername() + " " + s.getPoints());
                }
                break;
            case HARD:
                for (Score s : ws.getHardScores().getScores()) {
                    scoreList.add(s.getUsername() + " " + s.getPoints());
                }
                break;
            default:
                return null;
        }
//        System.out.println(scoreList);
        return scoreList;
    }


    @Override
    public void fetchUserScores() {
        System.out.println("Hello World1!");

        User.getInstance().fetchScores(EASY);
        User.getInstance().fetchScores(MEDIUM);
        User.getInstance().fetchScores(HARD);

        return;

    }


    @Override
    public int getTotalScore() {
        return engine.getTotalScore();
    }


    @Override
    public ArrayList<Number> getUserScores(ModeType difficulty) {
        if (this.isLoggedIn() == false) {
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

        ArrayList<Number> scoreList = new ArrayList<>();

        for (Score s : User.getInstance().getScores(difficulty).getScores()) {
            scoreList.add(s.getPoints());
        }
        return scoreList;
    }

    @Override
    public ArrayList<Number> getUserTime(ModeType difficulty) {
        if (this.isLoggedIn() == false) {
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
    public void logout() {
        User user = User.getInstance();
        user.logout();
    }

    @Override
    public boolean isLoggedIn() {
        User user = User.getInstance();
        return user.isLoggedIn();
    }

}
