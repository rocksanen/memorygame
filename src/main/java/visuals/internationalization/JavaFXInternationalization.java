/**
 The JavaFXInternationalization class handles the internationalization of the application by managing the current locale
 and loading the corresponding resource bundle file. It provides methods for setting and retrieving the current locale.
 */
package visuals.internationalization;
import java.util.Locale;
import java.util.ResourceBundle;

public class JavaFXInternationalization {

    // The default locale is English
    private static Locale locale = new Locale("en");
    /**
     * Retrieves the resource bundle for the current locale.
     *
     * @return A ResourceBundle object containing the key-value pairs for the current locale.
     */
    public static ResourceBundle internationalizationLoaderProperties() {

        return ResourceBundle.getBundle("Bundle", locale);
    }
    /**
     * Sets the current locale of the application to the specified locale.
     *
     * @param newLocale The Locale object representing the new locale.
     */
    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }
    /**
     * Retrieves the current locale of the application.
     *
     * @return The Locale object representing the current locale.
     */
    public static Locale getLocale(){
        return locale;
    }
}
