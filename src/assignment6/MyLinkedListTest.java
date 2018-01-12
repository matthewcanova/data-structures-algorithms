package assignment6;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the MyLinkedList class
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 *
 */
public class MyLinkedListTest
{

	MyLinkedList<Integer> testList, emptyList, oneElementList, twoElementList,
			duplicatesList;

	@Before
	public void setUp() throws Exception
	{
		// list for testing a large doubly-linked list
		testList = new MyLinkedList<Integer>();
		for (int i = 0; i < 50; i++)
		{
			testList.addLast(i);
		}

		// list for testing an empty doubly-linked list
		emptyList = new MyLinkedList<Integer>();

		// list for testing a doubly-linked list with one value
		oneElementList = new MyLinkedList<Integer>();
		oneElementList.addLast(5);

		// list for testing a doubly-linked list with two values
		twoElementList = new MyLinkedList<Integer>();
		twoElementList.addLast(5);
		twoElementList.addLast(10);

		// list for testing doubly-linked list with duplicate values
		duplicatesList = new MyLinkedList<Integer>();
		for (int i = 0; i < 50; i++)
		{
			duplicatesList.addLast(i);
		}
		for (int i = 0; i < 50; i++)
		{
			duplicatesList.addLast(i);
		}
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testAddFirst()
	{
		// add first to testList
		testList.addFirst(5);
		assertTrue(testList.get(0).equals(5));
		assertTrue(testList.size() == 51);

		// add first to emptyList
		emptyList.addFirst(5);
		assertTrue(emptyList.get(0).equals(5));
		assertTrue(emptyList.size() == 1);

		// add first to oneElementList
		oneElementList.addFirst(5);
		assertTrue(oneElementList.get(0).equals(5));
		assertTrue(oneElementList.size() == 2);

		// add first to twoElementList
		twoElementList.addFirst(5);
		assertTrue(twoElementList.get(0).equals(5));
		assertTrue(twoElementList.size() == 3);
	}

	@Test
	public void testAddLast()
	{
		// add last to testList
		testList.addLast(5);
		assertTrue(testList.get(50).equals(5));
		assertTrue(testList.size() == 51);

		// add last to emptyList
		emptyList.addLast(5);
		assertTrue(emptyList.get(0).equals(5));
		assertTrue(emptyList.size() == 1);

		// add last to oneElementList
		oneElementList.addLast(5);
		assertTrue(oneElementList.get(1).equals(5));
		assertTrue(oneElementList.size() == 2);

		// add last to twoElementList
		twoElementList.addLast(5);
		assertTrue(twoElementList.get(2).equals(5));
		assertTrue(twoElementList.size() == 3);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdd()
	{
		// add to a non-existent index in testList
		testList.add(100, 5);

		// add to the beginning of testList
		testList.add(0, 5);
		assertTrue(testList.get(0).equals(5));
		assertTrue(testList.size() == 51);

		// add to the middle of testList
		testList.add(20, 3);
		assertTrue(testList.get(20).equals(3));
		assertTrue(testList.size() == 52);

		// add to the end of testList
		testList.add(50, 5);
		assertTrue(testList.get(50).equals(5));
		assertTrue(testList.size() == 53);

		// add to emptyList
		emptyList.add(0, 6);
		assertTrue(emptyList.get(0).equals(5));
		assertTrue(emptyList.size() == 1);

		// add to the beginning of oneElementList
		oneElementList.add(0, 1);
		assertTrue(oneElementList.get(0).equals(5));
		assertTrue(oneElementList.size() == 2);

		// add to the end of oneElementList
		oneElementList.add(1, 9);
		assertTrue(oneElementList.get(1).equals(9));
		assertTrue(oneElementList.size() == 2);

		// add to the beginning of twoElementList
		twoElementList.add(0, 12);
		assertTrue(twoElementList.get(0).equals(12));
		assertTrue(twoElementList.size() == 3);

		// add to the middle of twoElementList
		twoElementList.add(1, 15);
		assertTrue(twoElementList.get(1).equals(15));
		assertTrue(twoElementList.size() == 3);

		// add to the end of twoElementList
		twoElementList.add(2, 16);
		assertTrue(twoElementList.get(2).equals(16));
		assertTrue(twoElementList.size() == 3);
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetFirst()
	{
		// get first element from testList
		assertTrue(testList.getFirst().equals(0));

		// get first element from emptyList (should return error)
		emptyList.getFirst();

		// get first element from oneElementList
		assertTrue(oneElementList.getFirst().equals(5));

		// get first element from twoElementList
		assertTrue(twoElementList.getFirst().equals(5));
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetLast()
	{
		// get last element from testList
		assertTrue(testList.getLast().equals(49));

		// get last element from emptyList (should return error)
		emptyList.getLast();

		// get last element from oneElementList
		assertTrue(oneElementList.getLast().equals(5));

		// get last element from twoElementList
		assertTrue(twoElementList.getLast().equals(10));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet()
	{
		// get element from testList at index
		assertTrue(testList.get(2).equals(2));

		// get element from testList at index out of bounds (should throw error)
		testList.get(50);

		// get element from emptyList at index (should throw error)
		emptyList.get(0);
		emptyList.get(5);

		// get element from oneElementList at index
		assertTrue(oneElementList.get(0).equals(5));

		// get element from twoElementList at index
		assertTrue(twoElementList.get(0).equals(5));
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst()
	{
		// remove and returns first element from testList
		Object test = testList.removeFirst();
		assertTrue(test.equals(0));
		assertFalse(testList.getFirst().equals(0));
		assertTrue(testList.size() == 49);

		// remove and returns first element from emptyList (Should throw error)
		emptyList.removeFirst();

		// remove and returns first element from oneElementList (should be empty
		// afterwards)
		Object oneElementTest = oneElementList.removeFirst();
		assertTrue(oneElementTest.equals(5));
		assertTrue(oneElementList.isEmpty());

		// remove and returns first element from twoElementList
		Object twoElementTest = twoElementList.removeFirst();
		assertTrue(twoElementTest.equals(5));
		assertFalse(twoElementList.getFirst().equals(5));
		assertTrue(twoElementList.size() == 1);
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveLast()
	{
		// remove and returns last element from testList
		Object test = testList.removeLast();
		assertTrue(test.equals(49));
		assertTrue(testList.size() == 49);
		try
		{
			assertFalse(testList.get(49).equals(49));
		} catch (IndexOutOfBoundsException e)
		{

		}

		// remove and returns last element from emptyList (Should throw error)
		emptyList.removeLast();

		// remove and returns last element from oneElementList (should be empty
		// afterwards)
		Object oneElementTest = oneElementList.removeLast();
		assertTrue(oneElementTest.equals(5));
		assertTrue(oneElementList.isEmpty());

		// remove and returns last element from twoElementList
		Object twoElementTest = twoElementList.removeLast();
		assertTrue(twoElementTest.equals(10));
		assertFalse(twoElementList.getLast().equals(10));
		assertTrue(twoElementList.size() == 1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove()
	{
		// remove element from testList
		Object test = testList.remove(5);
		assertTrue(test.equals(5));
		assertFalse(testList.get(5).equals(5));
		assertTrue(testList.size() == 49);

		// try to remove element from testList at index out of bounds (should
		// throw error)
		testList.remove(50);

		// remove element from emptyList (should throw
		// IndexOutOfBoundsException)
		emptyList.remove(0);

		// remove element from oneElementList (should be empty afterwards)
		Object oneElementTest = oneElementList.remove(0);
		assertTrue(oneElementTest.equals(5));
		assertTrue(oneElementList.isEmpty());

		// remove element from twoElementList (should have one element
		// afterwards)
		Object twoElementTest = twoElementList.remove(1);
		assertTrue(twoElementTest.equals(1));
		assertFalse(twoElementList.getLast().equals(10));
		assertTrue(oneElementList.size() == 1);
	}

	@Test
	public void testIndexOf()
	{
		// return the first index of the item in testList
		assertEquals(testList.indexOf(0), 0);
		assertEquals(testList.indexOf(25), 25);
		assertEquals(testList.indexOf(49), 49);

		// return -1 for item not in testList
		assertEquals(testList.indexOf(50), -1);

		// return -1 for emptyList
		assertEquals(emptyList.indexOf(10), -1);

		// return the first index of item in oneElementList
		assertEquals(oneElementList.indexOf(5), 0);

		// return -1 for item not in oneElementList
		assertEquals(oneElementList.indexOf(10), -1);

		// return first index of item in twoElementList
		assertEquals(twoElementList.indexOf(5), 0);
		assertEquals(twoElementList.indexOf(10), 1);

		// return -1 for item not in twoElementList
		assertEquals(twoElementList.indexOf(15), -1);

		// duplicatesList will have values at i and i+50
		// for indexOf, we should get the i return value
		assertEquals(duplicatesList.indexOf(0), 0); // should not return 50
		assertEquals(duplicatesList.indexOf(20), 20); // should not return 70
		assertEquals(duplicatesList.indexOf(49), 49); // should not return 99

		// returns -1 for item not in duplicatesList
		assertEquals(duplicatesList.indexOf(50), -1);

	}

	@Test
	public void testLastIndexOf()
	{
		// returns index of item of testList
		assertEquals(testList.lastIndexOf(0), 0);
		assertEquals(testList.lastIndexOf(25), 25);
		assertEquals(testList.lastIndexOf(49), 49);

		// returns -1 if item is not in testList
		assertEquals(testList.lastIndexOf(50), -1);

		// returns -1 for emptyList
		assertEquals(emptyList.lastIndexOf(0), -1);

		// returns index of item in oneElementList
		assertEquals(oneElementList.lastIndexOf(5), 0);

		// returns -1 for item not in oneElementList
		assertEquals(oneElementList.lastIndexOf(10), -1);

		// returns index of item of twoElementList
		assertEquals(twoElementList.lastIndexOf(5), 0);
		assertEquals(twoElementList.lastIndexOf(10), 1);

		// returns -1 for item not in twoElementList
		assertEquals(twoElementList.lastIndexOf(15), -1);

		// duplicatesList will have values at i and i+50
		// for lastIndexOf, we should get the i+50 return value
		assertEquals(duplicatesList.lastIndexOf(0), 50); // should not return 0
		assertEquals(duplicatesList.lastIndexOf(30), 80); // should not return
															// 30
		assertEquals(duplicatesList.lastIndexOf(49), 99); // should not return
															// 49

		// returns -1 for item not in duplicatesList
		assertEquals(duplicatesList.lastIndexOf(50), -1);
	}

	@Test
	public void testSize()
	{
		// testList should return 50
		assertEquals(testList.size(), 50);

		// emptyList should return 0
		assertEquals(emptyList.size(), 0);

		// oneElementList should return 1
		assertEquals(oneElementList.size(), 1);

		// twoElementList should return 2
		assertEquals(twoElementList.size(), 2);
	}

	@Test
	public void testIsEmpty()
	{
		// testList should return false
		assertFalse(testList.isEmpty());

		// emptyList should return true
		assertTrue(emptyList.isEmpty());

		// oneElementList should return false
		assertFalse(oneElementList.isEmpty());

		// twoElementList should return false
		assertFalse(twoElementList.isEmpty());
	}

	@Test
	public void testClear()
	{
		// clear testList
		testList.clear();
		assertTrue(testList.isEmpty());

		// clear emptyList
		emptyList.clear();
		assertTrue(emptyList.isEmpty());

		// clear oneElementList
		oneElementList.clear();
		assertTrue(oneElementList.isEmpty());

		// clear twoElementList
		twoElementList.clear();
		assertTrue(twoElementList.isEmpty());
	}

	@Test
	public void testToArray()
	{
		// testList toArray
		Object[] testListArray = testList.toArray();
		assertTrue(testListArray.length == 50);
		for (int i = 0; i < testListArray.length; i++)
		{
			assertTrue(testListArray[i].equals(i));
		}

		// emptyList toArray
		Object[] emptyListArray = emptyList.toArray();
		assertTrue(emptyListArray.length == 0);

		// oneElementList toArray
		Object[] oneElementListArray = oneElementList.toArray();
		assertTrue(oneElementListArray.length == 1);
		assertTrue(oneElementListArray[0] == (Object) 5);

		// twoElementList to Array
		Object[] twoElementListArray = twoElementList.toArray();
		assertTrue(twoElementListArray.length == 2);
		assertTrue(twoElementListArray[0] == (Object) 5);
		assertTrue(twoElementListArray[1] == (Object) 10);
	}

}
