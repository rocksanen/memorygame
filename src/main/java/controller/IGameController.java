package controller;


import model.MemoryObject;

import java.util.ArrayList;

public interface IGameController {
    void startEasyGame();

    void startMediumGame();

    void startHardGame();

    void sendIdToEngine(int id);

    void clearStorage();

    void sendReturnSignal();

    void killTimer();

    void clearPair(ArrayList<Integer> storage);

    void setEasyGame(ArrayList<MemoryObject> memoryObjects);

    void setMediumGame(ArrayList<MemoryObject> memoryObjects);

    void setHardGame(ArrayList<MemoryObject> memoryObjects);

    void gameOver();

    void getActive(int id);

    void getTime();

    void setTimer(int i);

    void getReturnSignal();

    void sendComparingSuccess();
}