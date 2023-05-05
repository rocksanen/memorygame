package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    Scoreboard sb;

    @BeforeEach
    void setUp() {
        sb = new Scoreboard();
        sb.fetchWorldScores(ModeType.TEST);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addScore() {
        User u = User.getInstance();
        u.signup("test", "test");
        u.login("test", "test");

        sb.addScore(1.0, 1, ModeType.EASY, false);

        assertEquals(1, sb.getScores().size());

        u.deleteAccount();
    }

    @Test
    void getScores() {
        assertEquals(0, sb.getScores().size());
    }

    @Test
    void deleteScore() {
        User u = User.getInstance();
        u.signup("test", "test");
        u.login("test", "test");

        sb.addScore(1.0, 1, ModeType.TEST, true);
        sb.fetchWorldScores(ModeType.TEST);
        Score score = sb.getScores().get(0);
        sb.deleteScore(score.getScoreid());
        assertEquals(0, sb.getScores().size());

        u.deleteAccount();

    }

    @Test
    void fetchWorldScores() {
        User u = User.getInstance();
        u.signup("test", "test");
        u.login("test", "test");


        sb.addScore(1.0, 1, ModeType.TEST, true);
        sb.fetchWorldScores(ModeType.TEST);
        assertEquals(1, sb.getScores().size());

        u.deleteAccount();
    }

    @Test
    void fetchUserScores() {
        User u = User.getInstance();
        u.signup("test", "test");
        u.login("test", "test");
        sb.addScore(1.0, 1, ModeType.TEST, true);
        sb.fetchUserScores(u.getUserId(), ModeType.TEST);
        assertTrue(sb.getScores().size() > 0);

        u.deleteAccount();
    }

    @Test
    void testToString() {
        assertEquals("Scoreboard{scores=[]}", sb.toString());
    }
}