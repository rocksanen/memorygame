package model;

public class Grader {

    /**
     * The initial score before calculations
     */
    private static final int INITIAL_SCORE = 1000;

    /**
     * The minimum score that can be returned
     */
    private static final int MINIMUM_SCORE = 100;

    /**
     * this times the number of tries is subtracted from the initial score
     */
    private static final int TRIES_INCREMENT = 100;

    /**
     * Smallest possible time used in calculations
     */
    private static final double MIN_SEC_CLAMP = 3;

    /**
     * largest possible time used in calculations
     */
    private static final double MAX_SEC_CLAMP = 10.0;


    /**
     * max sec clamp uses this multiplier for the score
     */
    private static final double MIN_SCORE_MULT = 0.5;


    /**
     * min sec clamp uses this multiplier for the score
     */
    private static final double MAX_SCORE_MULT = 1.0;


    /**
     * Calculates the score based on the number of tries and the time (s)
     * uses line equation y = mx + b
     * where y is the score multiplier, x is the time in seconds
     * m = (MIN_SCORE_MULT - MAX_SCORE_MULT) / (MAX_SEC_CLAMP - MIN_SEC_CLAMP)
     *
     * @param tries  number of tries
     * @param millis time in milliseconds since last correct guess
     * @return the score
     */
    public static int calculatePoints(int tries, long millis) {

        double midScore = INITIAL_SCORE - tries * TRIES_INCREMENT;

        // calculate a line 🤓
        // y = mx + b
        // y = score multiplier
        // x = seconds
        // m = (y2 - y1) / (x2 - x1)
        // b = y1 - m * x1

        double x = Math.abs(millis / 1000.0);
        // clamp x
        x = Math.max(Math.min(x, MAX_SEC_CLAMP), MIN_SEC_CLAMP);

        double m = (MIN_SCORE_MULT - MAX_SCORE_MULT) / (MAX_SEC_CLAMP - MIN_SEC_CLAMP);
        double b = MAX_SCORE_MULT - m * MIN_SEC_CLAMP;
        double y = m * x + b;
        // clamp y
        y = Math.max(Math.min(y, MAX_SCORE_MULT), MIN_SCORE_MULT);

//        System.out.println("Mid score: " + midScore);
//        System.out.println("Multiplier: " + y);
//        System.out.println("Seconds: " + x);
//        System.out.println("m: " + m);
//        System.out.println("b: " + b);

        double finalScore = midScore * y;
        if (finalScore < MINIMUM_SCORE) {
            finalScore = MINIMUM_SCORE;
        }
        System.out.println("Final score: " + finalScore);
        // round finalscore to int value
        return (int) Math.round(finalScore);
    }


    /**
     * gives grades for points. returns maximum 4 stars. ⭐⭐⭐⭐
     *
     * @param points   points
     * @param modeType difficulty
     * @return grade
     */
    public static String scoreGrader(int points, ModeType modeType) {

        int numberOfCubes = 1;
        switch (modeType) {
            case EASY -> numberOfCubes = 6;
            case MEDIUM -> numberOfCubes = 12;
            case HARD -> numberOfCubes = 20;
        }

        int maxPossibleScore = INITIAL_SCORE * (numberOfCubes / 2);

        // numbers selected for vanity reasons
        if (points >= maxPossibleScore * 0.8) {
            return "⭐⭐⭐";
        } else if (points >= maxPossibleScore * 0.65) {
            return "⭐⭐";
        } else {
            return "⭐";
        }
    }
}