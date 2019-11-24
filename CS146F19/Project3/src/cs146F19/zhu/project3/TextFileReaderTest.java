package cs146F19.zhu.project3;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.*;

import static org.junit.Assert.*;

//WIP haha... ha... hah.
public class TextFileReaderTest {


    private Cell[][] grid;
    private Maze maze;
    private Cell[][] grid2;
    private Maze maze2;

	private File m4 =new File("sample-inputs/maze4.txt");
	private	File m6 =new File("sample-inputs/maze6.txt");
	private	File m8 =new File("sample-inputs/maze8.txt");
	private	File m10 =new File("sample-inputs/maze10.txt");
	private	File m20 =new File("sample-inputs/maze20.txt");
	
	private TextFileReader t4 = new TextFileReader(m4);
	private	TextFileReader t6 = new TextFileReader(m6);
	private	TextFileReader t8 = new TextFileReader(m8);
	private	TextFileReader t10 = new TextFileReader(m10);
	private	TextFileReader t20 = new TextFileReader(m20);
    
    ASCIIGrid asciiGrid;
    ASCIIGrid asciiGrid2;
    MazePrinter printer = new MazePrinter();
    
    
//    @Test
//    public void 

    @Before
    public void initializiation() throws Exception {
    	t4.copyToList();
    	t4.listToASCIIGrid();
    	t4.ASCIIGridToCellGrid();
    	grid=t4.getCellGrid();
//    	printer.printMaze(t4.getASCIIGrid());
    }
    
    @Test
    public void readTextFile() throws Exception {
    	t4.copyToList();
    	t4.listToASCIIGrid();
    	t4.ASCIIGridToCellGrid();
    	System.out.println("readTextFile String[][] ASCII Grid:");
    	printer.printMaze(t4.getASCIIGrid());
    }

    /* Works as expected (and caused a lot of headache), properly displays DFS/BFS Traversal */
    @Test
    public void updateASCIIGridWithTraversalPath() {
        // DFS Display
        maze = new Maze(4);
        maze.replaceCellGrid(grid);
		maze.replaceStartAndFinishCell(grid);
		asciiGrid = new ASCIIGrid(maze, t4.getASCIIGrid());
        System.out.println("Grid to be solved:");
        printer.printMaze(t4.getASCIIGrid());

        System.out.print("\nSolved Grid using DFS:");
        ArrayList<Cell> visitOrder = maze.solveDFS();
        System.out.print("\n");
        printer.printMaze(asciiGrid.updateASCIIGridWithTraversalPath(visitOrder));
        System.out.print("\n");

        // BFS Display
        maze2= new Maze(4);
        maze2.replaceCellGrid(grid);
		maze2.replaceStartAndFinishCell(grid);
		printer.printMaze(t4.getASCIIGrid());
		asciiGrid2 = new ASCIIGrid(maze2, t4.getASCIIGrid());
//        System.out.println("Grid to be solved:");
//        printer.printMaze(t4.getASCIIGrid());
//
//        System.out.print("\nSolved Grid using BFS:");
//        ArrayList<Cell> visitOrder2 = maze2.solveBFS().get(0);
//        System.out.print("\n");
//        printer.printMaze(asciiGrid2.updateASCIIGridWithTraversalPath(visitOrder2));
    }
//
//    /* Works as expected */
//    @Test
//    public void updateASCIIGridWithShortestPath() {
//        maze.generateMaze();
//        System.out.println("Grid to be solved:");
//        printer.printMaze(asciiGrid.updateASCIIGridWalls());
//
//        ArrayList<ArrayList<Cell>> traversals = maze.solveBFS();
//        ArrayList<Cell> shortestOrder = traversals.get(1);
//        System.out.print("\n");
//
//        // Shortest Path Traversal
//        System.out.println("Shortest Path Traversal:");
//        printer.printMaze(asciiGrid.updateASCIIGridWithShortestPath(shortestOrder));
//    }
//
//    /* Works as expected */
//    @Test
//    public void printPath() {
//        maze.generateMaze();
//        System.out.println("Shortest Path Display:");
//        asciiGrid.updateASCIIGridWalls();
//
//        ArrayList<Cell> shortestPath = maze.solveBFS().get(1);
//        printer.printMaze(asciiGrid.updateASCIIGridWithShortestPath(shortestPath));
//        asciiGrid.printPath(shortestPath);
//    }
//
//    @Test
//    public void printLength() {
//    }
//
//    @Test
//    public void printVisited() {
//    }
}