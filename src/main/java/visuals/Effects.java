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

        Timeline timelineBackGround = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.layoutXProperty(),0),
                        new KeyValue(imageView.layoutYProperty(),0)),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(imageView.layoutXProperty(),10),
                        new KeyValue(imageView.layoutYProperty(),3)),
                new KeyFrame(Duration.seconds(8),
                        new KeyValue(imageView.layoutXProperty(),-10),
                        new KeyValue(imageView.layoutYProperty(),-3)));

        timelineBackGround.setAutoReverse(true);
        timelineBackGround.setCycleCount(Timeline.INDEFINITE);
        timelineBackGround.play();
    }
}
