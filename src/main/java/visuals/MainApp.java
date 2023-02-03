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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainApp extends Application {
    public static void main(String[] args) {

        /*
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
        /*
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
        /*
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

        visuals.Gui.main(args);
    }

    @Override
    public void start(Stage arg0) throws IOException {
        Gui gui = new Gui();
        gui.start(arg0);
    }
}