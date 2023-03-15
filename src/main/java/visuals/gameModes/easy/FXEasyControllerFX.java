package visuals.gameModes.easy;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.MemoryObject;
import visuals.Navigaattori;
import visuals.cubeFactories.BoxMaker;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.gameModes.FXAbstractGameController;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class FXEasyControllerFX extends FXAbstractGameController implements Initializable {

    @FXML GridPane easyGridi;
    @FXML ImageView background;
    @FXML ImageView easyTop;
    @FXML ImageView easyL;
    @FXML ImageView easyBot;
    @FXML ImageView play;
    @FXML ImageView returngame;
    @FXML Pane scorePane;
    @FXML Label p1,p2,p3,p4,p5;
    @FXML Label w1,w2,w3,w4,w5;
    @FXML ImageView easy3Dgrid;
    @FXML ImageView easyneo;

    private ICubeFactory easyCubeFactory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCamera();
        setImages();
        setStartEasyGame();
        setWorldScore();
    }

    @Override
    public void setCamera() {
        super.setCamera();
    }

    @Override
    public void setImages() {

        background.setImage(ImageCache.getInstance().getGameBackGroundCache().get(0));
        easyTop.setImage(ImageCache.getInstance().getGameBackGroundCache().get(7));
        easyBot.setImage(ImageCache.getInstance().getGameBackGroundCache().get(8));
        easyL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(9));
        easy3Dgrid.setImage(ImageCache.getInstance().getGameBackGroundCache().get(10));

        easyGridi.setHgap(-80);
    }

    @FXML
    public void newGame() {

        setStartEasyGame();
    }

    @FXML
    public void returnMenu() {



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
    public void setEasyGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        //selectedDifficulty = EASY;
        easyCubeFactory.createCubics(easyGridi, memoryObjects);
        //setPersonalScores(scoreController.getPersonalScores(EASY));
        //getWorldScore(scoreController.getScores(EASY));

    }

    @Override
    public void gameOver() {

        /*
        setPersonalScores(scoreController.getPersonalScores(EASY));
        getWorldScore(scoreController.getScores(EASY));

         */
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
    public void setStartEasyGame() {


        if (cubeList != null) {
            cubeList.clear();
        }

        cubeList = new ArrayList<>();
        easyGridi.getChildren().clear();
        easyCubeFactory = new EasyCubeFactory(this);
        gameController.startEasyGame();
    }

    @Override
    public void sendIdToEngine(int id) {
        super.sendIdToEngine(id);
    }

    @Override
    public void setWorldScore() {
        /*
        w1 = Gui.worldLabels.get(0);
        w2 = Gui.worldLabels.get(1);
        w3 = Gui.worldLabels.get(2);
        w4 = Gui.worldLabels.get(3);
        w5 = Gui.worldLabels.get(4);

         */
    }
}
