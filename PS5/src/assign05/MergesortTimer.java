package assign05;

import java.util.ArrayList;
import java.util.Random;

public class MergesortTimer {

	public static void main(String[] args) {

		int problemSize = 100000;

		int timesToLoop = 10;

		

		int incr = 100000;
		
		ArrayList<Integer> temp = new ArrayList<>();
		ArrayList<Integer> tempSubtract = new ArrayList<>();
		
		ArrayList<Integer> master = ArrayListSorter.generatePermuted(problemSize);
		ArrayList<Integer> masterIncr = ArrayListSorter.generatePermuted(incr);
		
		for (problemSize = 1000000; problemSize <= 2900000; problemSize += incr) {
			
			if (problemSize > 1000000)
				master.addAll(masterIncr);
			
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				temp = new ArrayList<>();
				temp.addAll(master);
				//System.out.println(i);
				ArrayListSorter.mergesort(temp);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (int i = 0; i < timesToLoop; i++) { // empty block
				tempSubtract = new ArrayList<>();
				tempSubtract.addAll(master);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((double) (midpointTime - startTime) - (double) (stopTime - midpointTime))
					/ (double) timesToLoop;

			System.out.println(averageTime);
			
		}
		
		String x = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
		for (int i = 0; i < 10; i++) {
			System.out.println(x);
		}

	}

}


