package command;

import db.User;
import command.constant.MessageType;

/**
 * Created by landschoot on 29/04/17.
 * Classe qui représente une commande executable lorsqu'on est connecté
 */
public abstract class CommandConnected extends Command {
    @Override
    protected void process() throws Exception {
        User user = clientSession.getUser();
        if(!user.isConnected()) {
            clientSession.sendMessage(MessageType.MSG_530);
        }
        exec();
    }

    protected abstract void exec() throws Exception;
}
