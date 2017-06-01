package command;

import command.constant.MessageType;
import db.User;
import db.UserService;

/**
 * Created by landschoot on 27/04/17.
 * Permet d'authentifier le mot de passe
 */
public class PassCommand extends Command {
    UserService userService = UserService.getInstance();

    @Override
    public void process() throws Exception {
        User user = clientSession.getUser();
        User principal = userService.getUserByLogin(user.getLogin());
        if(principal.getPassword().equals(request.getArgument())) {
            user.setAnonymous(false);
            user.setConnected(true);
            clientSession.sendMessage(MessageType.MSG_230);
        }  else {
            clientSession.sendMessage(MessageType.MSG_332);
        }
    }
}
