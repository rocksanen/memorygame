package visuals;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import model.ModeType;

import java.util.ArrayList;

public class Visibilities {

    private static Visibilities instance;
    private Pane gameMode;
    private Pane score;
    private Pane logAndReg;
    private ImageView pergament;
    private ImageView background;
    private ImageView mediumBackground;
    private ImageView mediumSpread;
    private ImageView hardBackground;
    private ImageView hardSpread;

    private ImageView midgrid;
    private ImageView midR;
    private ImageView midTop;
    private ImageView midL;
    private ImageView midBot;
    private ImageView midend;
    private ImageView easyTop;
    private ImageView easyL;
    private ImageView easyBot;

    private ArrayList<ImageView> gameWalls;

    public void setGameBackGrounds(
            ImageView background,ImageView mediumBackground,ImageView mediumSpread,
            ImageView hardBackground,ImageView hardSpread,ImageView midgrid,
            ImageView midR,ImageView midTop,ImageView midL,ImageView midBot,
            ImageView midend, ImageView easyTop, ImageView easyL, ImageView easyBot) {

        this.background = background;
        this.mediumBackground = mediumBackground;
        this.mediumSpread = mediumSpread;
        this.hardBackground = hardBackground;
        this.hardSpread = hardSpread;
        this.midgrid = midgrid;
        this.midR = midR;
        this.midTop = midTop;
        this.midL = midL;
        this.midBot = midBot;
        this.midend = midend;
        this.easyTop = easyTop;
        this.easyL = easyL;
        this.easyBot = easyBot;

        this.gameWalls = new ArrayList<>();

        gameWalls.add(this.midgrid);
        gameWalls.add(this.easyTop);
        gameWalls.add(this.easyL);
        gameWalls.add(this.easyBot);
        gameWalls.add(this.midTop);
        gameWalls.add(this.midL);
        gameWalls.add(this.midBot);
    }

    public void gameWallVisibilityOff() {

        for(ImageView wall: gameWalls) {
            wall.setVisible(false);
        }
    }
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
            //logAndReg.setVisible(false);
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



        public void gameBackGroundVisibility(ModeType type) {

            switch (type) {

                case EASY -> {
                    background.setVisible(true);
                    background.setOpacity(1);
                    mediumBackground.setOpacity(0);
                    mediumSpread.setOpacity(0);
                    hardBackground.setOpacity(0);
                    hardSpread.setOpacity(0);
                }
                case MEDIUM -> {
                    mediumBackground.setVisible(true);
                    mediumSpread.setVisible(false);        ////true!!!!!!!!!!!!!!!!!!!!!!!!
                    mediumBackground.setOpacity(1);
                    mediumSpread.setOpacity(1);
                    background.setOpacity(0);
                    hardBackground.setOpacity(0);
                    hardSpread.setOpacity(0);
                }
                case HARD -> {
                    hardBackground.setVisible(true);
                    hardSpread.setVisible(true);
                    hardBackground.setOpacity(1);
                    hardSpread.setOpacity(1);
                    background.setOpacity(0);
                    mediumBackground.setOpacity(0);
                    mediumSpread.setOpacity(0);
                }
        }
    }
}
