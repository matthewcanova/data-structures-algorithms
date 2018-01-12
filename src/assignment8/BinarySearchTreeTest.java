package assignment8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the BinarySearchTree Class
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 *
 */

public class BinarySearchTreeTest
{
	BinarySearchTree<Integer> testTree, allLeftTree, allRightTree,
			rootOnlyTree, emptyTree;
	ArrayList<Integer> testTreeArray, allLeftTreeArray, allRightTreeArray,
			rootOnlyTreeArray;
	ArrayList<Integer> testList, emptyList;

	@Before
	public void setUp() throws Exception
	{
		testTree = new BinarySearchTree<Integer>();
		allLeftTree = new BinarySearchTree<Integer>();
		allRightTree = new BinarySearchTree<Integer>();
		rootOnlyTree = new BinarySearchTree<Integer>();
		emptyTree = new BinarySearchTree<Integer>();

		testTreeArray = new ArrayList<Integer>();
		allLeftTreeArray = new ArrayList<Integer>();
		allRightTreeArray = new ArrayList<Integer>();
		rootOnlyTreeArray = new ArrayList<Integer>();

		testList = new ArrayList<Integer>();
		emptyList = new ArrayList<Integer>();

		// Add elements to tree
		testTree.add(100);
		testTree.add(102);
		testTree.add(101);
		testTree.add(103);
		testTree.add(50);
		testTree.add(48);
		testTree.add(46);
		testTree.add(70);
		testTree.add(75);
		testTree.add(80);
		testTree.add(65);
		testTree.add(60);
		testTree.add(68);

		// Add elements to tree Array for comparison
		testTreeArray.add(100);
		testTreeArray.add(102);
		testTreeArray.add(101);
		testTreeArray.add(103);
		testTreeArray.add(50);
		testTreeArray.add(48);
		testTreeArray.add(46);
		testTreeArray.add(70);
		testTreeArray.add(75);
		testTreeArray.add(80);
		testTreeArray.add(65);
		testTreeArray.add(60);
		testTreeArray.add(68);

		for (int i = 5; i < 55; i++)
		{
			allRightTree.add(i);
			allRightTreeArray.add(i);
		}

		allRightTree.add(60);
		allRightTreeArray.add(60);

		for (int i = 60; i > 6; i--)
		{
			allLeftTree.add(i);
			allLeftTreeArray.add(i);
		}

		rootOnlyTree.add(5);
		rootOnlyTreeArray.add(5);

		testList.add(60);
		testList.add(70);
		testList.add(80);
		testList.add(90);
	}

	@Test
	public void testAddTestTree()
	{
		assertTrue(testTree.add(5));
		assertTrue(testTree.contains(5));
		assertTrue(testTree.contains(46));
		assertTrue(testTree.size() == 14);

		assertTrue(testTree.add(61));
		assertTrue(testTree.contains(61));
		assertTrue(testTree.contains(65));
		assertTrue(testTree.size() == 15);

		assertTrue(testTree.add(78));
		assertTrue(testTree.contains(78));
		assertTrue(testTree.contains(80));
		assertTrue(testTree.size() == 16);

		assertFalse(testTree.add(5));
		assertTrue(testTree.contains(5));
		assertTrue(testTree.contains(46));
		assertTrue(testTree.size() == 16);
	}

	@Test
	public void testAddTestLeftTree()
	{
		assertTrue(allLeftTree.add(4));
		assertTrue(allLeftTree.contains(4));
		assertTrue(allLeftTree.size() == 55);

		assertTrue(allLeftTree.add(70));
		assertTrue(allLeftTree.contains(70));
		assertTrue(allLeftTree.size() == 56);

		assertTrue(allLeftTree.add(5));
		assertTrue(allLeftTree.contains(5));
		assertTrue(allLeftTree.size() == 57);

		assertFalse(allLeftTree.add(5));
		assertTrue(allLeftTree.contains(5));
		assertTrue(allLeftTree.size() == 57);
	}

