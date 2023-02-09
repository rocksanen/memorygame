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

    public HardCubeFactory(Gui gui) {

        this.gui = gui;

    }
    @Override
    public void createCubics(GridPane gridPane,ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        IImageServer hardImages = new HardImageServer();
        ArrayList<String> imageUrlList = hardImages.getImageUrl();

        ArrayList<String> chosenImages = new ArrayList<>();
        for(int i = 0; i < 10; i++){

            chosenImages.add(imageUrlList.get(i));
            chosenImages.add(imageUrlList.get(i));
        }

        for(String url: chosenImages) {

            System.out.println(url);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;
                if (index >= 20) {
                    break;
                }
                Group group = new Group();
                group.getChildren().add(new BoxMaker(80, 80, 80, 0, 0, 100,chosenImages.get(index),memoryObjects.get(index).getIdNumber(),gui,i).getBox());
                group.setCursor(Cursor.HAND);
                gridPane.add(group, j, i);
                GridPane.setHalignment(group, HPos.CENTER);
                GridPane.setValignment(group, VPos.CENTER);
            }
        }

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(21.5);
        gridPane.getColumnConstraints().addAll(columnConstraints,columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(23);
        gridPane.getRowConstraints().addAll(rowConstraints);

    }
}
