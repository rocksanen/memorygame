package database.datasource;

import jakarta.persistence.*;

/**
 * Singleton classs, that creates EntityManager,
 * which handles the connection between the database and the program
 *
 * @author Eetu Soronen
 * @version 1
 */
public class SqlJpaConn {

    /**
     * EntityManageFactory creates the EntityManager.
     */
    private static EntityManagerFactory emf = null;

    /**
     * EntityManager handles the connection between the database and the program.
     */
    private static EntityManager em = null;


    /**
     * Singleton constructor, which creates the entitymanager if it doesn't exist
     * or returns it if it already exists.
     *
     * @return returns the EntityManager
     */
    public static EntityManager getInstance() {

        if (em == null) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("DevPU");
            }
            em = emf.createEntityManager();
        }
        return em;
    }
}


