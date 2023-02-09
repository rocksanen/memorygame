package visuals;

import controller.Controller;
import controller.IControllerScoreToV;
import controller.IControllerVtoE;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MemoryObject;
import model.Scoreboard;
import visuals.CubeFactories.EasyCubeFactory;
import visuals.CubeFactories.ICubeFactory;
import visuals.CubeFactories.MediumCubeFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Gui extends Application implements IGui{

    private final IControllerVtoE controller = new Controller(this);
    private final IControllerScoreToV scoreController = new Controller(this);
    private final Scoreboard scoreboard = new Scoreboard(scoreController);
    private final String EASYMODE = "/visuals/game.fxml";
    Stage primaryStage;
    @FXML Button startEasyGame;
    @FXML Button startMediumGame;
    @FXML
    GridPane easyGrid = new GridPane();
    @FXML
    ListView<String> personalScores;
    @FXML
    ListView<String> worldScores;
    @FXML
    ImageView background;
    @FXML
    VBox vBox = new VBox();

    ArrayList<BoxMaker> cubeList;
    ICubeFactory easyCubeFactory;
    ICubeFactory mediumCubeFactory;
    Parent root;

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(EASYMODE)));
        Scene scene = new Scene(root, 1250, 750);

        Node worldScoresNode = root.lookup("#worldScores");
        if (worldScoresNode instanceof ListView<?>) {
            worldScores = (ListView<String>) worldScoresNode;
            setWorldScore();
        }

        PerspectiveCamera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        scene.setOnScroll((final ScrollEvent e) -> {
            camera.setTranslateZ(camera.getTranslateZ() + e.getDeltaY());
        });

        background = (ImageView) root.lookup("#background");

        if (background == null) {
            System.out.println("background is null");
            return;
        }

        this.primaryStage.setScene(scene);
        this.primaryStage.setFullScreenExitHint ("");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
        Effects.getInstance().moveBackGround(background);
    }
    @Override
    public void init() {

        background = new ImageView();
        personalScores = new ListView<>();
        startEasyGame = new Button();
        startMediumGame = new Button();
        easyGrid = new GridPane();
        background = new ImageView();
    }
    @FXML
    public void setStartEasyGame() {

        if(cubeList != null) {cubeList.clear();}
        cubeList = new ArrayList<>();
        easyCubeFactory = new EasyCubeFactory(this);
        easyGrid.getChildren().clear();
        controller.startEasyGame();
    }
    @FXML
    public void setStartMediumGame() {

        if(cubeList != null) {cubeList.clear();}
        cubeList = new ArrayList<>();
        mediumCubeFactory = new MediumCubeFactory(this);
        easyGrid.getChildren().clear();
        controller.startMediumGame();
    }

    @Override
    public void setEasyGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        easyCubeFactory.createCubics(easyGrid,memoryObjects);
    }
    @Override
    public void setMediumGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        mediumCubeFactory.createCubics(easyGrid,memoryObjects);
    }
    @Override
    public void clearStorage() {
        controller.clearStorage();
    }

    public void addToCubeList(BoxMaker cube) {cubeList.add(cube);}
    @Override
    public void clearPair(ArrayList<Integer> storage){

        cubeList.get(storage.get(0)).resetImage();
        cubeList.get(storage.get(1)).resetImage();
        clearStorage();
    }

    public void sendIdToEngine(int id) {controller.sendIdToEngine(id);}

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
    public void setWorldScore() {//scoreboard.fetchScores(ModeType.EASY);
    }

    @Override
    public void setPersonalScores(ArrayList<String> personalList) {

        ObservableList<String> personObservable = FXCollections.observableArrayList();
        personObservable.addAll(personalList);
        personalScores.getItems().addAll(personObservable);
    }
}
