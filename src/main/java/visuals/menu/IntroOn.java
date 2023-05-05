package visuals.menu;

/**

 The IntroOn class represents a singleton instance used to control whether the intro is on or off.
 */
public class IntroOn {

    /**

     The singleton instance of the IntroOn class.
     */
    private static IntroOn instance;

    /**

     A boolean indicating whether the intro is on or off.
     */
    private boolean on = true;

    /**

     Returns the singleton instance of the IntroOn class.

     @return the singleton instance of the IntroOn class.
     */
    public static IntroOn getInstance() {

        if(instance == null) {

            instance = new IntroOn();
        }

        return instance;
    }

    /**

     Returns whether the intro is currently on or off.
     @return true if the intro is on, false if the intro is off.
     */
    public boolean getIntroOn() {

        return on;
    }

    /**

     Sets the intro to be off.
     */
    public void setIntroOff() {
        on = false;
    }

}