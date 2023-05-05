/**
 * The InternationalizationController class controls the internationalization functionality of the application.
 * It handles the switching of languages and the loading of the appropriate language resources.
 * It also sets up the UI elements related to the internationalization feature.
 */
package visuals.internationalization;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.ModeType;
import visuals.Navigaattori;
import visuals.audio.AudioMemory;
import visuals.effects.commonHovers.Hovers;
import visuals.effects.menuEffects.IMenuLayoutEffects;
import visuals.effects.menuEffects.MenuLayoutEffects;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;
public class InternationalizationController {
    @FXML
    public Button buttonReturn;
    @FXML
    public AnchorPane anchorLbs;
    @FXML
    public Button en;
    @FXML
    public Button fin;
    @FXML
    public Label titleInfo, welcomeText, labelInfo,stepText;
    @FXML
    private ImageView previousButton;
    @FXML
    private ImageView nextButton;
    @FXML
    private ImageView stepImage;
    @FXML
    private Rectangle glowingBorder;
    @FXML AnchorPane infoBlack;

    private ResourceBundle bundle;
    private final IMenuLayoutEffects menuLayoutEffects = new MenuLayoutEffects();
    private final Hovers hovers = new Hovers(menuLayoutEffects);
    private int currentStep = 0;
    private List<Image> stepImages;


