package visuals.effects.gameEffects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
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
    private ImageView practiceButton;
    private ImageView practiseTree;

    private FXIGameController gameController;

    public HardEffects(FXIGameController gameController) {

        this.gameController= gameController;

    }
    public void setImagesAndComponents(

            ImageView hardBackground,Pane scorePane,GridPane hardGrid,
            ImageView hardGridImage,ImageView hardR,ImageView hardL,
            ImageView hardneo,ImageView play,ImageView returngame, ImageView practiseButton, ImageView practiseTree) {

        this.hardBackground = hardBackground;
        this.scorePane = scorePane;
        this.hardGrid = hardGrid;
        this.hardGridImage = hardGridImage;
        this.hardR = hardR;
        this.hardL = hardL;
        this.hardneo = hardneo;
        this.play = play;
        this.returngame = returngame;
        this.practiceButton = practiseButton;
        this.practiseTree = practiseTree;

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
                        new KeyValue(returngame.opacityProperty(),0),
                        new KeyValue(practiceButton.opacityProperty(),0),
                        new KeyValue(practiseTree.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.9),
                        new KeyValue(hardL.opacityProperty(),0.3),
                        new KeyValue(hardGridImage.opacityProperty(),1),
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1),
                        new KeyValue(practiceButton.opacityProperty(),1),
                        new KeyValue(practiseTree.opacityProperty(),1))
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
                        new KeyValue(returngame.opacityProperty(),1),
                        new KeyValue(practiceButton.opacityProperty(),1),
                        new KeyValue(practiseTree.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(hardL.opacityProperty(),0.3),
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0),
                        new KeyValue(practiceButton.opacityProperty(),0),
                        new KeyValue(practiseTree.opacityProperty(),0)),
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

    public void practiseColorsOn() {

        Bloom bloom = new Bloom();
        bloom.setThreshold(0.77);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0);

        colorAdjust.setInput(bloom);

        ColorAdjust treeButtonColors = new ColorAdjust();
        treeButtonColors.setBrightness(0.15);
        treeButtonColors.setHue(-1.0);
        treeButtonColors.setSaturation(1);

        ColorAdjust neoColor = new ColorAdjust();
        neoColor.setHue(0);

        hardneo.setEffect(neoColor);
        practiseTree.setEffect(treeButtonColors);
        hardBackground.setEffect(colorAdjust);


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(colorAdjust.hueProperty(),0),
                        new KeyValue(treeButtonColors.hueProperty(),treeButtonColors.getHue()),
                        new KeyValue(neoColor.hueProperty(),0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(colorAdjust.hueProperty(),0.37),
                        new KeyValue(treeButtonColors.hueProperty(),0),
                        new KeyValue(neoColor.hueProperty(),-0.58))
        );

        timeline.play();

    }

    public void practiseColorsOff() {

        Bloom bloom = new Bloom();
        bloom.setThreshold(0.77);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0.37);

        colorAdjust.setInput(bloom);

        ColorAdjust treeButtonColors = new ColorAdjust();
        treeButtonColors.setBrightness(0.15);
        treeButtonColors.setHue(0);
        treeButtonColors.setSaturation(1);

        ColorAdjust neoColor = new ColorAdjust();
        neoColor.setHue(-0.58);

        hardneo.setEffect(neoColor);
        practiseTree.setEffect(treeButtonColors);
        hardBackground.setEffect(colorAdjust);


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(colorAdjust.hueProperty(),0.37),
                        new KeyValue(treeButtonColors.hueProperty(),treeButtonColors.getHue()),
                        new KeyValue(neoColor.hueProperty(),neoColor.getHue())),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(colorAdjust.hueProperty(),0),
                        new KeyValue(treeButtonColors.hueProperty(),-1.0),
                        new KeyValue(neoColor.hueProperty(),0))
        );

        timeline.play();



    }
}
