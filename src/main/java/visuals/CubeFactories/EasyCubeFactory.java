package visuals.CubeFactories;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import model.MemoryObject;
import visuals.*;
import visuals.ImageServers.EasyImageServer;
import visuals.ImageServers.IImageServer;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EasyCubeFactory implements ICubeFactory {
    private final Gui gui;
    private ArrayList<String> imageUrlList;

    public EasyCubeFactory(Gui gui) {

        this.gui = gui;
        this.imageUrlList = new ArrayList<>();
    }
    @Override
    public void createCubics(GridPane gridPane, ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        IImageServer easyImages = new EasyImageServer();

        if(imageUrlList != null) {imageUrlList.clear();}
        imageUrlList = easyImages.getImageUrl();

        for(int i = 0; i < memoryObjects.size(); i++) {
            Group group = new Group();
            int imageIndex = memoryObjects.get(i).getTypeId();
            if(imageIndex >= 0 && imageIndex < imageUrlList.size()) {
                group.getChildren().add(
                        new BoxMaker(
                                140,140,140,0,0,100,
                                imageUrlList.get(imageIndex),memoryObjects.get(i).getTypeId(),gui,i).
                                getBox());
                gridPane.add(group, i % 2, i / 2);
                GridPane.setHalignment(group, HPos.CENTER);
                GridPane.setValignment(group, VPos.CENTER);
            }
        }
    }
}
