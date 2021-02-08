package assign04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import assign04.AnagramChecker;
/**
 * This class contains a series of tests for the AnagramChecker class.
 * @author Dan Ruley, Aric Woodliff 2019
 */
class AnagramCheckerTest {

	@BeforeEach
	public void setup() {

	}

	@Test
	void testSort() {
		String s1 = "being";
		String s2 = "BEGIN";

		s1 = AnagramChecker.sort(s1);
		s2 = AnagramChecker.sort(s2);

		assertTrue(s1.equalsIgnoreCase(s2));
	}
	
	@Test
	void SortEmpty() {
		String s1 = "";
		String s2 = "";

		s1 = AnagramChecker.sort(s1);
		s2 = AnagramChecker.sort(s2);

		assertTrue(s1.equalsIgnoreCase(s2));
	}

	@Test
	void testAreAnagrams() {
		String s1 = "zxcvbnmasdfghjkl";
		String s2 = "lkjhgfdsamnbvcxz";


		assertTrue(AnagramChecker.areAnagrams(s1, s2));
	}

	@Test
	void testEmptyStringSortAndAreAnagrams() {
		String s1 = "";
		String s2 = "";
		s1 = AnagramChecker.sort(s1);
		s2 = AnagramChecker.sort(s2);

		assertTrue(AnagramChecker.areAnagrams(s1, s2));
	}

	@Test
	void testGetLargestAnagramGroupArray() {
		String[] list = new String[] { "Begin", "a","abc","a","being", "cba", "bac",  "a", "a", "a" };

		String[] largestGroup = AnagramChecker.getLargestAnagramGroup(list);

		assertEquals(largestGroup[0], "a");
		assertEquals(largestGroup.length, 5);

	}

	@Test
	void testGetLrgstGrpEmptyStringsBehaveProperly() {
		String[] list = new String[] { "", "", "", "", "", "", "Begin", "abc", "being", "", "cba", "bac", "", "a", "a",
				"a", "a", "a" };

		String[] largestGroup = AnagramChecker.getLargestAnagramGroup(list);

		for (int i = 0; i < largestGroup.length;i++) {
		assertEquals(largestGroup[i], "");
		}
		
		assertEquals(largestGroup.length, 8);
	}

	@Test
	void testGetLrgstGrpNumbersAndSymbols() {
		String[] list = new String[] { "Begin", "abc", "being", "", "cba", "bac", "", "a", "a",
				"a", "!X@@654#$%", "546@@#$%X!" , "@X!#456$%@", "X!4@#@5$6%", "!@@654#$%X", "@!@X$%#456"};
		String [] largestGroup = AnagramChecker.getLargestAnagramGroup(list);
		assertEquals(largestGroup.length, 6);
	}
	
	
	@Test
	void testGetLrgstGrpFromFile() {
		String [] anagrams = AnagramChecker.getLargestAnagramGroup("src/assign04/sample_word_list.txt");
		String [] expectedResult = new String[] {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		assertEquals(7, anagrams.length);
		for (int i = 0; i < anagrams.length; i++) {
			assertEquals(anagrams[i], expectedResult[i]);
		}
	}
	
	@Test
	void testGetLrgstGrpFromBIGLISTFileWithManyDuplicates() {
		String [] anagrams = AnagramChecker.getLargestAnagramGroup("src/assign04/BIGLIST_MANY_DUPLICATES.txt");
		String [] expectedResult = new String[] {"reset", "ester", "terse", "trees", "terse", "steer", "reset", "trees", "terse","steer","reset","steer","trees"};
		assertEquals(13, anagrams.length);
		for (int i = 0; i < anagrams.length; i++) {
			assertEquals(anagrams[i], expectedResult[i]);
		}
	}
	
	@Test
	void testEmptyArrayReturnsEmptyArray() {
		String[] nothing = new String[0];
		assertEquals(nothing.length, AnagramChecker.getLargestAnagramGroup(nothing).length);
	}
	
	@Test
	void GetAnagramsArrayOfOneReturnsEmptyArray() {
		String[] oneEle = new String[] {"hello"};
		assertEquals(0, AnagramChecker.getLargestAnagramGroup(oneEle).length);
	}
	
}
