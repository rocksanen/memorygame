package database.dao;

import database.entity.Leaderboard;

import java.util.ArrayList;
import java.util.List;

public interface ILeaderboardDAO {
    void saveScores(Leaderboard lb);

    List<Leaderboard> getAccountScores(long userid);

    List<Integer> readWorldScores();
}
