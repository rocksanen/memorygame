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