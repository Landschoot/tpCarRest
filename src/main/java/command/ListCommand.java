package command;

import command.constant.MessageType;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

/**
 * Created by landschoot on 03/05/17.
 * Permet de lister les fichiers du dossier
 */
public class ListCommand extends CommandTransfert {
    @Override
    protected void execTransfert() throws Exception {
        String directoryName = getDirectoryName();

        DataOutputStream dtpDataOutputStream = new DataOutputStream(socketPassif.getOutputStream());
        dtpDataOutputStream.writeBytes(createList(directoryName));
        dtpDataOutputStream.flush();

        clientSession.sendMessage(MessageType.MSG_226);
    }

    /**
     * Permet de récupérer le répertoire
     * @return
     */
    private String getDirectoryName() {
        String toReturn;
        if(clientSession.getUser().isAnonymous()) {
            toReturn = clientSession.getDirectory();
        } else {
            if (!request.hasNotArgument()) {
                toReturn = clientSession.getDirectory();
            } else {
                toReturn = request.getArgument();
            }
        }
        return clientSession.getAbsoluePath()+toReturn;
    }

    /**
     * Permet de créer la liste des fichiers dans le répertoire
     * @param dir
     * @return
     */
    private String createList(final String dir) {
        String result = "";
        final File folder = new File(dir);
        if (!folder.exists() || !folder.isDirectory()) {
            return "not good";
        }
        final File[] files = folder.listFiles();
        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:ss", Locale.ENGLISH);
        for (int i = 0; i < files.length; i++) {
            final File file = files[i];
            result += (file.isDirectory() ? "d" : "-");

            String owner = "unknown";
            String group = "group";
            String permissionsString = (file.isDirectory() ? "d" : "-")
            + (file.canRead() ? 'r' : '-')
            + (file.canWrite() ? 'w' : '-')
            + (file.canExecute() ? 'x' : '-') + "r--r--";
            try {
                final PosixFileAttributes attr = Files.readAttributes(Paths.get(file.getAbsolutePath()), PosixFileAttributes.class);
                group = attr.group().getName();
                owner = attr.owner().getName();
                final Set<PosixFilePermission> permissions = attr.permissions();
                permissionsString = (permissions.contains(PosixFilePermission.OWNER_READ) ? "r" : "-")
                + (permissions.contains(PosixFilePermission.OWNER_WRITE) ? "w" : "-")
                + (permissions.contains(PosixFilePermission.OWNER_EXECUTE) ? "x" : "-")
                + (permissions.contains(PosixFilePermission.GROUP_READ) ? "r" : "-")
                + (permissions.contains(PosixFilePermission.GROUP_WRITE) ? "w" : "-")
                + (permissions.contains(PosixFilePermission.GROUP_EXECUTE) ? "x" : "-")
                + (permissions.contains(PosixFilePermission.OTHERS_READ) ? "r" : "-")
                + (permissions.contains(PosixFilePermission.OTHERS_WRITE) ? "w" : "-")
                + (permissions.contains(PosixFilePermission.OTHERS_EXECUTE) ? "x" : "-");
            } catch (final InvalidPathException e) {
            } catch (final IOException e2) {
            }

            result += permissionsString + " 1 " + owner + " " + group + " " + file.length() + " " + sdf.format(file.lastModified()) + " " + file.getName() + "\r\n";
        }
        return result;
    }

}
