package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a generic library book, extending the book class. A
 * library book is generic in that the holder may be any generic Type of the
 * user's choosing.  Has a due date, and may be checked in and out.
 * 
 * @author Erin Parker, Dan Ruley, and Aric Woodliff
 * @version January 22, 2019
 */

public class LibraryBookGeneric<Type> extends Book {

	private Type holder;  //The book's holder, can be any Type.
	private GregorianCalendar dueDate;	//The book's due date.

	/**
	 * Creates a generic library book by calling the superconstructor in Book.
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
	}

	/**
	 * Getter for the book's holder
	 * @return the generic Type which represent's the book's holder.
	 */
	public Type getHolder() {
		return this.holder;
	}

	/**
	 * Getter method for due date
	 * @return the due date of this generic library book.
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
	 * @param Type  hold
	 * @param GregorianCalendar due
	 */
	public void checkout(Type hold, GregorianCalendar due) {
		this.holder = hold;
		this.dueDate = due;
	}

}
