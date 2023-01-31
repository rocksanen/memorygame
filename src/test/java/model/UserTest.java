package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        this.user = User.getInstance();
        user.signup("JUNIT4000");
    }

    @AfterEach
    void tearDown() {
        user.deleteAccount();
    }


    @Test
    void loginNlogout() {
        user.logout();
        user.login("JUNIT4000");
    }

    @Test
    void getUsername() {
        assertEquals("junit4000", user.getUsername());
    }
    @Test
    void getUserId() {
        assertNotNull(user.getUserId());
    }

    @Test
    void testToString() {
        assertNotNull(user.toString());
    }
}