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
import visuals.gameModes.medium.FXMediumController;
import visuals.imageServers.ImageCache;

import java.util.ArrayList;
import java.util.Collections;

public class MediumCubeFactory implements ICubeFactory {

    private final FXMediumController gui;
    private static ArrayList<Image> imageUrlList = new ArrayList<>();
    private final Image backImage;
    private final Image behindImage;

    public MediumCubeFactory(FXAbstractGameController gui){


        this.gui = (FXMediumController) gui;
        this.backImage = ImageCache.getInstance().getMediumComp().get(0);
        this.behindImage =ImageCache.getInstance().getMediumComp().get(1);

        if(imageUrlList.isEmpty()) {imageUrlList = ImageCache.getInstance().getMediumCache();}
        Collections.shuffle(imageUrlList);
    }

    /**
     * Creates the cubes for the medium game mode and adds them to the given grid pane.
     * Adds event handlers for mouse enter and exit to scale the cubes on hover.
     * Adds a fade-in animation for each cube.
     *
     * @param gridPane the grid pane to add the cubes to
     * @param memoryObjects the list of memory objects to create cubes for
     */
    @Override
    public void createCubics(GridPane gridPane,ArrayList<MemoryObject> memoryObjects){

        for(int i = 0; i < memoryObjects.size(); i++) {
            Group group = new Group();
            group.setOpacity(0);
            int imageIndex = memoryObjects.get(i).getTypeId();
            if(imageIndex >= 0 && imageIndex < imageUrlList.size()) {
                group.getChildren().add(
                        new BoxMaker(
                                130,130,
                                imageUrlList.get(imageIndex),
                                backImage,
                                behindImage,
                                gui,i).getBox());

                group.setCursor(Cursor.HAND);

                group.setOnMouseEntered(mouseEvent -> {

                    group.setScaleX(1.03);
                    group.setScaleY(1.03);
                });

                group.setOnMouseExited(mouseEvent -> {

                    group.setScaleX(1);
                    group.setScaleY(1);
                });

                int finalI1 = i;
                Platform.runLater(() -> gridPane.add(group, finalI1 % 4, finalI1 / 4));
                GridPane.setHalignment(group, HPos.CENTER);
                GridPane.setValignment(group, VPos.CENTER);

                int finalI = i;

                Platform.runLater(() -> {
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5 + (finalI * 0.2) / 2), group);
                    fadeTransition.setFromValue(0);
                    fadeTransition.setToValue(1);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.play();
                });
            }
        }
    }
}
