package Euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Thirteen {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("src/Euler/bignums"));
		BigInteger sum = new BigInteger("0");
		while (s.hasNextLine()) {
			sum = sum.add(new BigInteger(s.nextLine()));
		}
		System.out.println(sum);
	}
}
