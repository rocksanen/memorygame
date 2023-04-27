package visuals.gameModes.medium;

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

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
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
    @FXML ImageView timeBar;
    @FXML Pane timerPane;
    @FXML Pane dynamicScorePane;



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

        setCamera();
        setImages();


        mediumEffects = new MediumEffects(this);
        mediumEffects.setImagesAndComponents(
                mediumBackground, midgrid, midTop, midL, midBot, midend,
                midneo,play, returngame, mediumGrid, scorePane);
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

        dynamicHeader.setFont(Font.font("Atari Classic", 26));
        dynamicHeader.setText("SCORE");
        dynamicScore.setFont(Font.font("Atari Classic", 26));
        dynamicScore.setText("0000");
    }

    @Override
    public void setCamera() {
        super.setCamera();
    }

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

    // To gamecontroller
    @Override
    public void setStartGame() {

        if (cubeList != null) {
            cubeList.clear();
        }
        dynamicScore.textProperty().unbind();
        dynamicScore.setText("0000");
        cubeList = new ArrayList<>();
        mediumGrid.getChildren().clear();
        mediumCubeFactory = new MediumCubeFactory(this);
        gameController.startGame(ModeType.MEDIUM);
        CompletableFuture.runAsync(() -> {

            try {
                Thread.sleep(600);
                Platform.runLater(() -> timerPane.setVisible(true));
                Platform.runLater(() -> dynamicScorePane.setVisible(true));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        mediumCubeFactory.createCubics(mediumGrid, memoryObjects);

    }

    @Override
    public void newGame() {
        gameController.killTimer();
        clearGameOverMenu(sceneRoot, gameRoot);
        Platform.runLater(() -> timeBar.setFitWidth(592) );
        setStartGame();
    }

    @FXML
    public void returnMenu() {

        Platform.runLater(() -> timerPane.setVisible(false));
        Platform.runLater(() -> dynamicScorePane.setVisible(false));
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
    public void gameOver(boolean victory) {


        setPersonalScore(ModeType.MEDIUM, personalLabels);
        setWorldScore(ModeType.MEDIUM, worldLabels);
        gameOverMenu(gameRoot, sceneRoot, victory);
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
        Platform.runLater(() -> timeBar.setFitWidth(timeBar.getFitWidth() - 0.058));
    }

    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }

    @Override
    public void updateDynamicScore(int score) {

        super.updateDynamicScore(score);
    }
}
