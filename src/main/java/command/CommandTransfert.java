package command;

import command.constant.MessageType;

import java.net.Socket;

/**
 * Created by landschoot on 03/05/17.
 * Classe qui représente une commande de transfert
 */
public abstract class CommandTransfert extends CommandConnected {
    Socket socketPassif;

    @Override
    protected void exec() throws Exception {
        socketPassif = clientSession.getSocketPassif();
        clientSession.sendMessage(MessageType.MSG_125);
        execTransfert();
        socketPassif.close();
    }

    protected abstract void execTransfert() throws Exception;
}
