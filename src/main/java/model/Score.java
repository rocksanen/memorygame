package model;

import database.entity.Leaderboard;

import java.util.Date;

/**
 * Score class for the game
 * Score-object contains all the information about a single score
 * it also grades the score based on the time and difficulty
 * @author Eetu Soronen
 * @version 1
 */
public class Score {

    /**
     * Username of the player
     */
    private final String username;

    /**
     * Id of the score, retrieved from the database.
     */
    private final Long scoreid;

    /**
     * Time of the score
     */
    private final Double time;

    /**
     * Difficulty of the score
     */
    private final ModeType difficulty;

    /**
     * Timestamp of the score
     */
    private final Date timestamp;

    /**
     * Grade of the score
     */
    private String grade;

    /**
     * Points of the score
     */
    private final int points;

    /**
     * Constructor for Score
     *
     * @param lb Leaderboard-object
     */
    public Score(Leaderboard lb) {
        this.username = lb.getAccountid().getUsername();
        this.time = lb.getTime();
        this.timestamp = lb.getTimestamp();
        this.difficulty = lb.getDifficulty();
        this.grade = scoreGrader(time, difficulty);
        this.scoreid = lb.getScoreid();
        this.points = lb.getPoints();
    }

    /**
     * getter for username
     *
     * @return - see {@link Score#username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for time
     *
     * @return - see {@link Score#time}
     */
    public Double getTime() {
        return time;
    }

    /**
     * getter for difficulty
     *
     * @return - see {@link Score#difficulty}
     */
    public ModeType getDifficulty() {
        return difficulty;
    }

    /**
     * getter for grade
     *
     * @return - see {@link Score#grade}
     */
    public String getGrade() {
        return grade;
    }

    /**
     * getter for timestamp
     *
     * @return - see {@link Score#timestamp}
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * getter for scoreid
     *
     * @return - see {@link Score#scoreid}
     */
    public Long getScoreid() {
        return scoreid;
    }

    /**
     * getter for points
     *
     * @return - see {@link Score#points}
     */
    public int getPoints() {
        return points;
    }

    /**
     * 3 difficulties, 4 grades each
     * 💀
     * can easily be changed later to anything, has no relevance to anything
     *
     * @param seconds  time in seconds
     * @param modeType difficulty
     * @return grade
     */
    private String scoreGrader(Double seconds, ModeType modeType) {

        switch (modeType.toString()) {
            case "easy" -> {
                if (seconds < 10) {
                    grade = "🎉";
                } else if (seconds >= 10 && seconds < 20) {
                    grade = "😲🤯";
                } else if (seconds >= 20 && seconds < 30) {
                    grade = "😲";
                } else if (seconds >= 30 && seconds < 40) {
                    grade = "💀";
                }
            }
            case "medium" -> {
                if (seconds < 10) {
                    grade = "Incredible!";
                } else if (seconds >= 10 && seconds < 20) {
                    grade = "Excellent";
                } else if (seconds >= 20 && seconds < 30) {
                    grade = "Well done";
                } else if (seconds >= 30 && seconds < 40) {
                    grade = "Try again";
                }
            }
            case "hard" -> {
                if (seconds < 10) {
                    grade = "John von Neumann reborn";
                } else if (seconds >= 10 && seconds < 20) {
                    grade = "Excellent";
                } else if (seconds >= 20 && seconds < 30) {
                    grade = "Well done";
                } else if (seconds >= 30 && seconds < 40) {
                    grade = "Maybe try an easier difficulty?";
                }
            }
            default -> grade = "Hämmästyttävä";
        }
        return grade;
    }

    /**
     * toString method for Score
     * @return - see {@link Score#username}, {@link Score#scoreid}, {@link Score#time}, {@link Score#difficulty}, {@link Score#timestamp}, {@link Score#grade}
     */
    @Override
    public String toString() {
        return "Score{" +
                "username='" + username + '\'' +
                ", scoreid=" + scoreid +
                ", time=" + time +
                ", difficulty=" + difficulty +
                ", timestamp=" + timestamp +
                ", grade='" + grade + '\'' +
                ", points=" + points +
                '}';
    }
}

