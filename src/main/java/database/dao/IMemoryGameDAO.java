package Database.dao;

import Database.entity.Account;

import java.util.ArrayList;

public interface IMemoryGameDAO {

    void saveUser(Account account);
    Account readUser(Integer id);
    void saveScores(Account account);
    ArrayList<Integer> readUserScores(Integer id);

    ArrayList<Integer> readWorldScores();
}

