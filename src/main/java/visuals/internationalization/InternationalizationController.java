package visuals.internationalization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import model.ModeType;
import visuals.Navigaattori;
import visuals.effects.commonHovers.Hovers;
import visuals.effects.menuEffects.IMenuLayoutEffects;
import visuals.effects.menuEffects.MenuLayoutEffects;

import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

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
    private Button previousButton;
    @FXML
    private Button nextButton;

    @FXML
    private ImageView stepImage;

    private ResourceBundle bundle;

    private final IMenuLayoutEffects menuLayoutEffects = new MenuLayoutEffects();

    private final Hovers hovers = new Hovers(menuLayoutEffects);

    private int currentStep = 0;
    private List<Image> stepImages;




    @FXML
    private void initialize(){

        Locale.setDefault(new Locale("en")); // set default language
        changeLanguage(Locale.getDefault()); // load default language resources

        stepImages = new ArrayList<>();
        stepImages.add(loadImage("pictures/images/step1.png"));
        stepImages.add(loadImage("pictures/images/step2.png"));
        stepImages.add(loadImage("pictures/images/step3.png"));
        stepImages.add(loadImage("pictures/images/step4.png"));
        stepImages.add(loadImage("pictures/images/step5.png"));
        stepImages.add(loadImage("pictures/images/step6.png"));

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
    }



    private void styleLabel(Label l ){
        if(l != null){
            l.setFont(Font.font(18));
            l.setStyle("-fx-text-fill: white;");

        }
    }

    private Image loadImage(String path) {
        URL resource = getClass().getResource("/" + path);
        if (resource == null) {
            System.err.println("Resource not found: " + path);
            return null;
        }
        return new Image(resource.toExternalForm());
    }


    private void styleButton(Button b) {
        // get hex for dark purple and light purple and save them to variables
        String darkPurple = "#800080";
        String lightPurple = "#cc00cc";
        b.setFont(Font.font("VCR OSD Mono", 14));
        b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: " + lightPurple + " ; -fx-text-fill: white;"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: " + darkPurple + "; -fx-text-fill: white;"));
    }
    private void updateInfo() {
        stepText.setText(bundle.getString("stepText"));
        switch (currentStep) {
            case 0:
                labelInfo.setText(bundle.getString("stepOne"));
                break;
            case 1:
                labelInfo.setText(bundle.getString("stepTwo"));
                break;
            case 2:
                labelInfo.setText(bundle.getString("stepThree"));
                break;
            case 3:
                labelInfo.setText(bundle.getString("stepFour"));
                break;
            case 4:
                labelInfo.setText(bundle.getString("stepFive"));
                break;
            case 5:
                labelInfo.setText(bundle.getString("stepSix"));
                break;
            default:
                labelInfo.setText("");
                break;
        }

        stepImage.setImage(stepImages.get(currentStep));

    }

    @FXML
    public void setButtonReturn(ActionEvent event) {
        try {
            Navigaattori.getInstance().changeScene(ModeType.MENU);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


    @FXML
    public void setButtonEn() {
        changeLanguage(new Locale("en"));
        JavaFXInternationalization.setLocale(new Locale("en"));
        anchorLbs.requestLayout();
    }


    @FXML
    public void setButtonFin() {
        changeLanguage(new Locale("fi"));
        JavaFXInternationalization.setLocale(new Locale("fi"));
        anchorLbs.requestLayout();
    }

    @FXML
    public void setButtonSwe() {
        changeLanguage(new Locale("swe"));
        JavaFXInternationalization.setLocale(new Locale("swe"));
        anchorLbs.requestLayout();
    }

    @FXML
    public void setButtonLat() {
        changeLanguage(new Locale("lat"));
        JavaFXInternationalization.setLocale(new Locale("lat"));
        anchorLbs.requestLayout();
    }

    @FXML
    public void hoverOn(javafx.scene.input.MouseEvent event) {

        hovers.commonHoverOn(event);
    }

    @FXML
    public void hoverOff(javafx.scene.input.MouseEvent event) {

        hovers.commonHoverOff(event);
    }

    private void updateNavigationButtons() {
        previousButton.setDisable(currentStep <= 0);
        nextButton.setDisable(currentStep >= getTotalSteps() - 1);
    }



    private int getTotalSteps() {
        return 6;
    }


    @FXML
    private void handlePreviousButton() {

        if(currentStep > 0 ){
            currentStep --;
            updateInfo();
            updateNavigationButtons();
        }

    }

    @FXML
    private void handleNextButton() {
        if(currentStep < getTotalSteps() -1 ){
            currentStep ++;
            updateInfo();
            updateNavigationButtons();
        }

    }
}