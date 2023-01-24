package Visuals;

import Database.dao.IMemoryGameDAO;
import Database.dao.MemoryGameDAO;
import Database.datasource.SqlJpaConn;
import Database.entity.Account;
import Model.*;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp extends Application {
    public static void main(String[] args) {

        //User ja hänen tuloslistansa haetaan oikeassa versiossa databasesta sovelluksen käynnistyttyä.
        ArrayList<Integer> tuloslista = new ArrayList<>();
        IUser user = new Model.User("kalle",1,tuloslista);
        user.addScore(3500);
        user.addScore(1200);

        System.out.println(user + "\n");

        IEngine engine = new Engine(ModeType.EASY);
        engine.setMemoryObjects();

        System.out.println(engine + "\n");

        for(MemoryObject object: engine.getSuffledObjects()) {

            System.out.println(
                    "Palikan id-numero: " + object.getIdNumber() +
                            ", Onko aktiivinen: " + object.isActive()
            );
        }


        //tessstti
        EntityManager em = SqlJpaConn.getInstance();
        IMemoryGameDAO dao = new MemoryGameDAO();
        Account account = new Account("tony", "tiger");

        dao.saveUser(account);

        System.out.println("not done☁️");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}