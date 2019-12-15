package structure;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.Objects;

import server.Broadcast;

/**
 * GroupNode
 */
public class GroupNode implements Serializable {
	private static final long serialVersionUID = -2445601595314701864L;

	private int ID;
	private String IP;
	private LinkedList<Message> messages;
	private ConnectionGraph connections;
	private Broadcast stub1;
	private Broadcast stub2;
	private Broadcast stub3;

	public GroupNode(String IP) {
		super();
		this.ID = 0;
		this.IP = IP;
		this.connections = new ConnectionGraph();
		this.connections.addNode(this);
		this.stub1 = null;
		this.stub2 = null;
		this.stub3 = null;
	}

	public String getIP() {
		return this.IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public LinkedList<Message> getMessages() {
		return this.messages;
	}

	public void addMessage(Message message) {
		this.messages.addLast(message);
	}

	public ConnectionGraph getConnections() {
		return this.connections;
	}

	public void addConnection(GroupNode node) {
		Registry registry;
		if (this.stub1 == null)
		{
			try {
				registry = LocateRegistry.getRegistry(node.getIP());
				this.stub1 = (Broadcast) registry.lookup("Broadcast");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			this.connections.addConnection(this.ID, node);
			this.connections.addConnection(node.getID(), this);
			node.mergeConnections(this.connections);
		}
		else if(this.stub2 == null)
		{
			try {
				registry = LocateRegistry.getRegistry(node.getIP());
				this.stub2 = (Broadcast) registry.lookup("Broadcast");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			this.connections.addConnection(this.ID, node);
			this.connections.addConnection(node.getID(), this);
			node.mergeConnections(this.connections);
		}
		else if(this.stub3 == null)
		{
			try {
				registry = LocateRegistry.getRegistry(node.getIP());
				this.stub3 = (Broadcast) registry.lookup("Broadcast");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			this.connections.addConnection(this.ID, node);
			this.connections.addConnection(node.getID(), this);
			node.mergeConnections(this.connections);
		}
	}
	
	public void mergeConnections(ConnectionGraph graph) {
		this.connections.merge(graph);
	}

	public boolean checkAvailability() {
		if(this.stub1 == null || this.stub2 == null || this.stub3 == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof GroupNode)) {
			return false;
		}
		GroupNode groupNode = (GroupNode) o;
		return ID == groupNode.ID && Objects.equals(messages, groupNode.messages) && Objects.equals(connections, groupNode.connections);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, messages, connections);
	}

	@Override
	public String toString() {
		return "{" +
			" ID='" + getID() + "'" +
			", messages='" + getMessages() + "'" +
			"}";
	}
	
}