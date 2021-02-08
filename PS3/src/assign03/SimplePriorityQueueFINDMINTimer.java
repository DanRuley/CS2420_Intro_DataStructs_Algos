package assign03;

import java.util.Random;

@SuppressWarnings({ "rawtypes", "unchecked" })

public class SimplePriorityQueueFINDMINTimer {

	public static void main(String[] args) {
		
		
		int problemSize;
		
		int timesToLoop = 10000;
		
		Random rng = new Random();
		
		
	
		for (problemSize = 100000; problemSize <= 2000000; problemSize += 100000)
		{
		
		SimplePriorityQueue<Integer> timeQueue = new SimplePriorityQueue();
		
		for (int i = 0; i < problemSize; i++) {
			timeQueue.insert(problemSize - i);
		}
		
		
		

		long startTime, midpointTime, stopTime;

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		// Now, run the test.

		startTime = System.nanoTime();

		for (int i = 0; i < timesToLoop; i++) {
			int x = timeQueue.findMin();
		}

		midpointTime = System.nanoTime();

		// Run an empty loop to capture the cost of running the loop.

		for (int i = 0; i < timesToLoop; i++) { // empty block
			
		}
		
		stopTime = System.nanoTime();

		// Compute the time, subtract the cost of running the loop
		// from the cost of running the loop and computing square roots.
		// Average it over the number of runs.

		double averageTime = ((double)(midpointTime - startTime) - (double) (stopTime - midpointTime)) / (double) timesToLoop;
		
		System.out.println(averageTime);
		
		}
		
	}

}
