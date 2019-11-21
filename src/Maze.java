import java.util.*;

/**
 * A Maze represented with a 2D Array of Cells (holding x & y coordinates),
 */
public class Maze {
    // 2D Array of Cells starts off as a Grid
    private final int numVertices;
    private Cell[][] grid;


    public Maze(int numVertices) {
        this.numVertices = numVertices;

        // Initializing & filling the grid with Cells of proper information
        grid = new Cell[this.numVertices][this.numVertices];
        fillGrid(this.grid);
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

        Random r = new Random(); // set seed here for testing
        while(visitedCells < totalCells) {
            // Finding all neighbors of currCell with ALL WALLS INTACT
            ArrayList<Cell> neighbors = findAdjacentNeighbors(currCell);
            for(Cell neighbor : neighbors) {
                if(!neighbor.allWallsIntact()) { neighbors.remove(neighbor); }
            }
            // If 1 or more are found, choose a Cell at random and knock down wall between it and currCell
            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(r.nextInt(neighbors.size() - 1));
                addEdge(currCell, neighbor);
                cellStack.push(currCell);
                currCell = neighbor;
                visitedCells++;
            }
            else {
                // pop the stack and backtrack
                currCell = cellStack.pop();
            }
        }
    }

    /**
     * DFS Iterative Solution
     */
    public void solveDFS() {
        // Stack eliminates recursion: holds cell locations
        Stack<Cell> cellStack = new Stack<>();
        Cell currCell = this.grid[0][0]; // initially the starting cell
        Cell finish = this.grid[numVertices - 1][numVertices - 1];
        ArrayList<Cell> visitOrder = new ArrayList<>();
        visitOrder.add(currCell); // first to be visited

        Random r = new Random(); // set seed here for testing
        // The goal is not to visit all nodes, but rather to reach the finishing Cell
        while(!currCell.equals(finish)) {
            // Finding all neighbors of currCell that have edges between them
            ArrayList<Cell> neighbors = findAdjacentNeighbors(currCell);
            for(Cell neighbor : neighbors) {
                if(!currCell.hasEdge(neighbor)) { neighbors.remove(neighbor); }
            }
            // If 1 or more are found, choose a Cell at random, add it to visitOrder, and make it the new currCell
            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(r.nextInt(neighbors.size() - 1));
                cellStack.push(currCell);
                currCell = neighbor;
                visitOrder.add(currCell);
            }
            else {
                // pop the stack and backtrack
                currCell = cellStack.pop();
            }
        }
    }

    /**
     * BFS Iterative Solution
     */
    public void solveBFS() {

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
                if(i != cell.getX() && j != cell.getY()) {
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
