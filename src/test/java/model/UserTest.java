package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User u;

    @BeforeEach
    void setUp() {
        this.u = User.getInstance();
        u.signup("test", "test");
        u.logout();
    }

    @AfterEach
    void tearDown() {
        u.login("test", "test");
        u.deleteAccount();
        u.logout();
    }

    @Test
    void getInstance() {
        User u = User.getInstance();
        assertNotNull(u);
    }

    @Test
    void login() {
        User u = User.getInstance();
        assertTrue(u.login("test", "test"));
    }

    @Test
    void signup() {
        User u = User.getInstance();
        u.login("test", "test");
        u.deleteAccount();
        assertTrue(u.signup("test", "test"));
    }

    @Test
    void getUsername() {
        u.login("test", "test");
        assertEquals("test", u.getUsername());
    }

    @Test
    void isLoggedIn() {
        u.login("test", "test");
        assertTrue(u.isLoggedIn());
    }

    @Test
    void getFetchAddScores() {
        u.login("test", "test");
        u.addScore(60.0, 100, ModeType.EASY);
        Scoreboard sb = u.getScores(ModeType.EASY);
        assertEquals(sb.getScores().size(), 1);
    }

    @Test
    void deleteAccount() {
        assertFalse(u.deleteAccount());
        u.login("test", "test");
        assertTrue(u.deleteAccount());
    }


    @Test
    void getAccount() {
        // default user
        u.logout();
        assertNull(u.getAccount());
        u.login("test", "test");
        assertEquals(u.getAccount().getUsername(), "test");
    }

    @Test
    void testToString() {
        assertTrue(u.toString().contains("username='tony the tiger'"));
    }
}