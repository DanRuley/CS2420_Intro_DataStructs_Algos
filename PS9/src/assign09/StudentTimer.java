package assign09;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class used to time the StudentXHashcode classes. 
 * @authors Dan Ruley, Subhan Gulistani
 * @version March 2019
 */
public class StudentTimer {

	public static void main(String[] args) {

		int problemSize = 10000;

		int timesToLoop = 1000;

		int incr = 1000;

		ArrayList<String> firstNames = new ArrayList<>();
		ArrayList<String> lastNames = new ArrayList<>();
		ArrayList<Integer> masterVals = new ArrayList<>();

		String x = "";

		String y = "";

		int n = 0;
		Random r = new Random();
		r.setSeed(314);

		ArrayList<Integer> colls = new ArrayList<>();
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
			
			firstNames.add(x);
			lastNames.add(y);
			masterVals.add(r.nextInt(1000000));
		}
		
		for (problemSize = 1000; problemSize <= 100000; problemSize += incr) {

			StudentGoodHash s = new StudentGoodHash(12345, "Bob", "Ross");
			HashTable<StudentGoodHash, Integer> ht = new HashTable<>();
			long startTime, midpointTime, stopTime;

			for (int i = 0; i < problemSize; i ++) {
				ht.put(new StudentGoodHash(masterVals.get(i), firstNames.get(i), lastNames.get(i)), masterVals.get(i));
			}
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				ht.put(s, 12345);
				ht.remove(s);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (int z = 0; z < timesToLoop; z++) { // empty block
				
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


	
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			result.add(i);
		}
		return result;
	}

}