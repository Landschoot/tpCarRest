package command;

import command.constant.MessageType;
import db.User;
import db.UserService;

/**
 * Created by landschoot on 27/04/17.
 * Permet d'authentifier le login
 */
public class UserCommand extends Command {
    UserService userService = UserService.getInstance();

    @Override
    public void process() throws Exception {
        User user = clientSession.getUser();
        if(userService.getAnonymousUser().getLogin().equals(request.getArgument())) {
            user.setConnected(true);
            user.setAnonymous(true);
            user.setLogin(userService.getAnonymousUser().getLogin());
            clientSession.setDirectory(clientSession.getDirectory());
            clientSession.sendMessage(MessageType.MSG_230);
        }
        else if(userService.userExist(request.getArgument())) {
            user.setLogin(userService.getUserByLogin(request.getArgument()).getLogin());
            clientSession.setDirectory(clientSession.getDirectory());
            clientSession.sendMessage(MessageType.MSG_331);
        } else {
            clientSession.sendMessage(MessageType.MSG_332);
        }
    }
}
