package visuals.ImageServers;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ImageCache {

    private static ImageCache instance;
    private final ArrayList<Image> easyImageCache = new ArrayList<>();
    private final ArrayList<Image> mediumImageCache = new ArrayList<>();
    private final ArrayList<Image> hardImageCache = new ArrayList<>();
    private final ArrayList<Image> easyCompImages = new ArrayList<>();
    private final ArrayList<Image> mediumCompImages = new ArrayList<>();
    private final ArrayList<Image> hardCompImages = new ArrayList<>();
    private ImageCache(){};

    public static ImageCache getInstance() {

        if(instance == null) {

            instance = new ImageCache();
        }

        return instance;

    }

    public void addToEasyCache() throws FileNotFoundException {

        Image nalle = new Image(new FileInputStream("src/main/java/visuals/Images/nalle.png")) ;
        Image panda = new Image(new FileInputStream("src/main/java/visuals/Images/panda.png"));
        Image pantteri = new Image(new FileInputStream("src/main/java/visuals/Images/pantteri.png"));
        Image possu = new Image(new FileInputStream("src/main/java/visuals/Images/possu.png"));
        Image pupu = new Image(new FileInputStream("src/main/java/visuals/Images/pupu.png"));
        Image tiikeri = new Image(new FileInputStream("src/main/java/visuals/Images/tiikeri.png"));

        easyImageCache.add(nalle);
        easyImageCache.add(panda);
        easyImageCache.add(pantteri);
        easyImageCache.add(possu);
        easyImageCache.add(pupu);
        easyImageCache.add(tiikeri);

        Image easyBack = new Image(new FileInputStream("src/main/java/visuals/Images/easybackground.jpg"));
        Image easyBehind = new Image(new FileInputStream("src/main/java/visuals/Images/background.png"));

        easyCompImages.add(easyBack);
        easyCompImages.add(easyBehind);

    }

    public void addToMediumCache() throws FileNotFoundException {

        Image goldDragon = new Image(new FileInputStream("src/main/java/visuals/Images/goldDragonHard.png")) ;
        Image greenDragon = new Image(new FileInputStream("src/main/java/visuals/Images/greenDragonHard.png"));
        Image lyhty = new Image(new FileInputStream("src/main/java/visuals/Images/lyhtyHard.png"));
        Image boyGirl = new Image(new FileInputStream("src/main/java/visuals/Images/boyGirlHard.png"));
        Image happyGirl = new Image(new FileInputStream("src/main/java/visuals/Images/happyGirlHard.png"));
        Image bird = new Image(new FileInputStream("src/main/java/visuals/Images/birdHard.png"));

        mediumImageCache.add(goldDragon);
        mediumImageCache.add(greenDragon);
        mediumImageCache.add(lyhty);
        mediumImageCache.add(boyGirl);
        mediumImageCache.add(bird);
        mediumImageCache.add(happyGirl);

        Image mediumBack = new Image(new FileInputStream("src/main/java/visuals/Images/mediumBack3.jpg"));
        Image mediumBehind = new Image(new FileInputStream("src/main/java/visuals/Images/mediumCubeBack.jpg"));

        mediumCompImages.add(mediumBack);
        mediumCompImages.add(mediumBehind);

    }

    public void addToHardCache() throws FileNotFoundException {

        Image goldDragon = new Image(new FileInputStream("src/main/java/visuals/Images/goldDragonHard.png")) ;
        Image greenDragon = new Image(new FileInputStream("src/main/java/visuals/Images/greenDragonHard.png"));
        Image skull = new Image(new FileInputStream("src/main/java/visuals/Images/skullHard.png"));
        Image boyGirl = new Image(new FileInputStream("src/main/java/visuals/Images/boyGirlHard.png"));
        Image happyGirl = new Image(new FileInputStream("src/main/java/visuals/Images/happyGirlHard.png"));
        Image pupu = new Image(new FileInputStream("src/main/java/visuals/Images/pupu.png"));
        Image tiikeri = new Image(new FileInputStream("src/main/java/visuals/Images/tiikeri.png"));
        Image lohnari = new Image(new FileInputStream("src/main/java/visuals/Images/lohnari.jpg"));

        Image login = new Image(new FileInputStream("src/main/java/visuals/Images/login.png"));
        Image hardback3 = new Image(new FileInputStream("src/main/java/visuals/Images/hardback3.jpg"));

        hardImageCache.add(goldDragon);
        hardImageCache.add(greenDragon);
        hardImageCache.add(skull);
        hardImageCache.add(boyGirl);
        hardImageCache.add(pupu);
        hardImageCache.add(tiikeri);
        hardImageCache.add(lohnari);
        hardImageCache.add(happyGirl);
        hardImageCache.add(login);
        hardImageCache.add(hardback3);

        Image hardBack = new Image(new FileInputStream("src/main/java/visuals/Images/hardback2.jpg"));
        Image hardBehind = new Image(new FileInputStream("src/main/java/visuals/Images/hardBehind1.jpg"));

        hardCompImages.add(hardBack);
        hardCompImages.add(hardBehind);

    }

    public ArrayList<Image> getEasyCache() {

        return easyImageCache;

    }

    public ArrayList<Image> getMediumCache() {


        return mediumImageCache;

    }

    public ArrayList<Image> getHardCache() {


        return hardImageCache;

    }

    public ArrayList<Image> getEasyComp() {

        return easyCompImages;

    }

    public ArrayList<Image> getMediumComp() {


        return mediumCompImages;

    }

    public ArrayList<Image> getHardComp() {


        return hardCompImages;

    }
}
