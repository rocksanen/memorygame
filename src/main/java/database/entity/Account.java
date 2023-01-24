package Database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userid;
    private String username;
    private String password;


    /**
     * hibernaten vaatima tyhjä konstruktori
     */
    public Account() {}

    /**
     * Olion konstruktori, joka asettaa sen kaikille muuttujille arvot.
     * @param username - katso  {@link #username}
     * @param password - katso {@link #password}
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    };


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
