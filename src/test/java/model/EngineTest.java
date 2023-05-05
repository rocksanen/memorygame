package model;

import controller.IGameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import visuals.menu.IMenu;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class EngineTest {

    private Engine e;


    @BeforeEach
    void setUp() {
        this.e = new Engine(ModeType.EASY, null);
    }

    @Test
    @DisplayName("Test adding memory objects to a list")
    void addMemoryObjectsToList() {
        e.addMemoryObjectsToList(8);
        ArrayList<MemoryObject> expectedObjects = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            expectedObjects.add(new MemoryObject(i, 2));
        }
        assertEquals(expectedObjects.size(), e.getMemoryObjectsList().size());
    }

    @Test
    @DisplayName("Test Shuffle")
    void suffleObjects() {
        e.addMemoryObjectsToList(8);
        ArrayList<MemoryObject> test = new ArrayList<>(e.getMemoryObjectsList());
        e.suffleObjects();
        ArrayList<MemoryObject> test2 = new ArrayList<>(e.getMemoryObjectsList());

        assertEquals(test.size(), test2.size());
        assertNotEquals(test, test2);
    }


    @Test
    @DisplayName("Count wrong tries")
    void testMaxTries() {
        e.incorrectTries = 3;
        e.updateScore(CompareResultType.NOTEQUAL);
        assertEquals(4, e.incorrectTries);
    }



    @Test
    @DisplayName("Test update score on wrong guess")
    void updateWrongScore() {

        e.updateScore(CompareResultType.NOTEQUAL);
        assertEquals(0, e.getTotalScore());
        assertEquals(1, e.incorrectTries);
    }

}