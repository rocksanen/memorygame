package visuals.gameModes.easy;

import controller.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.*;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.effects.gameEffects.EasyEffects;
import visuals.gameModes.FXAbstractGameController;
import visuals.gameModes.FXIGameController;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static model.ModeType.EASY;


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

        initScoreHeaders(personalScoreHeader, worldScoreHeader);
        this.personalLabels = List.of(p1, p2, p3, p4, p5);
        this.worldLabels = List.of(w1, w2, w3, w4, w5);
        setPersonalScore(EASY, personalLabels);
        setWorldScore(EASY, worldLabels);
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
        gameController.startGame(EASY);
    }

    // From gamecontroller
    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        easyCubeFactory.createCubics(easyGridi, memoryObjects);
    }

    @FXML
    public void newGame() {
        clearGameOverMenu(sceneRoot, gameRoot);
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
        setPersonalScore(EASY, personalLabels);
        setWorldScore(EASY, worldLabels);

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

    /*
    @Override
    public void getTime(int i) {
        super.getTime(i);
        System.out.println(i);
        Platform.runLater(()-> timer_easy.setText(Integer.toString(i)));
        progressbar_easy.setProgress(i*0.01);

    }
     */

    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }
}
