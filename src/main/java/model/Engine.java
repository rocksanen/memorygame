package Model;

import java.util.ArrayList;



public class Engine implements IEngine{

    private final ModeType type;
    private ArrayList<MemoryObject> memoryObjectsList;

    public Engine(ModeType type) {

        this.type = type;

    }

    @Override
    public void setMemoryObjects() {

        switch (this.type) {
            case EASY -> addMemoryObjectsToList(6);
            case MEDIUM -> addMemoryObjectsToList(12);
            case HARD -> addMemoryObjectsToList(18);
        }

    }

    @Override
    public void addMemoryObjectsToList(Integer amount) {

        this.memoryObjectsList = new ArrayList<>();

        for(int i = 0; i < amount; i++) {

            memoryObjectsList.add(new MemoryObject(i));

        }

    }

    @Override
    public void suffleObjects() {

        // sekoittakaa memoryObjectsList

    }

    @Override
    public ArrayList<MemoryObject> getSuffledObjects() {
        return memoryObjectsList;
    }

    @Override
    public void setChosenObjectReady(MemoryObject object) {

        object.setActive();

    }

    @Override
    public Boolean compareObjects(ArrayList<MemoryObject> objectList) {
        return null;
    }


    public String toString() {

        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }


}
