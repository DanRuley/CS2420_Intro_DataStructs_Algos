package comprehensive;

import java.util.ArrayList;
import java.util.Random;


public class GrammarTimer {

	public static void main(String[] args) {
		String x;
		String y;

		Random r = new Random();

		ArrayList<String> randStrings = new ArrayList<>();
		ArrayList<String> moreStrings = new ArrayList<>();
		char[] c = new char[26];

		for (int i = 0; i < 26; i++) {

			c[i] = (char) (97 + i);

		}

		for (int i = 0; i < 200000; i++) {

			x = "";

			y = "";

			for (int j = 0; j < r.nextInt(7) + 2; j++) {

				x += c[r.nextInt(26)];

			}

			for (int j = 0; j < r.nextInt(7) + 2; j++) {

				y += c[r.nextInt(26)];

			}
			randStrings.add(x);

			moreStrings.add(y);
		}

		

		int problemSize;
		int timesToLoop = 100;
		int incr = 1000;

		for (problemSize = 10000; problemSize <= 200000; problemSize += incr) {

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			for (int i = 0; i < problemSize; i++) {

			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (int i = 0; i < problemSize; i++) {

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
