package command;

import command.constant.MessageType;

import java.io.File;

/**
 * Created by landschoot on 04/05/17.
 * Permet de supprimer un dossier
 */
public class RmdCommand extends CommandConnected {
    @Override
    protected void exec() throws Exception {
        File file = new File(clientSession.getAbsoluePath()+ clientSession.getDirectory() + request.getArgument());
        file.delete();
        clientSession.sendMessage(MessageType.MSG_226);
    }
}
