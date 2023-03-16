package controller;


import model.MemoryObject;
import model.ModeType;

import java.util.ArrayList;

public interface IGameController {
    void startGame(ModeType type);

    void sendIdToEngine(int id);

    void clearStorage();

    void sendReturnSignal();

    void killTimer();

    void clearPair(ArrayList<Integer> storage);

    void setGame(ArrayList<MemoryObject> memoryObjects);

    void gameOver();

    void getActive(int id);

    void getTime();

    void setTimer(int i);

    void getReturnSignal();

    void sendComparingSuccess();
}