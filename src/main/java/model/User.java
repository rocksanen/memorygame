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
    private ArrayList<Leaderboard> personalScores;

    private IAccountDAO accountdao = new AccountDAO();
    private ILeaderboardDAO leaderboarddao = new LeaderboardDAO();

    private Account dbAccount;


    private static User instance;

    private User() {
        this.username = "tony the tiger";
        this.userId = null;
        this.personalScores = null;
        this.dbAccount = null;

        this.accountdao = new AccountDAO();
        this.leaderboarddao = new LeaderboardDAO();
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
                this.personalScores = leaderboarddao.getAccountScores(userId);
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
        this.personalScores = leaderboarddao.getAccountScores(userId);
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

    public ArrayList<Leaderboard> getPersonalScores() {
        try {
            personalScores = leaderboarddao.getAccountScores(userId);
            return personalScores;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public boolean saveScore(Integer seconds) {
        // not a defaultuser check
        if (userId != null) {
            String grade = scoreGrader(seconds);
            Leaderboard score = new Leaderboard(dbAccount, seconds, grade, new Date(System.currentTimeMillis()));
            return leaderboarddao.saveScore(score);
        }
        return false;
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

    /**
     * User can only delete its own scores
     * @param id
     * @return
     */
    public boolean deleteScore(Long id) {
        for (Leaderboard lb: personalScores) {

            // check if id is in personalScores list
            if (lb.getScoreid() == id) {
                // redundant check to see if score's accountid is the same as the userid.
                if (lb.getAccountid().getAccountid() == instance.userId) {
                    leaderboarddao.deleteScore(id);
                    return true;
                } else return false;
            }
        }

        try {
            leaderboarddao.deleteScore(id);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }


    /**
     * rough
     * @param seconds
     * @return
     */
    private String scoreGrader(Integer seconds) {
        String grade = "Hämmästyttävä";
        if (seconds < 10) {
            grade = "John von Neumann";
        } else if (seconds >= 10 && seconds < 20) {
            grade = "Excellent";
        } else if (seconds >= 20 && seconds < 30) {
            grade = "Acceptable";
        } else if (seconds >= 30 && seconds < 40) {
            grade = "Mediocore";
        } else if (seconds >= 40 && seconds < 50) {
            grade = "Passable";
        } else {
            grade = "Demented";
        }
        return grade;
    }


    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Nimi: " + this.username + ", Käyttäjätunnus: " + this.userId + ", listalla tuloksia: " + personalScores.size();
    }
}
