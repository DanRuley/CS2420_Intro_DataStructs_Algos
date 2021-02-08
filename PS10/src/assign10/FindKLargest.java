package assign10;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Erin Parker
 * @version April 3, 2019
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		if(k < 0 || k > items.size()) {
			throw new IllegalArgumentException("Error: k value is outside acceptable limit (0<k<=list size)");
		}
		
		BinaryMaxHeap<E> bmh = new BinaryMaxHeap<E>(items);
		List<E> lst = new LinkedList<E>();
		
		for(int i = 0; i < k ; i++) {
			lst.add(bmh.extractMax());
		}
		
		return lst;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if(k < 0 || k > items.size()) {
			throw new IllegalArgumentException("Error: k value is outside acceptable limit (0<k<=list size)");
		}
		
		BinaryMaxHeap<E> bmh = new BinaryMaxHeap<E>(items,cmp);
		List<E> lst = new LinkedList<E>();
		
		for(int i = 0; i <= k ; i++) {
			lst.add(bmh.extractMax());
		}
		return lst;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		if(k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}
		
		List<E> lst = new LinkedList<E>();
		
		Collections.sort(items);
		
		for(int i = items.size() - 1; i > items.size() - k - 1; i--) {
			lst.add(items.get(i));
		}
		
		return lst;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if(k < 0 || k > items.size()) {
			throw new IllegalArgumentException("Error: k value is outside acceptable limit (0<k<=list size)");
		}
		
		List<E> lst = new LinkedList<E>();
		
		items.sort(cmp);
		
		for(int i = items.size() - 1; i > items.size() - k - 1; i--) {
			lst.add(items.get(i));
		}
		
		return lst;
	}
}