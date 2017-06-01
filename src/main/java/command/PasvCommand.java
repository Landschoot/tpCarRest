package command;

import command.constant.MessageType;

/**
 * Created by landschoot on 03/05/17.
 * Permet de se connecter en mode passif
 */
public class PasvCommand extends CommandConnected {
    @Override
    protected void exec() throws Exception {
        int port = clientSession.getServerSocketPassif().getLocalPort();
        clientSession.sendMessage(MessageType.MSG_227.replace("port1", String.valueOf(port/256)).replace("port2",String.valueOf(port%256)));
        clientSession.setSocketPassif(clientSession.getServerSocketPassif().accept());
    }
}
