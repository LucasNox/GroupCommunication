package structure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

import server.Broadcast;

/**
 * ConnectionGraph
 */
public class ConnectionGraph implements Serializable {
	private static final long serialVersionUID = -2397552887357041731L;

	private HashMap<Integer, LinkedList<GroupNode>> connections;
	private LinkedList<GroupNode> nodes;

	public ConnectionGraph() {
		super();
		connections = new HashMap<>();
		nodes = new LinkedList<>();
	}

	public HashMap<Integer,LinkedList<GroupNode>> getConnections() {
		return this.connections;
	}

	public void merge(ConnectionGraph connections) {
		this.connections.putAll(connections.getConnections());
	}

	public void addConnection(Integer id, GroupNode node) {
		if(!this.connections.containsKey(id))
		{
			this.connections.put(id, new LinkedList<GroupNode>());
		}
		this.connections.get(id).addLast(node);
	}

	public LinkedList<GroupNode> getNodes() {
		return this.nodes;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ConnectionGraph)) {
			return false;
		}
		ConnectionGraph connectionGraph = (ConnectionGraph) o;
		return Objects.equals(connections, connectionGraph.connections);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(connections);
	}

	@Override
	public String toString() {
		return "{" +
			" connections='" + getConnections() + "'" +
			"}";
	}

}