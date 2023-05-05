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
import visuals.gameModes.FXIGameController;


/**

 The BoxMaker class represents a 3D box made up of six faces. Each face is a

 rectangular 3D object that has a PhongMaterial with an image applied to it.

 This class creates the box and sets the PhongMaterial for each face.

 It also provides methods to toggle the isActive field and get the active state of the box.
 */
public class BoxMaker {
    private Box backFace, topFace, rightFace, leftFace, frontFace, bottomFace;
    private PhongMaterial material1,material2,material3,material4,material5,material6;
    private Group boxGroup;
    private final double width;
    private final double height;
    private final int id;
    private final FXIGameController gameController;
    private final DoubleProperty rotateValueUp = new SimpleDoubleProperty(0);
    private final ObjectProperty<Point3D> rotationAxisUp = new SimpleObjectProperty<>(Rotate.X_AXIS);
    private final DoubleProperty rotateValueDown = new SimpleDoubleProperty(90);
    private final ObjectProperty<Point3D> rotationAxisDown = new SimpleObjectProperty<>(Rotate.X_AXIS);

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

    private Boolean isActive = false;

    /**
     * Creates a new instance of BoxMaker. The 3D box will have a width and height
     * that are passed as parameters. The findImage, backImage, and behindImage
     * parameters are image files that will be used to set the PhongMaterial of
     * each face. The gui parameter is an instance of the FXIGameController class.
     * The id parameter is an int that identifies the box.
     *
     * @param width The width of the box.
     * @param height The height of the box.
     * @param findImage An image file for the face that is on top of the box.
     * @param backImage An image file for the back and side faces of the box.
     * @param behindImage An image file for the face that is on the bottom of the box.
     * @param gameController An instance of the FXIGameController class.
     * @param id An int that identifies the box.
     */
    public BoxMaker(double width, double height, Image findImage, Image backImage, Image behindImage, FXIGameController gameController, int id){

        this.id = id;
        this.gameController = gameController;
        this.width = width;
        this.height = height;
        createMaterials(findImage,backImage,behindImage);
        createFaces();
        createGroup();
        gameController.addToCubeList(this);
    }

    /**
     * Toggles the state of the isActive field.
     */
    public void setActive() {isActive = !isActive;}

    /**
     * Returns the value of the isActive field.
     *
     * @return true if the isActive field is true, false otherwise.
     */
    public Boolean getActiveState() {return isActive;}

    /**
     * Creates the PhongMaterial for each face.
     *
     * @param findImage An image file for the face that is on top of the box.
     * @param backImage An image file for the back and side faces of the box.
     * @param behindImage An image file for the face that is on the bottom of the box.
     */
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

    /**

     Creates the four faces of the box.
     */
    private void createFaces() {

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


    /**

     The createGroup method creates a new Group object containing the four faces of a box.

     It sets an event listener to the group, such that when the group is clicked, the rotateBox method is called.
     */
    private void createGroup() {

        boxGroup = new Group();
        boxGroup.getChildren().addAll(backFace,bottomFace,topFace,frontFace);
        boxGroup.setOnMouseClicked(mouseEvent -> rotateBox());

    }

    /**

     The rotateBox method sends the ID of the box to the server and rotates the box upwards.

     It does this by calling the sendId method to send the ID to the server and then calling the rotateUp method to rotate the box.
     */
    private void rotateBox() {

        sendId();
        Platform.runLater(() -> rotateUp(boxGroup));

    }


    /**

     The sendId method sends the ID of the box to the game engine through the game controller.
     */
    private void sendId() {
        gameController.sendIdToEngine(this.id);}

    /**

     The resetImage method rotates the box downwards.
     This method is run on the JavaFX Application Thread.
     */
    public void resetImage() {Platform.runLater(() -> rotateDown(boxGroup));}

    /**

     The getBox method returns the Group object that contains the four faces of the box.
     @return The Group object that contains the four faces of the box.
     */
    public Group getBox() {return boxGroup;}



    /**

     The rotateUp method rotates the given Group object upwards using a Timeline animation.
     It binds the Group's rotateProperty and rotationAxisProperty to the rotateValueUp and rotationAxisUp properties, respectively.
     It then plays the Timeline animation from the start.
     @param group The Group object to rotate upwards.
     */
    public void rotateUp(Group group) {
        group.rotateProperty().bind(rotateValueUp);
        group.rotationAxisProperty().bind(rotationAxisUp);
        timelineUp.playFromStart();
    }

    /**

     The rotateDown method rotates the given Group object downwards using a Timeline animation.
     It binds the Group's rotateProperty and rotationAxisProperty to the rotateValueDown and rotationAxisDown properties, respectively.
     It then plays the Timeline animation from the start.
     @param group The Group object to rotate downwards.
     */
    public void rotateDown(Group group) {
        group.rotateProperty().bind(rotateValueDown);
        group.rotationAxisProperty().bind(rotationAxisDown);
        timelineDown.playFromStart();
    }
}

