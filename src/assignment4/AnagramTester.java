package assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class AnagramTester
{

	private static Random rand;

	/**
	 * @param args
	 */
	public static void main (String[] args)
	{
		// Set up the random number generator for the randomString function
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		// Reads a text file with a single word per line, returns them as an
		// array of Strings
		//String[] words = readFile("sample_word_list.txt");
		
		String[] largestGroup = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		System.out.println("sample_word_list.txt");
		System.out.println();
		for (String s: largestGroup)
		{
			System.out.println(s);
		}


	}

	// Create a random string [a-z] of specified length
	public static String randomString (int length)
	{
		String retval = "";
		Random rand1 = new Random();
		for (int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			retval += (char) ('a' + (rand1.nextInt(26)));
		}
		return retval;
	}

	// Reads words from a file (assumed to contain one word per line)
	// Returns the words as an array of strings.
	public static String[] readFile (String filename)
	{
		ArrayList<String> results = new ArrayList<String>();
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while (input.ready())
			{
				results.add(input.readLine());
			}
			input.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String[] retval = new String[0];
		return results.toArray(retval);
	}

}
