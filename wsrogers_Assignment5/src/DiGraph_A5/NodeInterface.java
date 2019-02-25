package DiGraph_A5;

import java.util.HashMap;

public interface NodeInterface {

	void addInnerEdge(String string, Edge edge);
	void addOuterEdge(String string, Edge edge);
	void removeInnerEdge(String string);
	void removeOuterEdge(String string);
//	boolean remove(Edge edge);
	long getID();
//	Edge getEdge(Node theEdge);
	int getInnerEdgeCount();
	int getOuterEdgeCount();
	HashMap<String, Edge> getInnerEdges();
	HashMap<String,Edge> getOuterEdges();
	String getName();
//	Node[] listPointingNodes();

}
