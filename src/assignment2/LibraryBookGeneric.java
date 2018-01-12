package assignment2;

import java.util.GregorianCalendar;

/**
 * Class representation of a generic LibraryBook which extends a Book object.
 * Has a holder and a due date which may be set to null if book is not checked
 * out.
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class LibraryBookGeneric<Type extends Object> extends Book
{
	// dueDate and holder are set to null if book is checked in.
	private GregorianCalendar dueDate; // the date the book should be returned
	private Type holder; // the current holder of the book

	/**
	 * Constructs a Library Book object which is checked in.
	 * 
	 * @param isbn
	 *            - the unique ISBN of the book
	 * @param author
	 *            - the author of the book
	 * @param title
	 *            - the title of the book
	 */
	public LibraryBookGeneric (long isbn, String author, String title)
	{
		super(isbn, author, title);

		// begin as checked in book
		holder = null;
		dueDate = null;
	}

	/**
	 * @return the current holder of the library book. Returns null if book is
	 *         checked in.
	 */
	public Type getHolder ()
	{
		return holder;
	}

	/**
	 * @return the current due date of the library book. Returns null if book is
	 *         checked in.
	 */
	public GregorianCalendar getDueDate ()
	{
		return dueDate;
	}

	/**
	 * Checks in a book. Removes the current holder and due date.
	 */
	public void checkIn ()
	{
		holder = null;
		dueDate = null;
	}

	/**
	 * Checks the book out. Assigns the book to the newHolder and gives the book
	 * the newDueDate.
	 * 
	 * Doesn't require the book to be checked in to change holder and dueDate
	 * values.
	 * 
	 * @param newHolder
	 *            - the individual who has checked out the book
	 * @param newDueDate
	 *            - the date the book is due for return
	 */
	public void checkOut (Type newHolder, GregorianCalendar newDueDate)
	{
		holder = newHolder;
		dueDate = newDueDate;
	}

}
