package assignment10;

import java.util.Random;

/**
 * Class for testing and comparing a Quadratic Probing Hash Table and a Chaining
 * Hash Table.
 * 
 * @author Boqian Yao and Matthew Canova
 */
public class Q8Timer {

	private static Random rand;

	public static void main(String[] args) {
		//set up tables 
		GoodHashFunctor goodFunctor = new GoodHashFunctor();

		QuadProbeHashTable goodQTable = new QuadProbeHashTable(1000,
				goodFunctor);
		ChainingHashTable goodCTable = new ChainingHashTable(1000, goodFunctor);

		rand = new Random();
        
		//print out the result.
		System.out.println("~~~~~~~~~~QuadProbeHashTable ~~~~~~~~~");
		report(goodQTable, goodCTable, true);

		System.out.println("~~~~~~~~~~ChainingHashTable~~~~~~~~~");
		report(goodQTable, goodCTable, false);

	}

	/**
	 * This method run the timing experiment and print the result.
	 * 
	 * @param QuadProbeHashTable
	 *            --- a  Quadratic Probing Hash Table
	 * @param ChainingHashTable
	 *            ---  a Chaining Hash Table.
	 * @param boolean flag
	 *   
	 */
	private static void report(QuadProbeHashTable goodQTable,
			ChainingHashTable goodCTable, boolean flag) {

		int count = 0;
		int testTimes = 5;
		long startTime, midpointTime, stopTime;

		int baseSize = 4096;
		int[] size = new int[testTimes];
        
		// set the various set sizes for our test 
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
			// test against the Quad table if flag is true
			// test against the Chaining table if flag is false
			for (long i = 0; i < timesToLoop; i++) {
				for (int j = 0; j < size[count]; j++) {
					if (flag) {
						goodQTable.add(randomString(rand.nextInt(10)));
					} else {
						goodCTable.add(randomString(rand.nextInt(10)));
					}
				}
				if (flag) {
					finalCollisionCount += goodQTable.getCollision();
					goodQTable.clear();
				} else {
					finalCollisionCount += goodCTable.getCollision();
					goodCTable.clear();
				}

			}

			midpointTime = System.nanoTime();
			// Run an empty loop to capture the cost of running the loop.
			for (long i1 = 0; i1 < timesToLoop; i1++) {
				for (int j = 0; j < size[count]; j++) {
					if (flag) {

					} else {

					}
				}
				if (flag) {

				} else {

				}
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
