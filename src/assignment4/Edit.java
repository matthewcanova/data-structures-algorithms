package assignment4;

import java.util.ArrayList;
import java.util.Comparator;
import assignment4.AnagramUtil;

public class Edit
{
	public static String[] getLargestAnagramGroup (String[] words)
	{

		if (words.length < 1)
		{
			return new String[0];
		}

		// make a copy of the words
		String[] wordsCopy = new String[words.length];

		// sort all the characters in the words
		for (int i = 0; i < wordsCopy.length; i++)// String s : wordsCopy)
		{
			wordsCopy[i] = AnagramUtil.sort(words[i]);
		}
		
		// sort the sorted words
		Comparator<String> compare = String.CASE_INSENSITIVE_ORDER;
		AnagramUtil.insertionSort(wordsCopy, compare);

		// identify the largest group
		int currentAnagramGroupSize = 0;
		int largestAnagramGroupSize = 1;
		int largestAnagramGroupFirst = 0;
		int lookingAt;
		int beginingOfGroup = 0;
		
		for (lookingAt = 0; lookingAt < wordsCopy.length; lookingAt++)
		{
			
			// see if that group is bigger
			//set the different one as the first in group
			//repeat
			
			// find the first list
						//move over till you find something different
			if (wordsCopy[beginingOfGroup].equals(wordsCopy[lookingAt]))
			{
				currentAnagramGroupSize++;
			}
			else
			{
				//must have found new group
				beginingOfGroup = lookingAt;
				if (currentAnagramGroupSize > largestAnagramGroupSize)
				{
					largestAnagramGroupSize = currentAnagramGroupSize;
					largestAnagramGroupFirst = lookingAt;
					currentAnagramGroupSize = 1;
				}
			}
			lookingAt++;
		}

		// our first item to compare
		for (int i = 0; i < wordsCopy.length - 1;)
		{
			// the item to compare to, only terminate when we find the start of
			// a new group and set i = j
			for (int j = i + 1; j != i && j < wordsCopy.length; j++)
			{
				// if the next item j is equal to i then we add one to the
				// currentAnagramGroupSize
				if (wordsCopy[i].equals(wordsCopy[j]))
				{
					currentAnagramGroupSize++;
				}
				// if the next item j is not equal to i then we have the start
				// of a new group
				else
				{
					// if the current group we just finished counting is the
					// biggest, update largestAnagramSize,
					// update largestAnagramSetFirst with the groups first
					// index, and reset the currentAnagramSize.
					// Finally, set i = j to mark the beginning of a new group.
					if (currentAnagramGroupSize > largestAnagramGroupSize)
					{
						largestAnagramGroupSize = currentAnagramGroupSize;
						largestAnagramGroupFirst = i;
						currentAnagramGroupSize = 1;
						i = j;
					}
					// if the current group we just finished counting isn't the
					// biggest, update currentAnagramSetSize,
					// and set i = j to mark the beginning of a new group.
					else
					{
						currentAnagramGroupSize = 1;
						i = j;
					}
				}
			}
		}
		// if there were no anagram groups, return an empty array
		if (largestAnagramGroupSize == 1)
		{
			return new String[0];
		}

		// return an array of the appropriate anagram group with the words in
		// their original form
		ArrayList<String> anagramGroup = new ArrayList<String>();

		for (int i = 0; i < words.length; i++)
		{
			// use isAnagram to compare the original words to our known anagram
			// result (largestAnagramGroupFirst)
			// and add them to the list if the result is true.
			if (AnagramUtil.areAnagrams(words[i],
					wordsCopy[largestAnagramGroupFirst]))
			{
				anagramGroup.add(words[i]);
			}
		}
		String[] returnArray = new String[anagramGroup.size()];
		anagramGroup.toArray(returnArray);
		return returnArray;

	}
}
