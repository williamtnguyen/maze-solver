/**
 * Data Structure to hold the x & y coordinates of a cell
 * Written By: William Nguyen
 */
public class Cell {
    // X & Y coordinates
    private final int x, y;
    // Explicitly keeping track if there are neighbors in North, East, South, West directions
    private boolean northWall, eastWall, southWall, westWall;

    public Cell(int x, int y) {
        // Theoretically, once x and y is set, it does not change
        this.x = x;
        this.y = y;

        // Initially, every cells walls are intact
        this.northWall = true;
        this.eastWall = true;
        this.southWall = true;
        this.westWall = true;
    }

    // Standard accessor methods
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public boolean getNorthWall() { return this.northWall; }
    public boolean getEastWall() { return this.eastWall; }
    public boolean getSouthWall() { return this.southWall; }
    public boolean getWestWall() { return this.westWall; }

    // Standard mutator methods for intact walls
    // Theoretically, once a wall is knocked down, it does not change
    public void northPath() { this.northWall = false; }
    public void eastPath() { this.eastWall = false; }
    public void southPath() { this.southWall = false; }
    public void westPath() { this.westWall = false; }

    // Tells whether or not all walls are intact
    public boolean allWallsIntact() {
        return (northWall && eastWall && southWall && westWall);
    }


    // Tells whether or not there is an edge or "no wall" between this Cell and the neighbor
    public boolean hasEdge(Cell neighbor) {
        // North/South Edge: Neighbor cell is above this Cell
        if((this.getX() - 1) == neighbor.getX()) {
            return this.northWall && neighbor.southWall;
        }
        // North/South Edge: This Cell is above Neighbor Cell
        else if((this.getX() + 1) == neighbor.getX()) {
            return this.southWall && neighbor.northWall;
        }
        // East/West Edge: Neighbor Cell precedes this Cell
        else if((this.getY() - 1) == neighbor.getY()) {
            return this.westWall && neighbor.eastWall;
        }
        // East/West Edge: This Cell precedes Neighbor Cell
        else if((this.getY() + 1) == neighbor.getY()) {
            return this.eastWall && neighbor.westWall;
        }
        // Otherwise, there is no edge between these cells
        return false;
    }


    // Equals/Hashcode contract
    @Override
    public boolean equals(Object x) {
        Cell that = (Cell)x;
        return this.getX() == that.getX() && this.getY() == that.getY();
    }
}
