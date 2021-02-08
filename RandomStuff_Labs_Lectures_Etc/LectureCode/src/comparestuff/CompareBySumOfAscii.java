package comparestuff;

import java.util.Comparator;

/**
 * The purpose of this class is to provide a method for comparing strings by the
 * sum of the ASCII codes for the characters in the string (instead of the
 * natural ordering that is lexicographical and encoded via Comparable).
 * 
 * Method comments are left for students to provide.
 *
 * @author Erin Parker
 * @version January 23, 2019
 */
public class CompareBySumOfAscii implements Comparator<String> {

	/**
	 * Compares two strings by ascii values. Returns a negative value if String 1 is
	 * less than String 2, 0 if they are equal, and a positive value if String 1 is
	 * greater than String 2.
	 */
	public int compare(String o1, String o2) {
		return sumOfAscii(o1) - sumOfAscii(o2);
	}

	/**
	 * Returns the integer sum of the ascii values of all characters contained in
	 * the input string.
	 */
	private int sumOfAscii(String s) {
		int total = 0;
		for (int i = 0; i < s.length(); i++) {
			total += s.charAt(i);
		}
		return total;
	}
}