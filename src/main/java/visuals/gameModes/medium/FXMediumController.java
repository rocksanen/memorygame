package visuals.gameModes.medium;

import controller.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.MemoryObject;
import model.ModeType;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.ICubeFactory;
import visuals.cubeFactories.MediumCubeFactory;
import visuals.effects.gameEffects.MediumEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
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
    ImageView midneo2;
    @FXML
    ImageView midneo3;
    @FXML
    ImageView midneo4;
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

    private List<Label> personalLabels;
    private List<Label> worldLabels;

    private ICubeFactory mediumCubeFactory;
    private MediumEffects mediumEffects;

    private ScoreController scoreController;


    public void setController(ScoreController scoreController) {

        this.scoreController = scoreController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(this::setCamera);
        setImages();
        mediumEffects = new MediumEffects();
        mediumEffects.setImagesAndComponents(
                mediumBackground, midgrid, midTop, midL, midBot, midend,
                midneo, midneo2, midneo3, midneo4, play, returngame, mediumGrid, scorePane);
        Platform.runLater(() -> mediumEffects.entrance());

        initScoreHeaders(personalScoreHeader, worldScoreHeader);
        this.personalLabels = List.of(p1, p2, p3, p4, p5);
        this.worldLabels = List.of(w1, w2, w3, w4, w5);
        setPersonalScore(ModeType.MEDIUM, personalLabels);
        setWorldScore(ModeType.MEDIUM, worldLabels);
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

        mediumBackground.setImage(ImageCache.getInstance().getGameBackGroundCache().get(1));
        mediumSpread.setImage(ImageCache.getInstance().getGameBackGroundCache().get(1));
        midTop.setImage(ImageCache.getInstance().getGameBackGroundCache().get(4));
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
        midneo.setOpacity(0);
        midneo2.setImage(ImageCache.getInstance().getGameBackGroundCache().get(19));
        midneo2.setOpacity(0);
        midneo3.setImage(ImageCache.getInstance().getGameBackGroundCache().get(20));
        midneo3.setOpacity(0);
        midneo4.setImage(ImageCache.getInstance().getGameBackGroundCache().get(21));
        midneo4.setOpacity(0);
        mediumGrid.setHgap(25);
        mediumGrid.setVgap(20);
    }

    // To gamecontroller
    @Override
    public void setStartGame() {

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        mediumGrid.getChildren().clear();
        mediumCubeFactory = new MediumCubeFactory(this);
        gameController.startGame(ModeType.MEDIUM);

    }

    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        mediumCubeFactory.createCubics(mediumGrid, memoryObjects);
    }

    @Override
    public void newGame() {
        clearGameOverMenu(sceneRoot, gameRoot);
        setStartGame();
    }

    @FXML
    public void returnMenu() {

        Platform.runLater(() -> mediumEffects.wallsOff());
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

        setPersonalScore(ModeType.MEDIUM, personalLabels);
        setWorldScore(ModeType.MEDIUM, worldLabels);

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
    }

    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }
}
