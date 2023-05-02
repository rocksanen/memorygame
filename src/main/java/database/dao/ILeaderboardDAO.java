package database.dao;

import database.entity.Leaderboard;
import model.ModeType;

import java.util.ArrayList;

public interface ILeaderboardDAO {
    boolean saveScore(Leaderboard lb);

    ArrayList<Leaderboard> getAccountScores(Long accountid);

    ArrayList<Leaderboard> readWorldScores(ModeType difficulty);

    ArrayList<Leaderboard> getAccountScoresByDifficulty(Long accountid, ModeType difficulty);

    boolean deleteScore(Long scoreid);

    void deleteAllScores(Long accountid);
}
