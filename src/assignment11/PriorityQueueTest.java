package assignment11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {

	PriorityQueue<Integer> comparator, comparatorEmpty;
	PriorityQueue<String> comparable, comparableEmpty;
	Random rand;

	@Before
	public void setUp() throws Exception {

		rand = new Random();

		comparableEmpty = new PriorityQueue<String>();
		comparable = new PriorityQueue<String>();

		for (int i = 0; i < 100; i++) {
			comparable.add(randomString(10));
		}

		comparatorEmpty = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer lhs, Integer rhs) {
				if (lhs > rhs)
					return 1;
				else if (lhs < rhs)
					return -1;
				return 0;
			}
		});

		comparator = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer lhs, Integer rhs) {
				if (lhs > rhs)
					return 1;
				else if (lhs < rhs)
					return -1;
				return 0;
			}
		});
		ArrayList<Integer> integers = new ArrayList<Integer>();
		for (int i = 1; i < 100; i++) {
			integers.add(i);
		}
		
		Collections.shuffle(integers);
		for (int i : integers) {
			comparator.add(i);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NoSuchElementException.class)
	public void testFindMinComparableEmpty() {
		comparableEmpty.findMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void testFindMinComparatorEmpty() {
		comparatorEmpty.findMin();
	}

	public void testFindMinComparable() {
		comparable.add("a");
		assertEquals("a", comparable.findMin());
	}

	public void testFindMinComparator() {
		Integer a = 1;
		assertEquals(a, comparator.findMin());

	}

	@Test
	public void testDeleteMinComparable() {
		comparable.add("a");
		comparable.add("aa");
		assertEquals("a", comparable.deleteMin());
		assertEquals("aa", comparable.findMin());

	}

	@Test
	public void testDeleteMinComparator() {
		Integer a = 1;
		assertEquals(a, comparator.deleteMin());
	}

	@Test(expected = NoSuchElementException.class)
	public void testDeleteMinComparableEmpty() {
		comparableEmpty.deleteMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void testDeleteMinComparatorEmpty() {
		comparatorEmpty.deleteMin();
	}

	@Test
	public void testAddComparable() {
		comparable.add("a");
		assertEquals("a", comparable.findMin());
	}

	@Test
	public void testAddComparator() {
		comparator.add(0);
		Integer a = 0;
		assertEquals(a, comparator.findMin());
	}

	@Test
	public void testAddComparableEmpty() {
		comparableEmpty.add("hello");
		assertEquals("hello", comparableEmpty.findMin());
	}

	@Test
	public void testAddComparatorEmpty() {
		comparatorEmpty.add(42);
		Integer a = 42;
		assertEquals(a, comparatorEmpty.findMin());
	}

	@Test
	public void testDuplicatesComparable() {
		comparable.add("a");
		comparable.add("a");
		assertEquals("a", comparable.deleteMin());
		assertEquals("a", comparable.findMin());
	}

	@Test
	public void testDuplicatesComparator() {
		Integer a = 0;
		comparator.add(0);
		comparator.add(0);
		assertEquals(a, comparator.deleteMin());
		assertEquals(a, comparator.findMin());
	}

	// Create a random string [a-z] of specified length
	private String randomString(int length) {

		String retval = "";
		for (int i = 0; i < length; i++) {
			// ASCII values a-z,A-Z are contiguous (52 characters)
			retval += (char) ('a' + (rand.nextInt(26)));
		}
		return retval;
	}

}
