import db.User;
import db.UserService;
import server.Server;
import server.ServerConfig;

import java.io.File;

/**
 * Created by landschoot on 27/04/17.
 * Main
 */
public class Application {
    /**
     * Permet d'initialiser le répertoire commun et le répertoire de chaque utilisateur pour le déroulement de l'application
     */
    public static void initDirectories(){
        File directory = new File(ServerConfig.DIRECTORIES);
        directory.mkdir();
        for(User user : UserService.getInstance().getUsers()){
            directory = new File(ServerConfig.DIRECTORIES+user.getLogin());
            directory.mkdir();
        }
    }

    public static void main(String[] args) throws Exception {
        Application.initDirectories();
        Server server = new Server();
        server.run();
    }
}
