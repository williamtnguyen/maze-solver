import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    Maze maze = new Maze(3);
    Cell[][] grid = maze.getGrid();

    /* Works as expected, an initialized maze should have all walls intact */
    @Test
    public void allWallsIntact() {
        for(int i = 0; i < maze.getNumVertices(); i++) {
            for(int j = 0; j < maze.getNumVertices(); j++) {
                assertTrue(grid[i][j].allWallsIntact());
            }
        }
    }

    /* Works as expected */
    @Test
    public void hasEdge() {
        grid[0][0].addEdge(grid[0][1]);
        assertTrue(grid[0][0].hasEdge(grid[0][1]));
    }


}