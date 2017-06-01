package command;

import command.constant.MessageType;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by landschoot on 03/05/17.
 * Permet d'envoyer un fichier
 */
public class RetrCommand extends CommandTransfert {
    @Override
    protected void execTransfert() throws Exception {
        DataOutputStream dtpDataOutputStream = new DataOutputStream(socketPassif.getOutputStream());
        File file = new File(clientSession.getAbsoluePath()+ clientSession.getDirectory() + request.getArgument());

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[socketPassif.getSendBufferSize()];
        int bytesRead = 0;

        while((bytesRead = fileInputStream.read(buffer))>0)
        {
            dtpDataOutputStream.write(buffer,0,bytesRead);
        }

        fileInputStream.close();
        dtpDataOutputStream.flush();
        clientSession.sendMessage(MessageType.MSG_226);
    }
}
