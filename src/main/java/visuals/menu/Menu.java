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

/**

 The Menu class is the controller for the menu of the game.
 It contains methods for initializing the menu and its components,
 handling events and user input, and interacting with the controllers
 for the user and scores.
 It implements the Initializable and IMenu interfaces.
 */
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


    /**

     This method initializes the main menu by calling several other methods that set up various parts of the menu.

     Additionally, the wallOfeetu is set to be clickable, the BurningSun is moved to its position, the introSongCheck is performed,

     and the menuBlackOff method is called. Lastly, the setWallOfeetuOff method is called.

     @param url The location of the fxml file for the main menu.

     @param resourceBundle The ResourceBundle that was used to load the fxml file.

     @return void
     */
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

    /**

     This method fades out the menuBlack pane. The fade out is achieved using a Timeline object.

     @return void
     */
    private void menuBlackOff() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(menuBlack.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(menuBlack.opacityProperty(),0))
        );

        timeline.play();


    }

    /**

     This method calls several other methods that set up various parts of the menu. It is called by the initialize method.

     @return void
     */
    private void initGoods() {

        initLogin();
        panesAndMisc();
        setMenuImages();

        zoomInEffects.setMiniImagesAndFrames(miniEasy, miniMedium, miniHard, easyFrame, mediumFrame, hardFrame);
        zoomInEffects.setEssenceImages(japan, jungle, redtree);
        zoomInEffects.setGeneralObjects(pergament);
        Platform.runLater(() -> menuLayoutEffects.infoBlink(info));
    }

    /**

     This method initializes the login panel by setting the visibility of several panes and calling a method to translate images.

     It is called by the initialize method.

     @return void
     */
    private void initLogin() {

        Platform.runLater(() -> logAndReg.setVisible(true));
        Platform.runLater(() -> paneLogin.setVisible(!userController.isLoggedIn()));
        Platform.runLater(() -> logOutPane.setVisible(userController.isLoggedIn()));
        Platform.runLater(() ->  userPane.setVisible(userController.isLoggedIn()));
    }

    /**

     This method translates images on the login panel. It is called by the initialize method.
     @return void
     */
    private void initLoginPanel()

    {
        imageTranslator.menuLoginTranslator(usernameButtonimage,passwordButtonImage,loginButtonImage,registerButtonImage,logoutButton);
    }

    /**

     This method translates text for game mode descriptions. It is called by the initialize method.

     @return void
     */
    private void initGameModeDescriptions() {

        imageTranslator.gameModeInfoTranslator(easydes1,easydes2,easydes3,medes1,medes2,medes3,hardes1,hardes2,hardes3);
    }

    /**

     The JavaFX method responsible for handling the user input when starting

     an easy level game.
     */
    @FXML
    public void easyStartScreenPlay() {

        returnFromGame = true;
        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(803, 10, -145.5, 14.5, EASY));
    }

    /**

     The JavaFX method responsible for handling the user input when starting

     a medium level game.
     */
    @FXML
    public void mediumStartScreenPlay() {

        returnFromGame = true;
        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1071, 10, 117.2, -144.92, MEDIUM));
    }

    /**

     The JavaFX method responsible for handling the user input when starting

     a hard level game.
     */
    @FXML
    public void hardStartScreenPlay() {

        returnFromGame = true;
        prepareToPlay();
        Platform.runLater(() -> zoomInEffects.gameZoomIn(1002, 10, 384, 14, HARD));
    }

    /**

     The method responsible for preparing the UI elements for the game start.

     It makes the wall of feetu game element visible and stops any timelines

     currently running.
     */
    private void prepareToPlay() {

        Platform.runLater(() -> wallOfeetu.setMouseTransparent(false));
        BurningSun.getInstance().savePosition();
        menuLayoutEffects.stopTimelines();
    }

    /**

     The JavaFX method responsible for handling the user input when registering

     a new user.
     */
    @FXML
    public void registerPane() {

        user = name.getText();
        userName.setText(user);
        String userPassword = password.getText();

        if (userController.isLoggedIn()) {
            return;
        }
        if (!userController.register(user, userPassword)) {
            return;
        }
        Platform.runLater(() -> paneLogin.setVisible(false));
        Platform.runLater(() -> logOutPane.setVisible(true));
        userName.setFont(Font.font("Atari Classic", 14));
        Platform.runLater(() -> userName.setText(user.toUpperCase()));
        Platform.runLater(() -> userPane.setVisible(true));
    }

    /**

     The method responsible for handling the user login actions.

     It performs user authentication, sets the necessary UI elements visible,

     and starts a thread to fetch the user's personal scores.
     */
    private void loginActions() {

        user = name.getText();
        userName.setText(user.toUpperCase());
        String userPassword = password.getText();

        try {
            userController.login(user, userPassword);
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

    /**

     Updates the user pane by setting the font of the user name to "Atari Classic",
     setting the user name to uppercase and showing the user pane.
     */
    private void updateUserPane() {

        userName.setFont(Font.font("Atari Classic", 14));
        Platform.runLater(() -> userName.setText(user.toUpperCase()));
        Platform.runLater(() -> userPane.setVisible(true));
    }

    /**

     Handles the login pane button action by calling the loginActions() method.
     */
    @FXML
    public void loginPane() {

        loginActions();
    }

    /**

     Handles the Enter key pressed event by checking if the Enter key was pressed,
     and if so, calling the loginActions() method.
     @param event The KeyEvent object representing the key pressed event.
     */
    @FXML
    private void handleEnterKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            loginActions();
        }
    }

    /**

     Sets the visibility of audioMute and audioUnMute based on the audio memory.
     Updates the user pane if the user is logged in.
     */
    private void panesAndMisc() {

        audioMute.setVisible(!audioMemory.isMuted());
        audioUnMute.setVisible(audioMemory.isMuted());

        if(userController.isLoggedIn()) {
            updateUserPane();
        }
    }

    /**

     Sets the images for the menu items.
     Uses the image cache to retrieve the menu images based on their index in the cache.
     */
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

    /**

     Displays the information for the easy level when the mouse is hovered over it.
     Calls displayInfoOn() from the menuLayoutEffects.
     */
    @FXML
    public void easyInfoOn() {menuLayoutEffects.displayInfoOn(easydes1, easydes2, easydes3);}

    /**

     Hides the information about the easy level game when the user stops hovering over the image.
     Calls the displayInfoOff method from the menuLayoutEffects object to hide the information.
     */
    @FXML
    public void easyInfoOff() {menuLayoutEffects.displayInfoOff(easydes1, easydes2, easydes3);}

    /**

     Displays information about the medium level game when the user hovers over the image.
     Calls the displayInfoOn method from the menuLayoutEffects object to show the information.
     */
    @FXML
    public void mediumInfoOn() {menuLayoutEffects.displayInfoOn(medes1, medes2, medes3);}

    /**

     Hides the information about the medium level game when the user stops hovering over the image.
     Calls the displayInfoOff method from the menuLayoutEffects object to hide the information.
     */
    @FXML
    public void mediumInfoOff() {menuLayoutEffects.displayInfoOff(medes1, medes2, medes3);}

    /**

     Displays information about the hard level game when the user hovers over the image.
     Calls the displayInfoOn method from the menuLayoutEffects object to show the information.
     */
    @FXML
    public void hardInfoOn() {menuLayoutEffects.displayInfoOn(hardes1, hardes2, hardes3);}

    /**

     Hides the information about the hard level game when the user stops hovering over the image.
     Calls the displayInfoOff method from the menuLayoutEffects object to hide the information.
     */
    @FXML
    public void hardInfoOff() {menuLayoutEffects.displayInfoOff(hardes1, hardes2, hardes3);}

    /**

     Logs out the current user and clears the name and password fields. Hides the user pane and shows the login pane.

     @throws Exception if an error occurs during the logout process
     */
    @FXML
    public void setButtonLogout() {

        try {

            userController.logout();
            name.clear();
            password.clear();
            Platform.runLater(() -> logOutPane.setVisible(false));
            Platform.runLater(() -> paneLogin.setVisible(true));
            Platform.runLater(() -> userPane.setVisible(false));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**

     Sets up and displays the leaderboards page when the leaderboards button is clicked.
     */
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

    /**

     Sets up and displays the info page when the info button is clicked.
     */
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

    /**

     This method is called when the mouse hovers over a button, and it calls the commonHoverOn() method in the Hovers class.
     @param event The mouse event that triggered the method call.
     */
    @FXML
    public void hoverOn(javafx.scene.input.MouseEvent event) {hovers.commonHoverOn(event);}

    /**

     This method is called when the mouse hovers off a button, and it calls the commonHoverOff() method in the Hovers class.
     @param event The mouse event that triggered the method call.
     */
    @FXML
    public void hoverOff(javafx.scene.input.MouseEvent event) {hovers.commonHoverOff(event);}

    /**

     This method is called when the "audio" button is clicked. It toggles the mute state of the game's audio.
     */
    @FXML
    public void setButtonAudio() {

        audioMemory.toggleMute();
        audioMute.setVisible(!audioMemory.isMuted());
        audioUnMute.setVisible(audioMemory.isMuted());
    }

    /**

     This method checks if the intro song is on, and sets it to off. If the intro song is off, it plays the game's menu music.
     */
    private void introSongCheck() {

        if (IntroOn.getInstance().getIntroOn()) {
            IntroOn.getInstance().setIntroOff();
        } else {
            Platform.runLater(() -> AudioMemory.getInstance().playSong(MENU));
        }
    }

    /**

     This method sets the mouse transparent property of the "wallOfeetu" node to true after a delay of 1 second.
     */
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

