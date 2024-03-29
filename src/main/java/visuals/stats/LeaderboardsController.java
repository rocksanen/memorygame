package visuals.stats;

import controller.ScoreController;
import controller.UserController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.ModeType;
import model.Score;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;
import visuals.internationalization.JavaFXInternationalization;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static model.ModeType.LEADERBOARD;

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

    @FXML
    public Label labelInfo;

    @FXML
    public Pane chartPane;
    @FXML
    public ImageView rugsweeper;
    @FXML AnchorPane leaderBlack;

    private ScoreController scoreController;

    private UserController userController;

    private boolean showUserOnly;

    private ModeType currentMode;

    ChartGUI chartGUI = new ChartGUI();

    private Boolean isChartOnline = true;

    final ResourceBundle r = ResourceBundle.getBundle("Bundle", JavaFXInternationalization.getLocale());

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        blackOff();

        Platform.runLater(() -> AudioMemory.getInstance().playSong(LEADERBOARD));

        changeLanguage(JavaFXInternationalization.getLocale());

        scoreController = new ScoreController();
        userController = new UserController();

        currentMode = ModeType.EASY;
        showUserOnly = false;

        if (!userController.isLoggedIn()) {
            //buttonUserGlobal.setText("Not logged in");
            buttonUserGlobal.setText(r.getString("notLoggedIn"));
            buttonUserGlobal.setDisable(true);
        }

        // load leaderboards.jpeg from resources and set it as background
        String imageurl = Objects.requireNonNull(getClass().getClassLoader().getResource("images/leaderboards.jpg")).toExternalForm();
        anchorLbs.setStyle("-fx-background-image: url('" + imageurl + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        initView();
        updateTable(ModeType.EASY, false);
        updateLabelInfo();
        currentMode = ModeType.EASY;
        chart();

        // hides a block above the invisible scrollbar
        rugsweeper.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("images/trophy.png")).toExternalForm()));
        blackOff();
    }



    private void blackOff() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(leaderBlack.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(leaderBlack.opacityProperty(),0))
        );

        timeline.play();
    }



    /**
     * Initializes the fxml elements in view.
     * calls initTable(), sets styles.
     */
    private void initView() {
        initTable();

        styleButton(buttonReturn);
        styleButton(buttonEasy);
        styleButton(buttonMedium);
        styleButton(buttonHard);
        styleButton(buttonUserGlobal);
        styleButton(buttonRefresh);

        // label set font and background should be dark purple with white font
        labelTitle.setStyle("-fx-font: 40px \"Atari Classic\"; -fx-text-fill: white;");
        labelInfo.setStyle("-fx-font: 20px \"Atari Classic\"; -fx-text-fill: white;");
    }


    private void chart() {

        try {
            chartGUI.init();
            chartPane.getChildren().add(chartGUI.stackedAreaChart());
            chartGUI.stackedAreaChart().setMaxWidth(550);
            chartGUI.stackedAreaChart().setMaxHeight(430);

        }catch (Exception e) {
            chartPane.setOpacity(0);
            e.printStackTrace();
            isChartOnline = false;
        }
    }


    /**
     * set fonts
     * get the font from resources folder
     * buttons set font and background should be dark purple with white font
     * also add hover light purple hover effect
     *
     * @param b Button to style
     */
    private void styleButton(Button b) {
        // get hex for dark purple and light purple and save them to variables
        String darkPurple = "#6d006d";
        String lightPurple = "#930093";
        b.setFont(Font.font("Atari Classic", 14));
        b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: " + lightPurple + " ; -fx-text-fill: white;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;"));
    }

    /**
     * initializes the tableview, adds columns, fills them and styles the whole thing
     */
    private void initTable() {


        //TableColumn<Score, String> nameCol = new TableColumn<>("Username");
        TableColumn<Score, String> nameCol = new TableColumn<>(r.getString("leaderboardsUsername"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toUpperCase());
                }
            }
        });

        //TableColumn<Score, Integer> scoreCol = new TableColumn<>("Points");
        TableColumn<Score, Integer> scoreCol = new TableColumn<>(r.getString("leaderBoardsPoints"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("points"));

        //TableColumn<Score, Double> timeCol = new TableColumn<>("Time (s)");
        TableColumn<Score, Double> timeCol = new TableColumn<>(r.getString("leaderboardsTime"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeCol.setCellFactory(column -> new TableCell<>() {
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

        //TableColumn<Score, String> gradeCol = new TableColumn<>("Grade");
        TableColumn<Score, String> gradeCol = new TableColumn<>(r.getString("leaderboardsGrade"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        // replace ⭐ characters with ⭐ images
        gradeCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox();
                    for (int i = 0; i < item.length(); i++) {
                        ImageView img = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().
                                getResourceAsStream("images/small_star.png"))));
                        img.setFitHeight(20);
                        img.setFitWidth(20);
                        hBox.getChildren().add(img);
                    }
                    setText(null);
                    setGraphic(hBox);
                }
            }
        });

        //TableColumn<Score, Date> dateCol = new TableColumn<>("Date");
        TableColumn<Score, Date> dateCol = new TableColumn<>(r.getString("leaderboardsDate"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        dateCol.setCellFactory(column -> new TableCell<>() {
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
        });
        // add mouse click events to nodes. prints score object to console
        scoreTable.setRowFactory(tv -> {
            TableRow<Score> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Score rowData = row.getItem();
                }
            });
            return row;
        });

        scoreTable.getColumns().clear();
        scoreTable.getColumns().addAll(nameCol, scoreCol, timeCol, gradeCol, dateCol);


        nameCol.setMinWidth(140);
        nameCol.setMaxWidth(nameCol.getMinWidth());
        scoreCol.setMinWidth(90);
        scoreCol.setMaxWidth(scoreCol.getMinWidth());
        timeCol.setMinWidth(110);
        timeCol.setMaxWidth(timeCol.getMinWidth());
        gradeCol.setMinWidth(70);
        gradeCol.setMaxWidth(gradeCol.getMinWidth());
        dateCol.setMinWidth(200);
        dateCol.setMaxWidth(dateCol.getMinWidth());
    }


    /**
     * Updates the tableview with new scores
     *
     * @param mode     difficulty mode
     * @param userOnly if true, only shows user scores, else shows all scores
     */
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

    private void chartReCreation() {


        chartGUI = null;
        chartGUI = new ChartGUI();
        chartPane.getChildren().clear();
        chartGUI.init();
        chartPane.getChildren().add(chartGUI.stackedAreaChart());
        chartGUI.stackedAreaChart().setMaxWidth(550);
        chartGUI.stackedAreaChart().setMaxHeight(430);


    }

    /**
     * sets the current mode to easy and updates the tableview
     *
     */
    @FXML
    public void setButtonEasy() {


        currentMode = ModeType.EASY;
        updateTable(ModeType.EASY, showUserOnly);
        updateLabelInfo();

        if(isChartOnline) {

            chartReCreation();
            if (!showUserOnly) {
                chartGUI.updateWorldChartData(currentMode);
            } else {
                chartGUI.updateUserChartData(currentMode);
            }

        }
    }

    /**
     * sets the current mode to medium and updates the tableview
     *
     */
    @FXML
    public void setButtonMedium() {


        currentMode = ModeType.MEDIUM;
        updateTable(ModeType.MEDIUM, showUserOnly);
        updateLabelInfo();

        if(isChartOnline) {

            chartReCreation();
            if (!showUserOnly) {
                chartGUI.updateWorldChartData(currentMode);
            } else {
                chartGUI.updateUserChartData(currentMode);
            }
        }
    }


    /**
     * sets the current mode to hard and updates the tableview
     *
     */
    @FXML
    public void setButtonHard() {

        currentMode = ModeType.HARD;
        updateTable(ModeType.HARD, showUserOnly);
        updateLabelInfo();

        if(isChartOnline) {

            chartReCreation();
            if (!showUserOnly) {
                chartGUI.updateWorldChartData(currentMode);
            } else {
                chartGUI.updateUserChartData(currentMode);
            }
        }
    }


    /**
     * toggles between showing user scores and global scores
     *
     */
    @FXML
    public void setButtonUserGlobal() {

        showUserOnly = !showUserOnly;
        //buttonUserGlobal.setText(showUserOnly ? "Global Scores" : "Personal Scores");
        buttonUserGlobal.setText(showUserOnly ? r.getString("globalScores"): r.getString("personalScores"));
        updateTable(currentMode, showUserOnly);
        updateLabelInfo();

        if(isChartOnline) {

            chartReCreation();
            if (!showUserOnly) {
                chartGUI.updateWorldChartData(currentMode);
            } else {
                chartGUI.updateUserChartData(currentMode);
            }

        }
    }


    /**
     * returns to main menu
     *
     */
    @FXML
    public void setButtonReturn() {

        buttonReturn.setMouseTransparent(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(leaderBlack.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(leaderBlack.opacityProperty(),1.5))
        );

        timeline.play();

        timeline.setOnFinished(actionEvent -> {

            Platform.runLater(() -> AudioMemory.getInstance().stopSong(LEADERBOARD));
            try {
                Navigaattori.getInstance().changeScene(ModeType.MENU);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });




    }

    /**
     * updates the info label to reflect currently displayed scores
     */
    private void updateLabelInfo() {
        String mode = currentMode.toString().toLowerCase();
        if (mode.contains("easy")) {
            mode = r.getString("leaderboardsEasy");
        } else if (mode.contains("medium")) {
            mode = r.getString("leaderboardsMed");
        } else if (mode.contains("hard")) {
            mode = r.getString("leaderboardsHard");
        }
        //Capitalize first letter
        mode = mode.substring(0, 1).toUpperCase() + mode.substring(1);

        String username = userController.getUsername();
        username = username.substring(0, 1).toUpperCase() + username.substring(1);

        //String text = showUserOnly ? username + "'s Scores - " : "Global Scores - ";
        String text = showUserOnly ? username + r.getString("someoneScores") + " - " : r.getString("globalScores") + " - ";
        text += mode;
        labelInfo.setText(text);
    }


    /**
     * reloads scores from server
     *
     */
    @FXML
    public void setButtonRefresh() {
        buttonRefresh.setDisable(true);
        buttonRefresh.setText("Reloading...");

        // new thread to fetch scores
        Thread thread = new Thread(() -> {
            scoreController.fetchScores(ModeType.EASY);
            scoreController.fetchScores(ModeType.MEDIUM);
            scoreController.fetchScores(ModeType.HARD);
            scoreController.fetchPersonalScores();
        });
        thread.start();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    buttonRefresh.setDisable(false);
                    buttonRefresh.setText("Reload Scores");
                    updateTable(currentMode, showUserOnly);
                });
            }
        }, 5000);
    }

    public void changeLanguage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle", locale);

        for (Button button : Arrays.asList(buttonReturn, buttonEasy, buttonMedium, buttonHard, buttonUserGlobal, buttonRefresh)) {
            if (button != null) {
                String key = button.getId();
                String text = bundle.getString(key);
                button.setText(text);
            }
        }

        for (Label label : Collections.singletonList(labelTitle)) {
            if (label != null) {
                String key = label.getId();
                String text = bundle.getString(key);
                label.setText(text);
            }
        }


    }
}


