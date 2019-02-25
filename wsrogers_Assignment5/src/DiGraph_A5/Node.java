package DiGraph_A5;
 import java.util.*;
public class Node implements NodeInterface {
	 long id;
	 String name;	
	 HashMap <String, Edge> outerEdge;
//	 HashSet <Long> outerEdgeID;
//	 HashMap <Edge, Node> EdgeHolder;
	 HashMap <String, Edge> innerEdge;
//	 HashSet <Long> innerEdgeID;
	 long distanceViewer;
	public Node(long id, String name) {
		this.id = id;
		this.name = name;
		outerEdge = new HashMap<>();
	//	outerEdgeID = new HashSet<>();
		innerEdge = new HashMap<>();
	//	innerEdgeID = new HashSet<>();
	}
	
	@Override
	public void addInnerEdge(String string, Edge edge) {
		// TODO Auto-generated method stub
		innerEdge.put(string, edge);
	}
	@Override
	public void removeInnerEdge(String sLabel){
		innerEdge.remove(sLabel);
	}

	@Override
	public void addOuterEdge(String string, Edge edge) {
		// TODO Auto-generated method stub
		outerEdge.put(string, edge);
	}
	@Override
	public void removeOuterEdge(String dLabel){
		outerEdge.remove(dLabel);
	}
///	@Override
//	public boolean remove(Edge edge) {
		// TODO Auto-generated method stub
//		if(edge.getStart().equals(this)) {
//			outerEdge.remove(edge);
	//		outerEdgeID.remove(edge.getID());
//			return true;
//		}
//		if(edge.getEnd().equals(this)) {
//			innerEdge.remove(edge);
	//		innerEdgeID.remove(edge.getID());
//			return true;
//		}
//		return false;
//	}

/*	@Override
	public Edge getEdge(Node theEdge) {
		if (outerEdge.containsValue(theEdge)== false) {
			return null;
		}
			for (Edge edge : outerEdge.keySet()) {
				Node node = outerEdge.get(edge);
				if (node.equals(theEdge)) {					
					return edge;
				}
			}
			return null;
			}
*/		
			@Override
	public long getID() {
		// TODO Auto-generated method stub
		return id;
	}
			@Override
			public int getInnerEdgeCount() {
				// TODO Auto-generated method stub
				return outerEdge.size();
			}
			@Override
			public int getOuterEdgeCount() {
				// TODO Auto-generated method stub
				return innerEdge.size();
			}
			@Override
			public HashMap<String, Edge> getInnerEdges() {
				// TODO Auto-generated method stub
				return innerEdge;
			}
			@Override
			public HashMap<String, Edge> getOuterEdges() {
				// TODO Auto-generated method stub
				return outerEdge;
			}
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return name;
			}
//			@Override
/*			public Node[] listPointingNodes() {
				// TODO Auto-generated method stub
				//java.util is a god-send
			Collection<Node> nodes = outerEdge.values();
			return nodes.toArray(new Node[outerEdge.size()]);
			}
*/

		
			
}
