package visuals;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import java.io.FileNotFoundException;

public class BoxMaker {
    private Box backFace, topFace, rightFace, leftFace, frontFace, bottomFace;
    private PhongMaterial material1,material2,material3,material4,material5,material6;
    private Group boxGroup;
    private PointLight light;
    private final double width;
    private final double height;
    private final int id;
    private final Gui gui;

    public BoxMaker(double width, double height, Image findImage, Image backImage, Image behindImage, Gui gui, int id){

        this.id = id;
        this.gui = gui;
        this.width = width;
        this.height = height;
        createMaterials(findImage,backImage,behindImage);
        createFaces();
        createLight();
        createGroup();
        gui.addToCubeList(this);
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

        leftFace = new Box(width, height, 0);
        leftFace.setMaterial(material5);
        leftFace.setTranslateX(width/-2);
        leftFace.setTranslateZ(width/2);
        leftFace.setRotationAxis(Rotate.Y_AXIS);
        leftFace.setRotate(90);

        backFace = new Box(width, height, 0);
        backFace.setMaterial(material1);
        backFace.setTranslateZ(width);
        backFace.setRotationAxis(Rotate.Z_AXIS);

        frontFace = new Box(width, height, 0);
        frontFace.setMaterial(material2);
        frontFace.setTranslateZ(0);
        frontFace.setTranslateY(0);
        frontFace.setRotationAxis(Rotate.Z_AXIS);

        topFace = new Box(width, height, 0);
        topFace.setMaterial(material3);
        topFace.setTranslateX(0);
        topFace.setTranslateY(width/-2);
        topFace.setTranslateZ(width/2);
        topFace.setRotationAxis(Rotate.X_AXIS);
        topFace.setRotate(-90);

        bottomFace = new Box(width, height, 0);
        bottomFace.setMaterial(material6);
        bottomFace.setTranslateX(0);
        bottomFace.setTranslateY(width/2);
        bottomFace.setTranslateZ(width/2);
        bottomFace.setRotate(-90);
        bottomFace.setRotationAxis(Rotate.X_AXIS);

    }


    private void createGroup() {

        boxGroup = new Group();
        Gui.camera.setFieldOfView(1);
        boxGroup.getChildren().addAll(backFace,bottomFace,topFace,frontFace,rightFace,leftFace);
        boxGroup.setOnMouseClicked(mouseEvent -> rotateBox());
    }
    private void rotateBox() {

        Platform.runLater(() -> Effects.getInstance().rotateUp(boxGroup));
        sendId();
    }
    private void sendId() {gui.sendIdToEngine(this.id);}
    private void createLight() {

        light = new PointLight();
        light.setTranslateX(0);
        light.setTranslateY(0);
        light.setTranslateZ(20);
        light.setColor(Color.WHITE);
    }

    public void resetImage() {Effects.getInstance().rotateDown(boxGroup);}
    public Group getBox() {return boxGroup;}
}

