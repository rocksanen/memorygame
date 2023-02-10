package controller;

public interface IControllerVtoE {
    void startEasyGame();
    void startMediumGame();
    void startHardGame();
    void sendIdToEngine(int id);
    void clearStorage();
}