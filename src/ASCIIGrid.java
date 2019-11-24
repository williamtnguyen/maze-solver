import java.util.ArrayList;

/**
 * String[][] ASCII Representation of Cell[][] Maze/Grid
 * Written by: Justin Zhu
 */
public class ASCIIGrid {

    // In an ASCII Grid, you'll include all the walls, actual cells, and corners
    // Rows...if n %2 = 0, "+-+-+-+" top/bottom walls and corners
    // ...if n % 2 = 1, "|.|.|.|" left/right walls and cells
    // Cols...if n % 2 = 0, "+|+|+|+" corners, left/right walls
    // ...if n % 2 = 1, "-.-.-.-" top/bottom walls, cells

    // Special case for first and last row/col cells. So if cell 1, can't remove
    // left wall... etc.

    // Size of the Cell Grid in the Maze class
    private int xSize;
    private int ySize;
    // Size of ASCII Grid in this class
    private int xSizeASCII;
    private int ySizeASCII;

    // Store the maze (as a Maze, Cell[][], and String[][] variables for ASCII)
    private Maze maze;
    private Cell[][] grid;
    private String[][] gridASCII;
    private String[][] shortestPathGridASCII;
    private String[][] traversalPathGridASCII;

    public ASCIIGrid(Maze maze) {
        this.xSize = maze.getNumVertices();
        this.ySize = maze.getNumVertices();
        this.grid = maze.getGrid();

        // Calculated by Justin: number of total cells to represent our ASCII
        // is: (numVertices * 2)
        // Note that +1 denotes size vs. index
        xSizeASCII = (xSize * 2) + 1;
        ySizeASCII = (ySize * 2) + 1;
    }

    //For testing Professor's text file test cases
    public ASCIIGrid(Maze maze, String[][] testCase) {
        this.xSize = maze.getNumVertices();
        this.ySize = maze.getNumVertices();
        this.grid = maze.getGrid();
        this.gridASCII = testCase;

        // Calculated by Justin: number of total cells to represent our ASCII is: (numVertices * 2)
        // Note that +1 denotes size vs. index
        xSizeASCII = (xSize * 2) + 1;
        ySizeASCII = (ySize * 2) + 1;
    }

    /**
     * Initial Generation Step. Stores new grid into global gridASCII variable.
     * @return the initial String[][] array
     */
    public String[][] generateASCIIGrid() {
        // Ex: 9 x 9 ASCII Grid for a 4x4 Maze Grid
        gridASCII = new String[xSizeASCII][ySizeASCII];

        // Double for loop to generate the walls and cells in the grid
        for (int x = 0; x < xSizeASCII; x++) {
            for (int y = 0; y < ySizeASCII; y++) {
                if (x % 2 == 0) { // Even Rows
                    if (y % 2 == 0) { // Even Columns
                        gridASCII[x][y] = "+";
                    } else if (y % 2 == 1) { // Odd Columns
                        gridASCII[x][y] = "-";
                    }
                } else if (x % 2 == 1) { // Odd Columns
                    if (y % 2 == 0) { // Even Columns
                        gridASCII[x][y] = "|";
                    } else if (y % 2 == 1) { // Odd Columns
                        gridASCII[x][y] = " ";
                    }
                }
            }
        }

        return gridASCII;
    }

