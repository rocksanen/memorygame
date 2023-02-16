package visuals;

import javafx.application.Application;
import javafx.stage.Stage;
import visuals.imageServers.ImageCache;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainApp extends Application {
    public static void main(String[] args) throws FileNotFoundException {

        ImageCache.getInstance().addToEasyCache();
        ImageCache.getInstance().addToMediumCache();
        ImageCache.getInstance().addToHardCache();
        visuals.Gui.main(args);
    }

    @Override
    public void start(Stage arg0) throws IOException {

        Gui gui = new Gui();
        gui.start(arg0);
    }
}