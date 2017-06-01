package command;

import command.constant.MessageType;

/**
 * Created by landschoot on 03/05/17.
 * Permet de se connecter en mode passif
 */
public class EpsvCommand extends CommandConnected {
    @Override
    protected void exec() throws Exception {
        int port = clientSession.getServerSocketPassif().getLocalPort();
        clientSession.sendMessage(MessageType.MSG_229.replace("port", String.valueOf(port)));
        clientSession.setSocketPassif(clientSession.getServerSocketPassif().accept());
    }
}
