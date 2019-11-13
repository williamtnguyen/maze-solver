import java.util.ArrayList;
import java.util.Stack;

public class MazeGenerator {

    /**
     * Starts off with a grid, then uses DFS to generate a solvable maze
     * @return generated maze
     */
    public Maze generateMaze(Maze maze) {

        // stack eliminates recursion
        Stack<Cell> cellStack = new Stack<>();
        int totalCells = (int) Math.pow(maze.getNumVertices(), 2);
        Cell currCell = new Cell(0, 0); // already "open-pathed" upon initialization of adj-matrix
        int visitedCells = 1; // visited 1 cell thus far

        while(visitedCells < totalCells) {
            // find all neighbors of currentCell with all walls intact
            ArrayList<Cell> neighbors = maze.findAdjacentNeighbors(currCell);
            for(Cell neighbor : neighbors) {
                if(maze.getAdjMatrix()[neighbor.getX()][neighbor.getY()] == 0) {
                    maze.getAdjMatrix()[neighbor.getX()][neighbor.getY()] = 1;
                    cellStack.push(currCell);
                    currCell = new Cell(neighbor.getX(), neighbor.getY());
                    visitedCells++;
                    break;
                }
                else {
                    // pop the most recent cell off stack and make it currCell
                    currCell = cellStack.pop();
                }
            }
        }
    }
}
