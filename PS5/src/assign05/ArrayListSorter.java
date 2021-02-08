package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * This class implements generic quicksort and mergesort algorithms. It also
 * implements methods for generating ArrayLists of integers in ascending,
 * descending, and permuted order.
 * 
 * @authors Aric Woodliff, Dan Ruley
 * @version February 2019
 */
public class ArrayListSorter {

	
	public static void main(String[] args) {
		ArrayList<Integer> x = generatePermuted(10);
	}
	
	
	/** Toggles used to easily switch partition strategy for quick sort */
	private static boolean pivAtEnd = false;
	private static boolean pivAtStart = false;

	// Set pivot to median of three by default, as this was found to be optimal
	// through timing experiments.
	private static boolean pivMedOfThree = true;

	// value used to determine when merge sort switches to ins. sort.
	private static int threshold;

	// keeps track of the merge sort sublist size. If below threshold, will call
	// ins. sort
	private static int sublistSize;


	
	/**
	 * Public driver method for the mergesort. This method is generic and will work
	 * on any Type T that is a Comparable.
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> alMaster) {

		ArrayList<T> temp = new ArrayList<>();
		temp.addAll(alMaster);

		// Hard coded threshold value, found to be optimal value through timing
		// experiments.
		threshold = 10;

		msort(alMaster, temp, 0, alMaster.size() - 1);
	}
	
	

	/**
	 * Public driver method for the quicksort. This method is generic and will work
	 * on any Type T that is a Comparable.
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> al) {
		int left = 0;
		int right = al.size() - 1;

		quick(al, left, right);
	}

	/**
	 * This method is responsible for the "divide" phase of the mergesort. It
	 * recursively splits the input array into smaller and smaller sublists.
	 */
	private static <T extends Comparable<? super T>> void msort(ArrayList<T> alMaster, ArrayList<T> temp, int left,
			int right) {

		// Check if we should switch over to insertion sort here.
		sublistSize = right - left;
		if (sublistSize < threshold) {
			insertion(alMaster, left, right);
			return;
		}

		// Keep going with the recursion if left < right
		if (left < right) {
			int mid = (right + left) / 2;

			msort(alMaster, temp, left, mid);
			msort(alMaster, temp, mid + 1, right);
			merge(alMaster, temp, left, mid + 1, right);
		}

	}

	/**
	 * This method is responsible for the "conquer" phase of the merge sort. It
	 * merges the sub arrays together in sorted order.
	 */
	protected static <T extends Comparable<? super T>> void merge(ArrayList<T> alMaster, ArrayList<T> temp, int left,
			int mid, int right) {

		// A bunch of indices used to keep track of our conceptual sub arrays
		int leftCursor = left;
		int leftStart = left;
		int leftEnd = mid - 1;

		int rightCursor = mid;
		int rightEnd = right;
		int tempIndex = leftStart;

		while (leftCursor <= leftEnd && rightCursor <= rightEnd) {

			// Check values in left sublist vs. right sublist. Add the lesser to the temp
			// sublist.
			if (alMaster.get(leftCursor).compareTo(alMaster.get(rightCursor)) <= 0) {
				temp.set(tempIndex, alMaster.get(leftCursor));
				tempIndex++;
				leftCursor++;
			} else {
				temp.set(tempIndex, alMaster.get(rightCursor));
				tempIndex++;
				rightCursor++;
			}
		}

		// This is when all the items from left sublist are in temp, but items remain in
		// right sublist.
		if (leftCursor > leftEnd) {
			while (rightCursor <= rightEnd) {
				temp.set(tempIndex, alMaster.get(rightCursor));
				rightCursor++;
				tempIndex++;
			}
		}

		// This is when all items from right sublist are in temp, but items remain in
		// left sublist.
		else if (rightCursor > rightEnd) {
			while (leftCursor <= leftEnd) {
				temp.set(tempIndex, alMaster.get(leftCursor));
				leftCursor++;
				tempIndex++;
			}
		}

		// Copy sorted sublist back to master array
		for (int i = leftStart; i <= rightEnd; i++) {
			alMaster.set(i, temp.get(i));
		}

	}

