package model;

public class WorldScores {

    // make this a singleton class containing Scoreboard objects for each difficulty

    private Scoreboard easyScores;
    private Scoreboard mediumScores;
    private Scoreboard hardScores;

    //singleton
    private static WorldScores instance;

    private WorldScores() {
        easyScores = new Scoreboard();
        mediumScores = new Scoreboard();
        hardScores = new Scoreboard();
    }

    public static WorldScores getInstance() {
        if (instance == null) {
            instance = new WorldScores();
        }
        return instance;
    }

    public Scoreboard getEasyScores() {
        return easyScores;
    }

    public Scoreboard getMediumScores() {
        return mediumScores;
    }

    public Scoreboard getHardScores() {
        return hardScores;
    }

}
