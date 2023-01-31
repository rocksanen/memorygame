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
        this.username = "Tony the Tiger";
        this.userId = 0L;

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
    public User login(String username) {
        try {
            Account account = accountdao.getAccountByName(username);

            if (userId != null && username != null) {
                this.userId = account.getAccountid();
                this.personalScores = leaderboarddao.getAccountScores(userId);
                this.dbAccount = account;
                return User.getInstance();
            }

        } catch (Exception e) {
            System.out.println("Username not found! Defaulting to defaultuser... " + e);
            this.userId = 0L;
            this.personalScores = null;
        }
        return null;
    }


    /**
     * Searches username from db, creates it if it does not exist
     * @param username
     * @return
     */
    public User signup(String username) {
        Account account = accountdao.getAccountByName(username);
        System.out.println("is account null? ");
        if (account == null) {
            try {
                Account a = new Account(username, "tiger");
                userId = accountdao.saveAccount(a);
                Account newAccount = accountdao.getAccount(userId);

                this.userId = newAccount.getAccountid();
                this. username = newAccount.getUsername();

                this.dbAccount = newAccount;
                return User.getInstance();

            } catch (Exception e) {
                System.out.println("error creating a user...");
            }
        }
        return null;
    }

    public String getUsername() {
        return username;
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
        if (userId != 0L) {
            String grade = scoreGrader(seconds);
            Leaderboard score = new Leaderboard(dbAccount, seconds, grade, new Date(System.currentTimeMillis()));
            return leaderboarddao.saveScores(score);
        }
        return false;
    }


    public boolean deleteAccount() {
        if (userId == 0L) return false;
        try {
            boolean deleted = accountdao.deleteAccount(instance.userId);
            if(deleted == true) {
                this.username = "Tony the Tiger";
                this.userId = 0L;
                return true;
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
