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
    private Integer score;
    private String grade;
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
     * @param accountid - katso  {@link #accountid}
     * @param score - katso {@link #score}
     */
    public Leaderboard(Account accountid, Integer score, String grade, Date timestamp) {
        this.accountid = accountid;
        this.score = score;
        this.grade = grade;
        this.timestamp = timestamp;
    };

    public Account getAccountid() {
        return accountid;
    }

    public void getAccountid(Account userid) {
        this.accountid = userid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getScoreid() {
        return scoreid;
    }


    public void setScoreid(Long scoreid) {
        this.scoreid = scoreid;
    }

    public void setAccountid(Account accountid) {
        this.accountid = accountid;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "scoreid=" + scoreid +
                ", accountid=" + accountid +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
