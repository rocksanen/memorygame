package controller;

import javafx.application.Platform;
import model.*;
import visuals.IGui;

import java.util.ArrayList;

public class Controller implements IControllerVtoE, IControllerEtoV, IControllerScoreToV {

    private final IGui ui;
    private IEngine engine;

    private Scoreboard scoreboard;
    public Controller(IGui ui) {

        this.ui = ui;

    }

    @Override
    public void startEasyGame() {

        this.engine = new Engine(ModeType.EASY,this);
        this.engine.setMemoryObjects();

    }
    @Override
    public void eB0(int i){

        engine.addToComparing(i);

    }
    @Override
    public void eB1(int i){

        engine.addToComparing(i);

    }
    @Override
    public void eB2(int i){

        engine.addToComparing(i);

    }
    @Override
    public void eB3(int i){

        engine.addToComparing(i);

    }
    @Override
    public void eB4(int i){

        engine.addToComparing(i);

    }
    @Override
    public void eB5(int i) {

        engine.addToComparing(i);

    }

    @Override
    public void sendType(int id,int typeID) {

       ui.setTypeToLabel(id,typeID);

    }

    @Override
    public void clearPair(ArrayList<MemoryObject> memoryList){

        Platform.runLater(() ->ui.clearPair(memoryList));

    }

    @Override
    public void setPersonalScore(ArrayList<String> personalScoreList) {

        Platform.runLater(() -> ui.setPersonalScores(personalScoreList));

    }

    @Override
    public void getWorldScore(ArrayList<String> worldScoreList) {

        Platform.runLater(() -> ui.getWorldScore(worldScoreList));

    }
}
