package Model;

import java.util.ArrayList;

public class User implements IUser{

    private final String name;
    private final Integer userId;
    private final ArrayList<Integer> scoreList;

    public User(String name, Integer userId, ArrayList<Integer> scoreList) {

        this.name = name;
        this.userId = userId;
        this.scoreList = scoreList;

    }
    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public ArrayList<Integer> getScores() {

        return scoreList;
    }

    @Override
    public void addScore(Integer score) {

        scoreList.add(score);
    }

    @Override
    public Integer getUserId() {

        return this.userId;
    }

    public String toString() {

        return "Nimi: " + this.name + ", Käyttäjätunnus: " + this.userId + ", listalla tuloksia: " + scoreList.size();
    }
}
