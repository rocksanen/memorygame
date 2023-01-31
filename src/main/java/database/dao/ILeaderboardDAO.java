package database.dao;

import database.entity.Leaderboard;

import java.util.ArrayList;

public interface ILeaderboardDAO {
    boolean saveScore(Leaderboard lb);

    ArrayList<Leaderboard> getAccountScores(Long accountid);

    ArrayList<Leaderboard> readWorldScores(String difficulty);

    ArrayList<Leaderboard> getAccountScoresByDifficulty(Long accountid, String difficulty);

    // does work
    ArrayList<Leaderboard> readWorldScoresByDifficulty(int difficulty);

    boolean deleteScore(Long scoreid);

    boolean deleteAllScores(Long accountid);
}
