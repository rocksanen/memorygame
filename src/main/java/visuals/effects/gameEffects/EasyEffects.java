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

 The EasyEffects class extends AbstractGameEffects and implements IGameEffects.

 This class manages the game effects for the Easy mode.

 It contains the methods to set images and components, and to control the game entrance and walls off events.
 */
public class EasyEffects extends AbstractGameEffects implements IGameEffects{

    private GridPane easyGridi;
    private ImageView easyTop;
    private ImageView easyL;
    private ImageView easyBot;
    private ImageView play;
    private ImageView returngame;
    private Pane scorePane;
    private ImageView easy3Dgrid;
    private ImageView easyneo;
    private ImageView easyEnd;

    private final FXIGameController gameController;

    /**

     Constructs an instance of EasyEffects with the given FXIGameController object.
     @param gameController an object of FXIGameController that controls the game flow
     */
    public EasyEffects(FXIGameController gameController) {

        this.gameController = gameController;

    }

    /**

     Sets the images and components for the Easy mode.
     @param easyTop an ImageView of the top wall
     @param easyBot an ImageView of the bottom wall
     @param easyL an ImageView of the left wall
     @param easy3Dgrid an ImageView of the 3D grid
     @param play an ImageView of the play button
     @param returngame an ImageView of the return button
     @param easyGridi a GridPane of the Easy mode
     @param easyEnd an ImageView of the end message
     @param easyneo an ImageView of the Neo logo
     @param scorePane a Pane of the score panel
     */
    public void setImagesAndComponents(
            ImageView easyTop, ImageView easyBot,
            ImageView easyL, ImageView easy3Dgrid, ImageView play,
            ImageView returngame, GridPane easyGridi, ImageView easyEnd,
            ImageView easyneo, Pane scorePane) {

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

    /**

     Controls the entrance of the Easy mode.
     The method plays a timeline animation that sets the opacity of the Neo logo and the 3D grid to 0,
     makes the top wall visible and sets its opacity to 0,
     makes the left wall visible and sets its opacity to 0.55,
     makes the bottom wall visible and sets its opacity to 0,
     sets the opacity of the end message, play button and return button to 0,
     sets the opacity of the Neo logo to 0.55,
     sets the opacity of the 3D grid to 0.75,
     and sets the visibility of the play button and the return button to true.
     */
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
        timeline.setOnFinished(actionEvent -> {

            timeline.stop();
            gameController.setStartGame();
        });
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