	/**
	 * Helper method that implements an insertion sort on a generic array list. The
	 * insertion sort is only performed within the bounds defined by the indices
	 * left and right.
	 */
	private static <T extends Comparable<? super T>> void insertion(ArrayList<T> alMaster, int left, int right) {

		for (int i = left + 1; i <= right; i++) {
			T val = alMaster.get(i);
			int j;
			for (j = i - 1; j >= left && alMaster.get(j).compareTo(val) > 0; j--) {
				alMaster.set(j + 1, alMaster.get(j));

			}
			alMaster.set(j + 1, val);
		}
	}

	private static <T extends Comparable<? super T>> void quick(ArrayList<T> al, int left, int right) {
		if (left < right) {

			// Three pivot strategies can be easily toggled with private instance booleans
			int pivdex = 0;
			if (pivAtEnd) {
				pivdex = partitionLast(al, left, right);
			}
			if (pivAtStart) {
				pivdex = partitionStart(al, left, right);
			}
			if (pivMedOfThree) {
				pivdex = partitionMedianOfThree(al, left, right);
			}

			// Keep recursing if sublist size is > 0
			quick(al, left, pivdex - 1);
			quick(al, pivdex + 1, right);
		}

	}

	/**
	 * Helper method that partitions sublist, using the right value in sublist as
	 * the pivot.
	 */
	private static <T extends Comparable<? super T>> int partitionLast(ArrayList<T> al, int left, int right) {

		T pivot = al.get(right);
		int i = left - 1;

		for (int j = left; j < right; j++) {
			if (al.get(j).compareTo(pivot) <= 0) {
				i++;
				T tmp1 = al.get(i);
				al.set(i, al.get(j));
				al.set(j, tmp1);
			}
		}

		T tmp2 = al.get(i + 1);
		al.set(i + 1, al.get(right));
		al.set(right, tmp2);
		return i + 1;
	}

	/**
	 * This method partitions the sub list using the item at the left index of the
	 * sublist as a pivot value.
	 */
	protected static <T extends Comparable<? super T>> int partitionStart(ArrayList<T> al, int left, int right) {

		T pivot = al.get(left);
		int i = right + 1;

		for (int j = right; j > left; j--) {
			if (al.get(j).compareTo(pivot) >= 0) {
				i--;
				T temp = al.get(i);
				al.set(i, al.get(j));
				al.set(j, temp);
			}
		}

		T temp = al.get(i - 1);
		al.set(i - 1, al.get(left));
		al.set(left, temp);

		return i - 1;
	}

	/**
	 * This method uses the "median of three" strategy to partian the sub list. Once
	 * the median is found, if it is not already at the right index, it swaps it
	 * there and then calls the partitionLast method.
	 */
	private static <T extends Comparable<? super T>> int partitionMedianOfThree(ArrayList<T> al, int left, int right) {

		int mid = (left + right) / 2;
		T pivotMid = al.get(mid);
		T pivotLeft = al.get(left);
		T pivotRight = al.get(right);

		// Case where the value at middle index is the median of three.
		if ((pivotMid.compareTo(pivotLeft) > 0 && pivotMid.compareTo(pivotRight) < 0)
				|| (pivotMid.compareTo(pivotLeft) < 0 && pivotMid.compareTo(pivotRight) > 0)) {
			T temp = pivotMid;
			al.set(mid, pivotRight);
			al.set(right, temp);
		}
		// Case where the value at left index is the median of three.
		if ((pivotLeft.compareTo(pivotMid) > 0 && pivotLeft.compareTo(pivotRight) < 0)
				|| (pivotLeft.compareTo(pivotMid) < 0 && pivotLeft.compareTo(pivotRight) > 0)) {
			T temp = pivotLeft;
			al.set(left, pivotRight);
			al.set(right, temp);
		}

		// Case where the value at left index is the median of three: Do nothing, since
		// if the first and middle are not the medians, the last must be.

		return partitionLast(al, left, right);
	}

	/** Generates an ArrayList of integers in ascending order. */
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> ascending = new ArrayList<>();
		for (int i = 1; i <= size; i++) {
			ascending.add(i);
		}
		return ascending;
	}

	/** Generates an ArrayList of integers in descending order. */
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> descending = new ArrayList<>();
		for (int i = size; i >= 1; i--) {
			descending.add(i);
		}
		return descending;
	}

	/** Generates an ArrayList of integers in permuted order. */
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> permuted = generateAscending(size);
		Collections.shuffle(permuted);
		return permuted;
	}
}
