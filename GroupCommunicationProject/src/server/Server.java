package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @file  Server.java
 * @brief Liga o node server
*/

public class Server implements Runnable {
    /*******************************************************************
	*   GLOBAL VARIABLES
	*******************************************************************/
    private String IP;

    /*******************************************************************
	*   IMPLEMENTATION
	*******************************************************************/
    public Server(String IP) {
        super();
        this.IP = IP;
    }

    @Override
    public void run () {
        try {
            System.out.println("Antes de setar hostname, deixa eu ver o IP: " + this.IP);
			System.setProperty("java.rmi.server.hostname", this.IP);

            BroadcastImpl obj = new BroadcastImpl();
            Broadcast stub = (Broadcast) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Broadcast", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}