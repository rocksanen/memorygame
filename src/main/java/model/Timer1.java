package model;

import java.util.TimerTask;

public class Timer1 extends TimerTask {
    public int i = 100;

    public void run() {
        System.out.println("Timer " + i);
        i--;

        if (i <= 0) {
            cancel();
        }
    }

    private int getTime() {
        return i;
    }
}
