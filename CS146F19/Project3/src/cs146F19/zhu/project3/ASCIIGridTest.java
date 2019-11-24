package cs146F19.zhu.project3;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ASCIIGridTest {

    private Maze maze = new Maze(6);
    private Maze maze2 = new Maze(6);
    private Cell[][] grid = maze.getGrid();
    private Cell[][] grid2 = maze2.getGrid();

    ASCIIGrid asciiGrid = new ASCIIGrid(maze);
    ASCIIGrid asciiGrid2 = new ASCIIGrid(maze2);
    MazePrinter printer = new MazePrinter();

    /* Works as expected, printing a fresh ASCII grid with all walls up */
    @Test
    public void generateASCIIGrid() {
        printer.printMaze(asciiGrid.generateASCIIGrid());
    }

    /* Works as expected, printing an ASCII grid with edge relationships */
    @Test
    public void updateASCIIGridWalls() {
        maze.generateMaze();
        printer.printMaze(asciiGrid.updateASCIIGridWalls());
    }

    /* Works as expected (and caused a lot of headache), properly displays DFS/BFS Traversal */
    @Test
    public void updateASCIIGridWithTraversalPath() {
        // DFS Display
        maze.generateMaze();
        System.out.println("Grid to be solved:");
        printer.printMaze(asciiGrid.updateASCIIGridWalls());

        System.out.print("\nSolved Grid using DFS:");
        ArrayList<Cell> visitOrder = maze.solveDFS();
        System.out.print("\n");
        printer.printMaze(asciiGrid.updateASCIIGridWithTraversalPath(visitOrder));
        System.out.print("\n");

        // BFS Display
        maze2.generateMaze();
        System.out.println("Grid to be solved:");
        printer.printMaze(asciiGrid2.updateASCIIGridWalls());

        System.out.print("\nSolved Grid using BFS:");
        ArrayList<Cell> visitOrder2 = maze2.solveBFS().get(0);
        System.out.print("\n");
        printer.printMaze(asciiGrid2.updateASCIIGridWithTraversalPath(visitOrder2));
    }

    /* Works as expected */
    @Test
    public void updateASCIIGridWithShortestPath() {
        maze.generateMaze();
        System.out.println("Grid to be solved:");
        printer.printMaze(asciiGrid.updateASCIIGridWalls());

        ArrayList<ArrayList<Cell>> traversals = maze.solveBFS();
        ArrayList<Cell> shortestOrder = traversals.get(1);
        System.out.print("\n");

        // Shortest Path Traversal
        System.out.println("Shortest Path Traversal:");
        printer.printMaze(asciiGrid.updateASCIIGridWithShortestPath(shortestOrder));
    }

    /* Works as expected */
    @Test
    public void printPath() {
        maze.generateMaze();
        System.out.println("Shortest Path Display:");
        asciiGrid.updateASCIIGridWalls();

        ArrayList<Cell> shortestPath = maze.solveBFS().get(1);
        printer.printMaze(asciiGrid.updateASCIIGridWithShortestPath(shortestPath));
        System.out.println(asciiGrid.getPath(shortestPath));
    }

    @Test
    public void printLength() {
    }

    @Test
    public void printVisited() {
    }
}