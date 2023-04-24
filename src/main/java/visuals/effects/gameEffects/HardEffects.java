package visuals.effects.gameEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;
import visuals.gameModes.FXIGameController;

public class HardEffects extends AbstractGameEffects implements IGameEffects{


    private ImageView hardSpread;
    private ImageView hardBackground;
    private Pane scorePane;
    private GridPane hardGrid;
    private ImageView hardGridImage;
    private ImageView hardR;
    private ImageView hardL;
    private ImageView hardneo;
    private ImageView play;
    private ImageView returngame;

    private FXIGameController gameController;

    public HardEffects(FXIGameController gameController) {

        this.gameController= gameController;

    }
    public void setImagesAndComponents(

            ImageView hardBackground,Pane scorePane,GridPane hardGrid,
            ImageView hardGridImage,ImageView hardR,ImageView hardL,
            ImageView hardneo,ImageView play,ImageView returngame) {

        this.hardBackground = hardBackground;
        this.scorePane = scorePane;
        this.hardGrid = hardGrid;
        this.hardGridImage = hardGridImage;
        this.hardR = hardR;
        this.hardL = hardL;
        this.hardneo = hardneo;
        this.play = play;
        this.returngame = returngame;

    }
    @Override
    public void entrance() {

        Timeline timeline = new Timeline(

                new KeyFrame(Duration.ZERO,
                        new KeyValue(hardneo.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(hardGridImage.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(hardR.visibleProperty(),true),
                        new KeyValue(hardR.opacityProperty(),0),
                        new KeyValue(hardneo.opacityProperty(),0.6)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(hardL.visibleProperty(),true),
                        new KeyValue(hardR.opacityProperty(),0.3),
                        new KeyValue(hardL.opacityProperty(),0),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.9),
                        new KeyValue(hardL.opacityProperty(),0.3),
                        new KeyValue(hardGridImage.opacityProperty(),1),
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1))
        );

        timeline.playFromStart();
        timeline.setOnFinished(actionEvent -> {

            timeline.stop();
            gameController.setStartGame();
        });

    }

    @Override
    public void wallsOff() {

        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(hardGridImage.opacityProperty(),1),
                        new KeyValue(hardneo.opacityProperty(),hardneo.getOpacity()),
                        new KeyValue(hardGrid.opacityProperty(),1),
                        new KeyValue(scorePane.opacityProperty(),1),
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(hardL.opacityProperty(),0.3),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(hardL.opacityProperty(),0),
                        new KeyValue(hardR.opacityProperty(),0.3)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(hardR.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(hardGridImage.opacityProperty(),0),
                        new KeyValue(hardneo.opacityProperty(),0),
                        new KeyValue(hardGrid.opacityProperty(),0),
                        new KeyValue(scorePane.opacityProperty(),0))
        );

        timeline.playFromStart();
        timeline.setOnFinished(actionEvent -> {

            timeline.stop();
            super.changeToMenu(ModeType.HARD);
        });
    }
}
