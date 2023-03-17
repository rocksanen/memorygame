package visuals.gameModes.hard;

import controller.ScoreController;
import javafx.fxml.Initializable;
import model.MemoryObject;
import visuals.gameModes.FXAbstractGameController;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXHardController extends FXAbstractGameController implements Initializable {

    private ScoreController scoreController;


    public void setController(ScoreController scoreController) {

        this.scoreController = scoreController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setImages() {

    }

    @Override
    public void setCubesToGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

    }


    @Override
    public void gameOver() {

    }

    @Override
    public void setStartGame() {

    }

    @Override
    public void sendIdToEngine(int id) {

    }

    @Override
    public void setWorldScore() {

    }

    @Override
    public void setPersonalScore() {

    }

    @Override
    public void newGame() {

    }

    @Override
    public void returnMenu() {

    }


}
