package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment4.AnagramUtil;

/**
 * Class that provides JUnit test cases for the AnagramUtil class.
 * 
 * @author Matthew Canova, Aaron Bellis
 *
 */

public class AnagramUtilTest
{
	// special strings
	private String emptyString, nullString, singleChar;

	// two words which are anagrams and have matching cases
	private String matchingCaseAnagram1, matchingCaseAnagram2,
			matchingCaseAnagramSortExpectedResult;

	// two words which are anagrams and have different cases
	private String differentCaseAnagram1, differentCaseAnagram2;

	// words for sorting
	private String lowerCaseRepeatingChar, expectedLowerCaseRepeatingChar;

	// Anagram arrays
	private String[] anagramsEmptyArray, anagramsArray,
			anagramsEqualGroupsArray, noAnagramsArray, anagramsResultsArray,
			anagramsResultsArray2;

	@Before
	public void setUp () throws Exception
	{
		// special strings
		emptyString = "";
		nullString = null;
		singleChar = "c";

		// two words which are anagrams and have matching cases
		matchingCaseAnagram1 = "alert";
		matchingCaseAnagram2 = "later";
		matchingCaseAnagramSortExpectedResult = "aelrt";

		// two words which are anagrams and have different cases
		differentCaseAnagram1 = "Being";
		differentCaseAnagram2 = "begin";

		// a word and it's expected sorted result
		lowerCaseRepeatingChar = "anagram";
		expectedLowerCaseRepeatingChar = "aaagmnr";

		// anagram group arrays
		anagramsEmptyArray = new String[0];

		String[] noAnagramsInitializer = {"hey", "you", "guys", "what", "is",
				"up", "cope", "copee", "freedom", "turkey"};
		noAnagramsArray = noAnagramsInitializer;

		String[] anagramsInitializer = {"carets", "Caller", "eat", "cellar",
				"recall", "Caters", "Ate", "caster", "aspired", "allergy",
				"despair", "asp", "pas", "least", "sap", "spa", "diapers",
				"praised", "crates", "Reacts", "bats", "tea", "Stab", "stale",
				"tabs", "recast", "darters", "Gallery", "retards", "starred",
				"code", "Coed", "deco", "traders", "traces"};
		anagramsArray = anagramsInitializer;

		String[] anagramsGroupResults = {"carets", "Caters", "caster",
				"crates", "Reacts", "recast", "traces"};
		anagramsResultsArray = anagramsGroupResults;

		// Contains list with two groups of anagrams of equal length
		// "carets", "Caters", "caster","crates", "Reacts", "recast", "traces"
		// and
		// "acres", "cares", "carse", "escar", "races", "scare", "serac"
		String[] anagramsEqualSizeInitializer = {"carets", "Caller", "eat",
				"cellar", "recall", "Caters", "Ate", "caster", "aspired",
				"allergy", "despair", "asp", "pas", "least", "sap", "spa",
				"diapers", "praised", "crates", "Reacts", "bats", "tea",
				"Stab", "stale", "tabs", "recast", "darters", "Gallery",
				"retards", "starred", "code", "Coed", "deco", "traders",
				"traces", "acres", "cares", "carse", "escar", "races", "scare",
				"serac"};
		anagramsEqualGroupsArray = anagramsEqualSizeInitializer;

		String[] anagramsGroupResults2 = {"acres", "cares", "carse", "escar",
				"races", "scare", "serac"};
		anagramsResultsArray2 = anagramsGroupResults2;

	}

	@After
	public void tearDown () throws Exception
	{

	}

	// tests for the sort() method
	@Test
	public void testSortPositive ()
	{
		String actualResult = AnagramUtil.sort(lowerCaseRepeatingChar);
		assertEquals(expectedLowerCaseRepeatingChar, actualResult);
	}

	@Test
	public void testSortRepeatCharacters ()
	{
		String actualResult = AnagramUtil.sort(lowerCaseRepeatingChar);
		assertEquals(expectedLowerCaseRepeatingChar, actualResult);
	}

	@Test
	public void testSortNoRepeatCharacters ()
	{
		String actualResult = AnagramUtil.sort(matchingCaseAnagram1);
		assertEquals(matchingCaseAnagramSortExpectedResult, actualResult);
	}

	@Test
	public void testSortEmptyString ()
	{
		String actualResult = AnagramUtil.sort(emptyString);
		assertEquals("", actualResult);

	}

	@Test
	public void testSortNullString ()
	{
		String actualResult = AnagramUtil.sort(nullString);
		assertEquals(null, actualResult);
	}

	@Test
	public void testSortSingleCharacter ()
	{
		String actualResult = AnagramUtil.sort(singleChar);
		assertEquals(singleChar, actualResult);
	}

