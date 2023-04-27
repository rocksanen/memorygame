package visuals.internationalization;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;
import java.util.Objects;

public class ImageTranslator {

    private final Locale locale;

    public ImageTranslator() {

        locale = JavaFXInternationalization.getLocale();
    }

    public void menuLoginTranslator(
            ImageView userName, ImageView password, ImageView LoginButton,
            ImageView RegisterButton, ImageView logoutButton)
    {

        System.out.println("locale is : " + locale.getLanguage());


        switch (locale.getLanguage()){
            case "fi" -> {
                userName.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Username_FI.png"))));
                password.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Password_FI.png"))));
                LoginButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Login_FI.png"))));

                LoginButton.setFitWidth(userName.getFitWidth() - 25);
                LoginButton.setLayoutX(LoginButton.getLayoutX() + 3);
                RegisterButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Register_FI.png"))));

                RegisterButton.setFitWidth(userName.getFitWidth() - 28);
                RegisterButton.setLayoutX(LoginButton.getLayoutX() + 2);

                logoutButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Logout_FI.png"))));


            }
            case "swe" -> {
                userName.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Username_SWE.png"))));
                password.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Password_SWE.png"))));
                LoginButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Login_SWE.png"))));
                LoginButton.setFitWidth(userName.getFitWidth() - 28);
                LoginButton.setLayoutX(LoginButton.getLayoutX() + 3);
                RegisterButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Register_SWE.png"))));
                RegisterButton.setFitWidth(userName.getFitWidth() - 28);
                RegisterButton.setLayoutX(LoginButton.getLayoutX() + 3);

                logoutButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Logout_SWE.png"))));


            }
            case "lat" ->{
                userName.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Username_LAT.png"))));

                password.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Password_LAT.png"))));
                LoginButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Login_LAT.png"))));
                LoginButton.setFitWidth(userName.getFitWidth() - 25);
                LoginButton.setLayoutX(LoginButton.getLayoutX() + 3);

                RegisterButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Register_LAT.png"))));
                RegisterButton.setFitWidth(userName.getFitWidth() - 25);

                logoutButton.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/menuImages/Logout_LAT.png"))));

            }
        }
    }

    public void inGameTranslator(ImageView personalScoreHeader,ImageView worldScoreHeader) {

        switch (locale.getLanguage()) {
            case "fi" -> {
                personalScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/fi_personalscores.png"))));
                worldScoreHeader.setImage(
                        new Image("/images/headers/fi_worldscores.png"));
            }
            case "swe" -> {
                personalScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/swe_personalscores.png"))));
                worldScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/swe_worldscores.png"))));
            }
            case "lat" -> {
                personalScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/latvian_personalscores.png"))));
                worldScoreHeader.setImage(
                        new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
                                "/images/headers/latvian_worldscores.png"))));
            }
        }
    }
}
