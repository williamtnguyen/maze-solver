import java.util.*;

/**
 * Maze class starts off as a grid with all 0's (all walls up)
 *
 * Represents a Graph using adjacency-matrix representation to make-
 * -retrieving neighbors much more intuitive
 */
public class Maze {
    // 2D Array of Cells starts off as a Grid
    private final int numVertices;
    private Cell[][] grid;
    private ArrayList<LinkedList<Cell>> adjList;


    public Maze(int numVertices) {
        this.numVertices = numVertices;

        // Initializing & filling the grid with Cells of proper information
        grid = new Cell[this.numVertices][this.numVertices];
        fillGrid(this.grid);

        // Initializing the ArrayList and it's inner LinkedLists
        this.adjList = new ArrayList<>();
        for(int i = 0; i < numVertices; i++) { this.adjList.add(new LinkedList<>()); }
    }

    /**
     * Maze Generation Algorithm using DFS
     */
    public void generateMaze() {
        // Stack eliminates recursion: holds cell locations
        Stack<Cell> cellStack = new Stack<>();
        int totalCells = (this.numVertices * this.numVertices);
        Cell currCell = this.grid[0][0]; // initially the starting cell
        int visitedCells = 1;

        while(visitedCells < totalCells) {
            // Finding all neighbors of currCell with all walls intact
            ArrayList<Cell> neighbors = findAdjacentNeighbors(currCell);
            // If 1 or more are found, choose a Cell at random and knock down wall between it and currCell
            Random r = new Random();
            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(r.nextInt(neighbors.size() - 1));
                addEdge(currCell, neighbor);
                cellStack.push(currCell);
                currCell = neighbor;
                visitedCells++;
            }
            else {
                currCell = cellStack.pop();
            }
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    /* SUPPLEMENTARY/HELPER METHODS */
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Fills the 2D Array representing the Grid with Cells containing initial information
     */
    private void fillGrid(Cell[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                // j dictates x-index whereas i dictates y-index
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Finds valid neighbors in the north, east, south, and west directions—
     * — that of which have all walls intact
     * @return a list of coordinate pairs in the adj-matrix
     */
    public ArrayList<Cell> findAdjacentNeighbors(Cell cell) {
        // list of adjacent neighbors
        ArrayList<Cell> neighbors = new ArrayList<>();

        // Conditionals to set valid values of starting and ending values for i and j of the loop
        int startPosX = ((cell.getX() - 1) < 0) ? cell.getX() : cell.getX() - 1;
        int endPosX = ((cell.getX() +  1) > (this.numVertices - 1)) ? cell.getX() : cell.getX() + 1;
        int startPosY = ((cell.getY() - 1) < 0) ? cell.getY() : cell.getY() - 1;
        int endPosY = ((cell.getY() + 1 > (this.numVertices - 1))) ? cell.getY() : cell.getY() + 1;

        // grid[i][j] are the neighbors of the current cell
        for(int i  = startPosX; i <= endPosX; i++) {
            for(int j = startPosY; j <= endPosY; j++) {
                // adding to a list of cells so that we can keep track of neighbor coordinates
                if(i != cell.getX() && j != cell.getY() && this.grid[i][j].allWallsIntact()) {
                    // j dictates x-index whereas i dictates y-index
                    neighbors.add(this.grid[i][j]);
                }
            }
        }
        return neighbors;
    }

    // Emulates knocking down a wall between 2 cells
    public void addEdge(Cell cell1, Cell cell2) {

        /* Although we call it "X", we determine "X" as "i" in the loop, which dictates rows (y-index) */

        // North/South Edge: Cell2 is above Cell1
        if((cell1.getX() - 1) == cell2.getX()) {
            cell1.northPath();
            cell2.southPath();
        }
        // North/South Edge: Cell1 is above Cell2
        else if((cell1.getX() + 1) == cell2.getX()) {
            cell1.southPath();
            cell2.northPath();
        }
        // East/West Edge: Cell2 precedes Cell1
        else if((cell1.getY() - 1) == cell2.getY()) {
            cell1.westPath();
            cell2.eastPath();
        }
        // East/West Edge: Cell1 precedes Cell2
        else if((cell1.getY() + 1) == cell2.getY()) {
            cell1.eastPath();
            cell2.westPath();
        }
    }
}
