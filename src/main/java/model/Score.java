package model;

import database.entity.Leaderboard;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Score class for the game
 * Score-object contains all the information about a single score
 * it also grades the score based on the time and difficulty
 *
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
        this.scoreid = lb.getScoreid();
        this.points = lb.getPoints();
        this.grade = Grader.scoreGrader(points, difficulty);
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
        if (timestamp.toString().length() > 26) {
            return null;
        }
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
     * toString method for Score
     *
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

