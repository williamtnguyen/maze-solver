import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTest {

    // 4 x 4 maze
    private Maze maze = new Maze(4);
    private Cell[][] grid = maze.getGrid();

    @Test
    public void generateMaze() {
    }

    @Test
    public void solveDFS() {
    }

    @Test
    public void solveBFS() {
    }

    @Test
    public void findAdjacentNeighbors() {
    }

    @Test
    public void addEdge() {
        Cell test1 = grid[0][0];
        Cell test2 = grid[0][1];
        Cell test3 = grid[0][2];

        maze.addEdge(test1, test2);
        maze.addEdge(test2, test3);

        assertFalse(test2.getNorthWall()); // test2 Cell has an edge with test1 Cell in North direction
        assertFalse(test2.getSouthWall()); // test2 Cell has an edge with test3 Cell in South direction
    }
}