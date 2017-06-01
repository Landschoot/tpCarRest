package command;

import command.constant.MessageType;

/**
 * Created by landschoot on 04/05/17.
 * Permet de déconnecter l'utilisateur
 */
public class QuitCommand extends Command {
    @Override
    protected void process() throws Exception {
        clientSession.setProcess(false);
        clientSession.sendMessage(MessageType.MSG_221);
    }
}
