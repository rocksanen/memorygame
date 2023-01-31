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
        // check if account by that name exists
        if (getAccountByName(account.getUsername()) != null) {
            System.out.println("account already exists");
            return null;
        }
        account.setUsername(account.getUsername().toLowerCase());

        try {
            System.out.println("saveAccount " + account);
            EntityManager em = SqlJpaConn.getInstance();
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
            em.flush();
            return account.getAccountid();
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * Hakee simulaattorin tietokannasta sen id:n perustella.
     * @param id
     * @return Simulaattori-olio
     */
    @Override
    public Account getAccount(Long id) {
        System.out.println("getAccount " + id);
        EntityManager em = SqlJpaConn.getInstance();
        Account a = em.find(Account.class, id);
        return a;
    }

    @Override
    public Account getAccountByName(String username) {
        username = username.toLowerCase();
        System.out.println("getAccountByName " + username);
        Account a = null;
        EntityManager em = SqlJpaConn.getInstance();
        try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username");
            query.setParameter("username", username);
            a = (Account) query.getSingleResult();
            System.out.println(a.toString());
            return a;
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }

    @Override
    public ArrayList<Account> getAllAccounts() {
        System.out.println("getAllAccounts");
        EntityManager em = SqlJpaConn.getInstance();
        String jpqlQuery = "SELECT s FROM Account s";
        Query q = em.createQuery(jpqlQuery);
        ArrayList<Account> resultList = (ArrayList<Account>) q.getResultList();
        return resultList;
    }

    @Override
    public boolean deleteAccount(Long id) {
        System.out.println("deleteAccount " + id);
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

