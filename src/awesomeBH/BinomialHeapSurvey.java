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
		int j;
		BinomialHeap h = new BinomialHeap();

		for (int size : sizes) {
			array = generateRandomSet(size);
			h.arrayToHeap(array);

			System.out.format("For size %d:\n", size);
			System.out.format("%s (%d)\n", Arrays.toString(h.treesSize()), h.size());

			for (j = 0; j < size/3; j++) {
				h.deleteMin();
			}

			System.out.format("After %d deleteMin()s:\n", j);
			System.out.format("%s (%d)\n\n", Arrays.toString(h.treesSize()), h.size());
		}
	}
}
