package visuals;

import javafx.stage.Stage;
import model.MemoryObject;
import model.ModeType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface IGui {
    void start(Stage primaryStage) throws IOException;
    void clearPair(ArrayList<Integer> storage);
    void getWorldScore(ArrayList<String> worldList);

    void setPersonalScores(ArrayList<String> personalList);
    void setEasyGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;
    void setMediumGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;
    void setHardGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;
    void clearStorage();
    void gameOver();
    void setActiveID(int activeID);
    void getTime(int i);
    void fetchUserScores();
    boolean isReturnStatus();

    void compareFoundMatch();
}
