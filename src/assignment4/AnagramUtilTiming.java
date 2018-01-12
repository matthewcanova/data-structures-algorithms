package assignment4;

import java.util.Random;
import java.util.TreeSet;

/**
 * Class for timing AnagramUtil methods.
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class AnagramUtilTiming
{

	public static void main (String[] args)
	{
		
		// run the test 20 times, starting with a set of 100,000 and then
		// increasing the
		// set size by 100,000 each run
//		for (int mySetSizeTracker = 1; mySetSizeTracker < 41; mySetSizeTracker++)
//		{
//
//			String word = AnagramTester.randomString(mySetSizeTracker * 100);
//
//			long startTime, midpointTime, stopTime;
//
//			// First, spin computing stuff until one second has gone by.
//			// This allows this thread to stabilize.
//
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000)
//			{ // empty block
//			}
//
//			// Now, run the test.
//
//			long timesToLoop = 10000;
//
//			startTime = System.nanoTime();
//
//			for (long i = 0; i < timesToLoop; i++)
//			{
//				AnagramUtil.areAnagrams(word, word);
//			}
//
//			midpointTime = System.nanoTime();
//
//			// Run an empty loop to capture the cost of running the loop.
//
//			for (long i = 0; i < timesToLoop; i++)
//			{ // empty block
//
//			}
//
//			stopTime = System.nanoTime();
//
//			// Compute the time, subtract the cost of running the loop
//			// from the cost of running the loop and computing square roots.
//			// Average it over the number of runs.
//
//			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
//					/ timesToLoop;
//			System.out
//					.println("AreAnagrams(String,String) ran " + timesToLoop
//							+ " times and took " + averageTime
//							+ " nanoseconds for word size of "
//							+ mySetSizeTracker * 100);
//		}

		
		
		// run the test 20 times, starting with a set of 100,000 and then
		// increasing the set size by 100,000 each run
		for (int mySetSizeTracker = 1; mySetSizeTracker < 21; mySetSizeTracker++)
		{
			// create a test array
			String[] anagramArray = new String[mySetSizeTracker * 100];
			for (int i = 0; i < anagramArray.length; i++)
			{
				// fill the array with random Strings of length 10
				anagramArray[i] = AnagramTester.randomString(8);
			}

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000)
			{ // empty block
			}

			// Now, run the test.

			long timesToLoop = 1000;

			startTime = System.nanoTime();

			for (long i = 0; i < timesToLoop; i++)
			{
				AnagramUtil.getLargestAnagramGroup(anagramArray);
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.

			for (long i = 0; i < timesToLoop; i++)
			{ // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;
			System.out
					.println("Running getLargestAnagramGroup "
							+ timesToLoop
							+ " times on a set of size "
							+ mySetSizeTracker
							* 10000
							+ " took, on average, "
							+ averageTime + " nanoseconds");
		}
	}
}