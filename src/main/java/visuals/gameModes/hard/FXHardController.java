package visuals.gameModes.hard;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.MemoryObject;
import model.ModeType;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.HardCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.effects.gameEffects.HardEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static model.ModeType.HARD;

public class FXHardController extends FXAbstractGameController implements Initializable, FXIGameController {

    @FXML
    ImageView hardSpread;
    @FXML
    ImageView hardBackground;
    @FXML
    Pane scorePane;
    @FXML
    GridPane hardGrid;
    @FXML
    ImageView hardGridImage;
    @FXML
    ImageView hardR;
    @FXML
    ImageView hardL;
    @FXML
    ImageView hardneo;
    @FXML
    ImageView play;
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
    AnchorPane gameRoot;
    @FXML
    AnchorPane sceneRoot;
    @FXML
    ImageView personalScoreHeader;
    @FXML
    ImageView worldScoreHeader;
    @FXML ImageView timeBar;
    @FXML ImageView practiseButton;
    @FXML Pane dynamicScorePane;
    @FXML AnchorPane wallOfeetu;
    @FXML ImageView practiseTree;
    @FXML Pane practisePane;

    private List<Label> personalLabels;
    private List<Label> worldLabels;

    private ICubeFactory hardCubeFactory;
    private HardEffects hardEffects;


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
        hardEffects = new HardEffects(this);
        hardEffects.setImagesAndComponents(
                hardBackground, scorePane, hardGrid,
                hardGridImage, hardR, hardL,
                hardneo, play, returngame, practiseButton,practiseTree);
        Platform.runLater(() -> hardEffects.entrance());

        initScoreHeaders(personalScoreHeader, worldScoreHeader);
        this.personalLabels = List.of(p1, p2, p3, p4, p5);
        this.worldLabels = List.of(w1, w2, w3, w4, w5);
        setPersonalScore(HARD, personalLabels);
        setWorldScore(HARD, worldLabels);
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
        hardBackground.setImage(ImageCache.getInstance().getGameBackGroundCache().get(2));
        hardSpread.setImage(ImageCache.getInstance().getGameBackGroundCache().get(2));
        hardGridImage.setImage(ImageCache.getInstance().getGameBackGroundCache().get(11));
        hardGridImage.setOpacity(0);
        hardR.setImage(ImageCache.getInstance().getGameBackGroundCache().get(12));
        hardR.setOpacity(0);
        hardL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(13));
        hardL.setOpacity(0);
        hardneo.setImage(ImageCache.getInstance().getGameBackGroundCache().get(23));
        hardneo.setOpacity(0);
        play.setImage(ImageCache.getInstance().getGameBackGroundCache().get(14));
        play.setOpacity(0);
        returngame.setImage(ImageCache.getInstance().getGameBackGroundCache().get(15));
        returngame.setOpacity(0);
        practiseButton.setOpacity(0);
        practiseTree.setOpacity(0);
        hardGrid.setVgap(120);
        hardGrid.setHgap(58);

        for (int i = 0; i < 5; i++) {
            RowConstraints rowConstraints = new RowConstraints(10);
            hardGrid.getRowConstraints().add(rowConstraints);
        }
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
        hardGrid.getChildren().clear();
        hardCubeFactory = new HardCubeFactory(this);
        gameController.startGame(HARD);
        if(practice) {
            gameController.killTimer();
        }
        countDown(HARD);
    }

    /**

     Sets the cubes to the game board.
     @param memoryObjects The cubes to be added to the game board.
     */
    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) {

        hardCubeFactory.createCubics(hardGrid, memoryObjects);
    }

    /**

     Starts a new game.
     */
    @Override
    public void newGame() {

        gameController.killTimer();
        clearGameOverMenu(sceneRoot, gameRoot);
        Platform.runLater(() -> timeBar.setFitWidth(524));
        setStartGame();
    }

    /**

     Returns to the game menu.
     */
    @Override
    public void returnMenu() {

        wallOfeetu.setMouseTransparent(false);
        Platform.runLater(() -> dynamicScorePane.setVisible(false));
        Platform.runLater(() -> timerPane.setVisible(false));
        hardEffects.wallsOff();
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

        if (!practice) {
            setPersonalScore(HARD, personalLabels);
            setWorldScore(HARD, worldLabels);
        }
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

     Overrides the parent method to glow the hint, but only if the game is in practice mode.
     @param idToGlow the ID of the hint to glow
     */
    @Override
    public void glowHint(int idToGlow) {

        if (practice) {
            super.glowHint(idToGlow);
        }
    }

    /**

     Sets the game to practice mode, which hides the time bar, stops the timer, and turns on practice colors.
     If the game is already in practice mode, it will turn off practice mode, reveal the time bar, start a new game, and turn off practice colors.
     */
    public void setPractice() {

        if (!practice) {
            gameController.killTimer();
            practice = true;
            hideTimeBar();
            hardEffects.practiseColorsOn();
        } else {
            practice = false;
            hardEffects.practiseColorsOff();
            newGame();
            revealTimeBar();
        }
    }

    /**

     Hides the time bar by fading it out using a fade transition.
     */
    private void hideTimeBar() {

        FadeTransition ft = new FadeTransition(Duration.millis(1000), timerPane);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
    }

    /**

     Reveals the time bar by fading it in using a fade transition.
     */
    private void revealTimeBar() {

        FadeTransition ft = new FadeTransition(Duration.millis(1000), timerPane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    /**

     Overrides the parent method to update the time bar's width, but only if the game is not in practice mode.
     @param i the current time in seconds
     */
    @Override
    public void getTime(int i) {

        if(!practice) {
            super.getTime(i);
            Platform.runLater(() -> timeBar.setFitWidth(timeBar.getFitWidth() - 0.052));
        }
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
