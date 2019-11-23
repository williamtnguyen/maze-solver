package cs146F19.zhu.project3;

import java.util.ArrayList;

/**
 * Data Structure to hold the x & y coordinates of a cell
 * Written By: William Nguyen
 */
public class Cell {
    // X & Y coordinates
    private final int x, y;
    // neighbors is all valid neighbors, connections is edge relationships
    private ArrayList<Cell> neighbors, connections;


    public Cell(int x, int y) {
        // Theoretically, once x and y is set, it does not change
        this.x = x;
        this.y = y;

        // Instantiate neighbor and connection fields, will be modified in Maze class
        this.neighbors = new ArrayList<>();
        /* NOTE: "Index : NeighborDirection" = { 0:North, 1:East, 2:South, 3:West } */
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
            this.connections.add( neighbor);
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

        // nvm about this  vvv
//        // Removing "this" Cell and Neighbor from eachother's neighbor list so we don't visit it again
//        this.neighbors.remove(neighbor);
//        neighbor.neighbors.remove(this);
    }


    // Equals/Hashcode contract
    @Override
    public boolean equals(Object x) {
        Cell that = (Cell)x;
        return this.getX() == that.getX() && this.getY() == that.getY();
    }
}