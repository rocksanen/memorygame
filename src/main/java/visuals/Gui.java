package visuals;

import controller.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
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
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import static model.ModeType.*;

public class Gui extends Application implements IGui, IChartGUI {

    private ModeType selectedDifficulty;
    private final IChartController scoreController2 = new ChartController(this);
    private final IGameController controller = new GameController(this);
    private final IScoreController scoreController = new ScoreController(this);
    Stage primaryStage;

    @FXML
    Button buttonLogout;
    @FXML
    Label labelLoggedIn;
    @FXML
    Button startEasyGame;
    @FXML
    Button startMediumGame;
    @FXML
    Button startHardGame;

    @FXML
    Button stats;
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
    @FXML
    ImageView hardSpread;
    @FXML
    ImageView mediumSpread;
    @FXML
    ImageView midgrid;
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
    ImageView groupFour;
    @FXML
    ImageView pergament;
    @FXML
    Pane score;
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
    static Pane logAndReg;
    @FXML
    ImageView dirt;
    @FXML
    ImageView burningsun;
    @FXML
    ImageView memomaze;
    @FXML
    ImageView midR;
    @FXML
    ImageView midTop;
    @FXML
    ImageView midL;
    @FXML
    ImageView midBot;
    @FXML
    ImageView midend;
    @FXML
    ImageView easyTop;
    @FXML
    ImageView easyL;
    @FXML
    ImageView easyBot;

    @FXML
    Pane paneLogin;

    @FXML
    Label w1;
    @FXML
    Label w2;
    @FXML
    Label w3;
    @FXML
    Label w4;
    @FXML
    Label w5;
    @FXML
    Label w6;
    @FXML
    Label w7;
    @FXML
    Label w8;
    @FXML
    Label w9;
    @FXML
    Label w10;

    @FXML
    Label p1;
    @FXML
    Label p2;
    @FXML
    Label p3;
    @FXML
    Label p4;
    @FXML
    Label p5;
    @FXML
    Label p6;
    @FXML
    Label p7;
    @FXML
    Label p8;
    @FXML
    Label p9;
    @FXML
    Label p10;
    @FXML
    ImageView hardGridImage;
    @FXML
    ImageView hardR;
    @FXML
    ImageView hardL;
    @FXML
    ImageView loading;
    @FXML ImageView easydes1;
    @FXML ImageView easydes2;
    @FXML ImageView easydes3;
    @FXML ImageView medes1;
    @FXML ImageView medes2;
    @FXML ImageView medes3;
    @FXML ImageView hardes1;
    @FXML ImageView hardes2;
    @FXML ImageView hardes3;
    @FXML ImageView kotoku;
    @FXML ImageView tigerden;
    @FXML ImageView treeoflife;
    @FXML ImageView telkku;
    @FXML ImageView play;
    @FXML ImageView returngame;

    @FXML ImageView movingjungle;

    @FXML ImageView easyend;
    @FXML ImageView midneo;
    @FXML ImageView midneo2;
    @FXML ImageView midneo3;
    @FXML ImageView midneo4;

    @FXML ImageView easyneo;
    @FXML ImageView hardneo;


    private static final ArrayList<Label> worldLabels = new ArrayList<>();
    private static final ArrayList<Label> personalLabels = new ArrayList<>();

