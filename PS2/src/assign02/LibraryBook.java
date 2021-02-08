package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a library book, extending the book class. A library
 * book differs from a book in that it may be checked out, and a holder and due
 * date can be set.
 * 
 * @author Erin Parker, Dan Ruley, and Aric Woodliff
 * @version January 22, 2019
 */
public class LibraryBook extends Book {

	private String holder;	//string representing the holder
	private GregorianCalendar dueDate;	//the due date of the library book

	/**
	 * Creates a library book with the given paramaters.
	 * 
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public LibraryBook(long isbn, String author, String title) {
		// call the superconstructor
		super(isbn, author, title);
	}

	/**
	 * Getter method for the book holder.
	 * 
	 * @return the String holder.
	 */
	public String getHolder() {
		// i'll do this.holder just to clarify
		return this.holder;
	}

	/**
	 * Getter method for the due date
	 * 
	 * @return a GregorianCalendar object representing the due date.
	 */
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}

	/**
	 * Checks the library book back in by setting the holder and due date to null.
	 */
	public void checkin() {
		this.holder = null;
		this.dueDate = null;
	}

	/**
	 * Checks out a book and sets the holder and due date based on the input
	 * paramaters.
	 * 
	 * @param String            hold
	 * @param GregorianCalendar due
	 */
	public void checkout(String hold, GregorianCalendar due) {
		this.holder = hold;
		this.dueDate = due;
	}

}
