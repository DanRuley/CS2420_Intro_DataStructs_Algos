package Euler;

import java.math.BigInteger;
import java.util.ArrayList;

public class TwentyFive {

	public static void main(String[] args) {
		BigInteger x = new BigInteger("1");
		BigInteger y = new BigInteger("1");
		BigInteger b = new BigInteger("0");
		ArrayList<BigInteger> a = new ArrayList<>();
		a.add(x);
		a.add(y);
		String num = b.toString();
		while (num.length() < 1000) {
			b = x.add(y);
			a.add(b);
			num = b.toString();
			x = y;
			y = b;
		}
		System.out.println(a.size() - 1);
	}
}
