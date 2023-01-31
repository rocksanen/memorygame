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
        ArrayList<Integer> tuloslista = new ArrayList<>();
        IUser user = new model.User("kalle",1,tuloslista);
        user.addScore(3500);
        user.addScore(1200);

        System.out.println(user + "\n");

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

        /* TEST for comparing cards! */
        ArrayList<MemoryObject> objectList = new ArrayList<>();
        objectList.add(new MemoryObject(1, 1));
        objectList.add(new MemoryObject(2, 2));
        objectList.add(new MemoryObject(3, 3));

        MemoryObject testObject = new MemoryObject(4, 1);
        for(MemoryObject obj: objectList) {
            if(testObject.compareTo(obj) == 0) {
                System.out.println("objects have the same type.");
                break;
            }
        }
        System.out.println("objects are not the same !");

        /* end TEST for comparing cards! */


        //test for db connection, remove for 500% faster load times!
        EntityManager em = SqlJpaConn.getInstance();

        IAccountDAO accountdao = new AccountDAO();
        Account acc = new Account("tony", "tiger");
        accountdao.saveAccount(acc);

        ILeaderboardDAO leaderdao = new LeaderboardDAO();
        Leaderboard lb = new Leaderboard(acc, 30, "Small", new Date(System.currentTimeMillis()));
        leaderdao.saveScores(lb);
        Leaderboard lb2 = new Leaderboard(acc, 200, "Large", new Date(System.currentTimeMillis()));
        leaderdao.saveScores(lb2);

        List<Leaderboard> userscores = leaderdao.getAccountScores(acc.getAccountid());
        System.out.println("User scores: ");
        for(Leaderboard score: userscores) {
            System.out.println(score.getScore() + " " + score.getTimestamp().toString());
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