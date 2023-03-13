package visuals.cubeFactories;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import visuals.menu.Gui;

public class BoxMaker {
    private Box backFace, topFace, rightFace, leftFace, frontFace, bottomFace;
    private PhongMaterial material1,material2,material3,material4,material5,material6;
    private Group boxGroup;
    private final double width;
    private final double height;
    private final int id;
    private final Gui gui;
    private final DoubleProperty rotateValueUp = new SimpleDoubleProperty(0);
    private final ObjectProperty<Point3D> rotationAxisUp = new SimpleObjectProperty<>(Rotate.X_AXIS);
    private final DoubleProperty rotateValueDown = new SimpleDoubleProperty(90);
    private final ObjectProperty<Point3D> rotationAxisDown = new SimpleObjectProperty<>(Rotate.X_AXIS);

    private Boolean isActive = false;

    public BoxMaker(double width, double height, Image findImage, Image backImage, Image behindImage, Gui gui, int id){

        this.id = id;
        this.gui = gui;
        this.width = width;
        this.height = height;
        createMaterials(findImage,backImage,behindImage);
        createFaces();
        createGroup();
        gui.addToCubeList(this);
    }

    public Integer getCubeId() {

        return this.id;
    }

    public void setActive() {

        isActive = !isActive;

    }

    public Boolean getActiveState() {

        return isActive;
    }
    private void createMaterials(Image findImage, Image backImage, Image behindImage){

        material1 = new PhongMaterial();
        material1.setDiffuseMap(backImage);
        material2 = new PhongMaterial();
        material2.setDiffuseMap(backImage);
        material3 = new PhongMaterial();
        material3.setDiffuseMap(findImage);
        material4 = new PhongMaterial();
        material4.setDiffuseMap(backImage);
        material5 = new PhongMaterial();
        material5.setDiffuseMap(backImage);
        material6 = new PhongMaterial();
        material6.setDiffuseMap(behindImage);
    }
    private void createFaces() {

        rightFace = new Box(width, height, 0);
        rightFace.setMaterial(material4);
        rightFace.setTranslateX(width/2);
        rightFace.setTranslateZ(width/2);
        rightFace.setRotationAxis(Rotate.Y_AXIS);
        rightFace.setRotate(90);
        rightFace.setCullFace(CullFace.BACK);

        leftFace = new Box(width, height, 0);
        leftFace.setMaterial(material5);
        leftFace.setTranslateX(width/-2);
        leftFace.setTranslateZ(width/2);
        leftFace.setRotationAxis(Rotate.Y_AXIS);
        leftFace.setRotate(90);
        leftFace.setCullFace(CullFace.BACK);


        backFace = new Box(width, height, 0);
        backFace.setMaterial(material1);
        backFace.setTranslateZ(width);
        backFace.setRotationAxis(Rotate.Z_AXIS);
        backFace.setCullFace(CullFace.BACK);

        frontFace = new Box(width, height, 0);
        frontFace.setMaterial(material2);
        frontFace.setTranslateZ(0);
        frontFace.setTranslateY(0);
        frontFace.setRotationAxis(Rotate.Z_AXIS);
        frontFace.setCullFace(CullFace.BACK);


        topFace = new Box(width, height, 0);
        topFace.setMaterial(material3);
        topFace.setTranslateX(0);
        topFace.setTranslateY(width/-2);
        topFace.setTranslateZ(width/2);
        topFace.setRotationAxis(Rotate.X_AXIS);
        topFace.setRotate(-90);
        topFace.setCullFace(CullFace.BACK);

        bottomFace = new Box(width, height, 0);
        bottomFace.setMaterial(material6);
        bottomFace.setTranslateX(0);
        bottomFace.setTranslateY(width/2);
        bottomFace.setTranslateZ(width/2);
        bottomFace.setRotate(-90);
        bottomFace.setRotationAxis(Rotate.X_AXIS);
        bottomFace.setCullFace(CullFace.BACK);

    }


    private void createGroup() {
        boxGroup = new Group();
        boxGroup.getChildren().addAll(backFace,bottomFace,topFace,frontFace,rightFace,leftFace);
        boxGroup.setOnMouseClicked(mouseEvent -> rotateBox());
    }
    private void rotateBox() {

        Platform.runLater(() -> rotateUp(boxGroup));
        sendId();
    }
    private void sendId() {gui.sendIdToEngine(this.id);}
    public void resetImage() {Platform.runLater(() -> rotateDown(boxGroup));}
    public Group getBox() {return boxGroup;}

    private final Timeline timelineUp = new Timeline(
            new KeyFrame(Duration.ZERO,
                    new KeyValue(rotateValueUp, 0),
                    new KeyValue(rotationAxisUp, Rotate.X_AXIS)
            ),
            new KeyFrame(Duration.seconds(0.6),
                    new KeyValue(rotateValueUp, 90))
    );

    private final Timeline timelineDown = new Timeline(
            new KeyFrame(Duration.ZERO,
                    new KeyValue(rotateValueDown,90),
                    new KeyValue(rotationAxisDown,Rotate.X_AXIS)
            ),
            new KeyFrame(Duration.seconds(0.6),
                    new KeyValue(rotateValueDown,0))
    );

    public void rotateUp(Group group) {
        group.rotateProperty().bind(rotateValueUp);
        group.rotationAxisProperty().bind(rotationAxisUp);
        timelineUp.playFromStart();
    }

    public void rotateDown(Group group) {
        group.rotateProperty().bind(rotateValueDown);
        group.rotationAxisProperty().bind(rotationAxisDown);
        timelineDown.playFromStart();
    }
}

