package Euler;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

public class TwentyOne {

	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<>();
		ArrayList<Point> a = new ArrayList<>();
		for (int i = 1; i <= 10000; i++) {
			int n = sumOfFactors(i);
			
			if (sumOfFactors(n) == i && n != i) {
				hs.add(n);
				hs.add(i);
				a.add(new Point (i,n));
			}
		}
		
		int sum = 0;
		for (Integer i : hs) {
			sum += i;
		}
		System.out.println(sum);
	}
	
	public static int sumOfFactors(int n) {
		int sum = 0;
		for (int i = 1; i <= n/2 + 1; i++) {
			if (n % i == 0) {
				sum += i;
			}
		}
		return sum;
	}
	
}
