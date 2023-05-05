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

/**

 The MediumEffects class represents the visual effects for the medium mode of the game.

 It extends the AbstractGameEffects class and implements the IGameEffects interface.
 */
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



    /**

     Constructor for the MediumEffects class.
     @param gameController The game controller.
     */
    public MediumEffects(FXIGameController gameController) {

        this.gameController = gameController;

    }

    /**

     Method to set the various images and components used in the game.

     @param midgrid The medium grid image.

     @param midTop The medium top wall image.

     @param midL The medium left wall image.

     @param midBot The medium bottom wall image.

     @param midend The medium end wall image.

     @param midneo The medium neon wall image.

     @param play The play button image.

     @param returngame The return to game button image.

     @param mediumGrid The medium grid pane.

     @param scorePane The score pane.
     */
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

    /**

     Method to perform the entrance animation for the medium mode.

     Sets a timeline to animate the various images and components in sequence.

     On finished, sets the game controller to start the game.
     */
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
