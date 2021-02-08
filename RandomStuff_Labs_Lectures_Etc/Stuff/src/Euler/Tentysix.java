package Euler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Tentysix {

	public static void main(String[] args) {
		
		MathContext m = new MathContext(100);
		BigDecimal b = new BigDecimal(new BigInteger("5"), 10, m);
		System.out.println(b.toString());
		
		double n = 1;
		double d = 1;
		
		while (d < 1000) {
			int x = (int)d;
			System.out.println("Denominator: " + x + " Result: " + n / d);
			d++;
		}
		
		
	}
	
	
}
