package visuals.stats;
import controller.ChartController;
import controller.IChartController;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.ModeType;

import java.util.ArrayList;
import java.util.Objects;


/**
 * The type Chart gui.
 */
public class ChartGUI implements IChartGUI {

    public AreaChart<String, Number> stackedAreaChart() {

        return stackedAreaChart;
    }

    /**
     * The Stacked area chart.
     */
    AreaChart<String, Number> stackedAreaChart = new AreaChart<>(new CategoryAxis(), new NumberAxis());
    private final IChartController chartController = new ChartController(this);

    private ModeType currentMode;
    public void init() {

        Font.loadFont(Objects.requireNonNull(getClass().getClassLoader().getResource("fonts/VCR_OSD_MONO_1.001.ttf")).toExternalForm(), 14);

        currentMode = ModeType.EASY;
        stackedAreaChart.getXAxis().setStyle("-fx-tick-label-fill: WHITE; -fx-font-size: 16px;-fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.getYAxis().setStyle("-fx-tick-label-fill: WHITE; -fx-font-size: 16px;-fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.setStyle("-fx-background-color: transparent;");
        stackedAreaChart.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        stackedAreaChart.lookup(".chart-plot-background").setStyle("-fx-background-color: rgba(0, 0, 0, 0.9);");
        // Create a StackedAreaChart object
        //stackedAreaChart.setTitle("Your Progress");
        stackedAreaChart.lookup(".chart-title").setStyle("-fx-font-size: 23px; -fx-font-weight: BOLD; -fx-text-fill: white; -fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.setLegendVisible(false);
        stackedAreaChart.getXAxis().setLabel("DATE");
        stackedAreaChart.getYAxis().setLabel("POINTS");
        stackedAreaChart.getXAxis().lookup(".axis-label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.getYAxis().lookup(".axis-label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.setPrefSize(600, 500);
        stackedAreaChart.setVerticalGridLinesVisible(false);
        stackedAreaChart.setHorizontalGridLinesVisible(false);

        updateWorldChartData(currentMode);

    }

    public void updateWorldChartData(ModeType mode) {

        stackedAreaChart.getData().clear();

        ArrayList<String> results = chartController.getWorldScores(mode);
        XYChart.Series<String, Number> scoreSeries = new XYChart.Series<>();
        scoreSeries.getData().clear();
        scoreSeries.setName(mode.toString());


        if (results == null) {
            throw new NullPointerException("score or date is null");
        }
        for (String s : results) {
            String[] parts = s.split(" ");
            int points = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1].substring(8, 10));
            int month = Integer.parseInt(parts[1].substring(5, 7));
            String date = day + "/" + month;
            scoreSeries.getData().add(new XYChart.Data<>(date,points));
            System.out.println(mode + ": " + " date: " + date + " points: " + points);
        }
        System.out.println("");
        System.out.println("");

        stackedAreaChart.getData().add(scoreSeries);
        results.clear();
    }

    public void updateUserChartData(ModeType mode) {

        stackedAreaChart.getData().clear();
        ArrayList<String> results = chartController.getUserScores(mode);
        XYChart.Series<String, Number> scoreSeries = new XYChart.Series<>();
        scoreSeries.setName(mode.toString());

        for (String s : results) {
            String[] parts = s.split(" ");
            int points = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1].substring(8, 10));
            int month = Integer.parseInt(parts[1].substring(5, 7));
            String date = day + "/" + month;
            scoreSeries.getData().add(new XYChart.Data<>(date,points));
            System.out.println(mode + ": " + " date: " + date + " points: " + points);
        }
        System.out.println("");
        System.out.println("");

        stackedAreaChart.getData().add(scoreSeries);
    }
}