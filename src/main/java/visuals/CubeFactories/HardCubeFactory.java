package visuals.CubeFactories;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.MemoryObject;
import visuals.BoxMaker;
import visuals.Gui;
import visuals.ImageServers.HardImageServer;
import visuals.ImageServers.IImageServer;
import visuals.ImageServers.ImageCache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HardCubeFactory implements ICubeFactory {

    private final Gui gui;
    private static ArrayList<Image> imageUrlList = new ArrayList<>();
    private final Image backImage;
    private final Image behindImage;

    public HardCubeFactory(Gui gui){

        this.gui = gui;
        this.backImage = ImageCache.getInstance().getHardComp().get(0);
        this.behindImage = ImageCache.getInstance().getHardComp().get(1);

        if (imageUrlList.isEmpty()) {imageUrlList = ImageCache.getInstance().getHardCache();}
        Collections.shuffle(imageUrlList);
    }
    @Override
    public void createCubics(GridPane gridPane, ArrayList<MemoryObject> memoryObjects){

        final int groupsPerRow = 4;

        for (int i = 0; i < memoryObjects.size(); i++) {
            Group group = new Group();
            group.setOpacity(0);
            int imageIndex = memoryObjects.get(i).getTypeId();
            if (imageIndex >= 0 && imageIndex < imageUrlList.size()) {
                group.getChildren().add(
                        new BoxMaker(
                                80, 80,
                                imageUrlList.get(imageIndex),
                                backImage,
                                behindImage,
                                gui, i).getBox());

                group.setCursor(Cursor.HAND);
                gridPane.add(group, i % groupsPerRow, i / groupsPerRow);
                GridPane.setHalignment(group, HPos.CENTER);
                GridPane.setValignment(group, VPos.CENTER);

                int finalI = i;
                Platform.runLater(() -> {
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5 + (finalI * 0.15) / 2), group);
                    fadeTransition.setFromValue(0);
                    fadeTransition.setToValue(1);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                });
            }
        }
    }
}
