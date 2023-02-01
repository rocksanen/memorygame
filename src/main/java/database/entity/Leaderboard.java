package database.entity;

import jakarta.persistence.*;
import model.ModeType;

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
    private Double time;

    @Enumerated(EnumType.STRING)
    private ModeType modeType;
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
    public Leaderboard(Account accountid, Double time, ModeType modeType, Date timestamp) {
        this.accountid = accountid;
        this.time = time;
        this.timestamp = timestamp;
        this.modeType = modeType;
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

    public ModeType getDifficulty() {
        return modeType;
    }

    public void setDifficulty(ModeType modeType) {
        this.modeType = modeType;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double seconds) {
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
                ", difficulty=" + modeType +
                ", timestamp=" + timestamp +
                '}';
    }
}
