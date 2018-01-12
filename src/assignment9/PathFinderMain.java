package assignment9;

import java.io.IOException;

/**
 * A main file to run the PathFinder class on several mazes to find their solutions
 * 
 * @author Matthew Canova
 * @author Trevor Chapman
 */
public class PathFinderMain
{
	public static void main(String[] args) throws IOException
	{
		PathFinder.solveMaze("demoMaze.txt", "demoMazeSolution.txt");
		PathFinder.solveMaze("bigMaze.txt", "bigMazeSolution.txt");
		PathFinder.solveMaze("bigMazeEmpty.txt", "bigMazeEmptySolution.txt");
		PathFinder.solveMaze("classic.txt", "classicSolution.txt");
		PathFinder.solveMaze("mediumMaze.txt", "mediumMazeSolution.txt");
		PathFinder.solveMaze("randomMaze.txt", "randomMazeSolution.txt");
		PathFinder.solveMaze("straight.txt", "straightSolution.txt");
		PathFinder.solveMaze("tinyMaze.txt", "tinyMazeSolution.txt");
		PathFinder.solveMaze("tinyOpen.txt", "tinyOpenSolution.txt");
		PathFinder.solveMaze("turn.txt", "turnSolution.txt");
		PathFinder.solveMaze("unsolvable.txt", "unsolvableSolution.txt");
		PathFinder.solveMaze("straightMazeMedium.txt", "straightMazeMediumSolution.txt");
		PathFinder.solveMaze("straightMazeLarge.txt", "straightMazeLargeSolution.txt");
	}
}
