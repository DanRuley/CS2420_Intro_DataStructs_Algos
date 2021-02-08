import java.util.ArrayList;

public class EulerFour {

	public static void main(String[] args) {

		int count = 0;
		int max = 0;

		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {

				int n = i * j;
				if (isPalindrome(n))
					if (n > max)
						max = n;
			}
		}
		System.out.println(max);

	}

	public static boolean isPalindrome(int p) {

		String s = "" + p;
		boolean palindrome = false;

		for (int i = 0; i < s.length() / 2; i++) {
			char c = s.charAt(i);
			char c1 = s.charAt(s.length() - 1 - i);
			palindrome = c == c1;
			if (palindrome == false)
				break;
		}

		return palindrome;
	}

}
