package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class MoreNonTermsTimer {

	public static void main(String[] args) {
		
		try {

			int problemSize = 5;
			int timesToLoop = 100000;

			Random r = new Random(314);
			char[] chars = new char[26];
			String x = "";
			ArrayList<String> randomStrings = new ArrayList<>();
			ArrayList<String> nonTs = new ArrayList<>();

			for (int i = 0; i < 26; i++) {
				chars[i] = (char) (i + 97);
				nonTs.add("<" + chars[i] + ">");
			}

			for (int i = 0; i < 10000; i++) {

				x = "";
				for (int j = 0; j < 10; j++) {
					x += chars[r.nextInt(26)];
				}
				randomStrings.add(x + " ");
			}

			File f = new File("src/comprehensive/TimingGrammar");

			int incr = 1;
			for (problemSize = 5; problemSize <= 26; problemSize += incr) {
				PrintWriter w = new PrintWriter(new File("src/comprehensive/TimingGrammar"));

				String nl = "\n";

				String startProd = "";
				for (int i = 0; i < problemSize; i++) {
					startProd = startProd + (nonTs.get(i) + " ");
				}

				w.write("{" + nl);
				w.write("<start>" + nl);
				w.write(startProd + nl);
				w.write("}" + nl);
				w.flush();

				for (int i = 0; i < problemSize; i++) {
					w.write(nl);
					w.write("{" + nl);
					w.write(nonTs.get(i) + nl);
					for (int j = 0; j < 10; j++) {
						w.write(randomStrings.get(r.nextInt(10000)) + nl);
					}
					w.write(randomStrings.get(r.nextInt(10000)) + " " + nonTs.get(i) + nl);
					w.write("}" + nl);
					w.flush();
				}
				
				GrammarParser gp = new GrammarParser(f);

				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.

				startTime = System.nanoTime();

				while (System.nanoTime() - startTime < 1000000000) { // empty block
				}

				// Now, run the test.

				startTime = System.nanoTime();

				for (int i = 0; i < timesToLoop; i++) {
					gp.parseRule(gp.gMap.get("<start>").get(0));
					//System.out.println(gp.fetchString());
					gp.reset();
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.

				for (int i = 0; i < timesToLoop; i++) {
					

				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.

				double averageTime = ((double) (midpointTime - startTime) - (double) (stopTime - midpointTime))
						/ (double) timesToLoop;

				System.out.println(averageTime );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
