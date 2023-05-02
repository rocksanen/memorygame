package visuals.cubeFactories;


import javafx.scene.layout.GridPane;
import model.MemoryObject;

import java.util.ArrayList;

public interface ICubeFactory {

    /**
     * Creates cubics from an ArrayList of MemoryObjects and adds them to a GridPane.
     * @param gridPane the GridPane where the cubics will be added
     * @param memoryObjects the ArrayList of MemoryObjects to create cubics from
     */
    void createCubics(GridPane gridPane, ArrayList<MemoryObject> memoryObjects);

}
