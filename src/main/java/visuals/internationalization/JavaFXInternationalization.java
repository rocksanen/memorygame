package visuals.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class JavaFXInternationalization {

    private static Locale locale = new Locale("en");

    public ResourceBundle internationalizationLoaderProperties() {

        ResourceBundle bundle = ResourceBundle.getBundle("Bundle", locale);
        return bundle;
    }

    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }
}
