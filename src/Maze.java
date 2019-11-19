import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Maze class starts off as a grid with all 0's (all walls up)
 *
 * Represents a Graph using adjacency-matrix representation to make-
 * -retrieving neighbors much more intuitive
 */
public class Maze {

    // 0 = wall, 1 = walls knocked down (i.e., edge)
    private int[][] adjMatrix;
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
     * Starts off with a grid, then uses DFS to generate a solvable maze
     */
    public void generateMaze() {
        // stack eliminates recursion
        Stack<Cell> cellStack = new Stack<>();
        int totalCells = getNumVertices() * getNumVertices();
        Cell currCell = new Cell(0, 0); // already "open-pathed" upon initialization of adj-matrix
        int visitedCells = 1; // visited 1 cell thus far

        while (visitedCells < totalCells) {
            // find all neighbors of currentCell with all walls intact
            ArrayList<Cell> neighbors = findAdjacentNeighbors(currCell);

            // choosing a random neighbor
            Random r = new Random();
            Cell neighbor = neighbors.get(r.nextInt(neighbors.size() - 1));

            if (getAdjMatrix()[neighbor.getX()][neighbor.getY()] == 0) {
                // Edge relationship is 2 Dimensional
                getAdjMatrix()[neighbor.getX()][neighbor.getY()] = 1;
                getAdjMatrix()[neighbor.getY()][neighbor.getX()] = 1;

                cellStack.push(currCell);
                currCell = new Cell(neighbor.getX(), neighbor.getY());
                visitedCells++;
            }
            else {
                // pop the most recent cell off stack and make it currCell
                currCell = cellStack.pop();
            }
        }
    }


    /**
     * Finds valid neighbors in the north, east, south, and west directions
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
        for(int i  = startPosX; i <= endPosX; i++) {
            for(int j = startPosY; j <= endPosY; j++) {
                // adding to a list of cells so that we can keep track of neighbor coordinates
                if(i != cell.getX() && j != cell.getY()) {
                    neighbors.add(new Cell(i, j));
                }
            }
        }
        return neighbors;
    }

    /**
     * Solves the maze with a greedy-search
     * NOTE: does not visit all vertices
     */
    public void solveDFS() {
        // to keep track of visit order
        ArrayList<Cell> dfsOrder = new ArrayList<>();
        // Stack for DFS eliminates recursion
        Stack<Cell> cellStack = new Stack<>();
        Cell currCell = new Cell(0, 0); // initially starting index
        cellStack.push(currCell);

        while(!cellStack.isEmpty()) {
            // Pop a vertex from stack and print it
            currCell = cellStack.pop();
            // Stack may contain the same vertex twice, only append if has not been visited
            if(!currCell.isVisited()) {
                currCell.setVisited();
                dfsOrder.add(currCell);
            }
            // pushing currCell's neighbors to the stack
            ArrayList<Cell> neighbors = findAdjacentNeighbors(currCell);
            for(Cell neighbor : neighbors) {
                if(!neighbor.isVisited()) {
                    cellStack.push(neighbor);
                }
            }
        }
    }
}
