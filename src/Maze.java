import java.lang.reflect.Array;
import java.util.*;

/**
 * A Maze represented with a 2D Array of Cells (holding x & y coordinates)
 * Written By: William Nguyen
 */
public class Maze {
    // 2D Array of Cells starts off as a grid
    private final int numVertices;
    private Cell[][] grid;
    private final int totalCells;
    private Cell start, finish;

    // Standard Constructor to initialize instance fields
    public Maze(int numVertices) {
        this.numVertices = numVertices;
        // Initializing & filling the grid with Cells with proper coordinates
        grid = new Cell[this.numVertices][this.numVertices];
        totalCells = (this.numVertices * this.numVertices);
        fillGrid(this.grid);

        // Denoting start and finish cells to use in DFS/BFS methods
        this.start = this.grid[0][0];
        this.finish = this.grid[this.numVertices - 1][this.numVertices - 1];
    }

    /**
     * Simple Maze Generation Algorithm using DFS
     */
    public void generateMaze() {
        // Stack eliminates recursion: holds cell locations
        Stack<Cell> cellStack = new Stack<>();
        Cell currCell = this.start; // initially the starting cell
        int visitedCells = 1;
        cellStack.push(currCell);

        Random r = new Random(); // set seed here for testing
        while(visitedCells < totalCells) {
            // Finding all valid neighbors of currCell with ALL WALLS INTACT
            findNewNeighbors(currCell);
            List<Cell> neighbors = currCell.getNeighbors();
            // If 1 or more are found, choose a Cell at Random and knock down wall between it and currCell
            if(!neighbors.isEmpty()) {
                Cell neighbor = neighbors.get(r.nextInt(neighbors.size()));
                // Adds to "connections" and removes from "neighbors" so that we don't push seen Cells onto Stack
                currCell.addEdge(neighbor);
                // Removing this neighbor from "neighbors" of all cells
                removeFromNeighbors(neighbor);
                neighbor.removeNeighbor(currCell);
                // Push to stack and update to next cell in traversal
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
        HashSet<Cell> seen = new HashSet<>();

        // Initially the starting point
        Cell currCell = start;
        cellStack.push(currCell);

        // The goal is not to visit all nodes, but rather to reach the finishing Cell
        while(!currCell.equals(finish)) {
            currCell = cellStack.pop();
            if(!seen.contains(currCell)) {
                visitOrder.add(currCell);
                seen.add(currCell);
            }
            // Pushing newly discovered edges to the stack
            for(Cell edge : currCell.getConnections()) {
                if (!seen.contains(edge)) {
                    cellStack.push(edge);
                }
            }
        }
        return visitOrder;
    }

    /**
     * BFS Iterative Solution
     * @return List-Index 0: BFS Visit Order; List-Index 1: Dijkstra's Shortest Path Visit Order
     */
    public ArrayList<ArrayList<Cell>> solveBFS() {
        // BFS nature calls for FIFO order
        Queue<Cell> cellQueue = new LinkedList<>();
        // Traversal Orders of interest
        ArrayList<Cell> visitOrder = new ArrayList<>();
        // Initially the starting point
        Cell currCell = start;

        // Initialize vertices with valid BFS fields
        bfsInitializeCells();

        cellQueue.add(currCell);
        while(!cellQueue.isEmpty()) {
            currCell = cellQueue.poll();
            visitOrder.add(currCell);
            // The goal is not to visit all nodes, but rather to reach the finishing Cell
            if(currCell.equals(finish)) { break; }

            for(Cell edge : currCell.getConnections()) {
                // If the edge is undiscovered, i.e., WHITE
                if(edge.getColor() == 0) {
                    edge.setColor(1); // Set to "not fully discovered", i.e., GRAY
                    edge.setDistance(edge.getDistance() + 1);
                    edge.setParent(currCell);
                    cellQueue.add(edge);
                }
            }
            // Set to "fully discovered", i.e., BLACK
            currCell.setColor(2);
        }
        // Get the shortest path order and return it with the visit order
        ArrayList<Cell> shortestOrder = bfsShortestPath(finish);
        ArrayList<ArrayList<Cell>> traversals = new ArrayList<>();
        traversals.add(visitOrder);
        traversals.add(shortestOrder);
        return traversals;
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
        if(xCoord > 0 && this.grid[xCoord - 1][yCoord].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord - 1][yCoord]);
        }
        if(yCoord > 0 && this.grid[xCoord][yCoord - 1].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord][yCoord - 1]);
        }
        if(xCoord + 1 < this.numVertices && this.grid[xCoord + 1][yCoord].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord + 1][yCoord]);
        }
        if(yCoord + 1 < this.numVertices && this.grid[xCoord][yCoord + 1].allWallsIntact()) {
            cell.addNeighbor(this.grid[xCoord][yCoord + 1]);
        }
    }

    // Effectively removes the "neighbors" list of every cell in the graph
    private void removeFromNeighbors(Cell neighbor) {
        for(int i = 0; i < numVertices; i++) {
            for(int j = 0; j < numVertices; j++) {
                Cell c = grid[i][j];
                if(c.getNeighbors().contains(neighbor)) {
                    c.removeNeighbor(neighbor);
                }
            }
        }
    }

    // Effectively initializes Cell fields for solveBFS(): distance, parent, and color
    private void bfsInitializeCells() {
        for(int i = 0; i < numVertices; i++) {
            for(int j = 0; j < numVertices; j++) {
                Cell c = grid[i][j];
                c.setColor(0); // initially WHITE
                c.setDistance(Integer.MAX_VALUE);
                c.setParent(null); // parents are null initially, "start" parent stays null
            }
        }
        start.setColor(1); // GREY because it is first to be visited
        start.setDistance(0);
        start.setParent(null); // repeating this step in case
    }

    // Reverse iteration from target to source to find the shortest path
    private ArrayList<Cell> bfsShortestPath(Cell finish) {
        Cell currCell = finish;
        Stack<Cell> cellStack = new Stack<>();
        while(currCell != null) {
            cellStack.push(currCell);
            currCell = currCell.getParent();
        }
        // Putting it into a List to make our lives easier (ascending iteration)
        ArrayList<Cell> shortestOrder = new ArrayList<>();
        while(!cellStack.isEmpty()) { shortestOrder.add(cellStack.pop()); }
        return shortestOrder;
    }

    // Standard Accessor methods for encapsulation
    public int getNumVertices() { return this.numVertices; }
    public Cell[][] getGrid() { return this.grid; }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    /* Methods for solving Professor Potika's Mazes >:] */
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    /**
     * Replaces "this" cell grid with a generated one from Prof's .txt inputs
     * @param generatedGrid a grid that is generated via reading Prof's .txt input mazes
     */
    public void replaceCellGrid(Cell[][] generatedGrid) {
        this.grid = generatedGrid;
    }

    public void replaceStartAndFinishCell(Cell[][] generatedGrid) {
        this.start = generatedGrid[0][0];
        this.finish = generatedGrid[generatedGrid.length - 1][generatedGrid[generatedGrid.length - 1].length - 1];
    }
}
