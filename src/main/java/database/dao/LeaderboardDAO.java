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


    // does work
    @Override
    public List<Leaderboard> getAccountScores(long accountid) {
        EntityManager em = SqlJpaConn.getInstance();
        // why is accountid typed twice? ¯\_(ツ)_/¯
        Query query = em.createQuery("SELECT l FROM Leaderboard l WHERE l.accountid.accountid = :accountid");
        query.setParameter("accountid", accountid);
        List<Leaderboard> scores = query.getResultList();
        return scores;
    }



    @Override
    public ArrayList<Integer> readWorldScores() {
        return null;
    }
}
