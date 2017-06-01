package command;

import command.constant.MessageType;

import java.io.*;

/**
 * Created by landschoot on 03/05/17.
 * Permet de recevoir un fichier
 */
public class StorCommand extends CommandTransfert {
    @Override
    protected void execTransfert() throws Exception {
        InputStream dtpInputStream = socketPassif.getInputStream();

        File file = new File(clientSession.getAbsoluePath()+ clientSession.getDirectory() + request.getArgument());

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        byte[] buffer = new byte[socketPassif.getReceiveBufferSize()];
        int bytesRead = 0;

        while ((bytesRead = dtpInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();
        fileOutputStream.flush();
        clientSession.sendMessage(MessageType.MSG_226);
    }
}
