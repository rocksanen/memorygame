package model;

import controller.IControllerEtoV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static model.CompareResultType.EQUAL;
import static model.CompareResultType.NOTEQUAL;
import static model.ModeType.EASY;


public class Engine implements IEngine {

    ArrayList<Integer> storage = new ArrayList<>();
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

    /**
     * The number of incorrect tries. Resets when a correct guess is made.
     */
    int incorrectTries = 0;


    public Engine(ModeType type, IControllerEtoV controller) {
        this.type = type;
        this.controller = controller;

        // get current time
        this.startTime = System.currentTimeMillis();


        // temp mock data
//        User u = User.getInstance();
//        u.signup("tony", "tiger");
//        u.login("tony", "tiger");
//        this.user = u;
//        u.addScore(Math.random() * 100, (int) (Math.random() * 70 + 50), EASY);
//        u.addScore(Math.random() * 100, (int) (Math.random() * 70 + 50), EASY);
//
//
//        u = User.getInstance();
//        u.signup("eetu", "soro");
//        u.login("eetu", "soro");
//        this.user = u;
//        u.addScore(Math.random() * 100, (int) (Math.random() * 70 + 50), EASY);
    }

    @Override
    public void setMemoryObjects() {

        switch (this.type) {
            case EASY -> {
                addMemoryObjectsToList(6);
                suffleObjects();
                controller.setEasyGame(memoryObjectsList);
            }
            case MEDIUM -> {
                addMemoryObjectsToList(12);
                suffleObjects();
                controller.setMediumGame(memoryObjectsList);

            }
            case HARD -> {
                addMemoryObjectsToList(20);
                suffleObjects();
                controller.setHardGame(memoryObjectsList);
            }
        }
    }

    @Override
    public void addMemoryObjectsToList(Integer amount) {

        this.memoryObjectsList = new ArrayList<>();
        int type;
        for (int i = 0; i < amount; i++) {

            type = i / 2;
            memoryObjectsList.add(new MemoryObject(i, type));
        }
    }

    @Override
    public void suffleObjects() {
        Collections.shuffle(memoryObjectsList);
    }

    @Override
    public void setChosenObjectReady(MemoryObject object) {
        object.setActive();
    }

    @Override
    public void addToComparing(int i) {

        MemoryObject memoryObject = memoryObjectsList.get(i);
        comparingList.add(memoryObject);
        storage.add(i);

        if (comparingList.size() == 2) {

            compareObjects(comparingList);
            comparingList.clear();
        }
    }

    @Override
    public void clearPair(ArrayList<MemoryObject> memoryList) {

        synchronized (this) {

            CompletableFuture.runAsync(() -> {
                try {

                    Thread.sleep(1000);
                    controller.clearPair(storage);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
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
     * In case of not equal, the next score is decreased by 100 * the number of incorrect tries.
     * The next score is never less than 100.
     *
     * @param type the result of the comparison, either equal or not equal.
     */
    private void updateScore(CompareResultType type) {
        switch (type) {
            case EQUAL -> {
                totalScore += nextScore;
                nextScore = 1000;
                incorrectTries = 0;
            }
            case NOTEQUAL -> {
                incorrectTries++;
                if (incorrectTries < 5) {
                    nextScore -= 100 * incorrectTries;
                }
                if (nextScore < 100) {
                    nextScore = 100;
                }
            }
        }
        System.out.println("Total score: " + totalScore);
        System.out.println("Incorrect tries: " + incorrectTries);
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
     *
     * @return see {@link #nextScore}
     */
    @Override
    public int getNextScore() {
        return nextScore;
    }

    @Override
    public void clearStorage() {
        storage.clear();
    }

    @Override
    public void compareObjects(ArrayList<MemoryObject> objectList) {

        if (objectList.get(0).getTypeId().equals(objectList.get(1).getTypeId())) {

            updateScore(EQUAL);
            clearStorage();

        } else {

            clearPair(objectList);
            updateScore(NOTEQUAL);
        }
    }

    public String toString() {
        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }
}
