package database;

import model.User;

import java.util.ArrayList;

public class DBAccessObject implements IDao {

    @Override
    public void saveUser(User user) {

    }

    @Override
    public User readUser(Integer id) {
        return null;
    }

    @Override
    public void saveScores(User user) {

    }

    @Override
    public ArrayList<Integer> readUserScores(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Integer> readWorldScores() {
        return null;
    }
}
