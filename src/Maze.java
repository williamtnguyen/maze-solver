import java.util.ArrayList;

public class Maze {
    // Using adjacency-matrix representation to make obtaining neighbors
    // of a cell much more intuitive
    private int adjMatrix[][]; // 0 = wall, 1 = open road
    private int numVertices;

    /**
     * Representing the Maze as a graph data structure.
     */
    public Maze(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices]; // Maze is of size r * r

        // Marking starting and finishing endpoints as having no walls
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
        ArrayList<Cell> neighbors = new ArrayList<>();

        // method body

        return neighbors;
    }

}
