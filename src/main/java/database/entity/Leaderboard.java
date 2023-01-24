package database.entity;

import jakarta.persistence.*;


@Entity
public class Leaderboard {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int scoreid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private Account userid;
    private int score;
    private String grade;

    /**
     * hibernaten vaatima tyhjä konstruktori
     */
    public Leaderboard() {}

    /**
     * Olion konstruktori, joka asettaa sen kaikille muuttujille arvot.
     * @param userid - katso  {@link #userid}
     * @param score - katso {@link #score}
     */
    public Leaderboard(Account userid, int score, String grade) {
        this.userid = userid;
        this.score = score;
    };


    public Account getUserid() {
        return userid;
    }

    public void setUserid(Account userid) {
        this.userid = userid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScoreid() {
        return scoreid;
    }

    public void setScoreid(int scoreid) {
        this.scoreid = scoreid;
    }
}
