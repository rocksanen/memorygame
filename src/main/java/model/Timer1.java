package model;

import controller.IControllerEtoV;

import java.util.TimerTask;

public class Timer1 extends TimerTask {

    private IControllerEtoV controller;
    public Timer1(IControllerEtoV controller) {
        this.controller = controller;
    }
    public int getI() {
        return i;
    }

    public int i = 100;

    public void run() {
        i--;
        controller.setTimer(i);
        if (i <= 0) {
            cancel();
        }
    }
}
