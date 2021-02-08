package Euler;

public class Five {

	public static void main(String[] args) {
		int result = -1;
		for (int i = 232792560; i < 1000000000; i++) {
			if (multiples(i)) {
				result = i;
				break;
			}
		}
		System.out.println(result);
	}

	public static boolean multiples(int num) {

		for (int i = 2; i <= 20; i++) {
			int quotient = num / i;
			int modulus = num % i;
			if (modulus != 0)
				return false;
		}

		return true;
	}
}
