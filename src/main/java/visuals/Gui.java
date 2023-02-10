package visuals;

import controller.Controller;
import controller.IControllerScoreToV;
import controller.IControllerVtoE;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MemoryObject;
import model.ModeType;
import model.Scoreboard;
import model.User;
import visuals.CubeFactories.EasyCubeFactory;
import visuals.CubeFactories.HardCubeFactory;
import visuals.CubeFactories.ICubeFactory;
import visuals.CubeFactories.MediumCubeFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Gui extends Application implements IGui {

    private final IControllerVtoE controller = new Controller(this);
    private final IControllerScoreToV scoreController = new Controller(this);
    private final Scoreboard scoreboard = new Scoreboard(scoreController);
    private final String EASYMODE = "/visuals/game.fxml";
    Stage primaryStage;
    @FXML
    Button startEasyGame;
    @FXML
    Button startMediumGame;
    @FXML
    Button startHardGame;
    @FXML
    GridPane easyGrid;

    @FXML
    GridPane mediumGrid;

    @FXML
    GridPane hardGrid;
    @FXML
    ListView<String> personalScores;
    @FXML
    ListView<String> worldScores;
    @FXML
    ImageView background;
    @FXML
    ImageView mediumBackground;
    @FXML
    ImageView hardBackground;
    @FXML
    VBox vBox = new VBox();

    @FXML
    Button register;
    @FXML
    Button login;
    @FXML
    Pane signOrReg;
    @FXML
    TextField name;
    @FXML
    TextField password;

    ArrayList<BoxMaker> cubeList;
    ICubeFactory easyCubeFactory;
    ICubeFactory mediumCubeFactory;
    ICubeFactory hardCubeFactory;
    Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/game2.fxml")));
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
        mediumBackground = (ImageView) root.lookup("#mediumBackground");
        hardBackground = (ImageView) root.lookup("#hardBackground");


        this.primaryStage.setScene(scene);
        this.primaryStage.setFullScreenExitHint("");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
        Platform.runLater(() -> Effects.getInstance().moveBackGround(background));
        Platform.runLater(() -> Effects.getInstance().moveBackGround(mediumBackground));
        Platform.runLater(() -> Effects.getInstance().moveBackGround(hardBackground));
    }

    @Override
    public void init() {
        personalScores = new ListView<>();
        startEasyGame = new Button();
        startMediumGame = new Button();
        startHardGame = new Button();
        easyGrid = new GridPane();
        mediumGrid = new GridPane();
        hardGrid = new GridPane();
        register = new Button();
        login = new Button();
        signOrReg = new Pane();
        name = new TextField();
        password = new TextField();
    }

    @FXML
    public void registerPane() {

        String user = name.getText();
        String userPassword = password.getText();

        controller.register(user, userPassword);
        signOrReg.setVisible(false);

    }

    @FXML
    public void loginPane() {
        String user = name.getText();
        String userPassword = password.getText();
        try {
            controller.login(user, userPassword);
            if (controller.isLoggedIn() != true) {
                return;
            }
            setPersonalScores(scoreController.getPersonalScores(ModeType.EASY));
            signOrReg.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    public void setStartEasyGame() {

        Platform.runLater(() -> Effects.getInstance().backGroundIn(background));
        Platform.runLater(() -> Effects.getInstance().backGroundOut(mediumBackground));
        Platform.runLater(() -> Effects.getInstance().backGroundOut(hardBackground));

        mediumGrid.setMouseTransparent(true);
        mediumGrid.setVisible(false);
        hardGrid.setMouseTransparent(true);
        hardGrid.setVisible(false);
        easyGrid.setMouseTransparent(false);
        easyGrid.setVisible(true);

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        easyCubeFactory = new EasyCubeFactory(this);
        easyGrid.getChildren().clear();
        controller.startEasyGame();
    }

    @FXML
    public void setStartMediumGame() {

        Platform.runLater(() -> Effects.getInstance().backGroundIn(mediumBackground));
        Platform.runLater(() -> Effects.getInstance().backGroundOut(background));
        Platform.runLater(() -> Effects.getInstance().backGroundOut(hardBackground));

        easyGrid.setMouseTransparent(true);
        easyGrid.setVisible(false);
        hardGrid.setMouseTransparent(true);
        hardGrid.setVisible(false);
        mediumGrid.setMouseTransparent(false);
        mediumGrid.setVisible(true);
        mediumGrid.setHgap(40);
        mediumGrid.setVgap(20);

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        mediumCubeFactory = new MediumCubeFactory(this);
        mediumGrid.getChildren().clear();
        controller.startMediumGame();
    }

    @FXML
    public void setStartHardGame() {

        Platform.runLater(() -> Effects.getInstance().backGroundIn(hardBackground));
        Platform.runLater(() -> Effects.getInstance().backGroundOut(background));
        Platform.runLater(() -> Effects.getInstance().backGroundOut(mediumBackground));

        easyGrid.setMouseTransparent(true);
        easyGrid.setVisible(false);
        mediumGrid.setMouseTransparent(true);
        mediumGrid.setVisible(false);
        hardGrid.setMouseTransparent(false);
        hardGrid.setVisible(true);
        hardGrid.setHgap(70);
        hardGrid.setVgap(50);

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        hardCubeFactory = new HardCubeFactory(this);
        hardGrid.getChildren().clear();
        controller.startHardGame();

    }

    @Override
    public void setEasyGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        easyCubeFactory.createCubics(easyGrid, memoryObjects);
    }

    @Override
    public void setMediumGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        mediumCubeFactory.createCubics(mediumGrid, memoryObjects);
    }

    @Override
    public void setHardGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        System.out.println(memoryObjects.size());
        hardCubeFactory.createCubics(hardGrid, memoryObjects);
    }

    @Override
    public void clearStorage() {
        controller.clearStorage();
    }

    public void addToCubeList(BoxMaker cube) {
        cubeList.add(cube);
    }

    @Override
    public void clearPair(ArrayList<Integer> storage) {

        cubeList.get(storage.get(0)).resetImage();
        cubeList.get(storage.get(1)).resetImage();
        clearStorage();
    }

    public void sendIdToEngine(int id) {
        controller.sendIdToEngine(id);
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
        scoreController.fetchScores(ModeType.EASY);
        getWorldScore(scoreController.getScores(ModeType.EASY));

    }

    @Override
    public void setPersonalScores(ArrayList<String> personalList) {
        if (personalList == null) {
            return;
        }
        ObservableList<String> personObservable = FXCollections.observableArrayList();
        personObservable.addAll(personalList);
        personalScores.getItems().addAll(personObservable);
    }
}
