package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import structure.GroupNode;

public class BroadcastImpl implements Broadcast {
    private GroupNode node;

    public BroadcastImpl() {
        super();
        this.node = null;
    }

    @Override
    public void createGroup(String own_ip) throws RemoteException {
        if (this.node == null) {
            this.node = new GroupNode(own_ip);
        }
    }

    @Override
    public void enterGroup(String group_ip) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(group_ip);
            Broadcast stub = (Broadcast) registry.lookup("Broadcast");
            LinkedList<GroupNode> nodes = stub.getNodes();
            System.out.println(nodes);
            Iterator<GroupNode> it = nodes.iterator();
            LinkedList<Long> dtime = new LinkedList<>();
            while(it.hasNext())
            {
                GroupNode node = it.next();
                if(!node.checkAvailability())
                {
                    it.remove();
                    continue;
                }
                registry = LocateRegistry.getRegistry(node.getIP());
                stub = (Broadcast) registry.lookup("Broadcast");
                Long start = System.currentTimeMillis();
                stub.testSpeed();
                Long finish = System.currentTimeMillis();
                dtime.addLast(finish - start);
                System.out.println(finish - start);
            }
            Long minor_time = Long.MAX_VALUE;
            int minor_time_index = 0;
            for(Long time : dtime) 
            {
                if(time < minor_time)
                {
                    minor_time = time;
                }
            }
            minor_time_index = dtime.indexOf(minor_time);
            registry = LocateRegistry.getRegistry(nodes.get(minor_time_index).getIP());
            stub = (Broadcast) registry.lookup("Broadcast");
            this.node = stub.receiveNode(this.node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    @Override
    public void testSpeed() throws RemoteException {
        System.out.println("Estou sendo testado para averiguar velocidade");
        return;
    }

    @Override
    public LinkedList<GroupNode> getNodes() throws RemoteException {
        return this.node.getConnections().getNodes();
    }    

    @Override
    public GroupNode receiveNode(GroupNode node) throws RemoteException {
        this.node.addConnection(node);
        return node;
    }

    @Override
    public String grAdmin() throws RemoteException{
        String info = "\n";

        HashMap<Integer, LinkedList<GroupNode>> tree = this.node.getConnections().getConnections();

        GroupNode aux = this.node;

        for (Map.Entry<Integer, LinkedList<GroupNode>> entry : tree.entrySet()){
            info = info + "Node: " + aux.getIP() + " Connections: ";
            
            for (GroupNode leaf : entry.getValue()) {
                info = info + leaf.getIP() + ", ";
            }
            info = info + "\n";
        }
        return info;
    }
}