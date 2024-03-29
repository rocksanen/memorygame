package visuals.gameModes.easy;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.*;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.effects.gameEffects.EasyEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static model.ModeType.EASY;


/**
 * A controller class that manages the gameplay for the easy level in a memory match game.
 * Extends {@link FXAbstractGameController} and implements {@link Initializable} and {@link FXIGameController}.
 */
public class FXEasyController extends FXAbstractGameController implements Initializable, FXIGameController {

    @FXML
    GridPane easyGridi;
    @FXML
    ImageView background;
    @FXML
    ImageView easyTop;
    @FXML
    ImageView easyL;
    @FXML
    ImageView easyBot;
    @FXML
    ImageView play;
    @FXML
    ImageView returngame;
    @FXML
    Pane scorePane;
    @FXML
    Label p1;
    @FXML
    Label p2;
    @FXML
    Label p3;
    @FXML
    Label p4;
    @FXML
    Label p5;
    @FXML
    Label w1;
    @FXML
    Label w2;
    @FXML
    Label w3;
    @FXML
    Label w4;
    @FXML
    Label w5;
    @FXML
    ImageView easy3Dgrid;
    @FXML
    ImageView easyneo;
    @FXML
    ImageView easyEnd;
    @FXML
    AnchorPane sceneRoot;
    @FXML
    AnchorPane gameRoot;
    @FXML
    ImageView personalScoreHeader;
    @FXML
    ImageView worldScoreHeader;

    private List<Label> personalLabels;
    private List<Label> worldLabels;
    private ICubeFactory easyCubeFactory;
    private EasyEffects easyEffects;


    /**
     * Initializes the controller with the specified URL and resource bundle.
     *
     * @param url the URL of the FXML file
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCamera();
        setImages();
        easyEffects = new EasyEffects(this);
        easyEffects.setImagesAndComponents(easyTop, easyBot, easyL, easy3Dgrid, play, returngame, easyGridi, easyEnd, easyneo, scorePane);
        easyEffects.entrance();
        
        initScoreHeaders(personalScoreHeader, worldScoreHeader);
        this.personalLabels = List.of(p1, p2, p3, p4, p5);
        this.worldLabels = List.of(w1, w2, w3, w4, w5);

        setPersonalScore(EASY, personalLabels);
        setWorldScore(EASY, worldLabels);

        Stream.concat(personalLabels.stream(), worldLabels.stream())
                .forEach(label -> label.setStyle("-fx-font: 14 \"Atari Classic\";"));

        dynamicHeader.setFont(Font.font("Atari Classic", 26));
        dynamicHeader.setText("SCORE");
        dynamicScore.setFont(Font.font("Atari Classic", 26));
        dynamicScore.setText("0000");
    }

    /**

     Sets the images for the game graphics.
     */
    @Override
    public void setImages() {

        dynamicScorePane.setVisible(false);
        background.setImage(ImageCache.getInstance().getGameBackGroundCache().get(0));
        easyTop.setImage(ImageCache.getInstance().getGameBackGroundCache().get(7));
        easyTop.setOpacity(0);
        easyBot.setImage(ImageCache.getInstance().getGameBackGroundCache().get(8));
        easyBot.setOpacity(0);
        easyL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(9));
        easyL.setOpacity(0);
        easy3Dgrid.setImage(ImageCache.getInstance().getGameBackGroundCache().get(24));
        easy3Dgrid.setOpacity(0);
        play.setImage(ImageCache.getInstance().getGameBackGroundCache().get(14));
        play.setOpacity(0);
        returngame.setImage(ImageCache.getInstance().getGameBackGroundCache().get(15));
        returngame.setOpacity(0);
        easyGridi.setHgap(-80);
        easyEnd.setOpacity(0);
    }

    /**

     Sets up the game to start.
     */
    @Override
    public void setStartGame() {

        wallOfeetu.setMouseTransparent(false);

        if (cubeList != null) {
            cubeList.clear();
        }

        dynamicScore.textProperty().unbind();
        dynamicScore.setText("0000");
        cubeList = new ArrayList<>();
        easyGridi.getChildren().clear();
        easyCubeFactory = new EasyCubeFactory(this);
        gameController.startGame(EASY);
        countDown(EASY);
    }

    /**

     Sets the cubes to the game board.
     @param memoryObjects The cubes to be added to the game board.
     */
    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) {

        easyCubeFactory.createCubics(easyGridi, memoryObjects);
    }

    /**

     Starts a new game.
     */
    @FXML
    public void newGame() {
        clearGameOverMenu(sceneRoot, gameRoot);
        setStartGame();
    }

    /**

     Returns to the game menu.
     */
    @FXML
    public void returnMenu() {

        wallOfeetu.setMouseTransparent(false);
        Platform.runLater(() -> dynamicScorePane.setVisible(false));
        Platform.runLater(() -> easyEffects.wallsOff());
    }

    /**

     Adds a cube to the list of cubes.
     @param cube The cube to be added to the list.
     */
    @Override
    public void addToCubeList(BoxMaker cube) {
        super.addToCubeList(cube);
    }

    /**

     Clears the stored pairs of cubes.
     @param storage The list of stored cube pairs to be cleared.
     */
    @Override
    public void clearPair(ArrayList<Integer> storage) {
        super.clearPair(storage);
    }

    /**

     Clears the storage of cubes.
     */
    @Override
    public void clearStorage() {
        super.clearStorage();
    }

    /**

     Shows the game over menu.
     @param victory Whether or not the player won the game.
     */
    @Override
    public void gameOver(boolean victory) {
        setPersonalScore(EASY, personalLabels);
        setWorldScore(EASY, worldLabels);
        gameOverMenu(gameRoot, sceneRoot, victory);
    }

    /**

     Sets the active ID for the current game session.
     @param activeID the ID of the active game session
     */
    @Override
    public void setActiveID(int activeID) {
        super.setActiveID(activeID);
    }

    /**

     Compares the currently selected pairs of objects and updates the game state accordingly.
     */
    @Override
    public void compareFoundMatch() {
        super.compareFoundMatch();
    }


    /**

     Sends the ID of the selected object to the game engine.
     @param id the ID of the selected object
     */
    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }

    /**

     Updates the dynamic score based on the current score value.
     @param score the current score value
     */
    @Override
    public void updateDynamicScore(int score) {

        super.updateDynamicScore(score);
    }

    /**

     Starts the countdown for the specified game mode.
     @param mode the game mode for which to start the countdown
     */
    @Override
    public void countDown(ModeType mode) {

        super.countDown(mode);
    }
}
