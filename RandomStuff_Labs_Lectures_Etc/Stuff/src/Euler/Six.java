package Euler;

import java.util.ArrayList;
import java.util.HashSet;

public class Six {

	public static void main(String[] args) {
		
		ArrayList<Integer> primes = new ArrayList<>();
		int x = 3;
		primes.add(2);
		
		while (primes.size() <= 10001) {
			if (isPrime(x))
				primes.add(x);
			x++;
		}
		System.out.println(primes.get(10000));
		
	}
	
	
	public static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if ( n % i == 0)
				return false;
		}
		return true;
	}
	
}
