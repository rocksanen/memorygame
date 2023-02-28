package visuals;

import controller.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MemoryObject;
import model.ModeType;
import model.Timer1;
import visuals.audio.AudioMemory;
import visuals.cubeFactories.EasyCubeFactory;
import visuals.cubeFactories.HardCubeFactory;
import visuals.cubeFactories.ICubeFactory;
import visuals.cubeFactories.MediumCubeFactory;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.text.Font.loadFont;
import static model.ModeType.*;

public class Gui extends Application implements IGui, IChartGUI {

    private ModeType selectedDifficulty;
    private final IChartController scoreController2 = new ChartController(this);
    private final IControllerVtoE controller = new Controller(this);
    private final IControllerScoreToV scoreController = new Controller(this);
    Stage primaryStage;

    @FXML
    Label labelLoggedIn;
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
    @FXML
    ImageView hardSpread;
    @FXML
    ImageView mediumSpread;
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

    private static final double CAMERA_INITIAL_DISTANCE = -1000;
    private static final double CAMERA_INITIAL_X_ANGLE = 0.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private double mousePosX;
    private double mouseOldX;
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    private final Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    private final Rotate jungleZ = new Rotate(0, Rotate.Z_AXIS);
    private final Rotate rotateX = new Rotate(CAMERA_INITIAL_X_ANGLE, Rotate.Z_AXIS);
    private final Translate translate = new Translate(1250 / 2, 750 / 2, 0);
    private ArrayList<BoxMaker> cubeList;
    private ICubeFactory easyCubeFactory;
    private ICubeFactory mediumCubeFactory;
    private ICubeFactory hardCubeFactory;
    private Parent root;
    private Scene scene;

    private long timerTime;
    public void setTimerTime(long timerTime) {
        this.timerTime = timerTime;
    }


    public void setActiveID(int activeID) {
        this.activeID = activeID;
        System.out.println(activeID);
    }

    private int activeID;
    public static PerspectiveCamera camera = new PerspectiveCamera();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        initGoods();

        this.primaryStage = primaryStage;
        this.scene = new Scene(root, 1250, 750);
        camera.setFieldOfView(25);
        this.scene.setCamera(camera);
        this.scene.getCamera().setNearClip(0.1);


        /*
        scene.setOnMousePressed(event -> {
            mousePosX = event.getSceneX();
            mouseOldX = event.getSceneX();
        });

        scene.setOnMouseMoved(event -> {
            mouseOldX = mousePosX;
            mousePosX = event.getSceneX();
            double deltaX = (mousePosX - mouseOldX);
            if (event.isPrimaryButtonDown()) {
                rotateY.setAngle(rotateY.getAngle() + deltaX / 5.0);
                pergament.getTransforms().setAll(rotateY);
            }
        });

         */

        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

        // If you want intro: "true", if not: "false". But is there life without intro?
        introOn(false);


