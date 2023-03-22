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
    private final ArrayList<Image> menuImages = new ArrayList<>();
    private ImageCache(){}

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

        Image easyBack = new Image(new FileInputStream("src/main/java/visuals/Images/easyGame/cubes/cubeBack.jpg"));
        Image easyBehind = new Image(new FileInputStream("src/main/java/visuals/Images/easyGame/cubes/cubeBehind.png"));

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

        Image mediumBack = new Image(new FileInputStream("src/main/java/visuals/images/uustigru5.jpg"));
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

        Image hardBack = new Image(new FileInputStream("src/main/java/visuals/Images/hardtree2.jpg"));
        Image hardBehind = new Image(new FileInputStream("src/main/java/visuals/Images/hardBehind1.jpg"));

        hardCompImages.add(hardBack);
        hardCompImages.add(hardBehind);

    }

    public void addToGameBackGroundCache() throws FileNotFoundException {

        Image easyBack = new Image(new FileInputStream("src/main/java/visuals/images/easyGame/enviroment/background.png"));
        Image mediumBack = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/tigru.jpg"));
        Image hardBack = new Image(new FileInputStream("src/main/java/visuals/images/hardGame/enviroment/hardback4.jpg"));
        Image midR = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/midR.png"));
        Image midTop = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/midTop.png"));
        Image midL = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/midL.png"));
        Image midBot = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/midBot.png"));
        Image easyTop = new Image(new FileInputStream("src/main/java/visuals/images/easyGame/enviroment/easyTop.png"));
        Image easyBot = new Image(new FileInputStream("src/main/java/visuals/images/easyGame/enviroment/easyBot.png"));
        Image easyL = new Image(new FileInputStream("src/main/java/visuals/images/easyGame/enviroment/easyL2.png"));
        Image midgrid = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/mediumGrid.png"));
        Image hardGrid = new Image(new FileInputStream("src/main/java/visuals/images/hardGame/enviroment/girdHard.png"));
        Image hardR = new Image(new FileInputStream("src/main/java/visuals/images/hardGame/enviroment/hardR.png"));
        Image hardL = new Image(new FileInputStream("src/main/java/visuals/images/hardGame/enviroment/hardL.png"));
        Image play = new Image(new FileInputStream("src/main/java/visuals/images/playretro.png"));
        Image returngame = new Image(new FileInputStream("src/main/java/visuals/images/return.png"));
        Image movingjungle = new Image(new FileInputStream("src/main/java/visuals/images/midR.jpg"));   // wtf? :D
        Image easyend = new Image(new FileInputStream("src/main/java/visuals/images/midR.jpg"));        // yep, nice placeholder confusion..
        Image midneo = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/medneo.png"));
        Image midneo2 = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/midneo2.png"));
        Image midneo3 = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/midneo3.png"));
        Image midneo4 = new Image(new FileInputStream("src/main/java/visuals/images/mediumGame/enviroment/midneo4.png"));
        Image easyneo = new Image(new FileInputStream("src/main/java/visuals/images/easyGame/enviroment/easyneo.png"));
        Image hardneo = new Image(new FileInputStream("src/main/java/visuals/images/hardGame/enviroment/hardneo.png"));
        Image easyGrid = new Image(new FileInputStream("src/main/java/visuals/images/easyGame/enviroment/easyGrid.png"));


        gameBackGroundImages.add(easyBack);     // 0
        gameBackGroundImages.add(mediumBack);   // 1
        gameBackGroundImages.add(hardBack);     // 2
        gameBackGroundImages.add(midR);         // 3
        gameBackGroundImages.add(midTop);       // 4
        gameBackGroundImages.add(midL);         // 5
        gameBackGroundImages.add(midBot);       // 6
        gameBackGroundImages.add(easyTop);      // 7
        gameBackGroundImages.add(easyBot);      // 8
        gameBackGroundImages.add(easyL);        // 9
        gameBackGroundImages.add(midgrid);      // 10
        gameBackGroundImages.add(hardGrid);     // 11
        gameBackGroundImages.add(hardR);        // 12
        gameBackGroundImages.add(hardL);        // 13
        gameBackGroundImages.add(play);         // 14
        gameBackGroundImages.add(returngame);   // 15
        gameBackGroundImages.add(movingjungle); // 16
        gameBackGroundImages.add(easyend);      // 17
        gameBackGroundImages.add(midneo);       // 18
        gameBackGroundImages.add(midneo2);      // 19
        gameBackGroundImages.add(midneo3);      // 20
        gameBackGroundImages.add(midneo4);      // 21
        gameBackGroundImages.add(easyneo);      // 22
        gameBackGroundImages.add(hardneo);      // 23
        gameBackGroundImages.add(easyGrid);     // 24

    }

    public void addToMenuCache() throws FileNotFoundException {

        Image pergament = new Image(new FileInputStream("src/main/java/visuals/images/menu/testaperga7.jpg"));
        Image miniEasy = new Image(new FileInputStream("src/main/java/visuals/images/menu/background.png"));
        Image miniMedium = new Image(new FileInputStream("src/main/java/visuals/images/menu/tigru.jpg"));
        Image miniHard = new Image(new FileInputStream("src/main/java/visuals/images/menu/hardback4.jpg"));
        Image easyFrame = new Image(new FileInputStream("src/main/java/visuals/images/menu/easyneo.png"));
        Image mediumFrame = new Image(new FileInputStream("src/main/java/visuals/images/menu/medneo.png"));
        Image hardFrame = new Image(new FileInputStream("src/main/java/visuals/images/menu/hardneo.png"));
        Image japan = new Image(new FileInputStream("src/main/java/visuals/images/menu/cherry2.png"));
        Image jungle = new Image(new FileInputStream("src/main/java/visuals/images/menu/jungle.png"));
        Image redtree = new Image(new FileInputStream("src/main/java/visuals/images/menu/redtree.png"));
        Image dirt = new Image(new FileInputStream("src/main/java/visuals/images/menu/dirt.png"));
        Image burningsun = new Image(new FileInputStream("src/main/java/visuals/images/menu/burningsun.png"));
        Image easydes1 = new Image(new FileInputStream("src/main/java/visuals/images/menu/easydes1.png"));
        Image easydes2 = new Image(new FileInputStream("src/main/java/visuals/images/menu/easydes2.png"));
        Image easydes3 = new Image(new FileInputStream("src/main/java/visuals/images/menu/easydes3.png"));
        Image kotoku = new Image(new FileInputStream("src/main/java/visuals/images/menu/kotoku.png"));
        Image tigerden = new Image(new FileInputStream("src/main/java/visuals/images/menu/tigerden2.png"));
        Image treeoflife = new Image(new FileInputStream("src/main/java/visuals/images/menu/treeoflife2.png"));
        Image medes1 = new Image(new FileInputStream("src/main/java/visuals/images/menu/medes1.png"));
        Image medes2 = new Image(new FileInputStream("src/main/java/visuals/images/menu/medes2.png"));
        Image medes3 = new Image(new FileInputStream("src/main/java/visuals/images/menu/medes3.png"));
        Image hardes1 = new Image(new FileInputStream("src/main/java/visuals/images/menu/hardes1.png"));
        Image hardes2 = new Image(new FileInputStream("src/main/java/visuals/images/menu/hardes2.png"));
        Image hardes3 = new Image(new FileInputStream("src/main/java/visuals/images/menu/hardes3.png"));


        menuImages.add(pergament);          // 0
        menuImages.add(miniEasy);           // 1
        menuImages.add(miniMedium);         // 2
        menuImages.add(miniHard);           // 3
        menuImages.add(easyFrame);          // 4
        menuImages.add(mediumFrame);        // 5
        menuImages.add(hardFrame);          // 6
        menuImages.add(japan);              // 7
        menuImages.add(jungle);             // 8
        menuImages.add(redtree);            // 9
        menuImages.add(dirt);               // 10
        menuImages.add(burningsun);         // 11
        menuImages.add(easydes1);           // 12
        menuImages.add(easydes2);           // 13
        menuImages.add(easydes3);           // 14
        menuImages.add(kotoku);             // 15
        menuImages.add(tigerden);           // 16
        menuImages.add(treeoflife);         // 17
        menuImages.add(medes1);             // 18
        menuImages.add(medes2);             // 19
        menuImages.add(medes3);             // 20
        menuImages.add(hardes1);            // 21
        menuImages.add(hardes2);            // 22
        menuImages.add(hardes3);            // 23

    }

    public ArrayList<Image> getMenuCache() {

        return menuImages;
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
