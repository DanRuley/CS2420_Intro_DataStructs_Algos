package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import assign05.ArrayListSorter;

/**
 * This class contains series of methods that check whether input words are
 * anagrams are not. Insertion sort is used to sort Strings lexicographically,
 * and then sort an array of strings and then finds the largest group of
 * anagrams.
 * @author Dan Ruley, Aric Woodliff 2019
 */
public class AnagramChecker {

	
	public static void main(String[] args) {
		
		
	}
	/**
	 * This method returns the lexicographically-sorted version of the input string.
	 * The sorting must be accomplished using an insertion sort.
	 */
	public static String sort(String input) {
		String result = input;
		char[] str = result.toCharArray();
		
		insSortCharArray(str);
		
		result = new String(str);
		return result;
	}

	
	/**
	 * Helper insertion sort method that sorts a in input char array lexicographically.
	 */
	public static void insSortCharArray(char[] stuff) {
		for (int i = 1; i < stuff.length; i++) {
			char toBeInserted = stuff[i];
			int j;
			for (j = i - 1; j >= 0 && stuff[j] > toBeInserted; j--) {
				stuff[j + 1] = stuff[j];
			}
			stuff[j + 1] = toBeInserted;
		}
		
	}
	
	
	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 */
	public static <T> void insertionSort(T[] inputArr, Comparator<? super T> arrComp) {

		for (int i = 1; i < inputArr.length; i++) {
			T toBeInserted = inputArr[i];
			int j;
			// j = i - 1, and i iterates to i < c.length, so j is always IN the array, no
			// bound exceptions.
			for (j = i - 1; j >= 0 && arrComp.compare(inputArr[j], toBeInserted) > 0; j--) {
				inputArr[j + 1] = inputArr[j];
			}
			inputArr[j + 1] = toBeInserted;
		}
	}

	/**
	 * This method returns true if the two input strings are anagrams of each other,
	 * otherwise returns false. This method calls the sort(String) method, and then
	 * compares the sorted strings for equality (case ignored).
	 */
	public static boolean areAnagrams(String s1, String s2) {

		s1 = sort(s1.toLowerCase());
		s2 = sort(s2.toLowerCase());

		return s1.equalsIgnoreCase(s2);

	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array. This method must call your areAnagrams(String,
	 * String) method and your insertionSort(T[], Comparator) method with a new
	 * Comparator class or lambda expression that you design.
	 */
	public static String[] getLargestAnagramGroup(String[] anagrams) {

		if (anagrams.length == 0 || anagrams.length == 1)
			returnEmptyArray();

		// Sort array using a lambda comparator that sorts the lowercase versions of the
		// strings and compares the results.
		
		insertionSort(anagrams, (String o1, String o2) -> AnagramChecker.sort(o1.toLowerCase())
				.compareTo(AnagramChecker.sort(o2.toLowerCase())));

		ArrayList<String> largestGroupSoFar = new ArrayList<>();
		ArrayList<String> currentGroup = new ArrayList<>();
		// Optimization loop that iterates through the array, looking for the largest
		// group of anagrams.
		int i;
		int j;

		for (i = 0; i < anagrams.length; i = j) {

			currentGroup = new ArrayList<String>();
			currentGroup.add(anagrams[i]);

			for (j = i + 1; j < anagrams.length; j++) {

				// Starting with i, keep checking the words at the j index. If they are
				// anagrams, keep adding them to the current group. Otherwise, break out of the
				// j loop since we've reached end of current group of anagrams.
				if (areAnagrams(anagrams[i], anagrams[j])) {
					currentGroup.add(anagrams[j]);
				}

				else {
					break;
				}
			}

			// if we've found a new best, set largest to current group.
			if (currentGroup.size() > largestGroupSoFar.size()) {
				largestGroupSoFar = new ArrayList<>();
				largestGroupSoFar.addAll(currentGroup);
			}
		}

		if (largestGroupSoFar.size() == 1)
			return returnEmptyArray();

		// Copy array list to String[]
		String[] result = new String[largestGroupSoFar.size()];
		int k = 0;
		for (String s : largestGroupSoFar) {
			result[k] = s;
			k++;
		}

		return result;
	}

	/**
	 * This method behaves the same as the previous method, but reads the list of
	 * words from the input filename. It is assumed that the file contains one word
	 * per line. If the file does not exist or is empty, the method returns an empty
	 * array because there are no anagrams. This method must call your
	 * getLargestAnagramGroup(String[]) method.
	 */
	public static String[] getLargestAnagramGroup(String filename) {

		ArrayList<String> anagramAL = new ArrayList<>();

		// Read the words out of the file with a scanner.
		try {
			Scanner anagramScanner = new Scanner(new File(filename));

			if (!anagramScanner.hasNext()) {
				anagramScanner.close();
				returnEmptyArray();
			}

			while (anagramScanner.hasNext()) {
				anagramAL.add(anagramScanner.next());
			}
			anagramScanner.close();
		}

		catch (FileNotFoundException e) {
			returnEmptyArray();
		}

		// Copy array list to String[]
		String[] result = new String[anagramAL.size()];
		int i = 0;
		for (String s : anagramAL) {
			result[i] = s;
			i++;
		}
		//call getLAG() and return the result.
		return getLargestAnagramGroup(result);
	}

	/**
	 * Helper method that returns an empty String array.
	 */
	private static String[] returnEmptyArray() {
		String[] empty = new String[0];
		return empty;
	}
	
	public static ArrayList<String> returnStrFromFile(String filename) {

		ArrayList<String> anagramAL = new ArrayList<>();

		// Read the words out of the file with a scanner.
		try {
			Scanner anagramScanner = new Scanner(new File(filename));

			if (!anagramScanner.hasNext()) {
				anagramScanner.close();
				returnEmptyArray();
			}

			while (anagramScanner.hasNext()) {
				anagramAL.add(anagramScanner.next());
			}
			anagramScanner.close();
		}

		catch (FileNotFoundException e) {
			returnEmptyArray();
		}
		
		return anagramAL;
	}
		
	
}
