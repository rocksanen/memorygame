package visuals;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Visibilities {

    private static Visibilities instance;
    private Pane gameMode;
    private Pane score;
    private Pane logAndReg;
    private ImageView pergament;

    public Visibilities(){}

    public static Visibilities getInstance() {

        if(instance == null) {

            instance = new Visibilities();

        }

        return instance;
    }

        public void setToGameObjects(Pane gameMode, Pane score, Pane logAndReg, ImageView pergament) {

            this.gameMode = gameMode;
            this.score = score;
            this.logAndReg = logAndReg;
            this.pergament = pergament;
        }
        public void toGame() {

            gameMode.setVisible(false);
            pergament.setVisible(false);
            score.setVisible(true);
            logAndReg.setVisible(false);
        }

        public void offGame(ImageView gameBackGround) {

            Platform.runLater(() -> gameBackGround.setVisible(false));
            Platform.runLater(() -> pergament.setVisible(true));
            gameMode.setVisible(true);
        }

        public void setGridLayoutToVisibility(GridPane easy, GridPane medium, GridPane hard) {

            easyGridLayout(-80, easy);
            mediumGridLayout(25,20, medium);
            hardGridLayout(58,120,10, 5, hard);
        }

        private void easyGridLayout(double hGap, GridPane gridPane) {

            gridPane.setHgap(hGap);
        }

        private void mediumGridLayout(double hGap, double vGap, GridPane gridPane) {

            gridPane.setHgap(hGap);
            gridPane.setVgap(vGap);
        }

        private void hardGridLayout(double hGap, double vGap, int rowConstraint, int rows,GridPane gridPane) {

            gridPane.setHgap(hGap);
            gridPane.setVgap(vGap);
            for (int i = 0; i < rows; i++) {
                RowConstraints rowConstraints = new RowConstraints(rowConstraint);
                gridPane.getRowConstraints().add(rowConstraints);
            }
        }

        public void inGameGrid(GridPane gridPane){

            gridPane.setMouseTransparent(false);
            gridPane.setVisible(true);
            gridPane.setOpacity(1);
        }

        public void offGameGrid(GridPane gridPane) {

            gridPane.setMouseTransparent(true);
            gridPane.setVisible(false);
            gridPane.setOpacity(0);
        }
}
