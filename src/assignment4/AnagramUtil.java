package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class containing static methods for analyzing words and determining if they
 * are anagrams of each other.
 * 
 * Two words are anagrams if they contain the same letters in the same
 * frequency. For example, alert and later are anagrams. Words with the same
 * letters in the same frequency, but different cases are still anagrams.
 * 
 * @author Aaron Bellis , Matthew Canova
 *
 */
public class AnagramUtil
{
	// implementation methods

	/**
	 * Sorts a String s using insertion sort and returns the resulting String.
	 * 
	 * @param s
	 *            - a string to be sorted
	 * @return - a string cast to lower case sorted by ascii value
	 */
	public static String sort (String s)
	{
		if (s == null)
		{
			return null;
		}

		// get all lower version of string
		String sortString = s.toLowerCase();

		// put chars from string into a Character array
		Character[] chars = new Character[sortString.length()];
		for (int i = 0; i < chars.length; i++)
		{
			chars[i] = sortString.charAt(i);
		}

		// new Comparator for insertion sort method
		Comparator<Character> compare = new Comparator<Character>()
		{
			/**
			 * Compares two Character objects using the natural ordering for the
			 * Character class.
			 * 
			 * @param char1
			 * @param char2
			 * @return - returns -1 if char1 comes before char2, 0 if they are
			 *         equal, and +1 if char1 comes after char2.
			 */
			@Override
			public int compare (Character char1, Character char2)
			{
				return char1.compareTo(char2);
			}

		};

		// sort the Characters
		insertionSort(chars, compare);

		// unbox the Characters
		char[] unboxedChars = new char[chars.length];
		for (int i = 0; i < chars.length; i++)
		{
			unboxedChars[i] = chars[i];
		}
		return new String(unboxedChars);
	}

	/**
	 * Generic method for sorting an array of type T
	 * 
	 * @param tArray
	 *            - the array to be sorted
	 * @param compare
	 *            - the comparator that defines the ordering of the array
	 */
	public static <T> void insertionSort (T[] tArray,
			Comparator<? super T> compare)
	{	
		for (int i = 1; i < tArray.length; i++)
		{
			// keep around the value
			T temp = tArray[i];
			int index = i;

			for (; index > 0 && compare.compare(temp, tArray[index - 1]) < 0; index--)
			{
				tArray[index] = tArray[index - 1];
			}
			tArray[index] = temp;
		}
	}

	/**
	 * Compares two words and determines if they are anagrams of each other. Two
	 * words are anagrams if they contain the same letters in the same
	 * frequency. For example, alert and later are anagrams. Words with the same
	 * letters in the same frequency, but different cases are still anagrams.
	 * 
	 * @param word1
	 *            - a word for comparison
	 * @param word2
	 *            - a word for comparison
	 * @return - returns true if the two input strings are anagrams of each
	 *         other, otherwise returns false.
	 */
	public static boolean areAnagrams (String word1, String word2)
	{
		// Compare the result of sort(word1) with the result of sort(word2)
		// if they are equal return true, else return false.
		return sort(word1).compareTo(sort(word2)) == 0;
	}

	/**
	 * Overloaded method that returns the largest group of anagrams in the input
	 * array of words, in no particular order. It returns an empty array if
	 * there are no anagrams in the input array.
	 * 
	 * Assumes that the word list given as input does not contain duplicates.
	 * 
	 * @param words
	 *            - an array of Strings to be analyzed
	 * @return - the largest group of anagrams in the input array of words,
	 *         returns an empty array if there are no anagrams in the input
	 *         array. If there are ties for largest, will return one of the
	 *         groups.
	 */
	public static String[] getLargestAnagramGroup (String[] words)
	{
		// if input array is empty, return an empty array
		if (words.length < 1)
		{
			return new String[0];
		}

		// make an array to copy words into
		String[] wordsCopy = new String[words.length];

		// sort all the characters in the words and insert into the new array
		for (int i = 0; i < wordsCopy.length; i++)// String s : wordsCopy)
		{
			wordsCopy[i] = sort(words[i]);
		}

		// sort the sorted words in the array
		Comparator<String> compare = String.CASE_INSENSITIVE_ORDER;
		AnagramUtil.insertionSort(wordsCopy, compare);

		// identify the largest anagram group
		int currentAnagramGroupSize = 1;
		int largestAnagramGroupSize = 1;
		int largestAnagramGroupFirst = 0;
		int currentGroupFirst = 0;

		for (int i = currentGroupFirst + 1; i < wordsCopy.length; i++)
		{
			// if the next item is equal to currentGroupFirst then we add one
			// to the currentAnagramGroupSize
			if (wordsCopy[currentGroupFirst].equals(wordsCopy[i]))
			{
				currentAnagramGroupSize++;
			}
			// if the next item is not equal to currentGroupFirst then we have
			// the start of a new group
			else
			{
				// if the current group we just finished counting is the
				// biggest, update largestAnagramSize,
				// update largestAnagramSetFirst with the groups first
				// index, and reset the currentAnagramSize.
				// Finally, set currentGroupFirst = i to mark the beginning
				// of a new group.
				if (currentAnagramGroupSize > largestAnagramGroupSize)
				{
					largestAnagramGroupSize = currentAnagramGroupSize;
					largestAnagramGroupFirst = currentGroupFirst;
					currentAnagramGroupSize = 1;
					currentGroupFirst = i;
				}
				// if the current group we just finished counting isn't the
				// biggest, update currentAnagramSetSize,
				// and set currentGroupFirst = i to mark the beginning of a
				// new group.
				else
				{
					currentAnagramGroupSize = 1;
					currentGroupFirst = i;
				}
			}
		}

		// check if there were any anagram groups, if not return an empty array
		if (largestAnagramGroupSize == 1)
		{
			return new String[0];
		}

		// return an array of the appropriate anagram group with the words in
		// their original form
		String[] anagramGroup = new String[largestAnagramGroupSize];

		int anagramGroupIndex = 0;
		for (int i = 0; i < words.length; i++)
		{
			// use isAnagram to compare the original words to our known anagram
			// result (largestAnagramGroupFirst)
			// and add them to the list if the result is true.
			if (AnagramUtil.areAnagrams(words[i],
					wordsCopy[largestAnagramGroupFirst]))
			{
				anagramGroup[anagramGroupIndex] = words[i];
				anagramGroupIndex++;
			}
		}

		return anagramGroup;
	}

	/**
	 * Overloaded method that returns the largest group of anagrams in the input
	 * array of words, in no particular order. Reads the list of words from the
	 * input filename. It is assumed that the file contains one word per line.
	 * If the file does not exist, is empty, or there are no anagrams in the
	 * input the method returns an empty array.
	 * 
	 * Assumes that the word list given as input does not contain duplicates.
	 * 
	 * @param filename
	 *            - the name of the file with words to be input
	 * @return - the largest group of anagrams in the input array of words,
	 *         returns an empty array if there are no anagrams in the input
	 *         array. If there are ties for largest, will return one of the
	 *         groups.
	 * 
	 */
	public static String[] getLargestAnagramGroup (String filename)
	{
		// get all words from file
		ArrayList<String> list = new ArrayList<String>();
		try (Scanner myScanner = new Scanner(new File(filename)))
		{
			while (myScanner.hasNext())
			{
				list.add(myScanner.next());
			}
		}
		catch (FileNotFoundException e)
		{
			// the file does not exist, return empty array
			return new String[0];
		}
		return getLargestAnagramGroup(list.toArray(new String[list.size()]));
	}

}
