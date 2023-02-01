package model;

import database.entity.Leaderboard;

import java.util.Date;

public class Score {

    private String username;
    private Long scoreid;
    private int time;
    private String difficulty;
    private Date timestamp;
    private String grade;


    public Score(Leaderboard lb) {
        this.username = lb.getAccountid().getUsername();
        this.time = lb.getTime();
        this.timestamp = lb.getTimestamp();
        this.difficulty = lb.getDifficulty();
        this.grade = scoreGrader(time, difficulty);
        this.scoreid = lb.getScoreid();
    }

    ;

    public String getUsername() {
        return username;
    }

    public int getTime() {
        return time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getGrade() {
        return grade;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Long getScoreid() {
        return scoreid;
    }


    /**
     * 3 difficulties, 4 grades each
     * 💀
     * @param seconds
     * @param difficulty
     * @return
     */
    private String scoreGrader(int seconds, String difficulty) {

        switch (difficulty) {
            case "easy":
                if (seconds < 10) {
                    grade = "🎉";
                } else if (seconds >= 10 && seconds < 20) {
                    grade = "😲🤯";
                } else if (seconds >= 20 && seconds < 30) {
                    grade = "😲";
                } else if (seconds >= 30 && seconds < 40) {
                    grade = "💀";
                }
                break;
            case "medium":
                if (seconds < 10) {
                    grade = "Incredible!";
                } else if (seconds >= 10 && seconds < 20) {
                    grade = "Excellent";
                } else if (seconds >= 20 && seconds < 30) {
                    grade = "Well done";
                } else if (seconds >= 30 && seconds < 40) {
                    grade = "Try again";
                }
                break;
            case "hard":
                if (seconds < 10) {
                    grade = "John von Neumann reborn";
                } else if (seconds >= 10 && seconds < 20) {
                    grade = "Excellent";
                } else if (seconds >= 20 && seconds < 30) {
                    grade = "Well done";
                } else if (seconds >= 30 && seconds < 40) {
                    grade = "Maybe try an easier difficulty?";
                }

                break;
            default:
                grade = "Hämmästyttävä";
        }
        return grade;
    }

    @Override
    public String toString() {
        return "Score{" +
                "username='" + username + '\'' +
                ", scoreid=" + scoreid +
                ", time=" + time +
                ", difficulty='" + difficulty + '\'' +
                ", timestamp=" + timestamp +
                ", grade='" + grade + '\'' +
                '}';
    }
}
