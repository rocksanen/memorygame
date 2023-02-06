package visuals;

import javafx.stage.Stage;
import model.MemoryObject;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public interface IGui {
    void start(Stage primaryStage) throws IOException;
    void setTypeToLabel(int id, int typeID);
    void clearTable();
    void clearPair(ArrayList<MemoryObject> memoryList);

    void getWorldScore(ArrayList<String> worldList);
    void setPersonalScores(ArrayList<String> personalList);

    void setWorldScore();

}
