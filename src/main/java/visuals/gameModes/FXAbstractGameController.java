package visuals.gameModes;

import controller.GameController;
import controller.IGameController;
import controller.ScoreController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.ModeType;
import visuals.Navigaattori;
import visuals.cubeFactories.BoxMaker;
import visuals.internationalization.JavaFXInternationalization;

import java.io.IOException;
import java.util.*;
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


    /**
     * Method for clearing the game over menu
     * @param sceneRoot scene root
     * @param gameRoot game root
     */
    public void clearGameOverMenu(AnchorPane sceneRoot, AnchorPane gameRoot) {
        // delete game over -view if it exists
        System.out.println(sceneRoot.getChildren());
        if (sceneRoot.getChildren().size() > 1) {
            sceneRoot.getChildren().remove(1);
        }
        // remove effects from gameroot
        gameRoot.setEffect(null);
    }


    /**
     * Method for initializing the game over menu
     * @param gameRoot game root
     * @param sceneRoot scene root
     */
    public void gameOverMenu(AnchorPane gameRoot, AnchorPane sceneRoot) {
        try {
            ResourceBundle bundle = JavaFXInternationalization.internationalizationLoaderProperties();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameOver.fxml"), bundle);
            AnchorPane gameOverView = loader.load();

            GameOverController goc = loader.getController();
            goc.Initialize(this, gameController, gameRoot);
            gameOverView.setOpacity(0.0);
            sceneRoot.getChildren().add(gameOverView);

            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), gameOverView);
            fadeTransition2.setFromValue(0.0);
            fadeTransition2.setToValue(1.0);
            fadeTransition2.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for initializing the score headers based on the language
     * @param personalScoreHeader personal score header
     * @param worldScoreHeader world score header
     */
    public void initScoreHeaders(ImageView personalScoreHeader, ImageView worldScoreHeader) {
        Locale locale = JavaFXInternationalization.getLocale();
        System.out.println("locale is : " + locale.getLanguage());

        switch (locale.getLanguage()) {
            case "fi" -> {
                personalScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/fi_personalscores.png"))));
                worldScoreHeader.setImage(
                        new Image("/images/headers/fi_worldscores.png"));
            }
            case "swe" -> {
                personalScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/swe_personalscores.png"))));
                worldScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/swe_worldscores.png"))));
            }
            case "lat" -> {
                personalScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/latvian_personalscores.png"))));
                worldScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/latvian_worldscores.png"))));
            }
        }
    }

    /**
     * Assigns world scores to the labels
     *
     * @param modeType difficulty
     * @param labels   labels to be used
     */
    public void setWorldScore(ModeType modeType, List<Label> labels) {
        ScoreController scoreController = new ScoreController();
        ArrayList<String> worldScores = scoreController.getTopFiveScores(modeType);

        for (Label l : labels) {
            l.setText(worldScores.get(labels.indexOf(l)));
        }
    }


    /**
     * Assigns personal scores to the labels
     *
     * @param modeType difficulty
     * @param labels   labels to be used
     */
    public void setPersonalScore(ModeType modeType, List<Label> labels) {
        ScoreController scoreController = new ScoreController();
        ArrayList<String> personalScores = scoreController.getTopFivePersonalScores(modeType);

        for (Label l : labels) {
            l.setText(personalScores.get(labels.indexOf(l)));
        }
    }
}
