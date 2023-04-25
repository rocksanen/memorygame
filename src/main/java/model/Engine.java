package model;

import controller.IGameController;

import java.util.*;
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

    ArrayList<Integer> correctIds = new ArrayList<>();
    ArrayList<Integer> correctIdsIds = new ArrayList<>();
    private final IGameController controller;
    private final ModeType type;

    private boolean isTimerRunning = false;

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

    private int hint;
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
     * The number of incorrect tries. Resets when a correct guess is made.
     */
    int incorrectTries = 0;

    private long timerTime = 1000;

    private long lastCorrectGuess;


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
        lastCorrectGuess = startTime;
    }

    @Override
    public void setMemoryObjects() {

        switch (this.type) {
            case EASY -> {
                addMemoryObjectsToList(6);
                suffleObjects();
                controller.setGame(memoryObjectsList);
                timerTime = 1000;
                runTimer();
            }
            case MEDIUM -> {

                addMemoryObjectsToList(12);
                suffleObjects();
                controller.setGame(memoryObjectsList);
                timerTime = 600;
                runTimer();
            }

            case HARD -> {

                addMemoryObjectsToList(20);
                suffleObjects();
                controller.setGame(memoryObjectsList);
                timerTime = 600;
                runTimer();
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

        for (int i = 0; i < memoryObjectsList.size(); i++) {
            correctIds.add(memoryObjectsList.get(i).getTypeId());
            correctIdsIds.add(memoryObjectsList.get(i).getIdNumber());
        }

    }

    @Override
    public void setChosenObjectReady(MemoryObject object) {
        object.setActive();
    }

    @Override
    public void addToComparing(int i) {

//        System.out.println("tänne meni");
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
        controller.gameOver();
        rightPairList.clear();
        System.out.println("Game ended!");
        setPersonalScore();
        stopTimer();
    }

    public void stopTimer() {


        if (t != null) {

            t.cancel();
        }

    }

    @Override
    public int getHint() {
        return hint;
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

        // user.addScore(finalTime, totalScore, type);
        CompletableFuture.runAsync(() -> {
            user.addScore(finalTime, totalScore, type);
        });

    }


    // was private void, changed
    public void updateScore(CompareResultType type) {
        switch (type) {
            case EQUAL -> {
                totalScore += Grader.calculatePoints(
                        incorrectTries, lastCorrectGuess - System.currentTimeMillis());
                incorrectTries = 0;
                lastCorrectGuess = System.currentTimeMillis();
            }
            case NOTEQUAL -> {
                incorrectTries++;
            }
        }
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


    @Override
    public void clearStorage() {
        storage.clear();
    }


    private int wrong_guesses = 0;
    private int firstIncorrectGuessIndex = -1;
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
                endGame();

            }
        } else {
            wrong_guesses++;

            if (wrong_guesses == 2 && getType().equals(ModeType.HARD)) {
                int idToMatch = objectList.get(0).getTypeId();
                int idToMatch2 = objectList.get(0).getIdNumber();
                System.out.println(idToMatch);
                System.out.println(memoryObjectsList.size());
                for (int i = 0; i < memoryObjectsList.size(); i++) {

                    if (idToMatch == correctIds.get(i) && idToMatch2 != correctIdsIds.get(i)) {
                        hint = i;
                        controller.showHint();
                        System.out.println("Position: " + i);
                        wrong_guesses = 0;
                        //break;
                    }


                }
            }

            clearPair(objectList);
            updateScore(NOTEQUAL);
        }
    }

    public void runTimer() {
        t.schedule(task, 0, timerTime);
    }


    @Override
    public ModeType getType() {
        return type;
    }

    public String toString() {
        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }
}
