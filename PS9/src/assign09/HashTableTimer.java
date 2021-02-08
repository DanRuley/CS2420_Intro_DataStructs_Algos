package assign09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

/**
 * Class used to time Hash Table vs. Java Hash Map 
 * @authors Dan Ruley, Subhan Gulistani
 * @version March 2019
 */
public class HashTableTimer {

	public static void main(String[] args) {

		int problemSize = 1000;

		int timesToLoop = 1000;

		int incr = 10000;

		ArrayList<Double> Times = new ArrayList<>();

		ArrayList<String> masterKeys = new ArrayList<>();
		ArrayList<Integer> masterVals = new ArrayList<>();

		String x = "";
		String y = "";
		int n = 0;
		
		Random r = new Random();
		r.setSeed(314);

		char[] c = new char[26];

		for (int i = 0; i < 26; i++) {
			c[i] = (char) (97 + i);
		}

		for (int i = 0; i < 200000; i++) {
			x = "";
			for (int j = 0; j < r.nextInt(7) + 2; j++) {
				x += c[r.nextInt(26)];
			}
			masterKeys.add(x);
			masterVals.add(r.nextInt(1000000));
		}
		
		System.out.println();
		System.out.println("***HASH TABLE***");
		System.out.println();
	for (problemSize = 1000; problemSize <= 200000; problemSize += incr) {

			HashTable<String, Integer> ht = new HashTable<>();

			for(int i = 0; i < problemSize; i++) {
				ht.put(masterKeys.get(i), masterVals.get(i));
			}
			
			ht.put("Hello", 12345);
			
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				ht.put("Hello", 12345);
				ht.remove("Hello");
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
		
		System.out.println();
		System.out.println("***JAVA HASH MAP***");
		System.out.println();

		for (problemSize = 10000; problemSize <= 200000; problemSize += incr) {

			HashMap<String, Integer> ht = new HashMap<>();

			for(int i = 0; i < problemSize; i++) {
				ht.put(masterKeys.get(i), masterVals.get(i));
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
				ht.put("Hello", 12345);
				ht.remove("Hello");
				
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
