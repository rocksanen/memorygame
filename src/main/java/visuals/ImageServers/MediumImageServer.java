package visuals.ImageServers;

import visuals.ImageServers.IImageServer;

import java.util.ArrayList;
import java.util.Collections;

public class MediumImageServer implements IImageServer {

    ArrayList<String> urlList;
    public MediumImageServer() {

        this.urlList = new ArrayList<>();

        urlList.add("src/main/java/visuals/Images/nalle.png");
        urlList.add("src/main/java/visuals/Images/panda.png");
        urlList.add("src/main/java/visuals/Images/pantteri.png");
        urlList.add("src/main/java/visuals/Images/possu.png");
        urlList.add("src/main/java/visuals/Images/pupu.png");
        urlList.add("src/main/java/visuals/Images/tiikeri.png");

        Collections.shuffle(urlList);
    }
    @Override
    public ArrayList<String> getImageUrl() {
        return urlList;
    }
}