    /**
     * Updates generated grid walls based on maze's cell grid.
     * @return an updated String[][] array
     */
    public String[][] updateASCIIGridWalls() {
        // If prior step of generating was skipped
        if (gridASCII == null)
            gridASCII = generateASCIIGrid();
        //Special case where top left wall and bottom right wall are the entrance/exits
        gridASCII[0][1] = " ";
        gridASCII[gridASCII.length-1][gridASCII[0].length-2] = " ";
        // Access the inputted cell[][] and check each cell's walls
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                ArrayList<Cell> a = grid[x][y].getConnections();

                int xASCII = (grid[x][y].getX() * 2) + 1;
                int yASCII = (grid[x][y].getY() * 2) + 1;
                int i = 0;
                int xDiff = 0;
                int yDiff = 0;
                // Only the left furthest north perimeter wall can be broken down, "x" if not.
                for(int g = 0; g<grid[x][y].getConnections().size(); g++){
                    //North wall: cell x - cell connection x = 1
                    xDiff = grid[x][y].getX()-a.get(i).getX();
                    yDiff = grid[x][y].getY()-a.get(i).getY();
                    if (xDiff == 1) { //Meaning north wall
                        if ((grid[x][y] == grid[0][y]) && grid[x][y] != grid[0][0]) {
                            gridASCII[xASCII - 1][yASCII] = "X";
                        }
                        if ((gridASCII[xASCII - 1][yASCII]) == "-") {
                            gridASCII[xASCII - 1][yASCII] = " ";
                        }
                    }
                    // Right perimeter walls can't be broken down, "X" marker is placed if done so.
                    if (yDiff == -1) { //Meaning east wall
                        if (grid[x][y] == grid[x][grid[x].length - 1]) {
                            gridASCII[xASCII][yASCII + 1] = "X";
                        } else if ((gridASCII[xASCII][yASCII + 1]) == "|") {
                            gridASCII[xASCII][yASCII + 1] = " ";
                        }
                    }
                    // Only the right furthest southern perimeter wall can be broken down, "X" if not.
                    if (xDiff == -1) { //Meaning south wall
                        if ((grid[x][y] == grid[grid.length - 1][y])
                                && (grid[x][y] != grid[grid.length - 1][grid[x].length - 1])) {
                            gridASCII[xASCII + 1][yASCII] = "X";
                        }
                        if ((gridASCII[xASCII + 1][yASCII]) == "-") {
                            gridASCII[xASCII + 1][yASCII] = " ";
                        }
                    }
                    // Left perimeter walls can't be broken down, "X" marker is placed if done so.
                    if (yDiff == 1) { //Meaning west wall
                        if (grid[x][y] == grid[x][0]) {
                            gridASCII[xASCII][yASCII - 1] = "X";
                        } else if ((gridASCII[xASCII][yASCII - 1]) == "|") {
                            gridASCII[xASCII][yASCII - 1] = " ";
                        }
                    }

                }
            }
        }
        return gridASCII;
    }

    /**
     * Updates grid cells based on maze's cell traversal/visit order. Traversal
     * order will go from 0-9 and reset back to 0 when traversal order reaches 9+
     * @return an updated String[][] array
     */
    public String[][] updateASCIIGridWithTraversalPath(ArrayList<Cell> visitOrder) {
        // Counter for traversal
        int traverseCount = 0;

        // New variable for traversal path so original grid doesn't get corrupted
        // Copy every element over

        traversalPathGridASCII = new String[xSizeASCII][ySizeASCII];
        for(int i = 0; i < gridASCII.length; i++){
            for(int j = 0; j<gridASCII[i].length; j++){
                traversalPathGridASCII[i][j] = gridASCII[i][j];
            }
        }

        // For loop to traverse the ArrayList<Cell> to get the traversal order
        for (int i = 0; i < visitOrder.size(); i++) {
            // x and y coordinates of cell in the ASCII Grid
            int xASCII = (visitOrder.get(i).getX() * 2) + 1;
            int yASCII = (visitOrder.get(i).getY() * 2) + 1;

            // Numbering each visited cell
            if (traverseCount <= 9) {
                traversalPathGridASCII[xASCII][yASCII] = Integer.toString(traverseCount);
            } else {
                traverseCount = 0;
                traversalPathGridASCII[xASCII][yASCII] = Integer.toString(traverseCount);
            }
            traverseCount++;
        }
        return traversalPathGridASCII;
    }

    /**
     * Updates grid cells based on shortest path of solving the maze. All cells
     * in the input ArrayList<Cell> will be given a '#' as a marker to signify
     * visited.
     *
     * @return an updated String[][] array
     */
    public String[][] updateASCIIGridWithShortestPath(ArrayList<Cell> shortestPath) {

        // New variable for traversal path so original grid doesn't get corrupted
        // Copy every element over
        shortestPathGridASCII = new String[xSizeASCII][ySizeASCII];
        for(int i = 0; i < gridASCII.length; i++){
            for(int j = 0; j<gridASCII[i].length; j++){
                shortestPathGridASCII[i][j] = gridASCII[i][j];
            }
        }
        // For loop to traverse the ArrayList<Cell> to get the traversal path
        for (int i = 0; i < shortestPath.size(); i++) {
            int xASCII = (shortestPath.get(i).getX() * 2) + 1;
            int yASCII = (shortestPath.get(i).getY() * 2) + 1;

            shortestPathGridASCII[xASCII][yASCII] = "#";
            if(i != shortestPath.size() - 1){
                int neighborXASCII = (shortestPath.get(i+1).getX()*2)+1;
                int neighborYASCII = (shortestPath.get(i+1).getY()*2)+1;
                shortestPathGridASCII[(xASCII + neighborXASCII)/2][(yASCII + neighborYASCII)/2] = "#";
            }
        }
        return shortestPathGridASCII;
    }

    /**
     * Get Path Coordinates, total length of path, total visited cells
     */
    public String getPath(ArrayList<Cell> shortestPath) {
        String path = "Path: ";
        for (int i = 0; i < shortestPath.size(); i++) {
            path = path + "(" + shortestPath.get(i).getX() + "," + shortestPath.get(i).getY() + ") ";
        }
        return path;
    }

    public String getLength(ArrayList<Cell> shortestPath) {
        return ("Length of Shortest Path: " + shortestPath.size());
    }

    public String getVisited(ArrayList<Cell> visitOrder) {
        return ("Visited Cells: " + visitOrder.size());
    }

    /**
     * Standard accessor methods if needed
     */
    public String[][] getGridASCII() {
        return gridASCII;
    }
    public String[][] getShortestPathASCII() {
        return shortestPathGridASCII;
    }
    public String[][] getTraversalPathASCII() {
        return traversalPathGridASCII;
    }
}