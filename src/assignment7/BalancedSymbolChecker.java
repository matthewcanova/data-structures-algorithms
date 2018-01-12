package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 */
public class BalancedSymbolChecker
{

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public static String checkFile(String filename) throws FileNotFoundException
	{
		// stack for matching brackets/braces/parentheses
		MyStack<Character> stack = new MyStack<Character>();
		Scanner scanner = new Scanner(new File(filename));
		
		// ints for tracking the column and line number
		int colNum = 1, lineNum = 0;
		
		// booleans for tracking comment blocks, strings, and characters
		boolean isCommentBlock = false;
		boolean isString = false;
		boolean isChar = false;
		
		char topOfStack; // hold char when we pop()
		
		String character; // hold's next line
		char[] characterArray; // holds character array of the line
		
		while(scanner.hasNextLine())
		{
			character = scanner.nextLine(); // grab next line
			characterArray = character.toCharArray(); // convert to character array
			lineNum++; // increment our line number
			colNum = 1; // new line, so reset column count
			
			// traverse the characterArray that represents the current line
			for(int i = 0; i < characterArray.length; i++)
			{
				// check if we are in a multi-line comment block, String, or character
				if (isCommentBlock == true && characterArray[i] != '/' ||
						  isString == true && characterArray[i] != '"' ||
					    	isChar == true && characterArray[i] != '\'')
				{
					// check for a character escape
					if(characterArray[i] == '\\')
					{
						i++;      // skip the next char
						colNum++; // increment to account for skipped char
					}
					
					colNum++; // increment to count this char
					continue;
				}
				
				else 
				{
					switch(characterArray[i])
					{
						// if opening character is found add to stack
						case '{':
						case '[':
						case '(':
							stack.push(characterArray[i]);
							break;
							
						// check for open or close of comment, string, or char block	
						case '/':
							// check for single-line comment
							if(i != characterArray.length-1 && characterArray[i+1] == '/')
								i = characterArray.length; // move to next line
							// check for open of multi-line comment
							else if(i != characterArray.length -1 && characterArray[i+1] == '*')
								isCommentBlock = true;
							// check for close of multi-line comment
							else if(characterArray[i-1] == '*')
								isCommentBlock = false;
							break;
						case '"':
							// check for open of String
							if(isString == true)
								isString = false;
							else
								isString = true;
							break;
						case '\'':
							// check for open of char
							if(isChar == true)
								isChar = false;
							else
								isChar = true;
							break;
						
						// if closing char is found, check stack and return errors, if appropriate 
						case '}':
							// no char available to match with
							if (stack.isEmpty())
								return unmatchedSymbol(lineNum, colNum, characterArray[i], ' ');
							
							topOfStack = stack.pop();
							// char at top of stack does not match 
							if(topOfStack != '{')
								return unmatchedSymbol(lineNum, colNum, characterArray[i], oppositeChar(topOfStack));
							break;
						case ']':
							// no char available to match with
							if (stack.isEmpty())
								return unmatchedSymbol(lineNum, colNum, characterArray[i], ' ');
							
							topOfStack = stack.pop();
							// char at top of stack does not match 
							if(topOfStack != '[')
								return unmatchedSymbol(lineNum, colNum, characterArray[i], oppositeChar(topOfStack));
							break;
						case ')':
							// no char available to match with
							if (stack.isEmpty())
								return unmatchedSymbol(lineNum, colNum, characterArray[i], ' ');
							
							topOfStack = stack.pop();
							// char at top of stack does not match
							if(topOfStack != '(')
								return unmatchedSymbol(lineNum, colNum, characterArray[i], oppositeChar(topOfStack));
							break;
					}	
				}
				colNum++; // increment for each char
			}
		}
		
		// check for unfinished comment block
		if (isCommentBlock)
			return unfinishedComment();
		
		// check for unmatched char
		if (!stack.isEmpty())
			return unmatchedSymbolAtEOF(oppositeChar(stack.pop()));
		
		// return for no errors found
		return allSymbolsMatch();
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber,
			char symbolRead, char symbolExpected)
	{
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column "
				+ colNumber + ". Expected " + symbolExpected + ", but read "
				+ symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected)
	{
		return "ERROR: Unmatched symbol at the end of file. Expected "
				+ symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private static String unfinishedComment()
	{
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private static String allSymbolsMatch()
	{
		return "No errors found. All symbols match.";
	}
	
	/**
	 * Returns the opposite of the {} [] and () characters.
	 * Any other chars will return '0'.
	 * 
	 * @param c The character to find the opposite of
	 * @return - returns the opposite of the provided char, if not a valid char for this method, returns '0'.
	 * 
	 */
	private static char oppositeChar(char c)
	{
		switch (c)
		{
			case '(':
				return ')';
			case '{':
				return '}';
			case '[':
				return ']';
			case ')':
				return '(';
			case '}':
				return '{';
			case ']':
				return '[';
		}
		return '0';
	}
}