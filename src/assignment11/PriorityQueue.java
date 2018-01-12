package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is
 * implemented as a min heap. The min heap is implemented implicitly as an
 * array.
 * 
 * @author
 */
public class PriorityQueue<AnyType> {

	private int currentSize;

	private AnyType[] array;

	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according to their
	 * natural ordering (i.e., AnyType is expected to be Comparable) AnyType is
	 * not forced to be Comparable.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator. Orders
	 * elements according to the input Comparator (i.e., AnyType need not be
	 * Comparable).
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		if (currentSize == 0) {
			throw new NoSuchElementException();
		}

		return array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException {

		// if the heap is empty, throw a NoSuchElementException
		if (currentSize == 0) {
			throw new NoSuchElementException();
		}

		// store the minimum item so that it may be returned at the end
		AnyType min = findMin();
		
		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;

		// update size
		currentSize--;
		
		// percolate the item at minIndex down the tree until heap order is
		// restored
		percolateDown();
		
		// return the minimum item that was stored
		return min;
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x
	 *            -- the item to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void add(AnyType x) {

		// if the array is full, double its capacity\
		if (currentSize >= array.length) {
			AnyType[] newArray = (AnyType[]) new Object[array.length * 2];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;

		// update size
		currentSize++;

		// percolate the new item up the levels of the tree until heap order is
		// restored
		// It is STRONGLY recommended that you write a percolateUp helper
		// method!
		percolateUp(currentSize-1);
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for (int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i]
						+ "|<f2> \"]");
				if (((i * 2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1)
							+ ":f1");
				if (((i * 2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2)
							+ ":f1");
			}

			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by
	 * the user at construction time, or Comparable, if no Comparator was
	 * provided.
	 */
	@SuppressWarnings("unchecked")
	private int compare(AnyType lhs, AnyType rhs) {
		if (cmp == null)
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe
																		// to
																		// ignore
																		// warning
		// We won't test your code on non-Comparable types if we didn't supply a
		// Comparator

		return cmp.compare(lhs, rhs);
	}

	// LEAVE IN for grading purposes
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}

	private void percolateUp(int index) {
		int currentIndex = index;
		while (compare(array[currentIndex], array[getParent(currentIndex)]) < 0) {
			AnyType temp = array[currentIndex];
			array[currentIndex] = array[getParent(currentIndex)];
			array[getParent(currentIndex)] = temp;
			currentIndex = getParent(currentIndex);
			if (currentIndex == 0)
				return;
		}
	}

	private void percolateDown() {

		int index = 0;

		// continue while there are children
		while (getLeftChild(index) < currentSize) {
			int leftC = getLeftChild(index);
			// if there is also a right child
			if (getRightChild(index) <currentSize) {
				int rightC = getRightChild(index);
				int compResult = compare(array[leftC], array[rightC]);
				// if left is smaller than right
				if (compResult < 0) {
					// compare left with parent
					if (swap(leftC, index)) {
						index = leftC;
						continue;
					} else
						return;
				}
				// if right is smaller than left
				else if (compResult > 0) {
					// compare right with parent
					if (swap(rightC, index)) {
						index = rightC;
						continue;
					} else
						return;
				}
				// if they are equal, use the left
				else {
					// compare left with parent
					if (swap(leftC, index)) {
						index = leftC;
						continue;
					} else
						return;
				}
			} else {
				// compare left with parent
				if (swap(leftC, index)) {
					index = leftC;
					continue;
				} else
					return;
			}
		}
	}

	private boolean swap(int child, int parent) {

		if (compare(array[child], array[parent]) < 0) {
			AnyType tmp = array[parent];
			array[parent] = array[child];
			array[child] = tmp;
			parent = child;
			return true;
		} else
			return false;

	}

	private int getParent(int i) {
		return (i - 1) / 2;
	}

	private int getRightChild(int i) {
		return (i * 2) + 2;
	}

	private int getLeftChild(int i) {
		return (i * 2) + 1;
	}

}
