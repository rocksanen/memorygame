package visuals.gameModes;

import controller.GameController;
import controller.IGameController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import visuals.Navigaattori;
import visuals.cubeFactories.BoxMaker;
import visuals.internationalization.JavaFXInternationalization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public abstract class FXAbstractGameController implements FXIGameController {

    protected ArrayList<BoxMaker> cubeList;
    protected final IGameController gameController = new GameController(this);
    private static final ArrayList<Group> activeList = new ArrayList<>();

    public FXAbstractGameController() {
    }

    @Override
    public void addToCubeList(BoxMaker cube) {
        cubeList.add(cube);
    }

    @Override
    public void clearPair(ArrayList<Integer> storage) {

        int firstIndex = storage.get(0);
        int secondIndex = storage.get(1);

        cubeList.get(firstIndex).resetImage();
        cubeList.get(secondIndex).resetImage();
        clearStorage();

        CompletableFuture.runAsync(() -> {

            try {

                Thread.sleep(700);
                cubeList.get(firstIndex).setActive();
                cubeList.get(secondIndex).setActive();
                cubeList.get(firstIndex).getBox().setMouseTransparent(false);
                cubeList.get(secondIndex).getBox().setMouseTransparent(false);

                for (BoxMaker cube : cubeList) {

                    if (!cube.getActiveState()) {
                        cube.getBox().setMouseTransparent(false);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void clearStorage() {
        gameController.clearStorage();
    }

    @Override
    public void setActiveID(int activeID) {

        if (!cubeList.get(activeID).getActiveState()) {

            cubeList.get(activeID).getBox().setMouseTransparent(true);
            cubeList.get(activeID).setActive();

            if (activeList.size() < 2) {
                activeList.add(cubeList.get(activeID).getBox());
            }
        }

        if (activeList.size() == 2) {
            for (BoxMaker cube : cubeList) {
                if (!cube.getActiveState()) {
                    cube.getBox().setMouseTransparent(true);
                }
            }
            activeList.clear();
        }
    }

    @Override
    public void compareFoundMatch() {

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
                for (BoxMaker cube : cubeList) {
                    if (!cube.getActiveState()) {
                        cube.getBox().setMouseTransparent(false);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void getTime(int i) {

        //System.out.println(i);
    }

    @Override
    public void sendIdToEngine(int id) {
        gameController.sendIdToEngine(id);
    }

    @Override
    public void setCamera() {

        Navigaattori.camera.setFieldOfView(1);
        Navigaattori.camera.setTranslateZ(0);
        Navigaattori.camera.setTranslateY(0);
        Navigaattori.camera.setTranslateX(0);
    }

    public void clearGameOverMenu(AnchorPane sceneRoot, AnchorPane gameRoot) {
        // delete game over -view if it exists
        System.out.println(sceneRoot.getChildren());
        if (sceneRoot.getChildren().size() > 1) {
            sceneRoot.getChildren().remove(1);
        }
        // remove effects from gameroot
        gameRoot.setEffect(null);
    }

    public void gameOverMenu(AnchorPane gameRoot, AnchorPane sceneRoot) {
        try {
            ResourceBundle bundle = JavaFXInternationalization.internationalizationLoaderProperties();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/visuals/gameModes/GameOver.fxml"), bundle);
            AnchorPane gameOverView = loader.load();

            GaussianBlur gaussianBlur = new GaussianBlur();
            gaussianBlur.setRadius(10);
            gameRoot.setEffect(gaussianBlur);

            GameOverController goc = loader.getController();
            goc.Initialize(this, gameController);
            gameOverView.setOpacity(0.0);
            sceneRoot.getChildren().add(gameOverView);

            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(1), gameOverView);
            fadeTransition2.setFromValue(0.0);
            fadeTransition2.setToValue(1.0);
            fadeTransition2.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
