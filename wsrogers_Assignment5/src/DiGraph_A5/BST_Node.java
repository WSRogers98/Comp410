package DiGraph_A5;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node parent;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public boolean containsNode(String s){ 
	int test =s.compareTo(this.data);
	if(test ==0) {
		return true;
	}
	if(test >0) {
		if(right !=null) {
		return right.containsNode(s);
		}
		return false;
	}
	if(test <0) {
		if(left!= null) {
		return left.containsNode(s);
		}
		return false;
	}
	  return false; 
  }
  
  public boolean insertNode(String s){ 
	  int test= s.compareTo(this.data);
	  if( test ==0) {
		  return false;
	  }
		if (test <0) {
			if(left != null) {
				return left.insertNode(s);
				
			}else {
				left= new BST_Node(s);
				left.parent = this;
				return true;
			}
		}
		if (test >0) {
			if(right != null) {
				return right.insertNode(s);
			}
			right =new BST_Node(s);
			right.parent=this;
			return true;
		}
			
		return false;
  }
  
  public boolean removeNode(String s){ 
		BST_Node test = this.getTestNode(s);
	
		if (test.left ==null &&test.right ==null) {
			if(test.parent == null) {
				test =null;

				return true;
			}
			if (test.parent.left ==test) {
				test.parent.left = null;
			} else {
				test.parent.right = null;
			}
			return true;
		
		} else if ((test.right!=null && test.left==null) || (test.right==null  && test.left!=null)) {
			if (test.left!=null && test.parent.right == test) {
				test.parent.right = test.left;
				test.left.parent = test.parent;
				return true;
			} else if (test.left!=null && test.parent.left ==test) {
				test.parent.left = test.left;
				test.left.parent = test.parent;

				return true;
			} else if (test.right!=null && test.parent.right== test) {
				test.parent.right = test.right;
				test.right.parent = test.parent;
				return true;
			} else {
				test.parent.left = test.right;
				test.right.parent = test.parent;
				return true;
			}
		}
		
		else {
			test.data =test.right.findMin().getData();
			test.right.removeNode(test.right.findMin().getData());
			return true;
		}

	}


	  
  
  	public BST_Node findMin(){ 
	  if(getLeft()== null) {
		  return this;
	  }else {
	return this.left.findMin();
	  }
	  }
   
  public BST_Node findMax(){ 
	  if(getRight()==null) {
		  return this;
	  }
	  else {
		 return this.right.findMin();
	  }
	  
  }
   
  
  public int getHeight() {
	  int leftHeight =0;
	  int rightHeight =0;
	  if(this.left!=null) {
	        leftHeight = this.left.getHeight();
	        leftHeight+=1;
	       
	  }
	  if(this.right!=null) {
	        rightHeight = this.right.getHeight();
	        rightHeight+=1;
	       
	      
	  }
		if(leftHeight >rightHeight) {
			return leftHeight;
		}
		if(leftHeight <rightHeight) {
			return rightHeight;
		}
		if(leftHeight == rightHeight) {
			return rightHeight;
		}
		return 0;
	  
	}
	public BST_Node getTestNode(String s) {
		BST_Node current = this;

		BST_Node test = null;

		if (current.getData().compareTo(s) == 0) {
			return current;
		}

		if (current.getData().compareTo(s) > 0 && current.getLeft() != null) {
			test = current.getLeft().getTestNode(s);
		} else if (current.getData().compareTo(s) < 0 && current.getRight() != null) {
			test = current.getRight().getTestNode(s);
		}
		return test;
	}

	
	
  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}