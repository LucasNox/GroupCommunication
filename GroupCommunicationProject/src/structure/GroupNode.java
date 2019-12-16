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
		this.messages = null;
		this.connections = new ConnectionGraph();
		this.connections.addNode(this);
		this.messages = new LinkedList<>();
		this.stub1 = null;
		this.stub2 = null;
		this.stub3 = null;
	}

	public void setStub1(Broadcast stub){
		this.stub1 = stub;
	}

	public void setStub2(Broadcast stub){
		this.stub2 = stub;
	}

	public void setStub3(Broadcast stub){
		this.stub3 = stub;
	}

	public Broadcast getStub1(){
		return this.stub1;
	}

	public Broadcast getStub2(){
		return this.stub2;
	}

	public Broadcast getStub3(){
		return this.stub3;
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
		//System.out.println("GET MESSAGES");

		LinkedList<Message> msg = this.messages;
		if(this.messages.size()>0){
			System.out.println("MSGS CLEAR");
			this.messages.clear();
		}

		return msg;
	}

	public void addMessage(Message msg) {
		System.out.println("ADD MESSAGES");
		System.out.println(msg.getAuthor()+msg.getMessage()+msg.getTime());
		
		this.messages.addLast(msg);
		//System.out.println(this.messages);

	}

	public ConnectionGraph getConnections() {
		return this.connections;
	}

	private Broadcast makeStub(GroupNode node) {
		Registry registry;
		Broadcast stub = null;
		try {
			registry = LocateRegistry.getRegistry(node.getIP());
			stub = (Broadcast) registry.lookup("Broadcast");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		node.setID(this.connections.getNodes().size());
		this.connections.addConnection(this.ID, node);
		this.connections.addConnection(node.getID(), this);
		node.mergeConnections(this.connections);
		
		return stub;
	}

	public void addConnection(GroupNode node) {
		if (this.stub1 == null)
		{
			this.stub1 = makeStub(node);
		}
		else if(this.stub2 == null)
		{
			this.stub2 = makeStub(node);
		}
		else if(this.stub3 == null)
		{
			this.stub3 = makeStub(node);
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