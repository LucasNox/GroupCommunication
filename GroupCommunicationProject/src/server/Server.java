package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.Random;

public class Server implements RandString {
    public Server () {}

	@Override
	public String saySomething() throws RemoteException {
        String[] strArray = {"Teste", "Oi", "Como vai você", "Eu vou muito bem", "Mas as vezes essa vida é uma merda", "E apenas desejo morrer..."};
        Random rand = new Random();
        String mensagem = strArray[rand.nextInt(6)];
        System.out.println(mensagem);
        return mensagem;
	}

    public static void main (String args[]) {
        try {
            Server obj = new Server();
            RandString stub = (RandString) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RandString"+args[0], stub);

            System.out.println("Server ready");
            
            if(args.length >= 2)
            {
                System.out.println("Preparing client from server");
                Thread t = new Thread(new Client(args[1], Integer.parseInt(args[2])));
                t.start();
                System.out.println("Client from server runned");
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}