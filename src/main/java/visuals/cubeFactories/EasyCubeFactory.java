package visuals.cubeFactories;

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
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.gameModes.easy.FXEasyControllerFX;
import visuals.imageServers.ImageCache;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class EasyCubeFactory implements ICubeFactory {
    private final FXEasyControllerFX gui;
    private static ArrayList<Image> imageUrlList = new ArrayList<>();
    private final Image backImage;
    private final Image behindImage;

    public EasyCubeFactory(FXIGameController gui){

        this.gui = (FXEasyControllerFX) gui;
        this.backImage = ImageCache.getInstance().getEasyComp().get(0);
        this.behindImage = ImageCache.getInstance().getEasyComp().get(1);

        if(imageUrlList.isEmpty()) {imageUrlList = ImageCache.getInstance().getEasyCache();}
        Collections.shuffle(imageUrlList);

    }
    @Override
    public void createCubics(GridPane gridPane, ArrayList<MemoryObject> memoryObjects){

        for(int i = 0; i < memoryObjects.size(); i++) {
            Group group = new Group();
            group.setOpacity(0);
            int imageIndex = memoryObjects.get(i).getTypeId();
            if(imageIndex >= 0 && imageIndex < imageUrlList.size()) {
                group.getChildren().add(
                        new BoxMaker(
                                140,140,
                                imageUrlList.get(imageIndex),
                                backImage,
                                behindImage,
                                gui,i).getBox());
                group.setCursor(Cursor.HAND);

                group.setOnMouseEntered(mouseEvent -> {

                    group.setScaleX(1.05);
                    group.setScaleY(1.05);
                });

                group.setOnMouseExited(mouseEvent -> {

                    group.setScaleX(1);
                    group.setScaleY(1);
                });

                int finalI1 = i;
                Platform.runLater(() -> gridPane.add(group, finalI1 % 2, finalI1 / 2));
                GridPane.setHalignment(group, HPos.CENTER);
                GridPane.setValignment(group, VPos.CENTER);
                int finalI = i;


                Platform.runLater(() -> {
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.2 + (finalI * 0.2) / 2), group);
                    fadeTransition.setFromValue(0);
                    fadeTransition.setToValue(1);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                });
            }
        }
    }
}
