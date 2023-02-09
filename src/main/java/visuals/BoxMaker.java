package visuals;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoxMaker {
    private Box frontFace, backFace, rightFace, leftFace, topFace, bottomFace;
    private PhongMaterial material, imageMaterial;
    private Group boxGroup;
    private PointLight light;
    private final double width;
    private final double height;
    private final double depth;
    private final int typeID;
    private final int id;
    private final Gui gui;
    private RotateTransition rotation;

    public BoxMaker
            (
            double width, double height,
            double depth, double translateX,
            double translateY, double translateZ,
            String imageUrl, int typeID, Gui gui, int id
            ) throws FileNotFoundException {

        this.id = id;
        this.gui = gui;
        this.typeID = typeID;
        this.width = width;
        this.height = height;
        this.depth = depth;
        createMaterials(imageUrl);
        createFaces();
        createLight();
        createGroup();
        gui.addToCubeList(this);
        //System.out.println("image path: " + imageUrl + " type id: " + typeID + "       boxxxxxxxxxxxxxxxx" + " id: " + this.id);
    }
    private void createMaterials(String imageUrl) throws FileNotFoundException {

        Image image = new Image(new FileInputStream(imageUrl));
        material = new PhongMaterial();
        imageMaterial = new PhongMaterial();
        material.setDiffuseColor(Color.WHITE);
        imageMaterial.setDiffuseMap(image);
    }
    public Integer getID() {return this.id;}
    private void createFaces() {

        frontFace = new Box(width, height, depth);
        frontFace.setMaterial(material);
        frontFace.setRotationAxis(Rotate.Z_AXIS);

        topFace = new Box(width, height, depth);
        topFace.setMaterial(imageMaterial);
        topFace.setRotationAxis(Rotate.Z_AXIS);

        backFace = new Box(width, height, depth);
        backFace.setMaterial(imageMaterial);
        backFace.setRotationAxis(Rotate.Z_AXIS);

        rightFace = new Box(width, height, depth);
        rightFace.setMaterial(material);
        rightFace.setRotationAxis(Rotate.Z_AXIS);

        leftFace = new Box(width, height, depth);
        leftFace.setMaterial(material);
        leftFace.setRotationAxis(Rotate.Z_AXIS);

        bottomFace = new Box(width, height, depth);
        bottomFace.setMaterial(imageMaterial);
        bottomFace.setRotationAxis(Rotate.Z_AXIS);
    }
    private void rotateBox() {

        Effects.getInstance().rotateUp(boxGroup,material);
        sendId();
    }
    public void sendId() {gui.sendIdToEngine(this.id);}
    private void createLight() {

        light = new PointLight();
        light.setTranslateX(0);
        light.setTranslateY(0);
        light.setTranslateZ(20);
        light.setColor(Color.WHITE);
    }
    private void createGroup() {

        boxGroup = new Group();
        boxGroup.getChildren().addAll(topFace,frontFace,backFace,bottomFace,rightFace,leftFace);
        boxGroup.setOnMouseClicked(mouseEvent -> rotateBox());
    }
    public void resetImage() {Effects.getInstance().rotateDown(boxGroup,material);}
    public Group getBox() {return boxGroup;}

}
