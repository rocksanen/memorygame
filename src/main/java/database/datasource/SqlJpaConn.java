package database.datasource;

import jakarta.persistence.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
                emf = Persistence.createEntityManagerFactory("DevPU", configOverider());
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    private static Map<String, Object> configOverider() {
        Properties props = new Properties();
        try (InputStream input = SqlJpaConn.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Map<String, Object> configOverrides = new HashMap<String, Object>();
        for (String key : props.stringPropertyNames()) {
            configOverrides.put(key, props.getProperty(key));
        }
        return configOverrides;
    }
}


