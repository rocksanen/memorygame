package database.dao;

import database.entity.Account;
import database.entity.Leaderboard;
import model.ModeType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardDAOTest {

    private static LeaderboardDAO leaderboardDAO;

    @BeforeEach
    void setUp() throws Exception {
        leaderboardDAO = new LeaderboardDAO();

    }

    @AfterEach
    void tearDown() throws Exception {
        leaderboardDAO = null;
    }

    //Test case for saving a score to the database
    @Test
    void saveScore() {
        // Create a test account
        AccountDAO  accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByName("test1");
        accountDAO.saveAccount(account);


        // Create a test leaderboard entry
        Double time = 10.5;
        int points = 100;
        ModeType difficulty = ModeType.EASY;
        Date timestamp = new Date();
        Leaderboard leaderboard = new Leaderboard(account, time, points, difficulty, timestamp);

        // Attempt to save the leaderboard entry
        boolean saved = leaderboardDAO.saveScore(leaderboard);

        // Check that the leaderboard entry was saved successfully
        assertTrue(saved);

    }

    @Test
    void getAccountScores() {
        LeaderboardDAO dao = new LeaderboardDAO();
        ArrayList<Leaderboard> scores = dao.getAccountScores(1L);
        assertTrue(scores instanceof ArrayList);

    }

    @Test
    void getAccountScoresByDifficulty() {
        // Call the DAO to retrieve scores for a specific account ID and difficulty level
        Long accountid = 1L; // Replace with a valid account ID from your database
        ModeType difficulty = ModeType.EASY;
        LeaderboardDAO dao = new LeaderboardDAO();
        ArrayList<Leaderboard> scores = dao.getAccountScoresByDifficulty(accountid, difficulty);

        // Check that the result list is not null
        assertNotNull(scores);

    }

    @Test
    void readWorldScores() {
        LeaderboardDAO dao = new LeaderboardDAO();
        ArrayList<Leaderboard> scores = dao.readWorldScores(ModeType.EASY);
        assertTrue(scores instanceof ArrayList<Leaderboard>);
        assertTrue(scores.get(0) instanceof Leaderboard);
    }

    @Test
    @Disabled
    void deleteScore() {
        Long scoreId = 11L;
        boolean result = leaderboardDAO.deleteScore(scoreId);
        Assertions.assertTrue(result);
    }

    @Test
    void deleteAllScores() {
        // Call the DAO to delete all scores for a specific account ID
        Long accountid = 9L;
        LeaderboardDAO dao = new LeaderboardDAO();

        // Get all the scores for the specified account ID
        ArrayList<Leaderboard> scores = dao.getAccountScores(accountid);

        // Delete each score
        for (Leaderboard score : scores) {
            dao.deleteScore(score.getScoreid());
        }

        // Check that there are no scores left for the specified account ID
        ArrayList<Leaderboard> remainingScores = dao.getAccountScores(accountid);
        assertEquals(0, remainingScores.size());

    }
}