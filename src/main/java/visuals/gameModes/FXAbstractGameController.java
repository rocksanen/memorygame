package visuals.gameModes;

import controller.GameController;
import controller.IGameController;
import controller.ScoreController;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;
import visuals.Navigaattori;
import visuals.cubeFactories.BoxMaker;
import visuals.internationalization.ImageTranslator;
import visuals.internationalization.JavaFXInternationalization;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public abstract class FXAbstractGameController implements FXIGameController {

    protected ArrayList<BoxMaker> cubeList;
    protected final IGameController gameController = new GameController(this);
    private static final ArrayList<Group> activeList = new ArrayList<>();
    private final ImageTranslator imageTranslator = new ImageTranslator();



    @FXML
    public Label dynamicScore;
    @FXML
    public Label dynamicHeader;

    @FXML public ImageView three;
    @FXML public ImageView two;
    @FXML public ImageView one;
    @FXML public Pane numberPane;
    @FXML public AnchorPane wallOfeetu;
    @FXML public Pane dynamicScorePane;
    @FXML public Pane timerPane;
    @FXML public ImageView play;
    public boolean practice = false;



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
        cubeList.get(firstIndex).setActive();
        cubeList.get(secondIndex).setActive();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.millis(850))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            for (BoxMaker cube : cubeList) {

                if (!cube.getActiveState()) {
                    cube.getBox().setMouseTransparent(false);
                }
            }
        });
    }

    /**
     * Visualizes the hint during practice mode
     * @param idToGlow - cube to visualize
     */
    @Override
    public void glowHint(int idToGlow) {

        CompletableFuture.runAsync(() -> {

            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), cubeList.get(idToGlow).getBox());
            fadeTransition.setFromValue(0.7);
            fadeTransition.setToValue(1.0);
            fadeTransition.setCycleCount(3);

            fadeTransition.play();
        });
    }

    /**

     Clears the storage used by the game controller.
     */
    @Override
    public void clearStorage() {
        gameController.clearStorage();
    }

    /**

     Sets the active ID for a BoxMaker object in the game, and adds it to the active list if less than two objects are currently active.

     @param activeID the ID of the BoxMaker object to be made active.
     */
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

    /**

     Compares the currently active BoxMaker objects in the game for a match.

     */
    @Override
    public void compareFoundMatch() {


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.millis(800))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            for (BoxMaker cube : cubeList) {
                if (!cube.getActiveState()) {
                    cube.getBox().setMouseTransparent(false);
                }
            }
        });

    }

    /**

     Override method to get the current time.
     @param i An integer representing the current time.
     */
    @Override
    public void getTime(int i) {
    }

    /**

     Override method to send an ID to the game engine.
     @param id An integer representing the ID to be sent to the game engine.
     */
    @Override
    public void sendIdToEngine(int id) {
        gameController.sendIdToEngine(id);
    }

    /**

     Override method to set the camera's position and field of view.
     */
    @Override
    public void setCamera() {

        Navigaattori.camera.setFieldOfView(1);
        Navigaattori.camera.setTranslateZ(0);
        Navigaattori.camera.setTranslateY(0);
        Navigaattori.camera.setTranslateX(0);
    }


    /**
     * Method for clearing the game over menu
     *
     * @param sceneRoot scene root
     * @param gameRoot  game root
     */
    public void clearGameOverMenu(AnchorPane sceneRoot, AnchorPane gameRoot) {
        // delete game over -view if it exists
        if (sceneRoot.getChildren().size() > 1) {
            sceneRoot.getChildren().remove(1);
        }
        // remove effects from gameroot
        gameRoot.setEffect(null);

    }


    /**
     * Method for initializing the game over menu
     *
     * @param gameRoot  game root
     * @param sceneRoot scene root
     */
    public void gameOverMenu(AnchorPane gameRoot, AnchorPane sceneRoot, boolean victory)  {
        try {
            gameController.killTimer();
            ResourceBundle bundle = JavaFXInternationalization.internationalizationLoaderProperties();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameOver.fxml"), bundle);
            AnchorPane gameOverView = loader.load();
            GameOverController goc = loader.getController();
            gameOverView.setOpacity(0.0);

            // if game is ended by a timeout, don't wait for animations to end
            double transitionTime = victory ? 0.6 : 0;
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(transitionTime));
            pauseTransition.setOnFinished(event -> {
                goc.Initialize(this, gameController, gameRoot, victory);
                Platform.runLater(() -> sceneRoot.getChildren().add(gameOverView));

                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), gameOverView);
                fadeTransition2.setFromValue(0.0);
                fadeTransition2.setToValue(1.0);
                fadeTransition2.play();
            });
            pauseTransition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for initializing the score headers based on the language
     *
     * @param personalScoreHeader personal score header
     * @param worldScoreHeader    world score header
     */
    public void initScoreHeaders(ImageView personalScoreHeader, ImageView worldScoreHeader) {

        imageTranslator.inGameTranslator(personalScoreHeader,worldScoreHeader);
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


    /**

     This method sets a Glow effect on a node when it is hovered over by the mouse
     @param event the MouseEvent that triggered the method
     */
    public void hoverOn(javafx.scene.input.MouseEvent event) {

        Glow glow = new Glow();
        glow.setLevel(0.3);
        Node source = (Node) event.getSource();
        source.setEffect(glow);
    }

    /**

     This method removes the Glow effect from a node when the mouse is no longer hovering over it
     @param event the MouseEvent that triggered the method
     */
    public void hoverOff(javafx.scene.input.MouseEvent event) {

        Node source = (Node) event.getSource();
        source.setEffect(null);
    }

    /**

     This method updates the dynamic score on the user interface with a smooth animation
     @param score the score value to update the dynamic score to
     */
    public void updateDynamicScore(int score) {

        IntegerProperty base = new SimpleIntegerProperty(Integer.parseInt(dynamicScore.getText()));
        dynamicScore.textProperty().bind(base.asString("%04d"));

        int fromValue = base.get();
        Duration duration = Duration.millis((score - fromValue) * 1.5);

        KeyValue keyValue = new KeyValue(base, score, Interpolator.LINEAR);
        KeyFrame keyFrame = new KeyFrame(duration, keyValue);
        Timeline timeline = new Timeline(keyFrame);

        timeline.play();

    }

    /**

     This method initiates a countdown animation with changing numbers and fades for the numbers and score
     @param mode the mode of the game that is being played
     */
    public void countDown(ModeType mode) {


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(three.opacityProperty(),0),
                        new KeyValue(three.scaleXProperty(),1),
                        new KeyValue(three.scaleYProperty(),1),
                        new KeyValue(numberPane.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(1.6),
                        new KeyValue(three.opacityProperty(),1),
                        new KeyValue(two.opacityProperty(),0),
                        new KeyValue(two.scaleXProperty(),1),
                        new KeyValue(two.scaleYProperty(),1)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(three.opacityProperty(),0),
                        new KeyValue(three.scaleXProperty(),1.2),
                        new KeyValue(three.scaleYProperty(),1.2)),
                new KeyFrame(Duration.seconds(2.2),
                        new KeyValue(two.opacityProperty(),1),
                        new KeyValue(one.opacityProperty(),0),
                        new KeyValue(one.scaleXProperty(),1),
                        new KeyValue(one.scaleYProperty(),1)),
                new KeyFrame(Duration.seconds(2.6),
                        new KeyValue(two.opacityProperty(),0),
                        new KeyValue(two.scaleXProperty(),1.2),
                        new KeyValue(two.scaleYProperty(),1.2)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(one.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(3.2),
                        new KeyValue(numberPane.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(3.4),
                        new KeyValue(dynamicScorePane.visibleProperty(),true),
                        new KeyValue(wallOfeetu.mouseTransparentProperty(),true),
                        new KeyValue(one.opacityProperty(),0),
                        new KeyValue(one.scaleXProperty(),1.2),
                        new KeyValue(one.scaleYProperty(),1.2),
                        new KeyValue(numberPane.opacityProperty(),0))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            if(!mode.equals(ModeType.EASY) ) {
                timerPane.setVisible(true);

                if(!practice) {

                    gameController.startTime();

                }
            }
        });
    }

}
