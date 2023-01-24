package Model;

import java.util.ArrayList;

public interface IUser {

    String getName();
    ArrayList<Integer> getScores();
    void addScore(Integer score);
    Integer getUserId();


}
