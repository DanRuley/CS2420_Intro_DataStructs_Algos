package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for the FindKLargest class.
 * @authors Dan Ruley, Aric Woodliff
 * @version April 2019
 */
class FindKLargestTester {
	private ArrayList<Integer> asc;
	private Random rng = new Random(314);
	char[] chars;
	ArrayList<String> randomStrings;

	@BeforeEach
	void setup() {
		asc = generateAscending(100);
		
		chars = new char[26];
		for (int i = 0; i < 26; i++) {
			chars[i] = (char) (i + 97);
		}

		randomStrings = new ArrayList<>();
		String x = "";

		for (int i = 0; i < 1000; i++) {
			x = "";
			int size = rng.nextInt(100);
			
			for (int j = 0; j < size + 1; j++) {
				x += (char) (rng.nextInt(26) + 97);
			}
			randomStrings.add(x);
		}

	}

	@Test
	void testFindKLargestHeapNatural() {
		List<Integer> desc = generateDescending(100);
		List<Integer> lst = FindKLargest.findKLargestHeap(asc, 100);

		for (int i = 0; i < 100; i++) {
			assertEquals(desc.get(i), lst.get(i));
		}
	}

	@Test
	void testFindKLargestStringComp() {

		OrderStringByLength c = new OrderStringByLength();

		List<String> lst = FindKLargest.findKLargestHeap(randomStrings, 100, c);
		List<String> expected = randomStrings;
		expected.sort(c);

		for (int i = 0; i < 100; i++) {
			assertEquals(expected.get(expected.size() - 1 - i).length(), lst.get(i).length());
			System.out.println(lst.get(i).length());
		}
	}

	@Test
	void testFindKLargestNaturalSorted() {
		ArrayList<Integer> stuff = generateAscending(100);
		Collections.shuffle(stuff);
		
		List<Integer> largest = FindKLargest.findKLargestSort(stuff, 5);
		
		assertTrue(stuff.get(99) == largest.get(0));
		assertTrue(stuff.get(98) == largest.get(1));
		assertTrue(stuff.get(97) == largest.get(2));
		assertTrue(stuff.get(96) == largest.get(3));
		assertTrue(stuff.get(95) == largest.get(4));
		
	}
	
	@Test
	void testFindKLargestCompReversedSorted() {
		ArrayList<Integer> stuff = generateAscending(100);
		Collections.shuffle(stuff);
		ReverseIntOrder c = new ReverseIntOrder();
		
		List<Integer> largest = FindKLargest.findKLargestSort(stuff, 5,c);
		
		assertTrue(1 == largest.get(0));
		assertTrue(2 == largest.get(1));
		assertTrue(3 == largest.get(2));
		assertTrue(4 == largest.get(3));
		assertTrue(5 == largest.get(4));
		
	}
	
	@Test
	void testFindKLargestCompReversedHeap() {
		ArrayList<Integer> stuff = generateAscending(100);
		Collections.shuffle(stuff);
		ReverseIntOrder c = new ReverseIntOrder();
		
		List<Integer> largest = FindKLargest.findKLargestHeap(stuff, 5,c);
		
		assertTrue(1 == largest.get(0));
		assertTrue(2 == largest.get(1));
		assertTrue(3 == largest.get(2));
		assertTrue(4 == largest.get(3));
		assertTrue(5 == largest.get(4));
		
	}
	
	@Test
	void testKLargestHeapThrows() {
		ArrayList<Integer> stuff = generateAscending(100);
		Collections.shuffle(stuff);
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestHeap(stuff, 101);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestHeap(stuff, -5);
		});
	}
	
	@Test
	void testKLargestHeapCompThrows() {
		ArrayList<Integer> stuff = generateAscending(100);
		Collections.shuffle(stuff);
		ReverseIntOrder c = new ReverseIntOrder();
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestHeap(stuff, 101,c);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestHeap(stuff, -5,c);
		});
	}
	@Test
	void testKLargestSortThrows() {
		ArrayList<Integer> stuff = generateAscending(100);
		Collections.shuffle(stuff);
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestSort(stuff, 101);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestSort(stuff, -5);
		});
	}
	
	
	@Test
	void testKLargestSortCompThrows() {
		ArrayList<Integer> stuff = generateAscending(100);
		Collections.shuffle(stuff);
		ReverseIntOrder c = new ReverseIntOrder();
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestSort(stuff, 101,c);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			List<Integer> largest = FindKLargest.findKLargestSort(stuff, -5,c);
		});
	}
	
	
	
	protected class OrderStringByLength implements Comparator<String> {

		@Override
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
		}
	}

	
	
	protected class ReverseIntOrder implements Comparator<Integer> {

		@Override
		public int compare(Integer i1, Integer i2) {
			return i2 - i1;
		}
	}

	
	
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> ascending = new ArrayList<>();
		for (int i = 1; i <= size; i++)
			ascending.add(i);
		return ascending;
	}

	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> descending = new ArrayList<>();
		for (int i = size; i >= 1; i--)
			descending.add(i);
		return descending;
	}

}
