package awesomeBH;

/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 */
public class BinomialHeap
{
	
	private static int EMPTY_HEAP_SIZE = 0; // TODO: check

	private HeapNode min;
	private HeapNode first;
	private HeapNode last;
	private int size; // Number of elements in the heap
	private int treesNum;
	private int[] treesSize;
	
	private void reset() {
		min = null;
		first = null;
		last = null;
		size = EMPTY_HEAP_SIZE;
		treesNum = 0;
		treesSize = null;
	}
	
	public BinomialHeap() {
		this(null);
	}
	
	public BinomialHeap(HeapNode node) {
		this.min = node;
		this.first = node;
		this.last = node;
		if (node != null) {
			this.size = node.getSize();
			treesNum = 1;
			buildTreesSize();
		} else {
			this.size = EMPTY_HEAP_SIZE;
			treesNum = 0;
			this.treesSize = null;
		}
	}
	
   /**
    * public boolean empty()
    *
    * precondition: none
    * 
    * The method returns true if and only if the heap
    * is empty.
    *   
    */
    public boolean empty()
    {
    	return this.size == EMPTY_HEAP_SIZE;
    }
		
   /**
    * public void insert(int value)
    *
    * Insert value into the heap 
    *
    */
    public void insert(int value) 
    {    
    	BinomialHeap heapToMeld;
    	HeapNode newHeapNode = new HeapNode(value);
    	if(empty()) {
    		this.first = newHeapNode;
    		this.last = newHeapNode;
    		this.min = newHeapNode;
    		this.size = 1;
    	} else {
    		heapToMeld = new BinomialHeap(newHeapNode);
    		this.meld(heapToMeld);
    	}
    }

   /**
    * public void deleteMin()
    *
    * Delete the minimum value
    *
    */
    public void deleteMin()
    {
    	// collect all min's children to new heap
    	BinomialHeap h = new BinomialHeap();
     	HeapNode p = this.min.getLeftMostChild();
     	
     	while (p != null) {
     		h.addTree(p);
     		p = p.getNext();
     	}
     	
     	// remove min from trees list
     	if (this.min.getPrev() != null) {
     		this.min.getPrev().setNext(this.min.getNext());
     	}
     	if (this.min.getNext() != null) {
     		this.min.getNext().setPrev(this.min.getPrev());
     	}
     	if (this.min == this.first) {
     		this.first = this.min.getNext();
     	}
     	if (this.min == this.last) {
     		this.last = this.min.getPrev();
     	}
     	
     	// meld everything together
     	this.meld(h);
    }

   /**
    * public int findMin()
    *
    * Return the minimum value
    *
    */
    public int findMin()
    {
    	return this.min.getValue();
    } 
    
   /**
    * public void meld (BinomialHeap heap2)
    *
    * Meld the heap with heap2
    *
    */
    public void meld (BinomialHeap heap2)
    {
    	BinomialHeap h = BinomialHeap.merge(this, heap2);
    	if (h.empty()) {
    		// this.empty(), so do nothing
    	} else {
    		HeapNode prevX = null;
    		HeapNode x = h.getFirst();
    		HeapNode nextX = x.getNext();
    		
    		while (nextX != null) {
    			if (x.getRank() != nextX.getRank() ||
    					(nextX.getNext() != null && nextX.getNext().getRank() == x.getRank())) {
    				prevX = x;
    				x = nextX;
    			} else {
    				if (x.getValue() <= nextX.getValue()) {
    					x.setNext(nextX.getNext());
    					if (nextX.getNext() != null) {
    						nextX.getNext().setPrev(x);
    					}
    					x.linkWith(nextX);
    				} else {
    					if (prevX == null) {
    						h.setFirst(nextX);
    					} else {
    						prevX.setNext(nextX);
    						if (nextX != null) {
    							nextX.setPrev(prevX);
    						}
    					}
    					nextX.linkWith(x);
    					x = nextX;
    				}
    			}
    			
        		nextX = x.getNext();
    		}
    		
    		this.first = h.first;
    		this.last = h.last;
    	}
		
		// fix size, trees count, min node
		this.fixProperties();
    }
	
	private void fixProperties() {
		HeapNode p = this.first;
		this.size = 0;
		this.treesNum = 0;
		this.min = null;
		while (p != null) {
			this.size+= p.getSize();
			this.treesNum++;
			if (this.min == null) {
				this.min = p;
			} else if (p.getValue() < this.min.getValue()) {
				this.min = p;
			}
			p = p.getNext();
		}
		// fix trees size array
		this.buildTreesSize();
	}
    
    public static BinomialHeap merge(BinomialHeap heap1, BinomialHeap heap2) {
    	BinomialHeap h = new BinomialHeap();
    	
    	HeapNode p1 = heap1.getFirst();
    	HeapNode p2 = heap2.getFirst();
    	
    	while (p1 != null && p2 != null) {
    		if (p1.getRank() <= p2.getRank()) {
    			h.addTree(p1);
    			p1 = p1.getNext();
    		} else {
    			h.addTree(p2);
    			p2 = p2.getNext();
    		}
    	}
    	
    	while (p1 != null) {
    		h.addTree(p1);
    		p1 = p1.getNext();
    	}
    	while (p2 != null) {
    		h.addTree(p2);
    		p2 = p2.getNext();
    	}
    	
    	return h;
    }

