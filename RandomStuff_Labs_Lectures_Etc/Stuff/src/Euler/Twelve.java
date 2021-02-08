package Euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Twelve {

	public static void main(String[] args) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		
		BigInteger tri = new BigInteger("1");
		BigInteger count = new BigInteger("2");
		BigInteger inc = new BigInteger("1");
		BigInteger den = new BigInteger("2");
		
		while(true) {
			tri = (count.multiply(count.add(inc)).divide(den));
			
			int n = getDivs(tri);
			

			if (n > 300) {
				System.out.println(tri.toString() + " " + n);
			}
			
			if (n > 500)
			{
				System.out.println("*********************!!!!!!!!!!********************");
				System.out.println(tri.toString());
			}
			count = count.add(inc);
		}
		
	}

	public static int getDivs(BigInteger n) {
		ArrayList<BigInteger> factors = new ArrayList<>();
		BigInteger i = new BigInteger("1");
		BigInteger inc = new BigInteger("1");
		while (true) {
			
			if (i.compareTo((n.divide(new BigInteger("2"))).add(inc)) == 0)
				break;
			if (n.mod(i).compareTo(new BigInteger("0")) == 0)
				factors.add(i);
			i = i.add(inc);
			
		}
		
		return factors.size() + 1;
	}
}
