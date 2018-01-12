package assignment8;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the SpellChecker Class
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 *
 */
public class SpellCheckerTest
{
	SpellChecker checker;
	String[] expectedHelloWorld, expectedGoodLuck;

	@Before
	public void setUp() throws Exception
	{
		checker = new SpellChecker(new File("dictionary.txt"));
		expectedGoodLuck = new String[1];
		expectedGoodLuck[0] = "bst";
		expectedHelloWorld = new String[0];
	}

	@Test
	public void testSpellCheck()
	{
		assertArrayEquals(expectedHelloWorld, checker.spellCheck(new File("hello_world.txt")).toArray());
		assertArrayEquals(expectedGoodLuck, checker.spellCheck(new File("good_luck.txt")).toArray());
	}
}
