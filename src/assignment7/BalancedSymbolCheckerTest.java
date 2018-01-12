package assignment7;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Tests for testing the BalancedSymbolChecker class
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 */
public class BalancedSymbolCheckerTest
{
	@Test
	public void testCheckFile1()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
			String output = BalancedSymbolChecker.checkFile("Class1.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile2()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
			String output = BalancedSymbolChecker.checkFile("Class2.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile3()
	{
		try 
		{
			String correct = "No errors found. All symbols match.";
			String output = BalancedSymbolChecker.checkFile("Class3.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile4()
	{
		try 
		{
			String correct = "ERROR: File ended before closing comment.";
			String output = BalancedSymbolChecker.checkFile("Class4.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile5()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
			String output = BalancedSymbolChecker.checkFile("Class5.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile6()
	{
		try 
		{
			String correct = "No errors found. All symbols match.";
			String output = BalancedSymbolChecker.checkFile("Class6.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile7()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
			String output = BalancedSymbolChecker.checkFile("Class7.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile8()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
			String output = BalancedSymbolChecker.checkFile("Class8.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile9()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
			String output = BalancedSymbolChecker.checkFile("Class9.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile10()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
			String output = BalancedSymbolChecker.checkFile("Class10.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile11()
	{
		try 
		{
			String correct = "ERROR: Unmatched symbol at the end of file. Expected }.";
			String output = BalancedSymbolChecker.checkFile("Class11.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile12()
	{
		try 
		{
			String correct = "No errors found. All symbols match.";
			String output = BalancedSymbolChecker.checkFile("Class12.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile13()
	{
		try 
		{
			String correct = "No errors found. All symbols match.";
			String output = BalancedSymbolChecker.checkFile("Class13.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
	
	@Test
	public void testCheckFile14()
	{
		try 
		{
			String correct = "No errors found. All symbols match.";
			String output = BalancedSymbolChecker.checkFile("Class14.java");
			assertEquals(correct, output);
		}
		catch (FileNotFoundException e)
		{
			fail("Unable to open file");
		}
	}
}
