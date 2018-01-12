package assignment3;

import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.util.*;
import org.junit.Before;
import org.junit.Test;


/**
 * JUnit test class for  MySortedSet.
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class MySortedSetTest
{
	// An empty set
	private Integer[] set1 = new Integer[0];
	private ArrayList<Integer> test1 = new ArrayList<Integer>(
			Arrays.asList(set1));

	// A set of 1
	private Integer[] set2 = {0};
	private ArrayList<Integer> test2 = new ArrayList<Integer>(
			Arrays.asList(set2));

	// A set of 2
	private Integer[] set3 = {0, 2};
	private ArrayList<Integer> test3 = new ArrayList<Integer>(
			Arrays.asList(set3));

	// A set of 3
	private Integer[] set4 = {0, 1, 3};
	private ArrayList<Integer> test4 = new ArrayList<Integer>(
			Arrays.asList(set4));

	// A larger odd set
	private Integer[] set5 = {0, 1, 3, 4, 5, 6, 8, 9};
	private ArrayList<Integer> test5 = new ArrayList<Integer>(
			Arrays.asList(set5));

	// A larger even set
	private Integer[] set6 = {0, 1, 3, 4, 5, 6, 8, 9, 10};
	private ArrayList<Integer> test6 = new ArrayList<Integer>(
			Arrays.asList(set6));

	@Test
	public void testBinarySearch ()
	{

		// test an empty array
		MySortedSet<Integer> bSearch1 = new MySortedSet<Integer>();
		bSearch1.addAll(test1);
		assertEquals(0, bSearch1.binarySearch(-1)); // should always return 0
		assertEquals(0, bSearch1.binarySearch(5));  // should always return 0

		// test a set of 1
		MySortedSet<Integer> bSearch2 = new MySortedSet<Integer>();
		bSearch2.addAll(test2);
		assertEquals(0, bSearch2.binarySearch(-1)); // lowest/negative integer insert
		assertEquals(0, bSearch2.binarySearch(0));  // find
		assertEquals(1, bSearch2.binarySearch(1));  // highest insert

		// test a set of 2
		MySortedSet<Integer> bSearch3 = new MySortedSet<Integer>();
		bSearch3.addAll(test3);
		assertEquals(0, bSearch3.binarySearch(-1)); // lowest/negative integer insert
		assertEquals(0, bSearch3.binarySearch(0));  // find
		assertEquals(1, bSearch3.binarySearch(2));  // find
		assertEquals(1, bSearch3.binarySearch(1));  // middle insert
		assertEquals(2, bSearch3.binarySearch(3));  // highest insert

		// test a set of 3
		MySortedSet<Integer> bSearch4 = new MySortedSet<Integer>();
		bSearch4.addAll(test4);
		assertEquals(0, bSearch4.binarySearch(-1)); // lowest/negative integer insert
		assertEquals(0, bSearch4.binarySearch(0));  // find
		assertEquals(1, bSearch4.binarySearch(1));  // find
		assertEquals(2, bSearch4.binarySearch(3));  // find
		assertEquals(2, bSearch4.binarySearch(2));  // middle insert
		assertEquals(3, bSearch4.binarySearch(4));  // highest insert

		// test a larger odd sized set
		MySortedSet<Integer> bSearch5 = new MySortedSet<Integer>();
		bSearch5.addAll(test5);
		assertEquals(0, bSearch5.binarySearch(-1)); // lowest/negative integer insert
		assertEquals(0, bSearch5.binarySearch(0));  // find
		assertEquals(1, bSearch5.binarySearch(1));  // find
		assertEquals(2, bSearch5.binarySearch(2));  // middle-left insert
		assertEquals(6, bSearch5.binarySearch(7));  // middle-right insert
		assertEquals(5, bSearch5.binarySearch(6));  // find
		assertEquals(6, bSearch5.binarySearch(8));  // find
		assertEquals(8, bSearch5.binarySearch(10)); // highest insert

		// test a larger even sized set
		MySortedSet<Integer> bSearch6 = new MySortedSet<Integer>();
		bSearch6.addAll(test6);
		assertEquals(0, bSearch6.binarySearch(-1)); // lowest/negative integer insert
		assertEquals(0, bSearch6.binarySearch(0));  // find
		assertEquals(1, bSearch6.binarySearch(1));  // find
		assertEquals(2, bSearch6.binarySearch(2));  // middle-left insert
		assertEquals(6, bSearch6.binarySearch(7));  // middle-right insert
		assertEquals(5, bSearch6.binarySearch(6));  // find
		assertEquals(6, bSearch6.binarySearch(8));  // find
		assertEquals(9, bSearch6.binarySearch(11)); // highest insert

	}

	@Test
	public void testMySortedSet ()
	{
		MySortedSet<Integer> testConstructor = new MySortedSet<Integer>();
		assertTrue(testConstructor instanceof MySortedSet<?>);
		assertEquals(null, testConstructor.comparator());
	}

	@Test
	public void testMySortedSetComparator ()
	{
		MySortedSetTest makeComparator = new MySortedSetTest();
		ReverseComparator comparator = makeComparator.new ReverseComparator();
		MySortedSet<Integer> testConstructorComparator = new MySortedSet<Integer>(
				comparator);
		// test constructor with a comparator
		assertEquals(comparator, testConstructorComparator.comparator());
	}

	@Test
	public void testComparator ()
	{
		MySortedSetTest makeComparator = new MySortedSetTest();
		ReverseComparator comparator = makeComparator.new ReverseComparator();
		MySortedSet<Integer> testConstructorComparator = new MySortedSet<Integer>(
				comparator);
		// test comparator()
		assertEquals(comparator, testConstructorComparator.comparator());
	}

	@Test (expected = NoSuchElementException.class)
	public void testFirst ()
	{
		// test that the correct exception is thrown
		MySortedSet<Integer> firstTest1 = new MySortedSet<Integer>();
		firstTest1.addAll(test1);
		firstTest1.first();

		// test that the first item is returned
		MySortedSet<Integer> firstTest2 = new MySortedSet<Integer>();
		firstTest2.addAll(test2);
		assertEquals(0, (Comparable) firstTest2.first());

	}

	@Test (expected = NoSuchElementException.class)
	public void testLast ()
	{
		// test that the correct exception is thrown
		MySortedSet<Integer> lastTest1 = new MySortedSet<Integer>();
		lastTest1.addAll(test1);
		lastTest1.last();

		// test that the first item is returned
		MySortedSet<Integer> lastTest2 = new MySortedSet<Integer>();
		lastTest2.addAll(test5);
		assertEquals(9, (Comparable) lastTest2.last());
	}

	@Test
	public void testAdd ()
	{
		// add to an empty set, should return true
		MySortedSet<Integer> testAdd1 = new MySortedSet<Integer>();
		assertTrue(testAdd1.add(1));
		assertEquals(true, testAdd1.contains(1));
		assertEquals(0, testAdd1.binarySearch(1));

		// create existing set for testing addition to an existing set
		MySortedSet<Integer> testAdd2 = new MySortedSet<Integer>();
		testAdd2.addAll(test3);

		// add item that already exists in the set, should return false
		assertFalse(testAdd2.add(0));

		// add item that will be inserted at the beginning, should return true
		assertTrue(testAdd2.add(-1));
		assertEquals(0, testAdd2.binarySearch(-1));

		// add item that will be inserted in the middle, should return true
		assertTrue(testAdd2.add(1));
		assertEquals(2, testAdd2.binarySearch(1));

		// add item that will be inserted at the end, should return true
		assertTrue(testAdd2.add(3));
		assertEquals(4, testAdd2.binarySearch(3));
	}

	@Test
	public void testAddAll ()
	{
		// test adding an empty collection, returns false
		MySortedSet<Integer> mySet = new MySortedSet<Integer>();
		assertFalse(mySet.addAll(test1));
		
		// test adding a collection with all unique values, returns true
		MySortedSet<Integer> mySet2 = new MySortedSet<Integer>();
		assertTrue(mySet2.addAll(test5));
		
		// test adding a collection with unique and non-unique (will not be
		// added) values, returns true
		MySortedSet<Integer> setTest4 = new MySortedSet();
		setTest4.addAll(test4);
		assertTrue(setTest4.addAll(test5));
		
		// test adding a collection with all non-unique (will not be added)
		// values, returns false
		MySortedSet<Integer> setTest6 = new MySortedSet();
		setTest6.addAll(test6);
		assertFalse(setTest6.addAll(test6));
		
		MySortedSet<Integer> addAllTest = new MySortedSet<Integer>();
		addAllTest.addAll(test5);
		assertFalse(addAllTest.addAll(test5));

	}

	@Test
	public void testClear ()
	{
		MySortedSet<Integer> clearTest = new MySortedSet<Integer>();
		clearTest.addAll(test5);
		clearTest.clear();
		// test that clearTest is indeed empty, 3 different ways!
		assertTrue(clearTest.size() == 0);
		assertTrue(clearTest.isEmpty());
		assertTrue(clearTest.toArray().length == 0);
	}

	@Test
	public void testContains ()
	{
		// test an empty array
		MySortedSet<Integer> bSearch1 = new MySortedSet<Integer>();
		bSearch1.addAll(test1);
		assertFalse(bSearch1.contains(0));  // should always return false
		assertFalse(bSearch1.contains(-1)); // should always return false
		assertFalse(bSearch1.contains(5));  // should always return false

		// test a set of 1
		MySortedSet<Integer> bSearch2 = new MySortedSet<Integer>();
		bSearch2.addAll(test2);
		assertFalse(bSearch2.contains(-1)); // not contained in list, outside
											// range of values
		assertTrue(bSearch2.contains(0));   // contained in list
		assertFalse(bSearch2.contains(1));  // not contained in list, outside
											// range of values

		// test a larger odd set
		MySortedSet<Integer> bSearch5 = new MySortedSet<Integer>();
		bSearch5.addAll(test5);
		assertTrue(bSearch5.contains(3));   // contained in list
		assertTrue(bSearch5.contains(6));   // contained in list
		assertFalse(bSearch5.contains(-1)); // not contained in list, outside
											// range of values
		assertFalse(bSearch5.contains(7));  // not contained in list, inside
											// range of values

		// test a larger even set
		MySortedSet<Integer> bSearch6 = new MySortedSet<Integer>();
		bSearch6.addAll(test6);
		assertTrue(bSearch6.contains(5));   // contained in list
		assertTrue(bSearch6.contains(9));   // contained in list
		assertFalse(bSearch6.contains(-1)); // not contained in list, outside
											// range of values
		assertFalse(bSearch6.contains(2));  // not contained in list, inside
											// range of values
	}

	@Test
	public void testContainsAll ()
	{
		MySortedSet<Integer> containsAllTest1 = new MySortedSet<Integer>();
		containsAllTest1.addAll(test4);

		MySortedSet<Integer> containsAllTest2 = new MySortedSet<Integer>();
		containsAllTest2.addAll(test5);

		MySortedSet<Integer> containsAllTest3 = new MySortedSet<Integer>();
		containsAllTest3.addAll(test6);

		MySortedSet<Integer> containsAllTest4 = new MySortedSet<Integer>();
		containsAllTest4.addAll(test1);

		// test containsAll with two matching sets
		assertTrue(containsAllTest1.containsAll(test4));
		assertTrue(containsAllTest2.containsAll(test5));
		assertTrue(containsAllTest3.containsAll(test6));

		// test containsAll with two sets with common elements
		assertTrue(containsAllTest3.containsAll(test5));
		assertTrue(containsAllTest3.containsAll(test4));
		assertTrue(containsAllTest2.containsAll(test4));

		// test containsAll with two sets with no common elements
		assertFalse(containsAllTest1.containsAll(test6));
		assertFalse(containsAllTest1.containsAll(test5));
		assertFalse(containsAllTest2.containsAll(test6));

		// test containsAll with an empty set
		assertFalse(containsAllTest4.containsAll(test4));
		assertFalse(containsAllTest4.containsAll(test5));
		assertFalse(containsAllTest4.containsAll(test6));
		assertFalse(containsAllTest3.containsAll(test1));

		// test to show that when containsAll() is called with an empty
		// collection,
		// it should return false, as it does with other Collections, like
		// ArrayList
		ArrayList<Integer> contains1 = new ArrayList<Integer>();
		ArrayList<Integer> contains2 = new ArrayList<Integer>();
		contains1.add(8);
		assertFalse(contains1.contains(contains2));

	}

	@Test
	public void testIsEmpty ()
	{
		// Test a set that has nothing added to it
		MySortedSet<Integer> mySet = new MySortedSet<Integer>();
		assertTrue(mySet.isEmpty());

		// test a set that has something added to it
		// add 10 random integers, some may be duplicates (therefore not added)
		Random randomNum = new Random();
		HashSet hash = new HashSet();
		int number = 0;
		for (int i = 0; i < 10; i++)
		{
			number = (randomNum.nextInt(10) + 1);
			mySet.add(number);
			hash.add(number);
		}

		assertFalse(mySet.isEmpty());

		// test a set that has items removed from it
		mySet.removeAll(hash);
		assertTrue(mySet.isEmpty());
	}

	@Test
	public void testIterator ()
	{
		// create empty MySortedSet
		MySortedSet<Integer> mySet = new MySortedSet<Integer>();

		// empty
		// mySet should be empty check the iterator
		Iterator<Integer> myIterator = mySet.iterator();
		assertFalse(myIterator.hasNext());

		// should throw exception when next is called on empty set
		try
		{
			myIterator.next();
			fail("Did not throw NoSuchElementException when next was called on empty set");
		}
		catch (NoSuchElementException e)
		{
			// threw correct exception
		}

		// should throw exception when remove is called on empty set
		try
		{
			myIterator.remove();
			fail("Did not throw NoSuchElementException when next was called on empty set");
		}
		catch (IllegalStateException e)
		{
			// threw correct exception
		}

		// with one item
		// add random number to array
		Random randomNum = new Random();
		int number = randomNum.nextInt(10);
		mySet.add(number);

		// mySet now has one element
		assertTrue(myIterator.hasNext());

		// next has not been called so remove should throw error
		try
		{
			myIterator.remove();
			fail("Did not throw NoSuchElementException when next was called on empty set");
		}
		catch (IllegalStateException e)
		{
			// threw correct exception
		}

		// now call next
		assertEquals(number, myIterator.next().intValue());
		// remove should now work
		try
		{
			myIterator.remove();
		}
		catch (IllegalStateException e)
		{
			fail("remove() threw IllegalStateException when it should have worked");
		}

		// set should now be empty
		assertTrue(mySet.isEmpty());

		// add random numbers to array
		TreeSet<Integer> tree = new TreeSet<Integer>();

		// add ten random integers to tree and set may be duplicates, so size
		// may not be 10
		for (int i = 0; i < 10; i++)
		{
			number = (randomNum.nextInt(10) + 1);
			mySet.add(number);
			tree.add(number);
		}

		// next has not been called so remove should throw error
		try
		{
			myIterator.remove();
			fail("Did not throw NoSuchElementException when next was called on empty set");
		}
		catch (IllegalStateException e)
		{
			// threw correct exception
		}

		// now call next
		myIterator.next();
		// remove should now work
		try
		{
			myIterator.remove();
		}
		catch (IllegalStateException e)
		{
			fail("remove() threw IllegalStateException when it should have worked");
		}

		// try to call remove twice should throw illegalStateException
		try
		{
			myIterator.remove();
			fail("Did not throw NoSuchElementException when next was called twice in a row set");
		}
		catch (IllegalStateException e)
		{
			// threw correct error
		}

		// remove everything from list
		while (myIterator.hasNext())
		{
			myIterator.next();
			myIterator.remove();
		}

		// mySet should be empty
		assertTrue(mySet.isEmpty());
		assertEquals(0, mySet.size());

	}

	@Test
	public void testRemove ()
	{
		MySortedSet<Integer> removeTest = new MySortedSet<Integer>();
		removeTest.addAll(test6);

		// test removing an element that we know exists, should return true
		assertTrue(removeTest.remove(9));
		assertFalse(removeTest.contains(9));

		// test removing an element that we know does not exist, should return
		// false
		assertFalse(removeTest.remove(7));
		assertFalse(removeTest.contains(7));
	}

	@Test
	public void testRemoveAll ()
	{
		MySortedSet<Integer> removeAllTest1 = new MySortedSet<Integer>();
		removeAllTest1.addAll(test6);

		MySortedSet<Integer> removeAllTest2 = new MySortedSet<Integer>();
		removeAllTest2.addAll(test4);

		MySortedSet<Integer> removeAllTest3 = new MySortedSet<Integer>();
		removeAllTest3.addAll(test1);

		// test removing known items from a set, should return true
		assertTrue(removeAllTest1.removeAll(test4));
		int[] expectedResults = {4, 5, 6, 8, 9, 10};
		Object[] actualResults = removeAllTest1.toArray();
		for (int i = 0; i < actualResults.length; i++)
		{
			assertEquals(expectedResults[i], (int) actualResults[i]);
		}

		// test trying to remove items not in a set, should return false
		assertFalse(removeAllTest3.removeAll(test6));

		// test trying to remove items both in and not in the set, should return
		// true
		assertTrue(removeAllTest2.removeAll(test6));
	}

	@Test
	public void testSize ()
	{
		// test non-empty set
		MySortedSet<Integer> sizeTest = new MySortedSet<Integer>();
		sizeTest.addAll(test5);
		// expected size is 8
		assertTrue(sizeTest.size() == 8);
		assertTrue(sizeTest.toArray().length == 8);

		// test empty set
		MySortedSet<Integer> emptySizeTest = new MySortedSet<Integer>();
		emptySizeTest.addAll(test1);
		// expected size is 0
		assertTrue(emptySizeTest.size() == 0);
		assertTrue(emptySizeTest.toArray().length == 0);
	}

	@Test
	public void testToArray ()
	{
		// test toArray

		// expected set (matches test6)
		Object[] expectedSet = {0, 1, 3, 4, 5, 6, 8, 9, 10};

		// result of toArray() to check against expectedSet
		MySortedSet<Integer> toArrayTest = new MySortedSet<Integer>();
		toArrayTest.addAll(test6);
		Object[] resultSet = toArrayTest.toArray();

		// check for equality of expectedSet and resultSet
		for (int i = 0; i < expectedSet.length; i++)
		{
			assertEquals(expectedSet[i], resultSet[i]);
		}

		// test toArray on an empty set

		// create an empty ArrayList to pass into addAll()
		Integer[] emptySet = new Integer[0];
		ArrayList<Integer> emptySetList = new ArrayList<Integer>(
				Arrays.asList(emptySet));

		// result of toArray() to check
		MySortedSet<Integer> emptyToArrayTest = new MySortedSet<Integer>();
		emptyToArrayTest.addAll(emptySetList);
		Object[] emptyResultSet = emptyToArrayTest.toArray();

		// check for equality of emptySet
		assertTrue(emptyResultSet.length == 0);
	}

	/**
	 * Comparator that will be used solely for testing purposes orders Integers
	 * in reverse order
	 */
	protected class ReverseComparator implements Comparator<Integer>
	{

		/**
		 * orders Integer objects in the reverse natural ordering.
		 * 
		 * @return -1
		 */
		@Override
		public int compare (Integer int1, Integer int2)
		{
			return -1 * (int1.compareTo(int2));
		}

	}

	/**
	 * Comparator that will be used solely for testing purposes orders Integers
	 * in reverse order
	 */
	protected class ForwardComparator implements Comparator<Integer>
	{

		/**
		 * orders Integer objects in the reverse natural ordering.
		 * 
		 * @return -1
		 */
		@Override
		public int compare (Integer int1, Integer int2)
		{
			return (int1.compareTo(int2));
		}

	}
}
