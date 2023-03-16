package model;

import controller.IGameController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

import static model.CompareResultType.EQUAL;
import static model.CompareResultType.NOTEQUAL;

/**
 * The type Engine.
 */
public class Engine implements IEngine {

    /**
     * The Storage.
     */
    ArrayList<Integer> storage = new ArrayList<>();
    private final IGameController controller;
    private final ModeType type;

    /**
     * Gets comparing list.
     *
     * @return the comparing list
     */

    public boolean isReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    private boolean returnStatus;

    public ArrayList<MemoryObject> getComparingList() {
        return comparingList;
    }

    private final ArrayList<MemoryObject> comparingList = new ArrayList<>();
    private ArrayList<Integer> rightPairList = new ArrayList<Integer>();
    private CompareResultType type2;

    /**
     * Gets memory objects list.
     *
     * @return the memory objects list
     */
    public ArrayList<MemoryObject> getMemoryObjectsList() {
        return memoryObjectsList;
    }

    private ArrayList<MemoryObject> memoryObjectsList;

    // private int foundPairs = 0;

    private int activeId;

    private int test;
    /**
     * logged in user
     */
    private User user = User.getInstance();

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

    private long timerTime = 1000;

    public long getTimerTime() {
        return timerTime;
    }

    Timer t;
    TimerTask task;

    /**
     * Instantiates a new Engine.
     *
     * @param type       the type
     * @param controller the controller
     */
    public Engine(ModeType type, IGameController controller) {

        this.type = type;
        this.controller = controller;
        this.t = new Timer();
        this.task = new Timer1(controller);

        // get current time
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void setMemoryObjects() {

        switch (this.type) {
            case EASY -> {
                timerTime = 1000;
                runTimer();
                controller.getTime();
                addMemoryObjectsToList(6);
                suffleObjects();
                controller.setGame(memoryObjectsList);

            }
            case MEDIUM -> {
                timerTime = 800;
                runTimer();
                controller.getTime();
                addMemoryObjectsToList(12);
                suffleObjects();
                controller.setGame(memoryObjectsList);

            }

            case HARD -> {
                timerTime = 600;
                runTimer();
                controller.getTime();
                addMemoryObjectsToList(20);
                suffleObjects();
                controller.setGame(memoryObjectsList);
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

        System.out.println("tänne meni");
        MemoryObject memoryObject = memoryObjectsList.get(i);
        controller.getActive(i);
        if (!rightPairList.contains(memoryObject.getTypeId())) {
            comparingList.add(memoryObject);
            storage.add(i);
        }

        if (comparingList.size() == 2) {
            compareObjects(comparingList);
            comparingList.clear();
        }

    }

    public int getActiveId() {
        return activeId;
    }

    public void endGame() {
        rightPairList.clear();
        System.out.println("Game ended!");
        // Make IF NOT returned
        setPersonalScore();
        stopTimer();

    }

    public void stopTimer() {
        task.cancel();
        t.cancel();
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
        double finalTime = (System.currentTimeMillis() - startTime) / 1000.0;
        // time (seconds), totalScore and difficulty
        System.out.println("Time: " + finalTime + "s, Score: " + totalScore + ", Difficulty: " + type);
        user.addScore(finalTime, totalScore, type);
    }

    /**
     * Updates the total score and the next score. In case of equal, the total score
     * is increased by the next score.
     * In case of not equal, the next score is decreased by 100 * the number of
     * incorrect tries.
     * The next score is never less than 100.
     *
     * @param type the result of the comparison, either equal or not equal.
     */
    // was private void, changed
    public void updateScore(CompareResultType type) {
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

        if (objectList.get(0).getTypeId().equals(objectList.get(1).getTypeId())
                && objectList.get(0) != objectList.get(1)) {

            rightPairList.add(objectList.get(0).getTypeId());
            rightPairList.add(objectList.get(1).getTypeId());
            updateScore(EQUAL);
            controller.sendComparingSuccess();
            clearStorage();

            if (rightPairList.size() == memoryObjectsList.size()) {
                controller.gameOver();
                endGame();

            }
        } else {
            clearPair(objectList);
            updateScore(NOTEQUAL);
        }
    }

    public void runTimer() {
        t.schedule(task, 0, timerTime);
    }
    public String toString() {
        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }
}
