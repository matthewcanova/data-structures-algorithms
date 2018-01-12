package assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * SortUtil provides generic static methods for sorting ArrayLists and creating ArrayLists
 * 
 * @author Matthew Canova, Aaron Bellis
 *
 */
public class SortUtil
{
	//seed for randomizing an ArrayList of Integers
	private static final long SEED = 42l;
	//controls threshold for switching from insertionsort to mergesort.
	private static final int INSERTIONSORTTHRESHOLD = 60;
	// PIVOTSTRAT = 1 : Use the end value as the pivot
	// PIVOTSTRAT = 2 : Use the middle value as the pivot
	// PIVOTSTRAT = 3 : Use a random value as the pivot
	// PIVOTSTRAT = 4 : Use the median of three as the pivot
	private static final int PIVOTSTRAT = 2;

	/**
	 * Generic method for sorting an array of type T using a Merge Sort
	 * algorithm. Switches to an Insertion Sort algorithm once the sub-array
	 * size is less than INSERTIONSORTTHRESHOLD. This is a driver method and
	 * relies on the recursive mergesort method.
	 * 
	 * @param arr
	 *            - the array to be sorted
	 * @param compare
	 *            - the comparator that defines the ordering of the array
	 */
	// driver method
	public static <T> void mergesort (ArrayList<T> arr,
			Comparator<? super T> compare)
	{
		// make sure array is big enough to be sorted before before doing
		// recursive call
		if (arr.size() > 1)
		{
			// initialize our temporary array and add null values
			// so that we can use set() on it in our merge method
			ArrayList<T> tmpArray = new ArrayList<T>(arr.size());
			for (int i = 0; i < arr.size(); i++)
			{
				tmpArray.add(null);
			}
			mergesort(arr, tmpArray, 0, arr.size() - 1, compare);
		}
	}

	/**
	 * Recursive method for sorting an array of type T using a Merge Sort
	 * algorithm. Switches to an Insertion Sort algorithm once the sub-array
	 * size is less than INSERTIONSORTTHRESHOLD. This is a recursive method that
	 * is called from the driver mergesort method.
	 * 
	 * @param arr
	 *            - the array to be sorted
	 * @param tmpArray
	 *            - a temporary array for sorting sub-arrays
	 * @param left
	 *            - the left bound of the current set
	 * @param right
	 *            - the right bound of the current set
	 * @param compare
	 *            - a comparator for sorting type T
	 */
	// recursive method
	private static <T> void mergesort (ArrayList<T> arr, ArrayList<T> tmpArray,
			int left, int right, Comparator<? super T> compare)
	{
		// base case -- once our sub-arrays are smaller than
		// INSERTIONSORTTHRESHOLD,
		// use an insertion sort to complete sorting.
		if (right - left < INSERTIONSORTTHRESHOLD)
		{
			insertionSort(arr, compare, left, right);
		}
		// recursive work -- call mergesort on two sub-arrays of arr, then
		// call merge in order to merge and sort the two sub-arrays.
		else
		{
			int center = (left + right) / 2;
			mergesort(arr, tmpArray, left, center, compare);
			mergesort(arr, tmpArray, center + 1, right, compare);
			merge(arr, tmpArray, left, center + 1, right, compare);
		}
	}

	/**
	 * Method for merging two sorted sub-arrays together.
	 * 
	 * @param arr - array that is being sorted
	 * @param tmpArray - the provided temp array for mergesort
	 * @param leftPos - start position of the left subarray
	 * @param rightPos - start position of the right subarray
	 * @param rightEnd - end of the right subarray
	 * @param compare - provided comparator for the ArrayList
	 */
	private static <T> void merge (ArrayList<T> arr, ArrayList<T> tmpArray,
			int leftPos, int rightPos, int rightEnd,
			Comparator<? super T> compare)
	{
		// track where the left sub-array ends
		int leftEnd = rightPos - 1;
		// track the beginning of the entire set for merging
		int tmpPos = leftPos;
		// track the total number of elements in the set
		int numElements = rightEnd - leftPos + 1;
		// track the beginning of the entire set for copying
		int start = leftPos;

		// while both sub-arrays still have elements, compare
		// the next unsorted items and add the smallest to the
		// temporary array.
		while (leftPos <= leftEnd && rightPos <= rightEnd)
		{
			if (compare.compare(arr.get(leftPos), arr.get(rightPos)) <= 0)
			{
				tmpArray.set(tmpPos++, arr.get(leftPos++));
			}
			else
			{
				tmpArray.set(tmpPos++, arr.get(rightPos++));
			}
		}
		// while only the left side has remaining elements,
		// copy the elements into the temporary array.
		while (leftPos <= leftEnd)
		{
			tmpArray.set(tmpPos++, arr.get(leftPos++));
		}
		// while only the right side has remaining elements,
		// copy the elements into the temporary array.
		while (rightPos <= rightEnd)
		{
			tmpArray.set(tmpPos++, arr.get(rightPos++));
		}
		// copy the temporary array back into arr, using
		// the start variable to ensure we are setting our
		// elements into the correct position in the target
		// array.
		for (int i = 0; i < numElements; i++)
		{
			arr.set(start + i, tmpArray.get(start + i));
		}
	}

