package visuals.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class JavaFXInternationalization {
    private static Locale locale = new Locale("en");

    public static ResourceBundle internationalizationLoaderProperties() {

        return ResourceBundle.getBundle("Bundle", locale);
    }

    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }

    public static Locale getLocale(){
        return locale;
    }
}
