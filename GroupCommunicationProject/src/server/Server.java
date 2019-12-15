package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Runnable {
    private String IP;

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
            registry.bind("Broadcast", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}