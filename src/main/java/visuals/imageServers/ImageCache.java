package visuals.imageServers;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Stores images for further use in ArrayLists.
 * Implemented as a singleton.
 */
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

    /**
     * Constructor. Does nothing.
     */
    private ImageCache() {
    }

    /**
     * Returns the instance of the class.
     *
     * @return instance of the class
     */
    public static ImageCache getInstance() {

        if (instance == null) {

            instance = new ImageCache();
        }

        return instance;

    }

    /**
     * Loads images used in the easy game mode to memory.
     *
     * @throws FileNotFoundException if the file is not found
     */
    public void addToEasyCache() throws FileNotFoundException {

        Image nalle = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/nalle.png")));
        Image panda = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/panda.png")));
        Image pantteri = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/pantteri.png")));
        Image possu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/possu.png")));
        Image pupu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/pupu.png")));
        Image tiikeri = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/tiikeri.png")));


        easyImageCache.add(nalle);
        easyImageCache.add(panda);
        easyImageCache.add(pantteri);
        easyImageCache.add(possu);
        easyImageCache.add(pupu);
        easyImageCache.add(tiikeri);

        Image easyBack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/cubes/cubeBack.jpg")));
        Image easyBehind = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/cubes/cubeBehind.png")));

        easyCompImages.add(easyBack);
        easyCompImages.add(easyBehind);

    }

    /**
     * Loads images used in the medium game mode to memory.
     *
     * @throws FileNotFoundException if the file is not found
     */
    public void addToMediumCache() throws FileNotFoundException {

        Image goldDragon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/goldDragonHard.png")));
        Image greenDragon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/greenDragonHard.png")));
        Image lyhty = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/lyhtyHard.png")));
        Image boyGirl = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/boyGirlHard.png")));
        Image happyGirl = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/happyGirlHard.png")));
        Image bird = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/birdHard.png")));


        mediumImageCache.add(goldDragon);
        mediumImageCache.add(greenDragon);
        mediumImageCache.add(lyhty);
        mediumImageCache.add(boyGirl);
        mediumImageCache.add(bird);
        mediumImageCache.add(happyGirl);

        Image mediumBack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/uustigru5.jpg")));
        Image mediumBehind = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumCubeBack.jpg")));

        mediumCompImages.add(mediumBack);
        mediumCompImages.add(mediumBehind);


    }

    /**
     * Loads images used in the hard game mode to memory.
     *
     * @throws FileNotFoundException if the file is not found
     */
    public void addToHardCache() throws FileNotFoundException {

        Image goldDragon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/goldDragonHard.png")));
        Image greenDragon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/greenDragonHard.png")));
        Image skull = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/skullHard.png")));
        Image boyGirl = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/boyGirlHard.png")));
        Image happyGirl = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/happyGirlHard.png")));
        Image pupu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/pupu.png")));
        Image tiikeri = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/tiikeri.png")));
        Image lohnari = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/lohnari.jpg")));

        Image login = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/login.png")));
        Image hardback3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardback3.jpg")));

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

        Image hardBack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardtree2.jpg")));
        Image hardBehind = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardBehind1.jpg")));

        hardCompImages.add(hardBack);
        hardCompImages.add(hardBehind);

    }

    /**
     * Loads images used in the background of every game mode
     *
     * @throws FileNotFoundException if the file is not found
     */
    public void addToGameBackGroundCache() throws FileNotFoundException {

        Image easyBack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/enviroment/background.png")));
        Image mediumBack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/tigru.jpg")));
        Image hardBack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardGame/enviroment/hardback4.jpg")));
        Image midR = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/midR.png")));
        Image midTop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/midTop.png")));
        Image midL = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/midL.png")));
        Image midBot = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/midBot.png")));
        Image easyTop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/enviroment/easyTop.png")));
        Image easyBot = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/enviroment/easyBot.png")));
        Image easyL = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/enviroment/easyL2.png")));
        Image midgrid = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/mediumGrid.png")));
        Image hardGrid = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardGame/enviroment/girdHard.png")));
        Image hardR = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardGame/enviroment/hardR.png")));
        Image hardL = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardGame/enviroment/hardL.png")));
        Image play = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/playretro.png")));
        Image returngame = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/return.png")));
        Image movingjungle = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/midR.jpg")));   // wtf? :D
        Image easyend = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/midR.jpg")));        // yep, nice placeholder confusion..
        Image midneo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/medneo.png")));
        Image midneo2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/midneo2.png")));
        Image midneo3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/midneo3.png")));
        Image midneo4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/mediumGame/enviroment/midneo4.png")));
        Image easyneo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/enviroment/easyneo.png")));
        Image hardneo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/hardGame/enviroment/hardneo.png")));
        Image easyGrid = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/easyGame/enviroment/easyGrid.png")));


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

    /**
     * Loads images for the main menu
     *
     * @throws FileNotFoundException if the image is not found
     */
    public void addToMenuCache() throws FileNotFoundException {

        Image pergament = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/testaperga7.jpg")));
        Image miniEasy = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/background.png")));
        Image miniMedium = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/tigru.jpg")));
        Image miniHard = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/hardback4.jpg")));
        Image easyFrame = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/easyneo.png")));
        Image mediumFrame = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/medneo.png")));
        Image hardFrame = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/hardneo.png")));
        Image japan = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/cherry2.png")));
        Image jungle = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/jungle.png")));
        Image redtree = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/redtree.png")));
        Image dirt = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/dirt.png")));
        Image burningsun = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/burningsun.png")));
        Image easydes1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/easydes1.png")));
        Image easydes2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/easydes2.png")));
        Image easydes3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/easydes3.png")));
        Image kotoku = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/kotoku.png")));
        Image tigerden = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/tigerden2.png")));
        Image treeoflife = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/treeoflife2.png")));
        Image medes1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/medes1.png")));
        Image medes2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/medes2.png")));
        Image medes3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/medes3.png")));
        Image hardes1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/hardes1.png")));
        Image hardes2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/hardes2.png")));
        Image hardes3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/images/menu/hardes3.png")));


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

    /**
     * returns images for the main menu
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getMenuCache() {

        return menuImages;
    }

    /**
     * returns background images for gamemodes
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getGameBackGroundCache() {

        return gameBackGroundImages;

    }

    /**
     * returns images for the easy game mode
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getEasyCache() {

        return easyImageCache;

    }

    /**
     * returns images for the medium game mode
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getMediumCache() {


        return mediumImageCache;

    }

    /**
     * returns images for the hard game mode
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getHardCache() {


        return hardImageCache;

    }

    /**
     * Background images specfic to easuy mode
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getEasyComp() {

        return easyCompImages;

    }

    /**
     * Background images specfic to medium mode
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getMediumComp() {


        return mediumCompImages;

    }

    /**
     * Background images specfic to hard mode
     *
     * @return arraylist of images
     */
    public ArrayList<Image> getHardComp() {


        return hardCompImages;

    }
}
