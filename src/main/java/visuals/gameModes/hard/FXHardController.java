package visuals.gameModes.hard;

import controller.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import model.MemoryObject;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.HardCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.effects.gameEffects.HardEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
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
    @FXML
    ProgressBar hard_progressbar;

    private List<Label> personalLabels;
    private List<Label> worldLabels;

    private ICubeFactory hardCubeFactory;
    private HardEffects hardEffects;
    private ScoreController scoreController;

    public void setController(ScoreController scoreController) {

        this.scoreController = scoreController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(this::setCamera);
        setImages();
        hardEffects = new HardEffects();
        hardEffects.setImagesAndComponents(
                hardBackground, scorePane, hardGrid,
                hardGridImage, hardR, hardL,
                hardneo, play, returngame);
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

        setStartGame();


    }

    @Override
    public void setCamera() {
        super.setCamera();
    }

    @Override
    public void setImages() {

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
    }

    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        hardCubeFactory.createCubics(hardGrid, memoryObjects);
    }

    @Override
    public void newGame() {
        clearGameOverMenu(sceneRoot, gameRoot);
        setStartGame();
    }

    @Override
    public void returnMenu() {
        Platform.runLater(() -> hardEffects.wallsOff());
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

        setPersonalScore(HARD, personalLabels);
        setWorldScore(HARD, worldLabels);

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
    public void getTime(int i) {
        super.getTime(i);
        hard_progressbar.setProgress(i*0.01);
    }

    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }
}