	@Test
	public void testAddTestRightTree()
	{
		assertTrue(allRightTree.add(58));
		assertTrue(allRightTree.contains(58));
		assertTrue(allRightTree.size() == 52);

		assertTrue(allRightTree.add(3));
		assertTrue(allRightTree.contains(3));
		assertTrue(allRightTree.size() == 53);

		assertTrue(allRightTree.add(57));
		assertTrue(allRightTree.contains(57));
		assertTrue(allRightTree.size() == 54);

		assertFalse(allRightTree.add(57));
		assertTrue(allRightTree.contains(57));
		assertTrue(allRightTree.size() == 54);
	}

	@Test
	public void testAddTestRootTree()
	{
		assertFalse(rootOnlyTree.add(5)); //duplicate
		assertTrue(rootOnlyTree.contains(5));
		assertTrue(rootOnlyTree.size() == 1);

		assertTrue(rootOnlyTree.add(3));
		assertTrue(rootOnlyTree.contains(3));
		assertTrue(rootOnlyTree.size() == 2);

		assertTrue(rootOnlyTree.add(6));
		assertTrue(rootOnlyTree.contains(6));
		assertTrue(rootOnlyTree.size() == 3);
	}

	@Test
	public void testAddEmptyTree()
	{
		assertTrue(emptyTree.add(5));
		assertTrue(emptyTree.contains(5));
		assertTrue(emptyTree.size() == 1);
	}

	@Test(expected = NullPointerException.class)
	public void testAddException()
	{
		testTree.add(null);
	}

	@Test
	public void testAddAllTestTree()
	{
		assertTrue(testTree.addAll(testList));
		assertTrue(testTree.contains(60));
		assertTrue(testTree.contains(70));
		assertTrue(testTree.contains(80));
		assertTrue(testTree.contains(90));
		assertTrue(testTree.size() == 14);

		assertFalse(testTree.addAll(emptyList));
		assertTrue(testTree.size() == 14);

		assertFalse(testTree.addAll(testList));
		assertTrue(testTree.size() == 14);
	}

	@Test
	public void testAddAllTestLeftTree()
	{
		assertTrue(allLeftTree.addAll(testList));
		assertTrue(allLeftTree.contains(60)); // duplicate
		assertTrue(allLeftTree.contains(70));
		assertTrue(allLeftTree.contains(80));
		assertTrue(allLeftTree.contains(90));
		assertTrue(allLeftTree.size() == 57);

		assertFalse(allLeftTree.addAll(emptyList));
		assertTrue(allLeftTree.size() == 57);

		assertFalse(allLeftTree.addAll(testList));
		assertTrue(allLeftTree.size() == 57);
	}

	@Test
	public void testAddAllTestRightTree()
	{
		assertTrue(allRightTree.addAll(testList));
		assertTrue(allRightTree.contains(60));
		assertTrue(allRightTree.contains(70));
		assertTrue(allRightTree.contains(80));
		assertTrue(allRightTree.contains(90));
		assertTrue(allRightTree.size() == 54);

		assertFalse(allRightTree.addAll(emptyList));
		assertTrue(allRightTree.size() == 54);

		assertFalse(allRightTree.addAll(testList));
		assertTrue(allRightTree.size() == 54);
	}

	@Test
	public void testAddAllTestRootTree()
	{
		assertTrue(rootOnlyTree.addAll(testList));
		assertTrue(rootOnlyTree.contains(60));
		assertTrue(rootOnlyTree.contains(70));
		assertTrue(rootOnlyTree.contains(80));
		assertTrue(rootOnlyTree.contains(90));
		assertTrue(rootOnlyTree.size() == 5);

		assertFalse(rootOnlyTree.addAll(emptyList));
		assertTrue(rootOnlyTree.size() == 5);

		assertFalse(rootOnlyTree.addAll(testList));
		assertTrue(rootOnlyTree.size() == 5);
	}

	@Test
	public void testAddAllEmptyTree()
	{
		assertTrue(emptyTree.addAll(testList));
		assertTrue(emptyTree.contains(60));
		assertTrue(emptyTree.contains(70));
		assertTrue(emptyTree.contains(80));
		assertTrue(emptyTree.contains(90));
		assertTrue(emptyTree.size() == 4);

		assertFalse(emptyTree.addAll(emptyList));
		assertTrue(emptyTree.size() == 4);

		assertFalse(emptyTree.addAll(testList));
		assertTrue(emptyTree.size() == 4);
	}

