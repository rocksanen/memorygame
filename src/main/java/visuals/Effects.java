package visuals;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.ModeType;
import org.jetbrains.annotations.NotNull;
import visuals.audio.AudioMemory;
import visuals.effects.BackGroundMover;
import visuals.menu.Menu;


public class Effects {
    private static Effects instance;
    private final Timeline timelineBlur = new Timeline();
    private final GaussianBlur gaussianBlur = new GaussianBlur();
    private Timeline glowLine;
    private ImageView japan;
    private ImageView jungle;
    private ImageView redtree;
    private ImageView miniEasy;
    private ImageView miniMedium;
    private ImageView miniHard;
    private ImageView easyFrame;
    private ImageView mediumFrame;
    private ImageView hardFrame;
    private ImageView pergament;
    private AnchorPane menuAnkkuri;
    private AnchorPane startBlack;
    private GridPane cubeGrid;
    private ImageView gameBackGround;
    private Pane gamePane;
    private final double japanStart = 0.6; //0.26
    private final double jungleStart = 0.29; // 0.2
    private final double redtreeStart = 0.75; //0.35
    private ModeType type;
    private double zOffset;
    private double fovOffset;
    private double xOffset;
    private double yOffset;
    private ImageView burningsun;
    private ImageView midgrid;
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    private final Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    private final Rotate jungleZ = new Rotate(0, Rotate.Z_AXIS);


    ImageView midTop;
    ImageView midL;
    ImageView midBot;
    ImageView easyTop;
    ImageView easyL;
    ImageView easyBot;
    ImageView hardGridImage;
    ImageView hardR;
    ImageView hardL;
    ImageView telkku;
    ImageView mediumSpread;
    ImageView dirt;
    ImageView play;
    ImageView returngame;
    ImageView movingjungle;
    ImageView easyend;
    ImageView midneo;
    ImageView midneo2;
    ImageView midneo3;
    ImageView midneo4;
    ImageView easyneo;
    ImageView hardneo;
    private ImageView mediumBackGround;
    private final BackGroundMover backGroundMover = new BackGroundMover();

    private Timeline burningSunLine;
    private Label labelLoggedIn;

    public Effects() {
        initializeTimelines();
    }

    public void setEssenceImages(ImageView japan, ImageView jungle, ImageView redtree) {

        this.japan = japan;
        this.jungle = jungle;
        this.redtree = redtree;
    }


    public void setBackGrounds(
            ImageView mediumBackGround, ImageView midgrid, ImageView midTop,
            ImageView midL, ImageView midBot, ImageView easyTop, ImageView easyL,
            ImageView easyBot, ImageView hardGridImage, ImageView hardR,
            ImageView hardL, ImageView mediumSpread, ImageView dirt, ImageView play,
            ImageView returngame, ImageView movingjungle, ImageView easyend,
            ImageView midneo,ImageView midneo2,ImageView midneo3,ImageView midneo4,
            ImageView easyneo, ImageView hardneo) {

        this.mediumBackGround = mediumBackGround;
        this.midgrid = midgrid;
        this.midTop = midTop;
        this.midL = midL;
        this.midBot = midBot;
        this.easyTop = easyTop;
        this.easyL = easyL;
        this.easyBot = easyBot;
        this.hardGridImage = hardGridImage;
        this.hardR = hardR;
        this.hardL = hardL;
        this.mediumSpread = mediumSpread;
        this.dirt = dirt;
        this.play = play;
        this.returngame = returngame;
        this.movingjungle = movingjungle;
        this.easyend = easyend;
        this.midneo = midneo;
        this.midneo2 = midneo2;
        this.midneo3 = midneo3;
        this.midneo4 = midneo4;
        this.easyneo = easyneo;
        this.hardneo = hardneo;

    }

    public void setGeneralObjects(
            ImageView pergament, AnchorPane menuAnkkuri, AnchorPane startBlack,
            Pane gamePane, ImageView burningsun, Label textLoggedIn, ImageView telkku) {

        this.pergament = pergament;
        this.menuAnkkuri = menuAnkkuri;
        this.startBlack = startBlack;
        this.gamePane = gamePane;
        this.burningsun = burningsun;
        this.labelLoggedIn = textLoggedIn;
        this.telkku = telkku;

    }

