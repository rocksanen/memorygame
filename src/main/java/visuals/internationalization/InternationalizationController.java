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

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

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
    public Label titleInfo, welcomeText, labelInfo,stepText ,stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, lastLine;

    private ResourceBundle bundle;

    private final IMenuLayoutEffects menuLayoutEffects = new MenuLayoutEffects();

    private final Hovers hovers = new Hovers(menuLayoutEffects);


    @FXML
    private void initialize(){

        Locale.setDefault(new Locale("en")); // set default language
        changeLanguage(Locale.getDefault()); // load default language resources

        Font.loadFont(Objects.requireNonNull(getClass().getClassLoader().getResource("fonts/VCR_OSD_MONO_1.001.ttf")).toExternalForm(), 14);
        styleButton(buttonReturn);
        String imageurl = Objects.requireNonNull(getClass().getClassLoader().getResource("images/infoBackground.jpeg")).toExternalForm();
        anchorLbs.setStyle("-fx-background-image: url('" + imageurl + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        for (Label label : Arrays.asList(titleInfo, labelInfo, stepText, stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, lastLine)) {
            styleLabel(label);
        }
        welcomeText.setStyle("-fx-font: 24px \"VCR OSD Mono\"; -fx-text-fill: white;");

    }

    private void styleLabel(Label l ){
        if(l != null){
            l.setFont(Font.font("VCR OSD Mono", 14));
            l.setStyle("-fx-text-fill: white;");

        }
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

    for (Label label : Arrays.asList(titleInfo, welcomeText, stepText, stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, lastLine)) {
        if (label != null) {
            String key = label.getId();
            String text = bundle.getString(key);
            label.setText(text);
        }
    }
}

    @FXML
    public void setButtonEn() {
        changeLanguage(new Locale("en"));
        anchorLbs.requestLayout();
    }


    @FXML
    public void setButtonFin() {
        changeLanguage(new Locale("fi"));
        anchorLbs.requestLayout();
    }

    @FXML
    public void setButtonSwe() {
        changeLanguage(new Locale("swe"));
        anchorLbs.requestLayout();
    }
    @FXML
    public void setButtonLat() {
        changeLanguage(new Locale("lat"));
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
}
