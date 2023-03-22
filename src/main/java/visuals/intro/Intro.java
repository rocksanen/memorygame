package visuals.intro;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import visuals.effects.introEffects.IntroEffects;

import java.net.URL;
import java.util.ResourceBundle;

public class Intro implements Initializable {


    @FXML
    Pane gameModePane;
    @FXML
    AnchorPane startBlack;
    @FXML
    AnchorPane menuAnkkuri;
    @FXML
    Label weDidIt;
    @FXML
    ImageView groupFour;
    @FXML
    ImageView pergament;
    @FXML
    ImageView sun;
    @FXML
    ImageView lightning;
    @FXML
    ImageView blacksun;
    @FXML
    ImageView miniEasy;
    @FXML
    ImageView miniMedium;
    @FXML
    ImageView miniHard;
    @FXML
    ImageView easyFrame;
    @FXML
    ImageView mediumFrame;
    @FXML
    ImageView hardFrame;
    @FXML
    ImageView japan;
    @FXML
    ImageView jungle;
    @FXML
    ImageView redtree;
    @FXML
    Pane logAndReg;
    @FXML
    ImageView dirt;
    @FXML
    ImageView memomaze;
    @FXML
    Pane paneLogin;
    @FXML
    ImageView loading;

    IntroEffects introEffects = new IntroEffects();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        startIntro();
    }


    private void startIntro() {

        Platform.runLater(() -> introEffects.intro(
                weDidIt, groupFour, logAndReg,
                sun, lightning, blacksun,
                easyFrame, mediumFrame, hardFrame,
                memomaze, labelLoggedIn, loading,
                kotoku, tigerden, treeoflife,pergament,burningsun,startBlack,
                gameModePane,japan,jungle,redtree,
                miniEasy,miniMedium,miniHard,dirt));



    }
}
