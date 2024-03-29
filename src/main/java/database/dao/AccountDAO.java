package database.dao;

import database.datasource.SqlJpaConn;
import database.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.Locksmith;

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
        if (SqlJpaConn.getInstance() == null) {
            return false;
        }

        // check if account by that name exists
        if (getAccountByName(account.getUsername()) != null) {
            return false;
        }
        account.setUsername(account.getUsername().toLowerCase());
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(account);

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
        if (SqlJpaConn.getInstance() == null) {
            return null;
        }
        EntityManager em = SqlJpaConn.getInstance();
        return em.find(Account.class, id);
    }

    /**
     * finds an account by name and password
     *
     * @param username account name
     * @param password account password
     * @return Account-object
     */
    @Override
    public Account getAccountByNameAndPassword(String username, String password) {
        if (SqlJpaConn.getInstance() == null) {
            return null;
        }
        username = username.toLowerCase();
        Account a;
        EntityManager em = SqlJpaConn.getInstance();
        try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            a = (Account) query.getSingleResult();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
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
        if (SqlJpaConn.getInstance() == null) {
            return null;
        }
        username = username.toLowerCase();
        Account a;
        EntityManager em = SqlJpaConn.getInstance();
        try {
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.username = :username");
            query.setParameter("username", username);
            a = (Account) query.getSingleResult();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
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
        if (SqlJpaConn.getInstance() == null) {
            return null;
        }
        EntityManager em = SqlJpaConn.getInstance();
        String jpqlQuery = "SELECT s FROM Account s";
        Query q = em.createQuery(jpqlQuery);
        return (ArrayList<Account>) q.getResultList();
    }

    /**
     * deletes an account by id
     *
     * @param id account id
     * @return true if successful, false if not
     */
    @Override
    public boolean deleteAccount(Long id) {
        if (SqlJpaConn.getInstance() == null) {
            return false;
        }
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
                e.printStackTrace();
                em.getTransaction().rollback();
                return false;
            }
        }
        return false;
    }

    /**
     * converts plaintext password to hashes
     * run this once and never again
     * <p>
     * checks if password is under certain length (40 chars)
     * and hashes the password if it is
     * hashes seem to be 45 chars long so this should work ¯\_(ツ)_/¯
     */
    // this was used to convert old passwords, has no use for anyone anymore
    @Override
    public void passwordHasher() {
        EntityManager em = SqlJpaConn.getInstance();
        if (em == null) {
            return;
        }
        em.getTransaction().begin();
        ArrayList<Account> accounts = getAllAccounts();
        for (Account a : accounts) {
            if (a.getPassword().length() < 40) {
                System.out.println("hashing " + a.getUsername());
                System.out.println("old password: " + a.getPassword());
                a.setPassword(Locksmith.hashPassword(a.getPassword()));
                System.out.println("new password: " + a.getPassword());
            }
            System.out.println("--------------------");
        }
        em.getTransaction().commit();
    }
}

