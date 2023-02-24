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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MemoryObject;
import model.ModeType;
import visuals.audio.AudioMemory;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.HardCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.cubeFactories.MediumCubeFactory;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static model.ModeType.*;

public class Gui extends Application implements IGui{

    private ModeType selectedDifficulty;
    private final IControllerVtoE controller = new Controller(this);
    private final IControllerScoreToV scoreController = new Controller(this);
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
    TextField name;
    @FXML
    TextField password;
    @FXML
    Pane gameModePane;
    @FXML
    AnchorPane startBlack;
    @FXML
    AnchorPane menuAnkkuri;
    @FXML
    Button newGame;
    @FXML
    Button returnMenu;
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

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {


        initOtto();

        this.primaryStage = primaryStage;
        this.scene = new Scene(root, 1250, 750);
        camera.setFieldOfView(25);
        this.scene.setCamera(camera);
        this.scene.getCamera().setNearClip(0.1);

        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        // If you want intro: "true", if not: "false". But is there life without intro?
        introOn(true);

        Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.MAIN));
        Platform.runLater(() -> Effects.getInstance().setGlow(pergament));
        Platform.runLater(() -> Effects.getInstance().playGlow());
        Visibilities.getInstance().setGridLayoutToVisibility(easyGrid,mediumGrid,hardGrid);
        Visibilities.getInstance().setGameBackGrounds(background,mediumBackground,mediumSpread,hardBackground,hardSpread);

        // If you want scores: "true", if not: "false".
        scoresOn(true);
    }

    public void initOtto() throws IOException {

        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/game2.fxml")));

        panesAndMisc();
        setIntroImages();
        setMenuImages();
        setGameImages();

        Effects.getInstance().setMiniImagesAndFrames(miniEasy, miniMedium, miniHard, easyFrame, mediumFrame, hardFrame);
        Effects.getInstance().setEssenceImages(japan,jungle,redtree);
        Effects.getInstance().setGeneralObjects(pergament, menuAnkkuri, startBlack, gameModePane, mtLista);
        Visibilities.getInstance().setToGameObjects(gameModePane,score,logAndReg,pergament);
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

            case 6 ->
                    Effects.getInstance().gameZoomOut(
                            easyGrid, background,
                            1000, 35, -145.5, 14.5, EASY);
            case 12 ->
                    Effects.getInstance().gameZoomOut(
                            mediumGrid, mediumBackground,
                            1001, 35, 117.2, -144.92, MEDIUM);
            case 20 ->
                    Effects.getInstance().gameZoomOut(
                            hardGrid, hardBackground,
                            1000.7, 35, 384.0, 14.5, ModeType.HARD);
        }

        Platform.runLater(() -> score.setVisible(false));
    }

    @FXML
    public void easyStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(EASY));

        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                background, 1000,
                10, -145.5, 14.5,
                EASY,this));

    }
    @FXML
    public void mediumStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(MEDIUM));

        Platform.runLater(() ->         Effects.getInstance().gameZoomIn(
                mediumBackground,
                1001, 10, 117.2, -144.92,
                MEDIUM,this));

    }

    @FXML
    public void hardStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(HARD));

        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                hardBackground, 1000.7,
                10, 384, 14.5,
                HARD,this));

    }

    public void startChoose(ModeType type) {

        switch (type) {

            case EASY -> setStartEasyGame();
            case MEDIUM -> setStartMediumGame();
            case HARD -> setStartHardGame();
        }


    }

    @FXML
    public void setStartEasyGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(easyGrid));

        if (cubeList != null) {cubeList.clear();}
        Platform.runLater(() -> Visibilities.getInstance().toGame());
        cubeList = new ArrayList<>();
        easyCubeFactory = new EasyCubeFactory(this);
        easyGrid.getChildren().clear();
        controller.startEasyGame();
    }

    @FXML
    public void setStartMediumGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(mediumGrid));

        if (cubeList != null) {cubeList.clear();}
        Platform.runLater(() -> Visibilities.getInstance().toGame());
        cubeList = new ArrayList<>();
        mediumCubeFactory = new MediumCubeFactory(this);
        mediumGrid.getChildren().clear();
        controller.startMediumGame();
    }

    @FXML
    public void setStartHardGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(hardGrid));

        if (cubeList != null) {cubeList.clear();}
        Platform.runLater(() -> Visibilities.getInstance().toGame());
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

    private void panesAndMisc(){

        startBlack = (AnchorPane) root.lookup("#startBlack");
        menuAnkkuri = (AnchorPane) root.lookup("#menuAnkkuri");
        weDidIt = (Label) root.lookup("#weDidIt");
        groupFour = (Label) root.lookup("#groupFour");
        gameModePane = (Pane) root.lookup("#gameModePane");
        score = (Pane) root.lookup("#score");
        easyGrid = (GridPane) root.lookup("#easyGrid");
        mediumGrid = (GridPane) root.lookup("#mediumGrid");
        hardGrid = (GridPane) root.lookup("#hardGrid");
        logAndReg = (Pane) root.lookup("#logAndReg");

        personalScores = new ListView<>();
        startEasyGame = new Button();
        startMediumGame = new Button();
        startHardGame = new Button();
        newGame = new Button();
        returnMenu = new Button();

    }
    private void setIntroImages() {

        sun = (ImageView) root.lookup("#sun");
        sun.setImage(ImageCache.getInstance().getIntroCache().get(0));
        lightning = (ImageView) root.lookup("#lightning");
        lightning.setImage(ImageCache.getInstance().getIntroCache().get(1));
        blacksun = (ImageView) root.lookup("#blacksun");
        blacksun.setImage(ImageCache.getInstance().getIntroCache().get(2));
    }

    private void setMenuImages() {

        pergament = (ImageView) root.lookup("#pergament");
        pergament.setImage(ImageCache.getInstance().getMenuCache().get(0));
        miniEasy = (ImageView) root.lookup("#miniEasy");
        miniEasy.setImage(ImageCache.getInstance().getMenuCache().get(1));
        miniMedium = (ImageView) root.lookup("#miniMedium");
        miniMedium.setImage(ImageCache.getInstance().getMenuCache().get(2));
        miniHard = (ImageView) root.lookup("#miniHard");
        miniHard.setImage(ImageCache.getInstance().getMenuCache().get(3));
        easyFrame = (ImageView) root.lookup("#easyFrame");
        easyFrame.setImage(ImageCache.getInstance().getMenuCache().get(4));
        mediumFrame = (ImageView) root.lookup("#mediumFrame");
        mediumFrame.setImage(ImageCache.getInstance().getMenuCache().get(5));
        hardFrame = (ImageView) root.lookup("#hardFrame");
        hardFrame.setImage(ImageCache.getInstance().getMenuCache().get(6));
        japan = (ImageView) root.lookup("#japan");
        japan.setImage(ImageCache.getInstance().getMenuCache().get(7));
        jungle = (ImageView) root.lookup("#jungle");
        jungle.setImage(ImageCache.getInstance().getMenuCache().get(8));
        redtree = (ImageView) root.lookup("#redtree");
        redtree.setImage(ImageCache.getInstance().getMenuCache().get(9));

        mt1 = (ImageView) root.lookup("#mt1");
        mt1.setImage(ImageCache.getInstance().getMenuCache().get(10));
        mt2 = (ImageView) root.lookup("#mt2");
        mt2.setImage(ImageCache.getInstance().getMenuCache().get(11));
        mt3 = (ImageView) root.lookup("#mt3");
        mt3.setImage(ImageCache.getInstance().getMenuCache().get(12));
        mt4 = (ImageView) root.lookup("#mt4");
        mt4.setImage(ImageCache.getInstance().getMenuCache().get(13));
        mt5 = (ImageView) root.lookup("#mt5");
        mt5.setImage(ImageCache.getInstance().getMenuCache().get(14));
        mt6 = (ImageView) root.lookup("#mt6");
        mt6.setImage(ImageCache.getInstance().getMenuCache().get(15));
        mt7 = (ImageView) root.lookup("#mt7");
        mt7.setImage(ImageCache.getInstance().getMenuCache().get(16));
        mt8 = (ImageView) root.lookup("#mt8");
        mt8.setImage(ImageCache.getInstance().getMenuCache().get(17));
        mt9 = (ImageView) root.lookup("#mt9");
        mt9.setImage(ImageCache.getInstance().getMenuCache().get(18));
        mt10 = (ImageView) root.lookup("#mt10");
        mt10.setImage(ImageCache.getInstance().getMenuCache().get(19));
        mt11 = (ImageView) root.lookup("#mt11");
        mt11.setImage(ImageCache.getInstance().getMenuCache().get(20));
        mt12 = (ImageView) root.lookup("#mt12");
        mt12.setImage(ImageCache.getInstance().getMenuCache().get(21));
        mt13 = (ImageView) root.lookup("#mt13");
        mt13.setImage(ImageCache.getInstance().getMenuCache().get(22));

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

    }

    private void setGameImages() {

        background = (ImageView) root.lookup("#background");
        background.setImage(ImageCache.getInstance().getGameBackGroundCache().get(0));
        mediumBackground = (ImageView) root.lookup("#mediumBackground");
        mediumSpread = (ImageView) root.lookup("#mediumSpread");
        mediumBackground.setImage(ImageCache.getInstance().getGameBackGroundCache().get(1));
        mediumSpread.setImage(ImageCache.getInstance().getGameBackGroundCache().get(1));
        hardBackground = (ImageView) root.lookup("#hardBackground");
        hardSpread = (ImageView) root.lookup("#hardSpread");
        hardBackground.setImage(ImageCache.getInstance().getGameBackGroundCache().get(2));
        hardSpread.setImage(ImageCache.getInstance().getGameBackGroundCache().get(2));

    }

    private void introOn(Boolean introStatus) {

        if(introStatus) {

            Platform.runLater(() -> Effects.getInstance().bringGameUp(
                    weDidIt, groupFour, logAndReg,
                    sun, lightning, blacksun,
                    easyFrame,mediumFrame, hardFrame));

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
            redtree.setOpacity(0.35);
        }
    }

    private void scoresOn(Boolean on) {

        Node worldScoresNode = root.lookup("#worldScores");
        if (worldScoresNode instanceof ListView<?>) {
            worldScores = (ListView<String>) worldScoresNode;
        }
        if (on) {fetchAllScores();}
    }


}
