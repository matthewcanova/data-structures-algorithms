package assignment2;

import static org.junit.Assert.*;

/**
 * Tests the Book class
 * 
 * @author Aaron Bellis, Matthew Canova
 */

import org.junit.Test;

/**
 * Tests the Book class. 
 * 
 * @author Aaron Bellis, Matthew Canova
 */

public class BookTest {

	@Test
	public void testEquals ()
	{
		long countIsbn = LibraryTest.generateIsbn();
		String count = "Count of Monte Cristo";
		String dumas = "Alexander Dumas";

		long frankIsbn = LibraryTest.generateIsbn();
		String frank = "Frankenstein";
		String marry = "Marry Shelly";
		
		// original 
		Book b1 = new Book(countIsbn, dumas, count);
		// the same
		Book b2 = new Book(countIsbn, dumas, count);
		// completely different
		Book b3 = new Book(frankIsbn, marry, frank);
		// different title
		Book b4 = new Book(countIsbn, dumas, frank);
		// different author
		Book b5 = new Book(countIsbn, marry, count);
		// different ISBN
		Book b6 = new Book(frankIsbn, dumas, count);

		assertTrue(b1.equals(b2));
		assertTrue(b2.equals(b1));
		assertFalse(b3.equals(b2));
		assertFalse(b1.equals(b4));
		assertFalse(b1.equals(b5));
		assertFalse(b1.equals(b6));
		
	}
}