	@Test(expected = NullPointerException.class)
	public void testAddAllException()
	{
		ArrayList<Integer> exceptionArray = new ArrayList<Integer>();
		exceptionArray.add(null);
		testTree.addAll(exceptionArray);
	}

	@Test
	public void tesClearTestTree()
	{
		testTree.clear();
		assertTrue(testTree.isEmpty());
		assertFalse(testTree.contains(100));
	}

	@Test
	public void testClearTestLeftTree()
	{
		allLeftTree.clear();
		assertTrue(allLeftTree.isEmpty());
		assertFalse(allLeftTree.contains(7));
	}

	@Test
	public void testClearTestRightTree()
	{
		allRightTree.clear();
		assertTrue(allRightTree.isEmpty());
		assertFalse(allRightTree.contains(8));
	}

	@Test
	public void testClearTestRootTree()
	{
		rootOnlyTree.clear();
		assertTrue(rootOnlyTree.isEmpty());
		assertFalse(rootOnlyTree.contains(5));
	}

	@Test
	public void testClearEmptyTree()
	{
		emptyTree.clear();
		assertTrue(emptyTree.isEmpty());
	}

	@Test
	public void testContainsTestTree()
	{
		assertTrue(testTree.contains(100));
		assertTrue(testTree.contains(46));
		assertTrue(testTree.contains(103));
		assertTrue(testTree.contains(60));
		assertTrue(testTree.contains(68));

		assertFalse(testTree.contains(40));
		assertFalse(testTree.contains(105));
		assertFalse(testTree.contains(58));
		assertFalse(testTree.contains(69));
	}

	@Test
	public void testContainsTestLeftTree()
	{
		for (int i = 60; i > 6; i--)
			assertTrue(allLeftTree.contains(i));

		assertFalse(allLeftTree.contains(2));
		assertFalse(allLeftTree.contains(70));
	}

	@Test
	public void testContainsTestRightTree()
	{
		for (int i = 5; i < 55; i++)
			assertTrue(allRightTree.contains(i));
		
		assertFalse(allRightTree.contains(65));
		assertFalse(allRightTree.contains(3));
	}

	@Test
	public void testContainsTestRootTree()
	{
		assertTrue(rootOnlyTree.contains(5));
		assertFalse(rootOnlyTree.contains(7));
		assertFalse(rootOnlyTree.contains(3));
	}

