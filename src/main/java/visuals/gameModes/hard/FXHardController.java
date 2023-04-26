package visuals.gameModes.hard;

import controller.ScoreController;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;
import model.MemoryObject;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.HardCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.effects.gameEffects.HardEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
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
    @FXML Pane timerPane;
    @FXML ImageView practiseButton;

    private List<Label> personalLabels;
    private List<Label> worldLabels;

    private ICubeFactory hardCubeFactory;
    private HardEffects hardEffects;
    private ScoreController scoreController;

    private boolean practice = false;

    public void setController(ScoreController scoreController) {

        this.scoreController = scoreController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCamera();
        setImages();
        hardEffects = new HardEffects(this);
        hardEffects.setImagesAndComponents(
                hardBackground, scorePane, hardGrid,
                hardGridImage, hardR, hardL,
                hardneo, play, returngame, practiseButton);
        Platform.runLater(() -> hardEffects.entrance());

        initScoreHeaders(personalScoreHeader, worldScoreHeader);
        this.personalLabels = List.of(p1, p2, p3, p4, p5);
        this.worldLabels = List.of(w1, w2, w3, w4, w5);
        setPersonalScore(HARD, personalLabels);
        setWorldScore(HARD, worldLabels);
        Stream.concat(personalLabels.stream(), worldLabels.stream())
                .forEach(label -> {
                    label.setStyle("-fx-font: 14 \"Atari Classic\";");
                });

    }

    @Override
    public void setCamera() {
        super.setCamera();
    }

    @Override
    public void setImages() {

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
        hardGrid.setVgap(120);
        hardGrid.setHgap(58);

        for (int i = 0; i < 5; i++) {
            RowConstraints rowConstraints = new RowConstraints(10);
            hardGrid.getRowConstraints().add(rowConstraints);
        }
    }

    @Override
    public void setStartGame() {

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        hardGrid.getChildren().clear();
        hardCubeFactory = new HardCubeFactory(this);
        gameController.startGame(HARD);
        if(practice) {
            gameController.killTimer();
        }

        CompletableFuture.runAsync(() -> {

            try {
                Thread.sleep(1000);
                timerPane.setVisible(true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }

    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        hardCubeFactory.createCubics(hardGrid, memoryObjects);
    }

    @Override
    public void newGame() {

        gameController.killTimer();
        clearGameOverMenu(sceneRoot, gameRoot);
        Platform.runLater(() -> timeBar.setFitWidth(592));
        setStartGame();
    }

    @Override
    public void returnMenu() {

        Platform.runLater(() -> timerPane.setVisible(false));
        hardEffects.wallsOff();
    }

    @Override
    public void addToCubeList(BoxMaker cube) {
        super.addToCubeList(cube);
    }

    @Override
    public void clearPair(ArrayList<Integer> storage) {
        super.clearPair(storage);
    }

    @Override
    public void clearStorage() {
        super.clearStorage();
    }

    @Override
    public void gameOver() {

        if (!practice) {
            setPersonalScore(HARD, personalLabels);
            setWorldScore(HARD, worldLabels);
        }
        gameOverMenu(gameRoot, sceneRoot);
    }

    @Override
    public void setActiveID(int activeID) {
        super.setActiveID(activeID);
    }

    @Override
    public void compareFoundMatch() {
        super.compareFoundMatch();
    }
    @Override
    public void glowHint(int idToGlow) {

        if (practice) {
            super.glowHint(idToGlow);
        }
    }

    public void setPractice() {

        if (!practice) {
            gameController.killTimer();
            practiseButton.setImage( new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                    "/pictures/images/hardGame/crazyButton.png"))));
            practice = true;
            hideTimeBar();
        } else {
            practice = false;
            practiseButton.setImage( new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                    "/pictures/images/hardGame/happyButton.png"))));
            newGame();
            revealTimeBar();
        }
    }

    private void hideTimeBar() {

        FadeTransition ft = new FadeTransition(Duration.millis(1000), timerPane);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
    }

    private void revealTimeBar() {

        FadeTransition ft = new FadeTransition(Duration.millis(1000), timerPane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @Override
    public void getTime(int i) {

        if(!practice) {
            super.getTime(i);
            Platform.runLater(() -> timeBar.setFitWidth(timeBar.getFitWidth() - 0.058));
        }
    }

    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }
}
