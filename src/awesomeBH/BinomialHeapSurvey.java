package awesomeBH;

import java.util.Arrays;
import java.util.Random;

public class BinomialHeapSurvey {

	private static boolean contains(int[] ar, int x) {
		for (int y : ar) {
			if (y == x) {
				return true;
			}
		}
		return false;
	}

	private static int[] generateRandomSet(int count) {
		int[] ar = new int[count];
		int x;
		Random rnd = new Random();

		for (int i = 0; i < count; i++) {
			x = rnd.nextInt(count * 30) + 1;

			while (contains(ar, x)) {
				x = rnd.nextInt(count * 30) + 1;
			}

			ar[i] = x;
			;
		}

		return ar;
	}

	public static void main(String[] args) {
		int[] sizes = { 750 , 1500, 2250 };
		int[] array;
		BinomialHeap h = new BinomialHeap();

		for (int size : sizes) {
			array = generateRandomSet(size);
			h.arrayToHeap(array);

			System.out.format("(i) After %d insert()s:\n", size);
			System.out.format("%d trees: %s\n", h.treesSize().length, Arrays.toString(h.treesSize()));

			h.deleteMin();

			System.out.println("(ii) After 1 deleteMin():");
			System.out.format("%d trees: %s\n", h.treesSize().length, Arrays.toString(h.treesSize()));
			System.out.println();
		}
	}
}
