package Model;

import java.util.ArrayList;

public interface IEngine {

    void suffleObjects();
    ArrayList<MemoryObject> getSuffledObjects();
    void setChosenObjectReady(MemoryObject object);
    Boolean compareObjects(ArrayList<MemoryObject> objectList);
    void setMemoryObjects();
    void addMemoryObjectsToList(Integer amount);

}
