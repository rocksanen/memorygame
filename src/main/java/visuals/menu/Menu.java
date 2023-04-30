package visuals.menu;

import controller.IScoreController;
import controller.IUserController;
import controller.ScoreController;
import controller.UserController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.ModeType;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;
import visuals.effects.commonHovers.Hovers;
import visuals.effects.menuEffects.BurningSun;
import visuals.effects.menuEffects.IMenuLayoutEffects;
import visuals.effects.menuEffects.MenuLayoutEffects;
import visuals.effects.menuEffects.ZoomInEffects;
import visuals.imageServers.ImageCache;
import visuals.internationalization.ImageTranslator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static model.ModeType.*;

public class Menu implements Initializable, IMenu {

    private final IScoreController scoreController = new ScoreController();
    private final IUserController userController = new UserController();
    private final ZoomInEffects zoomInEffects = new ZoomInEffects();
    private final IMenuLayoutEffects menuLayoutEffects = new MenuLayoutEffects();
    private final Hovers hovers = new Hovers(menuLayoutEffects);
    private final AudioMemory audioMemory = AudioMemory.getInstance();
    private final ImageTranslator imageTranslator = new ImageTranslator();

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
    Pane leaderPane;
    @FXML
    Pane logOutPane;
    @FXML
    ImageView info;
    @FXML
    ImageView loginButtonImage;
    @FXML
    ImageView registerButtonImage;
    @FXML ImageView passwordButtonImage;
    @FXML ImageView usernameButtonimage;
    @FXML ImageView logoutButton;
    @FXML Label userName;
    @FXML
    ImageView audioMute;
    @FXML
    ImageView audioUnMute;
    @FXML Pane audioPane;
    @FXML Pane userPane;
    @FXML AnchorPane wallOfeetu;
    @FXML AnchorPane menuBlack;

    private static boolean returnFromGame = true;

    private static String user;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        wallOfeetu.setMouseTransparent(false);

        initGoods();
        Platform.runLater(() -> BurningSun.getInstance().burningSunMove(burningsun));

        introSongCheck();
        initLoginPanel();
        initGameModeDescriptions();

        Platform.runLater(() -> menuLayoutEffects.setGlow(pergament));
        Platform.runLater(() -> menuLayoutEffects.moveDirt(dirt));
        Platform.runLater(() -> menuLayoutEffects.moveJungle(jungle));
        Platform.runLater(() -> menuLayoutEffects.moveRedTree(redtree));

