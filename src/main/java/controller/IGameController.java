package controller;


import model.MemoryObject;
import model.ModeType;

import java.util.ArrayList;

public interface IGameController {
    void startGame(ModeType type);

    void sendIdToEngine(int id);

    void clearStorage();


    void killTimer();

    void clearPair(ArrayList<Integer> storage);

    void setGame(ArrayList<MemoryObject> memoryObjects);

    void gameOver();

    void getActive(int id);

    void setTimer(int i);


    void showHint();

    void sendComparingSuccess();
    int getCurrentScore();

    ModeType getDifficulty();

    String getGrade();
}