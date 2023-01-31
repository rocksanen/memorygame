package model;

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
 *
 */
public class Scoreboard {
    private static ILeaderboardDAO leaderboarddao = LeaderboardDAO.getInstance();
    private static IAccountDAO accountdao = AccountDAO.getInstance();

    private ArrayList<Score> scores;

    public Scoreboard() {
        this.scores = new ArrayList<>();
    }

    public Scoreboard(ArrayList<Leaderboard> leaderboards) {
        this.scores = new ArrayList<>();
        for (Leaderboard lb : leaderboards){
            this.scores.add(new Score(lb));
        }
    }

    public void addScore(Integer time, String difficulty, String username) {
        Account a = accountdao.getAccountByName(username);
        Leaderboard lb = new Leaderboard(a, time, difficulty, new Date());
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
     * Fetch global scores of select difficulty, sorted by time
     * @param difficulty
     */
    public void fetchScores(String difficulty) {
        this.scores = new ArrayList<>();
        List<Leaderboard> leaderboards = leaderboarddao.readWorldScores(difficulty);
        for (Leaderboard lb : leaderboards){
            this.scores.add(new Score(lb));
        }
    }

    /**
     * Fetch personal scores of select difficulty, sorted by time
     * @param userid
     * @param difficulty
     */
    public void fetchScores(Long userid, String difficulty) {
        this.scores = new ArrayList<>();
        List<Leaderboard> leaderboards = leaderboarddao.getAccountScoresByDifficulty(userid, difficulty);
        for (Leaderboard lb : leaderboards){
            this.scores.add(new Score(lb));
        }
    }

    @Override
    public String toString() {
        return "Scoreboard{" +
                "scores=" + scores +
                '}';
    }
}
