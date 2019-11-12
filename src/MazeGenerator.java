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
        int currCell = maze.getAdjMatrix()[0][0];
        int visitedCells = 1; // visited 1 cell thus far

        while(visitedCells < totalCells) {
            // find all neighbors of currentCell with all walls intact
            for(int i = currCell; i < maze.getAdjMatrix().length; i++) {
                ArrayList<Cell> neighbors = maze.findAdjacentNeighbors();
            }
        }
    }
}
