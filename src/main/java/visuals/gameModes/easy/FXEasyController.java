package visuals.gameModes.easy;

import controller.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.*;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.effects.gameEffects.EasyEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.gameModes.GameOverController;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


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

    private ICubeFactory easyCubeFactory;
    private EasyEffects easyEffects;
    private ScoreController scoreController;


    public void setController(ScoreController scoreController) {

        this.scoreController = scoreController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(this::setCamera);
        setImages();
        easyEffects = new EasyEffects();
        easyEffects.setImagesAndComponents(background, easyTop, easyBot, easyL, easy3Dgrid, play, returngame, easyGridi, easyEnd, easyneo, scorePane);
        easyEffects.entrance();

        easyEffects.setImagesAndComponents(
                background, easyTop, easyBot, easyL, easy3Dgrid,
                play, returngame, easyGridi, easyEnd, easyneo, scorePane);
        Platform.runLater(() -> easyEffects.entrance());
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

    // To gamecontroller
    @Override
    public void setStartGame() {

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        easyGridi.getChildren().clear();
        easyCubeFactory = new EasyCubeFactory(this);
        gameController.startGame(ModeType.EASY);
    }

    // From gamecontroller
    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        easyCubeFactory.createCubics(easyGridi, memoryObjects);
    }

    @FXML
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

        Platform.runLater(() -> easyEffects.wallsOff());
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

    @Override
    public void setWorldScore() {
        ArrayList<String> worldScores = scoreController.getTopFiveScores(ModeType.EASY);

        w1.setText(worldScores.get(0));
        w2.setText(worldScores.get(1));
        w3.setText(worldScores.get(2));
        w4.setText(worldScores.get(3));
        w5.setText(worldScores.get(4));
    }


    @Override
    public void setPersonalScore() {
        ArrayList<String> personalScores = scoreController.getTopFivePersonalScores(ModeType.EASY);

        p1.setText(personalScores.get(0));
        p2.setText(personalScores.get(1));
        p3.setText(personalScores.get(2));
        p4.setText(personalScores.get(3));
        p5.setText(personalScores.get(4));
    }
}
