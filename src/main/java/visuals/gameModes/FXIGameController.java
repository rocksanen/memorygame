package visuals.gameModes;

import model.MemoryObject;
import visuals.cubeFactories.BoxMaker;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface FXIGameController {


    void addToCubeList(BoxMaker cube);
    void clearPair(ArrayList<Integer> storage);
    void clearStorage();
    void setEasyGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;
    void setMediumGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;
    void setHardGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;
    void gameOver(); // kesken
    void setActiveID(int activeID);
    void compareFoundMatch();
    void getTime(int i);
    void setStartEasyGame();
    void sendIdToEngine(int id);
    void setWorldScore();
    void setPersonalScore();
    void newGame();
    void returnMenu();
    void setCamera();
    void setImages();
}
