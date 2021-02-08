package lec;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Contains findMax and findSmallestDiff methods described in Lec05 slides,
 * as well as methods for collecting running times.
 * 
 * @author Erin Parker
 * @version January 23, 2019
 */
public class AlgDemo {

	/**
	 * GENERIC - works for any reference type that implements the
	 * Comparable interface
	 * 
	 * @param arr - the input array
	 * @return the largest object in the input generic array
	 * @throws NoSuchElementException
	 *             if the input array does not contain at least one item
	 */
	public static <T extends Comparable<? super T>> T findMax(T[] arr) {
		if(arr == null || arr.length == 0)
			throw new NoSuchElementException();

		T max = arr[0];
		for(int i = 1; i < arr.length; i++)
			if(arr[i].compareTo(max) > 0)
				max = arr[i];

		return max;
	}

	/**
	 * NON-GENERIC -- works only for type int
	 * 
	 * @param arr
	 *            - the input array
	 * @return the smallest difference among integers in the input array
	 * @throws NoSuchElementException
	 *             if the input array does not contain at least two items
	 */
	public static int findSmallestDiff(int[] arr) {
		// CAN WE make this a generic method?

		if(arr == null || arr.length == 0 || arr.length == 1)
			throw new NoSuchElementException();

		int diff = Math.abs(arr[0] - arr[1]);

		for(int i = 0; i < arr.length; i++)
			for(int j = i + 1; j < arr.length; j++)
				if(Math.abs(arr[i] - arr[j]) < diff)
					diff = Math.abs(arr[i] - arr[j]);

		return diff;
	}

	public static void timeFindMax() {
		long startTime, midpointTime, stopTime;
		Random rng = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while(System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Collect running times.

		int timesToLoop = 10000;  // use a large value since times will be small

		System.out.println("\nRUNNING TIMES for findMax\nN\tTime(ns)");

		// For each problem size n . . .
		for(int n = 10000; n <= 100000; n += 10000) {

			Integer[] list = new Integer[n];
			for(int x = 0; x < n; x++)
				list[x] = rng.nextInt();

			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++) {
				Integer max = findMax(list);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.
			for(int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and calling findMax.
			// Average it over the number of runs.

			long averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}

	public static void timeFindSmallestDiff() {
		long startTime, midpointTime, stopTime;
		Random rng = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while(System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Collect running times.

		int timesToLoop = 1000;  // okay to use a smaller value since times will be large

		System.out.println("\nRUNNING TIMES for findSmallestDiff\nN\tTime(ns)");

		// For each problem size n . . .
		for(int n = 1000; n <= 10000; n += 1000) {

			int[] list = new int[n];
			for(int x = 0; x < n; x++)
				list[x] = rng.nextInt();

			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++) {
				int diff = findSmallestDiff(list);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.
			for(int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and calling findSmallestDiff.
			// Average it over the number of runs.

			long averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}

	public static void main(String[] args) {
		timeFindMax();
		//timeFindSmallestDiff();
	}
}