	@Test
	public void testContainsEmptyTree()
	{
		assertFalse(emptyTree.contains(5));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsException()
	{
		testTree.contains(null);
	}

	@Test
	public void testContainsAllTestTree()
	{
		assertTrue(testTree.containsAll(testTreeArray));
		assertFalse(testTree.containsAll(allRightTreeArray));
		assertFalse(testTree.containsAll(emptyList));
	}

	@Test
	public void testContainsAllTestLeftTree()
	{
		assertTrue(allLeftTreeArray.containsAll(allLeftTreeArray));
		assertFalse(allLeftTreeArray.containsAll(testTreeArray));
		assertTrue(allLeftTreeArray.containsAll(emptyList));
	}

	@Test
	public void testContainsAllTestRightTree()
	{
		assertTrue(allRightTreeArray.containsAll(allRightTreeArray));
		assertFalse(allRightTreeArray.containsAll(testList));
		assertTrue(allRightTreeArray.containsAll(emptyList));
	}

	@Test
	public void testContainsAllTestRootTree()
	{
		assertFalse(rootOnlyTree.containsAll(testList));
		assertFalse(rootOnlyTree.containsAll(emptyList));
	}

	@Test
	public void testContainsAllEmptyTree()
	{
		assertFalse(emptyTree.containsAll(testList));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsAllException()
	{
		ArrayList<Integer> exceptionArray = new ArrayList<Integer>();
		exceptionArray.add(null);
		testTree.containsAll(exceptionArray);
	}

	@Test
	public void testFirstTestTree()
	{
		assertTrue(testTree.first().equals(46));
	}

	@Test
	public void testFirstTestLeftTree()
	{
		assertTrue(allLeftTree.first().equals(7));
	}

	@Test
	public void testFirstTestRightTree()
	{
		assertTrue(allRightTree.first().equals(5));
	}

	@Test
	public void testFirstTestRootTree()
	{
		assertTrue(rootOnlyTree.first().equals(5));
	}

	@Test(expected = NoSuchElementException.class)
	public void testFirstException()
	{
		emptyTree.first();
	}

	@Test
	public void testIsEmptyTestTree()
	{
		assertFalse(testTree.isEmpty());
	}

	@Test
	public void testIsEmptyTestLeftTree()
	{
		assertFalse(allLeftTree.isEmpty());
	}

	@Test
	public void testIsEmptyTestRightTree()
	{
		assertFalse(allRightTree.isEmpty());
	}

	@Test
	public void testIsEmptyTestRootTree()
	{
		assertFalse(rootOnlyTree.isEmpty());
	}

	@Test
	public void testIsEmptyEmptyTree()
	{
		assertTrue(emptyTree.isEmpty());
	}

	@Test
	public void testLastTestTree()
	{
		assertTrue(testTree.last().equals(103));
	}

	@Test
	public void testLastTestLeftTree()
	{
		assertTrue(allLeftTree.last().equals(60));
	}

	@Test
	public void testLastTestRightTree()
	{
		assertTrue(allRightTree.last().equals(60));
	}

	@Test
	public void testLastTestRootTree()
	{
		assertTrue(rootOnlyTree.last().equals(5));
	}

	@Test(expected = NoSuchElementException.class)
	public void testLastException()
	{
		emptyTree.last();
	}

	@Test
	public void testRemoveTestTree()
	{
		assertTrue(testTree.remove(46));
		assertTrue(testTree.size() == 12);
		assertFalse(testTree.contains(46));
		
		assertTrue(testTree.remove(103));
		assertTrue(testTree.size() == 11);
		assertFalse(testTree.contains(103));
		
		testTree.add(103);
		
		testTree.writeDOT("Before.dot");
		assertTrue(testTree.remove(102));
		testTree.writeDOT("after.dot");
		assertTrue(testTree.size() == 11);
		assertFalse(testTree.contains(102));
		assertTrue(testTree.contains(101));
		assertTrue(testTree.contains(103));
		
		assertTrue(testTree.remove(75));
		assertFalse(testTree.contains(75));
		assertTrue(testTree.size() == 10);
		assertTrue(testTree.contains(80));
		
		assertTrue(testTree.remove(50));
		assertFalse(testTree.contains(50));
		assertTrue(testTree.contains(70));
		assertTrue(testTree.contains(48));
		assertTrue(testTree.size() == 9);
		
		assertFalse(testTree.remove(500));
		assertTrue(testTree.size() == 9);
	}

	@Test
	public void testRemoveTestLeftTree()
	{
		assertTrue(allLeftTree.remove(7));
		assertTrue(allLeftTree.size() == 53);
		assertFalse(allLeftTree.contains(6));
		
		assertTrue(allLeftTree.remove(15));
		assertTrue(allLeftTree.size() == 52);
		assertFalse(allLeftTree.contains(15));
		
		assertTrue(allLeftTree.remove(60));
		assertTrue(allLeftTree.size() == 51);
		assertFalse(allLeftTree.contains(60));
		
		assertFalse(allLeftTree.remove(100));
		assertTrue(allLeftTree.size() == 51);
	}

	@Test
	public void testRemoveTestRightTree()
	{
		assertTrue(allRightTree.remove(50));
		assertTrue(allRightTree.size() == 50);
		assertFalse(allRightTree.contains(50));
		
		assertTrue(allRightTree.remove(15));
		assertTrue(allRightTree.size() == 49);
		assertFalse(allRightTree.contains(15));
		
		assertTrue(allRightTree.remove(5));
		assertTrue(allRightTree.size() == 48);
		assertFalse(allRightTree.contains(5));
		
		assertFalse(allRightTree.remove(100));
		assertTrue(allRightTree.size() == 48);
		assertFalse(allRightTree.contains(100));
	}

	@Test
	public void testRemoveTestRootTree()
	{
		assertTrue(rootOnlyTree.remove(5));
		assertEquals(0, rootOnlyTree.size());
		assertFalse(rootOnlyTree.contains(5));
	}

	@Test
	public void testRemoveEmptyTree()
	{
		assertFalse(emptyTree.remove(5));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveException()
	{
		testTree.remove(null);
	}

	@Test
	public void testRemoveAllTestTree()
	{
		ArrayList<Integer> removeArray = new ArrayList<Integer>();
		
		removeArray.add(103);
		removeArray.add(100);
		removeArray.add(60);
		
		assertTrue(testTree.removeAll(removeArray));
		assertFalse(testTree.removeAll(removeArray));
		
		assertEquals(10, testTree.size());
		assertFalse(testTree.contains(103));
		assertFalse(testTree.contains(100));
		assertFalse(testTree.contains(60));
	}

	@Test
	public void testRemoveAllTestLeftTree()
	{
		ArrayList<Integer> removeArray = new ArrayList<Integer>();
		removeArray.add(20);
		removeArray.add(30);
		removeArray.add(40);
		
		assertTrue(allLeftTree.removeAll(removeArray));
		assertFalse(allLeftTree.removeAll(removeArray));
		
		assertEquals(51, allLeftTree.size());
		assertFalse(allLeftTree.contains(20));
		assertFalse(allLeftTree.contains(30));
		assertFalse(allLeftTree.contains(40));

	}

	@Test
	public void testRemoveAllTestRightTree()
	{
		ArrayList<Integer> removeArray = new ArrayList<Integer>();
		removeArray.add(20);
		removeArray.add(30);
		removeArray.add(40);
		
		assertTrue(allRightTree.removeAll(removeArray));
		assertFalse(allRightTree.removeAll(removeArray));
		
		assertEquals(48, allRightTree.size());
		assertFalse(allRightTree.contains(20));
		assertFalse(allRightTree.contains(30));
 		assertFalse(allRightTree.contains(40));

	}

	@Test
	public void testRemoveAllTestRootTree()
	{
		ArrayList<Integer> removeArray = new ArrayList<Integer>();
		removeArray.add(5);
		
		assertTrue(rootOnlyTree.removeAll(removeArray));
		assertFalse(rootOnlyTree.removeAll(removeArray));
		
		assertEquals(0, rootOnlyTree.size());
		assertFalse(rootOnlyTree.contains(5));
	}

	@Test
	public void testRemoveAllEmptyTree()
	{
		ArrayList<Integer> removeArray = new ArrayList<Integer>();
		removeArray.add(5);
		
		assertFalse(emptyTree.removeAll(removeArray));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveAllException()
	{
		ArrayList<Integer> exceptionArray = new ArrayList<Integer>();
		exceptionArray.add(null);
		testTree.removeAll(exceptionArray);
	}

	@Test
	public void testSizeTestTree()
	{
		assertEquals(13, testTree.size());
	}

	@Test
	public void testSizeTestLeftTree()
	{
		assertEquals(54, allLeftTree.size());
	}

	@Test
	public void testSizeTestRightTree()
	{
		assertEquals(51, allRightTree.size());
	}

	@Test
	public void testSizeTestRootTree()
	{
		assertEquals(1, rootOnlyTree.size());
	}

	@Test
	public void testSizeEmptyTree()
	{
		assertEquals(0, emptyTree.size());
	}

	@Test
	public void testToArrayListTestTree()
	{
		Collections.sort(testTreeArray); //toArray returns in sorted order
		assertArrayEquals(testTree.toArrayList().toArray(),
				testTreeArray.toArray());
	}

	@Test
	public void testToArrayListTestLeftTree()
	{
		Collections.sort(allLeftTreeArray);
		assertArrayEquals(allLeftTree.toArrayList().toArray(),
				allLeftTreeArray.toArray());
	}

	@Test
	public void testToArrayListTestRightTree()
	{
		assertArrayEquals(allRightTree.toArrayList().toArray(),
				allRightTreeArray.toArray());
	}

	@Test
	public void testToArrayListTestRootTree()
	{
		assertArrayEquals(rootOnlyTree.toArrayList().toArray(),
				rootOnlyTreeArray.toArray());
	}

	@Test
	public void testToArrayListEmptyTree()
	{
		Object[] emptyArray = {};
		assertArrayEquals(emptyTree.toArrayList().toArray(), emptyArray);
	}

}
