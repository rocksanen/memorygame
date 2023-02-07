package model;

import controller.IControllerEtoV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static model.CompareResultType.EQUAL;
import static model.CompareResultType.NOTEQUAL;


public class Engine implements IEngine {

    private final IControllerEtoV controller;
    private final ModeType type;
    private final ArrayList<MemoryObject> comparingList = new ArrayList<>();
    private CompareResultType type2;
    private ArrayList<MemoryObject> memoryObjectsList;
    /**
     * logged in user
     */
    private User user;

    /**
     * The time when the game started / engine created (in milliseconds).
     */
    private final long startTime;

    /**
     * The points received for each correct guess,
     * decreased by 100 by each wrong guess.
     */
    private int totalScore = 0;

    /**
     * The points given for the next correct guess.
     */
    private int nextScore = 1000;

    public Engine(ModeType type, IControllerEtoV controller) {
        this.type = type;
        this.controller = controller;
        // this will be replaced by a login method SOMEDAY
        this.user = User.getInstance();
        user.login("eetu");

        // get current time
        this.startTime = System.currentTimeMillis();

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
        for (int i = 0; i < amount; i++) {
            // Calculate the type of each MemoryObject
            type = i / 2;
            // Add a new MemoryObject to the memoryObjectsList
            memoryObjectsList.add(new MemoryObject(i, type));
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
    public void addToComparing(int i) {
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
        if (comparingList.size() == 2) {
            compareObjects(comparingList);
            // Clear the comparingList after comparison
            comparingList.clear();
        }
    }

    @Override
    public void clearPair(ArrayList<MemoryObject> memoryList) {
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * Called by a method that ends the game. Saves the score to the leaderboard.
     * Only if logged in.
     */
    @Override
    public void setPersonalScore() {
        if (user == null) {
            return;
        }
        // get current time and detract the start time
        double finalTime = (System.currentTimeMillis() - startTime) / 1000;
        // time (seconds), totalScore and difficulty
        user.addScore(finalTime, totalScore, type);
    }

    /**
     * Updates the total score and the next score. In case of equal, the total score is increased by the next score.
     * In case of not equal, the next score is decreased by 100.
     *
     * @param type the result of the comparison, either equal or not equal.
     */
    private void updateScore(CompareResultType type) {
        switch (type) {
            case EQUAL -> {
                totalScore += nextScore;
                nextScore = 1000;
            }
            case NOTEQUAL -> {
                if (nextScore > 100) {
                    nextScore -= 100;

                }
            }
        }
        System.out.println("Total score: " + totalScore);
        System.out.println("Next score: " + nextScore);
    }

    /**
     * Getter for the total score.
     *
     * @return see {@link #totalScore}
     */
    @Override
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Getter for the next score.
     * @return see {@link #nextScore}
     */
    @Override
    public int getNextScore() {
        return nextScore;
    }

    //check if the two cards are the same.
    @Override
    public CompareResultType compareObjects(ArrayList<MemoryObject> objectList) {

        /*
        for(MemoryObject obj: objectList) {
            if(this.type.equals(obj.getTypeId())){
                return type2.EQUAL;
            }
        }
         */

        if (Objects.equals(objectList.get(0).getTypeId(), objectList.get(1).getTypeId())) {
            System.out.println("Jee jee");
            //update score
            updateScore(EQUAL);
            return EQUAL;
        } else {
            clearPair(objectList);
            updateScore(NOTEQUAL);
            return NOTEQUAL;
        }
    }

    public String toString() {
        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }
}
