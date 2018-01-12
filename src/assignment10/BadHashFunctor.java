package assignment10;

/**
 * A bad functor for generating a Hash Code for Strings
 * 
 * @author Boqian Yao
 * @author Matthew Canova
 *
 */
public class BadHashFunctor implements HashFunctor  {

	/**
	 * Method that will return a hash code for the String provided
	 * 
	 * @param the String to generate a hash code from
	 * @return the hash code associated with this String
	 */
	@Override
	public int hash(String item) {
	
		return item.length();
	}

}
