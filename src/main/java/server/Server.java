package server;

import clientSession.ClientSession;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by landschoot on 27/04/17.
 * Server ftp
 */
public class Server {
    /**
     * Server qui permet d'écouter les requêtes des clients
     */
    private ServerSocket serverSocket;
    /**
     * Server qui permet le transfert de fichier
     */
    private ServerSocket serverSocketPassif;
    private boolean running;
    private int port;
    private int portPassif;

    public Server() {
        this.port = ServerConfig.PORT;
        this.portPassif = ServerConfig.PORT_PASSIF;
        this.run();
    }

    /**
     * Permet de démarrer le serveur et d'écouter les clients
     */
    public void run(){
        System.out.println("Démarrage du serveur sur le PORT "+port+"..");
        try {
            this.serverSocket = new ServerSocket(port);
            this.serverSocketPassif = new ServerSocket(portPassif);
            this.running = true;
            System.out.println("Démarrage du serveur réussi");
            System.out.println("Serveur sur écoute..");
            while(this.running){
                Socket socket = this.serverSocket.accept();
                new Thread(new ClientSession(this.serverSocketPassif, socket)).start();
            }
        } catch (IOException e) {
            new IOException("Problème lors du démarrage du serveur");
        }
    }

}
