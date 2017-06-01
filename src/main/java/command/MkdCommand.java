package command;

import command.constant.MessageType;

import java.io.File;

/**
 * Created by landschoot on 04/05/17.
 * Permet de créer un nouveau dossier
 */
public class MkdCommand extends CommandConnected {
    @Override
    protected void exec() throws Exception {
        File file = new File(clientSession.getAbsoluePath()+ clientSession.getDirectory() + request.getArgument());
        file.mkdir();
        clientSession.sendMessage(MessageType.MSG_257.replace("directory",file.getAbsolutePath()));
    }
}
