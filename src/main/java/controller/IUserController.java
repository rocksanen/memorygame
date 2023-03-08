package controller;

public interface IUserController {

    boolean login(String username, String password);

    boolean register(String username, String password);

    void logout();

    boolean isLoggedIn();

    String getUsername();

}