    /**
     * Initializes the controller and sets up the UI elements.
     */
    @FXML
    private void initialize(){

        Locale.setDefault(new Locale("en")); // set default language
        changeLanguage(Locale.getDefault()); // load default language resources
        Platform.runLater(() -> AudioMemory.getInstance().playSong(ModeType.INFO));

        stepImages = new ArrayList<>();
        stepImages.add(loadImage("pictures/images/step1.png"));
        stepImages.add(loadImage("pictures/images/step2.png"));
        stepImages.add(loadImage("pictures/images/step3.png"));
        stepImages.add(loadImage("pictures/images/step4.png"));
        stepImages.add(loadImage("pictures/images/step5.png"));
        stepImages.add(loadImage("pictures/images/step6.png"));


        createEffectsAndAnimation();

        updateInfo();
        updateNavigationButtons();
        Font.loadFont(Objects.requireNonNull(getClass().getClassLoader().getResource("fonts/VCR_OSD_MONO_1.001.ttf")).toExternalForm(), 14);
        styleButton(buttonReturn);
        String imageurl = Objects.requireNonNull(getClass().getClassLoader().getResource("images/infoBackground.jpeg")).toExternalForm();
        anchorLbs.setStyle("-fx-background-image: url('" + imageurl + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        for (Label label : Arrays.asList(titleInfo, labelInfo, stepText)) {
            styleLabel(label);
        }
        welcomeText.setStyle("-fx-font: 24px; -fx-text-fill: white;");

        blackOff();
    }

    /**
     * Turns off the black screen.
     */
    private void blackOff() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(infoBlack.opacityProperty(),1)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(infoBlack.opacityProperty(),0))
        );

        timeline.play();
    }

    /**
     * Styles a label with a white font and font size of 18.
     * @param l The label to style.
     */
    private void styleLabel(Label l ){
        if(l != null){
            l.setFont(Font.font(18));
            l.setStyle("-fx-text-fill: white;");

        }
    }

    /**
     * Creates the effects and animations used in the UI.
     */
    private void createEffectsAndAnimation() {
        // Create the Glow effect
        StrokeTransition strokeTransition = new StrokeTransition(Duration.seconds(2), glowingBorder, Color.WHITE, Color.CYAN);
        strokeTransition.setAutoReverse(true);
        strokeTransition.setCycleCount(StrokeTransition.INDEFINITE);
        strokeTransition.play();
    }

    /**
     * Loads an image from a file path.
     * @param path The file path of the image.
     * @return The loaded image.
     */

    private Image loadImage(String path) {
        URL resource = getClass().getResource("/" + path);
        if (resource == null) {
            System.err.println("Resource not found: " + path);
            return null;
        }
        return new Image(resource.toExternalForm());
    }

    /**
     * Styles a button with a dark purple background and white text.
     * @param b The button to style.
     */
    private void styleButton(Button b) {
        // get hex for dark purple and light purple and save them to variables
        String darkPurple = "#800080";
        String lightPurple = "#cc00cc";
        b.setFont(Font.font("VCR OSD Mono", 14));
        b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: " + lightPurple + " ; -fx-text-fill: white;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;"));
    }

    /**
     * Updates the text and image of the UI elements related to the current step.
     */
    private void updateInfo() {
        stepText.setText(bundle.getString("stepText"));
        switch (currentStep) {
            case 0 -> labelInfo.setText(bundle.getString("stepOne"));
            case 1 -> labelInfo.setText(bundle.getString("stepTwo"));
            case 2 -> labelInfo.setText(bundle.getString("stepThree"));
            case 3 -> labelInfo.setText(bundle.getString("stepFour"));
            case 4 -> labelInfo.setText(bundle.getString("stepFive"));
            case 5 -> labelInfo.setText(bundle.getString("stepSix"));
            default -> labelInfo.setText("");
        }

        stepImage.setImage(stepImages.get(currentStep));

    }

    /**
     * Sets the buttonReturn to be unclickable and fades in a black screen with a song.
     * After the song finishes, it changes the scene to the main menu.
     * @param event The button press event.
     */
    @FXML
    public void setButtonReturn(ActionEvent event) {

        buttonReturn.setMouseTransparent(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(infoBlack.opacityProperty(),0)),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(infoBlack.opacityProperty(),1))
        );

        timeline.play();

        timeline.setOnFinished(actionEvent -> {

            Platform.runLater(() -> AudioMemory.getInstance().stopSong(ModeType.INFO));
            try {
                Navigaattori.getInstance().changeScene(ModeType.MENU);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    /**
     Changes the language of the UI to the specified locale and updates the UI elements accordingly.
     @param locale The locale to set the UI language to.
     */
    private void changeLanguage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle", locale);

        for (Label label : Arrays.asList(titleInfo, welcomeText)) {
            if (label != null) {
                String key = label.getId();
                String text = bundle.getString(key);
                label.setText(text);
            }
        }
        this.bundle = bundle;
        String text = bundle.getString("mainMenu");
        buttonReturn.setText(text);

        // Only update the info labels and image if the steps are initialized
        if (stepImages != null && !stepImages.isEmpty()) {
            updateInfo();
        }
    }

    /**
     * Changes the language of the UI to English.
     */

    @FXML
    public void setButtonEn() {
        changeLanguage(new Locale("en"));
        JavaFXInternationalization.setLocale(new Locale("en"));
        anchorLbs.requestLayout();
    }

    /**
     * Changes the language of the UI to Finnish.
     */

    @FXML
    public void setButtonFin() {
        changeLanguage(new Locale("fi"));
        JavaFXInternationalization.setLocale(new Locale("fi"));
        anchorLbs.requestLayout();
    }

    /**
     * Changes the language of the UI to Swedish.
     */
    @FXML
    public void setButtonSwe() {
        changeLanguage(new Locale("swe"));
        JavaFXInternationalization.setLocale(new Locale("swe"));
        anchorLbs.requestLayout();
    }

    /**
     * Changes the language of the UI to Latvian.
     */
    @FXML
    public void setButtonLat() {
        changeLanguage(new Locale("lat"));
        JavaFXInternationalization.setLocale(new Locale("lat"));
        anchorLbs.requestLayout();
    }


    /**
     * Handles the mouse hover event on an UI element.
     * @param event The mouse event.
     */
    @FXML
    public void hoverOn(javafx.scene.input.MouseEvent event) {

        hovers.commonHoverOn(event);
    }
    /**
     * Handles the mouse hover off event on an UI element.
     * @param event The mouse event.
     */
    @FXML
    public void hoverOff(javafx.scene.input.MouseEvent event) {

        hovers.commonHoverOff(event);
    }

    /**
     * Updates the visibility of the previous and next buttons based on the current step.
     */
    private void updateNavigationButtons() {
        previousButton.setVisible(currentStep > 0);
        nextButton.setVisible(currentStep < getTotalSteps() - 1);
    }

    /**
     * Gets the total number of steps in the UI.
     * @return The total number of steps.
     */
    private int getTotalSteps() {
        return 6;
    }

    /**
     * Handles the event of clicking the previous button.
     */
    @FXML
    private void handlePreviousButton() {

        if(currentStep > 0 ){
            currentStep --;
            updateInfo();
            updateNavigationButtons();
        }
    }
    /**
     * Handles the event of clicking the next button.
     */
    @FXML
    private void handleNextButton() {
        if(currentStep < getTotalSteps() -1 ){
            currentStep ++;
            updateInfo();
            updateNavigationButtons();
        }
    }
}