package Euler;

import java.math.BigInteger;
import java.util.ArrayList;

public class Ten {
	
	
	
	public static void main(String[] args) {
		int sum = 0;
		BigInteger s = new BigInteger("0");
		ArrayList<Integer> al = new ArrayList<>();
		for (int i = 2; i < 2000000;i++) {
			if (isPrime(i)) {
				String x = ((Integer)i).toString();
				s = s.add(new BigInteger(x));
				sum += i;
				al.add(i);
			}
		}
		
		System.out.println(s);
		System.out.println(sum);
	}

	public static boolean isPrime(int n) {

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
