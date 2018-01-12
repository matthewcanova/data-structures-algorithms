package assignment9;

import java.io.IOException;

public class TimingCode
{
	private static int timesToLoop = 10;

	public static void main(String[] args) throws IOException
	{
		TimingTest("bigMaze.txt", "bigMazeSolution.txt");
		TimingTest("bigMazeEmpty.txt", "bigMazeEmptySolution.txt");
	}	
	
	public static void TimingTest(String fileIn, String fileOut) throws IOException
	{
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000)
		{ // empty block
		}

		// Now, run the test.

		startTime = System.nanoTime();

		for (long i = 0; i < timesToLoop; i++)
			PathFinder.solveMaze(fileIn, fileOut);

		long midpointTime = System.nanoTime();

		// Run a loop to capture the cost of running the loop
		for (long i = 0; i < timesToLoop; i++)
		{
			// empty loop
		}

		long stopTime = System.nanoTime();

		// Compute the time, subtract the cost of running the loop
		// from the cost of running the loop and computing square roots.
		// Average it over the number of runs.

		double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
				/ timesToLoop;
		System.out.println(fileOut + "\t" + averageTime);
	}
}