	/**
	 * Generic method for sorting an array of type T using a Quicksort
	 * algorithm.
	 * 
	 * @param arr
	 *            - the array to be sorted
	 * @param compare
	 *            - the comparator that defines the ordering of the array
	 */
	// driver method
	public static <T> void quicksort (ArrayList<T> arr,
			Comparator<? super T> compare)
	{
		// make sure array is big enough to be sorted before before doing
		// recursive call
		if (arr.size() > 1)
		{
			quicksort(arr, compare, 0, arr.size() - 1);
		}
	}

	// recursive method
	private static <T> void quicksort (ArrayList<T> arr,
			Comparator<? super T> compare, int leftBound, int rightBound)
	{
		// if number of elements is < 1 return
		if (leftBound >= rightBound)
		{
			return;
		}
		else
		{
			// select pivot
			// pickPivot method both chooses a pivot based on the pivot strat
			// and puts it in the right place
			int pivot = pickPivot(arr, compare, leftBound, rightBound,
					PIVOTSTRAT);
			// swap pivot with end
			swapArrayElements(arr, pivot, rightBound);
			// update where pivot is
			pivot = rightBound;

			// partition
			int left = leftBound;
			int right = pivot - 1;
			// start moving from the left and right
			while (left <= right)
			{
				// move from left looking for elements that are greater than the
				// pivot
				while (left <= right
						&& compare.compare(arr.get(left), arr.get(pivot)) <= 0)
				{
					left++;
				}

				// move from left looking for elements that are less than the
				// pivot
				while (right >= left
						&& compare.compare(arr.get(right), arr.get(pivot)) >= 0)
				{
					right--;
				}

				// swap the items if we haven't found the end point
				if (left <= right)
				{
					swapArrayElements(arr, left, right);
				}
			}

			// put the pivot in the right place
			swapArrayElements(arr, pivot, left);

			// pivot is now in place of the left element
			pivot = left;

			// sort the left side
			quicksort(arr, compare, leftBound, pivot - 1);

			// sort the right side
			quicksort(arr, compare, pivot + 1, rightBound);
		}

	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * ascending order.
	 * 
	 * @param size
	 *            - The size of the list to be generated
	 * @return an array list of Integers from 1 to size in ascending order
	 */
	public static ArrayList<Integer> generateBestCase (int size)
	{
		ArrayList<Integer> retArray = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++)
		{
			retArray.add(i);
		}

		return retArray;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * permuted order (i,e., randomly ordered)
	 * 
	 * @param size
	 *            - The size of the list to be generated
	 * @return an array list of Integers from 1 to size in permuted order (i,e.,
	 *         randomly ordered)
	 */
	public static ArrayList<Integer> generateAverageCase (int size)
	{
		ArrayList<Integer> retArray = new ArrayList<Integer>();
		Random rand = new Random(SEED);
		for (int i = 1; i <= size; i++)
		{
			retArray.add(i);
		}

		Collections.shuffle(retArray, rand);
		return retArray;
	}

	/**
	 * Overload of the generateAveragecase method that accepts a seed as a
	 * parameter. Generates and returns an ArrayList of integers 1 to size in
	 * permuted order (i,e., randomly ordered) based on the provided seed.
	 * 
	 * @param size
	 *            - The size of the list to be generated
	 * @return an array list of Integers from 1 to size in permuted order (i,e.,
	 *         randomly ordered)
	 */
	public static ArrayList<Integer> generateAverageCase (int size, long seed)
	{
		ArrayList<Integer> retArray = new ArrayList<Integer>();
		Random rand = new Random(seed);
		for (int i = 1; i <= size; i++)
		{
			retArray.add(i);
		}

		Collections.shuffle(retArray, rand);
		return retArray;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * descending order.
	 * 
	 * @param size
	 *            - The size of the list to be generated
	 * @return an array list of Integers from 1 to size in descending order
	 */
	public static ArrayList<Integer> generateWorstCase (int size)
	{
		ArrayList<Integer> retArray = new ArrayList<Integer>();
		for (int i = size; i >= 1; i--)
		{
			retArray.add(i);
		}

		return retArray;
	}

	/**
	 * Generic method for sorting an array of type T using Insertion Sort
	 * 
	 * @param tArray
	 *            - the array to be sorted
	 * @param compare
	 *            - the comparator that defines the ordering of the array
	 */
	public static <T> void insertionSort (ArrayList<T> arr,
			Comparator<? super T> compare, int left, int right)
	{

		for (int i = left + 1; i <= right; i++)
		{
			// keep around the value
			T temp = arr.get(i);
			int index = i;

			while (index > left
					&& compare.compare(temp, arr.get(index - 1)) < 0)
			{
				arr.set(index, arr.get(index - 1));
				index--;
			}

			arr.set(index, temp);
		}
	}

	/**
	 * Private method that switches the pivot strategy for the quicksort
	 * algorithm. Will return the index in the ArrayList that contains the
	 * pivot.
	 * 
	 * @param arr
	 *            - the ArrayList to be sorted
	 * @param compare
	 *            - a Comparator that defines the ordering for arr
	 * @param leftBound
	 *            - the index of the left bound for the Array
	 * @param rightBound
	 *            - the index of the right bound for the Array
	 * @param pivotStrat
	 *            - The pivot Strategy used for the quicksort algorithm. Will
	 *            throw IllegalArgumentException if not 1, 2, 3, or 4
	 * 
	 *            <pre>
	 *            1 : Use the end value as the pivot 
	 *            2 : Use the middle value as the pivot 
	 *            3 : Use a random value as the pivot 
	 *            4 : Use the median of three as the pivot
	 * </pre>
	 * @return the index in the ArrayList that contains the pivot.
	 */
	private static <T> int pickPivot (ArrayList<T> arr,
			Comparator<? super T> compare, int leftBound, int rightBound,
			int pivotStrat)
	{
		int pivotIndex = 0;
		switch (pivotStrat)
		{
			case 1:
				pivotIndex = rightBound;
				break;
			case 2:
				// calculate the middle and use it as the pivot index
				pivotIndex = (rightBound + leftBound) / 2;
				break;
			case 3:
				// get random element from the sub array
				double rand = Math.random();
				int element = (int) ((rightBound - leftBound) * rand);
				pivotIndex = leftBound + element;

				break;
			case 4:
				// use median of 3 rightBound, middle, and LeftBound

				// get middle element
				int middle = (rightBound + leftBound) / 2;

				// left < middle < right
				if (compare.compare(arr.get(leftBound), arr.get(middle)) < 0
						&& compare
								.compare(arr.get(middle), arr.get(rightBound)) < 0)
				{
					pivotIndex = middle;
				}
				// middle < left < right
				else if (compare.compare(arr.get(middle), arr.get(leftBound)) < 0
						&& compare.compare(arr.get(leftBound),
								arr.get(rightBound)) < 0)
				{
					pivotIndex = leftBound;
				}
				// middle < right < left
				else
				{
					pivotIndex = rightBound;
				}
				break;

			default:
				if (pivotStrat < 1 && pivotStrat > 4)
				{
					throw new IllegalArgumentException();
				}
		}
		return pivotIndex;
	}

	/**
	 * Swaps the element at index1 with the element of index2 in an ArrayList
	 * arr.
	 * 
	 * @param arr
	 *            - the ArrayList where elements are being swapped
	 * @param index1
	 *            - the index of the element to be swapped with the element of
	 *            index2
	 * @param index2
	 *            - the index of the element to be swapped with the element of
	 *            index1
	 */
	private static <T> void swapArrayElements (ArrayList<T> arr, int index1,
			int index2)
	{
		// save the element in index1
		T temp = arr.get(index1);
		// place element at index2 in index1
		arr.set(index1, arr.get(index2));
		// place saved element at index1 in index2
		arr.set(index2, temp);
	}

}
