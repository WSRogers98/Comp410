package LinkedList_A1;

/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/

public class LinkedListImpl implements LIST_Interface {
  Node sentinel; //this will be the entry point to your linked list (the head)
   int counter =0;
  public LinkedListImpl(){//this constructor is needed for temping purposes. Please don't modify!
    sentinel=new Node(0);
    //Note that the root's data is not a true part of your data set!
  }
  
  //implement all methods in interface, and include the getRoot method we made for temping purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
	Node temp = new Node(elt);
	Node count = sentinel;
	if(index > size() || index <0) {
		return false;
	}
	//insert beginning
	if(isEmpty()) {
		sentinel.next =temp;
		sentinel.prev=temp;
		temp.next=sentinel;
		temp.prev=sentinel;
		counter++;
		return true;
	}
	for(int i=0; i<index; i++) {
		count=count.next;
	}
			temp.next = count.next;
			temp.prev=count;
			count.next.prev=temp;
			count.next=temp;
	
	
	counter++;
	return true;

}

@Override
public boolean remove(int index) {
	Node count = sentinel;

	//index out of bounds
	if(index >counter || index<0 || isEmpty()) {
		return false;
	}
		for(int i=0; i<=index; i++) {
			count= count.next;
		}
		count.next.prev=count.prev;
		count.prev.next=count.next;
		counter--;
		return true;	
			
}


@Override
public double get(int index) {
	Node temp = sentinel;
	if(index > counter || index <0 ||size()==0) {
		return Double.NaN;
	}
	
	for(int i = 0; i<=index; i++) {
		temp = temp.next;
	}
	if(temp == null) {
	return Double.NaN;
	}
	return temp.data;
}

@Override
public int size() {
	return counter;
}

@Override
public boolean isEmpty() {
	if(size() ==0 ) {
		return true;
	}
	return false;

	
}

@Override
public void clear() {
//	sentinel.next=null;
	sentinel.next = sentinel;
	sentinel.prev =sentinel;
	
		counter = 0;
		
	}
}
