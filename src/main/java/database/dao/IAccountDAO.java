package database.dao;

import database.entity.Account;

import java.util.List;

public interface IAccountDAO {

    boolean saveAccount(Account account);

    Account getAccount(Long id);

    Account getAccountByNameAndPassword(String username, String password);

    Account getAccountByName(String name);

    List<Account> getAllAccounts();

    boolean deleteAccount(Long id);

    void passwordHasher();
}

