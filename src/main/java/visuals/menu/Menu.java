package visuals.menu;

import controller.IScoreController;
import controller.IUserController;
import controller.ScoreController;
import controller.UserController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.ModeType;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;
import visuals.effects.menuEffects.BurningSun;
import visuals.effects.menuEffects.IMenuLayoutEffects;
import visuals.effects.menuEffects.MenuLayoutEffects;
import visuals.effects.menuEffects.ZoomInEffects;
import visuals.imageServers.ImageCache;
import visuals.stats.ChartGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static model.ModeType.*;

public class Menu implements Initializable, IMenu {

    private final IScoreController scoreController = new ScoreController();
    private final IUserController userController = new UserController(this);
    private final ZoomInEffects zoomInEffects = new ZoomInEffects();
    private final IMenuLayoutEffects menuLayoutEffects = new MenuLayoutEffects();

    @FXML
    ImageView burningsun;
    @FXML
    Button buttonLogout;
    @FXML
    Label labelLoggedIn;
    @FXML
    Button stats;
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
    AnchorPane menuAnkkuri;
    @FXML
    ImageView pergament;
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
    Pane logAndReg;
    @FXML
    ImageView dirt;
    @FXML
    Pane paneLogin;
    @FXML
    ImageView easydes1;
    @FXML
    ImageView easydes2;
    @FXML
    ImageView easydes3;
    @FXML
    ImageView medes1;
    @FXML
    ImageView medes2;
    @FXML
    ImageView medes3;
    @FXML
    ImageView hardes1;
    @FXML
    ImageView hardes2;
    @FXML
    ImageView hardes3;
    @FXML
    ImageView kotoku;
    @FXML
    ImageView tigerden;
    @FXML
    ImageView treeoflife;
    @FXML
    ImageView telkku;
    @FXML
    Button buttonLeaderboards;
    public static ArrayList<String> worldList;
    public static ArrayList<String> personalList;
    private boolean returnStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initGoods();

        Platform.runLater(() -> BurningSun.getInstance().burningSunMove(burningsun));

        if(IntroOn.getInstance().getIntroOn()) {IntroOn.getInstance().setIntroOff();
        }else {Platform.runLater(() -> AudioMemory.getInstance().playSong(MENU));}

