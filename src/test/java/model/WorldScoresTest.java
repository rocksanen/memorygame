package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorldScoresTest {

    private WorldScores worldScores;

    @BeforeEach
    public void setUp() {
        worldScores = WorldScores.getInstance();
    }

    @Test
    public void testGetInstance() {
        WorldScores expectedInstance = WorldScores.getInstance();
        assertSame(expectedInstance, worldScores, "getInstance should return the same instance");
    }

    @Test
    public void testGetEasyScores() {
        Scoreboard easyScores = worldScores.getEasyScores();
        assertNotNull(easyScores, "easyScores should not be null");
    }

    @Test
    public void testGetMediumScores() {
        Scoreboard mediumScores = worldScores.getMediumScores();
        assertNotNull(mediumScores, "mediumScores should not be null");
    }

    @Test
    public void testGetHardScores() {
        Scoreboard hardScores = worldScores.getHardScores();
        assertNotNull(hardScores, "hardScores should not be null");
    }
}
