package assignment2;

import static org.junit.Assert.*;

/**
 * Tests the LibraryBook class
 * 
 * @author Aaron Bellis, Matthew Canova
 */
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * Tests the LibraryBook class. 
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class LibraryBookTest {

	private long countIsbn = LibraryTest.generateIsbn();
	private String count = "Count of Monte Cristo";
	private String dumas = "Alexander Dumas";
	
	@Test
	public void testLibraryBook() {
		LibraryBook b1 = new LibraryBook(countIsbn, dumas, count);
		assertEquals(b1.getIsbn(), countIsbn);
		assertEquals(b1.getTitle(), count);
		assertEquals(b1.getAuthor(), dumas);
		assertEquals(b1.getHolder(), null);
		assertEquals(b1.getDueDate(), null);
	}

	@Test
	public void testGetHolder() {
		LibraryBook b1 = new LibraryBook(countIsbn, dumas, count);
		b1.checkOut("Matt Canova", null);
		assertEquals(b1.getHolder(), "Matt Canova");
		assertNotEquals(b1.getHolder(), "Not Matt");
	}

	@Test
	public void testGetDueDate() {
		LibraryBook b1 = new LibraryBook(countIsbn, dumas, count);
		b1.checkOut("Matt Canova", new GregorianCalendar(2014, 2, 4));
		assertEquals(b1.getDueDate(), new GregorianCalendar(2014, 2, 4));
		assertNotEquals(b1.getDueDate(), new GregorianCalendar(2013, 2, 4));
	}

	@Test
	public void testCheckIn() {
		LibraryBook b1 = new LibraryBook(countIsbn, dumas, count);
		b1.checkIn();
		assertEquals(b1.getHolder(), null);
		assertEquals(b1.getDueDate(), null);
		b1.checkOut("Matt Canova", new GregorianCalendar(2014, 2, 4));
		b1.checkIn();
		assertEquals(b1.getHolder(), null);
		assertEquals(b1.getDueDate(), null);
	}

	@Test
	public void testCheckOut() {
		LibraryBook b1 = new LibraryBook(countIsbn, dumas, count);
		b1.checkOut("Matt Canova", new GregorianCalendar(2014, 2, 4));
		assertEquals(b1.getHolder(), "Matt Canova");
		assertEquals(b1.getDueDate(), new GregorianCalendar(2014, 2, 4));
	}

}
