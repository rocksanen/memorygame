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
                logoutButton.setLayoutX(logoutButton.getLayoutX() - 1);

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

    public void gameModeInfoTranslator(
            ImageView easydes1,ImageView easydes2,ImageView easydes3,
            ImageView medes1,ImageView medes2,ImageView medes3,
            ImageView hardes1,ImageView hardes2,ImageView hardes3
            )
    {

        switch (locale.getLanguage()) {
            case "fi" -> {

                easydes1.setImage(new Image("/images/menuImages/Easygame_FI.png"));
                easydes1.setLayoutX(easydes1.getLayoutX() - 7.4);
                easydes2.setImage(new Image("/images/menuImages/6_cubes_FI.png"));
                easydes2.setLayoutX(easydes2.getLayoutX() + 7);
                easydes3.setImage(new Image("/images/menuImages/Unlimitedtime_FI.png"));
                medes1.setImage(new Image("/images/menuImages/Mediumgame_FI.png"));
                medes1.setLayoutX(medes1.getLayoutX() - 3.7);
                medes2.setImage(new Image("/images/menuImages/12_cubes_FI.png"));
                medes2.setLayoutX(medes2.getLayoutX() + 2);
                medes3.setImage(new Image("/images/menuImages/Timelimit_med_FI.png"));
                hardes1.setImage(new Image("/images/menuImages/Hardgame_FI.png"));
                hardes1.setLayoutX(hardes1.getLayoutX() - 7.5);
                hardes2.setImage(new Image("/images/menuImages/20_cubes_FI.png"));
                hardes2.setLayoutX(hardes2.getLayoutX() + 1.5);
                hardes3.setImage(new Image("/images/menuImages/Timelimit_hard_FI.png"));

            }
            case "swe" -> {

                easydes1.setImage(new Image("/images/menuImages/Easygame_SWE.png"));
                easydes1.setLayoutX(easydes1.getLayoutX() - 1);
                easydes2.setImage(new Image("/images/menuImages/6_cubes_SWE.png"));
                easydes3.setImage(new Image("/images/menuImages/Unlimitedtime_SWE.png"));
                medes1.setImage(new Image("/images/menuImages/Mediumgame_SWE.png"));
                medes1.setLayoutX(medes1.getLayoutX() + 1);
                medes2.setImage(new Image("/images/menuImages/12_cubes_SWE.png"));
                medes3.setImage(new Image("/images/menuImages/Timelimit_med_SWE.png"));
                hardes1.setImage(new Image("/images/menuImages/Hardgame_SWE.png"));
                hardes2.setImage(new Image("/images/menuImages/20_cubes_SWE.png"));
                hardes3.setImage(new Image("/images/menuImages/Timelimit_hard_SWE.png"));
            }
            case "lat" -> {

                easydes1.setImage(new Image("/images/menuImages/Easygame_LAT.png"));
                easydes1.setLayoutX(easydes1.getLayoutX() + 1);
                easydes2.setImage(new Image("/images/menuImages/6_cubes_LAT.png"));
                easydes2.setLayoutX(easydes2.getLayoutX() - 5.5);
                easydes3.setImage(new Image("/images/menuImages/Unlimitedtime_LAT.png"));
                medes1.setImage(new Image("/images/menuImages/Mediumgame_LAT.png"));
                medes1.setLayoutX(medes1.getLayoutX() - 4);
                medes2.setImage(new Image("/images/menuImages/12_cubes_LAT.png"));
                medes2.setLayoutX(medes2.getLayoutX() - 7);
                medes3.setImage(new Image("/images/menuImages/Timelimit_med_LAT.png"));
                hardes1.setImage(new Image("/images/menuImages/Hardgame_LAT.png"));
                hardes1.setLayoutX(hardes1.getLayoutX() - 2);
                hardes2.setImage(new Image("/images/menuImages/20_cubes_LAT.png"));
                hardes2.setLayoutX(hardes2.getLayoutX() - 6.5);
                hardes3.setImage(new Image("/images/menuImages/Timelimit_hard_LAT.png"));

            }
        }
    }
}
