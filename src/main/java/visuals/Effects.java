package visuals;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.ModeType;

public class Effects {
    private static Effects instance;
    private BackGroundMover easyMover;
    private BackGroundMover mediumMover;
    private BackGroundMover hardMover;
    private Effects(){}
    public static Effects getInstance(){

        if(instance == null) {
            instance = new Effects();
        }
        return instance;
    }

    public void rotateUp(Group group) {

        Timeline timelineUp = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(group.rotateProperty(),0),
                        new KeyValue(group.rotationAxisProperty(), Rotate.X_AXIS)
                ),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(group.rotateProperty(),90),
                        new KeyValue(group.rotationAxisProperty(),Rotate.X_AXIS))
        );

        timelineUp.play();
    }

    public void rotateDown(Group group) {

        Timeline timelineDown = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(group.rotateProperty(),90),
                        new KeyValue(group.rotationAxisProperty(),Rotate.X_AXIS)
                ),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(group.rotateProperty(),0),
                        new KeyValue(group.rotationAxisProperty(),Rotate.X_AXIS))
        );

        timelineDown.play();
    }

    public void backGroundIn(ImageView imageView) {

        final double start = imageView.getOpacity();

        Timeline timelineIN = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.opacityProperty(),start)),
                new KeyFrame(Duration.seconds(1
                ), new KeyValue(imageView.opacityProperty(),1)));

        timelineIN.play();
    }

    public void backGroundOut(ImageView imageView) {

        final double start = imageView.getOpacity();

        Timeline timelineOUT = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.opacityProperty(),start)),
                new KeyFrame(Duration.seconds(1.5
                ), new KeyValue(imageView.opacityProperty(),0)));

        timelineOUT.play();
    }

    public void backGroundBlurIn(ImageView imageView) {

        final GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(0);
        imageView.setEffect(gaussianBlur);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(gaussianBlur.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(6
                ), new KeyValue(gaussianBlur.radiusProperty(),10.58))
        );

        timeline.play();
    }


    public void easyGameZoomIn(PerspectiveCamera camera,AnchorPane startAnchor,ImageView imageView, Runnable callback ) {

        final double z = camera.getTranslateZ();
        final double fov = camera.getFieldOfView();
        final double x = camera.getTranslateX();
        final double y = camera.getTranslateY();

        Timeline timelineZoomEasy = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(camera.translateZProperty(), z),
                        new KeyValue(camera.fieldOfViewProperty(), fov),
                        new KeyValue(camera.translateXProperty(),x),
                        new KeyValue(camera.translateYProperty(),y)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(camera.translateZProperty(), z + 1000),
                        new KeyValue(camera.fieldOfViewProperty(), fov + 10),
                        new KeyValue(camera.translateXProperty(), x - 145.5),
                        new KeyValue(camera.translateYProperty(),y + 14.5)
                )
        );

        timelineZoomEasy.play();
        timelineZoomEasy.setOnFinished(actionEvent -> {

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(startAnchor.opacityProperty(), 1),
                            new KeyValue(camera.translateXProperty(),camera.getTranslateX()),
                            new KeyValue(camera.translateYProperty(),camera.getTranslateY()),
                            new KeyValue(camera.translateZProperty(),camera.getTranslateZ())),
                    new KeyFrame(Duration.seconds(0.0001),
                            new KeyValue(startAnchor.opacityProperty(),0),
                            new KeyValue(camera.translateXProperty(),x),
                            new KeyValue(camera.translateYProperty(),y),
                            new KeyValue(camera.translateZProperty(),z)
                    )
            );
            startAnchor.setMouseTransparent(true);
            timeline.play();
            callback.run();

            timeline.setOnFinished(actionEvent1 -> {

                backGroundBlurIn(imageView);
                easyMover = new BackGroundMover(imageView);
                easyMover.Animate();
                easyMover.play();

            });
        });
    }

    public void mediumGameZoomIn(PerspectiveCamera camera,AnchorPane startAnchor,ImageView imageView, Runnable callback ) {

        final double z = camera.getTranslateZ();
        final double fov = camera.getFieldOfView();
        final double x = camera.getTranslateX();
        final double y = camera.getTranslateY();

        Timeline timelineZoomEasy = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(camera.translateZProperty(), z),
                        new KeyValue(camera.fieldOfViewProperty(), fov),
                        new KeyValue(camera.translateXProperty(),x),
                        new KeyValue(camera.translateYProperty(),y)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(camera.translateZProperty(), z + 1000.9),
                        new KeyValue(camera.fieldOfViewProperty(), fov + 10),
                        new KeyValue(camera.translateXProperty(), x + 117),
                        new KeyValue(camera.translateYProperty(),y + 14.5)
                )
        );

        timelineZoomEasy.play();
        timelineZoomEasy.setOnFinished(actionEvent -> {

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(startAnchor.opacityProperty(), 1),
                            new KeyValue(camera.translateXProperty(),camera.getTranslateX()),
                            new KeyValue(camera.translateYProperty(),camera.getTranslateY()),
                            new KeyValue(camera.translateZProperty(),camera.getTranslateZ())),
                    new KeyFrame(Duration.seconds(0.0001),
                            new KeyValue(startAnchor.opacityProperty(),0),
                            new KeyValue(camera.translateXProperty(),x),
                            new KeyValue(camera.translateYProperty(),y),
                            new KeyValue(camera.translateZProperty(),z)
                    )
            );
            startAnchor.setMouseTransparent(true);
            timeline.play();
            callback.run();

            timeline.setOnFinished(actionEvent1 -> {


                backGroundBlurIn(imageView);
                mediumMover = new BackGroundMover(imageView);
                mediumMover.Animate();
                mediumMover.play();
            });
        });
    }

    public void hardGameZoomIn(PerspectiveCamera camera,AnchorPane startAnchor,ImageView imageView, Runnable callback ) {

        final double z = camera.getTranslateZ();
        final double fov = camera.getFieldOfView();
        final double x = camera.getTranslateX();
        final double y = camera.getTranslateY();

        Timeline timelineZoomEasy = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(camera.translateZProperty(), z),
                        new KeyValue(camera.fieldOfViewProperty(), fov),
                        new KeyValue(camera.translateXProperty(),x),
                        new KeyValue(camera.translateYProperty(),y)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(camera.translateZProperty(), z + 1000.7),
                        new KeyValue(camera.fieldOfViewProperty(), fov + 10),
                        new KeyValue(camera.translateXProperty(), x + 380),
                        new KeyValue(camera.translateYProperty(),y + 14.5)
                )
        );

        timelineZoomEasy.play();
        timelineZoomEasy.setOnFinished(actionEvent -> {

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(startAnchor.opacityProperty(), 1),
                            new KeyValue(camera.translateXProperty(),camera.getTranslateX()),
                            new KeyValue(camera.translateYProperty(),camera.getTranslateY()),
                            new KeyValue(camera.translateZProperty(),camera.getTranslateZ())),
                    new KeyFrame(Duration.seconds(0.01),
                            new KeyValue(startAnchor.opacityProperty(),0),
                            new KeyValue(camera.translateXProperty(),x),
                            new KeyValue(camera.translateYProperty(),y),
                            new KeyValue(camera.translateZProperty(),z)
                    )
            );
            startAnchor.setMouseTransparent(true);
            timeline.play();
            callback.run();


            timeline.setOnFinished(actionEvent1 -> {

                backGroundBlurIn(imageView);
                hardMover = new BackGroundMover(imageView);
                hardMover.Animate();
                hardMover.play();
            });
        });
    }

    public void shutDownMover(ModeType mode) {

        switch (mode) {

            case EASY -> {
                easyMover.stop();
                easyMover = null;
            }
            case MEDIUM -> {
                mediumMover.stop();
                mediumMover = null;
            }
            case HARD -> {
                hardMover.stop();
                hardMover = null;
            }
        }
    }
}
