import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class ASF {
	public static void main(String[] args) {

		HashSet<Integer> factors = new HashSet<>();
		BigInteger num = new BigInteger("600851475143");
		
		for (int i = 1; i < 775146; i++) {
			
		}
		
		
		
	}

	
	public static boolean isPrime (int n) {
		
		for (int i = 2; i < Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		
		return true;
		
	}
}
