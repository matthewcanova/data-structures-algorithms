package assignment10;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A Hash Table implementation that uses quadratic probing to resolve
 * collisions. Tracks a load factor, which represents the density of the table.
 * Will rehash once the load factor exceeds 0.5.
 * 
 * @author Boqian Yao
 * @author Matthew Canova
 * 
 */
public class QuadProbeHashTable implements Set<String> {

	private int size;
	private String[] table;
	
	private HashFunctor functor;
	
	private int primeCapacity;
	double loadFactor;
	private long collisionCount;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {

		// make sure the capacity is a prime number.
		// if it is not a prime number we pick the next prime number.
		primeCapacity = capacity;
		while (!isPrime(primeCapacity)) {
			primeCapacity++;
		}
		table = new String[primeCapacity];
		this.functor = functor;

		loadFactor = 0.0;
	}

	@Override
	public boolean add(String item) {

		if (item == null) {
			throw new NullPointerException();
		}
		if (loadFactor > 0.5) {
			reHash();
		}

		int hashCode = functor.hash(item) % primeCapacity;

		int current = hashCode;
		int collision = 0;

		// check the loadFactor.

		// find empty space for the item.
		while (table[current] != null) {
			// if it already exists, return false.
			if (table[current].equals(item))
				return false;
			// update collision times.
			collision++;
			collisionCount++;
			// get new index following quadratic probing
			current = (hashCode + (int) Math.pow(collision, 2)) % primeCapacity;
		}
		// insert item into empty spot
		table[current] = item;

		size++;
		updateLoadFactor();
		return true;

	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}

		// assume set is not changed
		boolean result = false;

		// attempt to add all items from the collection
		// if any succeed, then change return value to true
		for (String s : items) {
			if (add(s))
				result = true;
		}
		return result;
	}

	@Override
	public void clear() {
		// update all variables
		size = 0;
		loadFactor = 0.0;
		collisionCount = 0;

		// create a new empty table.
		table = new String[primeCapacity];
	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			throw new NullPointerException();
		}

		int hashCode = functor.hash(item) % primeCapacity;

		int i = hashCode;
		int collision = 0;

		// while this hash address continues to have items,
		// check for the item
		while (table[i] != null) {
			// if item exists return true.
			if (table[i].equals(item)) {
				return true;
			} else {
				// get new hash code plus quadratic probing increment.
				collision++;
				i = (int) (hashCode + Math.pow(collision, 2)) % primeCapacity;

			}

		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}

		if (items.isEmpty())
			return false;

		// if any item is not found, return false.
		for (String s : items) {
			System.out.println(s);
			if (!contains(s)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public int size() {

		return size;
	}

	/**
	 * A method for determining if an integer is a prime number.
	 * 
	 * @param the
	 *            number to check for primeness
	 * @return true if prime, false if not
	 */
	private boolean isPrime(int number) {
		// if value is even, return false
		if (number % 2 == 0)
			return false;

		// if value is divisible by any previous values
		// return false
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * A helper method that updates the load factor. Will be called whenever
	 * size or capacity changes.
	 */
	private void updateLoadFactor() {
		loadFactor = (double) size / primeCapacity;
	}

	/**
	 * A helper method that rehashes this table when the load factor is bigger
	 * than 0.5 Updates to a new prime capacity that is more than double the
	 * previous capacity and then reinserts all elements.
	 * 
	 */
	private void reHash() {
		// double the capacity
		int newCapacity = 2 * primeCapacity;
		// make sure new capacity is a prime number.
		while (!isPrime(newCapacity)) {
			newCapacity++;
		}
		// save the old values.
		primeCapacity = newCapacity;
		ArrayList<String> oldValues = new ArrayList<String>();
		for (String s : table) {
			oldValues.add(s);
		}

		// create a new table with new capacity.
		// update variables.
		table = new String[primeCapacity];
		size = 0;

		// transfer the old values to new table.
		for (String s : oldValues) {
			if (s != null)
				add(s);
		}
		// update load factor.
		updateLoadFactor();
	}
	
	
	/**
	 * Return the number of collisions that have occurred since 
	 * creation, rehashing, or clearing. 
	 * 
	 * @return long representing the number of collisions
	 */
	public long getCollision(){
		return collisionCount;
	}
	 
	
	/**
	  * Return the functor being used for this Hash Table
	  * 
	  * @return the HashFunctor being used by this Hash Table
	  */
	public HashFunctor getFunctor(){
		return functor;
	}
}
