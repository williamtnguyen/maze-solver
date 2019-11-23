//import java.util.ArrayList;
//
///**
// * ASCII Representation of Maze Cell[][] grid
// * Written by: Justin Zhu
// */
//public class ASCIIGrid {
//    //In an ASCII Grid, you'll include all the walls, actual cells, and corners
//    //Rows...if n %2  = 0, "+-+-+-+" top/bottom walls and corners
//    //    ...if n % 2 = 1, "|.|.|.|" left/right walls and cells
//    //Cols...if n % 2 = 0, "+|+|+|+" corners, left/right walls
//    //    ...if n % 2 = 1, "-.-.-.-" top/bottom walls, cells
//
//    //Special case for first and last row/col cells. So if cell 1, can't remove left wall... etc.
//
//    // Size of the Cell Grid in the Maze class
//    private int xSize;
//    private int ySize;
//    // Size of ASCII Grid in this class
//    private int xSizeASCII;
//    private int ySizeASCII;
//
//    private Maze maze;
//    private Cell[][] grid;
//
//
//    public ASCIIGrid(Maze maze){
//        this.xSize = maze.getNumVertices();
//        this.ySize = maze.getNumVertices();
//        this.grid = maze.getGrid();
//
//        // Calculated by Justin: number of total cells to represent our ASCII is: (numVertices * 2)
//        // Note that +1 denotes size vs. index
//        xSizeASCII = (xSize * 2) + 1;
//        ySizeASCII=(ySize * 2) + 1;
//    }
//
//
//    /**
//     * Initial Generation Step
//     * @return
//     */
//    public String[][] generateASCIIGrid(){
//
//        // Ex: 9 x 9 ASCII Grid for a 4x4 Maze Grid
//        String[][] asciiGrid = new String[xSizeASCII][ySizeASCII];
//
//        for(int x = 0; x < xSizeASCII; x++){
//            for(int y = 0; y < ySizeASCII; y++){
//                if(x % 2 == 0){ //Even Rows
//                    if(y % 2 == 0){//Even Columns
//                        asciiGrid[x][y] = "+";
//                    }
//                    else if(y % 2 == 1){//Odd Columns
//                        asciiGrid[x][y] = "-";
//                    }
//                }
//                else if(x % 2 == 1){//Odd Columns
//                    if(y % 2 == 0){//Even Columns
//                        asciiGrid[x][y] = "|";
//                    }
//                    else if(y % 2 == 1){//Odd Columns
//                        asciiGrid[x][y] = ".";
//                    }
//                }
//            }
//        }
//        return asciiGrid;
//    }
//
//
//    /**
//     *
//     * @return
//     */
//    public String[][] updateASCIIGrid(){
//        int traverseCount = 0;
//        String[][] updatedGrid = generateASCIIGrid();
//
//        System.out.println(xSizeASCII +"x"+ySizeASCII +" ASCII Grid for a "+ xSize+"x"+ySize
//                +" Maze Grid");
//
//        // Access the inputted cell[][] and check each cell's walls
//        for(int x = 0; x < grid.length; x++){
//            for(int y = 0; y < grid[x].length; y++){
//                int xASCII = (grid[x][y].getX() * 2) + 1;
//                int yASCII = (grid[x][y].getY() * 2) + 1;
//
//                if(traverseCount <= 9){
//                    updatedGrid[xASCII][yASCII]=Integer.toString(traverseCount);
//                }
//                else{
//                    traverseCount = 0;
//                    updatedGrid[xASCII][yASCII]=Integer.toString(traverseCount);
//
//                }
//                // No special cases, all walls can be broken down
//                if(grid[x][y].getNorthWall() == false){
////					System.out.println("North, x: "+xASCII+", y: "+yASCII);
//                    if((updatedGrid[xASCII -1][yASCII]) == "-"){
//                        System.out.println("Truee");
//                        updatedGrid[xASCII -1][yASCII] = " ";
//                    }
//                }
//                // Right Perimeter walls can't be broken down.
//                if(grid[x][y].getEastWall() == false){
//                    if(grid[x][y] == grid[x][grid[x].length-1]){
//                        System.out.println("Yo that can't be broken down bro");
//                    }
//                    if((updatedGrid[xASCII][yASCII +1]) == "|"){
//                        updatedGrid[xASCII][yASCII +1] = " ";
//                    }
//                }
//                if(grid[x][y].getSouthWall()==false){ //no special cases, ""
//                    if((updatedGrid[xASCII +1][yASCII]) == "-"){
//                        updatedGrid[xASCII +1][yASCII] = " ";
//                    }
//                }
//                if(grid[x][y].getWestWall() == false){ //Left perimeter walls can't be broken down.
//                    if(grid[x][y] == grid[x][0]){
//                        System.out.println("Nope, not this wall");
//                        updatedGrid[xASCII][yASCII -1] = "X";
//                    }
//                    else if((updatedGrid[xASCII][yASCII -1]) == "|"){
//                        updatedGrid[xASCII][yASCII -1] = " ";
//                    }
//                }
//                traverseCount++;
//            }
//        }
//        return updatedGrid;
//    }
//
//    // TODO
//    public String[][] updateASCIIGridWithPath(ArrayList<Cell> visitOrder){
//        return null;
//    }
//}
