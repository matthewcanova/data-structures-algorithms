package assignment8;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A class representing a binary search tree. This tree is not self-balancing.
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 * 
 * @param <Type> Items must be Comparable
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements
		SortedSet<Type>
{
	private Node root;
	private int size;

	public BinarySearchTree()
	{
		size = 0;
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean add(Type item)
	{
		if (item == null)
			throw new NullPointerException();

		// add if the tree is empty
		if (root == null)
		{
			root = new Node(item, null, null);
			size++;
			return true;
		}

		Node current = root;
		int comparison = 0;
		
		// find where to add the item
		while (true)
		{
			comparison = item.compareTo(current.data);
			if (comparison > 0)
				if (current.right == null)
				{
					current.right = new Node(item, null, null);
					size++;
					return true;
				} 
				else
					current = current.right;
			else if (comparison < 0)
				if (current.left == null)
				{
					current.left = new Node(item, null, null);
					size++;
					return true;
				} 
				else
					current = current.left;
			else
				return false;
		}
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items)
	{
		boolean retVal = false;
		for (Type i : items)
		{
			if (add(i))
				retVal = true;
		}
		return retVal;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear()
	{
		root = null;
		size = 0;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean contains(Type item)
	{
		if (item == null)
			throw new NullPointerException();

		Node current = root;

		// traverse the tree to find the item
		while (current != null)
		{
			int comparison = item.compareTo(current.data);
			if (comparison > 0)
				current = current.right;
			else if (comparison < 0)
				current = current.left;
			else
				return true;
		}

		return false;
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items)
	{
		if (items.isEmpty())
			return false;

		for (Type i : items)
			if (!contains(i))
				return false;

		return true;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public Type first() throws NoSuchElementException
	{
		if (isEmpty())
			throw new NoSuchElementException();

		Node current = root;
		
		// traverse the tree
		while (current.left != null)
			current = current.left;
		
		return current.data;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty()
	{
		return (size == 0);
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public Type last() throws NoSuchElementException
	{
		if (isEmpty())
			throw new NoSuchElementException();

		Node current = root;
		
		// traverse the tree
		while (current.right != null)
			current = current.right;
		
		return current.data;
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *            - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually removed); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean remove(Type item)
	{
		if (item == null)
			throw new NullPointerException();

		Node parent = root;
		Node child = null;

		// try to find our item and return the parent of the item we want to remove (null if not found)
		parent = findRecursive(root, item);

		// if we did not find our item
		if (parent == null)
			return false;

		// identify if we are removing the root
		if (root.data.compareTo(item) == 0)
		{
			parent = null;
			child = root;
		}

		// identify if we are removing left or right child of our parent
		else if (parent.left != null && parent.left.data.compareTo(item) == 0)
			child = parent.left;
		else
			child = parent.right;

		// no child
		if (child.left == null && child.right == null)
			return removeWithNoChildren(parent, child);

		// one child
		else if (child.left == null || child.right == null)
			return removeWithOneChild(parent, child);

		// two children
		else
			return removeWithTwoChildren(parent, child);
	}

	/**
	 * Traverses the tree and finds the parent of the corresponding node.
	 * 
	 * @param current The current node
	 * @param item The data to search for
	 * @return The parent of the node containing the data, or null if it is not contained
	 */
	private Node findRecursive(Node current, Type item)
	{
		if (current == null)
			return null;

		int comparison = item.compareTo(current.data);

		// if greater than check to the right
		if (comparison > 0)
		{
			if (current.right == null)
				return null;

			if (current.right.data.compareTo(item) == 0)
				return current;
			return findRecursive(current.right, item);
		}

		// if less than check to the left
		else if (comparison < 0)
		{
			if (current.left == null)
				return null;
			if (current.left.data.compareTo(item) == 0)
				return current;
			return findRecursive(current.left, item);
		}

		// will only be equal if located at the root
		else
			return current;
	}

	/**
	 * Removes a node that has no children
	 * @param parent The parent of the node to remove
	 * @param child The node to remove
	 * @return true when successfully removed
	 */
	private boolean removeWithNoChildren(Node parent, Node child)
	{
		if (parent == null)
		{
			clear();
			return true;
		} 
		else if (parent.left == child)
		{
			parent.left = null;
			size--;
			return true;
		} 
		else
		{
			parent.right = null;
			size--;
			return true;
		}
	}

	/**
	 * Removes a node with one child
	 * @param parent The parent of the node to remove
	 * @param child The node to remove
	 * @return true if the child was removed
	 */
	private boolean removeWithOneChild(Node parent, Node child)
	{
		// root
		if (parent == null)
		{
			if (child.left != null)
			{
				root = child.left;
				size--;
				return true;
			} 
			else
			{
				root = child.right;
				size--;
				return true;
			}
		}

		// if the child is on the right side
		if (child.left == null)
		{
			// get the child's relation to the parent (left or right child)
			if (parent.left != null && parent.left == child)
			{
				parent.left = child.right;
				size--;
				return true;
			} 
			else if (parent.right != null)
			{
				parent.right = child.right;
				size--;
				return true;
			}
		}

		// if the child is on the left side
		else
		{
			// get the child's relation to the parent (left or right child)
			if (parent.left == child)
			{
				parent.left = child.left;
				size--;
				return true;
			} 
			else
			{
				parent.right = child.left;
				size--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes a node with two children
	 * @param parent The parent of the child to remove
	 * @param child The node to remove
	 * @return true if the node was removed
	 */
	private boolean removeWithTwoChildren(Node parent, Node child)
	{
		// find the successor (lowest on the right)
		Type successor = findAndRemoveSuccessor(child);
		
		// identify relationship of item to it's parent
		if (child == root)
		{
			root.data = successor;
			return true;
		}

		if (parent.left == child)
		{
			parent.left.data = successor;
			return true;
		}

		else
		{
			parent.right.data = successor;
			return true;
		}
	}

	/**
	 * Finds the successor node for removing two children, and removes it
	 * @param subtreeRoot The root of the subtree to find the successor of
	 * @return The data of the successor node
	 */
	private Type findAndRemoveSuccessor(Node subtreeRoot)
	{
		Node current = subtreeRoot.right;

		// find the successor's parent
		while (current.left != null)
			if (current.left.left != null)
				current = current.left;
			else
				break;

		Type data;

		// check that the right child is not our successor (only non-left option)
		if (subtreeRoot.right.left != null)
		{
			data = current.left.data;
			// one child (guaranteed no left child)
			if (current.left.right != null)
				removeWithOneChild(current, current.left);
			// no child
			else
				removeWithNoChildren(current, current.left);
		} 
		// right child is our successor
		else
		{
			data = current.data;
			// one child (guaranteed no left child)
			if (current.right != null)
				removeWithOneChild(subtreeRoot, current);
			// no child
			else
				removeWithNoChildren(subtreeRoot, current);
		}

		return data;
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items
	 *            - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually removed);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items)
	{
		boolean retVal = false;
		for (Type i : items)
			if (remove(i))
				retVal = true;
		
		return retVal;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<Type> toArrayList()
	{
		ArrayList<Type> retArray = new ArrayList<Type>();
		if (isEmpty())
			return retArray;
		
		addValues(root, retArray);

		return retArray;
	}

	/**
	 * Adds all values to an array list in sorted order using in-order traversal
	 * @param current the current node being looked at
	 * @param retArray the array to add values to
	 */
	private void addValues(Node current, ArrayList<Type> retArray)
	{
		if (current == null)
			return;

		// travese the array in in-order traversal
		addValues(current.left, retArray);
		retArray.add(current.data);
		addValues(current.right, retArray);
	}

	/**
	 * Generates a DOT file that represents this BST
	 * 
	 * @param file
	 */
	public void writeDOT(String file)
	{
		try
		{
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(file));

			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");

			if (root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void writeDotRecursive(Node n, PrintWriter output) throws Exception
	{
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if (n.left != null)
		{
			// write the left subtree
			writeDotRecursive(n.left, output);

			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D");
		}
		if (n.right != null)
		{
			// write the left subtree
			writeDotRecursive(n.right, output);

			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D");
		}

	}

	/**
	 * Class representing a Node structure
	 * 
	 * @author Trevor Chapman
	 * @author Matthew Canova
	 *
	 */
	private class Node
	{
		Type data;
		Node left;
		Node right;

		private Node(Type data, Node left, Node right)
		{
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

}
