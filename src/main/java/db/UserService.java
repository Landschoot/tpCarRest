package db;

import db.exception.UserNotFoundException;

import java.util.List;

/**
 * Created by landschoot on 27/04/17.
 * Service qui permet de gérer les utilisateurs
 */
public class UserService {
    private UserDB userDB = new UserDB();

    private static UserService instance = null;

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    private UserService(){}

    /**
     * Retourne l'utilisateur anonyme
     * @return
     */
    public User getAnonymousUser(){
        return userDB.anonymousUser;
    }

    /**
     * Indique si l'utilsateur existe
     * @param login
     * @return
     */
    public boolean userExist(String login){
        for(User user : userDB.usersList){
            if(login.equals(user.getLogin())){
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne un utilisateur correspondant au login
     * @param login
     * @return
     * @throws UserNotFoundException
     */
    public User getUserByLogin(String login) throws UserNotFoundException {
        for(User user : userDB.usersList){
            if(login.equals(user.getLogin())){
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    /**
     * Retourne l'ensemble des utilisateurs
     * @return
     */
    public List<User> getUsers() {
        return userDB.usersList;
    }
}
