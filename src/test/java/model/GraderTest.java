package model;

import org.junit.jupiter.api.Test;

import static model.ModeType.*;
import static org.junit.jupiter.api.Assertions.*;

class GraderTest {


    @Test
    void calculatePoints() {
        int points = Grader.calculatePoints(3, 5000);
        assertEquals(600, points);
    }

    @Test
    void scoreGrader() {
        String grade = Grader.scoreGrader(7999, HARD);
        assertEquals("⭐⭐", grade);
        grade = Grader.scoreGrader(8000, HARD);
        assertEquals("⭐⭐⭐", grade);

        grade = Grader.scoreGrader(2400, EASY);
        assertEquals("⭐⭐⭐", grade);

        grade = Grader.scoreGrader(100, MEDIUM);
        assertEquals("⭐", grade);
    }
}