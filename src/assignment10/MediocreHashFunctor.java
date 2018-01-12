package assignment10;

/**
 * A mediocre functor for generating a Hash Code for Strings
 * 
 * @author Boqian Yao
 * @author Matthew Canova
 *
 */
public class MediocreHashFunctor implements HashFunctor  {

	/**
	 * Method that will return a hash code for the String provided
	 * 
	 * @param the String to generate a hash code from
	 * @return the hash code associated with this String
	 */
	@Override
	public int hash(String item) {
	    
		char[] chars = item.toCharArray();
		int hashCode = 1;
		for (char c : chars)
			hashCode += c;
		
		return Math.abs(hashCode);
		
		
	
	}

}
