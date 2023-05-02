package model;

import database.dao.ILeaderboardDAO;
import database.dao.LeaderboardDAO;
import database.datasource.SqlJpaConn;
import database.entity.Account;
import database.entity.Leaderboard;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Scoreboard class for the game
 * Contains list of Score-objects and methods for adding and retrieving scores
 * Also contains methods for retrieving scores from the database
 *
 * @author Eetu Soronen
 * @version 1
 */
public class Scoreboard {

    /**
     * DAO class for database connection
     */
    private static final ILeaderboardDAO leaderboarddao = new LeaderboardDAO();

    /**
     * List of scores
     */
    private final ArrayList<Score> scores;

    /**
     * Constructor for Scoreboard
     */
    public Scoreboard() {
        this.scores = new ArrayList<>();
    }

    /**
     * Constructor for Scoreboard
     *
     * @param leaderboards list of Leaderboard-objects
     */
    public Scoreboard(ArrayList<Leaderboard> leaderboards) {
        this.scores = new ArrayList<>();
        for (Leaderboard lb : leaderboards) {
            this.scores.add(new Score(lb));
        }
    }

    /**
     * Adds a score to the scoreboard
     *
     * @param time       time in seconds
     * @param points     points
     * @param difficulty difficulty
     */
    public void addScore(Double time, int points, ModeType difficulty, boolean saveToRemote) {
        User u = User.getInstance();
        Account a = u.getAccount();
        if (a == null) {
            System.out.println("cant save score if not logged in!");
            return;
        }
        Leaderboard lb = new Leaderboard(a, time, points, difficulty, new java.sql.Date(new Date().getTime()));
        scores.add(new Score(lb));

        // sort scores by points (desc) and then time (asc)
        scores.sort((s1, s2) -> {
            if (s1.getPoints() == s2.getPoints()) {
                return s1.getTime().compareTo(s2.getTime());
            } else {
                return s2.getPoints() - s1.getPoints();
            }
        }); // 🤖

        if (saveToRemote) {
            // save score to database
            leaderboarddao.saveScore(lb);
        }
    }

    /**
     * Returns the list of scores
     *
     * @return list of scores
     */
    public ArrayList<Score> getScores() {
//        System.out.println("getScores: " + scores);
        return scores;
    }

    /**
     * Deletes a score from the scoreboard
     *
     * @param scoreid id of the score to be deleted
     */
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
     * @param difficulty difficulty of the scores
     */
    public void fetchWorldScores(ModeType difficulty) {

        this.scores.clear();

        List<Leaderboard> leaderboards = leaderboarddao.readWorldScores(difficulty);
        if (leaderboards == null) {
            return;
        }

        for (Leaderboard lb : leaderboards) {
            this.scores.add(new Score(lb));
        }
//        System.out.println("fetchWorldScores: " + scores);
//        System.out.println("get iside fetch");
//        this.getScores();
    }

    /**
     * Fetch personal scores of select difficulty, sorted by time
     *
     * @param userid     id of the user
     * @param difficulty difficulty level of the scores
     */
    public void fetchUserScores(Long userid, ModeType difficulty) {
        this.scores.clear();
        List<Leaderboard> leaderboards = leaderboarddao.getAccountScoresByDifficulty(userid, difficulty);
        if (leaderboards == null) {
            return;
        }
        for (Leaderboard lb : leaderboards) {
            this.scores.add(new Score(lb));
        }
//        System.out.println("fetchWorldScores: " + scores);
//        System.out.println("get iside fetch");
//        this.getScores();
    }

    /**
     * tostring method for Scoreboard
     *
     * @return string representation of the scoreboard
     */
    @Override
    public String toString() {
        return "Scoreboard{" + "scores=" + scores + '}';
    }
}
