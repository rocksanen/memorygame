package controller;

import model.*;
import visuals.IChartGUI;

import java.util.ArrayList;

import static model.ModeType.*;

public class ChartController implements IChartController{
    private final IChartGUI chartUi;
    private IEngine engine;
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
    public void test(){
        System.out.println("This is a test!");
    }




    @Override
    public ArrayList<Number> getUserScores(ModeType difficulty) {


        switch (difficulty) {
            case EASY -> User.getInstance().getScores(EASY);
            case MEDIUM -> User.getInstance().getScores(MEDIUM);
            case HARD -> User.getInstance().getScores(HARD);
            default -> {
                return null;
            }
        }

        ArrayList<Number> scoreList = new ArrayList<>();

        Scoreboard scoreboard = User.getInstance().getScores(difficulty);
        if (scoreboard != null) {
            for (Score s : scoreboard.getScores()) {
                scoreList.add(s.getPoints());
            }
        } else {
            System.out.println("Error: Unable to fetch scores from database");
        }
        System.out.println("Score List:  " +scoreList);
        return scoreList;
    }
}
