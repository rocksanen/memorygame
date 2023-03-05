package visuals.imageServers;

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
    private final ArrayList<Image> gameBackGroundImages = new ArrayList<>();
    private final ArrayList<Image> introImages = new ArrayList<>();
    private final ArrayList<Image> menuImages = new ArrayList<>();
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

        Image easyBack = new Image(new FileInputStream("src/main/java/visuals/Images/ladders.jpg"));
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

        Image mediumBack = new Image(new FileInputStream("src/main/java/visuals/Images/stone4.png"));
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

        Image hardBack = new Image(new FileInputStream("src/main/java/visuals/Images/mysticalcu.png"));
        Image hardBehind = new Image(new FileInputStream("src/main/java/visuals/Images/hardBehind1.jpg"));

        hardCompImages.add(hardBack);
        hardCompImages.add(hardBehind);

    }

    public void addToGameBackGroundCache() throws FileNotFoundException {

        Image easyBack = new Image(new FileInputStream("src/main/java/visuals/images/background.png"));
        Image mediumBack = new Image(new FileInputStream("src/main/java/visuals/images/tigru.jpg"));
        Image hardBack = new Image(new FileInputStream("src/main/java/visuals/images/hardback4.jpg"));
        Image midR = new Image(new FileInputStream("src/main/java/visuals/images/midR.png"));
        Image midTop = new Image(new FileInputStream("src/main/java/visuals/images/midTop.png"));
        Image midL = new Image(new FileInputStream("src/main/java/visuals/images/midL.png"));
        Image midBot = new Image(new FileInputStream("src/main/java/visuals/images/midBot.png"));
        //Image midend = new Image(new FileInputStream(""));
        Image easyTop = new Image(new FileInputStream("src/main/java/visuals/images/easyTop.png"));
        Image easyBot = new Image(new FileInputStream("src/main/java/visuals/images/easyBot.png"));
        Image easyL = new Image(new FileInputStream("src/main/java/visuals/images/easyL2.png"));
        Image midgrid = new Image(new FileInputStream("src/main/java/visuals/images/gird.png"));
        Image hardGrid = new Image(new FileInputStream("src/main/java/visuals/images/girdHard.png"));
        Image hardR = new Image(new FileInputStream("src/main/java/visuals/images/hardR.png"));
        Image hardL = new Image(new FileInputStream("src/main/java/visuals/images/hardL.png"));

        gameBackGroundImages.add(easyBack);
        gameBackGroundImages.add(mediumBack);
        gameBackGroundImages.add(hardBack);
        gameBackGroundImages.add(midR);
        gameBackGroundImages.add(midTop);
        gameBackGroundImages.add(midL);
        gameBackGroundImages.add(midBot);
        gameBackGroundImages.add(easyTop);
        gameBackGroundImages.add(easyBot);
        gameBackGroundImages.add(easyL);
        gameBackGroundImages.add(midgrid);
        gameBackGroundImages.add(hardGrid);
        gameBackGroundImages.add(hardR);
        gameBackGroundImages.add(hardL);

    }


    public void addToIntroCache() throws FileNotFoundException {

        Image sun = new Image(new FileInputStream("src/main/java/visuals/images/sunRay.png"));
        Image lightning = new Image(new FileInputStream("src/main/java/visuals/images/lightning.png"));
        Image blackSun = new Image(new FileInputStream("src/main/java/visuals/images/blacksun1.png"));
        Image memomaze = new Image(new FileInputStream("src/main/java/visuals/images/memomaze.png"));
        Image loading = new Image(new FileInputStream("src/main/java/visuals/images/loading.png"));
        Image mightyfour= new Image(new FileInputStream("src/main/java/visuals/images/mighty4.png"));

        introImages.add(sun);
        introImages.add(lightning);
        introImages.add(blackSun);
        introImages.add(memomaze);
        introImages.add(loading);
        introImages.add(mightyfour);

    }

    public void addToMenuCache() throws FileNotFoundException {

        Image pergament = new Image(new FileInputStream("src/main/java/visuals/images/testaperga7.jpg"));
        Image miniEasy = new Image(new FileInputStream("src/main/java/visuals/images/background.png"));
        Image miniMedium = new Image(new FileInputStream("src/main/java/visuals/images/tigru.jpg"));
        Image miniHard = new Image(new FileInputStream("src/main/java/visuals/images/hardback4.jpg"));
        Image easyFrame = new Image(new FileInputStream("src/main/java/visuals/images/easyneo.png"));
        Image mediumFrame = new Image(new FileInputStream("src/main/java/visuals/images/medneo.png"));
        Image hardFrame = new Image(new FileInputStream("src/main/java/visuals/images/hardneo.png"));
        Image japan = new Image(new FileInputStream("src/main/java/visuals/images/cherry2.png"));
        Image jungle = new Image(new FileInputStream("src/main/java/visuals/images/jungle.png"));
        Image redtree = new Image(new FileInputStream("src/main/java/visuals/images/redtree.png"));
        Image mt1 = new Image(new FileInputStream("src/main/java/visuals/images/mt1.png"));
        Image mt2 = new Image(new FileInputStream("src/main/java/visuals/images/mt2.png"));
        Image mt3 = new Image(new FileInputStream("src/main/java/visuals/images/mt3.png"));
        Image mt4 = new Image(new FileInputStream("src/main/java/visuals/images/mt4.png"));
        Image mt5 = new Image(new FileInputStream("src/main/java/visuals/images/mt5.png"));
        Image mt6 = new Image(new FileInputStream("src/main/java/visuals/images/mt6.png"));
        Image mt7 = new Image(new FileInputStream("src/main/java/visuals/images/mt7.png"));
        Image mt8 = new Image(new FileInputStream("src/main/java/visuals/images/mt8.png"));
        Image mt9 = new Image(new FileInputStream("src/main/java/visuals/images/mt9.png"));
        Image mt10 = new Image(new FileInputStream("src/main/java/visuals/images/mt10.png"));
        Image mt11 = new Image(new FileInputStream("src/main/java/visuals/images/mt13.png"));
        Image mt12 = new Image(new FileInputStream("src/main/java/visuals/images/mt11.png"));
        Image mt13 = new Image(new FileInputStream("src/main/java/visuals/images/mt12.png"));
        Image dirt = new Image(new FileInputStream("src/main/java/visuals/images/dirt.png"));
        Image burningsun = new Image(new FileInputStream("src/main/java/visuals/images/burningsun.png"));
        Image easydes1 = new Image(new FileInputStream("src/main/java/visuals/images/easydes1.png"));
        Image easydes2 = new Image(new FileInputStream("src/main/java/visuals/images/easydes2.png"));
        Image easydes3 = new Image(new FileInputStream("src/main/java/visuals/images/easydes3.png"));
        Image kotoku = new Image(new FileInputStream("src/main/java/visuals/images/kotoku.png"));
        Image tigerden = new Image(new FileInputStream("src/main/java/visuals/images/tigerden2.png"));
        Image treeoflife = new Image(new FileInputStream("src/main/java/visuals/images/treeoflife2.png"));
        Image medes1 = new Image(new FileInputStream("src/main/java/visuals/images/medes1.png"));
        Image medes2 = new Image(new FileInputStream("src/main/java/visuals/images/medes2.png"));
        Image medes3 = new Image(new FileInputStream("src/main/java/visuals/images/medes3.png"));
        Image hardes1 = new Image(new FileInputStream("src/main/java/visuals/images/hardes1.png"));
        Image hardes2 = new Image(new FileInputStream("src/main/java/visuals/images/hardes2.png"));
        Image hardes3 = new Image(new FileInputStream("src/main/java/visuals/images/hardes3.png"));


        menuImages.add(pergament);
        menuImages.add(miniEasy);
        menuImages.add(miniMedium);
        menuImages.add(miniHard);
        menuImages.add(easyFrame);
        menuImages.add(mediumFrame);
        menuImages.add(hardFrame);
        menuImages.add(japan);
        menuImages.add(jungle);
        menuImages.add(redtree);
        menuImages.add(mt1);
        menuImages.add(mt2);
        menuImages.add(mt3);
        menuImages.add(mt4);
        menuImages.add(mt5);
        menuImages.add(mt6);
        menuImages.add(mt7);
        menuImages.add(mt8);
        menuImages.add(mt9);
        menuImages.add(mt10);
        menuImages.add(mt11);
        menuImages.add(mt12);
        menuImages.add(mt13);
        menuImages.add(dirt);
        menuImages.add(burningsun);
        menuImages.add(easydes1);
        menuImages.add(easydes2);
        menuImages.add(easydes3);
        menuImages.add(kotoku);
        menuImages.add(tigerden);
        menuImages.add(treeoflife);
        menuImages.add(medes1);
        menuImages.add(medes2);
        menuImages.add(medes3);
        menuImages.add(hardes1);
        menuImages.add(hardes2);
        menuImages.add(hardes3);

    }

    public ArrayList<Image> getMenuCache() {

        return menuImages;
    }

    public ArrayList<Image> getIntroCache() {

        return introImages;
    }

    public ArrayList<Image> getGameBackGroundCache() {

        return gameBackGroundImages;

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
