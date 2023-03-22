package visuals.menu;

public class IntroOn {

    private static IntroOn instance;
    private boolean on = true;

    public static IntroOn getInstance() {

        if(instance == null) {

            instance = new IntroOn();
        }

        return instance;
    }

    public boolean getIntroOn() {

        return on;
    }

    public void setIntroOff() {
        on = false;
    }

}
