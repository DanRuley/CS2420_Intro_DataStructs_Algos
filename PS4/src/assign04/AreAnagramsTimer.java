
package assign04;


import java.util.Random;


public class AreAnagramsTimer {

	public static void main(String[] args) {

		int problemSize;

		int timesToLoop = 1000;

		Random rng = new Random();

		String letters = "abcdefghijklmnopqrstuvwxyz";
		String one = "";
		String two = "";
		String oneMaster = "";
		String twoMaster = "";
		
		int incr = 1000;
		
		for (int i = 0; i < incr; i++) {
			
			//add random chars from letters String
			oneMaster+=Character.toString(letters.charAt(rng.nextInt(letters.length())));
			//make String two shorter so we can focus more on the N of string one
			if (i % 13 == 0)
				twoMaster+=Character.toString(letters.charAt(rng.nextInt(letters.length())));
			}
		
		for (int i = 0; i < 10; i ++)
			one = one.concat(oneMaster);
			two = two.concat(twoMaster);
		
			
		for (problemSize = 10000; problemSize <= 20000; problemSize += incr) {
			if (problemSize >10000) {
			one = one.concat(oneMaster);
			two = two.concat(twoMaster);
			
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
				boolean b = AnagramChecker.areAnagrams(one,two);
			}
			

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (int i = 0; i < timesToLoop; i++) { // empty block
				boolean b = false;
				
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
