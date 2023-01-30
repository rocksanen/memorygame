package visuals;

import database.dao.IAccountDAO;
import database.dao.AccountDAO;
import database.dao.ILeaderboardDAO;
import database.dao.LeaderboardDAO;
import database.datasource.SqlJpaConn;
import database.entity.Account;
import database.entity.Leaderboard;
import model.*;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainApp extends Application {
    public static void main(String[] args) {

        //User ja hänen tuloslistansa haetaan oikeassa versiossa databasesta sovelluksen käynnistyttyä.




        IEngine engine = new Engine(ModeType.EASY);
        engine.setMemoryObjects();
        engine.suffleObjects();

        System.out.println(engine + "\n");

        for(MemoryObject object: engine.getSuffledObjects()) {

            System.out.println(
                    "Palikan id-numero: " + object.getIdNumber() +
                            ", Palikan tyyppi-numero: " + object.getTypeId() +
                            ", Onko aktiivinen: " + object.isActive()
            );
        }


        /***** proof of concept ******/

        System.out.println("***************USER*****************");

        // login, if does not exist create user
        User kayttaja = User.getInstance();
        kayttaja = kayttaja.login("eetu");
        if (kayttaja != null) {
            kayttaja.signup("eetu");
        }
        // save a score
        kayttaja.saveScore(67);

        ArrayList<Leaderboard> personalScores = kayttaja.getPersonalScores();

        // get scores of user
        for (Leaderboard lb: personalScores) {
            System.out.println(lb.toString());
        }

        System.out.println("***************SCOREBOARD*****************");
        Scoreboard pistelauta = Scoreboard.getInstance();
        ArrayList<Leaderboard> worldscores = pistelauta.getWorldscores();
        // global top 100
        for (Leaderboard lb: worldscores) {
            System.out.println(lb.toString());
        }


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}