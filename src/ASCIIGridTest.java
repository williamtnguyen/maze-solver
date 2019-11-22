import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ASCIIGridTest {

    Maze maze = new Maze(4);
    ASCIIGrid asciiGrid = new ASCIIGrid(maze);

    @Test
    public void generateASCIIGrid() {
    }

    @Test
    public void updateASCIIGrid() {

    }

    @Test
    public void updateAsciiGridWithPath() {
        int index = 0; // to get the next element on top of array (FIFO order)

        // Call this before you call maze.generateMaze() because if you do it after, grid will have been modified already
        asciiGrid.generateASCIIGrid();

        // Knocks walls down and updates grid to a maze with 1 solution
        maze.generateMaze();

        // Call this after "maze.generateMaze()" because that updates the ASCII grid with knocked down walls
        asciiGrid.updateASCIIGrid();

        // ArrayLists containing the visit order of the traversal. Only test one at a time, this is just for example
        ArrayList<Cell> dfsVisitOrder = maze.solveDFS();
        ArrayList<Cell> bfsVisitOrder = maze.solveBFS();

        // Whatever you write for this method, call it like this in a main method to test it
        asciiGrid.updateASCIIGridWithPath(dfsVisitOrder); // pass the arrList returned by solveDFS() as a param

        // Todo: print accordingly

    }
}