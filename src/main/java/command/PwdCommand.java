package command;

import command.constant.MessageType;

/**
 * Created by landschoot on 29/04/17.
 * Permet de retourner le repertoire
 */
public class PwdCommand extends CommandConnected {
    @Override
    protected void exec() throws Exception {
        clientSession.sendMessage(MessageType.MSG_257.replace("directory", clientSession.getDirectory()));
    }
}
