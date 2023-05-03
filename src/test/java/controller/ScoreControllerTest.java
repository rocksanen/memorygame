package controller;

import database.entity.Leaderboard;
import model.ModeType;
import model.Score;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScoreControllerTest {

    ScoreController sc;

    @BeforeEach
    void setUp() {
        this.sc = new ScoreController();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchScores() {
        assertEquals(sc.getScoresRaw(ModeType.EASY).size(), 100);
        sc.fetchScores(ModeType.EASY);
        assertNotNull(sc.getScoresRaw(ModeType.EASY));
    }

    @Test
    void getTopFiveScores() {
        assertEquals(sc.getTopFiveScores(ModeType.EASY).size(), 5);
        sc.fetchScores(ModeType.EASY);
        assertNotNull(sc.getTopFiveScores(ModeType.EASY));
    }

    @Test
    void getScoresRaw() {
        assertEquals(100, sc.getScoresRaw(ModeType.EASY).size());
        sc.fetchScores(ModeType.EASY);
        assertNotNull(sc.getScoresRaw(ModeType.EASY));
    }

    @Test
    void formatScore() {
    }

    @Test
    void formatScoreVerbose() {

    }

    @Test
    void formatScores() {
        sc.fetchScores(ModeType.EASY);
        ArrayList<Score> scores = sc.getScoresRaw(ModeType.EASY);
        ArrayList<String> formattedScores = sc.formatScores(scores);
        assertEquals(5, formattedScores.size());
    }

    @Test
    void fetchPersonalScores() {
        sc.fetchPersonalScores();
        assertNull(sc.getUserScoresRaw(ModeType.EASY));
        User u = User.getInstance();
        u.signup("test", "test");
        u.login("test", "test");
        assertNotNull(sc.getUserScoresRaw(ModeType.EASY));
    }

    @Test
    void getTopFivePersonalScores() {
        sc.fetchPersonalScores();
        assertNotNull(sc.getTopFivePersonalScores(ModeType.EASY));
    }

    @Test
    void getUserScoresRaw() {
        sc.fetchPersonalScores();
        sc.getUserScoresRaw(ModeType.EASY);
        assertEquals(0, sc.getUserScoresRaw(ModeType.EASY).size());
    }
}