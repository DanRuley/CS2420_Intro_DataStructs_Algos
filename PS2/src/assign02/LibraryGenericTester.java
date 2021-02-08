package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign02.LibraryGeneric.OrderByDueDate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * This class contains tests for LibraryGeneric.
 * 
 * @author Erin Parker, Aric Woodliff, and Dan Ruley
 * @version January 16, 2019
 */
public class LibraryGenericTester {

	private LibraryGeneric<String> nameLib; // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib; // library that uses phone numbers to identify patrons
	private LibraryGeneric<String> medNameLib; // library of medium sized book list, using names
	private LibraryGeneric<PhoneNumber> edgeLib; // library used for testing a lexicographical edge case

	@BeforeEach
	void setUp() throws Exception {
		nameLib = new LibraryGeneric<String>();
		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		edgeLib = new LibraryGeneric<PhoneNumber>();
		edgeLib.add(0000000001217, "Testy McTestington", "A Treatise on Lexicographical Edge Cases>");
		edgeLib.add(0000000001216, "Testy McTestington", "A Treatise on Lexicographical Edge Cases<");
		

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		medNameLib = new LibraryGeneric<String>();
		medNameLib.addAll("src/assign02/Mushroom_Publishing.txt");
		
	}

	@Test
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}

	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}

	/**
	 * Test that getOverdueList returns an array list with the proper order, from
	 * oldest to newest.
	 */
	@Test
	public void testOrderByDueDate() {

		nameLib.checkout(9780374292799L, "Bob", 1, 22, 2018);
		nameLib.checkout(9780330351690L, "Steve", 12, 15, 2018);
		nameLib.checkout(9780446580342L, "Joe", 11, 12, 2018);

		ArrayList<LibraryBookGeneric<String>> overdue = nameLib.getOverdueList(1, 1, 2019);

		System.out.println(overdue.get(1).getHolder());
		assertTrue(overdue.get(0).getHolder().equals("Bob"));
		assertTrue(overdue.get(1).getHolder().equals("Joe"));
		assertTrue(overdue.get(2).getHolder().equals("Steve"));

	}

	/**
	 * Check that getOverdueList returns an empty list when the due date specified
	 * comes before all of the books that are checked out.
	 */
	@Test
	public void testOrderByDueDateButNotOverdue() {
		nameLib.checkout(9780374292799L, "Bob", 1, 22, 2018);
		nameLib.checkout(9780330351690L, "Steve", 12, 15, 2018);
		nameLib.checkout(9780446580342L, "Joe", 11, 12, 2018);

		ArrayList<LibraryBookGeneric<String>> overdue = nameLib.getOverdueList(1, 1, 2018);

		assertEquals(overdue.size(), 0);
	}

	/**
	 * Test lexicographical sorting of the small library.
	 */
	@Test
	public void testOrderByTitleNameLib() {
		ArrayList<LibraryBookGeneric<String>> ordered = nameLib.getOrderedByTitle();
		assertEquals(ordered.get(0).getTitle(), "Into the Wild");
		assertEquals(ordered.get(1).getTitle(), "Simple Genius");
		assertEquals(ordered.get(2).getTitle(), "The World is Flat");
	}

	/**
	 * Test lexicographical sorting of the medium library.
	 */
	@Test
	public void testOrderByTitleMedNameLib() {
		ArrayList<LibraryBookGeneric<String>> ordered = medNameLib.getOrderedByTitle();

		assertEquals(ordered.get(0).getTitle(), "An Endless Exile");
		assertEquals(ordered.get(1).getTitle(), "Bath City Centre Street Map and Guide");
		assertEquals(ordered.get(2).getTitle(), "Breaking the Gaze");
		assertEquals(ordered.get(3).getTitle(), "Cloner");
		assertEquals(ordered.get(4).getTitle(), "Crystal Legends");
		assertEquals(ordered.get(5).getTitle(), "Demogorgon Rising");
		assertEquals(ordered.get(6).getTitle(), "Herbs for Healthy Skin");
		assertEquals(ordered.get(7).getTitle(), "Ice and a Curious Man");
		assertEquals(ordered.get(8).getTitle(), "Iceni");
		assertEquals(ordered.get(9).getTitle(), "Machina Obscura");
		assertEquals(ordered.get(10).getTitle(), "Operation: Sergeant York");
		assertEquals(ordered.get(11).getTitle(), "Pathway Into Sunrise");
		assertEquals(ordered.get(12).getTitle(), "The Anxiety Relief Program");
		assertEquals(ordered.get(13).getTitle(), "The Call of the Sword");
		assertEquals(ordered.get(14).getTitle(), "The Coming of the Third");
		assertEquals(ordered.get(15).getTitle(), "The Eye of Callanish");
		assertEquals(ordered.get(16).getTitle(), "The Fuehrermaster");
		assertEquals(ordered.get(17).getTitle(), "The Lily and the Bull");
		assertEquals(ordered.get(18).getTitle(), "The Royal United Hospital");
		assertEquals(ordered.get(19).getTitle(), "The War Comes to Witham Street");
		assertEquals(ordered.get(20).getTitle(), "Transit to Scorpio");
		assertEquals(ordered.get(21).getTitle(), "Weapons of the Wolfhound");
		assertEquals(ordered.get(22).getTitle(), "Whistler");

	}

	/**
	 * This test checks proper functioning of sort by title on an edge case, where
	 * there is only a tiny difference in lexicographical ordering.
	 */
	@Test
	public void testSortByTitleEdgeCase() {
		ArrayList<LibraryBookGeneric<PhoneNumber>> ordered = edgeLib.getOrderedByTitle();
		assertEquals(ordered.get(0).getTitle(), "A Treatise on Lexicographical Edge Cases<");
		assertEquals(ordered.get(1).getTitle(), "A Treatise on Lexicographical Edge Cases>");
	}
	
	/**
	 * Tests when the list of overdue books has only one or zero elements.
	 */
	@Test
	public void testSortByDueDateOnlyOneOrZeroElements()
	{
		ArrayList<LibraryBookGeneric<PhoneNumber>> sorted = phoneLib.getOverdueList(1, 1, 2018);
		
		assertEquals(sorted.size(),0);
		
		phoneLib.checkout(9780374292799L, new PhoneNumber("801-444-4444"), 1,1,1999);
		
		sorted = phoneLib.getOverdueList(1, 1, 2018);
	
		assertEquals(sorted.size(),1);
	}
	
	
	
}
