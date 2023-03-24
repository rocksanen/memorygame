package model;

import controller.GameController;
import controller.IGameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import visuals.menu.IMenu;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
public class EngineTest {

    private Engine e;
    IMenu ui;
    private IGameController controller;


    @BeforeEach
    void setUp() {
        //controller = new GameController(ui);
        e = new Engine(ModeType.EASY, controller);
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
    @DisplayName("Test addToComparing")
    void addToComparing() {
        e.addMemoryObjectsToList(8);
        e.addToComparing(0);
        e.addToComparing(2);
        assertEquals(0, e.getComparingList().size());
        //Wrong pair
        assertEquals(2, e.storage.size());

        e.addToComparing(1);
        //After pair
        assertEquals(1, e.getComparingList().size());

        e.addToComparing(0);

        //Right pair
        assertEquals(0, e.storage.size());
    }

    @Test
    @DisplayName("Count wrong tries")
    void testMaxTries() {
        e.incorrectTries = 3;
        //+1
        e.updateScore(CompareResultType.NOTEQUAL);
        //assertEquals(600, e.getNextScore());
        assertEquals(4, e.incorrectTries);
    }

    @Test
    @DisplayName("Test update score on right guess")
    void updateCorrectScore() {
        e.updateScore(CompareResultType.EQUAL);
        assertEquals(1000, e.getTotalScore());
        //assertEquals(1000, e.getNextScore());
        assertEquals(0, e.incorrectTries);

    }

    @Test
    @DisplayName("Test update score on wrong guess")
    void updateWrongScore() {

        e.updateScore(CompareResultType.NOTEQUAL);
        assertEquals(0, e.getTotalScore());
        //assertEquals(900, e.getNextScore());
        assertEquals(1, e.incorrectTries);
    }

}