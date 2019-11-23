package cs146F19.zhu.project3;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MazeTest {

    Maze maze = new Maze(4);
    Cell[][] grid = maze.getGrid();

    /* Testing if the method compiles properly */
    @Test
    public void generateMaze() {
        maze.generateMaze();
    }


    @Test
    public void solveDFS() {
        maze.generateMaze();
        List<Cell> visitOrder = maze.solveDFS();
        // Asserting DFS reached the finish point
        assertEquals(3, visitOrder.get(visitOrder.size() - 1).getX());
        assertEquals(3, visitOrder.get(visitOrder.size() - 1).getY());
        // Printing visit order
        for(Cell c : visitOrder) {
            System.out.println("X Coord: " + c.getX() + " Y Coord: " + c.getY());
        }
    }

    @Test
    public void solveBFS() {
        maze.generateMaze();
        List<Cell> visitOrder = maze.solveBFS();
        // Asserting BFS reached the finish point
        assertEquals(3, visitOrder.get(visitOrder.size() - 1).getX());
        assertEquals(3, visitOrder.get(visitOrder.size() - 1).getY());
        for(Cell c : visitOrder) {
            System.out.println("X Coord: " + c.getX() + " Y Coord: " + c.getY());
        }
    }

    @Test
    public void findNewNeighbors() {
    }
}