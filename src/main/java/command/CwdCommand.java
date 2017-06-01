package command;

import command.constant.MessageType;
import db.User;

/**
 * Created by landschoot on 02/05/17.
 * Permet de changer de repertoire
 */
public class CwdCommand extends CommandConnected {
    public static final String SEPARATOR = "/";
    public static final String PARENT = "..";
    public static final String CURRENT = ".";
    public static final String RACINE = "/";

    @Override
    protected void exec() throws Exception {
        User user = clientSession.getUser();
        String directory = clientSession.getDirectory();
        if(user.isAnonymous()) {
            clientSession.sendMessage(MessageType.MSG_200.replace("DIRECTORY", directory));

        }
        if(PARENT.equals(request.getArgument())) {
            if(!SEPARATOR.equals(directory)) {
                String[] directories = directory.split(SEPARATOR);
                directory = RACINE;
                for(int i=1; i<directories.length-1; i++) {
                    directory += directories[i] + SEPARATOR;
                }
            }
        } else if(!CURRENT.equals(request.getArgument())) {
            if(request.getArgument().startsWith(RACINE)) {
                if(!request.hasNotArgument()) {
                    directory = request.getArgument() + SEPARATOR;
                }
            } else {
                if(RACINE.equals(directory)) {
                    if(!request.hasNotArgument()) {
                        directory += request.getArgument() + SEPARATOR;
                    }
                } else {
                    if(!request.hasNotArgument()) {
                        directory += RACINE + request.getArgument() + SEPARATOR;
                    }
                }
            }
        }
        clientSession.setDirectory(directory);
        clientSession.sendMessage(MessageType.MSG_200.replace("directory", directory));
    }
}
