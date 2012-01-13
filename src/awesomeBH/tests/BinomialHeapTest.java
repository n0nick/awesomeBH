package awesomeBH.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import awesomeBH.BinomialHeap;

public class BinomialHeapTest {
	@Test public void emptyTest1() {
		BinomialHeap heap = new BinomialHeap();
		assertEquals(true, heap.empty());
	}
	
	@Test public void insertTest1() {
		BinomialHeap heap = new BinomialHeap();
		
		for (int i=1; i<10; i++) {
			heap.insert(i);
		}
		
		assertEquals(false, heap.empty());
		assertEquals(9, heap.size());
	}
	
	@Test public void deleteMinTest1() {
		BinomialHeap heap = new BinomialHeap();
		
		heap.insert(3);
		heap.deleteMin();
		
		assertEquals(true, heap.empty());
	}
	
	@Test public void deleteMinTest2() {
		BinomialHeap heap = new BinomialHeap();
		
		heap.insert(3);
		heap.insert(1);
		heap.deleteMin();
		
		assertEquals(1, heap.size());
		assertEquals(3, heap.findMin());
	}
	
	@Test public void deleteMinTest3() {
		BinomialHeap heap = new BinomialHeap();

		heap.insert(1);
		heap.insert(3);
		heap.deleteMin();
		
		assertEquals(1, heap.size());
		assertEquals(3, heap.findMin());
	}
	
	@Test public void findMinTest1() {
		BinomialHeap heap = new BinomialHeap();
		
		heap.insert(6);
		heap.insert(5);
		heap.insert(7);
		heap.insert(9);
		heap.insert(3);
		heap.insert(8);
		heap.insert(4);
		heap.insert(10);
		
		assertEquals(3, heap.findMin());
	}
	
	//TODO @Test public void meldTest1();
	//TODO @Test public void treesSize1();
	
	@Test public void sizeTest1() {
		BinomialHeap heap = new BinomialHeap();
		assertEquals(0, heap.size());
	}
	
	@Test public void sizeTest2() {
		BinomialHeap heap = new BinomialHeap();
		heap.insert(6);
		assertEquals(1, heap.size());
	}
	
	@Test public void sizeTest3() {
		BinomialHeap heap = new BinomialHeap();
		for (int size=1; size<=25; size++) {
			heap.insert(size);
			assertEquals(size, heap.size());
		}
	}
	
	@Test public void arrayToHeapTest1() {
		int[] array = {16, 21, 30, 19, 14, 25, 13, 28};
		BinomialHeap heap = new BinomialHeap();
		heap.arrayToHeap(array);
		assertEquals(array.length, heap.size());
	}
	
	@Test public void arrayToHeapTest2() {
		BinomialHeap heap = new BinomialHeap();
		heap.insert(16);
		heap.arrayToHeap(new int[0]);
		assertEquals(0, heap.size());
		assertEquals(true, heap.empty());
	}
	
	//TODO @Test public void treesSizeTest1() {}
	
	//TODO @Test public void isValidTest1() {}
}
