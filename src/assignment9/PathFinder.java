package assignment9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Class that finds the shortest path from a starting point to a goal in a maze loaded from a file.
 * If a solution cannot be found, the file is output as it was.
 * <p>
 * Dimensions of the maze are provided on the first line in the format "Height Width" with the maze starting on the next line.
 * <p>
 * The rest of the file will represent the maze with the following values:
 * <font face="courier">
 * <ul> 
 * <li> 'X' = Wall
 * <li> ' ' = Space
 * <li> 'S' = Starting Point
 * <li> 'G' = Goal
 * <li> '.' = Solution Path
 * <ul>
 * </font>
 * <p>
 * 
 * @author Trevor Chapman
 * @author Matthew Canova
 */
public class PathFinder
{
	public static void solveMaze(String inputFile, String outputFile) throws IOException
	{
		Graph graph = readFile(inputFile); // reads in the file
		getSolution(graph); // finds the shortest path from the start to the goal
		outputFile(outputFile, graph); // outputs the file
	}
	
	/**
	 * Searches the maze for the shortest possible solution if it exists
	 * @param maze A graph containing a 2D array of nodes representing the graph
	 * @return the maze updated with the solution
	 */
	private static Graph getSolution(Graph maze)
	{
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.push(maze.getStart()); // add the starting node to the queue
		queue.peekFirst().setVisited(true);
		while(!queue.isEmpty())
		{
			Node currentNode = queue.removeFirst();
			
			// if the goal is found draw the solution and return
			if(currentNode.getValue() == 'G')
			{
				drawSolutionRecurse(currentNode.getCameFrom());
				return maze;
			}
			
			// adds all neighbor nodes to the queue as necessary and marks them as visited
			else
			{
				updateNeighbor(maze.getNode(currentNode.getRow() - 1, currentNode.getCol()), currentNode, queue);
				updateNeighbor(maze.getNode(currentNode.getRow() + 1, currentNode.getCol()), currentNode, queue);
				updateNeighbor(maze.getNode(currentNode.getRow(), currentNode.getCol() - 1), currentNode, queue);
				updateNeighbor(maze.getNode(currentNode.getRow(), currentNode.getCol() + 1), currentNode, queue);
			}
		}
		return maze; // if the entire maze is traversed and no solution is found, there are no solutions so return
	}
	
	/**
	 * Adds a neighboring node to the queue if they are a ' ', and marks them as visited
	 * @param neighbor The neighboring node
	 * @param cameFrom The node neighbor came from
	 * @param queue Queue to add the neighbor to
	 */
	private static void updateNeighbor(Node neighbor, Node cameFrom, LinkedList<Node> queue)
	{
		// if the node has already been visited or is an 'X', return
		if(neighbor.isVisited())
			return;
		
		if(neighbor.getValue() == 'X')
			return;
		
		// mark as visited,add to queue and update which node it came from
		neighbor.setVisited(true);
		queue.addLast(neighbor);
		neighbor.setCameFrom(cameFrom);
	}
	
	/**
	 * Recursively retraces a path from the goal to the start using the found shortest route. This method assumes 
	 * that the shortest route has already found, it simply modifies the file to display it.
	 * @param currentNode The node containing the goal
	 */
	private static void drawSolutionRecurse(Node currentNode)
	{
		if(currentNode.getValue() == 'S')
			 return;
		
		currentNode.setValue('.'); // change the node to a '.' representing part of the path
		drawSolutionRecurse(currentNode.getCameFrom());
	}
	
	/**
	 * Reads the file, adding each item to the graph
	 * @param inputFile The file to read from
	 * @throws IOException
	 */
	private static Graph readFile(String inputFile) throws IOException
	{
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		String[] dimensions = input.readLine().split(" ");

		// gets the dimensions of the file and creates a graph of that size
		int height = Integer.parseInt(dimensions[0]);
		int width = Integer.parseInt(dimensions[1]); 
		Graph graph = new Graph(height, width);
		
		// add nodes to the graph populated with the characters
		for (int j = 0; j < height; j++)
		{
			String[] line = input.readLine().split(""); // advance one line
			for (int i = 1; i <= width; i++) // since the delimiter is stored in the first index, we start at 1
			{
				// if the item is the starting point 'S' set it as such 
				if(line[i].charAt(0) == 'S') 
				{
					Node newNode = new Node('S');
					newNode.setRow(j);
					newNode.setCol(i-1);
					graph.setStart(newNode);
				}
				
				graph.setNode(j, i-1, line[i].charAt(0));
			}
		}
		
		input.close();
		return graph;
	}
	
	/**
	 * Outputs the solution to the maze
	 * @param outputFile The location to output the file to
	 */
	private static void outputFile(String outputFile, Graph graph)
	{
		try
		{
		    PrintWriter output = new PrintWriter(new FileWriter(outputFile));
		    output.println(graph.getRows() + " " + graph.getColumns()); // adds the dimensions
		    output.print(graph.toString()); // calls the toString method to fill out the rest of the graph
		    output.close();
		}
		catch(Exception e){};
	}
}

