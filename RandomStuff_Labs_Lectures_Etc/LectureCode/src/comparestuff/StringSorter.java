package comparestuff;

import java.util.Arrays;
import java.util.Random;

/**
 * This program sorts an array of strings, using various "orderings".
 * 
 * @author Erin Parker
 * @version January 23, 2019
 */
public class StringSorter {

	public static void main(String[] args) {
		String[] arr = buildArray(1000, 10);
		
		/* This call to sort orders lexicographically (i.e., almost alphabetically).
		   Because this is the "natural" ordering for Strings, we are all set. */
		Arrays.sort(arr);
		printArray(arr);
		System.out.println("\n\n");
		
		/* This call to sort orders by string length (shortest strings first).
		   Because this is not the "natural" ordering for Strings, we must build
		   and use a new Comparator. */
		Arrays.sort(arr, new CompareByLength());
		printArray(arr);
		System.out.println("\n\n");
		
		// This call to sort orders by sum of ASCII codes of the characters.
		Arrays.sort(arr, new CompareBySumOfAscii());
		printArray(arr);
		System.out.println("\n\n");
	}
	
	/** 
	 * Builds a basic array of random strings.
	 * 
	 * @param arrSize - Length of the array to build.
	 * @param maxStringSize - Limit on the length of each string.
	 * @return An array of randomly-generated strings, each of various lengths.
	 */
	private static String[] buildArray(int arrSize, int maxStringSize) {
		String[] result = new String[arrSize];
		Random rng = new Random();
		
		for(int i = 0; i < arrSize; i++) {
			String str = "";
			int strLen = rng.nextInt(maxStringSize) + 1;
			for(int j = 0; j < strLen; j++) {
				str += (char)('a' + rng.nextInt(26));
			}
			result[i] = str;
		}
		
		return result;
	}
	
	/** 
	 * Prints the strings in an array, in order and one per line.
	 *  
	 * @param arr - The array of strings.
	 */
	private static void printArray(String[] arr) {
		for(String str: arr) {
			System.out.println(str);
		}
	}
}