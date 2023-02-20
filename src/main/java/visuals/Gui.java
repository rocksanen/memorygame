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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.MemoryObject;
import model.ModeType;
import model.Scoreboard;
import visuals.audio.AudioMemory;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.HardCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.cubeFactories.MediumCubeFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static model.ModeType.*;

public class Gui extends Application implements IGui {

    private ModeType selectedDifficulty;


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
    static GridPane easyGrid;

    @FXML
    static GridPane mediumGrid;

    @FXML
    static GridPane hardGrid;
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

    @FXML
    Pane gameModePane;

    @FXML
    AnchorPane startAnchor;
    @FXML
    Button newGame;
    @FXML
    Button returnMenu;

    @FXML
    ImageView regLog;

    @FXML
    AnchorPane startBlack;

    @FXML
    Label weDidIt;
    @FXML
    Label groupFour;

    @FXML
    ImageView pergament;

    @FXML Pane score;
    @FXML ImageView sun;

    @FXML ImageView lightning;
    @FXML ImageView blacksun;
    @FXML ImageView mt1;
    @FXML ImageView mt2;
    @FXML ImageView mt3;
    @FXML ImageView mt4;
    @FXML ImageView mt5;
    @FXML ImageView mt6;
    @FXML ImageView mt7;
    @FXML ImageView mt8;
    @FXML ImageView mt9;
    @FXML ImageView mt10;
    @FXML ImageView mt11;
    @FXML ImageView mt12;
    @FXML ImageView mt13;
    @FXML ImageView miniEasy;
    @FXML ImageView miniMedium;
    @FXML ImageView miniHard;

    @FXML ImageView easyFrame;
    @FXML ImageView mediumFrame;
    @FXML ImageView hardFrame;
    @FXML ImageView japan;
    @FXML ImageView jungle;
    @FXML ImageView redtree;

    @FXML static Pane logAndReg;




    ArrayList<BoxMaker> cubeList;
    ICubeFactory easyCubeFactory;
    ICubeFactory mediumCubeFactory;
    ICubeFactory hardCubeFactory;
    Parent root;
    Scene scene;

    private final ArrayList<ImageView> mtLista = new ArrayList<>();


    public static PerspectiveCamera camera = new PerspectiveCamera();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.scene = new Scene(root, 1250, 750);
        camera.setFieldOfView(25);
        this.scene.setCamera(camera);

        background = (ImageView) root.lookup("#background");
        mediumBackground = (ImageView) root.lookup("#mediumBackground");
        mediumSpread = (ImageView) root.lookup("#mediumSpread");
        hardBackground = (ImageView) root.lookup("#hardBackground");
        startBlack = (AnchorPane) root.lookup("#startBlack");
        weDidIt = (Label) root.lookup("#weDidIt");
        groupFour = (Label) root.lookup("#groupFour");
        pergament = (ImageView) root.lookup("#pergament");
        gameModePane = (Pane) root.lookup("#gameModePane");
        score = (Pane) root.lookup("#score");
        sun = (ImageView) root.lookup("#sun");
        lightning = (ImageView) root.lookup("#lightning");
        blacksun = (ImageView) root.lookup("#blacksun");
        easyGrid = (GridPane) root.lookup("#easyGrid");
        mediumGrid = (GridPane) root.lookup("#mediumGrid");
        hardGrid = (GridPane) root.lookup("#hardGrid");
        mt1 = (ImageView) root.lookup("#mt1");
        mt2 = (ImageView) root.lookup("#mt2");
        mt3 = (ImageView) root.lookup("#mt3");
        mt4 = (ImageView) root.lookup("#mt4");
        mt5 = (ImageView) root.lookup("#mt5");
        mt6 = (ImageView) root.lookup("#mt6");
        mt7 = (ImageView) root.lookup("#mt7");
        mt8 = (ImageView) root.lookup("#mt8");
        mt9 = (ImageView) root.lookup("#mt9");
        mt10 = (ImageView) root.lookup("#mt10");
        mt11 = (ImageView) root.lookup("#mt11");
        mt12 = (ImageView) root.lookup("#mt12");
        mt13 = (ImageView) root.lookup("#mt13");
        miniEasy = (ImageView) root.lookup("#miniEasy");
        miniMedium = (ImageView) root.lookup("#miniMedium");
        miniHard = (ImageView) root.lookup("#miniHard");
        easyFrame = (ImageView) root.lookup("#easyFrame");
        mediumFrame = (ImageView) root.lookup("#mediumFrame");
        hardFrame = (ImageView) root.lookup("#hardFrame");
        japan = (ImageView) root.lookup("#japan");
        jungle = (ImageView) root.lookup("#jungle");
        redtree = (ImageView) root.lookup("#redtree");
        logAndReg = (Pane) root.lookup("#logAndReg");



        mtLista.add(mt1);
        mtLista.add(mt2);
        mtLista.add(mt3);
        mtLista.add(mt4);
        mtLista.add(mt5);
        mtLista.add(mt6);
        mtLista.add(mt7);
        mtLista.add(mt8);
        mtLista.add(mt9);
        mtLista.add(mt10);
        mtLista.add(mt11);
        mtLista.add(mt12);
        mtLista.add(mt13);



