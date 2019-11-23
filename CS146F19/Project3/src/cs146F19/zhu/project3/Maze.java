package cs146F19.zhu.project3;
import java.util.*;

/**
 * A Maze represented with a 2D Array of Cells (holding x & y coordinates)
 * Written By: William Nguyen
 */
public class Maze {
    // 2D Array of Cells starts off as a grid
    private final int numVertices;
    private Cell[][] grid;


    public Maze(int numVertices) {
        this.numVertices = numVertices;
        // Initializing & filling the grid with Cells with proper coordinates
        grid = new Cell[this.numVertices][this.numVertices];
        fillGrid(this.grid);
    }
    
    //Constructor for testing sample cell[][] grids only
    public Maze(int numVertices, Cell[][] grid) {
        this.numVertices = numVertices;
        this.grid=grid;
    }

    /**
     * Simple Maze Generation Algorithm using DFS
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
            findNewNeighbors(currCell);
            List<Cell> neighbors = currCell.getNeighbors();
            // If 1 or more are found, choose a Cell at random and knock down wall between it and currCell
            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(r.nextInt(neighbors.size()));
                currCell.addEdge(neighbor); // adds to connections and removes from neighbors
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
    public ArrayList<Cell> solveDFS() {
        // Stack eliminates recursion: holds cell locations, DFS nature calls for LIFO order
        Stack<Cell> cellStack = new Stack<>();
        ArrayList<Cell> visitOrder = new ArrayList<>();

        // Starting & Endpoints
        Cell currCell = this.grid[0][0];
        Cell finish = this.grid[numVertices - 1][numVertices - 1];
        visitOrder.add(currCell); // first to be visited

        Random r = new Random(); // set seed here for testing
        // The goal is not to visit all nodes, but rather to reach the finishing Cell
        while(!currCell.equals(finish)) {
            // Finding all neighbors of currCell that have edges between them and haven't been visited yet
            List<Cell> neighbors = currCell.getNeighbors();
            // If 1 or more are found, choose a Cell at random, add it to visitOrder, and make it the new currCell
            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(r.nextInt(neighbors.size()));
                cellStack.push(currCell);
                currCell = neighbor;
                if(!visitOrder.contains(currCell)) { visitOrder.add(currCell); }
            }
            else {
                // Pop the Stack and backtrack
                currCell = cellStack.pop();
            }
        }
        return visitOrder;
    }

    /**
     * BFS Iterative Solution
     */
    public ArrayList<Cell> solveBFS() {
        // BFS nature calls for FIFO order
        Queue<Cell> cellQueue = new LinkedList<>();
        ArrayList<Cell> visitOrder = new ArrayList<>();

        // Starting & Endpoints
        Cell currCell = this.grid[0][0];
        Cell finish = this.grid[this.numVertices - 1][this.numVertices - 1];
        visitOrder.add(currCell);

        Random r = new Random();
        while(!currCell.equals(finish)) {
            // Finding all neighbors of currCell that have edges between them and haven't been visited yet
            List<Cell> neighbors = currCell.getNeighbors();
            // If 1 or more are found, choose a Cell at random, add it to visitOrder, and make it the new currCell
            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(r.nextInt(neighbors.size()));
                cellQueue.add(neighbor);
                currCell = neighbor;
                if(!visitOrder.contains(currCell)) { visitOrder.add(currCell); }
            }
            else {
                // "Dequeue" the Queue and backtrack
                currCell = cellQueue.poll();
            }
        }
        return visitOrder;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    /* SUPPLEMENTARY/HELPER METHODS */
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Fills the 2D Array representing the Grid with Cells with proper coordinate info
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
     * — that of which have ALL WALLS INTACT
     * @return a list of coordinate pairs in the adj-matrix
     */
    public void findNewNeighbors(Cell cell) {
        int xCoord = cell.getX(), yCoord = cell.getY();

        // Only adding neighbors to the list that are within bounds & have ALL WALLS INTACT
        if(xCoord  > 0 && this.grid[xCoord - 1][yCoord].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord - 1][yCoord]);
        }
        if(yCoord  > 0 && this.grid[xCoord][yCoord - 1].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord][yCoord - 1]);
        }
        if(xCoord + 1 < this.numVertices && this.grid[xCoord + 1][yCoord].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord + 1][yCoord]);
        }
        if(yCoord + 1 < this.numVertices && this.grid[xCoord][yCoord + 1].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord][yCoord + 1]);
        }
    }

    // Standard Accessor methods for encapsulation
    public int getNumVertices() { return this.numVertices; }
    public Cell[][] getGrid() { return this.grid; }



    // ok this works fuckin finally
    public static void main(String[] args) {
        Maze maze = new Maze(3);
        Cell[][] grid = maze.getGrid();
        Cell start = grid[1][1];
        maze.findNewNeighbors(start);

        List<Cell> neighbors = start.getNeighbors();
        for(Cell c : neighbors) {
            System.out.println("X coord: " + c.getX() + " Y coord: "  + c.getY());
            for(Cell c1 : c.getNeighbors()) {
                System.out.println("X coord: " + c1.getX() + " Y coord: "  + c1.getY());
            }
        }
    }
}