package db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landschoot on 27/04/17.
 * Classe qui simule la base de données des utilisateurs de l'application
 */
public class UserDB {
    public List<User> usersList = new ArrayList<>();
    public User anonymousUser = new User("anonymous","");

    public UserDB() {
        populateDB();
    }

    private void populateDB(){
        usersList.add(new User("user1", "password"));
        usersList.add(new User("user2", "password"));
        usersList.add(new User("user3", "password"));
    }
}