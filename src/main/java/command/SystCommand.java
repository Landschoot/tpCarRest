package command;

import command.constant.MessageType;

/**
 * Created by landschoot on 03/05/17.
 * Permet d'indiquer le système
 */
public class SystCommand extends CommandConnected {
    @Override
    protected void exec() throws Exception {
        clientSession.sendMessage(MessageType.MSG_215);
    }
}
