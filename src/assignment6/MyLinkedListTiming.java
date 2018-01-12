package assignment6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

/**
 * Timing tests for the MyLinkedList class.
 * 
 * @author Trevor Chapman, Matthew Canova
 */
public class MyLinkedListTiming
{

	public static final long timesToLoop = 100;
	static Random rand;

	public static void main(String[] args)
	{
		rand = new Random();
		testMyLinkedListAddFirst();
		testMyLinkedListGet();
		testMyLinkedListRemove();
//		testArrayListAdd();
//		testArrayListGet();
//		testArrayListRemove();
	}

	private static void testMyLinkedListAddFirst()
	{
		System.out.println("Timing MyLinkedList AddFirst");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 9; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			LinkedList<Integer> testList = new LinkedList<Integer>();
			for (int i = 0; i < setSize; i ++)
			{
				testList.addFirst(i);
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

			for (long i = 0; i < timesToLoop; i++)
			{
				testList.addFirst(5);

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
			System.out.println(setSize + "\t" + averageTime);
		}
	}

	private static void testMyLinkedListGet()
	{
		System.out.println("Timing MyLinkedList Get");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			LinkedList<Integer> testList = new LinkedList<Integer>();
			for (int i = 0; i < setSize; i ++)
			{
				testList.addFirst(i);
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

			for (long i = 0; i < timesToLoop; i++)
			{
				testList.get(rand.nextInt((int)setSize));

			}

			midpointTime = System.nanoTime();
			
			// Run a loop to capture the cost of running the loop 
			for (long i = 0; i < timesToLoop; i++)
			{
				rand.nextInt((int)setSize+1);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println(setSize + "\t" + averageTime);
		}
	}

	private static void testMyLinkedListRemove()
	{
		System.out.println("Timing MyLinkedList Remove");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			LinkedList<Integer> testList = new LinkedList<Integer>();
			for (int i = 0; i < setSize; i ++)
			{
				testList.addFirst(i);
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

			for (long i = 0; i < timesToLoop; i++)
			{
				testList.remove(rand.nextInt((int)setSize));
				testList.addLast(5);
			}

			midpointTime = System.nanoTime();
			
			// Run a loop to capture the cost of running the loop 
			for (long i = 0; i < timesToLoop; i++)
			{
				rand.nextInt((int)setSize);
				testList.addLast(5);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println(setSize + "\t" + averageTime);
		}
	}

	private static void testArrayListAdd()
	{
		System.out.println("Timing ArrayList Add");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			ArrayList<Integer> testList = new ArrayList<Integer>();
			for (int i = 0; i < setSize; i ++)
			{
				testList.add(i);
			}

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			long totalTime = 0;
			

			for (long i = 0; i < timesToLoop; i++)
			{
				startTime = System.nanoTime();
				testList.add(0, 5);
				long endTime = System.nanoTime();
				totalTime += (endTime-startTime);
				testList.remove((int)setSize-1);
			}

			midpointTime = System.nanoTime();
			
			// Run a loop to capture the cost of running the loop 
			for (long i = 0; i < timesToLoop; i++)
			{
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = (totalTime - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println(setSize + "\t" + averageTime);
		}
	}

	private static void testArrayListGet()
	{
		System.out.println("Timing ArrayList Get");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 9; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			ArrayList<Integer> testList = new ArrayList<Integer>();
			for (int i = 0; i < setSize; i ++)
			{
				testList.add(i);
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

			for (long i = 0; i < timesToLoop; i++)
			{
				testList.get(rand.nextInt((int)setSize));
			}

			midpointTime = System.nanoTime();
			
			// Run a loop to capture the cost of running the loop 
			for (long i = 0; i < timesToLoop; i++)
			{
				rand.nextInt((int)setSize);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println(setSize + "\t" + averageTime);
		}
	}

	private static void testArrayListRemove()
	{
		System.out.println("Timing ArrayList Remove");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			ArrayList<Integer> testList = new ArrayList<Integer>();
			for (int i = 0; i < setSize; i ++)
			{
				testList.add(i);
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

			for (long i = 0; i < timesToLoop; i++)
			{
				testList.remove(rand.nextInt((int)setSize));
				testList.add(5);
			}

			midpointTime = System.nanoTime();
			
			// Run a loop to capture the cost of running the loop 
			for (long i = 0; i < timesToLoop; i++)
			{
				rand.nextInt((int)setSize);
				testList.add(5);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println(setSize + "\t" + averageTime);
		}
	}
}