package assignment7;

public class MyStackTiming
{
	
	private static int timesToLoop = 5;
	
	
	public static void main(String[] args)
	{
		testMyStackPush();
		testMyStackPop();
		testMyStackPeek();
	}
	
	private static void testMyStackPush()
	{
		System.out.println("Timing MyStack Push");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			MyStack<Integer> testStack = new MyStack<Integer>();
			for (int i = 0; i < setSize; i ++)
				testStack.push(i);

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
				testStack.push(5);

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
	
	private static void testMyStackPeek()
	{
		System.out.println("Timing MyStack Peek");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			MyStack<Integer> testStack = new MyStack<Integer>();
			for (int i = 0; i < setSize; i ++)
				testStack.push(i);

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
				testStack.peek();

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
	
	private static void testMyStackPop()
	{
		System.out.println("Timing MyStack Pop");
		System.out.println("Size" + "\t" + " Timing");
		System.out.println("---------------------------------");
		// this test will run on increasingly sized sets, running multiple times
		// and providing the average runtime for the set size
		for (double mySetSizeTracker = 10; mySetSizeTracker < 20; mySetSizeTracker++)
		{
			double setSize = Math.pow(2, mySetSizeTracker);
			MyStack<Integer> testStack = new MyStack<Integer>();
			for (int i = 0; i < setSize; i ++)
				testStack.push(i);

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
				testStack.pop();
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
}
