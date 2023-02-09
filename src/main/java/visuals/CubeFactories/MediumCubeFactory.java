package visuals.CubeFactories;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.MemoryObject;
import visuals.BoxMaker;
import visuals.Gui;
import visuals.ImageServers.IImageServer;
import visuals.ImageServers.MediumImageServer;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MediumCubeFactory implements ICubeFactory {

    private final Gui gui;
    private ArrayList<String> imageUrlList;

    public MediumCubeFactory(Gui gui) {

        this.gui = gui;
        this.imageUrlList = new ArrayList<>();

    }

    @Override
    public void createCubics(GridPane gridPane,ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        IImageServer mediumImages = new MediumImageServer();

        if(imageUrlList != null) {imageUrlList.clear();}
        imageUrlList = mediumImages.getImageUrl();

        for(int i = 0; i < memoryObjects.size(); i++) {
            Group group = new Group();
            int imageIndex = memoryObjects.get(i).getTypeId();
            if(imageIndex >= 0 && imageIndex < imageUrlList.size()) {
                group.getChildren().add(
                        new BoxMaker(
                                120,120,120,0,0,100,
                                imageUrlList.get(imageIndex),memoryObjects.get(i).getTypeId(),gui,i).
                                getBox());
                gridPane.add(group, i % 4, i / 4);
                GridPane.setHalignment(group, HPos.CENTER);
                GridPane.setValignment(group, VPos.CENTER);
            }
        }

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(28.5);
        gridPane.getColumnConstraints().addAll(columnConstraints);
    }
}
