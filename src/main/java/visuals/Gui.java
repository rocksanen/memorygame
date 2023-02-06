package visuals;

import controller.Controller;
import controller.IControllerScoreToV;
import controller.IControllerVtoE;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MemoryObject;
import model.ModeType;
import model.Score;
import model.Scoreboard;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;

public class Gui extends Application implements IGui{

    private final IControllerVtoE controller = new Controller(this);
    private final IControllerScoreToV scoreController = new Controller(this);
    private final Scoreboard scoreboard = new Scoreboard(scoreController);
    private final String EASYMODE = "/visuals/easymode.fxml";

    Stage primaryStage;

    //@FXML Box e0,e1,e2,e3,e4,e5;

    @FXML Button startEasyGame;
    @FXML
    Button eB0;
    @FXML
    Button eB1;
    @FXML
    Button eB2;
    @FXML
    Button eB3;
    @FXML
    Button eB4;
    @FXML
    Button eB5;

    @FXML
    Label eL0;
    @FXML
    Label eL1;
    @FXML
    Label eL2;
    @FXML
    Label eL3;
    @FXML
    Label eL4;
    @FXML
    Label eL5;
    @FXML
    Pane pane;
    @FXML PhongMaterial material;
    @FXML
    GridPane easyGrid;
    @FXML
    ListView<String> personalScores;
    @FXML
    ListView<String> worldScores;

    public static void main(String[] args) {launch(args);}


    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(EASYMODE)));

        Node worldScoresNode = root.lookup("#worldScores");
        if (worldScoresNode instanceof ListView<?>) {
            worldScores = (ListView<String>) worldScoresNode;
            setWorldScore();
        }

        Scene scene = new Scene(root, 1150, 700);
        this.primaryStage.setScene(scene);
        this.primaryStage.setFullScreenExitHint ("");
        this.primaryStage.setResizable(true);
        this.primaryStage.show();
    }
    @Override
    public void init() {

        eB0 = new Button();
        eB1 = new Button();
        eB2 = new Button();
        eB3 = new Button();
        eB4 = new Button();
        eB5 = new Button();

        eL0 = new Label();
        eL1 = new Label();
        eL2 = new Label();
        eL3 = new Label();
        eL4 = new Label();
        eL5 = new Label();

        personalScores = new ListView<>();
        startEasyGame = new Button();
    }

    @FXML
    public void eButton0() {controller.eB0(0);}
    @FXML
    public void eButton1() {controller.eB1(1);}
    @FXML
    public void eButton2() {controller.eB2(2);}
    @FXML
    public void eButton3() {controller.eB3(3);}
    @FXML
    public void eButton4() {controller.eB4(4);}
    @FXML
    public void eButton5() {controller.eB5(5);}

    @FXML
    public void setStartEasyGame() {

        clearTable();
        controller.startEasyGame();
    }

    public void setTypeToLabel(int id,int typeID) {

        switch (id) {
            case 0 -> eL0.setText(Integer.toString(typeID));
            case 1 -> eL1.setText(Integer.toString(typeID));
            case 2 -> eL2.setText(Integer.toString(typeID));
            case 3 -> eL3.setText(Integer.toString(typeID));
            case 4 -> eL4.setText(Integer.toString(typeID));
            case 5 -> eL5.setText(Integer.toString(typeID));
        }
    }

    public void clearTable(){

        eL0.setText("");
        eL1.setText("");
        eL2.setText("");
        eL3.setText("");
        eL4.setText("");
        eL5.setText("");

    }

    public void clearPair(ArrayList<MemoryObject> memoryList){

        for(MemoryObject obj: memoryList) {
            switch (obj.getIdNumber()) {
                case 0 -> eL0.setText("");
                case 1 -> eL1.setText("");
                case 2 -> eL2.setText("");
                case 3 -> eL3.setText("");
                case 4 -> eL4.setText("");
                case 5 -> eL5.setText("");
            }
        }
    }

    @Override
    public void getWorldScore(ArrayList<String> worldList) {

        // Create an observable list from the worldList
        ObservableList<String> worldObservable = FXCollections.observableArrayList();

        // Add all the elements from the worldList to the worldObservable
        worldObservable.addAll(worldList);

        // Add all the elements from the worldObservable to the worldScores list
        worldScores.getItems().addAll(worldObservable);

    }

    @Override
    public void setWorldScore() {

        //worldScores.getScores();

    }

    @Override
    public void setPersonalScores(ArrayList<String> personalList) {

        ObservableList<String> personObservable = FXCollections.observableArrayList();
        personObservable.addAll(personalList);
        personalScores.getItems().addAll(personObservable);

    }


}
