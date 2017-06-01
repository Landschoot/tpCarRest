package clientSession;

import command.Command;
import command.constant.MessageType;
import db.User;
import request.Request;
import server.ServerConfig;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by landschoot on 27/04/17.
 * Classe qui représente la session d'un client connecté au serveur
 */
public class ClientSession implements Runnable {
    /**
     * Instance du serveur
     */
    private ServerSocket serverSocketPassif;
    /**
     * Chemin du repertoire du serveur ftp.
     */
    private String directory;
    /**
     * Le socket de l'utilisateur servant à traiter les requêtes
     */
    private Socket socket;
    /**
     * Le socket de l'utilisateur servant à transferer des fichiers
     */
    private Socket socketPassif;
    /**
     * Buffer permettant la recuperation de commande
     */
    private BufferedReader bufferReader;
    /**
     * Stream d'envoie de reponses sur le canal de commande
     */
    private DataOutputStream dataOutputStream;
    /**
     * Les informations de l'utilisateur
     */
    private User user;

    public boolean isProcess() {
        return process;
    }

    public void setProcess(boolean process) {
        this.process = process;
    }

    /**
     * Indique a la classe receiveRequest si elle doit s'arreter ou non.
     */
    private boolean process;

    public ClientSession(ServerSocket serverSocket, Socket socketRequest) throws IOException {
        this.serverSocketPassif = serverSocket;
        this.socket = socketRequest;
        this.bufferReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        this.directory = "/";
        this.user = new User("","");
    }

    @Override
    public void run() {
        System.out.println("[Nouveau client connecté]");
        try {
            this.process = true;
            this.sendMessage(MessageType.MSG_220);
            this.receiveRequest();
        } catch (Exception exception) {
            System.out.println("[Déconnexion du client] Error: " + exception.getMessage());
        }
    }

    /**
     * Envoie un message au client
     * @param message
     * @throws Exception
     */
    public void sendMessage(String message) throws Exception {
        System.out.println("Envoie du message : " + message);
        this.dataOutputStream.writeBytes(message);
        this.dataOutputStream.flush();
    }

    /**
     * Retourne l'url global de l'utilisateur
     * @return
     */
    public String getAbsoluePath(){
        return ServerConfig.DIRECTORIES+user.getLogin();
    }

    /**
     * Reçoit et traite les requêtes du client
     * @throws Exception
     */
    private void receiveRequest() throws Exception {
        String requestString = this.bufferReader.readLine();
        Request request = new Request(requestString);
        System.out.println("Traitement de la requête : " + requestString + " - user "+user.getLogin());
        if(Command.COMMAND_LIST.containsKey(request.getType())){
            Command command = Command.COMMAND_LIST.get(request.getType());
            command.run(this, request);
        } else {
            this.sendMessage(MessageType.MSG_502);
        }
        if(this.process) {
            this.receiveRequest();
        } else {
            this.socket.close();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Socket getSocketPassif() {
        return socketPassif;
    }

    public void setSocketPassif(Socket socketPassif) {
        this.socketPassif = socketPassif;
    }

    public ServerSocket getServerSocketPassif() {
        return serverSocketPassif;
    }
}
