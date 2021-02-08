package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Timing class for comparing find k largest sort vs. heap methods.
 * @authors Dan Ruley, Aric Woodliff
 * @version April 2019
 */
public class HeapVsJavaSortTimer {
	static final int incr = 10000;
	static final int timesToLoop = 100;
	
	static final Random r = new Random(314);
	
	
	public static void main(String[] args) {
	int problemSize;
	
	
	
	List<Integer> kLargest;
	int k;
	ArrayList<Integer> master = generateRandomArray();
	BinaryMaxHeap<Integer> heap;
	
	for (problemSize = 10000; problemSize <=200000; problemSize+=incr) {
		
		k = 1000;
		if(problemSize > 10000)
			master.addAll(generateRandomArray());
		
		
		long startTime, midpointTime, stopTime;

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Now, run the test.

		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++) {
//			ArrayList<Integer> copy = new ArrayList<>();
//			copy.addAll(master);
//			kLargest = FindKLargest.findKLargestSort(copy, k);
			kLargest = FindKLargest.findKLargestHeap(master, k);
			
		}

		midpointTime = System.nanoTime();

		// Run an empty loop to capture the cost of running the loop.

		for (int i = 0; i < timesToLoop; i++) { // empty block
//			ArrayList<Integer> copy = new ArrayList<>();
//			copy.addAll(master);
		}

		stopTime = System.nanoTime();

		// Compute the time, subtract the cost of running the loop
		// from the cost of running the loop and computing square roots.
		// Average it over the number of runs.

		double averageTime = ((double) (midpointTime - startTime) - (double) (stopTime - midpointTime))
				/ (double) timesToLoop;

		System.out.println(averageTime);
		
	}
	
	
	
	}
	
	public static ArrayList<Integer> generateRandomArray() {
		ArrayList<Integer> random = new ArrayList<>();
		for (int i = 0; i < incr; i++) {
			random.add(r.nextInt(1000000));
		}

		return random;
	}

}
