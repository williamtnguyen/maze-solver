import java.util.ArrayList;

/**
 * Data Structure to hold the X & Y coordinates of a cell
 * Written By: William Nguyen
 */
public class Cell {
    // X & Y coordinates
    private final int x, y;
    // neighbors is all valid neighbors, connections is edge relationships
    public ArrayList<Cell> neighbors, connections; // public to allow modification by value, not reference
    // Fields to be utilized in BFS traversal and Dijkstra's shortest Path
    private Cell parent;
    private int distance;
    private int color;  // 0 = WHITE; 1 = GREY; 2 = BLACK


    // Standard Constructor to initialize instance fields
    public Cell(int x, int y) {
        // Theoretically, once x and y is set, it does not change
        this.x = x;
        this.y = y;

        // Instantiate neighbor and connection fields, will be modified in Maze class
        this.neighbors = new ArrayList<>();
        this.connections = new ArrayList<>();
    }


    // Standard accessor methods
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public ArrayList<Cell> getNeighbors() { return this.neighbors; }
    public ArrayList<Cell> getConnections() { return this.connections; }


    // Tells whether or not all walls are intact (i.e., connections field is empty)
    public boolean allWallsIntact() {
        return this.connections.isEmpty();
    }

    // Tells whether or not there is an edge or "no wall" between this Cell and the neighbor
    public boolean hasEdge(Cell neighbor) {
        // Is this edge and the neighbor param apart of eachother's connections field?
        return this.connections.contains(neighbor) && neighbor.getConnections().contains(this);
    }


    // Adds a neighbor Cell into "this" Cell's neighbor list in the valid index location
    public void addNeighbor(Cell neighbor) {
        /* Although we call it "X", we determine "X" as "i" in the loop, which dictates rows (y-index) */

        // North/South neighbor: Neighbor is above "this" Cell
        if((this.getX() - 1) == neighbor.getX()) {
            this.neighbors.add(neighbor);
            neighbor.neighbors.add(this);
        }
        // North/South neighbor: "this" Cell is above Neighbor
        else if((this.getX() + 1) == neighbor.getX()) {
            this.neighbors.add(neighbor);
            neighbor.neighbors.add(this);
        }
        // East/West neighbor: Neighbor precedes "this" cell
        else if((this.getY() - 1) == neighbor.getY()) {
            this.neighbors.add(neighbor);
            neighbor.neighbors.add(this);
        }
        // East/West neighbor: "this" Cell precedes Neighbor
        else if((this.getY() + 1) == neighbor.getY()) {
            this.neighbors.add(neighbor);
            neighbor.neighbors.add(this);
        }
    }

    // Emulates knocking down a wall between 2 cells
    public void addEdge(Cell neighbor) {

        /* Although we call it "X", we determine "X" as "i" in the loop, which dictates rows (y-index) */

        // North/South Edge: Neighbor is above "this" Cell
        if((this.getX() - 1) == neighbor.getX()) {
            this.connections.add(neighbor);
            neighbor.connections.add(this);
        }
        // North/South Edge: "this" Cell is above Neighbor
        else if((this.getX() + 1) == neighbor.getX()) {
            this.connections.add(neighbor);
            neighbor.connections.add(this);
        }
        // East/West Edge: Neighbor precedes "this" cell
        else if((this.getY() - 1) == neighbor.getY()) {
            this.connections.add(neighbor);
            neighbor.connections.add(this);
        }
        // East/West Edge: "this" Cell precedes Neighbor
        else if((this.getY() + 1) == neighbor.getY()) {
            this.connections.add(neighbor);
            neighbor.connections.add(this);
        }
    }

    // Easier than using a set
    public void removeEdge(Cell neighbor) {
        this.connections.remove(neighbor);
        neighbor.connections.remove(this);
    }

    // Removes neighbor bi-directionally
    public void removeNeighbor(Cell neighbor) {
        this.neighbors.remove(neighbor);
        neighbor.neighbors.remove(this);
    }

    /* Mutator methods for BFS() */
    public void setColor(int color) { this.color = color; }
    public void setDistance(int distance) { this.distance = distance; }
    public void setParent(Cell daddy) { this.parent = daddy; }

    /* Accessor methods for BFS() */
    public int getColor() { return this.color; }
    public int getDistance() { return this.distance; }
    public Cell getParent() { return this.parent; }


    // Equals/Hashcode contract
    @Override
    public boolean equals(Object x) {
        if(this.getClass() != x.getClass()) { return false; }
        Cell that = (Cell)x;
        return this.getX() == that.getX() && this.getY() == that.getY();
    }
}
