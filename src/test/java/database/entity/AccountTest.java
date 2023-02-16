package database.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("testuser", "testpassword");
    }


    @Test
    void getAccountid() {
        assertNull(account.getAccountid());
    }

    @Test
    void setAccountid() {
        account.setAccountid(1L);
        assertEquals(1L, account.getAccountid());
    }

    @Test
    void getUsername() {
        assertEquals("testuser", account.getUsername());
    }

    @Test
    void setUsername() {
        account.setUsername("newuser");
        assertEquals("newuser", account.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("testpassword", account.getPassword());
    }

    @Test
    void setPassword() {
        account.setPassword("hasnewpassword");
        assertEquals("hasnewpassword", account.getPassword());
    }

    @Test
    void testToString() {
        String expected = "Account{accountid=null, username='testuser', password='testpassword'}";
        assertEquals(expected, account.toString());
    }
}