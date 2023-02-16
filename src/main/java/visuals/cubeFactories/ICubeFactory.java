package visuals.cubeFactories;

import javafx.scene.layout.GridPane;
import model.MemoryObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ICubeFactory {


    void createCubics(GridPane gridPane, ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;

}
