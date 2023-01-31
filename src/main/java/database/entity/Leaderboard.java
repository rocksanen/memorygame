package database.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Leaderboard {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long scoreid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountid")
    private Account accountid;

    // this could maybe be time (in seconds)
    private Integer time;
    private String difficulty;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * hibernaten vaatima tyhjä konstruktori
     */
    public Leaderboard() {}

    /**
     * Olion konstruktori, joka asettaa sen kaikille muuttujille arvot.
     */
    public Leaderboard(Account accountid, Integer time, String difficulty, Date timestamp) {
        this.accountid = accountid;
        this.time = time;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
    };

    public Account getAccountid() {
        return accountid;
    }

    public void getAccountid(Account userid) {
        this.accountid = userid;
    }



    public void setAccountid(Account accountid) {
        this.accountid = accountid;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer seconds) {
        this.time = seconds;
    }

    public Long getScoreid() {
        return scoreid;
    }

    public void setScoreid(Long scoreid) {
        this.scoreid = scoreid;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "scoreid=" + scoreid +
                ", accountid=" + accountid +
                ", seconds=" + time +
                ", difficulty=" + difficulty +
                ", timestamp=" + timestamp +
                '}';
    }
}
