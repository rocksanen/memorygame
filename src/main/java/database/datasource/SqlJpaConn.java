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
        Map<String, Object> configOverrides = new HashMap<String, Object>();


        System.out.println("config.properties not found, trying to use env variables");
        configOverrides.put("jakarta.persistence.jdbc.url", System.getenv("MEMORYMAZE_DB_URL"));
        configOverrides.put("jakarta.persistence.jdbc.user", System.getenv("MEMORYMAZE_DB_USERNAME"));
        configOverrides.put("jakarta.persistence.jdbc.password", System.getenv("MEMORYMAZE_DB_PASSWORD"));

        // check if env values are null, if not return if yes continue
        if (configOverrides.get("jakarta.persistence.jdbc.url") != null
                && configOverrides.get("jakarta.persistence.jdbc.user") != null
                && configOverrides.get("jakarta.persistence.jdbc.password") != null) {
            return configOverrides;
        }


        Properties props = new Properties();
        try (InputStream input = SqlJpaConn.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            configOverrides.put("jakarta.persistence.jdbc.url", props.getProperty("jakarta.persistence.jdbc.url"));
            configOverrides.put("jakarta.persistence.jdbc.user", props.getProperty("jakarta.persistence.jdbc.user"));
            configOverrides.put("jakarta.persistence.jdbc.password", props.getProperty("jakarta.persistence.jdbc.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return configOverrides;
    }
}
