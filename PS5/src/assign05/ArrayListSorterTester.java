package assign05;

import static org.junit.jupiter.api.Assertions.*;
import assign05.ArrayListSorter;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for the ArrayListSorter class. NOTE: two tests will fail because
 * we set the methods back to private after testing.
 * 
 * @author Dan Ruley, Aric Woodliff
 * @version February 2014
 */
class ArrayListSorterTester {

	private ArrayList<Integer> smallAl, emptyAl, oneElem1, smallAl2;

	@BeforeEach
	public void setUp() throws Exception {
		smallAl = new ArrayList<>();
		smallAl.add(7);
		smallAl.add(5);
		smallAl.add(3);
		smallAl.add(1);

		emptyAl = new ArrayList<>();

		oneElem1 = new ArrayList<>();

		oneElem1.add(1);

		smallAl2 = new ArrayList<>();
		smallAl2.add(5);
		smallAl2.add(7);
		smallAl2.add(1);
		smallAl2.add(2);

	}

	@Test
	void PartitionFirst() {
		ArrayListSorter.partitionStart(smallAl, 0, 3);
		ArrayListSorter.partitionStart(smallAl, 0, 2);
		ArrayListSorter.partitionStart(smallAl, 1, 2);

		assertTrue(smallAl.get(3) == 7);
		assertTrue(smallAl.get(0) == 1);
		assertTrue(smallAl.get(2) == 5);
		assertTrue(smallAl.get(1) == 3);

	}

	@Test
	void quicksortEmptyOneElementTest() {
		ArrayListSorter.quicksort(emptyAl);

		assertTrue(emptyAl.isEmpty());

		ArrayListSorter.quicksort(oneElem1);

		assertTrue(oneElem1.get(0) == 1);
	}

	@Test
	void mergeTwoElemTest() {
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(0);
		temp.add(0);
		temp.add(0);
		temp.add(0);

		ArrayListSorter.merge(smallAl2, temp, 0, 2, 3);

		assertTrue(smallAl2.get(0) == 1);
		assertTrue(smallAl2.get(3) == 7);
	}

	@Test
	void testBigArrayMergeSort() {
		ArrayList<Integer> big = ArrayListSorter.generatePermuted(1000000);
		ArrayList<Integer> big1 = ArrayListSorter.generateAscending(1000000);

		ArrayListSorter.mergesort(big);

		for (int i = 0; i < big.size(); i++) {
			assertEquals(big.get(i), big1.get(i));
		}

	}

	@Test
	void testBigArrayMergeSortDescending() {
		ArrayList<Integer> big = ArrayListSorter.generateDescending(1000000);
		ArrayList<Integer> big1 = ArrayListSorter.generateAscending(1000000);

		ArrayListSorter.mergesort(big);

		for (int i = 0; i < big.size(); i++) {
			assertEquals(big.get(i), big1.get(i));
		}
	}

	@Test
	void testBigArrayQuickSortDescending() {
		ArrayList<Integer> big = ArrayListSorter.generateDescending(1000000);
		ArrayList<Integer> big1 = ArrayListSorter.generateAscending(1000000);

		ArrayListSorter.mergesort(big);

		for (int i = 0; i < big.size(); i++) {
			assertEquals(big.get(i), big1.get(i));
		}
	}

	@Test
	void testBigArrayQuickSort() {
		ArrayList<Integer> big = ArrayListSorter.generatePermuted(1000000);
		ArrayList<Integer> big1 = ArrayListSorter.generateAscending(1000000);

		ArrayListSorter.quicksort(big);

		for (int i = 0; i < big.size(); i++) {
			assertEquals(big.get(i), big1.get(i));
		}
	}

	@Test
	void testQuickDupes() {
		ArrayList<Integer> dupes = new ArrayList<>();
		dupes.add(0);
		dupes.add(2);
		dupes.add(1);
		dupes.add(1);
		dupes.add(2);
		dupes.add(1);
		dupes.add(1);
		dupes.add(0);

		ArrayList<Integer> dupesSorted = new ArrayList<>();
		dupesSorted.add(0);
		dupesSorted.add(0);
		dupesSorted.add(1);
		dupesSorted.add(1);
		dupesSorted.add(1);
		dupesSorted.add(1);
		dupesSorted.add(2);
		dupesSorted.add(2);

		ArrayListSorter.quicksort(dupes);

		for (int i = 0; i < dupes.size(); i++) {
			assertEquals(dupes.get(i), dupesSorted.get(i));
		}

	}

	@Test
	void testMergeDupes() {
		ArrayList<Integer> dupes = new ArrayList<>();
		dupes.add(0);
		dupes.add(2);
		dupes.add(1);
		dupes.add(1);
		dupes.add(2);
		dupes.add(1);
		dupes.add(1);
		dupes.add(0);

		ArrayList<Integer> dupesSorted = new ArrayList<>();
		dupesSorted.add(0);
		dupesSorted.add(0);
		dupesSorted.add(1);
		dupesSorted.add(1);
		dupesSorted.add(1);
		dupesSorted.add(1);
		dupesSorted.add(2);
		dupesSorted.add(2);

		ArrayListSorter.mergesort(dupes);

		for (int i = 0; i < dupes.size(); i++) {
			assertEquals(dupes.get(i), dupesSorted.get(i));
		}
	}

	@Test
	void testBIGMergeDupes() {
		ArrayList<Integer> dupes = new ArrayList<>();
		ArrayList<Integer> dupesSorted = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1000; j++) {
				dupes.add(i);
				dupesSorted.add(i);
			}
		}

		Collections.shuffle(dupes);

		ArrayListSorter.mergesort(dupes);

		for (int i = 0; i < dupes.size(); i++) {
			assertEquals(dupes.get(i), dupesSorted.get(i));
		}
	}

	@Test
	void testBIGQuickDupes() {
		ArrayList<Integer> dupes = new ArrayList<>();
		ArrayList<Integer> dupesSorted = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1000; j++) {
				dupes.add(i);
				dupesSorted.add(i);
			}
		}

		Collections.shuffle(dupes);

		ArrayListSorter.quicksort(dupes);

		for (int i = 0; i < dupes.size(); i++) {
			assertEquals(dupes.get(i), dupesSorted.get(i));
		}
	}

}
