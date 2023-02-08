package controller;

import model.MemoryObject;

import java.util.ArrayList;

public interface IControllerEtoV {

    void sendType(int id, int typeID);

    void clearPair(ArrayList<MemoryObject> memoryList);


    void setPersonalScore(ArrayList<String> personalScoreList);

}
