package database.entity;

import database.entity.Account;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AccountTest {
    @Test
    public void testConstructor() {
        Account account = new Account("user", "pass");
        assertEquals("user", account.getUsername());
        assertEquals("pass", account.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        Account account = new Account();
        account.setUsername("user");
        account.setPassword("pass");
        assertEquals("user", account.getUsername());
        assertEquals("pass", account.getPassword());
    }
}
