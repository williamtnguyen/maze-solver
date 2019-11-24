import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MazeTest {

    Maze maze = new Maze(3);
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
        assertEquals(2, visitOrder.get(visitOrder.size() - 1).getX());
        assertEquals(2, visitOrder.get(visitOrder.size() - 1).getY());
        // Printing visit order
        for (Cell c : visitOrder) {
            System.out.println("X Coord: " + c.getX() + " Y Coord: " + c.getY());
        }
    }

    @Test
    public void solveBFS() {
        maze.generateMaze();
        List<Cell> visitOrder = maze.solveBFS().get(0);
        // Asserting BFS reached the finish point
        assertEquals(2, visitOrder.get(visitOrder.size() - 1).getX());
        assertEquals(2, visitOrder.get(visitOrder.size() - 1).getY());
        for (Cell c : visitOrder) {
            System.out.println("X Coord: " + c.getX() + " Y Coord: " + c.getY());
        }
    }

    @Test
    public void findNewNeighbors() {
        maze.generateMaze();
        for (int i = 0; i < maze.getNumVertices(); i++) {
            for (int j = 0; j < maze.getNumVertices(); j++) {
                List<Cell> edges = grid[i][j].getConnections();
                for (Cell edge : edges) {
                    System.out.print("X Coord: " + edge.getX() + " ");
                    System.out.println("Y Coord: " + edge.getY());
                }
            }
        }
    }
}