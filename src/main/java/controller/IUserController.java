package controller;

public interface IUserController {

    /**
     * returns the score for the next wrong guess
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user was successfully logged in
     */
    boolean login(String username, String password);

    /**
     * registers a new user
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return true if the user was successfully registered
     */
    boolean register(String username, String password);

    /**
     * logs out the user if logged in
     */
    void logout();

    /**
     * returns true if the user is logged in
     *
     * @return true if the user is logged in
     */
    boolean isLoggedIn();

    /**
     * returns the username of the logged-in user
     *
     * @return the username of the logged-in user
     */
    String getUsername();

}
