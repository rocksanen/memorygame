package model;

import controller.IGameController;

import java.util.TimerTask;

/**
 * The timer class. Starts a new thread for every timer.
 */

public class Timer1 extends TimerTask {

    public int i = 10000;
    private final IGameController controller;
    /**
     * Constructs a Timer1 object with the specified game controller.
     *
     * @param controller the game controller that is associated with this timer.
     */

    public Timer1(IGameController controller) {
        this.controller = controller;
    }

    /**
     * Gets the current value of the timer.
     *
     * @return the current value of the timer.
     */
    public int getI() {
        return i;
    }

    /**
     * Decrements the timer value by one and updates the game controller.
     * If the timer reaches zero, the game over method is called on the controller.
     */
    public void run() {
        i--;
        controller.setTimer(i);
        if (i <= 0) {
            cancel();
            controller.gameOver(false);
        }
    }
}
