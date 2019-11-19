/**
 * This class handles the displaying of unsolved mazes,
 * -and the printing of paths for DFS/BFS solutions
 */
public class MazePrinter {

    private Maze maze;
    private int[][] adjMatrix;

    public MazePrinter(Maze maze) {
        this.maze = maze;
        this.adjMatrix = this.maze.getAdjMatrix();
    }

    /**
     * Handles displaying the unsolved maze
     */
    public void displayGraph() {
        for(int i = 0; i < adjMatrix.length; i++) {
            for(int j = 0; j < adjMatrix[0].length; j++) {
                // idk idk idk idk
            }
        }
    }

    /**
     * Prints DFS visit order on the simulated maze
     */
    public void printVisitOrderDFS() {

    }

    /**
     * Prints BFS visit order on the simulated maze
     */
    public void printVisitOrderBFS() {

    }

    /**
     * Prints the shortest-path from start to finish
     */
    public void printPath() {

    }
}
