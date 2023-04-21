package visuals.menu;

import controller.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.ModeType;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;
import visuals.effects.commonHovers.Hovers;
import visuals.effects.menuEffects.BurningSun;
import visuals.effects.menuEffects.IMenuLayoutEffects;
import visuals.effects.menuEffects.MenuLayoutEffects;
import visuals.effects.menuEffects.ZoomInEffects;
import visuals.imageServers.ImageCache;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.ModeType.*;

public class Menu implements Initializable, IMenu {

    private final IScoreController scoreController = new ScoreController();
    private final IUserController userController = new UserController(this);
    private final ZoomInEffects zoomInEffects = new ZoomInEffects();
    private final IMenuLayoutEffects menuLayoutEffects = new MenuLayoutEffects();
    private final Hovers hovers = new Hovers(menuLayoutEffects);
    private Image muteImage;
    private Image unmuteImage;

    @FXML
    ImageView burningsun;
    @FXML
    Button buttonLogout;
    @FXML
    Label labelLoggedIn;
    @FXML
    Button stats;
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
    ImageView loginButtonImage;
    @FXML
    ImageView registerButtonImage;
    @FXML
    Pane leaderPane;
    @FXML
    Pane logOutPane;
    @FXML
    ImageView info;
    @FXML
    ImageView audioMute;
    @FXML
    ImageView audioUnMute;

    private final AudioMemory audioMemory = AudioMemory.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initGoods();

        Platform.runLater(() -> BurningSun.getInstance().burningSunMove(burningsun));

        if (IntroOn.getInstance().getIntroOn()) {
            IntroOn.getInstance().setIntroOff();
        } else {
            Platform.runLater(() -> AudioMemory.getInstance().playSong(MENU));
        }


        Platform.runLater(() -> menuLayoutEffects.setGlow(pergament));
        Platform.runLater(() -> menuLayoutEffects.moveDirt(dirt));
        Platform.runLater(() -> menuLayoutEffects.moveJungle(jungle));
        Platform.runLater(() -> menuLayoutEffects.moveRedTree(redtree));

        initImages();
    }

    private void initImages() {
        muteImage = new Image("pictures/images/menu/mute1.png");
        unmuteImage = new Image("pictures/images/menu/audio.png");

        if (audioMemory.isMuted()) {
            audioMute.setImage(muteImage);
            audioUnMute.setImage(null);
        } else {
            audioMute.setImage(null);
            audioUnMute.setImage(unmuteImage);
        }
    }

    public void initGoods() {

        initLogin();
        panesAndMisc();
        setMenuImages();

        zoomInEffects.setMiniImagesAndFrames(miniEasy, miniMedium, miniHard, easyFrame, mediumFrame, hardFrame);
        zoomInEffects.setEssenceImages(japan, jungle, redtree);
        zoomInEffects.setGeneralObjects(pergament);
        Platform.runLater(() -> menuLayoutEffects.infoBlink(info));
        Platform.runLater(() -> menuLayoutEffects.infoBlink(audioMute));
        Platform.runLater(() -> menuLayoutEffects.infoBlink(audioUnMute));
    }

    private void initLogin() {

        Platform.runLater(() -> paneLogin.setVisible(!userController.isLoggedIn()));
        Platform.runLater(() -> logOutPane.setVisible(userController.isLoggedIn()));

        if (userController.isLoggedIn()) {
            Platform.runLater(() -> labelLoggedIn.setText("Logged in as " + userController.getUsername()));
        }
        Platform.runLater(() -> logAndReg.setVisible(true));
    }
    @FXML
    public void easyStartScreenPlay() {

        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(803, 10, -145.5, 14.5, EASY));
    }

    @FXML
    public void mediumStartScreenPlay() {

        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1071, 10, 117.2, -144.92, MEDIUM));
    }

    @FXML
    public void hardStartScreenPlay() {

        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1002, 10, 384, 14, HARD));
    }

    private void prepareToPlay() {

        Platform.runLater(() -> miniEasy.setMouseTransparent(true));
        Platform.runLater(() -> miniMedium.setMouseTransparent(true));
        Platform.runLater(() -> miniHard.setMouseTransparent(true));
        BurningSun.getInstance().savePosition();
        menuLayoutEffects.stopTimelines();
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
        Platform.runLater(() -> paneLogin.setVisible(false));
        Platform.runLater(() -> buttonLogout.setVisible(true));
        Platform.runLater(() -> labelLoggedIn.setText("Logged in as " + userController.getUsername()));
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
            Platform.runLater(() -> paneLogin.setVisible(false));
            Platform.runLater(() -> logOutPane.setVisible(true));

            Platform.runLater(() -> labelLoggedIn.setText("Logged in as " + userController.getUsername()));

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

    }

    private void setMenuImages() {

        burningsun.setImage(ImageCache.getInstance().getMenuCache().get(11));
        dirt.setImage(ImageCache.getInstance().getMenuCache().get(10));
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
        easydes1.setImage(ImageCache.getInstance().getMenuCache().get(12));
        easydes2.setImage(ImageCache.getInstance().getMenuCache().get(13));
        easydes3.setImage(ImageCache.getInstance().getMenuCache().get(14));
        kotoku.setImage(ImageCache.getInstance().getMenuCache().get(15));
        tigerden.setImage(ImageCache.getInstance().getMenuCache().get(16));
        treeoflife.setImage(ImageCache.getInstance().getMenuCache().get(17));
        medes1.setImage(ImageCache.getInstance().getMenuCache().get(18));
        medes2.setImage(ImageCache.getInstance().getMenuCache().get(19));
        medes3.setImage(ImageCache.getInstance().getMenuCache().get(20));
        hardes1.setImage(ImageCache.getInstance().getMenuCache().get(21));
        hardes2.setImage(ImageCache.getInstance().getMenuCache().get(22));
        hardes3.setImage(ImageCache.getInstance().getMenuCache().get(23));
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
            Platform.runLater(() -> logOutPane.setVisible(false));
            Platform.runLater(() -> paneLogin.setVisible(true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void setButtonLeaderboards() {

        BurningSun.getInstance().savePosition();
        Platform.runLater(() -> AudioMemory.getInstance().stopSong(MENU));
        try {
            Navigaattori.getInstance().changeScene(LEADERBOARD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setInfoButton() {

        BurningSun.getInstance().savePosition();
        try {
            Navigaattori.getInstance().changeScene(ModeType.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hoverOn(javafx.scene.input.MouseEvent event) {

        hovers.commonHoverOn(event);
    }

    @FXML
    public void hoverOff(javafx.scene.input.MouseEvent event) {

        hovers.commonHoverOff(event);
    }



    @FXML
    public void setButtonAudio() {
        audioMemory.toggleMute();

        if (audioMemory.isMuted()) {
            audioMute.setImage(muteImage);
            audioUnMute.setImage(null);
        } else {
            audioMute.setImage(null);
            audioUnMute.setImage(unmuteImage);
        }
    }
}

