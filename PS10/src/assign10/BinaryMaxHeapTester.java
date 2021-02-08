package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for the BinaryMaxHeap class.
 * @authors Dan Ruley, Aric Woodliff
 * @version April 2019
 */
class BinaryMaxHeapTester {

	private GregorianCalendar cal;
	private GregorianCalendar cal1;
	private GregorianCalendar cal2;
	private GregorianCalendar cal3;
	private GregorianCalendar cal4;
	
	private ArrayList<GregorianCalendar> calList;
	private ArrayList<Integer> intList;
	private ArrayList<Integer> dupeList;
	private Random rng = new Random(314);

	@BeforeEach
	public void setUp() {

		intList = new ArrayList<>();
		intList.add(1);
		intList.add(3);
		intList.add(7);
		intList.add(6);
		intList.add(5);
		intList.add(15);
		intList.add(10);

		dupeList = new ArrayList<>();
		
		for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 1000; j++) {
				dupeList.add(j);
			}
		}
		
		calList = new ArrayList();
		cal = new GregorianCalendar(1900,5,3);
		cal1 = new GregorianCalendar(1950,3,12);
		cal2 = new GregorianCalendar(1776,7,4);
		cal3 = new GregorianCalendar(2019,4,5);
		cal4 = new GregorianCalendar(1969,6,20);
		calList.add(cal);
		calList.add(cal1);
		calList.add(cal2);
		calList.add(cal3);
		calList.add(cal4);
	}

	@Test
	void testExtractMax01() {

		BinaryMaxHeap<Integer> h = new BinaryMaxHeap<>(intList);
		assertTrue(h.size() == 7);
		assertTrue(h.extractMax() == 15);
		assertTrue(h.extractMax() == 10);
		assertTrue(h.extractMax() == 7);
		assertTrue(h.extractMax() == 6);
		assertTrue(h.extractMax() == 5);
		assertTrue(h.extractMax() == 3);
		assertTrue(h.extractMax() == 1);

	}

	@Test
	void testAdd01() {
		BinaryMaxHeap<Integer> bmh = new BinaryMaxHeap<>();

		bmh.add(1);
		bmh.add(2);
		bmh.add(3);
		bmh.add(2);

		assertTrue(bmh.peek() == 3);
		assertTrue(bmh.size() == 4);
		assertTrue(bmh.extractMax() == 3);
		assertTrue(bmh.extractMax() == 2);
		assertTrue(bmh.extractMax() == 2);
		assertTrue(bmh.extractMax() == 1);
		assertEquals(bmh.size(), 0);

	}

	@Test
	void testAdd02() {
		BinaryMaxHeap<Integer> bmh = new BinaryMaxHeap<>(intList);

		for (int i = 0; i < 1000; i++) {
			bmh.add(rng.nextInt(1000));
		}

		assertTrue(bmh.size() == 1007);
		bmh.add(100000);
		assertTrue(bmh.peek() == 100000);
		assertTrue(bmh.extractMax() == 100000);
		assertFalse(bmh.isEmpty());
		bmh.clear();
		assertTrue(bmh.isEmpty());

	}
	
	@Test
	void testHeapSort() {
		ArrayList<Integer> expected = generateDescending(1000);
		ArrayList<Integer> toSort = generatePermuted(1000);
		ArrayList<Integer> sorted = new ArrayList<>();

		BinaryMaxHeap<Integer> bmh = new BinaryMaxHeap<>(toSort);

		for (int i = 0; i < 1000; i++) {
			sorted.add(bmh.extractMax());
		}
		for (int i = 0; i < 1000; i++) {
			assertEquals(expected.get(i), sorted.get(i));
		}
	}
	
	@Test
	void testWithStringComparator() {
		OrderStringByLength c = new OrderStringByLength();
		
		BinaryMaxHeap<String> bmh = new BinaryMaxHeap<>(c);
		bmh.add("This is short");
		bmh.add("This is longThis is longThis is longThis is longThis is longThis is longThis is longThis is longThis is longThis is long");
		bmh.add("asd");
		bmh.add("a");
		bmh.add("");
		
		assertTrue(bmh.peek().equals("This is longThis is longThis is longThis is longThis is longThis is longThis is longThis is longThis is longThis is long"));
		assertTrue(bmh.extractMax().equals("This is longThis is longThis is longThis is longThis is longThis is longThis is longThis is longThis is longThis is long"));
		
		assertTrue(bmh.peek().equals("This is short"));
		assertTrue(bmh.extractMax().equals("This is short"));
		
		assertTrue(bmh.peek().equals("asd"));
		assertTrue(bmh.extractMax().equals("asd"));
		
		assertTrue(bmh.peek().equals("a"));
		assertTrue(bmh.extractMax().equals("a"));
		
		assertTrue(bmh.peek().equals(""));
		assertTrue(bmh.extractMax().equals(""));
	}
	
	@Test
	void testExtractMaxThrows() {
		BinaryMaxHeap<Integer> emptyBmh = new BinaryMaxHeap<>();
		
		assertThrows(NoSuchElementException.class, () -> {
			emptyBmh.extractMax();
		});
	}
	
	@Test
	void peekThrows() {
		BinaryMaxHeap<Integer> emptyBmh = new BinaryMaxHeap<>();
		
		assertThrows(NoSuchElementException.class, () -> {
			emptyBmh.peek();
		});
	}
	
	@Test
	void testToArray() {
		BinaryMaxHeap<Integer> bmh = new BinaryMaxHeap<>(generatePermuted(100));
		Object[] items = bmh.toArray();
		List<Integer> list = generateAscending(100);
		
		for(Object o : items) {
			assertTrue(list.contains(o));
		}	
	}

	@Test
	void testCalendars() {
		BinaryMaxHeap<GregorianCalendar> bmh = new BinaryMaxHeap<>(calList);
		
		assertTrue(5 == bmh.size());
		assertTrue(cal3.equals(bmh.extractMax()));
		assertTrue(cal4.equals(bmh.extractMax()));
		assertTrue(cal1.equals(bmh.extractMax()));
		assertTrue(cal.equals(bmh.extractMax()));
		assertTrue(cal2.equals(bmh.extractMax()));
		
	}
	
	@Test
	void testLOTSOfDupes() {
		
		BinaryMaxHeap<Integer> bmh = new BinaryMaxHeap<>(dupeList);
		int max = 999;
		for (int i = 0; i < 1000; i++) {
			for(int j = 0; j < 500; j++) {
				assertTrue(bmh.extractMax().equals(max));
			}
			max--;
		}
		
		
	}
	protected class OrderStringByLength implements Comparator<String> {

		@Override
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
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

	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> permuted = generateAscending(size);
		Collections.shuffle(permuted);
		
		return permuted;
	}

}
