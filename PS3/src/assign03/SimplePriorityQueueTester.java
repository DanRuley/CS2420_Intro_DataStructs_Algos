package assign03;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for the SimplePriorityQueue class.
 * 
 * @authors Dan Ruley, Aric Woodliff
 * @version January 30, 2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
class SimplePriorityQueueTester {

	private SimplePriorityQueue<Integer> integerQueue, largeNQueue;
	private SimplePriorityQueue<String> smallStringQueue;
	private SimplePriorityQueue<Object> emptyQueue;
	private SimplePriorityQueue<String> stringQueue;
	private SimplePriorityQueue<BigInteger> bigIntQueue;

	@BeforeEach
	void setup() throws Exception {

		largeNQueue = new SimplePriorityQueue();

		emptyQueue = new SimplePriorityQueue();

		integerQueue = new SimplePriorityQueue();

		smallStringQueue = new SimplePriorityQueue();

		bigIntQueue = new SimplePriorityQueue();

	}

	@Test
	public void testInsertAndIsEmptyOnEmptyQueue() {
		assertTrue(emptyQueue.isEmpty());

		emptyQueue.insert(new String("A"));

		assertFalse(emptyQueue.isEmpty());
	}

	@Test
	public void testBasicInsertAndSort() {
		integerQueue.insert(-1);
		integerQueue.insert(0);
		integerQueue.insert(1);
		integerQueue.insert(2);
		integerQueue.insert(3);
		integerQueue.insert(-1000);
		assertEquals(-1000, (int) integerQueue.findMin());
	}

	@Test
	public void testMoreIntegerInsertsAndSorts() {
		integerQueue.insert(10);
		integerQueue.insert(-25);
		integerQueue.insert(3);
		integerQueue.insert(2);
		integerQueue.insert(71);
		integerQueue.insert(1000);
		integerQueue.insert(-54);
		integerQueue.insert(17);
		integerQueue.insert(-294);
		integerQueue.insert(71);
		integerQueue.insert(5);
		integerQueue.insert(10);
		integerQueue.insert(-25);
		integerQueue.insert(3);
		integerQueue.insert(2);
		integerQueue.insert(71);
		integerQueue.insert(1000);
		integerQueue.insert(10);
		integerQueue.insert(-25);
		integerQueue.insert(3);
		integerQueue.insert(2);
		integerQueue.insert(71);
		integerQueue.insert(1000);
		integerQueue.insert(-54000);
		integerQueue.insert(17);
		integerQueue.insert(-294);

		assertEquals(-54000, (int) integerQueue.findMin());
		assertEquals(-54000, (int) integerQueue.deleteMin());
		assertEquals(-294, (int) integerQueue.findMin());

		integerQueue.clear();

		integerQueue.insert(9);
		assertEquals(9, (int) integerQueue.findMin());

		integerQueue.insert(-25);
		integerQueue.insert(3);
		integerQueue.insert(2);
		integerQueue.insert(71);
		integerQueue.insert(1000);
		integerQueue.insert(-54000);
		integerQueue.insert(17);
		integerQueue.insert(-294);
		assertEquals(-54000, (int) integerQueue.findMin());
	}

	@Test
	public void testInsertAll() {
		ArrayList<String> al = new ArrayList<>();
		al.add("A");
		al.add("B");
		al.add("C");
		smallStringQueue.insertAll(al);
		assertEquals("A", (String) smallStringQueue.findMin());
	}
	
	@Test
	public void testInsertAllAndSize() {
		ArrayList<String> al = new ArrayList<>();
		al.add("A");
		al.add("B");
		al.add("C");
		smallStringQueue.insertAll(al);
		assertEquals(3, smallStringQueue.size());
	}

	@Test
	public void testInsertStringComparator() {
		stringQueue = new SimplePriorityQueue(new OrderStringByLength());

		stringQueue.insert("A");
		stringQueue.insert("B");
		stringQueue.insert("C");
		stringQueue.insert("D");

		assertEquals("A", stringQueue.findMin());
	}

