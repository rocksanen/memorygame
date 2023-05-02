package model;

import database.dao.AccountDAO;
import database.dao.IAccountDAO;
import database.entity.Account;
import static model.ModeType.*;


/**
 * Singleton class for the User
 * Contains methods for retrieving and setting user data,
 * as well as personal scoreboards of the user.
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
     * Id of the player, retrieved from the database.
     */
    private Long userId;

    /**
     * Personal scores for easy difficulty
     */
    private Scoreboard easyScores;

    /**
     * Personal scores for medium difficulty
     */
    private Scoreboard mediumScores;

    /**
     * Personal scores for hard difficulty
     */
    private Scoreboard hardScores;


    /**
     * DAO class for database connection
     */
    private final IAccountDAO accountdao;

    /**
     * jpa entity for the account
     */
    private Account account;

    /**
     * Private constructor for the User class
     * Initializes the username, id and personal scores
     * Also initializes AccountDAO for database connection
     */
    private User() {
        this.username = "tony the tiger";
        this.userId = null;
        this.easyScores = null;
        this.mediumScores = null;
        this.hardScores = null;

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
     * password is hashed and the hash is compared to the one in the database
     *
     * @param username - see {@link #username}
     * @param password user password
     * @return true or false depending on success of the login
     */
    public boolean login(String username, String password) {
        if (username == null) {
            return false;
        }
        Locksmith l = new Locksmith();

        // try to encrypt the password
        String hashedPassword = null;
        try {
            hashedPassword = l.hashPassword(password);
            System.out.println("Encrypted password: " + hashedPassword);
        } catch (Exception e) {
            System.out.println("Error encrypting password: " + e);
            return false;
        }

        // try to decrypt the password
        try {
            boolean allgood = l.checkPassword(password, hashedPassword);
            System.out.println("Decrypted password: " + allgood);
        } catch (Exception e) {
            System.out.println("Error decrypting password: " + e);
            return false;
        }

        try {
            Account account = accountdao.getAccountByNameAndPassword(username, hashedPassword);
//            System.out.println("sTRINGIFYING ACCOUNT: " + account.toString() + "");
            if (account.getAccountid() != null) {
                this.account = account;
                this.userId = account.getAccountid();
                this.username = account.getUsername();
                this.easyScores = new Scoreboard();
                this.mediumScores = new Scoreboard();
                this.hardScores = new Scoreboard();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Username not found!" + e);
        }
        return false;
    }


    /**
     * Searches username from db, creates it if it does not exist
     * and updates the instance variables
     * also hashes the password before saving it
     * no salt since that's unhealthy
     *
     * @param username see {@link #username}
     * @param password user password
     * @return true or false depending on success of the signup
     */
    public boolean signup(String username, String password) {
        Locksmith l = new Locksmith();

        // validate the username, it should contain no whitespace characters,
        // be 30 chars long at max
        // and be at least 3 chars long
        if (username.contains(" ") || username.length() > 30 || username.length() < 3) {
            System.out.println("Username is invalid!");
            return false;
        }

        // try to encrypt the password
        String hashedPassword = null;
        try {
            hashedPassword = l.hashPassword(password);
            System.out.println("Encrypted password: " + hashedPassword);
        } catch (Exception e) {
            System.out.println("Error encrypting password: " + e);
            return false;
        }

        // try to decrypt the password
        try {
            boolean allgood = l.checkPassword(password, hashedPassword);
            System.out.println("Decrypted password: " + allgood);
        } catch (Exception e) {
            System.out.println("Error decrypting password: " + e);
            return false;
        }


        Account a = accountdao.getAccountByName(username);
        if (a != null) {
            System.out.println("Username already exists!");
            return false;
        }
        accountdao.saveAccount(new Account(username, hashedPassword));
        this.account = accountdao.getAccountByName(username);

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
        this.mediumScores = null;
        this.hardScores = null;
        return true;
    }

    /**
     * Checks if the user is logged in
     *
     * @return true if logged in, false if not
     */
    public boolean isLoggedIn() {
        return userId != null;
    }

    /**
     * Getter for the personal scores.
     *
     * @return - see {@link #easyScores}
     */
    public Scoreboard getScores(ModeType difficulty) {

        return switch (difficulty) {
            case EASY -> easyScores;
            case MEDIUM -> mediumScores;
            case HARD -> hardScores;
            default -> null;
        };
    }

    /**
     * fetcher for the personal scores from server.
     * see Scoreboard#fetchUserScores(Long, ModeType)
     */
    public void fetchScores(ModeType difficulty) {

        switch (difficulty) {
            case EASY -> easyScores.fetchUserScores(userId, EASY);

            case MEDIUM -> mediumScores.fetchUserScores(userId, MEDIUM);

            case HARD -> hardScores.fetchUserScores(userId, HARD);

        }
    }


    /**
     * Adds a score to the personal scores
     *
     * @param time       - see {@link Scoreboard#addScore(Double, int, ModeType, boolean)}
     * @param difficulty - see {@link Scoreboard#addScore(Double, int, ModeType, boolean)}
     */
    public void addScore(Double time, int points, ModeType difficulty) {
        WorldScores ws = WorldScores.getInstance();
        if (!isLoggedIn()) return;

        switch (difficulty) {
            case EASY -> {
                easyScores.addScore(time, points, difficulty, true);
                ws.getEasyScores().addScore(time, points, difficulty, false);
            }
            case MEDIUM -> {
                mediumScores.addScore(time, points, difficulty, true);
                ws.getMediumScores().addScore(time, points, difficulty, false);
            }
            case HARD -> {
                hardScores.addScore(time, points, difficulty, true);
                ws.getHardScores().addScore(time, points, difficulty, false);
            }
            default -> {
            }
        }
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
            if (deleted) {
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
     * Getter for the account
     *
     * @return - see {@link #account}
     */
    public Account getAccount() {
        return account;
    }

    /**
     * To string method for the User class
     *
     * @return String represenation of the class
     */
    @Override
    public String toString() {
        return "Het";
    }
}
