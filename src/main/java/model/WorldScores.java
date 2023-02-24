package model;

/**
 * Singleton class for the WorldScores
 * Contains 3 scoreboards, one for each difficulty
 * @author Eetu Soronen
 * @version 1
 */
public class WorldScores {

    /**
     * Scoreboard for easy difficulty
     */
    private final Scoreboard easyScores;

    /**
     * Scoreboard for medium difficulty
     */
    private final Scoreboard mediumScores;

    /**
     * Scoreboard for hard difficulty
     */
    private final Scoreboard hardScores;

    /**
     * Singleton instance of the WorldScores class
     */
    private static WorldScores instance;

    /**
     * Private constructor for the WorldScores class
     */
    private WorldScores() {
        easyScores = new Scoreboard();
        mediumScores = new Scoreboard();
        hardScores = new Scoreboard();
    }


    /**
     * Returns the singleton instance of the WorldScores class
     * @return WorldScores instance
     */
    public static WorldScores getInstance() {
        if (instance == null) {
            instance = new WorldScores();
        }
        return instance;
    }

    /**
     * getter for the easyScores
     * @return - see {@link #easyScores}
     */
    public Scoreboard getEasyScores() {
        return easyScores;
    }

    /**
     * getter for the mediumScores
     * @return - see {@link #mediumScores}
     */
    public Scoreboard getMediumScores() {
        return mediumScores;
    }

    /**
     * getter for the hardScores
     * @return - see {@link #hardScores}
     */
    public Scoreboard getHardScores() {
        return hardScores;
    }
}
