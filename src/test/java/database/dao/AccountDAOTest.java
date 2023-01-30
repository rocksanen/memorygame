package database.dao;

import database.dao.IAccountDAO;
import database.dao.AccountDAO;
import database.entity.Account;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AccountDAOTest {
    private IAccountDAO accountDAO = new AccountDAO();

    @Test
    public void saveAccountTest() {
        Account account = new Account("testUsername", "testPassword");
        Long accountId = accountDAO.saveAccount(account);
        Account savedAccount = accountDAO.getAccount(accountId);
        assertEquals(account, savedAccount);
    }

    @Test
    public void getAccountTest() {
        Account account = new Account("testUsername2", "testPassword2");
        Long accountId = accountDAO.saveAccount(account);
        Account retrievedAccount = accountDAO.getAccount(accountId);
        assertEquals(account, retrievedAccount);
    }

    @Test
    public void getAccountByNameTest() {
        Account account = new Account("testUsername3", "testPassword3");
        accountDAO.saveAccount(account);
        Account retrievedAccount = accountDAO.getAccountByName("testUsername3");
        assertEquals(account, retrievedAccount);
    }

    @Test
    public void getAllAccountsTest() {
        Account account1 = new Account("testUsername4", "testPassword4");
        Account account2 = new Account("testUsername5", "testPassword5");
        accountDAO.saveAccount(account1);
        accountDAO.saveAccount(account2);
        ArrayList<Account> allAccounts = (ArrayList<Account>) accountDAO.getAllAccounts();
        assertTrue(allAccounts.contains(account1));
        assertTrue(allAccounts.contains(account2));
    }
}
