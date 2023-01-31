package database.dao;

import database.datasource.SqlJpaConn;
import database.entity.Account;
import database.entity.Leaderboard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class AccountDAO implements IAccountDAO {

    @Override
    public Long saveAccount(Account account) {
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        return account.getAccountid();

    }


    /**
     * Hakee simulaattorin tietokannasta sen id:n perustella.
     * @param id
     * @return Simulaattori-olio
     */
    @Override
    public Account getAccount(Long id) {
        EntityManager em = SqlJpaConn.getInstance();
        Account a = em.find(Account.class, id);
        return a;
    }

    @Override
    public Account getAccountByName(String name) {
        Account a = null;
        EntityManager em = SqlJpaConn.getInstance();
        try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :name");
            query.setParameter("name", name);
            a = (Account) query.getSingleResult();
            return a;
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }

    @Override
    public ArrayList<Account> getAllAccounts() {
        EntityManager em = SqlJpaConn.getInstance();
        String jpqlQuery = "SELECT s FROM Account s";
        Query q = em.createQuery(jpqlQuery);
        ArrayList<Account> resultList = (ArrayList<Account>) q.getResultList();
        return resultList;
    }

    @Override
    public boolean deleteAccount(Long id) {
        EntityManager em = SqlJpaConn.getInstance();
        Account acc = em.find(Account.class, id);
        if (id != null) {
            ILeaderboardDAO leaderboarddao = new LeaderboardDAO();
            leaderboarddao.deleteAllScores(id);
            em.getTransaction().begin();

            em.remove(acc);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().commit();
        return false;
    }
}

