
package assign04;

import java.util.Arrays;
import java.util.Random;



public class GetLargestAnagramTimer {

	public static void main(String[] args) {

		int problemSize;

		int timesToLoop = 10;

		Random rng = new Random();


		String letters = "abcdefghijklmnopqrstuvwxyz";


		int incr = 100000;

		String[] add = new String[incr];
		
		
		
		for (int i = 0; i < incr; i++) {
			String s = "";
			for (int j = 1; j <= rng.nextInt(8); j++) {
				s = s.concat(Character.toString(letters.charAt(rng.nextInt(letters.length()))));
			}
			add[i] = s;
		}

		for (problemSize = 1000000; problemSize <= 20000000; problemSize += incr) {
			
			
			String[] Master = new String[problemSize];
			for (int i = 0; i < problemSize; i++) {
				Master[i] = add[rng.nextInt(incr)];
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
				String[] temp = Arrays.copyOf(Master, problemSize);
				String[] grams = AnagramChecker.getLargestAnagramGroup(temp);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (int i = 0; i < timesToLoop; i++) { // empty block
			
				String[] temp = Arrays.copyOf(Master, problemSize);
				String[] grams;
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

}