        Platform.runLater(() -> menuLayoutEffects.setGlow(pergament));
        Platform.runLater(() -> menuLayoutEffects.moveDirt(dirt));
        Platform.runLater(() -> menuLayoutEffects.moveJungle(jungle));
        Platform.runLater(() -> menuLayoutEffects.moveRedTree(redtree));
    }

    public void initGoods() {

        panesAndMisc();
        setMenuImages();

        zoomInEffects.setMiniImagesAndFrames(miniEasy, miniMedium, miniHard, easyFrame, mediumFrame, hardFrame);
        zoomInEffects.setEssenceImages(japan, jungle, redtree);
        zoomInEffects.setGeneralObjects(pergament);
    }

    public boolean isReturnStatus() {
        return returnStatus;
    }

    @FXML
    public void easyStartScreenPlay() {

        BurningSun.getInstance().savePosition();
        worldList = scoreController.getTopFiveScores(EASY);
        personalList = scoreController.getTopFivePersonalScores(EASY);
        miniEasy.setMouseTransparent(true);
        Platform.runLater(() -> zoomInEffects.gameZoomIn(803, 10, -145.5, 14.5, EASY));
    }

    @FXML
    public void mediumStartScreenPlay() {

        BurningSun.getInstance().savePosition();
        worldList = scoreController.getTopFiveScores(MEDIUM);
        personalList = scoreController.getTopFivePersonalScores(MEDIUM);
        miniMedium.setMouseTransparent(true);
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1071, 10, 117.2, -144.92, MEDIUM));
    }

    @FXML
    public void hardStartScreenPlay() {

        BurningSun.getInstance().savePosition();
        worldList = scoreController.getTopFiveScores(HARD);
        personalList = scoreController.getTopFivePersonalScores(HARD);
        miniHard.setMouseTransparent(true);
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1002, 10, 384, 14, HARD));
    }

    @FXML
    public void registerPane() {

        String user = name.getText();
        String userPassword = password.getText();

        if (userController.isLoggedIn()) {
            System.out.println("Already logged in");
            return;
        }
        if (!userController.register(user, userPassword)) {
            System.out.println("Registration failed");
            return;
        }
        paneLogin.setVisible(false);
        buttonLogout.setVisible(true);
        labelLoggedIn.setText("Logged in as " + userController.getUsername());
    }


    @FXML
    public void loginPane() {
        String user = name.getText();
        String userPassword = password.getText();
        try {
            userController.login(user, userPassword);
            if (!userController.isLoggedIn()) {
                System.out.println("Login failed");
                stats.setVisible(false);
                return;
            }
            paneLogin.setVisible(false);
            buttonLogout.setVisible(true);
            stats.setVisible(true);

            labelLoggedIn.setText("Logged in as " + userController.getUsername());


            Thread thread = new Thread(scoreController::fetchPersonalScores);
            thread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void panesAndMisc() {

        URL url = Menu.class.getClassLoader().getResource("fonts/outrun_future.otf");
        // get the font from the resources, set size and add it to the label
        assert url != null;
        Font outrun = Font.loadFont(url.toExternalForm(), 13);
        labelLoggedIn.setFont(outrun);
        labelLoggedIn.setStyle("-fx-background-color: rgba(0,0,0,0.50);-fx-background-radius: 5; -fx-padding: 1 6 1 6");

        labelLoggedIn.setText(userController.isLoggedIn() ? "Logged in as " + userController.getUsername() : "Not logged in");

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

    private void setMenuImages() {

        burningsun.setImage(ImageCache.getInstance().getMenuCache().get(24));
        dirt.setImage(ImageCache.getInstance().getMenuCache().get(23));
        pergament.setImage(ImageCache.getInstance().getMenuCache().get(0));
        miniEasy.setImage(ImageCache.getInstance().getMenuCache().get(1));
        miniMedium.setImage(ImageCache.getInstance().getMenuCache().get(2));
        miniHard.setImage(ImageCache.getInstance().getMenuCache().get(3));
        easyFrame.setImage(ImageCache.getInstance().getMenuCache().get(4));
        mediumFrame.setImage(ImageCache.getInstance().getMenuCache().get(5));
        hardFrame.setImage(ImageCache.getInstance().getMenuCache().get(6));
        japan.setImage(ImageCache.getInstance().getMenuCache().get(7));
        jungle.setImage(ImageCache.getInstance().getMenuCache().get(8));
        redtree.setImage(ImageCache.getInstance().getMenuCache().get(9));
        easydes1.setImage(ImageCache.getInstance().getMenuCache().get(25));
        easydes2.setImage(ImageCache.getInstance().getMenuCache().get(26));
        easydes3.setImage(ImageCache.getInstance().getMenuCache().get(27));
        kotoku.setImage(ImageCache.getInstance().getMenuCache().get(28));
        tigerden.setImage(ImageCache.getInstance().getMenuCache().get(29));
        treeoflife.setImage(ImageCache.getInstance().getMenuCache().get(30));
        medes1.setImage(ImageCache.getInstance().getMenuCache().get(31));
        medes2.setImage(ImageCache.getInstance().getMenuCache().get(32));
        medes3.setImage(ImageCache.getInstance().getMenuCache().get(33));
        hardes1.setImage(ImageCache.getInstance().getMenuCache().get(34));
        hardes2.setImage(ImageCache.getInstance().getMenuCache().get(35));
        hardes3.setImage(ImageCache.getInstance().getMenuCache().get(36));
    }

    @FXML
    public void easyInfoOn() {
        menuLayoutEffects.displayInfoOn(easydes1, easydes2, easydes3);
    }

    @FXML
    public void easyInfoOff() {
        menuLayoutEffects.displayInfoOff(easydes1, easydes2, easydes3);
    }

    @FXML
    public void mediumInfoOn() {
        menuLayoutEffects.displayInfoOn(medes1, medes2, medes3);
    }

    @FXML
    public void mediumInfoOff() {
        menuLayoutEffects.displayInfoOff(medes1, medes2, medes3);
    }

    @FXML
    public void hardInfoOn() {
        menuLayoutEffects.displayInfoOn(hardes1, hardes2, hardes3);
    }

    @FXML
    public void hardInfoOff() {
        menuLayoutEffects.displayInfoOff(hardes1, hardes2, hardes3);
    }

    @FXML
    public void setButtonLogout() {
        try {

            userController.logout();
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
    public void statsGame() {
        ChartGUI c = new ChartGUI();

        try {
            c.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setButtonLeaderboards() {

        Platform.runLater(() -> AudioMemory.getInstance().stopSong(MENU));
        try {
            Navigaattori.getInstance().changeScene(LEADERBOARD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
