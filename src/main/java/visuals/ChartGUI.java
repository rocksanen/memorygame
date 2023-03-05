package visuals;

import controller.ChartController;
import controller.IChartController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.ModeType;

import java.io.IOException;
import java.util.ArrayList;


/**
 * The type Chart gui.
 */
public class ChartGUI extends Application implements IChartGUI {

    /**
     * The Stacked area chart.
     */
    AreaChart<Number, Number> stackedAreaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

    private final IChartController scoreController2 = new ChartController(this);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage stage) throws IOException {



        // Create a StackedAreaChart object
        stackedAreaChart.setTitle("Progress Chart");
        stackedAreaChart.getXAxis().setLabel("Time (s)");
        stackedAreaChart.getYAxis().setLabel("Points");


        ArrayList<Number> easyScore = scoreController2.getUserScores(ModeType.EASY);
        ArrayList<Number> easyTime = scoreController2.getUserTime(ModeType.EASY);

        ArrayList<Number> mediumScore = scoreController2.getUserScores(ModeType.MEDIUM);
        ArrayList<Number> mediumTime = scoreController2.getUserTime(ModeType.MEDIUM);

        ArrayList<Number> hardScore = scoreController2.getUserScores(ModeType.HARD);
        ArrayList<Number> hardTime = scoreController2.getUserTime(ModeType.HARD);


        // Create a series for each difficulty level
        XYChart.Series<Number, Number> easyScoreSeries = new XYChart.Series<>();
        easyScoreSeries.setName("Easy");
        if (easyScore == null || easyTime == null) {
            throw new NullPointerException("easyScore or easyTime is null");
        }
        for (int i = 0; i < easyScore.size(); i++) {
            easyScoreSeries.getData().add(new XYChart.Data<>(easyTime.get(i), easyScore.get(i)));
        }

        XYChart.Series<Number, Number> mediumScoreSeries = new XYChart.Series<>();
        mediumScoreSeries.setName("Medium");
        if (mediumScore == null || mediumTime == null) {
            throw new NullPointerException("mediumScore or mediumTime is null");
        }
        for (int i = 0; i < mediumScore.size(); i++) {
            mediumScoreSeries.getData().add(new XYChart.Data<>(mediumTime.get(i), mediumScore.get(i)));
        }

        XYChart.Series<Number, Number> hardScoreSeries = new XYChart.Series<>();
        hardScoreSeries.setName("Hard");
        if (hardScore == null || hardTime == null) {
            throw new NullPointerException("hardScore or hardTime is null");
        }
        for (int i = 0; i < hardScore.size(); i++) {
            hardScoreSeries.getData().add(new XYChart.Data<>(hardTime.get(i), hardScore.get(i)));
        }

        // Add the series to the stacked area chart
        stackedAreaChart.getData().addAll(easyScoreSeries,mediumScoreSeries, hardScoreSeries);


        // Create a button with a circle shape of the color which represents the Easy data
        Button easyButton = new Button("Easy");
        easyButton.setCursor(Cursor.HAND);
        easyButton.setShape(new Circle(10));
        easyButton.setStyle("-fx-background-color: #ff7135; -fx-min-width: 20px; -fx-min-height: 20px;");

        Button mediumButton = new Button("Medium");
        mediumButton.setCursor(Cursor.HAND);
        mediumButton.setShape(new Circle(10));
        mediumButton.setStyle("-fx-background-color: #ffb527; -fx-min-width: 20px; -fx-min-height: 20px;");

        Button hardButton = new Button("Hard");
        hardButton.setCursor(Cursor.HAND);
        hardButton.setShape(new Circle(10));
        hardButton.setStyle("-fx-background-color: #51c56b; -fx-min-width: 20px; -fx-min-height: 20px;");

        easyButton.setOnMouseClicked(event -> {
            Node node = easyScoreSeries.getNode();
            if( node == null){
                throw new NullPointerException("Null returned");
            }
            if (easyScoreSeries.getNode().isVisible()) {
                easyScoreSeries.getNode().setVisible(false);
                easyButton.setStyle("-fx-background-color: #cccccc; -fx-min-width: 20px; -fx-min-height: 20px;");
            } else {
                easyScoreSeries.getNode().setVisible(true);
                easyButton.setStyle("-fx-background-color: #ff7135; -fx-min-width: 20px; -fx-min-height: 20px;");
            }
        });
        mediumButton.setOnMouseClicked(event -> {
            Node node = easyScoreSeries.getNode();
            if( node == null){
                throw new NullPointerException("Null returned");
            }
            if (mediumScoreSeries.getNode().isVisible()) {
                mediumScoreSeries.getNode().setVisible(false);
                mediumButton.setStyle("-fx-background-color: #cccccc; -fx-min-width: 20px; -fx-min-height: 20px;");
            } else {
                mediumScoreSeries.getNode().setVisible(true);
                mediumButton.setStyle("-fx-background-color: #ffb527; -fx-min-width: 20px; -fx-min-height: 20px;");
            }
        });

        hardButton.setOnMouseClicked(event -> {
            Node node = easyScoreSeries.getNode();
            if( node == null){
                throw new NullPointerException("Null returned");
            }
            if (hardScoreSeries.getNode().isVisible()) {
                hardScoreSeries.getNode().setVisible(false);
                hardButton.setStyle("-fx-background-color: #cccccc; -fx-min-width: 20px; -fx-min-height: 20px;");
            } else {
                hardScoreSeries.getNode().setVisible(true);
                hardButton.setStyle("-fx-background-color: #51c56b; -fx-min-width: 20px; -fx-min-height: 20px;");
            }
        });


        stackedAreaChart.setLegendVisible(false);
        stackedAreaChart.setPadding(new Insets(10, 10, 30, 10));
        stackedAreaChart.setPrefSize(800, 600);

        //Empty Space for below the buttons that buttons should be a little up
        Region spacer = new Region();
        spacer.setPrefHeight(20);

        // Create a VBox to hold the chart
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);


        //Create a HBox to hold the button
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(easyButton, mediumButton, hardButton);

        vBox.getChildren().addAll(stackedAreaChart,hBox, spacer);

        // Display the scene
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();

    }
}
