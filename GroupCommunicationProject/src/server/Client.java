package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client implements Runnable {
    private String host;
    private int index;

    public Client(String host, int index) {
        this.host = host;
        this.index = index;
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Broadcast stub = (Broadcast) registry.lookup("Broadcast"+args[1]);
            String response = stub.saySomething();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    Broadcast stub = (Broadcast) registry.lookup("Broadcast"+this.index);
                    String response = stub.saySomething();
                    System.out.println("response: " + response);
                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
        
    }
}