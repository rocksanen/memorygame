package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocksmithTest {

    @Test
    void testHashPassword() {
        String password = "password123";
        String hashedPassword = Locksmith.hashPassword(password);
        Assertions.assertNotNull(hashedPassword);
    }

    @Test
    void testCheckPassword() {
        String password = "password123";
        String hashedPassword = Locksmith.hashPassword(password);
        Assertions.assertTrue(Locksmith.checkPassword(password, hashedPassword));
    }

    @Test
    void testCheckWrongPassword() {
        String password = "password123";
        String hashedPassword = Locksmith.hashPassword(password);
        String wrongPassword = "wrongpassword";
        Assertions.assertFalse(Locksmith.checkPassword(wrongPassword, hashedPassword));
    }
}
