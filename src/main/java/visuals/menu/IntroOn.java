package visuals.menu;

public record IntroOn(boolean on) {

    private static final IntroOn instance = new IntroOn(true);

    public static IntroOn getInstance() {
        return instance;
    }

    public boolean getIntroOn() {
        return on;
    }

    public void setIntroOff() {
        new IntroOn(false);
    }
}
