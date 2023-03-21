package visuals.effects.gameEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;

public class MediumEffects extends AbstractGameEffects implements IGameEffects{

    private ImageView mediumbackground;
    private ImageView midgrid;
    private ImageView midTop;
    private ImageView midL;
    private ImageView midBot;
    private ImageView midend;
    private ImageView midneo;
    private ImageView midneo2;
    private ImageView midneo3;
    private ImageView midneo4;
    private ImageView play;
    private ImageView returngame;
    private GridPane mediumGrid;
    private Pane scorePane;

    public void setImagesAndComponents(
            ImageView mediumbackground, ImageView midgrid, ImageView midTop,
            ImageView midL, ImageView midBot, ImageView midend, ImageView midneo,
            ImageView midneo2, ImageView midneo3, ImageView midneo4, ImageView play,
            ImageView returngame, GridPane mediumGrid, Pane scorePane) {

        this.mediumbackground = mediumbackground;
        this.midgrid = midgrid;
        this.midTop = midTop;
        this.midL = midL;
        this.midBot = midBot;
        this.midend = midend;
        this.midneo = midneo;
        this.midneo2 = midneo2;
        this.midneo3 = midneo3;
        this.midneo4 = midneo4;
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
            Timeline neoline = new Timeline(
                    new KeyFrame(Duration.seconds(0.4),
                            new KeyValue(midneo.opacityProperty(),0)),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(midneo.opacityProperty(),0.68)),
                    new KeyFrame(Duration.seconds(0.9),
                            new KeyValue(midneo2.opacityProperty(),0)),
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(midneo2.opacityProperty(),0.6)),
                    new KeyFrame(Duration.seconds(1.4),
                            new KeyValue(midneo3.opacityProperty(),0)),
                    new KeyFrame(Duration.seconds(1.5),
                            new KeyValue(midneo3.opacityProperty(),0.3),
                            new KeyValue(midneo2.opacityProperty(),0)),
                    new KeyFrame(Duration.seconds(1.9),
                            new KeyValue(midneo4.opacityProperty(),0)),
                    new KeyFrame(Duration.seconds(2),
                            new KeyValue(midneo4.opacityProperty(),1),
                            new KeyValue(midneo3.opacityProperty(),0)),
                    new KeyFrame(Duration.seconds(2.5),
                            new KeyValue(midneo4.opacityProperty(),0)),
                    new KeyFrame(Duration.seconds(3))
            );

            neoline.play();
            neoline.setDelay(Duration.seconds(0.5));
            neoline.setOnFinished(actionEvent1 -> neoline.stop());
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
