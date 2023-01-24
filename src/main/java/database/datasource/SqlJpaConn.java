package Database.datasource;

import jakarta.persistence.*;

/**
 * Singleton luokka, joka luo EntityManagerin,
 * joka huolehtii tietokannan ja ohjelman välisestä yhteydenpidosta
 * @author Eetu Soronen
 * @version 1
 */
public class SqlJpaConn {

    /**
     * EntityMaagerFactory luo entitymanagerin
     */
    private static EntityManagerFactory emf = null;
    /**
     * entitymanager olio, joka välittää tietoa ohjelman ja tietokanna välillä
     */
    private static EntityManager em = null;

    /**
     * Singletonin konstruktori, joka luo entitymanagerin jos sitä ei ole olemassa
     * tai palauttaa sen jos se on jo luotu.
     * @return palauttaa EntityManagerin
     */
    public static EntityManager getInstance() {

        if (em==null) {
            if (emf==null) {
                emf = Persistence.createEntityManagerFactory("DevPU");
            }
            em = emf.createEntityManager();
        }
        return em;
    }
}


