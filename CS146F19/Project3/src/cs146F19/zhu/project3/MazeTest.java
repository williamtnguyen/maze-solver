package cs146F19.zhu.project3;

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

    /* Works as expected */
    @Test
    public void addEdge() {
        Cell test1 = grid[0][0];
        Cell test2 = grid[0][1];
        Cell test3 = grid[0][2];

        maze.addEdge(test1, test2);
        maze.addEdge(test2, test3);

        assertTrue(test1.getWestWall());
        assertFalse(test1.getEastWall());
        assertFalse(test2.getWestWall()); // test2 Cell has an edge with test1 Cell in West direction
        assertFalse(test2.getEastWall()); // test2 Cell has an edge with test3 Cell in East direction
        assertTrue(test3.getEastWall());
    }
}
