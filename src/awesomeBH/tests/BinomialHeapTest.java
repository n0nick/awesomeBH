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
	
	@Test public void deleteMinTest4() {
		BinomialHeap heap = new BinomialHeap();
		int[] array = { 7,2,5,14,1,30,12,19,24,7,2,5,14,1,30 }; 
		heap.arrayToHeap(array);
		assertEquals(1, heap.findMin());
		heap.deleteMin();
		assertEquals(1, heap.findMin());
		heap.deleteMin();
		assertEquals(2, heap.findMin());
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
	
	@Test public void findMinTest2() {
		BinomialHeap heap = new BinomialHeap();
		
		for (int i=4; i<200; i++) {
			heap.insert(i);
		}
		heap.insert(3);
		for (int i=201; i<450; i++) {
			heap.insert(i);
		}
		
		assertEquals(3, heap.findMin());
	}
	
	@Test public void meldTest1() {
		BinomialHeap h1 = new BinomialHeap();
		int[] array = {16, 21, 30, 19, 14, 25, 13, 28};
		h1.arrayToHeap(array);
		
		BinomialHeap h2 = new BinomialHeap();
		h2.arrayToHeap(array);
		
		h1.meld(h2);
		
		assertEquals(true, h1.isValid());
		assertEquals(16, h1.size());
	}
	
	@Test public void meldTest2() {
		BinomialHeap h1 = new BinomialHeap();
		int[] array = {16, 21, 30, 19, 14, 25, 13, 28};
		h1.arrayToHeap(array);
		
		BinomialHeap h2 = new BinomialHeap();
		h2.insert(39);
		h2.insert(30);
		h2.insert(7);
		h2.insert(49877221);
		h2.insert(3);
		
		h1.meld(h2);
		
		assertEquals(true, h1.isValid());
		assertEquals(13, h1.size());
	}
	
	@Test public void treesSizeTest1() {
		BinomialHeap h1 = new BinomialHeap();
		int[] array = {16, 21, 30, 19, 14, 25, 13, 28};
		h1.arrayToHeap(array);
		int[] sizes = {8};
		assertArrayEquals(sizes, h1.treesSize());
		
		h1.insert(1);
		int[] sizes2 = {1, 8};
		assertArrayEquals(sizes2, h1.treesSize());
		
		h1.insert(3);
		int[] sizes3 = {2, 8};
		assertArrayEquals(sizes3, h1.treesSize());

		h1.insert(4);
		int[] sizes4 = {1, 2, 8};
		assertArrayEquals(sizes4, h1.treesSize());
		
		for (int i=99; i<107; i++) {
			h1.insert(i);
		}
		int[] sizes5 = {1, 2, 16};
		assertArrayEquals(sizes5, h1.treesSize());
	}
	
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
	
	@Test public void arrayToHeapTest3() {
		BinomialHeap heap = new BinomialHeap();
		heap.insert(1);
		int[] array = { 7,2,5 }; 
		heap.arrayToHeap(array);
		assertEquals(3, heap.size());
		assertEquals(2, heap.findMin());
	}
	
	@Test public void isValidTest1() {
		BinomialHeap heap = new BinomialHeap();
		int[] array = { 7,2,5,14,1,30 }; 
		heap.arrayToHeap(array);
		assertEquals(true, heap.isValid());
	}
	
	@Test public void isValidTest2() {
		BinomialHeap heap = new BinomialHeap();
		int[] array = { 7,2,5,14,1,30,12,19 }; 
		heap.arrayToHeap(array);

		heap.getLast().setLeftmostChild(heap.getLast().getLeftmostChild().getNext());
		
		assertEquals(false, heap.isValid());
	}
	
	@Test public void isValidTest3() {
		BinomialHeap heap = new BinomialHeap();
		int[] array = { 7,2,5,14,1,30,12,19,24,7,2,5,14,1,30 }; 
		heap.arrayToHeap(array);
		assertEquals(true, heap.isValid());
		
		heap.getFirst().getNext().getNext().getLeftmostChild().setNext(null);
		assertEquals(false, heap.isValid());
	}

	@Test public void isValidTest4() {
		BinomialHeap heap = new BinomialHeap();
		int[] array = { 7,2,5,14,1,30,12,19,24,7,2,5,14,1,30 }; 
		heap.arrayToHeap(array);
		assertEquals(true, heap.isValid());

		// kilkul for klal ha arema
		heap.getLast().setLeftmostChild(heap.getLast().getLeftmostChild().getNext());
		assertEquals(false, heap.isValid());
	}
	
	@Test public void isValidTest5() {
		BinomialHeap heap = new BinomialHeap();
		int[] array = { 7,2,5,14,1,30,12,19,24,7,2,5,14,1,30 }; 
		heap.arrayToHeap(array);
		assertEquals(true, heap.isValid());
		
		heap.getLast().getLeftmostChild().getLeftmostChild().setLeftmostChild(null);
		assertEquals(false, heap.isValid());
	}
	
	@Test public void emptyEmptyTest() {
		BinomialHeap heap = new BinomialHeap();
		assertEquals(true, heap.empty());
	}
	@Test public void emptyInsertTest() {
		BinomialHeap heap = new BinomialHeap();
		heap.insert(1);
		assertEquals(1, heap.size());
	}
	@Test public void emptyDeleteMinTest() {
		BinomialHeap heap = new BinomialHeap();
		heap.deleteMin();
		assertEquals(true, heap.empty());
	}
	@Test public void emptyFindMinTest() {
		BinomialHeap heap = new BinomialHeap();
		assertEquals(-1, heap.findMin());
	}
	@Test public void emptyMeldTest() {
		BinomialHeap h1 = new BinomialHeap();
		BinomialHeap h2 = new BinomialHeap();
		
		h1.meld(h2);
		assertEquals(true, h1.empty());
		h1.meld(null);
		assertEquals(true, h1.empty());
		
		h1.insert(1);
		h1.meld(h2);
		h1.meld(null);
		assertEquals(1, h1.size());
		
		h2.meld(h1);
		h2.meld(null);
		assertEquals(1, h1.size());
	}
	@Test public void emptySizeTest() {
		BinomialHeap heap = new BinomialHeap();
		assertEquals(0, heap.size());
	}
	@Test public void emptyArrayToHeapTest() {
		BinomialHeap heap = new BinomialHeap();
		int[] a = new int[0];
		heap.arrayToHeap(a);
		assertEquals(0, heap.size());
	}
	@Test public void emptyTreesSizeTest() {
		BinomialHeap heap = new BinomialHeap();
		assertEquals(0, heap.treesSize().length);
	}
	@Test public void emptyIsValidTest() {
		BinomialHeap heap = new BinomialHeap();
		assertEquals(true, heap.isValid());
	}
}
