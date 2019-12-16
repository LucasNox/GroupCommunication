package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;

import structure.GroupNode;
import structure.Message;

/**
 * @file  Broadcast.java
 * @brief Interface do BroadcastImpl
*/

public interface Broadcast extends Remote {
    
    void createGroup(String own_ip) throws RemoteException;
    void enterGroup(String group_ip) throws RemoteException;
    GroupNode receiveNode(GroupNode node) throws RemoteException;
    LinkedList<GroupNode> getNodes() throws RemoteException;
    void testSpeed() throws RemoteException;
    public String grAdmin() throws RemoteException;
    public void sendString(String txt, String nick, LocalTime time, String ipF) throws RemoteException;
    public LinkedList<Message> getMSGS() throws RemoteException;
    public void setMSG(Message msg) throws RemoteException;
    public GroupNode getNo() throws RemoteException;
    public void updateConnGraph(GroupNode node) throws RemoteException;
}