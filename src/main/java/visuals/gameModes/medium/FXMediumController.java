package visuals.gameModes.medium;

import controller.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.MemoryObject;
import model.ModeType;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.cubeFactories.MediumCubeFactory;
import visuals.effects.gameEffects.MediumEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.gameModes.GameOverController;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
        Platform.runLater(this::setWorldScore);
        Platform.runLater(this::setPersonalScore);

        p1.setStyle("-fx-font: 14 \"Atari Classic\";");
        p2.setStyle("-fx-font: 14 \"Atari Classic\";");
        p3.setStyle("-fx-font: 14 \"Atari Classic\";");
        p4.setStyle("-fx-font: 14 \"Atari Classic\";");
        p5.setStyle("-fx-font: 14 \"Atari Classic\";");
        w1.setStyle("-fx-font: 14 \"Atari Classic\";");
        w2.setStyle("-fx-font: 14 \"Atari Classic\";");
        w3.setStyle("-fx-font: 14 \"Atari Classic\";");
        w4.setStyle("-fx-font: 14 \"Atari Classic\";");
        w5.setStyle("-fx-font: 14 \"Atari Classic\";");

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

    @Override
    public void setWorldScore() {
        ArrayList<String> worldScores = scoreController.getTopFiveScores(ModeType.MEDIUM);

        w1.setText(worldScores.get(0));
        w2.setText(worldScores.get(1));
        w3.setText(worldScores.get(2));
        w4.setText(worldScores.get(3));
        w5.setText(worldScores.get(4));
    }


    @Override
    public void setPersonalScore() {
        ArrayList<String> personalScores = scoreController.getTopFivePersonalScores(ModeType.MEDIUM);

        p1.setText(personalScores.get(0));
        p2.setText(personalScores.get(1));
        p3.setText(personalScores.get(2));
        p4.setText(personalScores.get(3));
        p5.setText(personalScores.get(4));
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
        // delete game over -view if it exists
        System.out.println(sceneRoot.getChildren());
        if (sceneRoot.getChildren().size() > 1) {
            sceneRoot.getChildren().remove(1);
        }
        // remove effects from gameroot
        gameRoot.setEffect(null);

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

        Platform.runLater(this::setPersonalScore);
        Platform.runLater(this::setWorldScore);
        System.out.println("game over");

        //load gameover.fxml and add it to sceneroot also pass this controller
        try {
            // set gameroot gaussian blur
            GaussianBlur gaussianBlur = new GaussianBlur();
            gaussianBlur.setRadius(10);
            gameRoot.setEffect(gaussianBlur);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/visuals/gameModes/GameOver.fxml"));
            AnchorPane gameOverView = loader.load();
            GameOverController goc = loader.getController();
            goc.Initialize(this, gameController);
            sceneRoot.getChildren().add(gameOverView);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
