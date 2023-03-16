

import visuals.Navigaattori;
import visuals.imageServers.ImageCache;
import java.io.FileNotFoundException;

/**
 * Käynnistää simulaattorin 🚇
 * @author Eetu Soronen
 * @version 1
 */
public class Start {

	/**
	 * public static void main string args
	 * kutsuu Visuals.MainApp.main(args) ja käynnistää simulaattorin
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {

		ImageCache.getInstance().addToIntroCache();
		ImageCache.getInstance().addToMenuCache();
		ImageCache.getInstance().addToEasyCache();
		ImageCache.getInstance().addToMediumCache();
		ImageCache.getInstance().addToHardCache();
		ImageCache.getInstance().addToGameBackGroundCache();
		Navigaattori.main(args);
	}
}