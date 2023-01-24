package database.dao;

import database.datasource.SqlJpaConn;
import database.entity.Account;
import database.entity.Leaderboard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public interface IAccountDAO {

    void saveAccount(Account account);
    Account getAccount(int id);
    List<Account> getAllAccounts();

}