        if(!returnFromGame) {
           menuBlack.setOpacity(1);
           menuBlackOff();
        }
        setWallOfeetuOff();
    }

    private void menuBlackOff() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(menuBlack.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(menuBlack.opacityProperty(),0))
        );

        timeline.play();


    }

    private void initGoods() {

        initLogin();
        panesAndMisc();
        setMenuImages();

        zoomInEffects.setMiniImagesAndFrames(miniEasy, miniMedium, miniHard, easyFrame, mediumFrame, hardFrame);
        zoomInEffects.setEssenceImages(japan, jungle, redtree);
        zoomInEffects.setGeneralObjects(pergament);
        Platform.runLater(() -> menuLayoutEffects.infoBlink(info));
    }

    private void initLogin() {

        Platform.runLater(() -> logAndReg.setVisible(true));
        Platform.runLater(() -> paneLogin.setVisible(!userController.isLoggedIn()));
        Platform.runLater(() -> logOutPane.setVisible(userController.isLoggedIn()));
        Platform.runLater(() ->  userPane.setVisible(userController.isLoggedIn()));
    }

    private void initLoginPanel()

    {
        imageTranslator.menuLoginTranslator(usernameButtonimage,passwordButtonImage,loginButtonImage,registerButtonImage,logoutButton);
    }

    private void initGameModeDescriptions() {

        imageTranslator.gameModeInfoTranslator(easydes1,easydes2,easydes3,medes1,medes2,medes3,hardes1,hardes2,hardes3);
    }
    @FXML
    public void easyStartScreenPlay() {

        returnFromGame = true;
        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(803, 10, -145.5, 14.5, EASY));
    }

    @FXML
    public void mediumStartScreenPlay() {

        returnFromGame = true;
        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1071, 10, 117.2, -144.92, MEDIUM));
    }

    @FXML
    public void hardStartScreenPlay() {

        returnFromGame = true;
        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1002, 10, 384, 14, HARD));
    }

    private void prepareToPlay() {

        Platform.runLater(() -> wallOfeetu.setMouseTransparent(false));
        BurningSun.getInstance().savePosition();
        menuLayoutEffects.stopTimelines();
    }

    @FXML
    public void registerPane() {

        user = name.getText();
        userName.setText(user);
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

    private void loginActions() {

        user = name.getText();
        userName.setText(user.toUpperCase());
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
            userName.setFont(Font.font("Atari Classic", 14));
            Platform.runLater(() -> userName.setText(user.toUpperCase()));
            Platform.runLater(() -> userPane.setVisible(true));

            Thread thread = new Thread(scoreController::fetchPersonalScores);
            thread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserPane() {

        userName.setFont(Font.font("Atari Classic", 14));
        Platform.runLater(() -> userName.setText(user.toUpperCase()));
        Platform.runLater(() -> userPane.setVisible(true));
    }

    @FXML
    public void loginPane() {

        loginActions();
    }

    @FXML
    private void handleEnterKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            loginActions();
        }
    }

    private void panesAndMisc() {

        audioMute.setVisible(!audioMemory.isMuted());
        audioUnMute.setVisible(audioMemory.isMuted());

        if(userController.isLoggedIn()) {
            updateUserPane();
        }
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
    public void easyInfoOn() {menuLayoutEffects.displayInfoOn(easydes1, easydes2, easydes3);}
    @FXML
    public void easyInfoOff() {menuLayoutEffects.displayInfoOff(easydes1, easydes2, easydes3);}
    @FXML
    public void mediumInfoOn() {menuLayoutEffects.displayInfoOn(medes1, medes2, medes3);}
    @FXML
    public void mediumInfoOff() {menuLayoutEffects.displayInfoOff(medes1, medes2, medes3);}
    @FXML
    public void hardInfoOn() {menuLayoutEffects.displayInfoOn(hardes1, hardes2, hardes3);}
    @FXML
    public void hardInfoOff() {menuLayoutEffects.displayInfoOff(hardes1, hardes2, hardes3);}
    @FXML
    public void setButtonLogout() {

        try {

            userController.logout();
            labelLoggedIn.setText("Not logged in");
            name.clear();
            password.clear();
            Platform.runLater(() -> logOutPane.setVisible(false));
            Platform.runLater(() -> paneLogin.setVisible(true));
            Platform.runLater(() -> userPane.setVisible(false));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setButtonLeaderboards() {

        BurningSun.getInstance().savePosition();
        leaderPane.setMouseTransparent(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(menuBlack.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(menuBlack.opacityProperty(),1))
        );

        timeline.play();

        timeline.setOnFinished(actionEvent -> {

            returnFromGame = false;
            Platform.runLater(() -> AudioMemory.getInstance().stopSong(MENU));
            try {
                Navigaattori.getInstance().changeScene(LEADERBOARD);
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

    }

    @FXML
    public void setInfoButton() {

        BurningSun.getInstance().savePosition();
        info.setMouseTransparent(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(menuBlack.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(menuBlack.opacityProperty(),1))
        );

        timeline.play();

        timeline.setOnFinished(actionEvent -> {

            returnFromGame = false;

            Platform.runLater(() -> AudioMemory.getInstance().stopSong(MENU));
            try {
                Navigaattori.getInstance().changeScene(ModeType.INFO);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }

    @FXML
    public void hoverOn(javafx.scene.input.MouseEvent event) {hovers.commonHoverOn(event);}

    @FXML
    public void hoverOff(javafx.scene.input.MouseEvent event) {hovers.commonHoverOff(event);}

    @FXML
    public void setButtonAudio() {

        audioMemory.toggleMute();
        audioMute.setVisible(!audioMemory.isMuted());
        audioUnMute.setVisible(audioMemory.isMuted());
    }

    private void introSongCheck() {

        if (IntroOn.getInstance().getIntroOn()) {
            IntroOn.getInstance().setIntroOff();
        } else {
            Platform.runLater(() -> AudioMemory.getInstance().playSong(MENU));
        }
    }

    private void setWallOfeetuOff() {

        CompletableFuture.runAsync(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            wallOfeetu.setMouseTransparent(true);
        });
    }
}

