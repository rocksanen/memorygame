package visuals.gameModes;

import controller.IGameController;
import controller.IScoreController;
import controller.ScoreController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
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


    public void Initialize(FXIGameController fxigameController, IGameController gameController) {
        this.fxigameController = fxigameController;
        initStyles();

        labelScore.setText("Score: " + gameController.getCurrentScore());
        String stars = gameController.getGrade();
        for (int i = 0; i < stars.length(); i++) {
            ImageView imageStars = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().
                    getResourceAsStream("images/star.png"))));
            imageStars.setFitHeight(60);
            imageStars.setFitWidth(60);
            hboxStarContainer.getChildren().add(imageStars);
        }
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
        Font.loadFont(Objects.requireNonNull(getClass().getClassLoader().getResource("fonts/VCR_OSD_MONO_1.001.ttf")).toExternalForm(), 14);

        labelGameOver.setFont(Font.font("VCR OSD Mono", 30));
        labelGameOver.setTextFill(javafx.scene.paint.Color.WHITE);
        labelScore.setFont(Font.font("VCR OSD Mono", 24));
        labelScore.setTextFill(javafx.scene.paint.Color.WHITE);

        styleButton(buttonRestart);
        styleButton(buttonMenu);
    }

    private void styleButton(Button b) {
        // get hex for dark purple and light purple and save them to variables
        String darkPurple = "#800080";
        String lightPurple = "#cc00cc";
        b.setFont(Font.font("VCR OSD Mono", 24));
        b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: " + lightPurple + " ; -fx-text-fill: white;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;"));
    }
}
