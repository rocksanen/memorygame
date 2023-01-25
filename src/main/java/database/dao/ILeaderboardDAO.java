package database.dao;

import database.entity.Leaderboard;

import java.util.ArrayList;

public interface ILeaderboardDAO {
    void saveScores(Leaderboard lb);

    ArrayList<Integer> readUserScores(int id);

    ArrayList<Integer> readWorldScores();
}
