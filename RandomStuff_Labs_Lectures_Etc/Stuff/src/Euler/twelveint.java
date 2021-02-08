package Euler;

public class twelveint {

	public static void main(String[] args) {

		long triangle = 0;
		for (long i = 1; i < Long.MAX_VALUE; i++) {

			triangle = triangle + i;

			// long n = getFactNum(triangle);
			long n = 0;
			String s = "" + triangle;
			if (s.charAt(s.length() - 1) == '0') {
			n = getFactNum(triangle);

			if (n > 300)
				System.out.println(triangle + " " + n);

			if (n > 500) {
				System.out.println("*****************************");
				System.out.println(triangle);
				break;
			}
			}

		}

	}

	public static long getFactNum(long n) {

		long count = 0;
		long currentNum = 0;

		for (long i = 1; i <= n; i++) {
			if (n % i == 0) {
				currentNum = n / i;
				count++;
			}
				if (currentNum == i)
					break;
				else if (currentNum < i) {
					count*=2;
					break;
				}
					
		}
		System.out.println(Integer.reverse(12345));
		return count;

	}

}
