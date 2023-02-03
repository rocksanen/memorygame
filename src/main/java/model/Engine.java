package model;

import controller.IControllerEtoV;
import javafx.application.Platform;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


public class Engine implements IEngine{

    private final IControllerEtoV controller;
    private  CompareResultType type2;
    private final ModeType type;
    private ArrayList<MemoryObject> memoryObjectsList;

    private final ArrayList<MemoryObject> comparingList = new ArrayList<>();

    public Engine(ModeType type, IControllerEtoV controller) {

        this.type = type;
        this.controller = controller;

        URL url = getClass().getResource("resources/testi.jpg");
        System.out.println(url);


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


        // Initialize a new ArrayList for the memoryObjectsList
        this.memoryObjectsList = new ArrayList<>();

        // Variable to store the type of each MemoryObject
        int type;

        // Loop to add 'amount' number of MemoryObjects to the memoryObjectsList
        for(int i = 0; i < amount; i++) {

            // Calculate the type of each MemoryObject
            type = i / 2;

            // Add a new MemoryObject to the memoryObjectsList
            memoryObjectsList.add(new MemoryObject(i,type));

        }

        // Shuffle the objects in the memoryObjectsList
        suffleObjects();
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

    @Override
    public void addToComparing(int i){

        // Get the MemoryObject at the given index from memoryObjectsList
        // The index (int i) is from the object user have clicked in the game.
        MemoryObject memoryObject = memoryObjectsList.get(i);

        // Set the id of the MemoryObject to match the id in the userinterface
        memoryObject.setId(i);

        // Send the type of the MemoryObject to the controller
        controller.sendType(i, memoryObject.getTypeId());

        // Add the MemoryObject to the comparingList
        comparingList.add(memoryObject);

        // If there are two objects in the comparingList, compare them
        if(comparingList.size() == 2) {
            compareObjects(comparingList);
            // Clear the comparingList after comparison
            comparingList.clear();
        }
    }

    @Override
    public void clearPair(ArrayList<MemoryObject> memoryList){

        // Create a copy of the memoryList
        ArrayList<MemoryObject> copy = new ArrayList<>(memoryList);

        // Run the clearPair operation asynchronously
        CompletableFuture.runAsync(() -> {
            try {
                // Wait for 1 second
                Thread.sleep(1000);
                // Create another copy of the memoryList
                ArrayList<MemoryObject> copy2 = new ArrayList<>(copy);
                // Call the clearPair method on the controller with the copy of the memoryList
                controller.clearPair(copy2);
            } catch (InterruptedException e) {e.printStackTrace();}
        });
    }

    @Override
    public void setWorldScore() {

        ArrayList<String> lista = new ArrayList<>();
        lista.add("Eetu Soronen 500");
        lista.add("Hasan Safradi 700");
        lista.add("Samu Oksala 600");

        controller.setWorldScore(lista);

    }

    @Override
    public void setPersonalScore() {

    }

    //check if the two cards are the same.
    @Override
    public CompareResultType compareObjects(ArrayList<MemoryObject> objectList){

        /*
        for(MemoryObject obj: objectList) {
            if(this.type.equals(obj.getTypeId())){
                return type2.EQUAL;
            }
        }
         */

        if(Objects.equals(objectList.get(0).getTypeId(), objectList.get(1).getTypeId())) {

            System.out.println("Jee jee");
            setWorldScore();

            return type2.EQUAL;

        }else{

            clearPair(objectList);


            return type2.NOTEQUAL;


        }


    }
    public String toString() {

        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }


}
