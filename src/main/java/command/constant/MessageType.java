package command.constant;

/**
 * Created by landschoot on 27/04/17.
 * Classe qui représente les différents messages envoyés par le serveur
 */
public class MessageType {
    public static final String MSG_125 = "125 Data connection already open; transfer starting.\r\n";
    public static final String MSG_200 = "200 Directory changed to \"directory\"\r\n";
    public static final String MSG_215 = "215 Unix system type.\r\n";
    public static final String MSG_220 = "220 Service ready for new ClientSession.\r\n";
    public static final String MSG_221 = "221 Service closing control connection.\r\n";
    public static final String MSG_226 = "226 Closing data connection.\r\n";
    public static final String MSG_227 = "227 Entering Passive Mode (127,0,0,1,port1,port2).\r\n";
    public static final String MSG_229 = "229 Entering Extended Passive Mode (|||port|).\r\n";
    public static final String MSG_230 = "230 User logged in, proceed.\r\n";
    public static final String MSG_257 = "257 \"directory\"\r\n";
    public static final String MSG_331 = "331 User name okay, need password.\r\n";
    public static final String MSG_332 = "332 Need account for login.\r\n";
    public static final String MSG_502 = "502 Command not implemented.\r\n";
    public static final String MSG_530 = "530 Not logged in.\r\n";
    public static final String MSG_550 = "550 Directory already exists";
}