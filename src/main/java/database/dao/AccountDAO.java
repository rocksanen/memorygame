package database.dao;

import database.datasource.SqlJpaConn;
import database.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class AccountDAO implements IAccountDAO {

    @Override
    public void saveAccount(Account account) {
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }


    /**
     * Hakee simulaattorin tietokannasta sen id:n perustella.
     * @param id
     * @return Simulaattori-olio
     */
    @Override
    public Account getAccount(int id) {
        EntityManager em = SqlJpaConn.getInstance();
        em.getTransaction().begin();
        Account a = em.find(Account.class, id);
        em.getTransaction().commit();
        return a;
    }

    @Override
    public List<Account> getAllAccounts() {
        EntityManager em = SqlJpaConn.getInstance();
        String jpqlQuery = "SELECT s FROM Account s";
        Query q = em.createQuery(jpqlQuery);
        List<Account> resultList = q.getResultList();
        return resultList;
    }
}

