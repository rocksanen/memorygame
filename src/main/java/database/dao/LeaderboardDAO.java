package database.dao;

import database.datasource.SqlJpaConn;
import database.entity.Account;
import database.entity.Leaderboard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardDAO implements ILeaderboardDAO {


    @Override
    public void saveScores(Leaderboard lb) {
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(lb);
        em.getTransaction().commit();
    }

    @Override
    public ArrayList<Integer> readUserScores(int id) {
        return null;
    }



    @Override
    public ArrayList<Integer> readWorldScores() {
        return null;
    }
}
