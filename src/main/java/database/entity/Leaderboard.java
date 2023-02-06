package database.entity;

import jakarta.persistence.*;
import model.ModeType;

import java.util.Date;


/**
 * Entity that is respoonsible mapping data to Leaderboard table in database.
 * This table contains all the scores of the players.
 *
 * @author Eetu Soronen
 * @version 1
 */
@Entity
public class Leaderboard {


    /**
     * scoreid is the primary key of the table.
     * It's generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreid;


    /**
     * accountid is the foreign key of the table.
     * It's used to connect the score to the account.
     * Each account can have multiple scores.
     * Each score can only belong to one account.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountid")
    private Account accountid;

    /**
     * Finishing time of the game.
     */
    private Double time;

    /**
     * points of the score.
     */
    private int points;

    /**
     * Difficulty of the played game.
     */
    @Enumerated(EnumType.STRING)
    private ModeType difficulty;

    /**
     * Timestamp of the score.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    /**
     * Empty constructor required by Hibernate
     */
    public Leaderboard() {
    }

    /**
     * Constructor for Leaderboard class.
     *
     * @param accountid  - see {@link #accountid}
     * @param time       - see {@link #time}
     * @param difficulty - see {@link #difficulty}
     * @param timestamp  - see {@link #timestamp}
     */
    public Leaderboard(Account accountid, Double time, int points, ModeType difficulty, Date timestamp) {
        this.accountid = accountid;
        this.time = time;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.points = points;
    }

    /**
     * Getter for timestamp
     *
     * @return - see {@link #timestamp}
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Setter for timestamp
     *
     * @param timestamp - see {@link #timestamp}
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter for accountid
     *
     * @return - see {@link #accountid}
     */
    public Account getAccountid() {
        return accountid;
    }


    /**
     * Setter for accountid
     *
     * @param accountid - see {@link #accountid}
     */
    public void setAccountid(Account accountid) {
        this.accountid = accountid;
    }

    /**
     * Setter for accountid
     *
     * @param userid - see {@link #accountid}
     */
    public void getAccountid(Account userid) {
        this.accountid = userid;
    }


    /**
     * Getter for difficulty
     *
     * @return - see {@link #difficulty}
     */
    public ModeType getDifficulty() {
        return difficulty;
    }

    /**
     * Setter for difficulty
     *
     * @param difficulty - see {@link #difficulty}
     */
    public void setDifficulty(ModeType difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Getter for time
     *
     * @return - see {@link #time}
     */
    public Double getTime() {
        return time;
    }

    /**
     * Setter for time
     *
     * @param seconds - see {@link #time}
     */
    public void setTime(Double seconds) {
        this.time = seconds;
    }

    /**
     * Getter for scoreid
     *
     * @return - see {@link #scoreid}
     */
    public Long getScoreid() {
        return scoreid;
    }

    /**
     * Setter for scoreid
     *
     * @param scoreid - see {@link #scoreid}
     */
    public void setScoreid(Long scoreid) {
        this.scoreid = scoreid;
    }

    /**
     * Getter for points
     * @return - see {@link #points}
     */
    public int getPoints() {
        return points;
    }

    /**
     * Setter for points
     * @param points - see {@link #points}
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * toString method for Leaderboard class.
     *
     * @return - String representation of the class.
     */
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
