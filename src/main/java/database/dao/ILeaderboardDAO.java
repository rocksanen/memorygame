package database.dao;

import database.entity.Leaderboard;

import java.util.ArrayList;

public interface ILeaderboardDAO {
    boolean saveScores(Leaderboard lb);

    ArrayList<Leaderboard> getAccountScores(Long accountid);
    ArrayList<Leaderboard> readWorldScores();
}
