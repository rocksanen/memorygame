package visuals.effects.gameEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;
import visuals.gameModes.FXIGameController;

public class MediumEffects extends AbstractGameEffects implements IGameEffects{

    private ImageView midgrid;
    private ImageView midTop;
    private ImageView midL;
    private ImageView midBot;
    private ImageView midend;
    private ImageView midneo;
    private ImageView play;
    private ImageView returngame;
    private GridPane mediumGrid;
    private Pane scorePane;


    private final FXIGameController gameController;

    public MediumEffects(FXIGameController gameController) {

        this.gameController = gameController;

    }

    public void setImagesAndComponents(
            ImageView midgrid, ImageView midTop,
            ImageView midL, ImageView midBot, ImageView midend, ImageView midneo,
            ImageView play, ImageView returngame, GridPane mediumGrid, Pane scorePane) {

        this.midgrid = midgrid;
        this.midTop = midTop;
        this.midL = midL;
        this.midBot = midBot;
        this.midend = midend;
        this.midneo = midneo;
        this.play = play;
        this.returngame = returngame;
        this.mediumGrid = mediumGrid;
        this.scorePane = scorePane;

    }
    @Override
    public void entrance() {

        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(midgrid.visibleProperty(),true),
                        new KeyValue(midgrid.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(midTop.visibleProperty(),true),
                        new KeyValue(midTop.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(midL.visibleProperty(),true),
                        new KeyValue(midTop.opacityProperty(),1),
                        new KeyValue(midL.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(midBot.visibleProperty(),true),
                        new KeyValue(midL.opacityProperty(),1),
                        new KeyValue(midBot.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(midBot.opacityProperty(),1),
                        new KeyValue(midend.opacityProperty(),0),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(midend.opacityProperty(),1),
                        new KeyValue(midgrid.opacityProperty(),0.75),
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

                new KeyFrame(Duration.ZERO,
                        new KeyValue(midend.opacityProperty(),1),
                        new KeyValue(midneo.opacityProperty(),0.68),
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1),
                        new KeyValue(scorePane.opacityProperty(),1),
                        new KeyValue(mediumGrid.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(midend.opacityProperty(),0),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(midgrid.opacityProperty(),0.75)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(midBot.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(midBot.opacityProperty(),0),
                        new KeyValue(midL.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(midL.opacityProperty(),0),
                        new KeyValue(midTop.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(1.2),
                        new KeyValue(midTop.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(midgrid.opacityProperty(),0),
                        new KeyValue(scorePane.opacityProperty(),0),
                        new KeyValue(mediumGrid.opacityProperty(),0))
        );

        timeline.playFromStart();
        timeline.setOnFinished(actionEvent -> {
            timeline.stop();
            super.changeToMenu(ModeType.MEDIUM);
        });
    }
}
