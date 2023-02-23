package visuals;

import controller.ChartController;
import controller.IChartController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.ModeType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static model.ModeType.EASY;

public class ChartGUI extends Application implements IChartGUI {
    private ModeType selectedDifficulty;

    private final IChartController scoreController = new ChartController(this);

    Stage primaryStage;

    Parent root;

    Scene scene;

    @FXML
    ListView<String> personalScores;

    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage stage) throws IOException {
        init();
        this.primaryStage = stage;
        this.scene = new Scene(root, 1250, 750);

        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }
    public void init() throws IOException {
        System.out.println("loader ... ");
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/visuals/chartScore.fxml")));

        scoreController.test();
        selectedDifficulty = EASY;

        ArrayList<Number> temp = scoreController.getUserScores(EASY);


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
}
