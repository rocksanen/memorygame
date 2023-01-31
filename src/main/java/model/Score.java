package model;

import database.entity.Leaderboard;

import java.util.Date;

class Score {

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
        this.grade = scoreGrader(time);
        this.scoreid = lb.getScoreid();
    };

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

    private String scoreGrader(int seconds) {
        String grade = "Hämmästyttävä";
        if (seconds < 10) {
            grade = "John von Neumann";
        } else if (seconds >= 10 && seconds < 20) {
            grade = "Excellent";
        } else if (seconds >= 20 && seconds < 30) {
            grade = "Acceptable";
        } else if (seconds >= 30 && seconds < 40) {
            grade = "Mediocore";
        } else if (seconds >= 40 && seconds < 50) {
            grade = "Passable";
        } else {
            grade = "Demented";
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