    private final Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);

    private final Rotate jungleZ = new Rotate(0, Rotate.Z_AXIS);
    private ArrayList<BoxMaker> cubeList;
    private ICubeFactory easyCubeFactory;
    private ICubeFactory mediumCubeFactory;
    private ICubeFactory hardCubeFactory;
    private Parent root;
    private Scene scene;
    private static final ArrayList<Group> activeList = new ArrayList<>();
    public boolean isReturnStatus() {
        return returnStatus;
    }
    private boolean returnStatus;
    private boolean playIntro = true;

    @Override
    public void getTime(int i) {

        /*
        if (i <= 0) {
            returnMenu();
        }

         */

    }

    public int getActiveID() {
        return activeID;
    }

    private int activeID;
    public static PerspectiveCamera camera = new PerspectiveCamera();

    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been fully loaded.
     */
    @FXML
    private void initialize() {}

    /**
     * Loads the properties file and sets the playIntro boolean value.
     */
    private void loadProperties() {
        // you need config.properties file in your resources directory. playIntro=[boolean] value is checked from there
        try (InputStream input = Gui.class.getClassLoader().getResource("config.properties").openStream()) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            System.out.println("playIntro value from properties: " + prop.getProperty("playIntro"));
            playIntro = Boolean.parseBoolean(prop.getProperty("playIntro"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws IOException {

        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });

        loadProperties();
        initGoods();

        this.primaryStage = primaryStage;
        this.scene = new Scene(root, 1250, 750);
        camera.setFieldOfView(25);
        this.scene.setCamera(camera);
        this.scene.getCamera().setNearClip(0.1);

        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        // If you want scores: "true", if not: "false".
        scoresOn(true);
        introOn(playIntro);
    }

    public void initGoods() throws IOException {

        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/game2.fxml")));

        panesAndMisc();
        setIntroImages();
        setMenuImages();
        setGameImages();

        Effects.getInstance().setMiniImagesAndFrames(miniEasy, miniMedium, miniHard, easyFrame, mediumFrame, hardFrame);
        Effects.getInstance().setEssenceImages(japan, jungle, redtree);
        Effects.getInstance().setGeneralObjects(pergament, menuAnkkuri, startBlack, gameModePane, burningsun, labelLoggedIn, telkku);
        Visibilities.getInstance().setToGameObjects(gameModePane, score, logAndReg, pergament);
        Visibilities.getInstance().setGridLayoutToVisibility(easyGrid, mediumGrid, hardGrid);
        Visibilities.getInstance().setGameBackGrounds(
                background, mediumBackground, mediumSpread,
                hardBackground, hardSpread, midgrid, midR,
                midTop, midL, midBot, midend, easyTop, easyL, easyBot);
        Effects.getInstance().setBackGrounds(
                mediumBackground, midgrid, midTop,
                midL, midBot, easyTop, easyL,
                easyBot, hardGridImage, hardR,
                hardL, mediumSpread, dirt, play, returngame, movingjungle,
                easyend,midneo,midneo2,midneo3,midneo4,easyneo,hardneo);
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

        returnStatus = true;
        controller.sendReturnSignal();
        returnStatus = false;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0))
        );

        timeline.play();


        switch (cubeList.size()) {

            case 6 -> Effects.getInstance().gameZoomOut(
                    easyGrid, background,
                    800, 35, -145.5, 14.5, EASY);
            case 12 -> Effects.getInstance().gameZoomOut(
                    mediumGrid, mediumBackground,
                    1071, 35, 117.2, -144.92, MEDIUM);

            case 20 -> Effects.getInstance().gameZoomOut(
                    hardGrid, hardBackground,
                    1000.7, 35, 384.0, 14.5, ModeType.HARD);
        }


        Platform.runLater(() -> score.setVisible(false));
        miniEasy.setMouseTransparent(false);
        miniMedium.setMouseTransparent(false);
        miniHard.setMouseTransparent(false);
    }

    @FXML
    public void easyStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(EASY));
        Platform.runLater(() -> logAndReg.setVisible(false));
        miniEasy.setMouseTransparent(true);

        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                background, 803,
                10, -145.5, 14.5,
                EASY, this));

    }

    @FXML
    public void mediumStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(MEDIUM));
        Platform.runLater(() -> logAndReg.setVisible(false));
        miniMedium.setMouseTransparent(true);


        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                mediumBackground,
                1071, 10, 117.2, -144.92,
                MEDIUM, this));

    }

    @FXML
    public void hardStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(HARD));
        Platform.runLater(() -> logAndReg.setVisible(false));
        miniHard.setMouseTransparent(true);


        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                hardBackground, 1002,
                10, 384, 14,
                HARD, this));

    }

    public void startChoose(ModeType type) {
        switch (type) {

            case EASY -> setStartEasyGame();
            case MEDIUM -> setStartMediumGame();
            case HARD -> setStartHardGame();
        }

        Platform.runLater(() -> Visibilities.getInstance().toGame());
        if (AudioMemory.noIntro) {
            Platform.runLater(() -> AudioMemory.getInstance().stopTheIntro());
        }

    }

    @FXML
    public void setStartEasyGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(easyGrid));
        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        easyGrid.getChildren().clear();
        easyCubeFactory = new EasyCubeFactory(this);
        controller.startEasyGame();
    }

    @FXML
    public void setStartMediumGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(mediumGrid));
        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        mediumGrid.getChildren().clear();
        mediumCubeFactory = new MediumCubeFactory(this);
        controller.startMediumGame();
    }

    @FXML
    public void setStartHardGame() {

        Platform.runLater(() -> Visibilities.getInstance().inGameGrid(hardGrid));
        if (cubeList != null) {
            cubeList.clear();
        }
        cubeList = new ArrayList<>();
        hardGrid.getChildren().clear();
        hardCubeFactory = new HardCubeFactory(this);
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

        int firstIndex = storage.get(0);
        int secondIndex = storage.get(1);

        cubeList.get(firstIndex).resetImage();
        cubeList.get(secondIndex).resetImage();
        clearStorage();

        CompletableFuture.runAsync(() -> {

            try {

                Thread.sleep(700);
                cubeList.get(firstIndex).setActive();
                cubeList.get(secondIndex).setActive();
                cubeList.get(firstIndex).getBox().setMouseTransparent(false);
                cubeList.get(secondIndex).getBox().setMouseTransparent(false);

                for (BoxMaker cube : cubeList) {

                    if (!cube.getActiveState()) {
                        cube.getBox().setMouseTransparent(false);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void setActiveID(int activeID) {

        if (!cubeList.get(activeID).getActiveState()) {

            cubeList.get(activeID).getBox().setMouseTransparent(true);
            cubeList.get(activeID).setActive();

            if (activeList.size() < 2) {
                activeList.add(cubeList.get(activeID).getBox());
            }
        }

        if (activeList.size() == 2) {
            for (BoxMaker cube : cubeList) {
                if (!cube.getActiveState()) {
                    cube.getBox().setMouseTransparent(true);
                }
            }
            activeList.clear();
        }
    }

    @Override
    public void compareFoundMatch() {

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
                for (BoxMaker cube : cubeList) {
                    if (!cube.getActiveState()) {
                        cube.getBox().setMouseTransparent(false);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void sendIdToEngine(int id) {
        controller.sendIdToEngine(id);
    }


    public void fetchAllScores() {
        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() {
                try {
                    database.datasource.SqlJpaConn.getInstance();
                    scoreController.fetchPersonalScores();
                    scoreController.fetchScores(EASY);
                    scoreController.fetchScores(MEDIUM);
                    scoreController.fetchScores(HARD);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };

        // Add a listener to the task's value property to handle the result
        task.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Do something if the task returns true
                System.out.println("fetchallscores Task returned true");
            } else {
                // Do something if the task returns false
                System.out.println("fetchallscores Task returned false");
                // Show the error message.
                System.out.println("Error connecting to database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Virhe");
                alert.setHeaderText("Virhe..");
                alert.setContentText("Ei yhteyttä tietokantaan");
                alert.showAndWait();

            }
        });
        new Thread(task).start();
    }

    @Override
    public void getWorldScore(ArrayList<String> worldList) {


        for (int i = 0; i < 5; i++) {

            String[] words = worldList.get(i).split("\\s+");
            String name = words[0];
            name = name.substring(0, 3);
            String points = words[1];

            worldLabels.get(i).setText((i + 1) + "." + name.toUpperCase() + " " + points.toUpperCase());
        }
    }

    @Override
    public void setPersonalScores(ArrayList<String> personalList) {
        if (personalList == null) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            personalLabels.get(i).setText("");
        }

        for (int i = 0; i < 5; i++) {
//
//            String[] words = personalList.get(i).split("\\s+");
//            String name = words[0];
//            name = name.substring(0, 3);
//            String points = words[1];

            personalLabels.get(i).setText((i + 1) + "." + personalList.get(i));
        }
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
        paneLogin.setVisible(false);
        buttonLogout.setVisible(true);
        labelLoggedIn.setText("Logged in as " + controller.getUsername());
    }

    @Override
    public void fetchUserScores() {
        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() {
                try {
                    database.datasource.SqlJpaConn.getInstance();
                    scoreController.fetchPersonalScores();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };

        // Add a listener to the task's value property to handle the result
        task.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Do something if the task returns true
                System.out.println("fetchallscores Task returned true");
            } else {
                // Do something if the task returns false
                System.out.println("fetchallscores Task returned false");
                // Show the error message.
                System.out.println("Error connecting to database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Virhe");
                alert.setHeaderText("Virhe..");
                alert.setContentText("Ei yhteyttä tietokantaan");
                alert.showAndWait();

            }
        });
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
                stats.setVisible(false);
                return;
            }
            fetchUserScores();
            paneLogin.setVisible(false);
            buttonLogout.setVisible(true);
            stats.setVisible(true);

            labelLoggedIn.setText("Logged in as " + controller.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void panesAndMisc() {

        startBlack = (AnchorPane) root.lookup("#startBlack");
        menuAnkkuri = (AnchorPane) root.lookup("#menuAnkkuri");
        weDidIt = (Label) root.lookup("#weDidIt");
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

        labelLoggedIn = (Label) root.lookup("#labelLoggedIn");

        buttonLogout = (Button) root.lookup("#buttonLogout");
        stats = (Button) root.lookup("#stats");

        login = (Button) root.lookup("#login");
        register = (Button) root.lookup("#register");
        name = (TextField) root.lookup("#name");
        password = (TextField) root.lookup("#password");
        paneLogin = (Pane) root.lookup("#paneLogin");

        w1 = (Label) root.lookup("#w1");
        w2 = (Label) root.lookup("#w2");
        w3 = (Label) root.lookup("#w3");
        w4 = (Label) root.lookup("#w4");
        w5 = (Label) root.lookup("#w5");
        w6 = (Label) root.lookup("#w6");
        w7 = (Label) root.lookup("#w7");
        w8 = (Label) root.lookup("#w8");
        w9 = (Label) root.lookup("#w9");
        w10 = (Label) root.lookup("#w10");


        worldLabels.add(w1);
        worldLabels.add(w2);
        worldLabels.add(w3);
        worldLabels.add(w4);
        worldLabels.add(w5);
        worldLabels.add(w6);
        worldLabels.add(w7);
        worldLabels.add(w8);
        worldLabels.add(w9);
        worldLabels.add(w10);

        p1 = (Label) root.lookup("#p1");
        p2 = (Label) root.lookup("#p2");
        p3 = (Label) root.lookup("#p3");
        p4 = (Label) root.lookup("#p4");
        p5 = (Label) root.lookup("#p5");
        p6 = (Label) root.lookup("#p6");
        p7 = (Label) root.lookup("#p7");
        p8 = (Label) root.lookup("#p8");
        p9 = (Label) root.lookup("#p9");
        p10 = (Label) root.lookup("#p10");

        personalLabels.add(p1);
        personalLabels.add(p2);
        personalLabels.add(p3);
        personalLabels.add(p4);
        personalLabels.add(p5);
        personalLabels.add(p6);
        personalLabels.add(p7);
        personalLabels.add(p8);
        personalLabels.add(p9);
        personalLabels.add(p10);

        URL url = Gui.class.getClassLoader().getResource("fonts/outrun_future.otf");
        // get the font from the resources, set size and add it to the label
        Font outrun = Font.loadFont(url.toExternalForm(), 13);
        labelLoggedIn.setFont(outrun);
        labelLoggedIn.setStyle("-fx-background-color: rgba(0,0,0,0.50);-fx-background-radius: 5; -fx-padding: 1 6 1 6");


        buttonLogout.setFont(outrun);
        // make button logout purple with shadow, white text and hover effect
        buttonLogout.setStyle(
                "-fx-background-color: rgba(0,0,0,0.50); -fx-background-radius: 5; -fx-padding: 1 2 1 2; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        stats.setFont(outrun);
        stats.setStyle(
                "-fx-background-color: rgba(0,0,0,0.50); -fx-background-radius: 5; -fx-padding: 1 2 1 2; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");


        login.setFont(outrun);
        login.setStyle(
                "-fx-background-color: rgba(0,0,0,0.50); -fx-background-radius: 5; -fx-padding: 1 2 1 2; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        register.setFont(outrun);
        register.setStyle(
                "-fx-background-color: rgba(0,0,0,0.50); -fx-background-radius: 5; -fx-padding: 1 2 1 2; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

    }

    private void setIntroImages() {

        sun = (ImageView) root.lookup("#sun");
        sun.setImage(ImageCache.getInstance().getIntroCache().get(0));
        lightning = (ImageView) root.lookup("#lightning");
        lightning.setImage(ImageCache.getInstance().getIntroCache().get(1));
        blacksun = (ImageView) root.lookup("#blacksun");
        blacksun.setImage(ImageCache.getInstance().getIntroCache().get(2));
        memomaze = (ImageView) root.lookup("#memomaze");
        memomaze.setImage(ImageCache.getInstance().getIntroCache().get(3));
        loading = (ImageView) root.lookup("#loading");
        loading.setImage(ImageCache.getInstance().getIntroCache().get(4));
        groupFour = (ImageView) root.lookup("#groupFour");
        groupFour.setImage(ImageCache.getInstance().getIntroCache().get(5));
    }

    private void setMenuImages() {

        burningsun = (ImageView) root.lookup("#burningsun");
        burningsun.setImage(ImageCache.getInstance().getMenuCache().get(24));
        dirt = (ImageView) root.lookup("#dirt");
        dirt.setImage(ImageCache.getInstance().getMenuCache().get(23));
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
        easydes1 = (ImageView) root.lookup("#easydes1");
        easydes1.setImage(ImageCache.getInstance().getMenuCache().get(25));
        easydes2 = (ImageView) root.lookup("#easydes2");
        easydes2.setImage(ImageCache.getInstance().getMenuCache().get(26));
        easydes3 = (ImageView) root.lookup("#easydes3");
        easydes3.setImage(ImageCache.getInstance().getMenuCache().get(27));
        kotoku = (ImageView) root.lookup("#kotoku");
        kotoku.setImage(ImageCache.getInstance().getMenuCache().get(28));
        tigerden = (ImageView) root.lookup("#tigerden");
        tigerden.setImage(ImageCache.getInstance().getMenuCache().get(29));
        treeoflife = (ImageView) root.lookup("#treeoflife");
        treeoflife.setImage(ImageCache.getInstance().getMenuCache().get(30));
        medes1 = (ImageView) root.lookup("#medes1");
        medes1.setImage(ImageCache.getInstance().getMenuCache().get(31));
        medes2 = (ImageView) root.lookup("#medes2");
        medes2.setImage(ImageCache.getInstance().getMenuCache().get(32));
        medes3 = (ImageView) root.lookup("#medes3");
        medes3.setImage(ImageCache.getInstance().getMenuCache().get(33));
        hardes1 = (ImageView) root.lookup("#hardes1");
        hardes1.setImage(ImageCache.getInstance().getMenuCache().get(34));
        hardes2 = (ImageView) root.lookup("#hardes2");
        hardes2.setImage(ImageCache.getInstance().getMenuCache().get(35));
        hardes3 = (ImageView) root.lookup("#hardes3");
        hardes3.setImage(ImageCache.getInstance().getMenuCache().get(36));
        telkku = (ImageView) root.lookup("#telkku");



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
        midR = (ImageView) root.lookup("#midR");
        midR.setImage(ImageCache.getInstance().getGameBackGroundCache().get(3));
        midTop = (ImageView) root.lookup("#midTop");
        midTop.setImage(ImageCache.getInstance().getGameBackGroundCache().get(4));
        midL = (ImageView) root.lookup("#midL");
        midL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(5));
        midBot = (ImageView) root.lookup("#midBot");
        midBot.setImage(ImageCache.getInstance().getGameBackGroundCache().get(6));
        easyTop = (ImageView) root.lookup("#easyTop");
        easyTop.setImage(ImageCache.getInstance().getGameBackGroundCache().get(7));
        easyBot = (ImageView) root.lookup("#easyBot");
        easyBot.setImage(ImageCache.getInstance().getGameBackGroundCache().get(8));
        easyL = (ImageView) root.lookup("#easyL");
        easyL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(9));
        midgrid = (ImageView) root.lookup("#midgrid");
        midgrid.setImage(ImageCache.getInstance().getGameBackGroundCache().get(10));
        hardGridImage = (ImageView) root.lookup("#hardGridImage");
        hardGridImage.setImage(ImageCache.getInstance().getGameBackGroundCache().get(11));
        hardR = (ImageView) root.lookup("#hardR");
        hardR.setImage(ImageCache.getInstance().getGameBackGroundCache().get(12));
        hardL = (ImageView) root.lookup("#hardL");
        hardL.setImage(ImageCache.getInstance().getGameBackGroundCache().get(13));
        play = (ImageView) root.lookup("#play");
        play.setImage(ImageCache.getInstance().getGameBackGroundCache().get(14));
        returngame = (ImageView) root.lookup("#returngame");
        returngame.setImage(ImageCache.getInstance().getGameBackGroundCache().get(15));
        movingjungle = (ImageView) root.lookup("#movingjungle");
        easyend = (ImageView) root.lookup("#easyend");
        midneo = (ImageView) root.lookup("#midneo");
        midneo.setImage(ImageCache.getInstance().getGameBackGroundCache().get(18));
        midneo2 = (ImageView) root.lookup("#midneo2");
        midneo2.setImage(ImageCache.getInstance().getGameBackGroundCache().get(19));
        midneo3 = (ImageView) root.lookup("#midneo3");
        midneo3.setImage(ImageCache.getInstance().getGameBackGroundCache().get(20));
        midneo4 = (ImageView) root.lookup("#midneo4");
        midneo4.setImage(ImageCache.getInstance().getGameBackGroundCache().get(21));
        easyneo = (ImageView) root.lookup("#easyneo");
        easyneo.setImage(ImageCache.getInstance().getGameBackGroundCache().get(22));
        hardneo = (ImageView) root.lookup("#hardneo");
        hardneo.setImage(ImageCache.getInstance().getGameBackGroundCache().get(23));

        //midend = (ImageView) root.lookup("#midend");
        //midend.setImage(ImageCache.getInstance().getGameBackGroundCache().get(7));

    }

    @FXML
    public void easyInfoOn() {displayInfoOn(easydes1,easydes2,easydes3);}

    @FXML
    public void easyInfoOff(){displayInfoOff(easydes1,easydes2,easydes3);}

    @FXML
    public void mediumInfoOn() {displayInfoOn(medes1,medes2,medes3);}

    @FXML
    public void mediumInfoOff() {displayInfoOff(medes1,medes2,medes3);}

    @FXML
    public void hardInfoOn() {displayInfoOn(hardes1,hardes2,hardes3);}

    @FXML
    public void hardInfoOff() {displayInfoOff(hardes1,hardes2,hardes3);}


    private void displayInfoOn(ImageView a, ImageView b, ImageView c) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(a.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(a.opacityProperty(),1),
                        new KeyValue(b.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(b.opacityProperty(),1),
                        new KeyValue(c.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(c.opacityProperty(),1))
        );
        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            timeline.stop();
        });
    }

    private void displayInfoOff(ImageView a, ImageView b, ImageView c) {


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(a.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(a.opacityProperty(),0),
                        new KeyValue(b.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(b.opacityProperty(),0),
                        new KeyValue(c.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(c.opacityProperty(),0))
        );

        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            timeline.stop();
        });
    }

    private void introOn(Boolean introStatus) {

        if (introStatus) {

            Platform.runLater(() -> Effects.getInstance().intro(
                    weDidIt, groupFour, logAndReg,
                    sun, lightning, blacksun,
                    easyFrame, mediumFrame, hardFrame,
                    memomaze, labelLoggedIn, loading,
                    kotoku,tigerden,treeoflife));

        } else {
            labelLoggedIn.setVisible(true);
            menuAnkkuri.setVisible(true);
            midgrid.setVisible(false);
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
            japan.setOpacity(0.6);
            jungle.setOpacity(0.29);
            redtree.setOpacity(0.75);
            kotoku.setOpacity(1);
            tigerden.setOpacity(1);
            treeoflife.setOpacity(1);
            Platform.runLater(() -> Effects.getInstance().playBuringSun());
            AudioMemory.getInstance().playSong(MENU);
            Platform.runLater(() -> Effects.getInstance().setGlow(pergament));
            Platform.runLater(() -> Effects.getInstance().playGlow());

            Timeline backMover = new Timeline(
                    new KeyFrame(Duration.ZERO),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(dirt.scaleXProperty(), dirt.getScaleX())),
                    new KeyFrame(Duration.seconds(15),
                            new KeyValue(dirt.scaleXProperty(), dirt.getScaleX() + 0.4)));

            redtree.getTransforms().add(rotateZ);
            jungle.getTransforms().add(jungleZ);

            Timeline redLine = new Timeline(
                    new KeyFrame(Duration.ZERO),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(rotateZ.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(15),
                            new KeyValue(rotateZ.angleProperty(), 4)));

            Timeline jungleLine = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(jungleZ.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(20),
                            new KeyValue(jungleZ.angleProperty(), -1.2)));

            jungleLine.setAutoReverse(true);
            jungleLine.setCycleCount(Timeline.INDEFINITE);
            jungleLine.play();

            redLine.setAutoReverse(true);
            redLine.setCycleCount(Timeline.INDEFINITE);
            redLine.play();
            backMover.setAutoReverse(true);
            backMover.setCycleCount(Timeline.INDEFINITE);
            backMover.play();
        }
    }

    private void scoresOn(Boolean on) {

        if (on) {
            fetchAllScores();
        }
    }

    @FXML
    public void setButtonLogout() {
            try {
                controller.logout();
                labelLoggedIn.setText("Not logged in");
                name.clear();
                password.clear();

                buttonLogout.setVisible(false);
                stats.setVisible(false);
                paneLogin.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    public void statsGame(MouseEvent mouseEvent) {
        ChartGUI c = new ChartGUI();

        try {
            c.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
