package database.dao;

import database.entity.Leaderboard;

import java.util.ArrayList;

public interface ILeaderboardDAO {
    boolean saveScore(Leaderboard lb);

    ArrayList<Leaderboard> getAccountScores(Long accountid);
    ArrayList<Leaderboard> readWorldScores();

    boolean deleteScore(Long scoreid);

    boolean deleteAllScores(Long accountid);
}
