package assignment9;

/**
 * A class representing a node for usage in the PathFinder class
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 */
public class Node
{

	private boolean visited;
	private Node cameFrom;
	private char value;
	private int row;
	private int col;

	public Node(char value)
	{
		visited = false;
		cameFrom = null;
		this.value = value;
	}

	/**
	 * Returns which row the node is at
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * Sets the location the node is at
	 * @param row The row location
	 */
	public void setRow(int row)
	{
		this.row = row;
	}

	/**
	 * Returns the column the node is at 
	 */
	public int getCol()
	{
		return col;
	}

	/**
	 * Sets the column the node is at
	 * @param col The column location
	 */
	public void setCol(int col)
	{
		this.col = col;
	}

	/**
	 * Returns whether the node has been visited
	 */
	public boolean isVisited()
	{
		return visited;
	}

	/**
	 * Sets whether the node has been visited
	 * @param visited A boolean value describing whether this node has been visited
	 */
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}

	/**
	 * Returns the node this node came from
	 */
	public Node getCameFrom()
	{
		return cameFrom;
	}

	/**
	 * Sets the node this node came from
	 * @param cameFrom The node this node came from
	 */
	public void setCameFrom(Node cameFrom)
	{
		this.cameFrom = cameFrom;
	}

	/**
	 * Returns the character value located in this node
	 * @return
	 */
	public char getValue()
	{
		return value;
	}

	/**
	 * Sets the character value located at this node
	 */
	public void setValue(char value)
	{
		this.value = value;
	}
}
