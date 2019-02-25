package DiGraph_A5;

import java.util.HashMap;
import java.util.*;

public class DiGraph implements DiGraphInterface {	
	// thanks to the TA who helped with my spaghetti code 
	// on Wednesday, I really owe you
	//Its been so long i forgot how to comment
	  // in here go all your data and methods for the graph
	 HashSet<Node> nodeSet;
	 HashMap<String, Node> nodeHashMap;
	 HashSet<Long> nodeNameSet;
//	 HashSet<Edge> edgeSet;
	 HashSet<Long> edgeNameSet;
	int countNode;
	int countEdge;
	  public DiGraph () { // default constructor
	    // explicitly include this
	    // we need to have the default constructor
	    // if you then write others, this one will still be there
		  	nodeSet = new HashSet<>();
			nodeHashMap = new HashMap<>();
			nodeNameSet = new HashSet<>();
		//	edgeSet = new HashSet<>();
			edgeNameSet = new HashSet<>();
	  }
	@Override
	public boolean addNode(long idNum, String label) {
		Node tester=nodeHashMap.get(label);
		// some checks may be redundant but better safe than sorry
		if (idNum < 0 || label == null || nodeNameSet.contains(idNum)|| nodeHashMap.containsKey(label) || tester!=null) {
			return false;
		}
		Node node= new Node(idNum, label);
		nodeSet.add(node);
		nodeHashMap.put(label,node);
		nodeNameSet.add(idNum);
		countNode++;
		return true;
	}
	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		// TODO Auto-generated method stub
		// some checks may be redundant but better safe than sorry
		if( idNum <0  || sLabel.equals(dLabel) || sLabel==null || dLabel==null) {
			return false;
		}
//		Node start;
	//	Node end;
		if(nodeHashMap.containsKey(sLabel)==false ||nodeHashMap.containsKey(dLabel)==false|| edgeNameSet.contains(idNum)) {
			return false;
		}
		if(nodeHashMap.get(sLabel).outerEdge.containsKey(dLabel) ||nodeHashMap.get(dLabel).innerEdge.containsKey(sLabel) ) {
			return false;
		}	
	//	start = nodeHashMap.get(sLabel);
	//	end= nodeHashMap.get(dLabel);
		Edge edge = new Edge(idNum, nodeHashMap.get(sLabel), nodeHashMap.get(dLabel),weight);	
		//for (Edge test : edgeSet) {
		//	if (test.start == edge.start && test.end== edge.end) {
		//		return false;
		//	}
		//}
	//	edgeSet.add(edge);
		nodeHashMap.get(sLabel).addOuterEdge(dLabel,edge);
		nodeHashMap.get(dLabel).addInnerEdge(sLabel,edge);
		edgeNameSet.add(idNum);	
		countEdge++;
		return true;
	}
	@Override
	public boolean delNode(String label) {
		// TODO Auto-generated method stub
		// some checks may be redundant but better safe than sorry
		if(nodeHashMap.containsKey(label)== false) {
			return false;
		}			
			for(String string: nodeHashMap.get(label).innerEdge.keySet()){
				edgeNameSet.remove(nodeHashMap.get(label).innerEdge.get(string).id);
				nodeHashMap.get(label).innerEdge.get(string).start.removeOuterEdge(label);
				countEdge--;
			}
			for(String string: nodeHashMap.get(label).outerEdge.keySet()){
				edgeNameSet.remove(nodeHashMap.get(label).outerEdge.get(string).id);
			nodeHashMap.get(label).outerEdge.get(string).end.removeInnerEdge(label);
				countEdge--;
			}
			nodeNameSet.remove(nodeHashMap.get(label).id);
			nodeHashMap.remove(label);
			countNode--;
			return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub	
		// some checks may be redundant but better safe than sorry
		Node start = nodeHashMap.get(sLabel);	
		Node end = nodeHashMap.get(dLabel);
		if (start == null || end==null/* || edge == null*/) {
			return false;
	}
		if(nodeHashMap.containsKey(sLabel)==false || nodeHashMap.containsKey(dLabel)==false) {
			return false;
		}	
		if(nodeHashMap.get(sLabel).outerEdge.containsKey(dLabel)==false || nodeHashMap.get(dLabel).innerEdge.containsKey(sLabel)==false){
			return false;
		}
		edgeNameSet.remove(nodeHashMap.get(sLabel).outerEdge.get(dLabel).id);
			nodeHashMap.get(sLabel).removeOuterEdge(dLabel);
		nodeHashMap.get(dLabel).removeInnerEdge(sLabel);
		countEdge--;
		return true;
	}
	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return countNode;
	}
	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return countEdge;
	}
	@Override
	public ShortestPathInfo[] shortestPath(String label){
		long tester = 1000000;
		ShortestPathInfo[] shortPath = new ShortestPathInfo[countNode];	
		MinBinHeap minHeap = new MinBinHeap();
		nodeHashMap.get(label).distanceViewer = 0;
		minHeap.insert(new EntryPair(label,nodeHashMap.get(label).distanceViewer)); 
		for(String string: nodeHashMap.keySet()){
			if(!string.equals(label)){
				nodeHashMap.get(string).distanceViewer = tester;
				minHeap.insert(new EntryPair(string,nodeHashMap.get(string).distanceViewer));
			}
		}	
		while(minHeap.size() >0){		
			Node nodey = nodeHashMap.get(minHeap.getMin().value);
			minHeap.delMin();
			for(String string: nodey.outerEdge.keySet()){
				long test1 = nodey.distanceViewer + nodey.outerEdge.get(string).weight;
				long test2 = nodey.outerEdge.get(string).end.distanceViewer;
				if(test1 < test2){
					nodey.outerEdge.get(string).end.distanceViewer = test1;
					minHeap.insert(new EntryPair(nodey.outerEdge.get(string).end.name, nodey.outerEdge.get(string).end.distanceViewer));
				}
			}		
		}
		// must be placed outside apparently
		int i = 0;
		for(String string: nodeHashMap.keySet()){	
			if(nodeHashMap.get(string).distanceViewer == tester){
				shortPath[i] = new ShortestPathInfo(string,-1);
			}else{
				shortPath[i] = new ShortestPathInfo(string, nodeHashMap.get(string).distanceViewer);
			}	
			i++;
		}
		return shortPath;
	} 
  // rest of your code to implement the various operations
}
//RRREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE RREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE    REEEEEEEEEEEEEE REEEEEE RERERERRERE REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE