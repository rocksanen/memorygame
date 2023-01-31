package model;

import database.dao.ILeaderboardDAO;
import database.dao.LeaderboardDAO;
import database.datasource.SqlJpaConn;
import database.entity.Leaderboard;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScoreboardTest {

    //before all method that create entitymanager to save load time
    @BeforeAll
    static void setUpAll() {
        EntityManager em = SqlJpaConn.getInstance();
    }

    @BeforeEach
    void setUp() {
        User u = User.getInstance();
        u.signup("JUNITTEST");
        u.login("JUNITTEST");
        u.saveScore(999);
        u.logout();
    }

    @AfterEach
    void tearDown() {
        User u = User.getInstance();
        u.login("JUNITTEST");
        u.deleteAccount();
    }

    @Test
    void getWorldscores() {
        Scoreboard scoreboard = Scoreboard.getInstance();
        ArrayList<Leaderboard> worldscores = scoreboard.getWorldscores();
        // assert that worldscore containts at least 1 leaderboard object
        assertNotNull(worldscores.get(0));
    }
}
