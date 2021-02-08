package Euler;

import java.math.BigInteger;

public class Sixteen {

	public static void main(String[] args) {
		
		int sum = 0;
		
		for (char c: (new BigInteger("2").pow(1000)).toString().toCharArray()) 
			sum += Character.getNumericValue(c);
		
		System.out.println(sum);
	}
}