   private void addTree(HeapNode tree) {
	   if (empty()) {
		   this.first = tree;
		   this.last = tree;
	   } else {
		   this.last.setNext(tree);
		   tree.setPrev(this.last);
		   this.last = tree;
	   }
	   
	   this.size+= tree.getSize();
	   
	   //TODO after calling this, fix: min, size, treesNum, treesSize
	}

public HeapNode getFirst() {
	return first;
	}
	
	public void setFirst(HeapNode first) {
		this.first = first;
	}
	
	public HeapNode getLast() {
		return last;
	}
	
	public void setLast(HeapNode last) {
		this.last = last;
	}
	
	/**
    * public int size()
    *
    * Return the number of elements in the heap
    *   
    */
    public int size()
    {
    	return this.size;
    }
    
   /**
    * public int[] treesSize()
    *
    * Return an array containing the sizes of the trees that represent the heap
    * in ascending order.
    * 
    */
    public int[] treesSize()
    {
        return this.treesSize;
    }

   /**
    * public void arrayToHeap()
    *
    * Insert the array to the heap. Delete previous elemnts in the heap.
    * @author sakhbak1
    */
    public void arrayToHeap(int[] array)
    {
    	this.reset();
    	for(int i : array) {
    		this.insert(i);
    	}
    }
	
   /**
    * public boolean isValid()
    *
    * Returns true if and only if the heap is valid.
    *   
    */
    public boolean isValid() 
    {
    	//TODO validate no more than 1 rank per value
    	//TODO validate minimum
    	
    	HeapNode p = this.first;
    	int minVal = this.min.getValue();
    	
    	while (p != null) {
    		if (!p.isValid()) {
    			return false; // invalid binomial minimal tree
    		}
    		if (p.getValue() < minVal) {
    			return false; // pointer 'min' is invalid
    		}
    		p = p.getNext();
    	}
    	return true;
    }
    
    private void buildTreesSize() {
    	this.treesSize = new int[this.treesNum];
    	HeapNode current = first;
    	int i = 0;
    	while(current != null) {
    		this.treesSize[i++] = current.getSize();    		
    		current = current.getNext();
    	}
    }
    
    @Override
    public String toString() {
    	StringBuffer str = new StringBuffer();
    	
    	HeapNode tree = this.getFirst();
    	while (tree != null) {
    		if (tree == this.min) {
    			str.append('*');
    		}
    		str.append(tree.toString());
    		tree = tree.getNext();
    	}
    	
    	return String.format("{ %s }", str);
    }
    
   /**
    * public class HeapNode
    * 
    * If you wish to implement classes other than BinomialHeap
    * (for example HeapNode), do it in this file, not in 
    * another file 
    *  
    */
    public class HeapNode {

    	// Members 
    	
    	private int value;
    	private int rank;
    	private int size;
      	private HeapNode next;
    	private HeapNode prev;
    	private HeapNode leftMostChild;
    	
    	// C'tors
    	
		public HeapNode(int value) {
    		this.value = value;
    		this.size = 1;
    		this.rank = 0;
    	}
    	
    	// Getters and Setters
    	
		public HeapNode getNext() {
			return next;
		}
		public void setNext(HeapNode next) {
			this.next = next;
		}
		public HeapNode getPrev() {
			return prev;
		}
		public void setPrev(HeapNode prev) {
			this.prev = prev;
		}    	
    	public HeapNode getLeftMostChild() {
			return leftMostChild;
		}

		public void setLeftMostChild(HeapNode leftMostChild) {
			this.leftMostChild = leftMostChild;
		}
    	public int getRank() {
    		return this.rank;
    	}
    	
    	public int getSize() {
    		return this.size;
    	}
    	
    	public int getValue() {
    		return this.value;
    	}
    	
    	public void setValue(int value) {
    		this.value = value;
    	}
    	
    	// Methods
    	
    	// TODO
    	public boolean isValid() {
    		return validate() != null;
    	}
    	
    	//TODO fix result type
    	private int[] validate() {
    		HeapNode child = this.getLeftMostChild();
    		int[] childResult;
    		int size = 1;
    		int rank = 0;
    		
    		while (child != null) {
    			if (this.getValue() > child.getValue()) {
    				return null;
    			}
    			childResult = child.validate();
    			if (childResult != null) {
    				size+= childResult[0];
    				rank++;
    			} else {
    				return null;
    			}
    			
    			child = child.getNext();
    		}
    		
    		if (size != Math.pow(2, rank)) {
    			return null;
    		}
    		
    		
    		int[] result = { size, rank };
    		return result;
    	}
    	
    	// TODO: whoever calls link needs to check the following assumptions:
    	// 1. other is a tree with a larger root than this
    	// 2. both trees must be of same rank
    	public void linkWith(HeapNode other) {
    		other.next = this.leftMostChild;
    		if(this.leftMostChild != null) {
    			this.leftMostChild.setPrev(other);
    		}
    		this.leftMostChild = other;
    		
    		this.rank++;
    		this.size += other.size;
    	}
        
        @Override
        public String toString() {
        	StringBuffer children = new StringBuffer();;
        	
        	HeapNode child = this.getLeftMostChild();
        	while (child != null) {
        		children.append(child.toString());
        		child = child.getNext();
        	}
        	
        	return String.format("[ %d %s ]", this.getValue(), children);
        }
    }

}
