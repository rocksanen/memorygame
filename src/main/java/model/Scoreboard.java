package model;

import database.dao.ILeaderboardDAO;
import database.dao.LeaderboardDAO;
import database.entity.Leaderboard;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class Scoreboard {
    private static ILeaderboardDAO leaderboarddao;
    private static ArrayList<Leaderboard> worldscores;

    private static Scoreboard instance;

    private Scoreboard() {
        this.worldscores = refreshWorldScores();
        this.leaderboarddao = new LeaderboardDAO();
    }

    // singleton should help with unnessecary db reads
    public static Scoreboard getInstance() {
        if (instance == null) {
            instance = new Scoreboard();
        }
        return instance;
    }

    public static ArrayList<Leaderboard> getWorldscores() {
       if (worldscores == null) {
           return refreshWorldScores();
       }
        return worldscores;
    }

    public static ArrayList<Leaderboard> refreshWorldScores() {
        try {
            worldscores = leaderboarddao.readWorldScores();

        } catch (Exception e) {
            System.out.println(e);
        }
        return worldscores;
    }
}
