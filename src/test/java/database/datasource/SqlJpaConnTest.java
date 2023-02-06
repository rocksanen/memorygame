package database.datasource;

import static org.junit.Assert.assertNotNull;

import database.datasource.SqlJpaConn;
import jakarta.persistence.EntityManager;
import org.junit.Test;

public class SqlJpaConnTest {
    @Test
    public void getInstance_shouldReturnEntityManager() {
        EntityManager em = SqlJpaConn.getInstance();
        assertNotNull(em);
    }
}