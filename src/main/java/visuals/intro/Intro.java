package visuals.intro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ModeType;
import visuals.Navigaattori;
import visuals.effects.introEffects.IntroEffects;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**

 The Intro class controls the behavior of the introduction screen of the game,
 where various visual effects are applied to the screen to introduce the game to the user.
 It implements the Initializable interface to initialize the JavaFX objects defined in the FXML file.
 */
public class Intro implements Initializable {
    @FXML Pane gameModePane;
    @FXML AnchorPane startBlack;
    @FXML AnchorPane menuAnkkuri;
    @FXML Label weDidIt;
    @FXML ImageView groupFour;
    @FXML ImageView pergament;
    @FXML ImageView sun;
    @FXML ImageView lightning;
    @FXML ImageView miniEasy;
    @FXML ImageView miniMedium;
    @FXML ImageView miniHard;
    @FXML ImageView easyFrame;
    @FXML ImageView mediumFrame;
    @FXML ImageView hardFrame;
    @FXML ImageView japan;
    @FXML ImageView jungle;
    @FXML ImageView redtree;
    @FXML Pane logAndReg;
    @FXML ImageView dirt;
    @FXML ImageView memomaze;
    @FXML Pane paneLogin;
    @FXML ImageView loading;
    @FXML ImageView kotoku;
    @FXML ImageView tigerden;
    @FXML ImageView treeoflife;
    @FXML ImageView telkku;
    @FXML AnchorPane bottom;
    @FXML ImageView info;
    @FXML Pane leaderpane;
    @FXML Pane audioPane;
    private IntroEffects introEffects = new IntroEffects();

    /**
     * Initializes the `Intro` controller after its root element has been completely processed.
     * Sets the opacity of several UI elements to zero and starts the intro animation.
     *
     * @param url The location used to resolve relative paths.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bottom.setMouseTransparent(true);
        startBlack.setVisible(true);
        logAndReg.setOpacity(0);
        easyFrame.setOpacity(0);
        mediumFrame.setOpacity(0);
        hardFrame.setOpacity(0);
        japan.setOpacity(0);
        jungle.setOpacity(0);
        redtree.setOpacity(0);
        miniEasy.setOpacity(0);
        miniMedium.setOpacity(0);
        miniHard.setOpacity(0);
        kotoku.setOpacity(0);
        tigerden.setOpacity(0);
        treeoflife.setOpacity(0);
        leaderpane.setOpacity(0);
        info.setOpacity(0);
        audioPane.setOpacity(0);

        introEffects.intro(
                weDidIt, groupFour, logAndReg,
                sun, lightning, easyFrame, mediumFrame, hardFrame,
                memomaze,loading,kotoku, tigerden, treeoflife,pergament,startBlack,
                gameModePane,japan,jungle,redtree,
                miniEasy,miniMedium,miniHard,leaderpane,info,bottom,audioPane);
    }

    /**
     * Exits the `Intro` screen and changes the scene to the menu screen.
     * Stops the intro animation and sets the `introEffects` object to `null`.
     *
     * @throws RuntimeException if there is an error changing the scene to the menu screen.
     */
    @FXML
    public void exitIntro() {

        try {
            Navigaattori.getInstance().changeScene(ModeType.MENU);
            introEffects.stopIntro();
            introEffects = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
