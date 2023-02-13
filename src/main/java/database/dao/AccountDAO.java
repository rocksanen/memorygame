package database.dao;

import database.datasource.SqlJpaConn;
import database.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;


/**
 * AccountDAO class for the game
 * Contains methods for saving and retrieving accounts from the database
 *
 * @author Eetu Soronen
 * @version 1
 */
public class AccountDAO implements IAccountDAO {

    /**
     * Saves an account to the database
     *
     * @param account Account-object to be saved
     * @return true if successful, false if not
     */
    @Override
    public boolean saveAccount(Account account) {
        // check if account by that name exists
        if (getAccountByName(account.getUsername()) != null) {
            System.out.println("account already exists");
            return false;
        }
        account.setUsername(account.getUsername().toLowerCase());
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(account);

        System.out.println("saveAccount " + account);
        try {
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    /**
     * finds an account by id
     *
     * @param id account id
     * @return Account-object
     */
    @Override
    public Account getAccount(Long id) {
        System.out.println("getAccount " + id);
        EntityManager em = SqlJpaConn.getInstance();
        Account a = em.find(Account.class, id);
        return a;
    }


    /**
     * finds an account by name & password
     *
     * @param username account name
     * @param username account password
     * @return Account-object
     */
    @Override
    public Account getAccountByNameAndPassword(String username, String password) {
        username = username.toLowerCase();
        System.out.println("getAccountByName " + username);
        Account a = null;
        EntityManager em = SqlJpaConn.getInstance();
        try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            a = (Account) query.getSingleResult();
            System.out.println(a.toString());
            return a;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * finds an account by name
     *
     * @param username account name
     * @return Account-object
     */
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
        return null;
    }

    /**
     * finds all accounts
     *
     * @return ArrayList of Account-objects
     */
    @Override
    public ArrayList<Account> getAllAccounts() {
        System.out.println("getAllAccounts");
        EntityManager em = SqlJpaConn.getInstance();
        String jpqlQuery = "SELECT s FROM Account s";
        Query q = em.createQuery(jpqlQuery);
        ArrayList<Account> resultList = (ArrayList<Account>) q.getResultList();
        return resultList;
    }

    /**
     * deletes an account by id
     *
     * @param id account id
     * @return true if successful, false if not
     */
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
            try {
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                System.out.println(e);
                em.getTransaction().rollback();
                return false;
            }
        }
        return false;
    }
}

