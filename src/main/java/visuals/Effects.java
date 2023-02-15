package visuals;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.PerspectiveCamera;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ModeType;

public class Effects {
    private static Effects instance;
    private final Timeline timelineBlur = new Timeline();
    private final Timeline timelineIN = new Timeline();
    private final Timeline timelineOUT = new Timeline();
    private final GaussianBlur gaussianBlur = new GaussianBlur();
    private ImageView inView = new ImageView();
    private ImageView outView = new ImageView();
    private BackGroundMover backGroundMover = new BackGroundMover();

    public Effects() {
        initializeTimelines();
    }

    private void initializeTimelines() {

        timelineBlur.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(gaussianBlur.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(6), new KeyValue(gaussianBlur.radiusProperty(), 10.58))
        );

        timelineIN.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(inView.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(inView.opacityProperty(), 1))
        );

        timelineOUT.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(outView.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(outView.opacityProperty(), 0))
        );

    }
    public static Effects getInstance(){

        if(instance == null) {
            instance = new Effects();
        }
        return instance;
    }

    public void backGroundIn(ImageView imageView) {

        timelineOUT.stop();
        inView = imageView;
        timelineIN.play();
    }

    public void backGroundOut(ImageView imageView) {

        timelineOUT.stop();
        outView = imageView;
        timelineOUT.play();
    }

    public void backGroundBlurIn(ImageView imageView) {

        gaussianBlur.setRadius(0);
        imageView.setEffect(gaussianBlur);
        timelineBlur.stop();
        timelineBlur.setCycleCount(1);
        timelineBlur.play();
    }


    public void shutDownMover() {

        if(backGroundMover != null) {

            backGroundMover.stop();
            backGroundMover = null;
        }
    }

    public void gameZoomOut(Pane pane, GridPane gridPane, PerspectiveCamera camera, AnchorPane startAnchor, ImageView imageView, double zOffset, double fovOffset, double xOffset, double yOffset) {

        Platform.runLater(() -> backGroundMover.stop());
        Platform.runLater(() -> backGroundMover.returnToPositionZero());

        Timeline blurOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(gridPane.opacityProperty(),gridPane.getOpacity()),
                        new KeyValue(gaussianBlur.radiusProperty(), gaussianBlur.getRadius())),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(gaussianBlur.radiusProperty(),0),
                        new KeyValue(gridPane.opacityProperty(),0))
        );

        blurOut.play();

        blurOut.setOnFinished(actionEvent -> {

            gridPane.setVisible(false);
            camera.setTranslateZ(zOffset);
            camera.setFieldOfView(fovOffset);
            camera.setTranslateX(xOffset);
            camera.setTranslateY(yOffset);
            imageView.setVisible(false);
            pane.setVisible(true);
            startAnchor.setOpacity(1);

            Timeline timelineZoomOut = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(camera.translateZProperty(), camera.getTranslateZ()),
                            new KeyValue(camera.fieldOfViewProperty(), camera.getFieldOfView()),
                            new KeyValue(camera.translateXProperty(), camera.getTranslateX()),
                            new KeyValue(camera.translateYProperty(), camera.getTranslateY())
                    ),
                    new KeyFrame(Duration.seconds(2),
                            new KeyValue(camera.translateZProperty(), 0),
                            new KeyValue(camera.fieldOfViewProperty(), 25),
                            new KeyValue(camera.translateXProperty(), 0),
                            new KeyValue(camera.translateYProperty(), 0)
                    )
            );

            timelineZoomOut.play();

            timelineZoomOut.setOnFinished(actionEvent1 -> {

                startAnchor.setMouseTransparent(false);
            });
        });
    }

    public void gameZoomIn(PerspectiveCamera camera, AnchorPane startAnchor, ImageView imageView,  double zOffset, double fovOffset, double xOffset, double yOffset, Runnable callback) {
        final double z = camera.getTranslateZ();
        final double fov = camera.getFieldOfView();
        final double x = camera.getTranslateX();
        final double y = camera.getTranslateY();


        Timeline timelineZoom = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(camera.translateZProperty(), z),
                        new KeyValue(camera.fieldOfViewProperty(), fov),
                        new KeyValue(camera.translateXProperty(), x),
                        new KeyValue(camera.translateYProperty(), y)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(camera.translateZProperty(), z + zOffset),
                        new KeyValue(camera.fieldOfViewProperty(), fov + fovOffset),
                        new KeyValue(camera.translateXProperty(), x + xOffset),
                        new KeyValue(camera.translateYProperty(), y + yOffset)
                )
        );

        timelineZoom.play();
        timelineZoom.setOnFinished(actionEvent -> {
            System.out.println(z + zOffset + " in");
            System.out.println(fov + fovOffset + " in");
            System.out.println(x + xOffset + " in");
            System.out.println(y + yOffset + " in");
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(startAnchor.opacityProperty(), 1),
                            new KeyValue(camera.translateXProperty(), camera.getTranslateX()),
                            new KeyValue(camera.translateYProperty(), camera.getTranslateY()),
                            new KeyValue(camera.translateZProperty(), camera.getTranslateZ())
                    ),
                    new KeyFrame(Duration.seconds(0.0001),
                            new KeyValue(startAnchor.opacityProperty(), 1),
                            new KeyValue(camera.translateXProperty(), x),
                            new KeyValue(camera.translateYProperty(), y),
                            new KeyValue(camera.translateZProperty(), z)
                    )
            );
            startAnchor.setMouseTransparent(true);
            timeline.play();
            callback.run();
            timeline.setOnFinished(actionEvent1 -> {

                backGroundBlurIn(imageView);
                backGroundMover.animate(imageView);
                Platform.runLater(() -> backGroundMover.play());
            });
        });
    }
}
