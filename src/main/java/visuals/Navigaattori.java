package visuals;

import controller.ScoreController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ModeType;
import visuals.gameModes.easy.FXEasyController;
import visuals.gameModes.hard.FXHardController;
import visuals.gameModes.medium.FXMediumController;
import visuals.internationalization.JavaFXInternationalization;
import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Navigaattori extends Application {

    private static Navigaattori instance;
    private final String MENU = "/fxml/menu.fxml";
    private static Stage MAINSTAGE;
    public static PerspectiveCamera camera = new PerspectiveCamera();
    private final JavaFXInternationalization i18n = new JavaFXInternationalization();


    public static void main(String[] args) {
        launch(args);
    }

    public static Navigaattori getInstance() {

        if (instance == null) {
            instance = new Navigaattori();
        }
        return instance;
    }

    // .....................................Ruudun
    // vaihto...................................................

    public void changeScene(ModeType type) throws IOException {

        Parent pane = new Pane();

        switch (type) {

            case MENU -> pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(MENU)));
            case EASY -> {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/easy.fxml"));
                pane = loader.load();
                FXEasyController fxEasyController = loader.getController();
                fxEasyController.setController(new ScoreController());
            }
            case MEDIUM -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/medium.fxml"));
                pane = loader.load();
                FXMediumController mediumController = loader.getController();
                mediumController.setController(new ScoreController());
            }
            case HARD -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hard.fxml"));
                pane = loader.load();
                FXHardController fxHardController = loader.getController();
                fxHardController.setController(new ScoreController());
            }

            case LEADERBOARD -> pane = FXMLLoader
                    .load(Objects.requireNonNull(getClass().getResource("/fxml/Leaderboards.fxml")));

            case INFO -> {
                ResourceBundle bundle = i18n.internationalizationLoaderProperties();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/visuals/internationalization/info.fxml"),
                        bundle);

                pane = loader.load();
            }
        }

        MAINSTAGE.getScene().setRoot(pane);
    }

    @Override
    public void start(Stage stage) throws Exception {

        MAINSTAGE = new Stage();
        Platform.setImplicitExit(true);
        MAINSTAGE.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });


        Parent root = FXMLLoader.load (Objects.requireNonNull(getClass().getResource("/fxml/intro.fxml")));

        Scene scene = new Scene(root, 1250, 750);
        camera.setFieldOfView(25);
        scene.setCamera(camera);
        MAINSTAGE.setScene(scene);
        MAINSTAGE.setResizable(false);
        MAINSTAGE.centerOnScreen();
        MAINSTAGE.setFullScreen(false);
        MAINSTAGE.show();
    }
}
