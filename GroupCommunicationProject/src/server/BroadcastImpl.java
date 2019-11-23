package server;

import java.rmi.RemoteException;
import java.util.HashMap;

public class BroadcastImpl implements Broadcast {
    HashMap<String, String> connections;

    public BroadcastImpl() {
        super();
        connections = new HashMap<String, String>();
    }

    @Override
    public void enterGroup(String own_ip_address, String group_ip_address) throws RemoteException {
        

    }
    
}