        this.primaryStage.setScene(scene);
        this.primaryStage.setFullScreenExitHint("");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.MAIN));

        // If you want intro: "true", if not: "false". But is there life without intro?
        introOn(true);

        Platform.runLater(() -> Effects.getInstance().setGlow(pergament));
        Platform.runLater(() -> Effects.getInstance().playGlow());
        Visibilities.getInstance().setGridLayoutToVisibility(easyGrid,mediumGrid,hardGrid);


        Node worldScoresNode = root.lookup("#worldScores");
        if (worldScoresNode instanceof ListView<?>) {
            worldScores = (ListView<String>) worldScoresNode;
            fetchAllScores();
        }
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
        weDidIt = new Label();
        groupFour = new Label();
        startBlack = new AnchorPane();
        name = new TextField();
        pergament = new ImageView();
        password = new TextField();
        startAnchor = new AnchorPane();
        score = new Pane();
        sun = new ImageView();
        lightning = new ImageView();
        blacksun = new ImageView();
        miniEasy = new ImageView();
        miniMedium = new ImageView();
        miniHard = new ImageView();
        logAndReg = new Pane();

        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/game2.fxml")));
    }

    private void introOn(Boolean introStatus) {

        if(introStatus) {

            Platform.runLater(() -> Effects.getInstance().bringGameUp(
                    startBlack, weDidIt, groupFour,
                    gameModePane, logAndReg, sun,
                    lightning, blacksun, mtLista,
                    miniEasy,miniMedium,miniHard,
                    easyFrame,mediumFrame,hardFrame,
                    japan,jungle,redtree));

        }else{

            startBlack.setVisible(false);
            gameModePane.setOpacity(1);
            logAndReg.setVisible(true);
            logAndReg.setOpacity(1);
            miniEasy.setOpacity(1);
            miniMedium.setOpacity(1);
            miniHard.setOpacity(1);
            easyFrame.setOpacity(1);
            mediumFrame.setOpacity(1);
            hardFrame.setOpacity(1);
            japan.setOpacity(0.26);
            jungle.setOpacity(0.2);
            redtree.setOpacity(0.25);
        }
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
                            gameModePane, easyGrid, camera, startAnchor, background,
                            1000, 35, -145.5, 14.5, EASY, pergament));
            case 12 -> Platform.runLater(() ->
                    Effects.getInstance().gameZoomOut(
                            gameModePane, mediumGrid, camera, startAnchor, mediumBackground,
                            1001, 35, 117.2, -144.92, MEDIUM, pergament));
            case 20 -> Platform.runLater(() ->
                    Effects.getInstance().gameZoomOut(
                            gameModePane, hardGrid, camera, startAnchor, hardBackground,
                            1000.7, 35, 384.0, 14.5, ModeType.HARD, pergament));
        }

        Platform.runLater(() -> Effects.getInstance().playGlow());
        Platform.runLater(() -> score.setVisible(false));
    }

    @FXML
    public void easyStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> gameBackGroundVisibility(EASY));

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                        camera, startAnchor, background, 1000,
                        10, -145.5, 14.5, () -> {

                            Platform.runLater(() -> AudioMemory.getInstance().stopSong(ModeType.MAIN));
                            Platform.runLater(() -> AudioMemory.getInstance().playSong(EASY));
                            setStartEasyGame();
                        }));
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    public void mediumStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> gameBackGroundVisibility(MEDIUM));

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                        camera, startAnchor, mediumBackground,
                        1001, 10, 117.2, -144.92, () -> {

                            Platform.runLater(() -> AudioMemory.getInstance().stopSong(ModeType.MAIN));
                            Platform.runLater(() -> AudioMemory.getInstance().playSong(MEDIUM));
                            setStartMediumGame();
                        }));
                return null;
            }
        };
        new Thread(task).start();

    }

    @FXML
    public void hardStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> gameBackGroundVisibility(HARD));

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {

                Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                        camera, startAnchor, hardBackground, 1000.7,
                        10, 384, 14.5, () -> {

                            Platform.runLater(() -> AudioMemory.getInstance().stopSong(ModeType.MAIN));
                            Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.HARD));
                            setStartHardGame();
                        }));
                return null;
            }
        };
        new Thread(task).start();
    }

    private void gameBackGroundVisibility(ModeType type) {

        switch (type) {

            case EASY -> {
                background.setVisible(true);
                background.setOpacity(1);
                mediumBackground.setOpacity(0);
                mediumSpread.setOpacity(0);
                hardBackground.setOpacity(0);
                hardSpread.setOpacity(0);
            }
            case MEDIUM -> {
                mediumBackground.setVisible(true);
                mediumSpread.setVisible(true);
                mediumBackground.setOpacity(1);
                mediumSpread.setOpacity(1);
                background.setOpacity(0);
                hardBackground.setOpacity(0);
                hardSpread.setOpacity(0);
            }
            case HARD -> {
                hardBackground.setVisible(true);
                hardSpread.setVisible(true);
                hardBackground.setOpacity(1);
                hardSpread.setOpacity(1);
                background.setOpacity(0);
                mediumBackground.setOpacity(0);
                mediumSpread.setOpacity(0);
            }
        }
    }

    @FXML
    public void setStartEasyGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(easyGrid));

        if (cubeList != null) {cubeList.clear();}
        Platform.runLater(() -> Visibilities.getInstance().toGame(gameModePane,score,logAndReg,pergament));
        cubeList = new ArrayList<>();
        easyCubeFactory = new EasyCubeFactory(this);
        easyGrid.getChildren().clear();
        controller.startEasyGame();
    }

    @FXML
    public void setStartMediumGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(mediumGrid));

        if (cubeList != null) {cubeList.clear();}
        Platform.runLater(() -> Visibilities.getInstance().toGame(gameModePane,score,logAndReg,pergament));
        cubeList = new ArrayList<>();
        mediumCubeFactory = new MediumCubeFactory(this);
        mediumGrid.getChildren().clear();
        controller.startMediumGame();
    }

    @FXML
    public void setStartHardGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(hardGrid));

        if (cubeList != null) {cubeList.clear();}
        Platform.runLater(() -> Visibilities.getInstance().toGame(gameModePane,score,logAndReg,pergament));
        cubeList = new ArrayList<>();
        hardCubeFactory = new HardCubeFactory(this);
        hardGrid.getChildren().clear();
        controller.startHardGame();
    }

    @Override
    public void setEasyGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        selectedDifficulty = EASY;
        easyCubeFactory.createCubics(easyGrid, memoryObjects);

        setPersonalScores(scoreController.getPersonalScores(EASY));
        getWorldScore(scoreController.getScores(EASY));
    }

    @Override
    public void setMediumGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        selectedDifficulty = MEDIUM;
        mediumCubeFactory.createCubics(mediumGrid, memoryObjects);

        setPersonalScores(scoreController.getPersonalScores(MEDIUM));
        getWorldScore(scoreController.getScores(MEDIUM));

    }

    @Override
    public void setHardGame(ArrayList<MemoryObject> memoryObjects) throws FileNotFoundException {

        selectedDifficulty = HARD;
        hardCubeFactory.createCubics(hardGrid, memoryObjects);

        setPersonalScores(scoreController.getPersonalScores(HARD));
        getWorldScore(scoreController.getScores(HARD));
    }



    public void gameOver() {

        switch (selectedDifficulty) {
            case EASY -> {
                setPersonalScores(scoreController.getPersonalScores(EASY));
                getWorldScore(scoreController.getScores(EASY));
            }
            case MEDIUM -> {
                setPersonalScores(scoreController.getPersonalScores(MEDIUM));
                getWorldScore(scoreController.getScores(MEDIUM));
            }
            case HARD -> {
                setPersonalScores(scoreController.getPersonalScores(HARD));
                getWorldScore(scoreController.getScores(HARD));
            }
        }

        System.out.println("game over");
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
        // clears the list of previous scores
        Platform.runLater(() -> worldScores.getItems().clear());
        // Create an observable list from the worldList
        ObservableList<String> worldObservable = FXCollections.observableArrayList();
        // Add all the elements from the worldList to the worldObservable
        worldObservable.addAll(worldList);
        // Add all the elements from the worldObservable to the worldScores list
        Platform.runLater(() -> worldScores.getItems().addAll(worldObservable));
    }

    public void fetchAllScores() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                scoreController.fetchScores(EASY);
                scoreController.fetchScores(MEDIUM);
                scoreController.fetchScores(HARD);
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
        Platform.runLater(() -> personalScores.getItems().clear());
        ObservableList<String> personObservable = FXCollections.observableArrayList();
        personObservable.clear();
        personObservable.addAll(personalList);
        Platform.runLater(() -> personalScores.getItems().addAll(personObservable));
    }

    @FXML
    public void registerPane() {

        String user = name.getText();
        String userPassword = password.getText();

        if (controller.isLoggedIn()) {
            System.out.println("Already logged in");
            return;
        }
        if (!controller.register(user, userPassword)) {
            System.out.println("Registration failed");
            return;
        }
        logAndReg.setVisible(false);

    }

    @Override
    public void fetchUserScores() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                scoreController.fetchPersonalScores();
                return null;
            }
        };
        new Thread(task).start();
    }


    @FXML
    public void loginPane() {
        String user = name.getText();
        String userPassword = password.getText();
        try {
            controller.login(user, userPassword);
            if (!controller.isLoggedIn()) {
                System.out.println("Login failed");
                return;
            }
            fetchUserScores();
            logAndReg.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hover(ImageView imageView) {
        Glow glow = new Glow();
        glow.setLevel(0.2);
        imageView.setEffect(glow);

    }

    @FXML
    public void unhover(ImageView image) {
        image.setEffect(null);
    }
}
