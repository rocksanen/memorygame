package model;

import controller.IControllerScoreToV;
import database.dao.AccountDAO;
import database.dao.IAccountDAO;
import database.dao.ILeaderboardDAO;
import database.dao.LeaderboardDAO;
import database.entity.Account;
import database.entity.Leaderboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Scoreboard class for the game
 * Contains list of Score-objects and methods for adding and retrieving scores
 * Also contains methods for retrieving scores from the database
 * @author Eetu Soronen
 * @version 1
 */
public class Scoreboard {
    private static ILeaderboardDAO leaderboarddao = new LeaderboardDAO();
    private static IAccountDAO accountdao = new AccountDAO();

    private IControllerScoreToV controller;

    private ArrayList<Score> scores;

    public Scoreboard() {
        this.scores = new ArrayList<>();
    }

    public Scoreboard(ArrayList<Leaderboard> leaderboards) {
        this.scores = new ArrayList<>();
        for (Leaderboard lb : leaderboards) {
            this.scores.add(new Score(lb));
        }
    }

    public Scoreboard(IControllerScoreToV controller) {

        this.controller = controller;
    }

    public void addScore(Double time, int points, ModeType difficulty, String username) {
        Account a = accountdao.getAccountByName(username);
        Leaderboard lb = new Leaderboard(a, time, points, difficulty, new Date());
        leaderboarddao.saveScore(lb);
        scores.add(new Score(lb));
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void deleteScore(Long scoreid) {
        leaderboarddao.deleteScore(scoreid);
        // find score with scoreid in scores
        for (Score s : scores) {
            if (s.getScoreid().equals(scoreid)) {
                scores.remove(s);
                break;
            }
        }
    }

    /**
     * Fetch global scores of select difficulty, sorted by points (desc) and then time (asc)
     *
     * @param difficulty
     */
    public void fetchWorldScores(ModeType difficulty) {
        this.scores = new ArrayList<>();
        List<Leaderboard> leaderboards = leaderboarddao.readWorldScores(difficulty);
        for (Leaderboard lb : leaderboards) {
            this.scores.add(new Score(lb));
        }


    }

    /**
     * Fetch personal scores of select difficulty, sorted by time
     *
     * @param userid
     * @param difficulty
     */
    protected void fetchScores(Long userid, ModeType difficulty) {
        this.scores = new ArrayList<>();
        List<Leaderboard> leaderboards = leaderboarddao.getAccountScoresByDifficulty(userid, difficulty);
        for (Leaderboard lb : leaderboards) {
            this.scores.add(new Score(lb));
        }
    }

    @Override
    public String toString() {
        return "Scoreboard{" + "scores=" + scores + '}';
    }
}
