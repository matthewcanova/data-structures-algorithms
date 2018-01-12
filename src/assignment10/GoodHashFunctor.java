package assignment10;

/**
 * A good functor for generating a Hash Code for Strings
 * 
 * @author Boqian Yao
 * @author Matthew Canova
 * 
 */
public class GoodHashFunctor implements HashFunctor {

	/**
	 * Method that will return a hash code for the String provided
	 * 
	 * @param the
	 *            String to generate a hash code from
	 * @return the hash code associated with this String
	 */
	@Override
	public int hash(String item) {
		long hashCode = 0l;
		char[] c = item.toCharArray();
		for (int i = 0; i < c.length; i++) {
			hashCode += c[i] * Math.pow(31, c.length - (i + 1));
		}
		int i = (int) hashCode % 2147483647;

	
		return Math.abs(i);
		
	}
}
