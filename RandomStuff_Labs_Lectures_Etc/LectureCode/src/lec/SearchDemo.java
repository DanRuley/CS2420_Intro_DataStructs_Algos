package lec;

import java.util.Arrays;
import java.util.Random;

/**
 * Contains search methods described in Lec05 slides,
 * as well as methods for collecting running times.
 * 
 * @author Erin Parker
 * @version January 23, 2019
 */
public class SearchDemo {

	private static final int PROB_SIZE_START = 10000;
	private static final int PROB_SIZE_END = 150000;
	private static final int PROB_SIZE_STEP = 10000;
	private static final int TIMES_TO_LOOP = 10000;

	/**
	 * Sequential search O(N)
	 * 
	 * GENERIC -- works for any reference type
	 * 
	 * @param arr
	 *            - the input array
	 * @param item
	 *            - the item to search for
	 * @return true if the item is contained in the input array
	 */
	public static <T> boolean sequentialSearch(T[] arr, T item) {
		for(T x : arr)
			if(x.equals(item))
				return true;

		return false;
	}

	/**
	 * Binary search O(logN)
	 * 
	 * NON-GENERIC version -- works only for type int ITERATIVE version -- uses
	 * a loop, not recursion
	 * 
	 * @param arr - the input array
	 * @param item - the item to search for
	 * @return true if the item is contained in the input array
	 */
	public static boolean binarySearch(int[] arr, int item) {
		int low = 0;
		int high = arr.length;

		while(low < high) {
			int mid = (low + high) / 2;

			if(arr[mid] == item) {
				return true;
			}

			if(arr[mid] < item) {
				low = mid + 1;
			}
			else {
				high = mid;
			}
		}

		return false;
	}

	// A generic, iterative version of binarySearch is left for student to implement
	// in Assignment 3.

	// Recursive versions of binarySearch will be discussed in a future lecture.
	
	public static void timeSequentialSearch() {
		
		Random rng = new Random();

		System.out.println("Sequential search\nN\ttime(ns)");

		// Experiment for specified problem sizes
		for(int probSize = PROB_SIZE_START; probSize <= PROB_SIZE_END; probSize += PROB_SIZE_STEP) {

			// Generate a sorted array of random integers
			Integer[] arr = new Integer[probSize];

			for(int i = 0; i < arr.length; i++) {
				arr[i] = rng.nextInt(PROB_SIZE_END);
			}
			Arrays.sort(arr);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times

			startTime = System.nanoTime();

			for(int i = 0; i < TIMES_TO_LOOP; i++) {
				// Generate a random integer to search for
				int item = rng.nextInt(PROB_SIZE_END);

				boolean flag = sequentialSearch(arr, item);
			}

			midpointTime = System.nanoTime();

			// Run to capture the cost of loop and generating random integer
			for(int i = 0; i < TIMES_TO_LOOP; i++) { 
				// Generate random integer 
				int item = rng.nextInt(PROB_SIZE_END);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop and
			// generating a random integer from the cost of running the loop, 
			// generating a random integer, and searching.
			// Average it over the number of runs.

			long averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / TIMES_TO_LOOP;

			System.out.println(probSize + "\t" + averageTime);
		}
	}

	public static void timeBinarySearch() {
		
		Random rng = new Random();

		System.out.println("Binary search\nN\ttime(ns)");

		// Experiment for specified problem sizes
		for(int probSize = PROB_SIZE_START; probSize <= PROB_SIZE_END; probSize += PROB_SIZE_STEP) {

			// Generate a sorted array of random integers
			int[] arr = new int[probSize];

			for(int i = 0; i < arr.length; i++) {
				arr[i] = rng.nextInt(PROB_SIZE_END);
			}
			Arrays.sort(arr);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times

			startTime = System.nanoTime();

			for(int i = 0; i < TIMES_TO_LOOP; i++) {
				// Generate a random integer to search for
				int item = rng.nextInt(PROB_SIZE_END);

				boolean flag =binarySearch(arr, item);
			}

			midpointTime = System.nanoTime();

			// Run to capture the cost of loop and generating random integer

			for(int i = 0; i < TIMES_TO_LOOP; i++) {
				// Generate random integer to search for
				int item = rng.nextInt(PROB_SIZE_END);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop and
			// generating a random integer from the cost of running the loop, 
			// generating a random integer, and searching.
			// Average it over the number of runs.
			
			long averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / TIMES_TO_LOOP;

			System.out.println(probSize + "\t" + averageTime);
		}
	}

	public static void main(String[] args) {
		timeSequentialSearch();
		timeBinarySearch();
	}
}
