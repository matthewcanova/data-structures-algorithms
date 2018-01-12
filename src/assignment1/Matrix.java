package assignment1;

/**
 * Matrix is a class representing a mathematical Matrix. 
 * <p>
 * It accepts an int[][] in order to generate the Matrix
 * and then provides overwritten equals and toString methods. 
 * <p>
 * Additionally, methods are provided for multiplying this 
 * Matrix with another Matrix (times) and for adding this 
 * Matrix to another Matrix (plus). 
 * 
 * @author               Matthew Canova
 * @param  numRows       the number of Rows that the instantiated Matrix has
 * @param  numColumns    the number of Columns that the instantiated Matrix has
 * @param  data          the int[][] that holds the values representing the Matrix
 */

public class Matrix {
	
	int numRows;
	int numColumns;
	int data[][];
	
	/**
	 * Constructor with data for new matrix (automatically determines dimensions)
	 * 
	 * @param  d            the data used to create a new matrix
	 */
	public Matrix(int d[][])  
	{
		numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; // d[0] is the first 1D array
		data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}
	
	/**
	 * Returns a boolean value based on whether the Object is equal to this.
	 * The method checks for whether the Object is of type Matrix, and if true,
	 * whether the dimensions of the two Matrices are the same and whether the 
	 * values are the same. Any failure will return false. If all tests pass,
	 * returns true.
	 * 
	 * @param  o       the object passed for comparison
	 * @return boolean returns true if the Object is equal to this
	 */
	
	@Override // Override the superclass' (Object) version of equals
	public boolean equals(Object o)
	{
		// make sure the Object we're comparing to is a Matrix, if not, go ahead and return false
		if (!(o instanceof Matrix))
				return false;
		// if the above was not true, we know it's safe to treat 'o' as a Matrix
		Matrix m = (Matrix) o; 
		// make sure that the now identified Matrix has the same dimensions as this, 
		// if not, return false
		if(this.numColumns != m.numColumns || this.numRows != m.numRows) 
			return false;
		// if the Object is a Matrix and it has the same dimensions as this, compare all values
		// and if any do not match, return false
		for (int i = 0; i < numRows; i++) 
		{
			for (int j = 0; j < numColumns; j++) 
			{
				if (this.data[i][j] != m.data[i][j])
						return false;
			}
		}
		return true; // if all tests for equality have passed, return true
	}	
	
	/**
	 * Returns a printable String that represents this matrix, 
	 * where a value is equal to [row][column], example:
	 * 
	 * 1 2 3
	 * 4 5 6
	 * 7 8 9 
	 * 
	 * Each value will have a trailing space and each row will
	 * end with a new line.
	 * 
	 * @return String returns a String containing values and styling representing this Matrix
	 */
	
	@Override // Override the superclass' (Object) version of toString
	public String toString()
	{
		String printableString = "";
		for(int i=0; i < numRows; i++)
		{
			for(int j=0; j < numColumns; j++)
			{
				// traverse data and add values to string with a trailing space
				printableString = printableString + data[i][j] + " "; 
			}
			// after each row completes, add a new line
			printableString = printableString + "\n";
		}
		return printableString; // once all values and styling have been added, return the String
	}
	
	/**
	 * Returns a Matrix that is the product of this Matrix and the passed 
	 * Matrix 'm'. 
	 * 
	 * @param  m      the matrix that this will be multiplied by
	 * @return Matrix the matrix that represents the product of this Matrix and Matrix 'm'
	 */
	
	public Matrix times(Matrix multiplicand)
	{
		Matrix leftMatrix = this;
		Matrix rightMatrix = multiplicand;
		int[][] productArrays = new int[leftMatrix.numRows][rightMatrix.numColumns];
		if(leftMatrix.numColumns != rightMatrix.numRows) 
			return null; // if matrices are not compatible for multiplication, return null
		else 
		{
			// traverse the productArrays indices
			for(int i=0; i < productArrays.length; i++)
			{
				for(int j=0; j < productArrays[0].length; j++)
				{
					// for each productArrays index location, calculate the product value (ijValue)
					int ijValue = 0;
						for(int k = 0; k < leftMatrix.numColumns; k++)
						{
							ijValue = ijValue + leftMatrix.data[i][k] * rightMatrix.data[k][j];
						}
					productArrays[i][j] = ijValue; // store ijValue in productArrays
				}
			}
		}
		Matrix productMatrix = new Matrix(productArrays); // create a new Matrix using productArrays
		return productMatrix; // return the new Matrix
	}
	
	/**
	 * Returns a Matrix that is the sum of this Matrix and the passed 
	 * Matrix 'm'. 
	 * 
	 * @param  m      the matrix that will be added to this
	 * @return Matrix the matrix that represents the sum of this Matrix and Matrix 'm'
	 */
	
	public Matrix plus(Matrix operand)
	{
		Matrix leftMatrix = this;
		Matrix rightMatrix = operand;
		// check that matrices are compatible for addition
		if(leftMatrix.numColumns == rightMatrix.numColumns && leftMatrix.numRows == rightMatrix.numRows)
		{
			int[][] sumArrays = new int[leftMatrix.numRows][leftMatrix.numColumns];
			for(int i=0; i < leftMatrix.numRows; i++)
			{
				for(int j=0; j < leftMatrix.numColumns; j++)
				{
					// add values from input arrays and store in sumArrays
					sumArrays[i][j] = leftMatrix.data[i][j] + rightMatrix.data[i][j];
				}
			}
			Matrix sumMatrix = new Matrix(sumArrays); // create a new Matrix using sumArrays
			return sumMatrix; // return the new Matrix
		}
		else 
			return null; // if matrices are not compatible for addition, return null
	}	
}