	// tests for the insertionSort() method
	@Test
	public void testInsertionSortSortedArray ()
	{
		// two arrays containing the characters 'a', 'b', 'c', 'd', 'e'
		Character[] chars1 = new Character[5];
		Character[] chars2 = new Character[5];
		for (int i = 0; i < chars1.length; i++)
		{
			char c = 'a';
			c += i;
			chars1[i] = c;
			chars2[i] = c;
		}

		// create a comparator for the method test
		Comparator<Character> compare;
		compare = new Comparator<Character>()
		{
			@Override
			public int compare (Character char1, Character char2)
			{
				return char1.compareTo(char2);
			}
		};

		// run the method
		AnagramUtil.insertionSort(chars1, compare);

		// get strings of the two char arrays
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < chars1.length; i++)
		{
			s1 += chars1[i].toString() + " ";
			s2 += chars2[i].toString() + " ";
		}

		assertEquals(s1, s2);
	}

	@Test
	public void testInsertionSortReverseSortedArray ()
	{
		// two arrays to test the method
		Character[] chars1 = new Character[5];
		Character[] chars2 = new Character[5];
		for (int i = 0; i < chars1.length; i++)
		{
			// initialize array with the characters 'e', 'd', 'c', 'b', 'a'
			char c1 = 'e';
			c1 -= i;
			chars1[i] = c1;

			// initialize array with the characters 'a', 'b', 'c', 'd', 'e'
			char c2 = 'a';
			c2 += i;
			chars2[i] = c2;
		}

		// create a comparator with the natural ordering for Characters
		Comparator<Character> compare;
		compare = new Comparator<Character>()
		{
			@Override
			public int compare (Character char1, Character char2)
			{
				return char1.compareTo(char2);
			}
		};

		// run the method
		AnagramUtil.insertionSort(chars1, compare);

		// get strings of the two char arrays
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < chars1.length; i++)
		{
			s1 += chars1[i].toString() + " ";
			s2 += chars2[i].toString() + " ";
		}

		assertEquals(s1, s2);
	}

	@Test
	public void testInsertionSortRandomSortedArray ()
	{
		// two arrays containing the characters 'a', 'b', 'c', 'd', 'e'
		Character[] chars1 = {'a', 'e', 'b', 'd', 'c'};
		Character[] chars2 = {'a', 'b', 'c', 'd', 'e'};

		// create a comparator for the method test
		Comparator<Character> compare;
		compare = new Comparator<Character>()
		{
			@Override
			public int compare (Character char1, Character char2)
			{
				return char1.compareTo(char2);
			}
		};

		// run the method
		AnagramUtil.insertionSort(chars1, compare);

		// get strings of the two char arrays
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < chars1.length; i++)
		{
			s1 += chars1[i].toString() + " ";
			s2 += chars2[i].toString() + " ";
		}

		assertEquals(s1, s2);
	}

	@Test
	public void testInsertionSortWithDuplicateCharacters ()
	{
		// an unsorted array
		Character[] chars1 = {'a', 'b', 'c', 'd', 'e', 'a', 'b', 'c', 'd', 'e'};
		// a sorted array
		Character[] chars2 = {'a', 'a', 'b', 'b', 'c', 'c', 'd', 'd', 'e', 'e'};

		// create a comparator for the method test
		Comparator<Character> compare;
		compare = new Comparator<Character>()
		{
			@Override
			public int compare (Character char1, Character char2)
			{
				return char1.compareTo(char2);
			}
		};

		// run the method
		AnagramUtil.insertionSort(chars1, compare);

		// get strings of the two char arrays
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < chars1.length; i++)
		{
			s1 += chars1[i].toString() + " ";
			s2 += chars2[i].toString() + " ";
		}

		assertEquals(s1, s2);
	}

	@Test
	public void testInsertionSortWithIntegers ()
	{
		// two arrays containing the Integers
		Integer[] ints1 = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
		Integer[] ints2 = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};

		// create a comparator for the method test
		Comparator<Integer> compare;
		compare = new Comparator<Integer>()
		{
			@Override
			public int compare (Integer int1, Integer int2)
			{
				return int1.compareTo(int2);
			}
		};

		// run the method
		AnagramUtil.insertionSort(ints1, compare);

		// get strings of the two Integer arrays
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < ints1.length; i++)
		{
			s1 += ints1[i].toString() + " ";
			s2 += ints2[i].toString() + " ";
		}

		assertEquals(s1, s2);
	}

	// tests for the areAnagrams() method
	@Test
	public void testAreAnagramsSameCaseTrue ()
	{
		// are anagrams and should return true
		assertTrue(AnagramUtil.areAnagrams(matchingCaseAnagram1,
				matchingCaseAnagram2));
	}

	@Test
	public void testAreAnagramsSameCaseFalse ()
	{
		// both strings are lower case but are not anagrams of each other
		assertFalse(AnagramUtil.areAnagrams(matchingCaseAnagram1,
				differentCaseAnagram2));
	}

	@Test
	public void testAreAnagramsDifferentCaseTrue ()
	{
		// are anagrams and should return true
		assertTrue(AnagramUtil.areAnagrams(differentCaseAnagram1,
				differentCaseAnagram2));
	}

	@Test
	public void testAreAnagramsDifferentCaseFalse ()
	{
		// both strings have different cases but are not anagrams of each other
		assertFalse(AnagramUtil.areAnagrams(differentCaseAnagram1,
				matchingCaseAnagram2));
	}

	// tests for the getLargestAnagramGroup() method with a String array input
	@Test
	public void testGetLargestAnagramGroupStringArrayPositiveResult ()
	{
		// get the return array for the getLargestAnagramGroup method
		String[] outputValues = AnagramUtil
				.getLargestAnagramGroup(anagramsArray);

		// check the return array for the expected results
		for (String output : outputValues)
		{
			boolean isPresent = false;
			for (String expected : anagramsResultsArray)
			{
				if (output.equals(expected))
					isPresent = true;
			}
			assertTrue(isPresent);
		}
	}

	@Test
	public void testGetLargestAnagramGroupStringArrayNoAnagrams ()
	{
		// run the method
		String[] outputValues = AnagramUtil
				.getLargestAnagramGroup(noAnagramsArray);

		// test that you get the expected empty array
		assertTrue(outputValues.length == 0);
	}

	@Test
	public void testGetLargestAnagramGroupStringArrayTwoEqualGroups ()
	{
		String[] results = AnagramUtil
				.getLargestAnagramGroup(anagramsEqualGroupsArray);

		
		boolean hasAllElements = true;
		for (int i = 0; i < anagramsResultsArray2.length; i++)
		{
			boolean hasElement = false;
			for (int j = 0; j < results.length; j++)
			{
				if (anagramsResultsArray2[i].equals(results[j]))
				{
					hasElement = true;
				}
			}
			if (hasElement = false)
			{
				hasAllElements = false;
			}
		}
		assertTrue(hasAllElements);

	}

	@Test
	public void testGetLargestAnagramGroupStringArrayEmptyArray ()
	{
		// run the method
		String[] outputValues = AnagramUtil
				.getLargestAnagramGroup(anagramsEmptyArray);

		// test that you get the expected empty array
		assertTrue(outputValues.length == 0);
	}

	// tests for the getLargestAnagramGroup() method with a filename input
	@Test
	public void testGetLargestAnagramGroupFilePositiveResult ()
	{
		// get the return array for the getLargestAnagramGroup method
		String[] outputValues = AnagramUtil
				.getLargestAnagramGroup("sample_word_list.txt");

		// check the return array for the expected results
		for (String output : outputValues)
		{
			boolean isPresent = false;
			for (String expected : anagramsResultsArray)
			{
				if (output.equals(expected))
					isPresent = true;
			}
			assertTrue(isPresent);
		}
	}

	@Test
	public void testGetLargestAnagramGroupFileNoAnagrams ()
	{
		// run the method
		String[] outputValues = AnagramUtil
				.getLargestAnagramGroup("noAnagrams.txt");

		// test that you get the expected empty array
		assertTrue(outputValues.length == 0);
	}

	@Test
	public void testGetLargestAnagramGroupFileTwoEqualGroups ()
	{
		String[] results = AnagramUtil
				.getLargestAnagramGroup("equalAnagramGroups.txt");
		boolean hasAllElements = true;
		for (int i = 0; i < anagramsResultsArray2.length; i++)
		{
			boolean hasElement = false;
			for (int j = 0; j < results.length; j++)
			{
				if (anagramsResultsArray2[i].equals(results[j]))
				{
					hasElement = true;
				}
			}
			if (hasElement = false)
			{
				hasAllElements = false;
			}
		}
		assertTrue(hasAllElements);

	}

	@Test
	public void testGetLargestAnagramGroupFileEmptyFile ()
	{
		// run the method
		String[] outputValues = AnagramUtil
				.getLargestAnagramGroup("emptyFile.txt");

		// test that you get the expected empty array
		assertTrue(outputValues.length == 0);
	}

	@Test
	public void testGetLargestAnagramGroupNoFile ()
	{
		// run the method
		String[] outputValues = AnagramUtil
				.getLargestAnagramGroup("thisfiledoesnotexist.txt");

		// test that you get the expected empty array
		assertTrue(outputValues.length == 0);
	}
}
