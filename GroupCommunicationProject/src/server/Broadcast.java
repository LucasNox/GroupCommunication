package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import structure.GroupNode;

public interface Broadcast extends Remote {
    
    void createGroup(String own_ip) throws RemoteException;
    void enterGroup(String group_ip) throws RemoteException;
    GroupNode receiveNode(GroupNode node) throws RemoteException;
    LinkedList<GroupNode> getNodes() throws RemoteException;
    void testSpeed() throws RemoteException;
    public String grAdmin() throws RemoteException;
}