        Platform.runLater(() -> Effects.getInstance().setGlow(pergament));
        Platform.runLater(() -> Effects.getInstance().playGlow());
        Visibilities.getInstance().setGridLayoutToVisibility(easyGrid, mediumGrid, hardGrid);
        Visibilities.getInstance().setGameBackGrounds(background, mediumBackground, mediumSpread, hardBackground, hardSpread);
        AudioMemory.getInstance().playTheIntro();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX())),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX() + 0.4))
        );

        redtree.getTransforms().add(rotateZ);
        jungle.getTransforms().add(jungleZ);

        Timeline redLine = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(rotateZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(rotateZ.angleProperty(), 4))
        );

        Timeline jungleLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(jungleZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(20),
                        new KeyValue(jungleZ.angleProperty(), -1.2))
        );

        jungleLine.setAutoReverse(true);
        jungleLine.setCycleCount(Timeline.INDEFINITE);
        jungleLine.play();

        redLine.setAutoReverse(true);
        redLine.setCycleCount(Timeline.INDEFINITE);
        redLine.play();
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        // If you want scores: "true", if not: "false".
        scoresOn(true);
    }

    public void initGoods() throws IOException {

        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/game2.fxml")));


        panesAndMisc();
        setIntroImages();
        setMenuImages();
        setGameImages();

        Effects.getInstance().setMiniImagesAndFrames(miniEasy, miniMedium, miniHard, easyFrame, mediumFrame, hardFrame);
        Effects.getInstance().setEssenceImages(japan, jungle, redtree);
        Effects.getInstance().setGeneralObjects(pergament, menuAnkkuri, startBlack, gameModePane, burningsun, labelLoggedIn);
        Visibilities.getInstance().setToGameObjects(gameModePane, score, logAndReg, pergament);
    }

    @FXML
    public void newGame() {

        Timer t = new Timer();
        TimerTask task = new Timer1();
        t.schedule(task, 0, timerTime);
        switch (cubeList.size()) {

            case 6 -> setStartEasyGame();
            case 12 -> setStartMediumGame();
            case 20 -> setStartHardGame();
        }
    }

    @FXML
    public void returnMenu() {

        switch (cubeList.size()) {

            case 6 -> Effects.getInstance().gameZoomOut(
                    easyGrid, background,
                    800, 35, -145.5, 14.5, EASY
            );
            case 12 -> Effects.getInstance().gameZoomOut(
                    mediumGrid, mediumBackground,
                    1101, 35, 117.2, -144.92, MEDIUM
            );
            case 20 -> Effects.getInstance().gameZoomOut(
                    hardGrid, hardBackground,
                    1000.7, 35, 384.0, 14.5, ModeType.HARD
            );
        }

        Platform.runLater(() -> score.setVisible(false));
    }

    @FXML
    public void easyStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(EASY));
        Platform.runLater(() -> logAndReg.setVisible(false));

        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                background, 800,
                10, -145.5, 14.5,
                EASY, this));

    }

    @FXML
    public void mediumStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(MEDIUM));
        Platform.runLater(() -> logAndReg.setVisible(false));

        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                mediumBackground,
                1101, 10, 117.2, -144.92,
                MEDIUM, this));

    }

    @FXML
    public void hardStartScreenPlay() {

        Platform.runLater(() -> Effects.getInstance().stopGlow());
        Platform.runLater(() -> Visibilities.getInstance().gameBackGroundVisibility(HARD));
        Platform.runLater(() -> logAndReg.setVisible(false));

        Platform.runLater(() -> Effects.getInstance().gameZoomIn(
                hardBackground, 1000.7,
                10, 384, 14.5,
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
                return;
            }
            fetchUserScores();
            logAndReg.setVisible(false);
            labelLoggedIn.setText("Logged in as " + controller.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void panesAndMisc() {

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

        labelLoggedIn = (Label) root.lookup("#labelLoggedIn");

        URL url = Gui.class.getClassLoader().getResource("fonts/outrun_future.otf");
        System.out.println(url);
        // get the font from the resources, set size and add it to the label
        labelLoggedIn.setFont(Font.loadFont(url.toExternalForm(), 18));

        labelLoggedIn.setStyle("-fx-background-color: rgba(0,0,0,0.50);-fx-background-radius: 5; -fx-padding: 1 6 1 6");

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

        if (introStatus) {

            Platform.runLater(() -> Effects.getInstance().intro(
                    weDidIt, groupFour, logAndReg,
                    sun, lightning, blacksun,
                    easyFrame, mediumFrame, hardFrame, labelLoggedIn));

        } else {
            labelLoggedIn.setVisible(true);

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
            japan.setOpacity(0.4);
            jungle.setOpacity(0.26);
            redtree.setOpacity(0.75);
            Platform.runLater(() -> Effects.getInstance().playBuringSun());
            AudioMemory.getInstance().playSong(MENU);
        }
    }

    private void scoresOn(Boolean on) {

        Node worldScoresNode = root.lookup("#worldScores");
        if (worldScoresNode instanceof ListView<?>) {
            worldScores = (ListView<String>) worldScoresNode;
        }
        if (on) {
            fetchAllScores();
        }
    }


    public void statsGame(MouseEvent mouseEvent) {
        ChartGUI c = new ChartGUI();

        try {
            c.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
