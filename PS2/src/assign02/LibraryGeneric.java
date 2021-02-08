package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 
 * This class represents a generic library, which is a collection of generic
 * library books. A generic library must be Type-compatible with the generic
 * library books it contains.
 * 
 * @author Dan Ruley, Aric Woodliff
 * @version January 22, 2019
 * @param <Type>
 */
public class LibraryGeneric<Type> {

	private ArrayList<LibraryBookGeneric<Type>> library;

	/**
	 * Creates an empty generic library.
	 */
	public LibraryGeneric() {
		library = new ArrayList<LibraryBookGeneric<Type>>();
	}

	/**
	 * Adds to the library the book, specified by the given ISBN, author, and title.
	 * Assumes there is no possibility of duplicate library books.
	 * 
	 * @param isbn   - ISBN of the book to be added
	 * @param author - author of the book to be added
	 * @param title  - title of the book to be added
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBookGeneric<Type>(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library. Assumes there is no possibility
	 * of duplicate library books.
	 * 
	 * @param list - list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
		library.addAll(list);
	}

	/**
	 * Adds the the library the books specified by the input file. Assumes the input
	 * files specifies one book per line with ISBN, author, and title separated by
	 * tabs.
	 * 
	 * If file does not exist or format is violated, prints an error message and
	 * does not change the library.
	 * 
	 * @param filename
	 */
	public void addAll(String filename) {
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter("\\t");

				if (!lineIn.hasNextLong()) {
					lineIn.close();
					throw new ParseException("ISBN", lineNum);
				}
				long isbn = lineIn.nextLong();

				if (!lineIn.hasNext()) {
					lineIn.close();
					throw new ParseException("Author", lineNum);
				}
				String author = lineIn.next();

				if (!lineIn.hasNext()) {
					lineIn.close();
					throw new ParseException("Title", lineNum);
				}
				String title = lineIn.next();

				toBeAdded.add(new LibraryBookGeneric<Type>(isbn, author, title));

				lineNum++;
				lineIn.close();
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn - ISBN of the book to be looked up
	 */
	public Type lookup(long isbn) {
		Type hold = null;

		// get book with isbn
		for (int i = 0; i < library.size(); i++) {
			if (isbn == library.get(i).getIsbn()) {
				hold = library.get(i).getHolder();
				break;
			}
		}
		return hold;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder - holder whose checked out books are returned
	 */
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
		// holds what is checked out
		ArrayList<LibraryBookGeneric<Type>> out = new ArrayList<>();

		//
		if (holder == null) {
			return out;
		}

		// we go through the list
		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getHolder() != null && holder.equals(library.get(i).getHolder())) {
				out.add(library.get(i));
			}
		}

		return out;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn   - ISBN of the library book to be checked out
	 * @param holder - new holder of the library book
	 * @param month  - month of the new due date of the library book
	 * @param day    - day of the new due date of the library book
	 * @param year   - year of the new due date of the library book
	 * 
	 */
	public boolean checkout(long isbn, Type holder, int month, int day, int year) {
		boolean inLib = false;
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		for (int i = 0; i < library.size(); i++) {
			// check that holder is null at this point, to ensure it is not already checked
			// out.
			if (library.get(i).getIsbn() == isbn && library.get(i).getHolder() == null) {
				library.get(i).checkout(holder, date);
				inLib = true;
				break;
			}
		}
		return inLib;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn - ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) {
		boolean inLib = false;

		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getIsbn() == isbn && library.get(i).getHolder() != null) {
				library.get(i).checkin();
				inLib = true;
				break;
			}
		}
		return inLib;
	}

	/**
	 * Unsets the holder and due date for all library books checked out by the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder - holder of the library books to be checked in
	 */
	public boolean checkin(Type holder) {
		boolean inLib = false;

		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getHolder() != null && library.get(i).getHolder().equals(holder)) {
				library.get(i).checkin();
				inLib = true;
			}
		}
		return inLib;
	}

	/**
	 * Returns the list of library books, sorted by ISBN (smallest ISBN first).
	 */
	public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByIsbn comparator = new OrderByIsbn();

		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 * Returns the list of library books whose due date is older than the input
	 * date. The list is sorted by date (oldest first).
	 *
	 * If no library books are overdue, returns an empty list.
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {

		GregorianCalendar dueDate = new GregorianCalendar(year, month, day);

		ArrayList<LibraryBookGeneric<Type>> overdueList = new ArrayList<LibraryBookGeneric<Type>>();

		OrderByDueDate comparator = new OrderByDueDate();

		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getDueDate() != null && library.get(i).getDueDate().before(dueDate)) {
				overdueList.add(library.get(i));
			}
		}

		sort(overdueList, comparator);

		return overdueList;
	}

	/**
	 * Returns the list of library books, sorted lexicographically by title.
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOrderedByTitle() {
		ArrayList<LibraryBookGeneric<Type>> sorted = new ArrayList<LibraryBookGeneric<Type>>();

		OrderByTitle comparator = new OrderByTitle();

		for (int i = 0; i < library.size(); i++) {
			sorted.add(library.get(i));
		}

		sort(sorted, comparator);
		return sorted;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList.
	 * 
	 * 1. Finds the smallest item in the list. 2. Swaps the smallest item with the
	 * first item in the list. 3. Reconsiders the list be the remaining unsorted
	 * portion (second item to Nth item) and repeats steps 1, 2, and 3.
	 */
	/**
	 * @param list
	 * @param c
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the ISBN.
	 */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a positive value
		 * if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			return lhs.getIsbn() > rhs.getIsbn() ? 1 : (lhs.getIsbn() < rhs.getIsbn() ? -1 : 0);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the due date.
	 */
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a positive value if the lhs due date comes after the rhs. Returns a
		 * negative value if the lhs due date comes before the rhs. Returns 0 if both
		 * dates are equal.
		 */
		@Override
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			int result = 0;

			if (lhs.getDueDate().compareTo(rhs.getDueDate()) > 0) {
				result = 1;
			} else if (lhs.getDueDate().compareTo(rhs.getDueDate()) < 0) {
				result = -1;
			} else {
				result = 0;
			}
			return result;
		}
	}

	/**
	 * Comparator that defines a lexicographical ordering among library books using
	 * the title.
	 */
	protected class OrderByTitle implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if the lhs comes before the rhs lexicographically,
		 * positive if it comes after, and 0 if both title Strings are identical.
		 */
		@Override
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			return lhs.getTitle().compareTo(rhs.getTitle());
		}

	}

}
