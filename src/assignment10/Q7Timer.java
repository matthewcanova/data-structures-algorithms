package assignment10;

import java.util.Random;

/**
 * Class for testing and comparing three different functors for hashing. The
 * three different functors represent a bad, mediocre, and good version of a
 * hashing function.
 * 
 * @author Boqian Yao and Matthew Canova
 */
public class Q7Timer {

	private static Random rand;

	public static void main(String[] args) {
		// set up functors
		GoodHashFunctor goodFunctor = new GoodHashFunctor();
		MediocreHashFunctor mediocreFunctor = new MediocreHashFunctor();
		BadHashFunctor badFunctor = new BadHashFunctor();

		// set up tables with different functors.
		QuadProbeHashTable goodTable = new QuadProbeHashTable(1000, goodFunctor);
		QuadProbeHashTable mediocreTable = new QuadProbeHashTable(1000,
				mediocreFunctor);
		QuadProbeHashTable badTable = new QuadProbeHashTable(1000, badFunctor);

		rand = new Random();

		// print out the result.
		System.out.println("~~~~~~~~~~Bad Hash Functor~~~~~~~~~");
		report(badTable);

		System.out.println("~~~~~~~~~~Mediocre Hash Functor~~~~~~~~~");
		report(mediocreTable);

		System.out.println("~~~~~~~~~~Good Hash Functor~~~~~~~~~");
		report(goodTable);
	}

	/**
	 * This method run the timing experiment and print the result.
	 * 
	 * @param QuadProbeHashTable
	 *            --- a Quadratic Probing Hash Table
	 * 
	 */
	private static void report(QuadProbeHashTable testTable) {

		int count = 0;
		int testTimes = 5;
		long startTime, midpointTime, stopTime;

		int baseSize = 2048;
		int[] size = new int[testTimes];

		for (int i = 0; i < size.length; i++) {
			size[i] = baseSize;
			baseSize *= 2;
		}

		int finalCollisionCount = 0;

		while (count < testTimes) {

			startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 100) { // empty block
			}
			startTime = System.nanoTime();
			// Now, run the test.

			long timesToLoop = 10;

			// add items based on the current set size value
			for (long i = 0; i < timesToLoop; i++) {
				for (int j = 0; j < size[count]; j++) {
					testTable.add(randomString(rand.nextInt(10)));

				}
				finalCollisionCount += testTable.getCollision();
				testTable.clear();
			}

			midpointTime = System.nanoTime();
			// Run an empty loop to capture the cost of running the loop.
			for (long i1 = 0; i1 < timesToLoop; i1++) {
				for (int j = 0; j < size[count]; j++) {

					//testTable.getFunctor().hash(randomString(rand.nextInt(10)));
				}
				testTable.getCollision();
			}
			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;

			System.out.println(size[count] + "\t" + averageTime + "\t"
					+ finalCollisionCount / 10);
			count++;

		}

	}

	// Create a random string [a-z] of specified length
	private static String randomString(int length) {

		String retval = "";
		for (int i = 0; i < length; i++) {
			// ASCII values a-z,A-Z are contiguous (52 characters)
			retval += (char) ('a' + (rand.nextInt(26)));
		}
		return retval;
	}
}
