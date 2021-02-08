package Euler;

import java.util.ArrayList;

public class Fourteen {

	/**
	 * 
	 * 
	 * The following iterative sequence is defined for the set of positive integers:
	 * 
	 * n → n/2 (n is even) n → 3n + 1 (n is odd)
	 * 
	 * Using the rule above and starting with 13, we generate the following
	 * sequence:
	 * 
	 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1 It can be seen that this sequence
	 * (starting at 13 and finishing at 1) contains 10 terms. Although it has not
	 * been proved yet (Collatz Problem), it is thought that all starting numbers
	 * finish at 1.
	 * 
	 * Which starting number, under one million, produces the longest chain?
	 * 
	 * NOTE: Once the chain starts the terms are allowed to go above one million.
	 * 
	 */
	public static void main(String[] args) {

		long max = 0;
		long num = 0;
		for (long i = 1; i < 1000000; i++) {
			ArrayList<Long> terms = collatz(i);
			if (terms.size() > max) {
				max = terms.size();
				num = i;
			}
//			for (int j : terms) {
//				System.out.print(j + " ");
//			}
//			System.out.println("---" + " " + terms.size());
		}
		System.out.println(num + " has: " + max + " terms in its Collatz sequence.");
	}

	public static ArrayList<Long> collatz(long n) {

		ArrayList<Long> terms = new ArrayList<>();
		terms.add(n);
		while (n > 1) {
			if (n % 2 == 0) {
				n = n / 2;
				terms.add(n);
			} else {
				n = (3 * n) + 1;
				terms.add(n);
			}
		}

		return terms;
	}

}
