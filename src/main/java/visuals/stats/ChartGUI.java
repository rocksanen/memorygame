package visuals.stats;
import controller.ChartController;
import controller.IChartController;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Font;
import model.ModeType;
import java.util.ArrayList;
import java.util.Objects;


/**
 * The type Chart gui.
 */
public class ChartGUI implements IChartGUI {

    public AreaChart<Number, Number> stackedAreaChart() {

        return stackedAreaChart;
    }

    /**
     * The Stacked area chart.
     */
    AreaChart<Number, Number> stackedAreaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

    private final IChartController scoreController2 = new ChartController(this);

    private ModeType currentMode;
    public void init() {
        Font.loadFont(Objects.requireNonNull(getClass().getClassLoader().getResource("fonts/VCR_OSD_MONO_1.001.ttf")).toExternalForm(), 14);

        currentMode = ModeType.EASY;
        stackedAreaChart.getXAxis().setStyle("-fx-tick-label-fill: WHITE; -fx-font-size: 16px;-fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.getYAxis().setStyle("-fx-tick-label-fill: WHITE; -fx-font-size: 16px;-fx-font-family: 'VCR OSD Mono'");

        // Create a StackedAreaChart object
        stackedAreaChart.setTitle("Your Progress");
        stackedAreaChart.lookup(".chart-title").setStyle("-fx-font-size: 23px; -fx-font-weight: BOLD; -fx-text-fill: white; -fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.setLegendVisible(false);
        stackedAreaChart.getXAxis().setLabel("Time (s)");
        stackedAreaChart.getYAxis().setLabel("Points");
        stackedAreaChart.getXAxis().lookup(".axis-label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.getYAxis().lookup(".axis-label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'VCR OSD Mono'");
        stackedAreaChart.setPrefSize(600, 500);
        stackedAreaChart.setVerticalGridLinesVisible(true);
        stackedAreaChart.setHorizontalGridLinesVisible(true);

        updateChartData(currentMode);

    }

    public void updateChartData(ModeType mode) {
        ArrayList<Number> score = scoreController2.getUserScores(mode);
        ArrayList<Number> time = scoreController2.getUserTime(mode);

        XYChart.Series<Number, Number> scoreSeries = new XYChart.Series<>();
        scoreSeries.setName(mode.toString());
        if (score == null || time == null) {
            return;
        }
        for (int i = 0; i < score.size(); i++) {
            scoreSeries.getData().add(new XYChart.Data<>(time.get(i), score.get(i)));
        }
        stackedAreaChart.getData().clear();
        stackedAreaChart.getData().add(scoreSeries);
    }
}