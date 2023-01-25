package database.dao;

import database.datasource.SqlJpaConn;
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


    // does not work
    @Override
    public List<Leaderboard> getAccountScores(long id) {
        EntityManager em = SqlJpaConn.getInstance();

        Query query = em.createQuery("SELECT l FROM Leaderboard l WHERE l.score = :id");
        query.setParameter("id", id);
        List<Leaderboard> scores = query.getResultList();
        return scores;
    }



    @Override
    public ArrayList<Integer> readWorldScores() {
        return null;
    }
}
