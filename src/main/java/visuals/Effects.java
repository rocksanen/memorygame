package visuals;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Effects {
    private static Effects instance;
    private Effects(){};
    public static Effects getInstance(){

        if(instance == null) {
            instance = new Effects();
        }
        return instance;
    }

    public void rotateUp(Group group, PhongMaterial material) {

        Timeline timelineUp = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(group.rotateProperty(),0),
                        new KeyValue(group.rotationAxisProperty(), Rotate.X_AXIS),
                        new KeyValue(material.diffuseColorProperty(), Color.color(1,1,1,1))),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(group.rotateProperty(),90),
                        new KeyValue(group.rotationAxisProperty(),Rotate.X_AXIS),
                        new KeyValue(material.diffuseColorProperty(),Color.color(0.3,0.3,0.3,0.3)))
        );

        timelineUp.play();
    }

    public void rotateDown(Group group, PhongMaterial material) {

        Timeline timelineDown = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(group.rotateProperty(),90),
                        new KeyValue(group.rotationAxisProperty(),Rotate.X_AXIS),
                        new KeyValue(material.diffuseColorProperty(),Color.color(0.3,0.3,0.3,0.3))),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(group.rotateProperty(),0),
                        new KeyValue(group.rotationAxisProperty(),Rotate.X_AXIS),
                        new KeyValue(material.diffuseColorProperty(),Color.color(1,1,1,1)))
        );

        timelineDown.play();
    }
    public void moveBackGround(ImageView imageView) {

        double x = imageView.getLayoutX();
        double y = imageView.getLayoutY();

        Timeline timelineBackGround = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.layoutXProperty(),x),
                        new KeyValue(imageView.layoutYProperty(),y)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(imageView.layoutXProperty(),x + 10),
                        new KeyValue(imageView.layoutYProperty(),y + 3)),
                new KeyFrame(Duration.seconds(9),
                        new KeyValue(imageView.layoutXProperty(),x + 15),
                        new KeyValue(imageView.layoutYProperty(),y + 6)));

        timelineBackGround.setAutoReverse(true);
        timelineBackGround.setCycleCount(Timeline.INDEFINITE);
        timelineBackGround.play();
    }

    public void backGroundIn(ImageView imageView) {

        double start = imageView.getOpacity();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.opacityProperty(),start)),
                new KeyFrame(Duration.seconds(0.7
                ), new KeyValue(imageView.opacityProperty(),1)));

        timeline.play();
    }

    public void backGroundOut(ImageView imageView) {

        double start = imageView.getOpacity();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.opacityProperty(),start)),
                new KeyFrame(Duration.seconds(0.7
                ), new KeyValue(imageView.opacityProperty(),0)));

        timeline.play();
    }
}
