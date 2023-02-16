package database.dao;

import database.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    private AccountDAO accountDAO;

    @BeforeEach
    void setUp() throws Exception {
        accountDAO = new AccountDAO();
    }

    @AfterEach
    void tearDown() throws Exception{
        accountDAO = null;
    }

    @Test
    void saveAccount() {
        Account account = new Account();
        account.setUsername("testUser");
        account.setPassword("testPassword");
        assertTrue(accountDAO.saveAccount(account));
    }

    @Test
    void getAccount() {
        Account acc = new Account();
        acc.setUsername("testUser");
        acc.setPassword("testPass");
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
        Account acc1 = new Account("testuser5", "password5");
        Account acc2 = new Account("testuser6", "password6");

        accountDAO.saveAccount(acc1);
        accountDAO.saveAccount(acc2);

        ArrayList<Account> accounts = accountDAO.getAllAccounts();
        assertTrue(accounts.contains(acc1));
        assertTrue(accounts.contains(acc2));
    }

    @Test
    void deleteAccount() {
        // check that the account exists in the database
        Account retrievedAccount = accountDAO.getAccountByName("testuser5");
        assertNotNull(retrievedAccount);

        // delete the account
        boolean deleteResult = accountDAO.deleteAccount(retrievedAccount.getAccountid());
        assertTrue(deleteResult);

        // check that the account no longer exists in the database
        retrievedAccount = accountDAO.getAccountByName("testUser");
        assertNull(retrievedAccount);
    }
}