    public void setMiniImagesAndFrames(

            ImageView miniEasy, ImageView miniMedium, ImageView miniHard,
            ImageView easyFrame, ImageView mediumFrame, ImageView hardFrame) {

        this.miniEasy = miniEasy;
        this.miniMedium = miniMedium;
        this.miniHard = miniHard;
        this.easyFrame = easyFrame;
        this.mediumFrame = mediumFrame;
        this.hardFrame = hardFrame;
    }

    private void initializeTimelines() {

        timelineBlur.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(gaussianBlur.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(6), new KeyValue(gaussianBlur.radiusProperty(), 10.58))
        );
    }

    public static Effects getInstance() {

        if (instance == null) {
            instance = new Effects();
        }
        return instance;
    }

    public void backGroundBlurIn(@NotNull ImageView imageView) {

        gaussianBlur.setRadius(0);
        imageView.setEffect(gaussianBlur);
        timelineBlur.stop();
        timelineBlur.play();
    }


    public void gameZoomOut(

            GridPane cubeGrid,
            ImageView gameBackGround, double zOffset,
            double fovOffset, double xOffset, double yOffset, @NotNull ModeType type
    ) {

        this.cubeGrid = cubeGrid;
        this.gameBackGround = gameBackGround;
        this.zOffset = zOffset;
        this.fovOffset = fovOffset;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.type = type;

        Platform.runLater(() -> Visibilities.getInstance().offGameGrid(cubeGrid));  // This is actually very important...

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(cubeGrid.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(cubeGrid.opacityProperty(),0))
        );

        timeline.playFromStart();


            switch (type) {

                case EASY -> {
                    zoomOutEasyWalls();
                    miniEasy.setOpacity(1);
                }
                case MEDIUM -> {
                    zoomOutMediumWalls();
                    miniMedium.setOpacity(1);
                }
                case HARD -> {
                    zoomOutHardWalls();
                    miniHard.setOpacity(1);
                }
            }

            Platform.runLater(backGroundMover::stop);
            Platform.runLater(backGroundMover::returnToPositionZero);

    }

    private void zoomOutEasyWalls() {

       Timeline timeline = new Timeline(
               new KeyFrame(Duration.ZERO,
                       new KeyValue(midgrid.opacityProperty(),0.75),
                       new KeyValue(easyend.opacityProperty(),1),
                       new KeyValue(easyneo.opacityProperty(),0.5)),
               new KeyFrame(Duration.seconds(0.2),
                       new KeyValue(easyend.opacityProperty(),0)),
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
                       new KeyValue(midgrid.opacityProperty(),0),
                       new KeyValue(easyneo.opacityProperty(),0))
       );

       timeline.play();

        timeline.setOnFinished(actionEvent -> {

            timeline.stop();
            zoomOutBlurEndings();

        });



    }

    private void zoomOutMediumWalls() {


        Timeline timeline = new Timeline(

                new KeyFrame(Duration.ZERO,
                        new KeyValue(movingjungle.opacityProperty(),1),
                        new KeyValue(midneo.opacityProperty(),0.68)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(movingjungle.opacityProperty(),0)),
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
                        new KeyValue(midgrid.opacityProperty(),0))
        );

        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {

            timeline.stop();

            zoomOutBlurEndings();

        });
    }

    private void zoomOutHardWalls() {


        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(hardGridImage.opacityProperty(),1),
                        new KeyValue(hardneo.opacityProperty(),hardneo.getOpacity())),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(hardL.opacityProperty(),0.3)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(hardL.opacityProperty(),0),
                        new KeyValue(hardR.opacityProperty(),0.3)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(hardR.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(hardGridImage.opacityProperty(),0),
                        new KeyValue(hardneo.opacityProperty(),0))
        );

        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {

            timeline.stop();

            zoomOutBlurEndings();


        });




    }

    private void zoomOutBlur() {


            zoomOutBlurEndings();

    }

    private void zoomOutCamera() {

        Timeline timelineZoomOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(Menu.camera.translateZProperty(), Menu.camera.getTranslateZ()),
                        new KeyValue(Menu.camera.fieldOfViewProperty(), Menu.camera.getFieldOfView()),
                        new KeyValue(Menu.camera.translateXProperty(), Menu.camera.getTranslateX()),
                        new KeyValue(Menu.camera.translateYProperty(), Menu.camera.getTranslateY())
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(telkku.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(Menu.camera.translateZProperty(), 0),
                        new KeyValue(Menu.camera.fieldOfViewProperty(), 25),
                        new KeyValue(Menu.camera.translateXProperty(), 0),
                        new KeyValue(Menu.camera.translateYProperty(), 0),
                        new KeyValue(telkku.opacityProperty(),1)
                )
        );

        timelineZoomOut.playFromStart();
        zoomOutOpacities();

        timelineZoomOut.setOnFinished(actionEvent1 -> {

            timelineZoomOut.stop();
            zoomOutCameraEndings();
        });
    }

    private void zoomOutOpacities() {

        mediumSpread.setOpacity(0);
        mediumSpread.setVisible(false);
        Timeline opacityOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(pergament.opacityProperty(), 0),
                        new KeyValue(miniEasy.opacityProperty(), miniEasy.getOpacity()),
                        new KeyValue(miniMedium.opacityProperty(), miniMedium.getOpacity()),
                        new KeyValue(miniHard.opacityProperty(), miniHard.getOpacity()),
                        new KeyValue(japan.opacityProperty(), 0),
                        new KeyValue(jungle.opacityProperty(), 0),
                        new KeyValue(redtree.opacityProperty(), 0),
                        new KeyValue(easyFrame.opacityProperty(),0),
                        new KeyValue(mediumFrame.opacityProperty(),0),
                        new KeyValue(hardFrame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(hardFrame.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(pergament.opacityProperty(), 1),
                        new KeyValue(miniEasy.opacityProperty(), 1),
                        new KeyValue(miniMedium.opacityProperty(), 1),
                        new KeyValue(miniHard.opacityProperty(), 1),
                        new KeyValue(japan.opacityProperty(), japanStart),
                        new KeyValue(jungle.opacityProperty(), jungleStart),
                        new KeyValue(redtree.opacityProperty(), redtreeStart))
        );

        opacityOut.playFromStart();
        opacityOut.setOnFinished(actionEvent -> {
            opacityOut.stop();
        });
    }

    private void zoomOutCameraEndings() {

        Platform.runLater(this::playGlow);
        Platform.runLater(() -> menuAnkkuri.setMouseTransparent(false));
    }

    private void zoomOutBlurEndings() {

        Visibilities.getInstance().gameWallVisibilityOff();
        Visibilities.getInstance().offGame(gameBackGround);
        midneo.setOpacity(0);

        Menu.camera.setTranslateZ(zOffset);
        Menu.camera.setFieldOfView(fovOffset);
        Menu.camera.setTranslateX(xOffset);
        Menu.camera.setTranslateY(yOffset);

        menuAnkkuri.setOpacity(1);

        Platform.runLater(() -> Menu.logAndReg.setVisible(true));
        Platform.runLater(() -> AudioMemory.getInstance().stopSong(type));
        Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.MENU));

        zoomOutCamera();
        zoomOutOpacities();
    }

    public void gameZoomIn(

            ImageView gameBackGround,
            double zOffset, double fovOffset, double xOffset,
            double yOffset, @NotNull ModeType type, Menu menu

    ) {

        this.zOffset = zOffset;
        this.fovOffset = fovOffset;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.gameBackGround = gameBackGround;
        this.type = type;
        double easyFinish = 0;
        double mediumFinish = 0;
        double hardFinish = 0;
        double easyFrameFinish = 0;
        double mediumFrameFinish = 0;
        double hardFrameFinish = 0;

        switch (type) {

            case EASY -> {
                miniEasy.setOpacity(1);
                easyFinish = 1;
                easyFrameFinish = 0;
            }
            case MEDIUM -> {
                miniMedium.setOpacity(1);
                mediumFinish = 1;
                mediumFrameFinish = 0;
                mediumSpread.setVisible(true);

            }
            case HARD -> {
                miniHard.setOpacity(1);
                hardFinish = 1;
                hardFrameFinish = 0;
            }
        }

        cameraZoomIn(menu);
        opacitiesIn(easyFinish, mediumFinish, hardFinish, easyFrameFinish, mediumFrameFinish, hardFrameFinish);
    }


    private void cameraZoomIn(Menu menu) {

        Timeline timelineZoomIn = new Timeline(

                new KeyFrame(Duration.ZERO,
                        new KeyValue(Menu.camera.translateZProperty(), 0),
                        new KeyValue(Menu.camera.fieldOfViewProperty(), 25),
                        new KeyValue(Menu.camera.translateXProperty(), 0),
                        new KeyValue(Menu.camera.translateYProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(Menu.camera.translateZProperty(), 0 + zOffset / 2),
                        new KeyValue(Menu.camera.fieldOfViewProperty(), 25 + fovOffset / 2),
                        new KeyValue(Menu.camera.translateXProperty(), 0 + xOffset / 2),
                        new KeyValue(Menu.camera.translateYProperty(), 0 + yOffset / 2)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(Menu.camera.translateZProperty(), 0 + zOffset),
                        new KeyValue(Menu.camera.fieldOfViewProperty(), 25 + fovOffset),
                        new KeyValue(Menu.camera.translateXProperty(), 0 + xOffset),
                        new KeyValue(Menu.camera.translateYProperty(), 0 + yOffset)
                ),
                new KeyFrame(Duration.seconds(3))

        );
        timelineZoomIn.play();

        timelineZoomIn.setOnFinished(actionEvent -> {

            telkku.setOpacity(0);

            timelineZoomIn.stop();
            AudioMemory.getInstance().stopSong(ModeType.MENU);
            AudioMemory.getInstance().playSong(type);
            cameraZoomInEndings(menu);
        });
    }

    private void cameraZoomInEndings(Menu menu) {


        quickSwitchCamera(menu);
        menuAnkkuri.setMouseTransparent(true);
    }

    private void quickSwitchCamera(Menu menu) {

        Timeline quickSwitch = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(menuAnkkuri.opacityProperty(), 1),
                        new KeyValue(Menu.camera.translateXProperty(), Menu.camera.getTranslateX()),
                        new KeyValue(Menu.camera.translateYProperty(), Menu.camera.getTranslateY()),
                        new KeyValue(Menu.camera.translateZProperty(), Menu.camera.getTranslateZ())
                ),
                new KeyFrame(Duration.seconds(0.0001),
                        new KeyValue(menuAnkkuri.opacityProperty(), 0),
                        new KeyValue(Menu.camera.translateXProperty(), 0),
                        new KeyValue(Menu.camera.translateYProperty(), 0),
                        new KeyValue(Menu.camera.translateZProperty(), 0)
                )
        );

        menuAnkkuri.setMouseTransparent(true);
        quickSwitch.play();



        quickSwitch.setOnFinished(actionEvent1 -> {


            quickSwitch.stop();
            quickSwitchCameraEndings(menu);
        });
    }

    private void quickSwitchCameraEndings(@NotNull Menu menu) {

        Menu.camera.setFieldOfView(1);



        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.3))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            switch (type) {

                case EASY -> easyEntrance(menu);
                case MEDIUM -> mediumEntrance(menu);
                case HARD -> hardEntrance(menu);
            }
        });
        //Platform.runLater(() -> backGroundBlurIn(gameBackGround));
    }

    private void easyEntrance(Menu menu) {

        easyneo.setOpacity(0.55);

        Timeline timeline = new Timeline(

                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(midgrid.visibleProperty(),true),
                        new KeyValue(midgrid.opacityProperty(),0),
                        new KeyValue(easyneo.opacityProperty(),0.55)),
                new KeyFrame(Duration.seconds(0.2),
                        new KeyValue(easyTop.visibleProperty(),true),
                        new KeyValue(easyTop.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(easyL.visibleProperty(),false),            //true
                        new KeyValue(easyTop.opacityProperty(),0.55),
                        new KeyValue(easyL.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(easyBot.visibleProperty(),true),
                        new KeyValue(easyL.opacityProperty(),0),                //1
                        new KeyValue(easyBot.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(easyBot.opacityProperty(),1),
                        new KeyValue(easyend.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(midgrid.opacityProperty(),0.75),
                        new KeyValue(easyend.opacityProperty(),1),
                        new KeyValue(easyneo.opacityProperty(),0.55))
        );

        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            timeline.stop();

            zoomInFinalEndings(menu);

        });
    }

    private void mediumEntrance(Menu menu) {

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
                        new KeyValue(movingjungle.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(movingjungle.opacityProperty(),1),
                        new KeyValue(midgrid.opacityProperty(),0.75))
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
            zoomInFinalEndings(menu);



            neoline.setOnFinished(actionEvent1 -> {
                neoline.stop();

            });




        });
    }

    private void hardEntrance(Menu menu) {



        Timeline timeline = new Timeline(

                new KeyFrame(Duration.ZERO,
                        new KeyValue(hardneo.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(hardGridImage.visibleProperty(),true),
                        new KeyValue(hardGridImage.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(hardR.visibleProperty(),true),
                        new KeyValue(hardR.opacityProperty(),0),
                        new KeyValue(hardneo.opacityProperty(),0.6)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(hardL.visibleProperty(),true),
                        new KeyValue(hardR.opacityProperty(),0.3),
                        new KeyValue(hardL.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.9),
                        new KeyValue(hardL.opacityProperty(),0.3),
                        new KeyValue(hardGridImage.opacityProperty(),1))
        );

        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            timeline.stop();
            zoomInFinalEndings(menu);

        });
    }

    private void zoomInFinalEndings(Menu menu) {

        menu.startChoose(type);
        Platform.runLater(() -> backGroundMover.animate(gameBackGround));
        Platform.runLater(backGroundMover::play);

        Timeline playAndreturn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(play.opacityProperty(),0),
                        new KeyValue(returngame.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(play.opacityProperty(),1),
                        new KeyValue(returngame.opacityProperty(),1))
        );

        playAndreturn.play();
        playAndreturn.setOnFinished(actionEvent1 -> {
            playAndreturn.stop();

            //animateMediumNeonLambs();
        });
    }

    private void animateMediumNeonLambs() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(midneo.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.9),
                        new KeyValue(midneo.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(midneo.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(midneo2.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(midneo2.opacityProperty(),0.5),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(2.5),
                        new KeyValue(midneo2.opacityProperty(),0.5),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(midneo2.opacityProperty(),0),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(3.5),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo2.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(midneo3.opacityProperty(),0.3),
                        new KeyValue(midneo2.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(4.5),
                        new KeyValue(midneo3.opacityProperty(),0.3),
                        new KeyValue(midneo2.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo2.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo4.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(5.5),
                        new KeyValue(midneo4.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(6),
                        new KeyValue(midneo4.opacityProperty(),0.9),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo2.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(6.5),
                        new KeyValue(midneo4.opacityProperty(),0.9),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo2.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(7),
                        new KeyValue(midneo4.opacityProperty(),0),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo2.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(7.5),
                        new KeyValue(midneo4.opacityProperty(),0),
                        new KeyValue(midneo3.opacityProperty(),0),
                        new KeyValue(midneo.opacityProperty(),0),
                        new KeyValue(midneo2.opacityProperty(),0))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();




    }

    private void opacitiesIn(
            double easyFinish, double mediumFinish, double hardFinish,
            double easeFrameFinish, double mediumFrameFinish, double hardFrameFinish) {

        Timeline opacitiesIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(pergament.opacityProperty(), 1),
                        new KeyValue(miniEasy.opacityProperty(), 1),
                        new KeyValue(miniMedium.opacityProperty(), 1),
                        new KeyValue(miniHard.opacityProperty(), 1),
                        new KeyValue(japan.opacityProperty(), japanStart),
                        new KeyValue(jungle.opacityProperty(), jungleStart),
                        new KeyValue(redtree.opacityProperty(), redtreeStart),
                        new KeyValue(easyFrame.opacityProperty(), 1),
                        new KeyValue(mediumFrame.opacityProperty(), 1),
                        new KeyValue(hardFrame.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(2.3),
                        new KeyValue(easyFrame.opacityProperty(), 1),
                        new KeyValue(mediumFrame.opacityProperty(), 1),
                        new KeyValue(hardFrame.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(2.5),
                        new KeyValue(easyFrame.opacityProperty(), 0),
                        new KeyValue(mediumFrame.opacityProperty(), 0),
                        new KeyValue(hardFrame.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(2.8),
                        new KeyValue(pergament.opacityProperty(), 0),
                        new KeyValue(miniEasy.opacityProperty(), easyFinish),
                        new KeyValue(miniMedium.opacityProperty(), mediumFinish),
                        new KeyValue(miniHard.opacityProperty(), hardFinish),
                        new KeyValue(japan.opacityProperty(), 0),
                        new KeyValue(redtree.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(jungle.opacityProperty(), 0.3))
        );

        opacitiesIn.playFromStart();
        opacitiesIn.setOnFinished(actionEvent -> {
            opacitiesIn.stop();
        });


    }


    public void intro(
            @NotNull Label first, @NotNull ImageView groupFour,
            @NotNull Pane logAndReg, @NotNull ImageView sun,
            @NotNull ImageView lightning, @NotNull ImageView blacksun,
            @NotNull ImageView easyFrame, @NotNull ImageView mediumFrame, @NotNull ImageView hardFrame,
            ImageView memomaze, Label textLoggedIn, ImageView loading,
            ImageView kotoku, ImageView tigerden, ImageView treeoflife) {


        AudioMemory.noIntro = true;
        SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(1);
        Platform.runLater(() -> pergament.setEffect(sepiaTone));

        SepiaTone sunTone = new SepiaTone();
        sunTone.setLevel(0);
        Platform.runLater(() -> sun.setEffect(sunTone));

        Timeline timelySun = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(28.5))
        );


        timelySun.setOnFinished(actionEvent -> {

            Platform.runLater(this::burningSunMove);

        });

        timelySun.play();


        BoxBlur fourblur = new BoxBlur();
        fourblur.setIterations(1);
        fourblur.setHeight(0);
        fourblur.setWidth(0);

        groupFour.setEffect(fourblur);

        final GaussianBlur sunblur = new GaussianBlur();
        sun.setEffect(sunblur);

        final Reflection reflection = new Reflection();
        reflection.setBottomOpacity(0.04);
        reflection.setTopOpacity(0.08);
        reflection.setTopOffset(0);
        reflection.setFraction(0);

        first.setEffect(reflection);

        EventHandler<ActionEvent> startAudio = arg0 -> AudioMemory.getInstance().playTheIntro();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,startAudio),
                new KeyFrame(Duration.seconds(3.5),
                        new KeyValue(first.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(5.8),
                        new KeyValue(first.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(7.05),
                        new KeyValue(lightning.opacityProperty(), 0)),                  ///Lightning start!
                new KeyFrame(Duration.seconds(7.15),
                        new KeyValue(reflection.fractionProperty(), 0)),
                new KeyFrame(Duration.seconds(7.18),
                        new KeyValue(reflection.fractionProperty(), reflection.getFraction() + 0.7)),
                new KeyFrame(Duration.seconds(7.25),
                        new KeyValue(lightning.opacityProperty(), 0.6)),
                new KeyFrame(Duration.seconds(13.5),
                        new KeyValue(lightning.opacityProperty(), 0)),                  ///Lightning end!
                new KeyFrame(Duration.seconds(13.7),
                        new KeyValue(first.opacityProperty(), 0),
                        new KeyValue(sun.opacityProperty(), 0),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth()),
                        new KeyValue(sun.rotateProperty(), sun.getRotate()),
                        new KeyValue(sun.scaleYProperty(), sun.getScaleY()),
                        new KeyValue(sun.layoutYProperty(), sun.getLayoutY())),
                new KeyFrame(Duration.seconds(14.8),
                        new KeyValue(groupFour.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(15.1),
                        new KeyValue(groupFour.opacityProperty(), 1),
                        new KeyValue(sun.opacityProperty(), 0.24),
                        new KeyValue(sun.layoutXProperty(), sun.getLayoutX())),
                new KeyFrame(Duration.seconds(16.8),
                        new KeyValue(fourblur.widthProperty(),0)),
                new KeyFrame(Duration.seconds(17),
                        new KeyValue(fourblur.widthProperty(),13)),
                new KeyFrame(Duration.seconds(17.2),
                        new KeyValue(fourblur.widthProperty(),0)),
                new KeyFrame(Duration.seconds(24.2),
                        new KeyValue(groupFour.opacityProperty(), 0),
                        new KeyValue(sun.opacityProperty(), 0.5),
                        new KeyValue(sunTone.levelProperty(), 0)),
                new KeyFrame(Duration.seconds(26.2),
                        new KeyValue(startBlack.opacityProperty(),1),
                        new KeyValue(sunblur.radiusProperty(),0),
                        new KeyValue(memomaze.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(30),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(31),
                        new KeyValue(memomaze.opacityProperty(),1),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(32),
                        new KeyValue(loading.opacityProperty(),0),
                        new KeyValue(sun.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(33),
                        new KeyValue(gamePane.opacityProperty(),0),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(34),
                        new KeyValue(loading.opacityProperty(),0),
                        new KeyValue(startBlack.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(35),
                        new KeyValue(gamePane.opacityProperty(),1),
                        new KeyValue(sunTone.levelProperty(), 0.9),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(36),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(36.4),
                        new KeyValue(sun.fitWidthProperty(), sun.getFitWidth() + 80),
                        new KeyValue(sun.layoutXProperty(),sun.getLayoutX() - 8),
                        new KeyValue(sunblur.radiusProperty(), 50),
                        new KeyValue(sun.rotateProperty(),sun.getRotate() - 8),
                        new KeyValue(sun.scaleYProperty(),sun.getScaleY() + 0.5),
                        new KeyValue(sun.layoutYProperty(),sun.getLayoutY() + 130)),
                new KeyFrame(Duration.seconds(37),
                        new KeyValue(loading.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(38),
                        new KeyValue(memomaze.layoutYProperty(),memomaze.getLayoutY()),
                        new KeyValue(memomaze.opacityProperty(),1),
                        new KeyValue(loading.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.2),
                        new KeyValue(miniEasy.opacityProperty(),0),                 // Easy start!!!!
                        new KeyValue(easyFrame.opacityProperty(),0),
                        new KeyValue(japan.opacityProperty(),0),
                        new KeyValue(memomaze.layoutYProperty(),memomaze.getLayoutY() - 80),
                        new KeyValue(memomaze.opacityProperty(),0),
                        new KeyValue(kotoku.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.4),
                        new KeyValue(miniEasy.opacityProperty(),1),                 // Easy on!!!
                        new KeyValue(easyFrame.opacityProperty(),1),
                        new KeyValue(japan.opacityProperty(),japanStart),
                        new KeyValue(kotoku.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(40.4),
                        new KeyValue(miniMedium.opacityProperty(),0),               ////Medium start!!
                        new KeyValue(mediumFrame.opacityProperty(),0),
                        new KeyValue(jungle.opacityProperty(),0),
                        new KeyValue(tigerden.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.6),
                        new KeyValue(miniMedium.opacityProperty(),1),
                        new KeyValue(mediumFrame.opacityProperty(),1),
                        new KeyValue(jungle.opacityProperty(),jungleStart),
                        new KeyValue(tigerden.opacityProperty(),1)),       //////Medium on  0.426
                new KeyFrame(Duration.seconds(40.6),
                        new KeyValue(miniHard.opacityProperty(), 0),             ////Hard start!!!
                        new KeyValue(hardFrame.opacityProperty(), 0),
                        new KeyValue(redtree.opacityProperty(), 0),
                        new KeyValue(treeoflife.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(40.8),                              //////Hard on 0.494
                        new KeyValue(miniHard.opacityProperty(), 1),
                        new KeyValue(hardFrame.opacityProperty(), 1),
                        new KeyValue(redtree.opacityProperty(), redtreeStart),
                        new KeyValue(treeoflife.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(40.8),
                        new KeyValue(logAndReg.opacityProperty(), 0),
                        new KeyValue(sepiaTone.levelProperty(), 1)),
                new KeyFrame(Duration.seconds(40.9),
                        new KeyValue(logAndReg.opacityProperty(), 1),
                        new KeyValue(sepiaTone.levelProperty(), 0))
        );



        timeline.play();
        timeline.setOnFinished(actionEvent -> {


            timeline.stop();
            moveMenuBackground();
            //moveCamera();
            blacksun.setDisable(true);
            blacksun.setVisible(false);
            sun.setDisable(true);
            sun.setVisible(false);
            startBlack.setDisable(true);
            startBlack.setVisible(false);
            lightning.setDisable(true);
            lightning.setVisible(false);
            first.setDisable(true);
            first.setVisible(false);
            groupFour.setDisable(true);
            groupFour.setVisible(false);



            textLoggedIn.setVisible(true);
        });
    }


    private void moveMenuBackground() {


        Platform.runLater(() -> Effects.getInstance().setGlow(pergament));
        Platform.runLater(() -> Effects.getInstance().playGlow());

        Timeline backMover = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX())),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(dirt.scaleXProperty(), dirt.getScaleX() + 0.4)));

        redtree.getTransforms().add(rotateZ);
        jungle.getTransforms().add(jungleZ);

        Timeline redLine = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(rotateZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(rotateZ.angleProperty(), 4)));

        Timeline jungleLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(jungleZ.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(20),
                        new KeyValue(jungleZ.angleProperty(), -1.2)));

        jungleLine.setAutoReverse(true);
        jungleLine.setCycleCount(Timeline.INDEFINITE);
        jungleLine.play();

        redLine.setAutoReverse(true);
        redLine.setCycleCount(Timeline.INDEFINITE);
        redLine.play();
        backMover.setAutoReverse(true);
        backMover.setCycleCount(Timeline.INDEFINITE);
        backMover.play();
    }

    private void burningSunMove() {

        burningsun.setOpacity(0.3);
        burningSunLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(burningsun.layoutXProperty(), -260),
                        new KeyValue(burningsun.layoutYProperty(), -59)),
                new KeyFrame(Duration.minutes(1.5),
                        new KeyValue(burningsun.layoutYProperty(), -59 - 80)),
                new KeyFrame(Duration.minutes(3),
                        new KeyValue(burningsun.layoutXProperty(), -260 + 1470),
                        new KeyValue(burningsun.layoutYProperty(), -59))
        );

        burningSunLine.playFromStart();
        burningSunLine.setOnFinished(actionEvent -> {

            burningsun.setLayoutX(-260);
            burningsun.setLayoutY(-59);
            burningSunLine.play();
        });
    }

    public void playBuringSun() {burningSunMove();}

    public void moveCamera() {

        double transx = Menu.camera.getTranslateX();
        double transz = Menu.camera.getTranslateZ();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(Menu.camera.translateXProperty(), transx),
                        new KeyValue(Menu.camera.translateZProperty(), transz)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(Menu.camera.translateZProperty(), Menu.camera.getTranslateZ() + 5),
                        new KeyValue(Menu.camera.translateXProperty(), Menu.camera.getTranslateX() - 5)),
                new KeyFrame(Duration.seconds(10),
                        new KeyValue(Menu.camera.translateXProperty(), Menu.camera.getTranslateX() + 5),
                        new KeyValue(Menu.camera.translateZProperty(), Menu.camera.getTranslateZ() - 5)),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(Menu.camera.translateXProperty(), Menu.camera.getTranslateX() - 5),
                        new KeyValue(Menu.camera.translateZProperty(), Menu.camera.getTranslateZ() + 5))
        );

        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

    public void stopBurningSun() {


        burningSunLine.stop();

    }

    public void setGlow(@NotNull ImageView imageView) {

        Glow glow = new Glow();
        imageView.setEffect(glow);

        glowLine = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.levelProperty(), 0)),
                new KeyFrame(Duration.seconds(0.1),
                        new KeyValue(glow.levelProperty(), 0.07)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(glow.levelProperty(), 0.02)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(glow.levelProperty(), 0.05)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(glow.levelProperty(), 0.01)),
                new KeyFrame(Duration.seconds(1.1),
                        new KeyValue(glow.levelProperty(), 0.08)),
                new KeyFrame(Duration.seconds(1.2),
                        new KeyValue(glow.levelProperty(), 0.04)),
                new KeyFrame(Duration.seconds(1.4),
                        new KeyValue(glow.levelProperty(), 0.06)),
                new KeyFrame(Duration.seconds(1.9),
                        new KeyValue(glow.levelProperty(), 0.03))
        );

        glowLine.setAutoReverse(true);
        glowLine.setCycleCount(Timeline.INDEFINITE);
    }

    public void stopGlow() {

        glowLine.stop();
    }

    public void playGlow() {

        glowLine.play();
    }
}