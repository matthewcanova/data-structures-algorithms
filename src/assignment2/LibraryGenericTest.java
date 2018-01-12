package assignment2;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Tests the LibraryGeneric class
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class LibraryGenericTest
{

	public static void main (String[] args)
	{

		// test a library that uses names (String) to id patrons
		LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
		lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib1.add(9780446580342L, "David Baldacci", "Simple Genius");

		String patron1 = "Jane Doe";

		if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
			System.err.println("TEST FAILED: first checkout");
		if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
			System.err.println("TEST FAILED: second checkout");
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1
				.lookup(patron1);
		if (booksCheckedOut1 == null
				|| booksCheckedOut1.size() != 2
				|| !booksCheckedOut1.contains(new Book(9780330351690L,
						"Jon Krakauer", "Into the Wild"))
				|| !booksCheckedOut1.contains(new Book(9780374292799L,
						"Thomas L. Friedman", "The World is Flat"))
				|| !booksCheckedOut1.get(0).getHolder().equals(patron1)
				|| !booksCheckedOut1.get(0).getDueDate()
						.equals(new GregorianCalendar(2008, 1, 1))
				|| !booksCheckedOut1.get(1).getHolder().equals(patron1)
				|| !booksCheckedOut1.get(1).getDueDate()
						.equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: lookup(holder)");
		if (!lib1.checkin(patron1))
			System.err.println("TEST FAILED: checkin(holder)");

		// test a library that uses phone numbers (PhoneNumber) to id patrons
		LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
		lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

		PhoneNumber patron2 = new PhoneNumber("801.555.1234");

		if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
			System.err.println("TEST FAILED: first checkout");
		if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))
			System.err.println("TEST FAILED: second checkout");
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2
				.lookup(patron2);
		if (booksCheckedOut2 == null
				|| booksCheckedOut2.size() != 2
				|| !booksCheckedOut2.contains(new Book(9780330351690L,
						"Jon Krakauer", "Into the Wild"))
				|| !booksCheckedOut2.contains(new Book(9780374292799L,
						"Thomas L. Friedman", "The World is Flat"))
				|| !booksCheckedOut2.get(0).getHolder().equals(patron2)
				|| !booksCheckedOut2.get(0).getDueDate()
						.equals(new GregorianCalendar(2008, 1, 1))
				|| !booksCheckedOut2.get(1).getHolder().equals(patron2)
				|| !booksCheckedOut2.get(1).getDueDate()
						.equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: lookup(holder)");
		if (!lib2.checkin(patron2))
			System.err.println("TEST FAILED: checkin(holder)");
		
		// test checkIn by ISBN
		lib2.checkout(9780330351690L, patron2, 1, 1, 2008);
		
		if (!lib2.checkin(9780330351690L))
			System.err.println("TEST FAILED: checkin(ISBN) for checked out book");
		if (lib2.checkin(9780446580342L))
			System.err.println("TEST FAILED: checkin(ISBN) for checked in book");
		
		// test getInventoryList
		LibraryGeneric<PhoneNumber> lib3 = new LibraryGeneric<PhoneNumber>();
		lib3.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib3.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib3.add(9780446580342L, "David Baldacci", "Simple Genius");
		ArrayList<LibraryBookGeneric<PhoneNumber>> testReturnList = lib3.getInventoryList();
		
		if(testReturnList.get(0).getIsbn() != 9780330351690L)
			System.err.println("TEST FAILED: getInventoryList()");
		if(testReturnList.get(1).getIsbn() != 9780374292799L)
			System.err.println("TEST FAILED: getInventoryList()");
		if(testReturnList.get(2).getIsbn() != 9780446580342L)
			System.err.println("TEST FAILED: getInventoryList()");
		
		// test getOrderedByAuthor
		LibraryGeneric<PhoneNumber> lib4 = new LibraryGeneric<PhoneNumber>();
		lib4.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib4.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib4.add(9780446580342L, "David Baldacci", "Simple Genius");
		lib4.add(9780446580343L, "David Baldacci", "Another Book");
		ArrayList<LibraryBookGeneric<PhoneNumber>> testReturnList2 = lib4.getOrderedByAuthor();
		
		// test that primary and secondary sort worked correctly
		if(!testReturnList2.get(0).getAuthor().equals("David Baldacci") || !testReturnList2.get(0).getTitle().equals("Another Book"))
			System.err.println("TEST FAILED: getOrderedByAuthor()");
		if(!testReturnList2.get(1).getAuthor().equals("David Baldacci") || !testReturnList2.get(1).getTitle().equals("Simple Genius"))
			System.err.println("TEST FAILED: getOrderedByAuthor()");
		if(!testReturnList2.get(2).getAuthor().equals("Jon Krakauer"))
			System.err.println("TEST FAILED: getOrderedByAuthor()");
		if(!testReturnList2.get(3).getAuthor().equals("Thomas L. Friedman"))
			System.err.println("TEST FAILED: getOrderedByAuthor()");
		
		// test getOverdueList
		LibraryGeneric<String> lib5 = new LibraryGeneric<String>();
		lib5.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib5.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib5.add(9780446580342L, "David Baldacci", "Simple Genius");
		ArrayList<LibraryBookGeneric<String>> testReturnList3 = lib5.getOverdueList(1,1,2015);
		
		// test against no checked out books/no overdue books
		if(!testReturnList3.isEmpty())
			System.err.println("TEST FAILED: getOverdueList");
		
		// create two checked out books, one overdue, one not
		lib5.checkout(9780330351690L, patron1, 1, 1, 2020);
		lib5.checkout(9780374292799L, patron1, 1, 1, 2008);
		lib5.checkout(9780446580342L, patron1, 1, 1, 2009);
		testReturnList3 = lib5.getOverdueList(1,1,2015);
		
		// test that the overdue books are in the returned ArrayList in the correct order
		if(testReturnList3.get(0).getIsbn() != 9780374292799L)
			System.err.println("TEST FAILED: getOverdueList");
		if(testReturnList3.get(1).getIsbn() != 9780446580342L)
			System.err.println("TEST FAILED: getOverdueList");
		// make sure non-overdue books are not returned
		if(testReturnList3.size() > 2)
			System.err.println("TEST FAILED: getOverdueList");
		
		System.out.println("Testing done.");
		
	}
}
