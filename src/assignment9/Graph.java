package assignment9;

/**
 * Class representing a Graph structure. Uses a matrix as the underlying data
 * structure, so Node links are implicit to the left, right, up, and down
 * neighbors. There are no diagonal links.
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 *
 */
public class Graph
{
	private Node[][] graph;
	private int rows;
	private int columns;
	private Node start;

	public Graph(int rows, int col)
	{
		graph = new Node[rows][col];
		this.columns = col;
		this.rows = rows;
	}
	
	/**
	 * Returns the starting node of the graph
	 */
	public Node getStart()
	{
		return start;
	}

	/**
	 * Sets the starting node of the graph
	 * @param start The starting node
	 */
	public void setStart(Node start)
	{
		this.start = start;
	}

	/**
	 * Updates a node at the provided location
	 * @param row The row the node is located at
	 * @param col The column the node is located at
	 * @param item The value to update the node
	 * @return True if it was updated, false if not
	 */
	public boolean setNode(int row, int col, char item)
	{
		// if index is out of bounds return false
		if ((row > getRows() || row < 0) || (col > getColumns() || col < 0))
			return false;

		// otherwise, update with new Node and return true
		Node newNode = new Node(item);
		graph[row][col] = newNode;
		newNode.setRow(row);
		newNode.setCol(col);

		return true;
	}

	/**
	 * Returns the number of rows in the graph
	 * @return
	 */
	public int getRows()
	{
		return graph.length;
	}

	/**
	 * Returns the number of columns in the graph
	 * @return
	 */
	public int getColumns()
	{
		return graph[0].length;
	}

	/**
	 * Returns the node at the provided location
	 * @param row The row the node is located at
	 * @param col The column the node is located at
	 */
	public Node getNode(int row, int col)
	{
		return graph[row][col];
	}

	/**
	 * Returns whether the node has been visited
	 * @param row The row the node is located at
	 * @param col The column the node is located at
	 * @return
	 */
	public boolean isVisited(int row, int col)
	{
		return graph[row][col].isVisited();
	}

	/**
	 * Returns which node the provided node came from
	 * @param row The row the node is located at
	 * @param col The column the node is located at
	 * @return
	 */
	public Node cameFrom(int row, int col)
	{
		return graph[row][col].getCameFrom();
	}

	/**
	 * Returns the value of the node at the provided location
	 * @param row The row the node is located at
	 * @param col The column the node is located at
	 * @return
	 */
	public char getValue(int row, int col)
	{
		return graph[row][col].getValue();
	}

	/**
	 * Outputs the graph to a string
	 */
	public String toString()
	{
		String matrix = new String();

		// populate the String with our values
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
				matrix += graph[i][j].getValue();
			
			// new line after each row
			matrix += '\n';
		}

		return matrix;
	}
}
