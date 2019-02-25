package DiGraph_A5;

public class Edge implements EdgeInterface {
	 long id;
	 Node start;
	 Node end;
	 long weight;
	
	public Edge(long id, Node start, Node end, long weight) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.weight = weight;
		
	}
	@Override
	public long getID() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public Node getEnd() {
		// TODO Auto-generated method stub
		return end;
	}
	@Override
	public Node getStart() {
		// TODO Auto-generated method stub
		return start;
	}
	@Override
	public long getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

}
