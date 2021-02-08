package Euler;

import java.util.ArrayList;

public class Thirty {

	public static void main(String[] args) {

		ArrayList<Long> nums = new ArrayList<>();
		for (long i = 2; i < 1000000; i++) {
			if (i == fifthPow(i)) {
				nums.add(i);
			}
		}
		
		long result = 0;
		for (Long l : nums) {
			result += l;
		}
		
		System.out.println(result);
	}

	public static long fifthPow(long n) {
		String s = Long.toString(n);
		long sum = 0;
		for (int i = 0 ; i < s.length(); i++) {
			long x = Character.getNumericValue(s.charAt(i));
			sum += (x * x * x * x * x);
		}
		
		return sum;
	}
	
	public static long fourthPow(long n) {
		String s = Long.toString(n);
		long sum = 0;
		for (int i = 0 ; i < s.length(); i++) {
			long x = Character.getNumericValue(s.charAt(i));
			sum += (x * x * x * x);
		}
		
		return sum;
	}
}
