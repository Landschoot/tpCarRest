package command;

import clientSession.ClientSession;
import request.Request;
import request.constant.RequestType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by landschoot on 27/04/17.
 * Classe qui représente une commande executable
 */
public abstract class Command {
    public static Map<String, Command> COMMAND_LIST = new HashMap<>();
    static {
        COMMAND_LIST.put(RequestType.USER_TYPE_REQUEST, new UserCommand());
        COMMAND_LIST.put(RequestType.PASS_TYPE_REQUEST, new PassCommand());
        COMMAND_LIST.put(RequestType.SYST_TYPE_REQUEST, new SystCommand());
        COMMAND_LIST.put(RequestType.PWD_TYPE_REQUEST, new PwdCommand());
        COMMAND_LIST.put(RequestType.CWD_TYPE_REQUEST, new CwdCommand());
        COMMAND_LIST.put(RequestType.PASV_TYPE_REQUEST, new PasvCommand());
        COMMAND_LIST.put(RequestType.EPSV_TYPE_REQUEST, new EpsvCommand());
        COMMAND_LIST.put(RequestType.LIST_TYPE_REQUEST, new ListCommand());
        COMMAND_LIST.put(RequestType.TYPE_TYPE_REQUEST, new TypeCommand());
        COMMAND_LIST.put(RequestType.RETR_TYPE_REQUEST, new RetrCommand());
        COMMAND_LIST.put(RequestType.STOR_TYPE_REQUEST, new StorCommand());
        COMMAND_LIST.put(RequestType.MKD_TYPE_REQUEST, new MkdCommand());
        COMMAND_LIST.put(RequestType.RMD_TYPE_REQUEST, new RmdCommand());
        COMMAND_LIST.put(RequestType.DELE_TYPE_REQUEST, new DeleCommand());
        COMMAND_LIST.put(RequestType.QUIT_TYPE_REQUEST, new QuitCommand());
    }

    protected ClientSession clientSession;
    protected Request request;

    public void run(ClientSession clientSession, Request request) throws Exception {
        this.clientSession = clientSession;
        this.request = request;
        process();
    }

    protected abstract void process() throws Exception;
}
