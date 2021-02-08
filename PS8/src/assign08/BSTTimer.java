package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class BSTTimer {


		public static void main(String[] args) {

			int problemSize = 1000;

			int timesToLoop = 1000;

			

			
			int incr = 1000;
			
			ArrayList<Double> addTimes = new ArrayList<>();
			ArrayList<Double> containsTimes = new ArrayList<>();
			
			ArrayList<Integer> master;
			//BinarySearchTree<Integer> tree = new BinarySearchTree<>();
			
			
			ArrayList<Integer> masterIncr = null;
			
			TreeSet<Integer> java = new TreeSet<>();
			
			for (problemSize = 1000; problemSize <= 100000; problemSize += incr) {
				
				master = generateAscending(problemSize);
				Collections.shuffle(master);
				
		
				
				
				

				
				
				long startTime, midpointTime, stopTime;

				
				
				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1000000000) { // empty block
				}

				// Now, run the test.

				startTime = System.nanoTime();

				java.addAll(master);
				
				
				midpointTime = System.nanoTime();

				for (int i: master) {
					
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.

				
				double addTime = ((double) (midpointTime - startTime) - (double) (stopTime - midpointTime))
						/ (double) timesToLoop;

				addTimes.add(addTime);
				
				
				
				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.

				
				
				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1000000000) { // empty block
				}

				// Now, run the test.

				startTime = System.nanoTime();

				
					for (int i = 0; i < timesToLoop; i++) {
						boolean b = (java.contains(master.size() - 1));
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.

				for (int i = 0; i < timesToLoop; i++) { // empty block
					
					int k = master.size() - 1;
					boolean b = false;
					
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.

				
				double averageTime = ((double) (midpointTime - startTime) - (double) (stopTime - midpointTime))
						/ (double) timesToLoop;

				containsTimes.add(averageTime);
				
				//System.out.println(averageTime);
				
				
			}
			
			System.out.println("ADD TIME:");
			for(Double d: addTimes) {
				System.out.println(d);
			}
			
			System.out.println();
			System.out.println();
			System.out.println("CONTAIN TIME:");
			
			for(Double d: containsTimes) {
				System.out.println(d);
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



	

