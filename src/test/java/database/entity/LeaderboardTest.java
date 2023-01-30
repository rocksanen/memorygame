package database.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class LeaderboardTest {
    @Test
    public void testGettersAndSetters() {
        Account account = new Account("user", "pass");
        Date date = new Date();
        Leaderboard leaderboard = new Leaderboard(account, 10, "A", date);

        assertEquals(account, leaderboard.getAccountid());
        assertEquals(10, leaderboard.getScore());
        assertEquals("A", leaderboard.getGrade());
        assertEquals(date, leaderboard.getTimestamp());

        Account newAccount = new Account("newuser", "newpass");
        leaderboard.setAccountid(newAccount);
        leaderboard.setScore(20);
        leaderboard.setGrade("B");
        leaderboard.setTimestamp(new Date());

        assertEquals(newAccount, leaderboard.getAccountid());
        assertEquals(20, leaderboard.getScore());
        assertEquals("B", leaderboard.getGrade());
        assertEquals(new Date(), leaderboard.getTimestamp());
    }
}
