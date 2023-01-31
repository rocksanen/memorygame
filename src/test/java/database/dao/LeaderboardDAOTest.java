package database.dao;

import database.datasource.SqlJpaConn;
import jakarta.persistence.EntityManager;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeaderboardDAOTest {

    //before all method creates entitymanager
    @BeforeAll
    static void setUpAll() {
        EntityManager em = SqlJpaConn.getInstance();
    }


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveScores() {
    }

    @Test
    void getAccountScores() {
    }

    @Test
    void readWorldScores() {
    }

    @Test
    void deleteScore() {
    }

    @Test
    void deleteAllScores() {
        ILeaderboardDAO leaderboardDAO = new LeaderboardDAO();
        leaderboardDAO.deleteAllScores(23L);


    }
}
