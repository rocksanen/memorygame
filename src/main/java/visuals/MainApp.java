package visuals;

import model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp extends Application {
    public static void main(String[] args) {
        IEngine engine = new Engine(ModeType.EASY);
        engine.setMemoryObjects();
        engine.suffleObjects();

        System.out.println(engine + "\n");

        for (MemoryObject object : engine.getSuffledObjects()) {

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
        for (MemoryObject obj : objectList) {
            if (testObject.compareTo(obj) == 0) {
                System.out.println("objects have the same type.");
                break;
            }
        }
        System.out.println("objects are not the same !");

        /* end TEST for comparing cards! */

        /* TEST for database */
        User user = User.getInstance();
        user.signup("eetu");
        user.login("eetu");

        user.addScore(7000, "intermediate");
        user.addScore(5000, "intermediate");

        System.out.println("personal scores: " + user.getPersonalScores().getScores().size());
        for (Score score : user.getPersonalScores().getScores()) {
            System.out.println(score.toString());
        }


        user.getPersonalScores().deleteScore(user.getPersonalScores().getScores().get(0).getScoreid());
        System.out.println("deleted first score");
        System.out.println("personal scores: " + user.getPersonalScores().getScores().size());



        Scoreboard globalScores = new Scoreboard();
        globalScores.fetchScores("intermediate");

        System.out.println("global scores: " + globalScores.getScores().size());
        // print
        for (Score score : globalScores.getScores()) {
            System.out.println(score.toString());
        }

        user.logout();
        /* end TEST for database */

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