package controller;


public interface IControllerVtoE {
    void startEasyGame();
    void startMediumGame();
    void startHardGame();
    void sendIdToEngine(int id);
    void clearStorage();
    void sendReturnSignal();
    void killTimer();

    boolean login(String username, String password);

    boolean register(String username, String password);

    void logout();

    boolean isLoggedIn();

    String getUsername();
}