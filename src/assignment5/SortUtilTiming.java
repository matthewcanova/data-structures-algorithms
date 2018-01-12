package assignment5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

import org.junit.internal.runners.TestMethod;

/**
 * Timing tests for the SortUtil class. 
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class SortUtilTiming
{

	public static final long SEED = 42l;

	public static void main (String[] args)
	{

		// create comparator with natural ordering for Integer types
		Comparator<Integer> intComparator = new Comparator<Integer>()
		{
			@Override
			public int compare (Integer int1, Integer int2)
			{
				return int1.compareTo(int2);
			}
		};
		
		// test mergesort
		System.out.println("Test mergesort");
		System.out.println();
		testMergeSortTiming (intComparator);
		
		// test quicksort
		System.out.println("Test quicksort");
		System.out.println();
		testQuickSortTiming (intComparator);
		
	}

	private static void testMergeSortTiming (Comparator<Integer> compare)
	{
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			ArrayList<Integer> testSet = SortUtil.generateAverageCase(
					(int) setSize, SEED);

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.
			
			startTime = System.nanoTime();

			long timesToLoop = 100;

			for (long i = 0; i < timesToLoop; i++)
			{
				ArrayList<Integer> set = new ArrayList<Integer>();
				set.addAll(testSet);
				SortUtil.mergesort(set, compare);

			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the loop and
			// copying the ArrayList
			for (long i = 0; i < timesToLoop; i++)
			{
				ArrayList<Integer> set = new ArrayList<Integer>();
				set.addAll(testSet);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println("Running mergesort() on a set of size "
					+ setSize + " took, on average " + averageTime
					+ " nanoseconds (Average of " + timesToLoop + " runs)");
		}
	}

	private static void testQuickSortTiming (Comparator<Integer> compare)
	{
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			ArrayList<Integer> testSet = SortUtil.generateAverageCase(
					(int) setSize, SEED);

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			long timesToLoop = 100;

			startTime = System.nanoTime();
			
			for (long i = 0; i < timesToLoop; i++)
			{
				ArrayList<Integer> set = new ArrayList<Integer>();
				set.addAll(testSet);
				SortUtil.quicksort(set, compare);

			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the loop 
			// and copying the ArrayList

			for (long i = 0; i < timesToLoop; i++)
			{
				ArrayList<Integer> set = new ArrayList<Integer>();
				set.addAll(testSet);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out.println("Running quicksort() on a set of size "
					+ setSize + " took, on average " + averageTime
					+ " nanoseconds (Average of " + timesToLoop + " runs)");
		}
	}
}