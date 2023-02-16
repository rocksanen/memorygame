package database.entity;

import model.ModeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    private Leaderboard leaderboard;
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("test", "password");
        leaderboard = new Leaderboard(account, 30.0, 100, ModeType.EASY, new Date());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(account, leaderboard.getAccountid());
        leaderboard.setAccountid(null);
        assertNull(leaderboard.getAccountid());

        assertEquals(30.0, leaderboard.getTime());
        leaderboard.setTime(40.0);
        assertEquals(40.0, leaderboard.getTime());

        assertEquals(100, leaderboard.getPoints());
        leaderboard.setPoints(200);
        assertEquals(200, leaderboard.getPoints());

        assertEquals(ModeType.EASY, leaderboard.getDifficulty());
        leaderboard.setDifficulty(ModeType.MEDIUM);
        assertEquals(ModeType.MEDIUM, leaderboard.getDifficulty());

        assertNotNull(leaderboard.getTimestamp());
        Date timestamp = new Date();
        leaderboard.setTimestamp(timestamp);
        assertEquals(timestamp, leaderboard.getTimestamp());

        assertNull(leaderboard.getScoreid());
        leaderboard.setScoreid(1L);
        assertEquals(1L, leaderboard.getScoreid());
    }

    @Test
    void testToString() {
        String expected = "Leaderboard{scoreid=null, accountid=" + account.toString() + ", seconds=30.0, difficulty=EASY, timestamp=" + leaderboard.getTimestamp().toString() + '}';
        assertEquals(expected, leaderboard.toString());
    }
}