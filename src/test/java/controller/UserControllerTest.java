package controller;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController uc;

    @BeforeEach
    void setUp() {
        uc = new UserController();
        uc.register("test", "test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        assertTrue(uc.login("test", "test"));
    }

    @Test
    void register() {
        uc.login("test", "test");
        User u = User.getInstance();
        u.deleteAccount();
        assertTrue(uc.register("test", "test"));
    }

    @Test
    void logout() {
        uc.login("test", "test");
        uc.logout();
        assertEquals(uc.getUsername(), "tony the tiger");
    }

    @Test
    void isLoggedIn() {
        uc.logout();
        assertFalse(uc.isLoggedIn());
        uc.login("test", "test");
        assertTrue(uc.isLoggedIn());
    }

    @Test
    void getUsername() {
        uc.logout();
        assertEquals(uc.getUsername(), "tony the tiger");
    }
}