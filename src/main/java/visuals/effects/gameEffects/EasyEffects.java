package visuals.effects.gameEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;


public class EasyEffects extends AbstractGameEffects implements IGameEffects{

    private GridPane easyGridi;
    private ImageView background;
    private ImageView easyTop;
    private ImageView easyL;
    private ImageView easyBot;
    private ImageView play;
    private ImageView returngame;
    private Pane scorePane;
    private ImageView easy3Dgrid;
    private ImageView easyneo;
    private ImageView easyEnd;

    public void setImagesAndComponents(
            ImageView background, ImageView easyTop, ImageView easyBot,
            ImageView easyL, ImageView easy3Dgrid, ImageView play,
            ImageView returngame, GridPane easyGridi, ImageView easyEnd,
            ImageView easyneo, Pane scorePane) {

        this.background = background;
        this.easyTop = easyTop;
        this.easyBot = easyBot;
        this.easyL = easyL;
        this.easy3Dgrid = easy3Dgrid;
        this.play = play;
        this.returngame = returngame;
        this.easyGridi = easyGridi;
        this.easyEnd = easyEnd;
        this.easyneo = easyneo;
        this.scorePane = scorePane;
    }

    @Override
    public void entrance() {

        easyneo.setOpacity(0.55);

        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(easy3Dgrid.opacityProperty(),0),
                        new KeyValue(easyneo.opacityProperty(),0.55)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(easyTop.visibleProperty(),true),
                        new KeyValue(easyTop.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(easyL.visibleProperty(),true),            //true
                        new KeyValue(easyTop.opacityProperty(),0.55),
                        new KeyValue(easyL.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(easyBot.visibleProperty(),true),
                        new KeyValue(easyL.opacityProperty(),1),                //1
                        new KeyValue(easyBot.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(easyBot.opacityProperty(),1),
                        new KeyValue(easyEnd.opacityProperty(),0),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(easy3Dgrid.opacityProperty(),0.75),
                        new KeyValue(easyEnd.opacityProperty(),1),
                        new KeyValue(easyneo.opacityProperty(),0.55),
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1))
        );

        timeline.playFromStart();
    }

    @Override
    public void wallsOff() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(easyGridi.opacityProperty(),1),
                        new KeyValue(easyEnd.opacityProperty(),1),
                        new KeyValue(easyneo.opacityProperty(),0.5),
                        new KeyValue(easy3Dgrid.opacityProperty(),0.75),
                        new KeyValue(scorePane.opacityProperty(),1),
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(easyEnd.opacityProperty(),0),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(easyBot.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(easyBot.opacityProperty(),0),
                        new KeyValue(easyL.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(easyL.opacityProperty(),0),
                        new KeyValue(easyTop.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(easyTop.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1.2),
                        new KeyValue(easyGridi.opacityProperty(),0),
                        new KeyValue(easyneo.opacityProperty(),0),
                        new KeyValue(easy3Dgrid.opacityProperty(),0),
                        new KeyValue(scorePane.opacityProperty(),0))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            timeline.stop();
            super.changeToMenu(ModeType.EASY);
        });
    }
}
