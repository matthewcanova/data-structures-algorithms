package assignment1;

/**
 * MatrixTester is a class that will contain our main method and
 * a suite of tests for assessing the Matrix class and it's methods. 
 * 
 * @author Matthew Canova
 */

public class MatrixTester {
	public static void main(String[] args)
	{		
		// this matrix will be manipulated for testing
		Matrix M1 = new Matrix(new int[][]
		                                 {{1, 2, 3},
										  {2, 5, 6}});
		
		// this matrix will be used for testing the times method with M1 and
		// will be the incompatible matrix for adding to M1.
		Matrix M2 = new Matrix(new int[][]
		                                 {{4, 5},
										  {3, 2},
										  {1, 1}});
		
		// this matrix will be used for testing the plus method with M1 and 
		// will be the incompatible matrix for multiplying with M1.
		Matrix M3 = new Matrix(new int[][]
										 {{1, 1, 1},
										  {1, 1, 1}});

		
		// this is the known correct result of multiplying M1 by M2
		Matrix referenceMultiply = new Matrix(new int[][]
		                                                {{13, 12},
														 {29, 26}});
		
		// this is the known correct result of adding M1 to M3
		Matrix referenceAdd = new Matrix(new int[][]			
													{{2, 3, 4},
													 {3, 6, 7}});

		/** 
		 * Test: Matrix()
		 * 
		 * Below are all tests for the implementation of the Matrix class. 
		 * This code will exercise all methods. 
		 */
		System.out.println("----------\n" + "Test Suite\n" + "----------\n");		
		
	
		/** 
		 * Test: equals()
		 * 
		 * Exercises the equals method with known matrices
		 */
		System.out.println("--------\n" + "equals\n" + "--------\n");
		Matrix equalsM1 = new Matrix(new int[][]
												{{1, 2, 3},
					  							{2, 5, 6}});
		Matrix doesNotEqualM1 = new Matrix(new int[][]
													{{1, 2, 3},
													{2, 5, 7}});
		// test two Matrices that are equal
		System.out.println("If the 'equals' method works, this should be true: " + M1.equals(equalsM1));
		// test two Matrices that are not equal
		System.out.println("If the 'equals' method works, this should be false: " + M1.equals(doesNotEqualM1) + "\n");
		
		
		/** 
		 * Test: toString()
		 * 
		 * Exercises the toString() method with known matrices
		 */
		System.out.println("--------\n" + "toString\n" + "--------");
		System.out.println("M1.toString():\n" + M1);
		System.out.println("M2.toString():\n" + M2);
		System.out.println("M3.toString():\n" + M3);
				
		
		/** 
		 * Test: times()
		 * 
		 * Exercises the times() method 
		 */
		System.out.println("--------\n" + "times\n" + "--------");
		// times M1 by M2
		Matrix computedMultiply = M1.times(M2);
		// print the resulting Matrix
		System.out.println("M1 times M2:\n");
		System.out.println("" + computedMultiply.toString());
		// use the equals method with referenceMultiply to validate that results match expected results
		System.out.println("If 'times' method works, this should be true: " + 
							computedMultiply.equals(referenceMultiply) + "\n");
		// test that trying to multiply matrices that are incompatible with multiplication returns null
		Matrix incompatibleTimes = M1.times(M3);
		if (incompatibleTimes == null)
			System.out.println("This method correctly returned null when provided matrices incompatible for multiplication\n");		
		
		
		/** 
		 * Test: plus()
		 * 
		 * exercise the plus() method, and make sure that your computed result is the same as the known result 
		 */
		System.out.println("--------\n" + "plus\n" + "--------");
		// add M3 to M1
		Matrix computedAdd = M1.plus(M3);
		// print the resulting Matrix
		System.out.println("M1 plus M3:\n");
		System.out.println("" + computedAdd.toString());
		// use the equals method with reference Add to validate that results match expected results
		System.out.println("If 'plus' method works, this should be true: " + 
							computedAdd.equals(referenceAdd) + "\n");
		//test that trying to add matrices that are incompatible with addition returns null
		Matrix incompatiblePlus = M1.plus(M2);
		if (incompatiblePlus == null)
			System.out.println("This method correctly returned null when provided matrices incompatible for addition\n");
		
		
		/**
		 * Edge Cases
		 * 
		 * test small, large, and "jagged edge" matrices
		 */
		
		System.out.println("--------\n" + "edge cases\n" + "--------");

		// 1x1 matrix
		System.out.println("Smallest matrix:\n");
		Matrix small = new Matrix(new int[][]{{1}});
		System.out.println(small);
		
		// larger matrix
		System.out.println("Larger matrix:\n");
		int[][] big = new int[50][50];
		for(int i=0; i < 50; i++)
		{
			for(int j=0; j < 50; j++)
			{
				big[i][j] = 3;
			}
		}
		Matrix bigMatrix = new Matrix(big);
		System.out.println(bigMatrix);
		
		// test that an array object without matrix-valid data ("jagged edges")
		// throws the appropriate exception on instantiation as a Matrix
		System.out.println("Array without matrix-valid data:\n");
		try 
		{
		int[][] badData = new int[][]{{1,2,3},
									  {4}};
		Matrix badDataMatrix = new Matrix(badData);
		} 
		catch(Exception e) 
		{
			System.out.println("This threw the appropriate exception");
		}
	}
}