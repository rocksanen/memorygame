package database.dao;

import database.datasource.SqlJpaConn;
import database.entity.Leaderboard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class LeaderboardDAO implements ILeaderboardDAO {

    // make this a singleton
    private static LeaderboardDAO instance = null;
    private LeaderboardDAO() {}
    public static LeaderboardDAO getInstance() {
        if (instance == null) {
            instance = new LeaderboardDAO();
        }
        return instance;
    }


    @Override
    public boolean saveScore(Leaderboard lb) {
        // check if account exists
        AccountDAO accountDAO = AccountDAO.getInstance();
        if (accountDAO.getAccount(lb.getAccountid().getAccountid()) == null) {
            return false;
        }


        System.out.println("saveScores " + lb);
        try {
            EntityManager em = SqlJpaConn.getInstance();
            em.getTransaction().begin();
            em.persist(lb);
            em.getTransaction().commit();
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("error saving a score to db.." + e);
        }
        return false;
    }


    // does work
    @Override
    public ArrayList<Leaderboard> getAccountScores(Long accountid) {
        System.out.println("getAccountScores " + accountid);
        EntityManager em = SqlJpaConn.getInstance();
        // why is accountid typed twice? ¯\_(ツ)_/¯
        Query query = em.createQuery("SELECT l FROM Leaderboard l WHERE l.accountid.accountid = :accountid ORDER BY time DESC limit 100");
        query.setParameter("accountid", accountid);
        ArrayList<Leaderboard> scores = (ArrayList<Leaderboard>) query.getResultList();
        return scores;
    }

    @Override
    public ArrayList<Leaderboard> getAccountScoresByDifficulty(Long accountid, String difficulty) {
        System.out.println("getAccountScores " + accountid);
        EntityManager em = SqlJpaConn.getInstance();
        // why is accountid typed twice? ¯\_(ツ)_/¯
        Query query = em.createQuery("SELECT l FROM Leaderboard l WHERE l.accountid.accountid = :accountid AND l.difficulty = :difficulty ORDER BY time DESC limit 100");
        query.setParameter("accountid", accountid);
        query.setParameter("difficulty", difficulty);
        ArrayList<Leaderboard> scores = (ArrayList<Leaderboard>) query.getResultList();
        return scores;
    }

    /**
     * selects top 100 scores
     * @return
     */
    @Override
    public ArrayList<Leaderboard> readWorldScores(String difficulty) {
        System.out.println("readWorldScores");
        EntityManager em = SqlJpaConn.getInstance();
        // why is accountid typed twice? ¯\_(ツ)_/¯
        Query query = em.createQuery("SELECT l FROM Leaderboard l WHERE l.difficulty = :difficulty ORDER BY time DESC limit 100");
        query.setParameter("difficulty", difficulty);
        ArrayList<Leaderboard> scores = (ArrayList<Leaderboard>) query.getResultList();
        return scores;
    }

    // does work
    @Override
    public ArrayList<Leaderboard> readWorldScoresByDifficulty(int difficulty) {
        EntityManager em = SqlJpaConn.getInstance();
        // why is accountid typed twice? ¯\_(ツ)_/¯
        Query query = em.createQuery("SELECT l FROM Leaderboard l WHERE l.difficulty = :difficulty ORDER BY time DESC limit 100");
        query.setParameter("difficulty", difficulty);
        ArrayList<Leaderboard> scores = (ArrayList<Leaderboard>) query.getResultList();
        return scores;
    }
    @Override
    public boolean deleteScore(Long scoreid) {
        System.out.println("deleteScore " + scoreid);
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        Leaderboard score = em.find(Leaderboard.class, scoreid);
        if (score != null) {
            em.remove(score);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().commit();
        return false;
    }

    @Override
    public boolean deleteAllScores(Long accountid) {
        System.out.println("deleteAllScores " + accountid);
        try {
            EntityManager em = SqlJpaConn.getInstance();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Leaderboard l WHERE l.accountid.accountid = :accountid");
            query.setParameter("accountid", accountid);
            query.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("error deleting all scores from db.." + e);
            return false;
        }
    }
}
