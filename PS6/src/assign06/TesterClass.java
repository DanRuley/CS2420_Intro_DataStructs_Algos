package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Random;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign06.*;

/**
 * 
 * This class contains a suite of tests for the SinglyLinkedList,
 * LinkedListStack, and the BalancedSymbolChecker classes. Tests for the three
 * classes are separated by comment blocks.
 * 
 * @authors Dan Ruley, Aric Woodliff
 * @version February, 2019
 */
class TesterClass {

	private String class1, class2, class3, class4, class5, class6, class7, class8, class9, class10, class11, class12,
			class13, class14;
	private SinglyLinkedList<String> testSLL;
	private LinkedListStack<Integer> testLLS;

	@BeforeEach
	public void setUp() {
		try {
			class1 = BalancedSymbolChecker.checkFile("src/assign06/Class1.java");
			class2 = BalancedSymbolChecker.checkFile("src/assign06/Class2.java");
			class3 = BalancedSymbolChecker.checkFile("src/assign06/Class3.java");
			class4 = BalancedSymbolChecker.checkFile("src/assign06/Class4.java");
			class5 = BalancedSymbolChecker.checkFile("src/assign06/Class5.java");
			class6 = BalancedSymbolChecker.checkFile("src/assign06/Class6.java");
			class7 = BalancedSymbolChecker.checkFile("src/assign06/Class7.java");
			class8 = BalancedSymbolChecker.checkFile("src/assign06/Class8.java");
			class9 = BalancedSymbolChecker.checkFile("src/assign06/Class9.java");
			class10 = BalancedSymbolChecker.checkFile("src/assign06/Class10.java");
			class11 = BalancedSymbolChecker.checkFile("src/assign06/Class11.java");
			class12 = BalancedSymbolChecker.checkFile("src/assign06/Class12.java");
			class13 = BalancedSymbolChecker.checkFile("src/assign06/Class13.java");
			class14 = BalancedSymbolChecker.checkFile("src/assign06/Class14.java");

		} catch (FileNotFoundException e) {
			System.out.println("error");

		}

		testSLL = new SinglyLinkedList<>();
		testSLL.addFirst("Hello");
		testSLL.addFirst("World");
		testSLL.addFirst("Abcdefg");
		testSLL.addFirst("Zyxwvut");

		Random rng = new Random();

		testLLS = new LinkedListStack<>();

		for (int i = 0; i < 100; i++) {
			testLLS.push(rng.nextInt(100));
		}

	}

	/**
	 * 
	 * 
	 * 
	 * Tests for the UnbalancedSymbolChecker class contained below this block.
	 * 
	 * 
	 * 
	 */

	@Test
	void testUnmatchedLeftSymbols() {
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.", class1);

	}

	@Test
	void testUnmatchedRightSymbol() {
		assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.", class2);
	}

	@Test
	void testMatchingSymbols() {
		assertEquals("No errors found. All symbols match.", class3);
		assertEquals("No errors found. All symbols match.", class6);
	}

	@Test
	void testUnclosedComment() {
		assertEquals("ERROR: File ended before closing comment.", class4);
	}

	@Test
	void testUnmatched() {
		assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.", class5);
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.", class7);
		assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.", class8);
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.", class9);
		assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.", class10);
	}

	@Test
	void testStuffLeftInStack() {
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.", class11);
	}

	@Test
	void testQuotesIgnoreSymbols() {
		assertEquals("No errors found. All symbols match.", class12);
		assertEquals("No errors found. All symbols match.", class13);
		assertEquals("No errors found. All symbols match.", class14);
	}

	/**
	 *
	 * 
	 * 
	 * Tests for SinglyLinkedList Class contained below this block
	 * 
	 * 
	 * 
	 */

	@Test
	void testVariousSLLMethods() {
		assertEquals(4, testSLL.size());
		assertEquals("Zyxwvut", testSLL.removeFirst());
		assertEquals(3, testSLL.size());
		assertEquals("Abcdefg", testSLL.getFirst());

		testSLL.add(2, "Cat");

		assertEquals("Cat", testSLL.get(2));

		testSLL.add(3, "Dog");

		assertEquals("Abcdefg", testSLL.getFirst());
		assertEquals("World", testSLL.get(1));
		assertEquals("Cat", testSLL.get(2));
		assertEquals("Dog", testSLL.get(3));
		assertEquals("Hello", testSLL.get(4));

		testSLL.remove(3);
		assertEquals("Hello", testSLL.get(3));

		testSLL.removeFirst();
		assertEquals("World", testSLL.getFirst());

		assertEquals("World", testSLL.remove(0));
		assertEquals("Cat", testSLL.getFirst());

		testSLL.clear();
		assertEquals(0, testSLL.size());
	}

	@Test
	public void testMoreSLLMethods() {
		assertEquals(0, testSLL.indexOf("Zyxwvut"));
		assertEquals(1, testSLL.indexOf("Abcdefg"));
		assertEquals(2, testSLL.indexOf("World"));
		assertEquals(3, testSLL.indexOf("Hello"));

		Object[] test = new Object[4];
		test[0] = "Zyxwvut";
		test[1] = "Abcdefg";
		test[2] = "World";
		test[3] = "Hello";

		Object[] test1 = testSLL.toArray();

		for (int i = 0; i < test.length; i++) {
			assertTrue(test[i].equals(test1[i]));
		}

		testSLL.remove(1);
		assertEquals(1, testSLL.indexOf("World"));

	}

	@Test
	public void testSLLOutOfBoundsExceptions() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testSLL.add(20, "Illegal");
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testSLL.add(-1, "Illegal");
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testSLL.get(-1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testSLL.get(20);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testSLL.remove(-1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			testSLL.remove(20);
		});
	}

	@Test
	public void testSLLNoSuchElementException() {
		SinglyLinkedList<Integer> x = new SinglyLinkedList<>();
		assertThrows(NoSuchElementException.class, () -> {
			x.getFirst();
		});
		assertThrows(NoSuchElementException.class, () -> {
			x.removeFirst();
		});
	}

	@Test
	public void testSLLIter() {
		Iterator<String> iter = testSLL.iterator();

		assertEquals("Zyxwvut", iter.next());
		iter.remove();
		assertEquals("Abcdefg", iter.next());
		assertEquals("World", iter.next());
		iter.remove();

		assertEquals("Abcdefg", testSLL.get(0));
		assertEquals("Hello", testSLL.get(1));

	}

	/**
	 * 
	 * 
	 * 
	 * Tests for the LinkedListStack class contained below this block.
	 * 
	 * 
	 * 
	 */

	@Test
	public void testPop() {
		for (int i = 0; i < 100; i++) {
			testLLS.pop();
		}
		assertTrue(testLLS.isEmpty());
		assertEquals(0, testLLS.size());
	}

	@Test
	public void testPushPeekClear() {

		testLLS.clear();
		assertTrue(testLLS.isEmpty());
		assertEquals(0, testLLS.size());

		for (int i = 0; i < 100; i++) {
			testLLS.push(i);
		}

		assertTrue(100 == testLLS.size());

		for (int i = 0; i < 100; i++) {
			assertTrue(99 == testLLS.peek());
		}
		for (int i = 99; i >= 0; i--) {
			assertTrue(i == testLLS.pop());
		}

	}

	@Test
	public void testLLSNoSuchElementException() {
		testLLS.clear();
		assertThrows(NoSuchElementException.class, () -> {
			testLLS.pop();
		});
		assertThrows(NoSuchElementException.class, () -> {
			testLLS.peek();
		});
	}

}