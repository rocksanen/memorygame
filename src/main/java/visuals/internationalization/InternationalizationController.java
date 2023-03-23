package visuals.internationalization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import model.ModeType;
import visuals.Navigaattori;

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
    public Label infoText, lableTitle, labelInfo,stepText ,stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, lastLine;

    private ResourceBundle bundle;


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
        for (Label label : Arrays.asList(infoText, lableTitle, labelInfo, stepText, stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, lastLine)) {
            styleLabel(label);
        }
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
/*
    private void changeLanguage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle", locale);
        if (infoText != null) {
            infoText.setText(bundle.getString("welcomeText"));
        }
        if (lableTitle != null) {
            lableTitle.setText(bundle.getString("titleInfo"));
        }
        if(labelInfo != null){
            labelInfo.setText(bundle.getString("stepText"));
        }
        if(stepOne != null){
            stepOne.setText(bundle.getString("stepOne"));
        }
        if(stepTwo != null){
            stepTwo.setText(bundle.getString("stepTwo"));
        }
        if(stepThree != null){
            stepThree.setText(bundle.getString("stepThree"));

        }

        if(stepFour != null){
            stepFour.setText(bundle.getString("stepFour"));
        }

        if(stepFive != null){
            stepFive.setText(bundle.getString("stepFive"));
        }

        if(stepSix != null){
            stepSix.setText(bundle.getString("stepSix"));
        }
        if(lastLine != null){
            lastLine.setText(bundle.getString("lastLine"));
        }

    }*/
private void changeLanguage(Locale locale) {
    ResourceBundle bundle = ResourceBundle.getBundle("Bundle", locale);

    for (Label label : Arrays.asList(infoText, lableTitle, stepText, stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, lastLine)) {
        if (label != null) {
            String key = label.getId();
            String text = bundle.getString(key);
            label.setText(text);
        }
    }
}




    public void setButtonEn(ActionEvent event) {
        changeLanguage(new Locale("en"));
        anchorLbs.requestLayout();
    }

    public void setButtonFin(ActionEvent event) {
        changeLanguage(new Locale("fi"));
        anchorLbs.requestLayout();
    }
}
