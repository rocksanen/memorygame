package visuals.gameModes;

import model.MemoryObject;
import model.ModeType;
import visuals.cubeFactories.BoxMaker;

import java.util.ArrayList;

public interface FXIGameController {

    void addToCubeList(BoxMaker cube);
    void clearPair(ArrayList<Integer> storage);
    void clearStorage();
    void setCubesToGame(ArrayList<MemoryObject> memoryObjects);

    void gameOver(boolean victory);

    void setActiveID(int activeID);
    void compareFoundMatch();
    void getTime(int i);
    void setStartGame();
    void sendIdToEngine(int id);
    void newGame();
    void returnMenu();
    void setCamera();
    void setImages();
    void glowHint(int idToGlow);
    void updateDynamicScore(int score);
    void countDown(ModeType mode);
}
