package model;

import controller.IGameController;

import java.util.TimerTask;

public class Timer1 extends TimerTask {

    private final IGameController controller;
    public Timer1(IGameController controller) {
        this.controller = controller;
    }
    public int getI() {
        return i;
    }

    public int i = 100;

    public void run() {
        i--;
        controller.setTimer(i);
        //System.out.println(i);
        if (i <= 0) {
            cancel();
        }
    }
}
