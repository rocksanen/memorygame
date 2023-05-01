package visuals.cubeFactories;


import javafx.scene.layout.GridPane;
import model.MemoryObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ICubeFactory {

    /**
     * Creates cubics from an ArrayList of MemoryObjects and adds them to a GridPane.
     * @param gridPane the GridPane where the cubics will be added
     * @param memoryObjects the ArrayList of MemoryObjects to create cubics from
     * @throws FileNotFoundException if the file containing the texture for the cubics cannot be found
     */
    void createCubics(GridPane gridPane, ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException;

}
