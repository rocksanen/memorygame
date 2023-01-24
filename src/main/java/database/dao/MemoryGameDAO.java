package Database.dao;

import Database.datasource.SqlJpaConn;
import Database.entity.Account;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class MemoryGameDAO implements IMemoryGameDAO {

    @Override
    public void saveUser(Account account) {
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    @Override
    public Account readUser(Integer id) {
        return null;
    }

    @Override
    public void saveScores(Account user) {

    }

    @Override
    public ArrayList<Integer> readUserScores(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Integer> readWorldScores() {
        return null;
    }
}

