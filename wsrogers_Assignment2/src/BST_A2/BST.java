package BST_A2;
//
//

//
//
public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	if(s != null) {
		if(root ==null) {
			root = new BST_Node(s); 
			size++;
			return true;
		}
			if(root.containsNode(s) ==true) {
				return false;
			
		}else {
			size++;
			return root.insertNode(s);
		}
		
		
	}
	return false;
}



@Override
public boolean remove(String s) {
	if(s== null || root==null) {
		return false;
	
	}
	if(root.containsNode(s) !=true) {
		return false;
	}
	if(root.data.compareTo(s) ==0) {
		if(root.left ==null && root.right ==null) {
			root =null;
			size--;
			return true;	
		}
		if(root.left ==null && root.right !=null) {
			root =root.right;
			size--;
			return true;	
				
	}
		if(root.left !=null && root.right ==null) {
			root =root.left;
			size--;
			return true;	
		}
		if(root.left !=null && root.right !=null) {
			root.data =root.right.findMin().getData();
			root.right.removeNode(root.right.findMin().getData());
			size--;
			return true;
		}
	}
	size--;
	return root.removeNode(s);
}

@Override
public String findMin() {
	if (root == null) {
		return null;
	}
	return root.findMin().getData();
}

@Override
public String findMax() {
	if (root == null) {
		return null;
	}
	return root.findMax().getData();
}

@Override
public boolean empty() {
	if(size ==0) {
		return true;
	}
	return false;
}

@Override
public boolean contains(String s) {
	if(s== null) {
		return false;
	}
	if(size ==0) {
		return false;
	}
	return root.containsNode(s);
}

@Override
public int size() {
	if( root ==null || size == 0) {
		return 0;
	}
	return size;
}

@Override
public int height() {
	if(root== null) {
		return -1;
	}
	return root.getHeight();

			
}



}
