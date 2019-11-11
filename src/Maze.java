public class Maze {
    // Using adjacency-matrix representation to make obtaining neighbors
    // of a cell much more intuitive
    private int adjMatrix[][];
    private int numVertices;

    /**
     * Representing the Maze as a graph data structure.
     */
    public Maze(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices]; // Maze is of size r * r
    }

    public int[][] getAdjMatrix() {
        return this.adjMatrix;
    }

    public int getNumVertices() {
        return this.numVertices;
    }

}
