package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocksmithTest {

    String password = "password";
    String hashedPassword = "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=";

    @Test
    void hashPassword() {
        String password = "password";
        String hash = Locksmith.hashPassword(password);
        assertEquals(hashedPassword, hash);
    }

    @Test
    void checkPassword() {
        boolean check = Locksmith.checkPassword(password, hashedPassword);
        assertTrue(check);
    }
}