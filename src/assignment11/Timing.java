package assignment11;

import java.util.Random;

import assignment10.QuadProbeHashTable;

public class Timing {

	private static Random rand;

	public static void main(String[] args) {
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();

		rand = new Random();

		// print out the result.
		System.out.println("~~~~~~~~~~Timing for add~~~~~~~~~");

		report(p);

		System.out.println("~~~~~~~~~~Timing for finMin~~~~~~~~~");

		reportFindMin(p,true);
		
		 System.out.println("~~~~~~~~~~Timing for deleteMin~~~~~~~~~");
		
		 reportFindMin(p,false);

	}

	/**
	 * This method run the timing experiment and print the result.
	 * 
	 * @param QuadProbeHashTable
	 *            --- a Quadratic Probing Hash Table
	 * 
	 */
	private static void report(PriorityQueue<Integer> p) {

		int count = 0;
		int testTimes = 9;
		long startTime, midpointTime, stopTime;

		int baseSize = 32768;
		int[] size = new int[testTimes];

		for (int i = 0; i < size.length; i++) {
			size[i] = baseSize;
			baseSize *= 2;
		}

		while (count < testTimes) {

			startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 100) { // empty block
			}
			startTime = System.nanoTime();
			// Now, run the test.

			long timesToLoop = 50;

			// add items based on the current set size value
			for (long i = 0; i < timesToLoop; i++) {
				for (int j = 0; j < size[count]; j++) {

					p.add(rand.nextInt(1000000));

				}
				p.clear();
			}

			midpointTime = System.nanoTime();
			// Run an empty loop to capture the cost of running the loop.
			for (long i1 = 0; i1 < timesToLoop; i1++) {
				for (int j = 0; j < size[count]; j++) {
					rand.nextInt(1000000);
				}

			}
			stopTime = System.nanoTime();

			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop)
					/ size[count];

			System.out.println(size[count] + "	" + averageTime);
			count++;

		}

	}

	private static void reportFindMin(PriorityQueue<Integer> p, boolean flag) {

		int count = 0;
		int testTimes = 14;
		long startTime, midpointTime, stopTime;

		int baseSize = 4096;
		int[] size = new int[testTimes];

		for (int i = 0; i < size.length; i++) {
			size[i] = baseSize;
			baseSize *= 2;
		}

		while (count < testTimes) {

			startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 10000) { // empty block
			}

			for (long i = 0; i < size[count]; i++) {
				p.add(rand.nextInt(1000000));
			}

			startTime = System.nanoTime();
			// Now, run the test.

			long timesToLoop = 100;

			// add items based on the current set size value
			for (long i = 0; i < timesToLoop; i++) {
				if (flag) {
					p.findMin();
				} else {
					p.deleteMin();
				}
			}

			midpointTime = System.nanoTime();
			// Run an empty loop to capture the cost of running the loop.
			for (long i1 = 0; i1 < timesToLoop; i1++) {
				if (flag) {
					
				} else {
					
				}
			}
			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;

			System.out.println(size[count] + "	" + averageTime);
			count++;
			p.clear();

		}

	}
}
