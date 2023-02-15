package visuals;

import controller.Controller;
import controller.IControllerScoreToV;
import controller.IControllerVtoE;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MemoryObject;
import model.ModeType;
import model.Scoreboard;
import visuals.CubeFactories.EasyCubeFactory;
import visuals.CubeFactories.HardCubeFactory;
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
    @FXML ImageView hardSpread;
    @FXML ImageView mediumSpread;
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

    @FXML Pane gameModePane;

    @FXML
    AnchorPane startAnchor;
    @FXML Button newGame;
    @FXML Button returnMenu;

    @FXML ImageView regLog;

    ArrayList<BoxMaker> cubeList;
    ICubeFactory easyCubeFactory;
    ICubeFactory mediumCubeFactory;
    ICubeFactory hardCubeFactory;
    Parent root;
    Scene scene;

    public static PerspectiveCamera camera = new PerspectiveCamera();

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        this.scene = new Scene(root, 1250, 750);
        camera.setFieldOfView(25);
        this.scene.setCamera(camera);

        Node worldScoresNode = root.lookup("#worldScores");
        if (worldScoresNode instanceof ListView<?>) {
            worldScores = (ListView<String>) worldScoresNode;
            setWorldScore();
        }

        scene.setOnScroll((final ScrollEvent e) -> {
            camera.setTranslateZ(camera.getTranslateZ() + e.getDeltaY());
        });

        background = (ImageView) root.lookup("#background");
        mediumBackground = (ImageView) root.lookup("#mediumBackground") ;
        mediumSpread = (ImageView) root.lookup("#mediumSpread");
        hardBackground = (ImageView) root.lookup("#hardBackground") ;

        this.primaryStage.setScene(scene);
        this.primaryStage.setFullScreenExitHint ("");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    @Override
    public void init() throws IOException {
        personalScores = new ListView<>();
        startEasyGame = new Button();
        startMediumGame = new Button();
        startHardGame = new Button();
        newGame = new Button();
        returnMenu = new Button();
        easyGrid = new GridPane();
        mediumGrid = new GridPane();
        hardGrid = new GridPane();
        register = new Button();
        login = new Button();
        signOrReg = new Pane();
        gameModePane = new Pane();
        name = new TextField();
        password = new TextField();
        startAnchor = new AnchorPane();
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/game2.fxml")));
    }


    @FXML
    public void newGame() {

        switch (cubeList.size()) {

           case 6 -> setStartEasyGame();
           case 12 -> setStartMediumGame();
           case 20 -> setStartHardGame();
        }
    }

    @FXML
    public void returnMenu() {

        switch (cubeList.size()) {

            case 6 -> Platform.runLater(() ->
                    Effects.getInstance().gameZoomOut(
                            gameModePane,easyGrid,camera,startAnchor,background,
                            1000, 35, -145.5, 14.5));
            case 12 -> Platform.runLater(() ->
                    Effects.getInstance().gameZoomOut(
                            gameModePane,mediumGrid,camera,startAnchor,mediumBackground,
                            1000.9, 35, 117.0, 14.5));
            case 20 -> Platform.runLater(() ->
                    Effects.getInstance().gameZoomOut(
                            gameModePane,hardGrid,camera,startAnchor,hardBackground,
                            1000.7, 35, 380.0, 14.5));
        }
    }
    @FXML
    public void easyStartScreenPlay(){

        background.setOpacity(1);
        background.setVisible(true);
        mediumBackground.setOpacity(0);
        mediumSpread.setOpacity(0);
        hardBackground.setOpacity(0);
        hardSpread.setOpacity(0);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call(){
                Platform.runLater(() -> Effects.getInstance().gameZoomIn(camera, startAnchor, background,1000, 10, -145.5, 14.5,() -> {
                    Platform.runLater(() -> {

                        setStartEasyGame();

                    });
                }));
                return null;
            }
        };
        new Thread(task).start();

    }
    @FXML
    public void mediumStartScreenPlay(){

        mediumBackground.setVisible(true);
        mediumBackground.setOpacity(1);
        background.setOpacity(0);
        hardBackground.setOpacity(0);
        hardSpread.setOpacity(0);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> Effects.getInstance().gameZoomIn(camera, startAnchor, mediumBackground, 1000.9, 10, 117, 14.5,() -> {
                    Platform.runLater(() -> {

                        setStartMediumGame();

                    });
                }));
                return null;
            }
        };
        new Thread(task).start();

    }

    @FXML
    public void hardStartScreenPlay(){

        hardBackground.setVisible(true);
        hardBackground.setOpacity(1);
        background.setOpacity(0);
        mediumBackground.setOpacity(0);
        mediumSpread.setOpacity(0);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call(){
                Platform.runLater(() -> Effects.getInstance().gameZoomIn(camera, startAnchor, hardBackground,1000.7, 10, 380, 14.5,() -> {
                    Platform.runLater(() -> {

                        setStartHardGame();

                    });
                }));
                return null;
            }
        };
        new Thread(task).start();

    }

    @FXML
    public void setStartEasyGame(){

        Platform.runLater(() -> Effects.getInstance().backGroundIn(background));

        mediumGrid.setMouseTransparent(true);
        mediumGrid.setVisible(false);
        hardGrid.setMouseTransparent(true);
        hardGrid.setVisible(false);
        easyGrid.setMouseTransparent(false);
        easyGrid.setOpacity(1);
        easyGrid.setVisible(true);

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        easyCubeFactory = new EasyCubeFactory(this);
        easyGrid.getChildren().clear();
        controller.startEasyGame();
        gameModePane.setVisible(false);

    }

    @FXML
    public void setStartMediumGame(){

        Platform.runLater(() -> Effects.getInstance().backGroundIn(mediumBackground));

        easyGrid.setMouseTransparent(true);
        easyGrid.setVisible(false);
        hardGrid.setMouseTransparent(true);
        hardGrid.setVisible(false);
        mediumGrid.setMouseTransparent(false);
        mediumGrid.setVisible(true);
        mediumGrid.setOpacity(1);
        mediumGrid.setHgap(40);
        mediumGrid.setVgap(20);

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        mediumCubeFactory = new MediumCubeFactory(this);
        mediumGrid.getChildren().clear();
        controller.startMediumGame();
        gameModePane.setVisible(false);
    }

    @FXML
    public void setStartHardGame(){

        Platform.runLater(() -> Effects.getInstance().backGroundIn(hardBackground));

        easyGrid.setMouseTransparent(true);
        easyGrid.setVisible(false);
        mediumGrid.setMouseTransparent(true);
        mediumGrid.setVisible(false);
        hardGrid.setMouseTransparent(false);
        hardGrid.setVisible(true);
        hardGrid.setOpacity(1);
        hardGrid.setHgap(70);
        hardGrid.setVgap(50);

        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        hardCubeFactory = new HardCubeFactory(this);
        hardGrid.getChildren().clear();
        controller.startHardGame();
        gameModePane.setVisible(false);

    }

    @Override
    public void setEasyGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        easyCubeFactory.createCubics(easyGrid,memoryObjects);
    }

    @Override
    public void setMediumGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        mediumCubeFactory.createCubics(mediumGrid,memoryObjects);
    }

    @Override
    public void setHardGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        hardCubeFactory.createCubics(hardGrid,memoryObjects);
    }

    @Override
    public void clearStorage() {
        controller.clearStorage();
    }

    public void addToCubeList(BoxMaker cube) {
        cubeList.add(cube);
    }

    @Override
    public void clearPair(ArrayList<Integer> storage){

        cubeList.get(storage.get(0)).resetImage();
        cubeList.get(storage.get(1)).resetImage();
        clearStorage();
    }

    public void sendIdToEngine(int id) {
        controller.sendIdToEngine(id);
    }

    @Override
    public void getWorldScore(ArrayList<String> worldList) {
        // clears the list of previous scores
        worldScores.getItems().clear();
        // Create an observable list from the worldList
        ObservableList<String> worldObservable = FXCollections.observableArrayList();
        // Add all the elements from the worldList to the worldObservable
        worldObservable.addAll(worldList);
        // Add all the elements from the worldObservable to the worldScores list
        worldScores.getItems().addAll(worldObservable);
    }

    @Override
    public void setWorldScore() {
        // fetch scores in a new thread to avoid blocking the UI
        //         scoreController.fetchScores(ModeType.EASY);
        //        getWorldScore(scoreController.getScores(ModeType.EASY));
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    scoreController.fetchScores(ModeType.EASY);
                    scoreController.fetchScores(ModeType.MEDIUM);
                    scoreController.fetchScores(ModeType.HARD);
                    getWorldScore(scoreController.getScores(ModeType.EASY));
                });
                return null;
            }
        };
        new Thread(task).start();
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

    @FXML
    public void registerPane() {

        String user = name.getText();
        String userPassword = password.getText();

        if (controller.isLoggedIn() == true) {
            System.out.println("Already logged in");
            return;
        }
        if (controller.register(user, userPassword) == false) {
            System.out.println("Registration failed");
            return;
        }
        signOrReg.setVisible(false);

    }

    @FXML
    public void loginPane() {
        String user = name.getText();
        String userPassword = password.getText();
        try {
            controller.login(user, userPassword);
            if (controller.isLoggedIn() != true) {
                System.out.println("Login failed");
                return;
            }
            setPersonalScores(scoreController.getPersonalScores());
            signOrReg.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
