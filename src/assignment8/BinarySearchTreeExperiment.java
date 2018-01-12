package assignment8;

import java.util.ArrayList;

/**
 * Timing experiment for BinarySearchTree
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 */
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class BinarySearchTreeExperiment
{

	private static int timesToLoop = 100;

	public static void main(String[] args)
	{
		 testBSTSorted();
		 //testBSTRandom();
		 //testBSTBalanceAdd();
		 //testBSTBalanceContains();
	}

	private static void testBSTSorted()
	{
		System.out.println("Timing BST with Sorted Insertion");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 15; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			BinarySearchTree<Integer> testTree = new BinarySearchTree<Integer>();
			for (int i = 0; i < setSize; i++)
			{
				testTree.add(i);
			}

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			// test using contains method
			for (int i = 0; i < timesToLoop; i++)
			{
				for (int j = 0; j < setSize; j++)
				{
					testTree.contains(j);
				}
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the loop
			for (int i = 0; i < timesToLoop; i++)
			{
				for (int j = 0; j < setSize; j++)
				{
				}
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop)/setSize;
			System.out.println(setSize + "\t" + averageTime);
		}
	}

	private static void testBSTRandom()
	{
		System.out.println("Timing BST with Random Insertion");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 15; mySetSizeTracker++)
		{
			Random rand = new Random();
			double setSize = Math.pow(2, mySetSizeTracker);
			BinarySearchTree<Integer> testTree = new BinarySearchTree<Integer>();
			ArrayList<Integer> randomArray = new ArrayList<Integer>();

			for (int i = 0; i < setSize; i++)
				randomArray.add(i);

			// shuffle the array and add it to the tree
			Collections.shuffle(randomArray);

			testTree.addAll(randomArray);

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			// test using the contains method
			for (long i = 0; i < timesToLoop; i++)
				for (int j = 0; j < setSize; j++)
					testTree.contains(j);

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the loop
			for (long i = 0; i < timesToLoop; i++)
			{
				for (int j = 0; j < setSize; j++)
				{
				}
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop)/1000000;
			System.out.println(setSize + "\t" + averageTime);
		}
	}

	private static void testBSTBalanceAdd()
	{
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		System.out.println("Timing Tree Random Add");
		System.out.println("Size" + "\t" + " TimingUn + \t TimingBal");
		System.out.println("---------------------------------");
		for (double mySetSizeTracker = 10; mySetSizeTracker < 15; mySetSizeTracker++)
		{
			/*
			 * Test add on BinarySearchTree (does not auto-balance)
			 */
			double setSize = Math.pow(2, mySetSizeTracker);
			Random rand = new Random();

			ArrayList<Integer> randomArray = new ArrayList<Integer>();

			for (int i = 0; i < setSize; i++)
				randomArray.add(i);

			// shuffle the array
			Collections.shuffle(randomArray);

			BinarySearchTree<Integer> testTree = new BinarySearchTree<Integer>();

			// test add on BinarySearchTree

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++)
			{
				testTree.addAll(randomArray);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the loop
			for (long i = 0; i < timesToLoop; i++)
			{
				// empty loop
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.print(setSize + "\t" + averageTime);

			/*
			 * test add on TreeSet (auto-balances)
			 */

			TreeSet<Integer> balancedTree = new TreeSet<Integer>();

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++)
			{
				balancedTree.addAll(randomArray);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the loop
			for (long i = 0; i < timesToLoop; i++)
			{
				// empty loop
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println("" + '\t' + averageTime);
		}
	}

	private static void testBSTBalanceContains()
	{
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		System.out.println("Timing Tree Random Contains");
		System.out.println("Size" + "\t" + " TimingUn + \t TimingBal");
		System.out.println("---------------------------------");
		for (double mySetSizeTracker = 10; mySetSizeTracker < 15; mySetSizeTracker++)
		{
			/*
			 * Test for BinarySearchTree (unbalanced)
			 */
			double setSize = Math.pow(2, mySetSizeTracker);
			ArrayList<Integer> randomArray = new ArrayList<Integer>();

			// add elements, then shuffle
			for (int i = 0; i < setSize; i++)
				randomArray.add(i);

			Collections.shuffle(randomArray);

			BinarySearchTree<Integer> testTree = new BinarySearchTree<Integer>();

			// test add on BinarySearchTree

			long startTime, midpointTime, stopTime;

			// add all elements to arrays
			testTree.addAll(randomArray);
			TreeSet<Integer> balancedTree = new TreeSet<Integer>();

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++)
			{
				testTree.containsAll(randomArray);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the loop
			for (long i = 0; i < timesToLoop; i++)
			{
				// empty loop
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.print(setSize + "\t" + averageTime);

			/*
			 * test add on TreeList (balanced)
			 */

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			startTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++)
			{
				balancedTree.containsAll(randomArray);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the loop
			for (long i = 0; i < timesToLoop; i++)
			{
				// empty loop
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println("" + '\t' + averageTime);
		}
	}
}
