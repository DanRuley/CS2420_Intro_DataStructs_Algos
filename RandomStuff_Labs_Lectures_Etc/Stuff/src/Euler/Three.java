package Euler;

import java.math.BigInteger;
import java.util.HashSet;

public class Three {
	public static void main(String[] args) {

		HashSet<Integer> factors = new HashSet<>();
		BigInteger num = new BigInteger("600851475143");
		
		for (int i = 1; i < 775146; i++) {
			BigInteger modulus = num.mod(new BigInteger(Integer.toString(i)));
	
			
			if (modulus.intValue() == 0)
				if (isPrime(i))
					factors.add(i);
	
		}

		System.out.println("wahoo");
	}

	public static boolean isPrime(int n) {

		for (int i = 2; i < Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}

		return true;

	}

}
