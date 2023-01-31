package model;

import database.entity.Account;
import database.entity.Leaderboard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    Score score;
    Date date;

    @BeforeEach
    void setUp() {
        this.date = new Date();
        this.score = new Score(
                new Leaderboard(
                        new Account("junit", "password"),
                        9000, "test", date));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsername() {
        assertEquals("junit", score.getUsername());
    }

    @Test
    void getTime() {
        assertEquals(9000, score.getTime());
    }

    @Test
    void getDifficulty() {
        assertEquals("test", score.getDifficulty());
    }

    @Test
    void getGrade() {
        assertEquals("Demented", score.getGrade());
    }

    @Test
    void getTimestamp() {
        assertEquals(date, score.getTimestamp());
    }

    @Test
    void getScoreid() {
        assertEquals(null, score.getScoreid());
    }

    @Test
    void testToString() {
        assertEquals("Score{username='junit', scoreid=null, time=9000, difficulty='test', timestamp=" + date + ", grade='Demented'}", score.toString());
    }
}