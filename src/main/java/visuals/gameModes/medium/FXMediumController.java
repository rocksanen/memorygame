package visuals.gameModes.medium;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.MemoryObject;
import model.ModeType;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.ICubeFactory;
import visuals.cubeFactories.MediumCubeFactory;
import visuals.effects.gameEffects.MediumEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class FXMediumController extends FXAbstractGameController implements Initializable, FXIGameController {


    @FXML
    ImageView mediumBackground;
    @FXML
    ImageView mediumSpread;
    @FXML
    ImageView midgrid;
    @FXML
    ImageView midR;
    @FXML
    ImageView midTop;
    @FXML
    ImageView midL;
    @FXML
    ImageView midBot;
    @FXML
    ImageView midend;
    @FXML
    ImageView midneo;


    @FXML
    ImageView returngame;
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
    GridPane mediumGrid;
    @FXML
    Pane scorePane;
    @FXML
    AnchorPane gameRoot;
    @FXML
    AnchorPane sceneRoot;
    @FXML
    ImageView personalScoreHeader;
    @FXML
    ImageView worldScoreHeader;
    @FXML ImageView timeBar;

    @FXML Pane dynamicScorePane;
    @FXML AnchorPane wallOfeetu;



    private List<Label> personalLabels;
    private List<Label> worldLabels;

    private ICubeFactory mediumCubeFactory;
    private MediumEffects mediumEffects;


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


        mediumEffects = new MediumEffects(this);
        mediumEffects.setImagesAndComponents(
                midgrid, midTop, midL, midBot, midend,
                midneo,play, returngame, mediumGrid, scorePane);
        Platform.runLater(() -> mediumEffects.entrance());


        initScoreHeaders(personalScoreHeader, worldScoreHeader);
        this.personalLabels = List.of(p1, p2, p3, p4, p5);
        this.worldLabels = List.of(w1, w2, w3, w4, w5);
        setPersonalScore(ModeType.MEDIUM, personalLabels);
        setWorldScore(ModeType.MEDIUM, worldLabels);
        Stream.concat(personalLabels.stream(), worldLabels.stream())
                .forEach(label -> label.setStyle("-fx-font: 14 \"Atari Classic\";"));

        dynamicHeader.setFont(Font.font("Atari Classic", 26));
        dynamicHeader.setText("SCORE");
        dynamicScore.setFont(Font.font("Atari Classic", 26));
        dynamicScore.setText("0000");
    }

    @Override
    public void setCamera() {
        super.setCamera();
    }

    /**

     Sets the images for the game graphics.
     */
    @Override
    public void setImages() {


        dynamicScorePane.setVisible(false);
        timerPane.setVisible(false);
        midend.setOpacity(0);
        midTop.setOpacity(0);
        midL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(5));
        midL.setOpacity(0);
        midBot.setImage(ImageCache.getInstance().getGameBackGroundCache().get(6));
        midBot.setOpacity(0);
        midgrid.setImage(ImageCache.getInstance().getGameBackGroundCache().get(10));
        midgrid.setOpacity(0);
        play.setImage(ImageCache.getInstance().getGameBackGroundCache().get(14));
        play.setOpacity(0);
        returngame.setImage(ImageCache.getInstance().getGameBackGroundCache().get(15));
        returngame.setOpacity(0);
        midneo.setImage(ImageCache.getInstance().getGameBackGroundCache().get(18));
        midneo.setOpacity(1);
        mediumGrid.setHgap(25);
        mediumGrid.setVgap(20);
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
        mediumGrid.getChildren().clear();
        mediumCubeFactory = new MediumCubeFactory(this);
        gameController.startGame(ModeType.MEDIUM);
        countDown(ModeType.MEDIUM);

    }

    /**

     Sets the cubes to the game board.
     @param memoryObjects The cubes to be added to the game board.
     */
    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) {

        mediumCubeFactory.createCubics(mediumGrid, memoryObjects);

    }

    /**

     Starts a new game.
     */
    @Override
    public void newGame() {
        gameController.killTimer();
        clearGameOverMenu(sceneRoot, gameRoot);
        Platform.runLater(() -> timeBar.setFitWidth(592) );
        setStartGame();
    }

    /**

     Returns to the game menu.
     */
    @FXML
    public void returnMenu() {

        wallOfeetu.setMouseTransparent(false);
        Platform.runLater(() -> timerPane.setVisible(false));
        Platform.runLater(() -> dynamicScorePane.setVisible(false));
        Platform.runLater(() -> mediumEffects.wallsOff());
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


        setPersonalScore(ModeType.MEDIUM, personalLabels);
        setWorldScore(ModeType.MEDIUM, worldLabels);
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

     Overrides the parent method to update the time bar's width, but only if the game is not in practice mode.
     @param i the current time in seconds
     */
    @Override
    public void getTime(int i) {

        super.getTime(i);
        Platform.runLater(() -> timeBar.setFitWidth(timeBar.getFitWidth() - 0.058));
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
