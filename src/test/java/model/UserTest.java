package model;

import database.dao.ILeaderboardDAO;
import database.dao.LeaderboardDAO;
import database.entity.Leaderboard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
        User u = User.getInstance();
        u.signup("junittest");
        u.login("junittest");
        u.saveScore(10);

    }

    @AfterEach
    void tearDown() {
        User u = User.getInstance();
        u.deleteAccount();
    }

    @Test
    void getInstance() {
        User u = User.getInstance();
        assertNotNull(u);
    }

    @Test
    void loginAndSignup() {
        User u = User.getInstance();
        u.signup("junittest");
        u.login("junittest");
        assertEquals("junittest", u.getUsername());
    }

    @Test
    void getUsername() {
        User u = User.getInstance();
        u.signup("junittest");
        assertEquals("junittest", u.getUsername());
    }

    @Test
    void getPersonalScores() {
        User u = User.getInstance();
        u.getPersonalScores();
        assertNull(u.getPersonalScores());
    }

    @Test
    void saveScore() {
        User u = User.getInstance();
        u.saveScore(10);
        ArrayList<Leaderboard> scores = u.getPersonalScores();
        assertEquals(10, scores.get(0).getScore());
    }

    @Test
    void deleteAccount() {
        User u = User.getInstance();
        Long userid = u.getUserId();
        System.out.println("userid: " + userid);
        u.deleteAccount();
        ILeaderboardDAO leaderboarddao = new LeaderboardDAO();
        ArrayList<Leaderboard> scores = leaderboarddao.getAccountScores(userid);
        assertEquals(0, scores.size());
        assertEquals(u.getUsername(), "Tony the Tiger");
    }

    @Test
    void deleteScore() {
        User u = User.getInstance();
        u.saveScore(10);
        ArrayList<Leaderboard> scores = u.getPersonalScores();
        // print all sscores
        for (Leaderboard l : scores) {
            System.out.println(l.toString());
            u.deleteScore(l.getScoreid());
        }
        assertEquals(u.getPersonalScores().size(), 0);
    }
}