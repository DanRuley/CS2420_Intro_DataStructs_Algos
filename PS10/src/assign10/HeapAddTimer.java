package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Timing class for the add method of BinaryMaxHeap.
 * @authors Dan Ruley, Aric Woodliff
 * @version April 2019
 */
public class HeapAddTimer {
	static final int incr = 100000;
	static final int timesToLoop = 1000;
	
	static final Random r = new Random(314);
	
	
	public static void main(String[] args) {
	
	int problemSize;
	
	
	
	
	ArrayList<Integer> master = generateRandomArray();
	BinaryMaxHeap<Integer> heap;
	
	for (problemSize = 100000; problemSize <=2000000; problemSize+=incr) {
		
		if(problemSize > 100000) {
			master.addAll(generateRandomArray());
		}
		
		heap = new BinaryMaxHeap<>(master);
		
		
		
		
		long startTime, midpointTime, stopTime;

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Now, run the test.

		long totalTime = 0;
		startTime = System.nanoTime();
		
		for (int i = 0; i < timesToLoop; i++) {
			
			startTime = System.nanoTime();
			heap.add(10000000);
			totalTime += System.nanoTime() - startTime;
			heap.extractMax();
		}

		midpointTime = System.nanoTime();

		// Run an empty loop to capture the cost of running the loop.

		for (int i = 0; i < timesToLoop; i++) { // empty block
			
		}

		stopTime = System.nanoTime();

		// Compute the time, subtract the cost of running the loop
		// from the cost of running the loop and computing square roots.
		// Average it over the number of runs.

		double averageTime = (double) totalTime / (double) timesToLoop;
		
//		double averageTime = ((double) (midpointTime - startTime) - (double) (stopTime - midpointTime))
//				/ (double) timesToLoop;

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
