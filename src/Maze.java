import java.util.ArrayList;

public class Maze {
    // Using adjacency-matrix representation to make obtaining neighbors
    // of a cell much more intuitive
    private int[][] adjMatrix; // 0 = wall, 1 = open path (i.e., walls knocked down)
    private int numVertices;

    /**
     * Representing the Maze as a graph data structure.
     */
    public Maze(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices]; // Maze is of size r * r

        // Marking starting and finishing points as open paths
        adjMatrix[0][0] = 1;
        adjMatrix[numVertices - 1][numVertices - 1] = 1;
    }


    /* Standard accessor methods for encapsulation */
    public int[][] getAdjMatrix() { return this.adjMatrix; }
    public int getNumVertices() { return this.numVertices; }


    /**
     *
     * @return a list of coordinate pairs in the adj-matrix
     */
    public ArrayList<Cell> findAdjacentNeighbors(Cell cell) {

        // list of adjacent neighbors
        ArrayList<Cell> neighbors = new ArrayList<>();

        // Conditionals to set valid values of starting and ending values for i and j of the loop
        int startPosX = ((cell.getX() - 1) < 0) ? cell.getX() : cell.getX() - 1;
        int endPosX = ((cell.getX() +  1) > (numVertices - 1)) ? cell.getX() : cell.getX() + 1;
        int startPosY = ((cell.getY() - 1) < 0) ? cell.getY() : cell.getY() - 1;
        int endPosY = ((cell.getY() + 1 > (numVertices - 1))) ? cell.getY() : cell.getY() + 1;

        // grid[i][j] are the neighbors of the current cell
        for(int i  = startPosX; i < endPosX; i++) {
            for(int j = startPosY; j < endPosY; j++) {
                // adding to a list of cells so that we can keep track of individual coordinates
                neighbors.add(new Cell(i, j));
            }
        }
        return neighbors;
    }
}
