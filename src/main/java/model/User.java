package model;

import database.dao.AccountDAO;
import database.dao.IAccountDAO;
import database.dao.ILeaderboardDAO;
import database.dao.LeaderboardDAO;
import database.entity.Account;
import database.entity.Leaderboard;

import java.util.ArrayList;
import java.util.Date;


public class User {

    private String username;
    private Long userId;
    private Scoreboard personalScores;

    private IAccountDAO accountdao = AccountDAO.getInstance();
    private ILeaderboardDAO leaderboarddao = LeaderboardDAO.getInstance();

    private Account dbAccount;


    private static User instance;

    private User() {
        this.username = "tony the tiger";
        this.userId = null;
        this.dbAccount = null;
        this.personalScores = null;
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }


    /**
     * Searches username from database and returns the updated the User object
     * @param username
     * @return
     */
    public boolean login(String username) {
        try {
            Account account = accountdao.getAccountByName(username);
            System.out.println("sTRINGIFYING ACCOUNT: " + account.toString() + "");

            if (account.getAccountid() != null) {
                this.userId = account.getAccountid();
                this.username = account.getUsername();
                this.personalScores = new Scoreboard(leaderboarddao.getAccountScores(userId));
                this.dbAccount = account;
                return true;
            }

        } catch (Exception e) {
            System.out.println("Username not found!" + e);;
        }
        return false;
    }


    /**
     * Searches username from db, creates it if it does not exist
     * @param username
     * @return
     */
    public boolean signup(String username) {
        // save account
        Long id = accountdao.saveAccount(new Account(username, "tiger"));
        if (id != null) {
            System.out.println("Username already exists!");
            return false;
        }

        Account account = accountdao.getAccountByName(username);
        this.userId = account.getAccountid();
        this.username = account.getUsername();
        this.personalScores = new Scoreboard(leaderboarddao.getAccountScores(userId));
        this.dbAccount = account;
        return true;
    }

    public String getUsername() {
        return username;
    }

    public boolean logout() {
        this.username = "tony the tiger";
        this.userId = null;
        this.personalScores = null;
        this.dbAccount = null;
        return true;
    }



    public boolean deleteAccount() {
        if (userId == null) return false;
        try {
            boolean deleted = accountdao.deleteAccount(instance.userId);
            if(deleted == true) {
                return logout();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", personalScores=" + personalScores +
                ", accountdao=" + accountdao +
                ", leaderboarddao=" + leaderboarddao +
                ", dbAccount=" + dbAccount +
                '}';
    }
}
