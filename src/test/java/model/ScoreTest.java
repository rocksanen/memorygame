package model;

import database.entity.Account;
import database.entity.Leaderboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        Account account = new Account();
        account.setUsername("testuser");

        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setAccountid(account);
        leaderboard.setTime(60.0);
        leaderboard.setDifficulty(ModeType.EASY);
        leaderboard.setTimestamp(new Date());
        leaderboard.setScoreid(1L);
        leaderboard.setPoints(10);

        score = new Score(leaderboard);
    }

    @Test
    void getUsername() {
        assertEquals("testuser", score.getUsername());
    }

    @Test
    void getTime() {
        assertEquals(60.0, score.getTime());
    }

    @Test
    void getDifficulty() {
        assertEquals(ModeType.EASY, score.getDifficulty());
    }

    @Test
    void getGrade() {
        assertEquals("⭐", score.getGrade());
    }

    @Test
    void getTimestamp() {
        assertEquals(new Date(), score.getTimestamp());
    }

    @Test
    void getScoreid() {
        assertEquals(1L, score.getScoreid());
    }

    @Test
    void getPoints() {
        assertEquals(10, score.getPoints());
    }

    @Test
    void testToString() {
        assertEquals("Score{username='testuser', scoreid=1, time=60.0, difficulty=EASY, " +
                "timestamp=" + new Date() + ", grade='⭐', points=10}", score.toString());
    }
}
