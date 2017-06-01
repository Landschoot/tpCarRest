package db;

/**
 * Created by landschoot on 27/04/17.
 * Classe qui correspond à un utilisateur
 */
public class User {
    private boolean connected;
    private String login;
    private String password;
    private boolean anonymous;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
