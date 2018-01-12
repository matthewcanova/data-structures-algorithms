package assignment5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the SortUtil class
 * 
 * @author Matthew Canova, Aaron Bellis
 *
 */
public class SortUtilTest
{
	// seed for Random
	private static final long SEED = 42l;
	private static final int COMPARISON_ARRAY_LENGTH = 1000;

	private Random rand;

	private ArrayList<Integer> bestCaseArray, averageCaseArray, worstCaseArray,
			averageCaseArrayDoubles, expectedResultArray,
			expectedResultArrayDoubles;

	private Comparator<Integer> intComparator;

	@Before
	public void setUp () throws Exception
	{
		// set up a random number generator
		rand = new Random(SEED);

		// initialize bestCaseArray with Integers 1 to COMPARISON_ARRAY_LENGTH
		// in ascending order
		bestCaseArray = new ArrayList<Integer>();
		expectedResultArray = new ArrayList<Integer>();
		for (int i = 1; i <= COMPARISON_ARRAY_LENGTH; i++)
		{
			bestCaseArray.add(i);
			expectedResultArray.add(i);
		}

		// initialize averageCaseArray with COMPARISON_ARRAY_LENGTH random
		// Integers based on our SEED
		averageCaseArray = new ArrayList<Integer>();
		for (int i = 1; i <= COMPARISON_ARRAY_LENGTH; i++)
		{
			averageCaseArray.add(i);
		}
		Collections.shuffle(averageCaseArray, rand);

		// initialize averageCaseArrayDoubles with math.random()
		averageCaseArrayDoubles = new ArrayList<Integer>();
		for (int i = 1; i <= COMPARISON_ARRAY_LENGTH; i++)
		{
			averageCaseArrayDoubles.add((int) (Math.random()
					* COMPARISON_ARRAY_LENGTH + 1));
		}

		expectedResultArrayDoubles = new ArrayList<Integer>();
		for (Integer n : averageCaseArrayDoubles)
		{
			expectedResultArrayDoubles.add(n);
		}
		Collections.sort(expectedResultArrayDoubles);

		// initialize worstCaseArray with Integers COMPARISON_ARRAY_LENGTH to 1
		// in descending order
		worstCaseArray = new ArrayList<Integer>();
		for (int i = COMPARISON_ARRAY_LENGTH; i >= 1; i--)
		{
			worstCaseArray.add(i);
		}

		// create comparator with natural ordering for Integer types
		intComparator = new Comparator<Integer>()
		{
			@Override
			public int compare (Integer int1, Integer int2)
			{
				return int1.compareTo(int2);
			}
		};

	}

	@After
	public void tearDown () throws Exception
	{
	}

	@Test
	public void testMergesortAverageCase ()
	{
		// sort array
		SortUtil.mergesort(averageCaseArray, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArray.size(); i++)
		{
			if (!expectedResultArray.get(i).equals(averageCaseArray.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ averageCaseArray.get(i) + " at " + "index " + i
						+ " expected " + expectedResultArray.get(i));
			}
		}
	}

	@Test
	public void testMergesortAverageCaseDoubles ()
	{
		// sort array
		SortUtil.mergesort(averageCaseArrayDoubles, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArrayDoubles.size(); i++)
		{
			if (!expectedResultArrayDoubles.get(i).equals(
					averageCaseArrayDoubles.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ averageCaseArrayDoubles.get(i) + " at " + "index "
						+ i + " expected " + expectedResultArrayDoubles.get(i));
			}

		}
	}

	@Test
	public void testMergesortBestCase ()
	{
		// sort array
		SortUtil.mergesort(bestCaseArray, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArray.size(); i++)
		{
			if (!expectedResultArray.get(i).equals(bestCaseArray.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ bestCaseArray.get(i) + " at " + "index " + i
						+ " expected " + expectedResultArray.get(i));
			}
		}
	}

	@Test
	public void testMergesortWorstCase ()
	{
		// sort array
		SortUtil.mergesort(worstCaseArray, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArray.size(); i++)
		{
			if (!expectedResultArray.get(i).equals(worstCaseArray.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ worstCaseArray.get(i) + " at " + "index " + i
						+ " expected " + expectedResultArray.get(i));
			}
		}
	}

	@Test
	public void testQuicksortBestCase ()
	{
		// sort array
		SortUtil.quicksort(bestCaseArray, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArray.size(); i++)
		{
			if (!expectedResultArray.get(i).equals(bestCaseArray.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ averageCaseArray.get(i) + " at " + "index " + i
						+ " expected " + bestCaseArray.get(i));
			}

		}
	}

	@Test
	public void testQuicksortAverageCase ()
	{
		// sort array
		SortUtil.quicksort(averageCaseArray, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArray.size(); i++)
		{
			if (!expectedResultArray.get(i).equals(averageCaseArray.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ averageCaseArray.get(i) + " at " + "index " + i
						+ " expected " + bestCaseArray.get(i));
			}

		}
	}

	@Test
	public void testQuicksortWorstCase ()
	{
		// sort array
		SortUtil.quicksort(worstCaseArray, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArray.size(); i++)
		{
			if (!expectedResultArray.get(i).equals(worstCaseArray.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ averageCaseArray.get(i) + " at " + "index " + i
						+ " expected " + bestCaseArray.get(i));
			}

		}
	}

	@Test
	public void testQuicksortAverageCaseDoubles ()
	{
		// sort array
		SortUtil.quicksort(averageCaseArrayDoubles, intComparator);

		// check to see if it sorted correctly
		for (int i = 0; i < expectedResultArrayDoubles.size(); i++)
		{
			if (!expectedResultArrayDoubles.get(i).equals(
					averageCaseArrayDoubles.get(i)))
			{
				fail("result for testGenerateBestCase() contains "
						+ averageCaseArrayDoubles.get(i) + " at " + "index "
						+ i + " expected " + expectedResultArrayDoubles.get(i));
			}

		}
	}

	@Test
	public void testGenerateBestCase ()
	{
		ArrayList<Integer> results = SortUtil
				.generateBestCase(COMPARISON_ARRAY_LENGTH);
		for (int i = 0; i < bestCaseArray.size(); i++)
		{
			if (!bestCaseArray.get(i).equals(results.get(i)))
			{
				fail("result for testGenerateBestCase() does not contain "
						+ bestCaseArray.get(i) + "at " + "index " + i);
			}

		}
	}

	@Test
	public void testGenerateAverageCase ()
	{
		ArrayList<Integer> results = SortUtil.generateAverageCase(
				COMPARISON_ARRAY_LENGTH, SEED);
		for (int i = 0; i < results.size(); i++)
		{
			if (!averageCaseArray.get(i).equals(results.get(i)))
			{
				fail("result for testGenerateAverageCase() does not contain "
						+ averageCaseArray.get(i) + " at " + "index " + i);
			}
		}
	}

	@Test
	public void testGenerateWorstCase ()
	{
		ArrayList<Integer> results = SortUtil
				.generateWorstCase(COMPARISON_ARRAY_LENGTH);
		for (int i = 0; i < worstCaseArray.size(); i++)
		{
			if (!worstCaseArray.get(i).equals(results.get(i)))
			{
				fail("result for testGenerateWorstCase() does not contain "
						+ worstCaseArray.get(i) + "at " + "index " + i);
			}
		}
	}

}
