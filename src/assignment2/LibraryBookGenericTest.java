package assignment2;

import static org.junit.Assert.*;

/**
 * Tests the LibraryBookGeneric class
 * 
 * @author Aaron Bellis, Matthew Canova
 */

import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * Class representation of a generic LibraryBook which extends a Book object.
 * Has a holder and a due date which may be set to null if book is not checked
 * out.
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class LibraryBookGenericTest {

	private long countIsbn = LibraryTest.generateIsbn();
	private String count = "Count of Monte Cristo";
	private String dumas = "Alexander Dumas";
	
	@Test
	public void testLibraryBookGeneric() {
		LibraryBookGeneric<String> testLibraryBook = new LibraryBookGeneric<String>(countIsbn, dumas, count);
		assertEquals(testLibraryBook.getIsbn(), countIsbn);
		assertEquals(testLibraryBook.getAuthor(), dumas);
		assertEquals(testLibraryBook.getTitle(), count);
		
	}

	@Test
	public void testGetHolder() {
		// test with holder as String
		LibraryBookGeneric<String> testLibraryBook = new LibraryBookGeneric<String>(countIsbn, dumas, count);
		assertEquals(testLibraryBook.getHolder(), null);
		testLibraryBook.checkOut("Matt Canova", new GregorianCalendar(2015, 2, 4));
		assertEquals(testLibraryBook.getHolder(), "Matt Canova");
		
		// test with holder as PhoneNumber
		LibraryBookGeneric<PhoneNumber> testLibraryBook2 = new LibraryBookGeneric<PhoneNumber>(countIsbn, dumas, count);
		PhoneNumber holderNumber = new PhoneNumber("801.123.4567");
		testLibraryBook2.checkOut(holderNumber, new GregorianCalendar(2015, 2, 4));
		assertEquals(testLibraryBook2.getHolder(), holderNumber);
		
	}

	@Test
	public void testGetDueDate() {
		LibraryBookGeneric<String> testLibraryBook = new LibraryBookGeneric<String>(countIsbn, dumas, count);
		assertEquals(testLibraryBook.getDueDate(), null);
		testLibraryBook.checkOut("Matt Canova", new GregorianCalendar(2015, 2, 4));
		assertEquals(testLibraryBook.getDueDate(), new GregorianCalendar(2015, 2, 4));
	}

	@Test
	public void testCheckIn() {
		// test with holder as String
		LibraryBookGeneric<String> testLibraryBook = new LibraryBookGeneric<String>(countIsbn, dumas, count);
		testLibraryBook.checkIn();
		assertEquals(testLibraryBook.getHolder(), null);
		assertEquals(testLibraryBook.getDueDate(), null);
		testLibraryBook.checkOut("Matt Canova", new GregorianCalendar(2014, 2, 4));
		testLibraryBook.checkIn();
		assertEquals(testLibraryBook.getHolder(), null);
		assertEquals(testLibraryBook.getDueDate(), null);
		
		// test with holder as PhoneNumber
		LibraryBookGeneric<PhoneNumber> testLibraryBook2 = new LibraryBookGeneric<PhoneNumber>(countIsbn, dumas, count);
		testLibraryBook2.checkIn();
		assertEquals(testLibraryBook2.getHolder(), null);
		assertEquals(testLibraryBook2.getDueDate(), null);
		testLibraryBook2.checkOut(new PhoneNumber("801.123.4567"), new GregorianCalendar(2014, 2, 4));
		testLibraryBook2.checkIn();
		assertEquals(testLibraryBook2.getHolder(), null);
		assertEquals(testLibraryBook2.getDueDate(), null);
	}

	@Test
	public void testCheckOut() {
		// test with holder as String
		LibraryBookGeneric<String> testLibraryBook = new LibraryBookGeneric<String>(countIsbn, dumas, count);
		testLibraryBook.checkOut("Matt Canova", new GregorianCalendar(2014, 2, 4));
		assertEquals(testLibraryBook.getHolder(), "Matt Canova");
		assertEquals(testLibraryBook.getDueDate(), new GregorianCalendar(2014, 2, 4));
		
		// test with holder as PhoneNumber
		LibraryBookGeneric<PhoneNumber> testLibraryBook2 = new LibraryBookGeneric<PhoneNumber>(countIsbn, dumas, count);
		PhoneNumber holderNumber = new PhoneNumber("801.123.4567");
		testLibraryBook2.checkOut(holderNumber, new GregorianCalendar(2014, 2, 4));
		assertEquals(testLibraryBook2.getHolder(), holderNumber);
		assertEquals(testLibraryBook2.getDueDate(), new GregorianCalendar(2014, 2, 4));
	}

}
