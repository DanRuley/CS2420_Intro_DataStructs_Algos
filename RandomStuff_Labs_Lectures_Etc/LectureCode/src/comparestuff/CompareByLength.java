package comparestuff;

import java.util.Comparator;

/**
 * The purpose of this class is to provide a method for comparing strings by
 * length (instead of the natural ordering that is lexicographical and encoded
 * via Comparable).
 * 
 * Method comment is left for students to provide.
 * 
 * @author Erin Parker
 * @version January 23, 2019
 */
public class CompareByLength implements Comparator<String> {

	/**
	 * Compares two strings by length. returns a negative integer if s1 is shorter
	 * than s2, and a positive integer if s1 is longer than s2. If they are equal in
	 * length, returns the value of the natural lexicographical ordering.
	 */
	public int compare(String s1, String s2) {
		int diff = s1.length() - s2.length();

		// Break ties with the "natural" lexicographical ordering.
		if (diff == 0) {
			return s1.compareTo(s2);
		}

		return diff;
	}
}