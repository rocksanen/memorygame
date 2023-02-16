package model;

import database.dao.AccountDAO;
import database.dao.IAccountDAO;
import database.dao.LeaderboardDAO;
import database.entity.Account;

import java.util.ArrayList;


/**
 * Singleton class for the User
 * Contains the username, id, and personal scores of the player
 * Also contains the DAO classes for database connection
 *
 * @author Eetu Soronen
 * @version 1
 */
public class User {

    /**
     * Singleton instance of the User class
     */
    private static User instance;
    /**
     * Username of the player
     */
    private String username;
    /**
     * password of the player
     */
    private String password;
    /**
     * Id of the player, retrieved from the database.
     */
    private Long userId;
    /**
     * Personal scores of the player
     */
    private Scoreboard easyScores;

    /**
     * Personal scores of the player
     */
    private Scoreboard mediumScores;

    /**
     * Personal scores of the player
     */
    private Scoreboard hardScores;

    /**
     * Personal scores of the player
     */
    private Scoreboard personalScores;

    /**
     * DAO class for database connection
     */
    private IAccountDAO accountdao;

    /**
     * Private constructor for the User class
     * Initializes the username, id and personal scores
     * Also initializes the DAO classes
     */
    private User() {
        this.username = "tony the tiger";
        this.userId = null;
        this.easyScores = null;

        this.accountdao = new AccountDAO();
    }

    /**
     * getInstance method for the User class.
     * Returns the singleton instance of the User class.
     * If this does not exist creates it.
     *
     * @return - see {@link #instance}
     */
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }


    /**
     * Searches username from database and updates the instance variables
     *
     * @param username - see {@link #username}
     * @param password - see {@link #password}
     * @return true or false depending success of the login
     */
    public boolean login(String username, String password) {
        try {
            Account account = accountdao.getAccountByNameAndPassword(username, password);
            System.out.println("sTRINGIFYING ACCOUNT: " + account.toString() + "");

            if (account.getAccountid() != null) {
                this.userId = account.getAccountid();
                this.username = account.getUsername();
                this.easyScores = new Scoreboard();
                this.mediumScores = new Scoreboard();
                this.hardScores = new Scoreboard();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Username not found!" + e);
            ;
        }
        return false;
    }


    /**
     * Searches username from db, creates it if it does not exist
     * and updates the instance variables
     *
     * @param username see {@link #username}
     * @param password see {@link #password}
     * @return true or false depending success of the signup
     */
    public boolean signup(String username, String password) {
        // save account
        accountdao.saveAccount(new Account(username, password));
        Account account = accountdao.getAccountByName(username);

        this.userId = account.getAccountid();
        this.username = account.getUsername();
        this.easyScores = new Scoreboard();
        this.mediumScores = new Scoreboard();
        this.hardScores = new Scoreboard();
        return true;
    }

    /**
     * Getter for the username
     *
     * @return - see {@link #username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Logs out the user and resets the instance variables
     *
     * @return true
     */
    public boolean logout() {
        this.username = "tony the tiger";
        this.userId = null;
        this.easyScores = null;
        return true;
    }

    public boolean isLoggedIn() {
        return userId != null;
    }

    /**
     * Getter for the personal scores.
     *
     * @return - see {@link #easyScores}
     */
    public Scoreboard getScores(ModeType difficulty) {

        switch (difficulty) {
            case EASY:
                return easyScores;
            case MEDIUM:
                return mediumScores;
            case HARD:
                return hardScores;
            default:
                return personalScores;
        }
    }

    /**
     * fetcher for the personal scores from server.
     *
     * @return - see {@link #easyScores}
     */
    public ArrayList<Score> fetchScores(ModeType difficulty) {

        switch (difficulty) {
            case EASY:
                return easyScores.fetchUserScores(userId, difficulty);
            case MEDIUM:
                return mediumScores.fetchUserScores(userId, difficulty);
            case HARD:
                return hardScores.fetchUserScores(userId, difficulty);
            default:
                return null;
        }
    }


    /**
     * Adds a score to the personal scores
     *
     * @param time       - see {@link Scoreboard#addScore(Double, int, ModeType, String)}
     * @param difficulty - see {@link Scoreboard#addScore(Double, int, ModeType, String)}
     */
    public void addScore(Double time, int points, ModeType difficulty) {
        easyScores.addScore(time, points, difficulty, username);
    }

    /**
     * Deletes the account from the database
     *
     * @return true or false depending success of the deletion
     */
    public boolean deleteAccount() {
        if (userId == null) return false;
        try {
            boolean deleted = accountdao.deleteAccount(instance.userId);
            if (deleted == true) {
                return logout();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Getter for the userId
     *
     * @return - see {@link #userId}
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * To string method for the User class
     *
     * @return String represenation of the class
     */
    @Override
    public String toString() {
        return "niet";
    }
}
