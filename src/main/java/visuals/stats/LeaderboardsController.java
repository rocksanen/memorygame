package visuals.stats;

import controller.ScoreController;
import controller.UserController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.ModeType;
import model.Score;
import visuals.Navigaattori;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LeaderboardsController {

    @FXML
    public Button buttonReturn;

    @FXML
    public Button buttonEasy;

    @FXML
    public Button buttonMedium;

    @FXML
    public Button buttonHard;

    @FXML
    public Button buttonUserGlobal;

    @FXML
    public TableView<Score> scoreTable;

    @FXML
    public AnchorPane anchorLbs;

    @FXML
    public Label labelTitle;

    @FXML
    public Button buttonRefresh;

    private ScoreController scoreController;

    private UserController userController;

    private boolean userOnly;

    private ModeType currentMode;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        scoreController = new ScoreController();
        userController = new UserController();

        currentMode = ModeType.EASY;
        userOnly = false;

        if (!userController.isLoggedIn()) {
            buttonUserGlobal.setText("Not logged in");
            buttonUserGlobal.setDisable(true);
        }

        // load leaderboards.jpeg from resources and set it as background
        String imageurl = Objects.requireNonNull(getClass().getClassLoader().getResource("images/leaderboards.jpg")).toExternalForm();
        anchorLbs.setStyle("-fx-background-image: url('" + imageurl + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        initView();
        updateTable(ModeType.EASY, false);
    }

    private void initView() {
        Font.loadFont(Objects.requireNonNull(getClass().getClassLoader().getResource("fonts/VCR_OSD_MONO_1.001.ttf")).toExternalForm(), 14);

        initTable();

        // set fonts
        // get the font from resources folder

        // buttons set font and background should be dark purple with white font
        buttonReturn.setStyle("-fx-font: 18px \"VCR OSD Mono\"; -fx-background-color: #4d004d; -fx-text-fill: white;");
        buttonEasy.setStyle("-fx-font: 18px \"VCR OSD Mono\"; -fx-background-color: #4d004d; -fx-text-fill: white;");
        buttonMedium.setStyle("-fx-font: 18px \"VCR OSD Mono\"; -fx-background-color: #4d004d; -fx-text-fill: white;");
        buttonHard.setStyle("-fx-font: 18px \"VCR OSD Mono\"; -fx-background-color: #4d004d; -fx-text-fill: white;");
        buttonUserGlobal.setStyle("-fx-font: 18px \"VCR OSD Mono\"; -fx-background-color: #4d004d; -fx-text-fill: white;");
        buttonRefresh.setStyle("-fx-font: 18px \"VCR OSD Mono\"; -fx-background-color: #4d004d; -fx-text-fill: white;");


        labelTitle.setStyle("-fx-font: 48px \"VCR OSD Mono\"; -fx-text-fill: white;");
    }

    private void initTable() {
        TableColumn<Score, String> nameCol = new TableColumn<>("Username");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Score, Integer> scoreCol = new TableColumn<>("Points");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("points"));


        TableColumn<Score, Double> timeCol = new TableColumn<>("Time (s)");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeCol.setCellFactory(column -> new TableCell<Score, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });


        TableColumn<Score, String> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        TableColumn<Score, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        dateCol.setCellFactory(column -> {
            return new TableCell<>() {
                @Override
                protected void updateItem(Date date, boolean empty) {
                    super.updateItem(date, empty);
                    if (empty || date == null) {
                        setText(null);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime ldt = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                        setText(ldt.format(formatter));
                    }
                }
            };
        });

        // add mouse click events to nodes. prints score object to console
        scoreTable.setRowFactory(tv -> {
            TableRow<Score> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Score rowData = row.getItem();
                    System.out.println(rowData);
                }
            });
            return row;
        });


        scoreTable.getColumns().clear();
        scoreTable.getColumns().addAll(nameCol, scoreCol, timeCol, gradeCol, dateCol);

        // center text on columns
        scoreTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));

        // hide the h-scrollbar
        // i spent 4 hours on this 🙃🔫
        nameCol.setMinWidth(140);
        nameCol.setMaxWidth(140);
        scoreCol.setMinWidth(90);
        scoreCol.setMaxWidth(90);
        timeCol.setMinWidth(90);
        timeCol.setMaxWidth(90);
        gradeCol.setMinWidth(90);
        gradeCol.setMaxWidth(90);
        dateCol.setMinWidth(150);
        dateCol.setMaxWidth(150);

        scoreTable.setMinWidth(590);
        scoreTable.setMaxWidth(590);

        scoreTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        scoreTable.setStyle("-fx-font: 14px \"VCR OSD Mono\";");

    }


    private void updateTable(ModeType mode, boolean userOnly) {
        ArrayList<Score> scoreList;
        if (userOnly) {
            scoreList = scoreController.getUserScoresRaw(mode);
            if (scoreList == null || scoreList.isEmpty()) {
                scoreController.fetchScores(mode);
                scoreList = scoreController.getUserScoresRaw(mode);
            }
        } else {
            scoreList = scoreController.getScoresRaw(mode);
            if (scoreList == null || scoreList.isEmpty()) {
                scoreController.fetchScores(mode);
                scoreList = scoreController.getScoresRaw(mode);
            }
        }


        ObservableList<Score> observableScoreList = FXCollections.observableArrayList(scoreList);
        scoreTable.setItems(observableScoreList);
    }

    @FXML
    public void setButtonEasy(ActionEvent event) {
        currentMode = ModeType.EASY;
        updateTable(ModeType.EASY, userOnly);
    }

    @FXML
    public void setButtonMedium(ActionEvent event) {
        currentMode = ModeType.MEDIUM;
        updateTable(ModeType.MEDIUM, userOnly);
    }

    @FXML
    public void setButtonHard(ActionEvent event) {
        currentMode = ModeType.HARD;
        updateTable(ModeType.HARD, userOnly);
    }

    @FXML
    public void setButtonUserGlobal(ActionEvent event) {

        userOnly = !userOnly;
        buttonUserGlobal.setText(userOnly ? "Global Scores" : "User Scores");
        updateTable(currentMode, userOnly);
    }

    @FXML
    public void setButtonReturn(ActionEvent event) {
        try {
            Navigaattori.getInstance().changeScene(ModeType.MENU);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setButtonRefresh(ActionEvent event) {
        buttonRefresh.setDisable(true);
        buttonRefresh.setText("Reloading...");
        // fetch for all difficulties
        scoreController.fetchScores(ModeType.EASY);
        scoreController.fetchScores(ModeType.MEDIUM);
        scoreController.fetchScores(ModeType.HARD);
        scoreController.fetchPersonalScores();

        // 5 secs later set disable false and text to reload scores

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    buttonRefresh.setDisable(false);
                    buttonRefresh.setText("Reload Scores");
                });
            }
        }, 5000);
    }
}
