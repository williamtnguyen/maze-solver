/**
 * Data Structure to hold the x & y coordinates of a cell
 */
public class Cell {
    private final int x, y;
    private boolean visited; // for bfs and dfs

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        visited = false;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public boolean isVisited() { return this.visited; }

    // Will set it as visited
    public void setVisited() { this.visited = true; }
}
