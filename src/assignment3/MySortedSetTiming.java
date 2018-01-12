package assignment3;

import java.util.Random;
import java.util.TreeSet;

/**
 * Class for timing  MySortedSet methods.
 * 
 * @author Aaron Bellis, Matthew Canova
 */
public class MySortedSetTiming {

	public static void main(String[] args) {

		MySortedSet<Integer> mySet = new MySortedSet<Integer>();
		Random randn = new Random();
		
		// run the test 20 times, starting with a set of 100,000 and then increasing the 
		// set size by 100,000 each run
//		for (int mySetSizeTracker = 1; mySetSizeTracker < 21; mySetSizeTracker++)
//		{
//			for (int i = 0; i < mySetSizeTracker*100000; i++)
//			{
//				// fill the set
//				mySet.add(i);
//			}
//			
//			long startTime, midpointTime, stopTime;
//
//			// First, spin computing stuff until one second has gone by.
//			// This allows this thread to stabilize.
//
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000) { // empty block
//			}
//
//			// Now, run the test.
//
//			long timesToLoop = 100000000;
//
//			startTime = System.nanoTime();
//
//			for (long i = 0; i < timesToLoop; i++)
//			{
//				mySet.contains(randn.nextInt(mySetSizeTracker*100000));
//			}
//
//			midpointTime = System.nanoTime();
//
//			// Run an empty loop to capture the cost of running the loop.
//
//			for (long i = 0; i < timesToLoop; i++) { // empty block
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
//			System.out.println("Running contains() 100,000,000 times on a set of size " + mySetSizeTracker*100000 + " took " 
//					+ averageTime + " nanoseconds");
//			mySet.clear();
//		}
			
		mySet.clear();
		
		// run the test 20 times, starting with a set of 100,000 and then increasing the 
		// set size by 100,000 each run
		for (int mySetSizeTracker = 1; mySetSizeTracker < 21; mySetSizeTracker++)
			{
				for (int i = 0; i < mySetSizeTracker*100000; i++)
				{
					// fill the set
					if(i != 50000)
					mySet.add(i);
				}
				
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.

				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1000000000) { // empty block
				}

				// Now, run the test.

				long timesToLoop = 1000;
				double totalTime = 0;

				for (long i = 0; i < timesToLoop; i++)
				{
					startTime = System.nanoTime();
					mySet.add(50000);
					stopTime = System.nanoTime();
					mySet.remove(50000);
					totalTime += (stopTime - startTime);			
				}

				double averageTime = totalTime / timesToLoop;
				System.out.println("Running add() 100,000,000 times on a set of size " + mySetSizeTracker*100000 + " took, on average " 
						+ averageTime + " nanoseconds per call");
				mySet.clear();
			
		}
	}
}
