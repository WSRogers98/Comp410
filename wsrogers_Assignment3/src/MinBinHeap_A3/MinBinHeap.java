package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
		if(entry == null || entry.priority <0|| entry.value==null) {
			return;
		}
			if (array[1]==null) {
			array[1]= entry;
			size++;
		}else {
			int i = size+1;
		for(int useless =0; entry.priority < array[i/2].priority; i/=2) {
				array[i] = array[i/2];
			
		}
				size++;
				array[i] =entry;
		}
	
}

@Override
public void delMin() {
	if(array[1]==null) {
		return;
	}   
	
	array[1]=array[size];
	orderArray(1);
	size--;
	
}

private void orderArray(int i) {
	EntryPair test= array[i];
	int temp;
	while(i * 2 <= size){
		if(i*2 +1 >size) {
			temp =i*2;
		}
			else{
				 if(array[i*2].priority < array[(i*2) +1].priority)
					 temp= i*2;
				 else
					 temp= (i*2)+1;
			 }
		if(array[i].priority> array[temp].priority){
			 array[i] = array[temp];
			 array[temp] = test;
		 }else{
			 array[i]=test;
			
		 }
		 i = temp;
	 }
	
}

@Override
public EntryPair getMin() {
	if(array[1] == null) {
		return null;
	}
	return array[1];
}

@Override
public int size() {
	return size;
}

@Override
public void build(EntryPair[] entries) {
	if (entries.length > array.length|| entries == null) {
		return;
	}
	if(size!=0) {
		size = 0;
	}
	for(int i=1; i<= entries.length; i++) {
		array[i] = entries[i-1];
		size++;
		}
	for(int i = size/2; i > 0 ; i--)
		 orderArray(i);
}
}
