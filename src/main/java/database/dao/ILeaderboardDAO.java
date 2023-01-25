package database.dao;

import database.entity.Account;
import database.entity.Leaderboard;

import java.util.ArrayList;
import java.util.List;

public interface ILeaderboardDAO {
    void saveScores(Leaderboard lb);

    List<Leaderboard> getAccountScores(long accountid);

    List<Integer> readWorldScores();
}
