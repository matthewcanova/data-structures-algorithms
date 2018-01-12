package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the ChaingingHashTable class.
 * 
 * @author Boqian Yao
 * @author Matthew Canova
 * 
 */
public class ChainingHashTableTest {

	static final int CAPACITY = 10;
	static final int NUMBEROFITEMS = 200;
	ChainingHashTable emptyBadFunctor, emptyMediocreFunctor, emptyGoodFunctor,
			badFunctor, mediocreFunctor, goodFunctor;
	ArrayList<String> list, wrongList, emptyList;
	Random rand;

	@Before
	public void setUp() throws Exception {
		rand = new Random();

		emptyBadFunctor = new ChainingHashTable(CAPACITY, new BadHashFunctor());
		emptyMediocreFunctor = new ChainingHashTable(CAPACITY,
				new MediocreHashFunctor());
		emptyGoodFunctor = new ChainingHashTable(CAPACITY,
				new GoodHashFunctor());

		badFunctor = new ChainingHashTable(CAPACITY, new BadHashFunctor());
		mediocreFunctor = new ChainingHashTable(CAPACITY,
				new MediocreHashFunctor());
		goodFunctor = new ChainingHashTable(CAPACITY, new GoodHashFunctor());

		// fill in the hash tables with different strings
		// Use different lengths to make sure we do not get duplicate strings.
		for (int i = 0; i < NUMBEROFITEMS; i++) {
			badFunctor.add(randomString(i));
			mediocreFunctor.add(randomString(i));
			goodFunctor.add(randomString(i));

		}

		list = new ArrayList<String>();
		for (int i = 0; i < NUMBEROFITEMS; i++)
			list.add(randomString(i));

		wrongList = new ArrayList<String>();
		for (int i = 0; i < NUMBEROFITEMS; i++)
			wrongList.add(randomString(i));
  
		emptyList = new ArrayList<String>();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		assertTrue(emptyBadFunctor.add("test"));
		assertTrue(emptyMediocreFunctor.add("test"));
		assertTrue(emptyGoodFunctor.add("test"));

		assertTrue(emptyBadFunctor.contains("test"));
		assertTrue(emptyMediocreFunctor.contains("test"));
		assertTrue(emptyGoodFunctor.contains("test"));
	}

	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		assertTrue(emptyBadFunctor.add(null));
		assertTrue(emptyMediocreFunctor.add(null));
		assertTrue(emptyGoodFunctor.add(null));
	}

	@Test
	public void testAddEmptyString() {
		assertTrue(emptyBadFunctor.add(""));
		assertTrue(emptyMediocreFunctor.add(""));
		assertTrue(emptyGoodFunctor.add(""));
	}

	@Test
	public void testAddAll() {
		assertTrue(emptyBadFunctor.addAll(list));
		assertTrue(emptyMediocreFunctor.addAll(list));
		assertTrue(emptyGoodFunctor.addAll(list));
		
	}

	@Test
	public void testAddAllEmpty() {
		assertFalse(emptyBadFunctor.addAll(emptyList));
		assertFalse(emptyMediocreFunctor.addAll(emptyList));
		assertFalse(emptyGoodFunctor.addAll(emptyList));
	}

	@Test(expected = NullPointerException.class)
	public void testAddAllNull() {
		assertTrue(emptyBadFunctor.addAll(null));
		assertTrue(emptyMediocreFunctor.addAll(null));
		assertTrue(emptyGoodFunctor.addAll(null));
	}

	@Test
	public void testClearNotEmpty() {
		badFunctor.clear();
		mediocreFunctor.clear();
		goodFunctor.clear();

		assertTrue(badFunctor.isEmpty());
		assertTrue(mediocreFunctor.isEmpty());
		assertTrue(goodFunctor.isEmpty());
	}

	@Test
	public void testClearEmpty() {
		emptyBadFunctor.clear();
		emptyMediocreFunctor.clear();
		emptyGoodFunctor.clear();

		assertTrue(emptyBadFunctor.isEmpty());
		assertTrue(emptyMediocreFunctor.isEmpty());
		assertTrue(emptyGoodFunctor.isEmpty());
	}

	@Test
	public void testContainsTrue() {
		for (int i = 1; i < 6; i++) {
			emptyBadFunctor.add(randomString(i));
			emptyMediocreFunctor.add(randomString(i));
			emptyGoodFunctor.add(randomString(i));
		}
		emptyBadFunctor.add("tested");
		emptyMediocreFunctor.add("tested");
		emptyGoodFunctor.add("tested");

		assertTrue(emptyBadFunctor.contains("tested"));
		assertTrue(emptyMediocreFunctor.contains("tested"));
		assertTrue(emptyGoodFunctor.contains("tested"));
	}

	@Test
	public void testContainsFalse() {
		for (int i = 1; i < 6; i++) {
			emptyBadFunctor.add(randomString(i));
			emptyMediocreFunctor.add(randomString(i));
			emptyGoodFunctor.add(randomString(i));
		}
		emptyBadFunctor.add("tested");
		emptyMediocreFunctor.add("tested");
		emptyGoodFunctor.add("tested");

		assertFalse(emptyBadFunctor.contains("testedx"));
		assertFalse(emptyMediocreFunctor.contains("testedx"));
		assertFalse(emptyGoodFunctor.contains("testedx"));
	}

	@Test
	public void testContainsAllTrue() {
		emptyBadFunctor.addAll(list);
		emptyMediocreFunctor.addAll(list);
		emptyGoodFunctor.addAll(list);

		assertTrue(emptyBadFunctor.containsAll(list));
		assertTrue(emptyMediocreFunctor.containsAll(list));
		assertTrue(emptyGoodFunctor.containsAll(list));
	}

	@Test
	public void testContainsAllFalse() {
		emptyBadFunctor.addAll(list);
		emptyMediocreFunctor.addAll(list);
		emptyGoodFunctor.addAll(list);

		assertFalse(emptyBadFunctor.containsAll(wrongList));
		assertFalse(emptyMediocreFunctor.containsAll(wrongList));
		assertFalse(emptyGoodFunctor.containsAll(wrongList));
	}

	@Test
	public void testContainsAllEmpty() {
		assertFalse(badFunctor.containsAll(emptyList));
		assertFalse(mediocreFunctor.containsAll(emptyList));
		assertFalse(goodFunctor.containsAll(emptyList));
	}

	@Test
	public void testIsEmptyTrue() {
		assertTrue(emptyBadFunctor.isEmpty());
		assertTrue(emptyMediocreFunctor.isEmpty());
		assertTrue(emptyGoodFunctor.isEmpty());
	}

	@Test
	public void testIsEmptyFalse() {
		assertFalse(badFunctor.isEmpty());
		assertFalse(mediocreFunctor.isEmpty());
		assertFalse(goodFunctor.isEmpty());
	}

	@Test
	public void testSizeNotEmpty() {
		assertEquals(NUMBEROFITEMS, badFunctor.size());
		assertEquals(NUMBEROFITEMS, mediocreFunctor.size());
		assertEquals(NUMBEROFITEMS, goodFunctor.size());
	}

	@Test
	public void testSizeEmpty() {
		assertEquals(emptyBadFunctor.size(), 0);
		assertEquals(emptyMediocreFunctor.size(), 0);
		assertEquals(emptyGoodFunctor.size(), 0);
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
