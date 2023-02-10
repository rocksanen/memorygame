package visuals.CubeFactories;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import model.MemoryObject;
import visuals.*;
import visuals.ImageServers.HardImageServer;
import visuals.ImageServers.IImageServer;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HardCubeFactory implements ICubeFactory {

    private final Gui gui;
    private ArrayList<String> imageUrlList;

    public HardCubeFactory(Gui gui) {

        this.gui = gui;
        this.imageUrlList = new ArrayList<>();

    }
    @Override
    public void createCubics(GridPane gridPane, ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        IImageServer mediumImages = new HardImageServer();

        if (imageUrlList != null) {
            imageUrlList.clear();
        }
        imageUrlList = mediumImages.getImageUrl();

        int groupsPerRow = 4;

        for (int i = 0; i < memoryObjects.size(); i++) {
            Group group = new Group();
            int imageIndex = memoryObjects.get(i).getTypeId();
            if (imageIndex >= 0 && imageIndex < imageUrlList.size()) {
                group.getChildren().add(
                        new BoxMaker(
                                80, 80, 80, 0, 0, 90,
                                imageUrlList.get(imageIndex), memoryObjects.get(i).getTypeId(), gui, i)
                                .getBox());
                group.setCursor(Cursor.HAND);
                gridPane.add(group, i % groupsPerRow, i / groupsPerRow);
                GridPane.setHalignment(group, HPos.CENTER);
                GridPane.setValignment(group, VPos.CENTER);
            }
        }
    }
}
