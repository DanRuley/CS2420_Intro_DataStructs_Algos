import java.util.ArrayList;

public class quiz3 {

	public static void main(String[] args) {

		ArrayList<Integer> primes = new ArrayList<>();
		
		for (int i = 1; i <= 1000; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		
		System.out.println("There are " + primes.size() + " Prime numbers from 1 to 1000:");
		for (int i = 0; i < primes.size(); i++) {
			System.out.print(primes.get(i) + " ");
			if (primes.get(i) < 100) {
				System.out.print(" ");
			}
			if (i % 25 == 0 && i != 0) {
				System.out.println();
			}
		}
		System.out.println("\n" + primes.size());
		
	}

	public static boolean isPrime(int n) {
		int numberOfDivisors = 0;

		for (int i = 1; i <= 100000; ++i) {
			if (n % i == 0) {
				numberOfDivisors++;
			}
		}
		return numberOfDivisors == 2;
	}
}
