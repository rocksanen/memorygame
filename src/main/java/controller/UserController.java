package controller;

import model.User;

/**
 * The controller for the user class in model
 */
// this could easily be static, as most of these controllers, but that's a lot of refactoring for little benefit
public class UserController implements IUserController {

    /**
     * constructor
     */
    public UserController() { }

    /**
     * returns the score for the next wrong guess
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user was successfully logged in
     */
    @Override
    public boolean login(String username, String password) {
        User user = User.getInstance();
        return user.login(username, password);
    }

    /**
     * registers a new user
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return true if the user was successfully registered
     */
    @Override
    public boolean register(String username, String password) {
        User user = User.getInstance();
        return user.signup(username, password);
    }

    /**
     * logs out the user if logged in
     */
    @Override
    public void logout() {
        User user = User.getInstance();
        user.logout();
    }

    /**
     * returns true if the user is logged in
     *
     * @return true if the user is logged in
     */
    @Override
    public boolean isLoggedIn() {
        User user = User.getInstance();
        return user.isLoggedIn();
    }

    /**
     * returns the username of the logged in user
     *
     * @return the username of the logged in user
     */
    @Override
    public String getUsername() {
        User user = User.getInstance();
        return user.getUsername();
    }
}
