package comprehensive;

import java.io.File;
import java.util.Random;

public class MoreLoopTimer {
 
		static final int incr = 100000;
		static final int timesToLoop = 100;
		
		Random r = new Random(314);
		
		
		public static void main(String[] args) {
		int problemSize;
		
		GrammarParser g = new GrammarParser(new File("src/comprehensive/assignment_extension_request.g"));
		
	
		
		for (problemSize = 100000; problemSize <= 2000000; problemSize+=incr) {
			
			
			
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();
			
			for (int i = 0; i < problemSize; i++) {
				g.parseRule(g.gMap.get("<start>").get(0));
				//System.out.println(g.fetchString());
				g.reset();
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


