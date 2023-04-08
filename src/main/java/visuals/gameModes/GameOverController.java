package visuals.gameModes;

import controller.IGameController;
import controller.IScoreController;
import controller.ScoreController;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.ModeType;
import visuals.Navigaattori;

import java.util.Objects;

public class GameOverController {

    private FXIGameController fxigameController;

    @FXML
    AnchorPane gameOverRoot;
    @FXML
    HBox hboxStarContainer;
    @FXML
    Button buttonRestart;
    @FXML
    Button buttonMenu;
    @FXML
    Label labelScore;
    @FXML
    Label labelGameOver;
    @FXML
    VBox gameOverPane;


    public void Initialize(FXIGameController fxigameController, IGameController gameController) {
        this.fxigameController = fxigameController;
        initStyles();

        gameOverPane.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(getClass().getClassLoader().
                getResourceAsStream("images/gameover4.png"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        labelScore.setText("Score: " + gameController.getCurrentScore());

        String stars = gameController.getGrade();
        animateStars(stars);
    }

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
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.7), imageStars);
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


    @FXML
    public void setButtonMenu() {
        Navigaattori n = Navigaattori.getInstance();
        try {
            n.changeScene(ModeType.MENU);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setButtonRestart() {
        fxigameController.newGame();
    }

    private void initStyles() {
        labelGameOver.setFont(Font.font("Atari Classic", 44));
        labelGameOver.setTextFill(javafx.scene.paint.Color.WHITE);
        labelScore.setFont(Font.font("Atari Classic", 30));
        labelScore.setTextFill(javafx.scene.paint.Color.WHITE);

        styleButton(buttonRestart);
        styleButton(buttonMenu);
    }

    private void styleButton(Button b) {
        // get hex for dark purple and light purple and save them to variables
        String darkPurple = "#6d006d";
        String lightPurple = "#930093";
        b.setFont(Font.font("Atari Classic", 24));
        b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: " + lightPurple + " ; -fx-text-fill: white;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;"));
    }
}
