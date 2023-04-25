package visuals.gameModes;

import controller.IGameController;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.ModeType;
import visuals.Navigaattori;
import visuals.effects.gameEffects.EasyEffects;
import visuals.internationalization.JavaFXInternationalization;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameOverController {

    private FXIGameController fxigameController;
    private AnchorPane gameRoot;
    private GaussianBlur gaussianBlur;

    @FXML
    AnchorPane gameOverRoot;
    @FXML
    HBox hboxStarContainer;
    @FXML
    Button newGame;
    @FXML
    Button mainMenu;
    @FXML
    Label score;
    @FXML
    Label gameOver;
    @FXML
    VBox gameOverPane;


    /**
     * initialize method serves as a constructor for the class
     * @param fxigameController FXIGameController this view's controller
     * @param gameController IGameController the game controller
     * @param gameRoot AnchorPane the root of the game. is blurred when this pane is shown
     */
    public void Initialize(FXIGameController fxigameController, IGameController gameController, AnchorPane gameRoot) {

        changeLanguage(JavaFXInternationalization.getLocale());

        this.fxigameController = fxigameController;
        this.gameRoot = gameRoot;

        gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(10);
        gameRoot.setEffect(gaussianBlur);

        initStyles();

        gameOverPane.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(getClass().getClassLoader().
                getResourceAsStream("images/gameover4.png"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        score.setText("Score: " + gameController.getCurrentScore());

        String stars = gameController.getGrade();
        animateStars(stars);
    }

    /**
     * pretty complicated method that does a simple thing. based on the grade, makes 1 to 3 spinning stars pop up.
     * @param stars number of stars (grade)
     */
    private void animateStars(String stars) {
        SequentialTransition sequentialTransition = new SequentialTransition();

        for (int i = 0; i < stars.length(); i++) {
            ImageView imageStars = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().
                    getResourceAsStream("images/rotating_star_medium.gif"))));
            imageStars.setFitHeight(90);
            imageStars.setFitWidth(90);
            hboxStarContainer.getChildren().add(imageStars);


            // Set the initial scale of the ImageView to be very small
            imageStars.setScaleX(0.01);
            imageStars.setScaleY(0.01);

            // Create a ScaleTransition that starts with a small scale and grows to the final size
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), imageStars);
            scaleTransition.setFromX(0.01);
            scaleTransition.setFromY(0.01);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);

            // Add the ScaleTransition to the SequentialTransition
            sequentialTransition.getChildren().add(scaleTransition);

            // Show the ImageView before playing the ScaleTransition
            PauseTransition showTransition = new PauseTransition(Duration.seconds(0.3));
            showTransition.setOnFinished(event -> imageStars.setVisible(true));
            sequentialTransition.getChildren().add(showTransition);
        }
        sequentialTransition.play();
    }


    /**
     * return to menu, with fades and blurs as with all other return to menu methods
     */
    @FXML
    public void setButtonMenu() {

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), gameOverRoot);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        gaussianBlur.setRadius(0);
        gameRoot.setEffect(gaussianBlur);

        fadeTransition.setOnFinished(actionEvent -> {

            fxigameController.returnMenu();

        });
    }

    /**
     * Start a mew game.
     */
    @FXML
    public void setButtonRestart() {
        fxigameController.newGame();
    }

    /**
     * Initialize styles, first does the fonts, then calls styleButton for the buttons
     */
    private void initStyles() {
        gameOver.setFont(Font.font("Atari Classic", 44));
        gameOver.setTextFill(javafx.scene.paint.Color.WHITE);
        score.setFont(Font.font("Atari Classic", 30));
        score.setTextFill(javafx.scene.paint.Color.WHITE);

        styleButton(newGame);
        styleButton(mainMenu);
    }

    /**
     * Style a button.
     * @param b Button to be styled
     */
    private void styleButton(Button b) {
        // get hex for dark purple and light purple and save them to variables
        String darkPurple = "#6d006d";
        String lightPurple = "#930093";
        b.setFont(Font.font("Atari Classic", 24));
        b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: " + lightPurple + " ; -fx-text-fill: white;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;"));
    }

    /**
     * Change the language of the game. Based on language selected elsewhere
     * @param locale Locale to be changed to
     */
    public void changeLanguage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle", locale);

        for (Button button : Arrays.asList(mainMenu, newGame)) {
            if (button != null) {
                String key = button.getId();
                String text = bundle.getString(key);
                button.setText(text);
            }
        }

        for (Label label : Arrays.asList(score, gameOver)) {
            if (label != null) {
                String key = label.getId();
                String text = bundle.getString(key);
                label.setText(text);
            }
        }
    }
}
