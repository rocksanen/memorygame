package model;

import java.util.ArrayList;

public interface IEngine {

    void suffleObjects();
    ArrayList<MemoryObject> getSuffledObjects();
    void setChosenObjectReady(MemoryObject object);
    CompareResultType compareObjects(ArrayList<MemoryObject> objectList);
    void setMemoryObjects();
    void addMemoryObjectsToList(Integer amount);
    void addToComparing(int i);
    void clearPair(ArrayList<MemoryObject> memoryList);
    void setWorldScore();
    void setPersonalScore();

}
