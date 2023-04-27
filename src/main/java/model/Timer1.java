package model;

import controller.IGameController;

import java.util.TimerTask;

public class Timer1 extends TimerTask {

    public int i = 10000;
    private final IGameController controller;
    public Timer1(IGameController controller) {
        this.controller = controller;
    }
    public int getI() {
        return i;
    }

    public void run() {
        i--;
        controller.setTimer(i);
        if (i <= 0) {
            cancel();
            controller.gameOver();
        }
    }
}
