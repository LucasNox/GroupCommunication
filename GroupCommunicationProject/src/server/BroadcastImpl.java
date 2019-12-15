package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import structure.GroupNode;
import structure.Message;

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

        info = info + "Quantidade de elementos: " + this.node.getConnections().getNodes().size() + "\n";

        HashMap<Integer, LinkedList<GroupNode>> tree = this.node.getConnections().getConnections();

        for (Map.Entry<Integer, LinkedList<GroupNode>> entry : tree.entrySet()){
            info = info + "Node: " + this.node.getConnections().getNodes().get(entry.getKey()).getIP() + " Connections: ";
            
            for (GroupNode leaf : entry.getValue()) {
                info = info + leaf.getIP() + ", ";
            }
            info = info + "\n";
        }
        return info;
    }

    @Override
    public void setMSG(Message msg) throws RemoteException{
        this.node.addMessage(msg);
    }

    @Override
    public void sendString(String txt, String nick, LocalTime time, String ipF) throws RemoteException{
        System.out.println("SEND STRING");
        System.out.println(nick+txt+time);
        Message msg = new Message(nick, txt, time);
        
        LinkedList<GroupNode> cons = this.node.getConnections().getNodes();
        for (GroupNode nos : cons) {
            if(!ipF.equals(nos.getIP())){
                try {
                    Registry registry = LocateRegistry.getRegistry(nos.getIP());
                    Broadcast stub = (Broadcast) registry.lookup("Broadcast");
                    stub.setMSG(msg);
                    stub.sendString(txt, nick, time, this.node.getIP());
                }catch(Exception e){}
            }
        }
    }

    @Override
    public LinkedList<Message> getMSGS() throws RemoteException{
        //System.out.println("GET MSGS");
        return this.node.getMessages();
    }
}