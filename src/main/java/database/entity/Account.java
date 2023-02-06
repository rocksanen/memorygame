package database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * Account class entity, which is to map data to Acount table in the database.
 * This class is used to store the username and password of the user.
 * @author Eetu Soronen
 * @version 1
 */
@Entity
public class Account {

    /**
     * accountid is the primary key of the table.
     * It's generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long accountid;

    /**
     * Username of the user.
     */
    private String username;

    /**
     * Password of the user.
     */
    private String password;


    /**
     * Empty constructor required by Hibernate
     */
    public Account() {}

    /**
     * Constructor for Account class.
     * @param username - see {@link #username}
     * @param password - see {@link #password}
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    };


    /**
     * Getter for accountid
     * @return - see {@link #accountid}
     */
    public Long getAccountid() {
        return accountid;
    }


    /**
     * Setter for accountid
     * @param userid - see {@link #accountid}
     */
    public void setAccountid(Long userid) {
        this.accountid = userid;
    }

    /**
     * Getter for username
     * @return - see {@link #username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username - see {@link #username}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password
     * @return - see {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password - see {@link #password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * toString method for Account class
     * @return - see {@link #accountid}, {@link #username}, {@link #password}
     */
    @Override
    public String toString() {
        return "Account{" +
                "accountid=" + accountid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
