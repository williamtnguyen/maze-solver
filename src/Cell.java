/**
 * Data Structure to hold the x & y coordinates of a cell
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

    // Standard mutator methods for intact walls
    // Theoretically, once a wall is knocked down, it does not change
    public void northPath() { this.northWall = false; }
    public void eastPath() { this.eastWall = false; }
    public void southPath() { this.southWall = false; }
    public void westPath() { this.westWall = false; }

    // Returns whether or not all walls are intact
    public boolean allWallsIntact() {
        return (northWall && eastWall && southWall && westWall);
    }


}