	@Test
	public void testInsertAndInsertAllAndSomeOtherMethodsStringComparator() {
		stringQueue = new SimplePriorityQueue(new OrderStringByLength());

		stringQueue.insert("asdkfh");
		stringQueue.insert("asdfkjhasdkfjhasdk;fj");
		stringQueue.insert("asdkfh");
		stringQueue.insert("asj");
		stringQueue.insert("a");
		stringQueue.insert("asdfkjadsfaasdk;fj");

		assertEquals("a", stringQueue.findMin());

		stringQueue.deleteMin();

		assertEquals("asj", stringQueue.findMin());

		stringQueue.clear();

		assertTrue(stringQueue.isEmpty());

		ArrayList<String> al = new ArrayList<>();
		al.add("asdkfh");
		al.add("asdfkjhasdkfjhasdk");
		al.add("asdkfh");
		al.add("asj");
		al.add("a");
		al.add("asdfkjadsfaasdk;fj");

		stringQueue.insertAll(al);

		assertEquals("a", stringQueue.findMin());

		stringQueue.deleteMin();

		assertEquals("asj", stringQueue.findMin());

		stringQueue.clear();

		assertTrue(stringQueue.isEmpty());

	}

	@Test
	public void testFindMin() {
		assertThrows(NoSuchElementException.class, () -> {
			emptyQueue.findMin();
		});
	}

	@Test
	public void testDeleteMin() {
		assertThrows(NoSuchElementException.class, () -> {
			emptyQueue.deleteMin();
		});
	}

	@Test
	public void testBigQueueInsert() {
		for (int i = 10000000; i > 0; i--) {
			largeNQueue.insert(i);
		}
		assertEquals(1, (int) largeNQueue.findMin());
	}
	
	@Test
	public void testBigQueueSizeAndDelete() {
		for (int i = 0; i < 1000000; i++) {
			largeNQueue.insert(i);
		}
		assertEquals(1000000, largeNQueue.size());
		largeNQueue.deleteMin();
		assertEquals(999999, largeNQueue.size());
	}

	/**@Test
	public void testBigQueueInsert1() {

		Random rng = new Random();
		for (int i = 10000; i > 0; i--) {
			largeNQueue.insert(rng.nextInt(10000));
		}
		largeNQueue.insert(-1);
		assertEquals(-1, (int) largeNQueue.findMin());
	}

	@Test
	public void testBigIntegerQueueVariousMethods() {

		bigIntQueue.insert(new BigInteger("10592835729587235"));
		bigIntQueue.insert(new BigInteger("105928357295872438573498734598374698347635"));
		bigIntQueue.insert(new BigInteger("12398"));
		bigIntQueue.insert(new BigInteger("10592835729587235"));
		bigIntQueue.insert(new BigInteger("1059283572958724385732345806183247618476498734598374698347635"));
		bigIntQueue.insert(new BigInteger("1213241324398"));
		bigIntQueue.insert(new BigInteger("1059141234123412342835729587235"));
		bigIntQueue.insert(new BigInteger("10592835729587232423142134438573498734598374698347635"));
		bigIntQueue.insert(new BigInteger("1238"));

		assertTrue(bigIntQueue.findMin().equals(new BigInteger("1238")));

		bigIntQueue.deleteMin();

		assertTrue(bigIntQueue.findMin().equals(new BigInteger("12398")));

	}

	@Test
	public void testClear() {
		integerQueue.insert(9);
		integerQueue.clear();

		assertTrue(integerQueue.size() == 0);
	}

	/**
	 * Functor class that defines a comparator for Strings. Compares them by length
	 * instead of lexicographical ordering.
	 */
	protected class OrderStringByLength implements Comparator<String> {

		/**
		 * Return the difference in length of the two Strings (second subtracted from
		 * first).
		 */
		@Override
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
		}

	}

}
