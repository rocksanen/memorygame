package database.dao;

import database.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    private AccountDAO accountDAO;

    @BeforeEach
    void setUp() {
        accountDAO = new AccountDAO();
    }

    @AfterEach
    void tearDown() {
        accountDAO = null;
    }

    @Test
    void saveAccount() {
        Random r = new Random();
        Account account = new Account();
        account.setUsername("testUser" + r.nextInt(1000000));
        account.setPassword("testPassword");
        assertTrue(accountDAO.saveAccount(account));
    }

    @Test
    void getAccount() {
        Account acc = accountDAO.getAccountByName("testUser");
        accountDAO.saveAccount(acc);
        assertNotNull(accountDAO.getAccount(acc.getAccountid()));

    }

    @Test
    void getAccountByName() {
        Account acc = new Account();
        acc.setUsername("userTest");
        accountDAO.saveAccount(acc);
        assertNotNull(accountDAO.getAccountByName("userTest"));

    }

    @Test
    void getAllAccounts() {
        Account acc1 = accountDAO.getAccountByName("testuser5");
        Account acc2 = accountDAO.getAccountByName("testuser6");

        accountDAO.saveAccount(acc1);
        accountDAO.saveAccount(acc2);

        ArrayList<Account> accounts = accountDAO.getAllAccounts();
        assertTrue(accounts.contains(acc1));
        assertTrue(accounts.contains(acc2));
    }

    @Test
    void deleteAccount() {
        // check that the account exists in the database
        Account retrievedAccount = accountDAO.getAccountByName("testuser55");
        if (retrievedAccount == null) {
            Account acc1 = new Account();
            acc1.setUsername("testuser55");
            acc1.setPassword("test123");
            accountDAO.saveAccount(acc1);
            retrievedAccount = accountDAO.getAccountByName("testuser55");
        }
        assertNotNull(retrievedAccount);

        // delete the account
        boolean deleteResult = accountDAO.deleteAccount(retrievedAccount.getAccountid());
        assertTrue(deleteResult);

        // check that the account no longer exists in the database
        retrievedAccount = accountDAO.getAccountByName("testUser55");
        assertNull(retrievedAccount);
    }
}