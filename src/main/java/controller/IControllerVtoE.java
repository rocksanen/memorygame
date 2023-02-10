package controller;

public interface IControllerVtoE {
    void startEasyGame();
    void startMediumGame();
    void startHardGame();
    void sendIdToEngine(int id);
    void clearStorage();

    boolean login(String username, String password);

    boolean register(String username, String password);
}