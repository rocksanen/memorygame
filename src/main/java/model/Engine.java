package model;

import java.util.ArrayList;
import java.util.Collections;


public class Engine implements IEngine{

    private  CompareResultType type2;
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
        int type;

        for(int i = 0; i < amount; i++) {

            type = i / 2;
            memoryObjectsList.add(new MemoryObject(i,type));

        }
    }

    @Override
    public void suffleObjects() {
        Collections.shuffle(memoryObjectsList);
        /*
        for(int i = 0; i < memoryObjectsList.size(); i++) {
            System.out.println(memoryObjectsList.get(i).toString());
        }
         */


    }

    @Override
    public ArrayList<MemoryObject> getSuffledObjects() {
        return memoryObjectsList;
    }

    @Override
    public void setChosenObjectReady(MemoryObject object) {

        object.setActive();

    }


    //check if the two cards are the same.
    @Override
    public CompareResultType compareObjects(ArrayList<MemoryObject> objectList) {
        for(MemoryObject obj: objectList) {
            if(this.type.equals(obj.getTypeId())){
                return type2.EQUAL;
            }
        }
        return type2.NOTEQUAL;
    }
    public String toString() {

        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }


}
