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
                Map<String, String> configOverrides = configOverider();
                emf = Persistence.createEntityManagerFactory("DevPU", configOverrides);
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    private static Map<String, String> configOverider() {
        Map<String, String> configOverrides = new HashMap<>();

        // jenkins can (not) use env variables
        if (System.getenv("MEMORYMAZE_DB_LINK") != null) {
            System.out.println("link: " + System.getenv("MEMORYMAZE_DB_LINK"));
            configOverrides.put("jakarta.persistence.jdbc.url", System.getenv("MEMORYMAZE_DB_LINK"));
        }
        if (System.getenv("MEMORYMAZE_DB_USER") != null) {
            System.out.println("user: " + System.getenv("MEMORYMAZE_DB_USER"));
            configOverrides.put("jakarta.persistence.jdbc.user", System.getenv("MEMORYMAZE_DB_USER"));
        }
        if (System.getenv("MEMORYMAZE_DB_PASSWORD") != null) {
            System.out.println("password: " + System.getenv("MEMORYMAZE_DB_PASSWORD"));
            configOverrides.put("jakarta.persistence.jdbc.password", System.getenv("MEMORYMAZE_DB_PASSWORD"));
        }

        if (configOverrides.size() == 3) {
            return configOverrides;
        }

        Properties props = new Properties();
        try (InputStream input = SqlJpaConn.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (String key : props.stringPropertyNames()) {
            configOverrides.put(key, props.getProperty(key));
        }


//        // print configOverride
//        for (String key : configOverrides.keySet()) {
//            System.out.println(key + "=" + configOverrides.get(key));
//        }
//        System.out.println(configOverrides.size());

        return configOverrides;
    }
}


