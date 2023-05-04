package model;

import controller.IGameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static model.CompareResultType.EQUAL;
import static model.CompareResultType.NOTEQUAL;

/**
 * The {@code Engine} class represents the game engine that manages the memory game logic.
 */
public class Engine implements IEngine {

    /**
     * The Storage.
     */
    final ArrayList<Integer> storage = new ArrayList<>();

    final ArrayList<Integer> correctIds = new ArrayList<>();
    final ArrayList<Integer> correctIdsIds = new ArrayList<>();
    private final IGameController controller;
    private final ModeType type;

    private final ArrayList<MemoryObject> comparingList = new ArrayList<>();
    private final ArrayList<Integer> rightPairList = new ArrayList<>();

    /**
     * Gets memory objects list.
     *
     * @return the memory objects list
     */
    public ArrayList<MemoryObject> getMemoryObjectsList() {
        return memoryObjectsList;
    }

    private ArrayList<MemoryObject> memoryObjectsList;


    private int hint;
    /**
     * logged in user
     */
    private final User user = User.getInstance();

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

    final Timer t;
    final TimerTask task;

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

    /**
     * A method to set the memory objects in the engine for each game mode. Objects are set to a list, then shuffled and then a game is started
     * A timer time is given for the medium and hard modes
     */
    @Override
    public void setMemoryObjects() {

        switch (this.type) {
            case EASY -> {

                addMemoryObjectsToList(6);
                suffleObjects();
                controller.setGame(memoryObjectsList);
            }
            case MEDIUM -> {

                addMemoryObjectsToList(12);
                suffleObjects();
                controller.setGame(memoryObjectsList);
                timerTime = 6;
            }

            case HARD -> {

                addMemoryObjectsToList(20);
                suffleObjects();
                controller.setGame(memoryObjectsList);
                timerTime = 6;
            }
        }
    }

    /**
     * Starts the timer
     */
    @Override
    public void startTime() {

        runTimer();
    }

    /**
     * Adds memory objects to a list
     * @param amount - The amount of memory objects to be added
     */
    @Override
    public void addMemoryObjectsToList(Integer amount) {

        this.memoryObjectsList = new ArrayList<>();
        int type;
        for (int i = 0; i < amount; i++) {

            type = i / 2;
            memoryObjectsList.add(new MemoryObject(i, type));
        }
    }

    /**
     * Shuffles the objects to randomize the game
     */
    @Override
    public void suffleObjects() {
        Collections.shuffle(memoryObjectsList);

        for (MemoryObject memoryObject : memoryObjectsList) {
            correctIds.add(memoryObject.getTypeId());
            correctIdsIds.add(memoryObject.getIdNumber());
        }

    }

    @Override
    public void setChosenObjectReady(MemoryObject object) {
        object.setActive();
    }

    /**
     * A function to compare the objects once two different objects are selected
     * @param i - the position of a memory object
     */
    @Override
    public void addToComparing(int i) {

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

    /**
     * Method to end the game
     */
    public void endGame() {

        controller.gameOver(true);
        rightPairList.clear();
        setPersonalScore();
        stopTimer();
    }

    /**
     * Method to stop the timer
     */
    public void stopTimer() {

        if (t != null) {
            t.cancel();
        }
    }

    /**
     * Method for the practice mode, to get a hint from the engine to the UI
     * @return returns the correct position of the hint
     */
    @Override
    public int getHint() {
        return hint;
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
        // user.addScore(finalTime, totalScore, type);
        CompletableFuture.runAsync(() -> user.addScore(finalTime, totalScore, type));

    }


    public void updateScore(CompareResultType type) {
        switch (type) {
            case EQUAL -> {
                totalScore += Grader.calculatePoints(
                        incorrectTries, lastCorrectGuess - System.currentTimeMillis());
                incorrectTries = 0;
                lastCorrectGuess = System.currentTimeMillis();
                updateDynamicScore(totalScore);
            }
            case NOTEQUAL -> incorrectTries++;
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


    /**
     * Clears the storage of memory objects
     */
    @Override
    public void clearStorage() {
        storage.clear();
    }

    private int wrong_guesses = 0;


    @Override
    public void clearPair(ArrayList<MemoryObject> memoryList) {


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.millis(750))
        );

        timeline.play();
        timeline.setOnFinished(actionEvent -> {

            controller.clearPair(storage);

        });
    }

    /**
     * Compares the memory objects once they are selected and added to the list
     * Checks if the two different objects are a pair or no
     * @param objectList
     */
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


                for (int i = 0; i < memoryObjectsList.size(); i++) {

                    if (idToMatch == correctIds.get(i) && idToMatch2 != correctIdsIds.get(i)) {
                        hint = i;
                        controller.showHint();
                        wrong_guesses = 0;
                        //break;
                    }
                }
            }

            clearPair(objectList);
            updateScore(NOTEQUAL);
        }
    }

    /**
     * Method to schedule & start the timer
     */
    public void runTimer() {
        t.schedule(task, 0, timerTime);
    }


    @Override
    public ModeType getType() {
        return type;
    }

    @Override
    public void updateDynamicScore(int score) {

        controller.updateDynamicScore(score);
    }

    public String toString() {
        return "Pelin tyyppi: " + this.type.toString() + ", Palikoiden määrä: " + memoryObjectsList.size();
    }
}
