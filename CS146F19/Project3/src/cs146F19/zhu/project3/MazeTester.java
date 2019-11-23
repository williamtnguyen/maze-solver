package cs146F19.zhu.project3;

import java.util.ArrayList;

public class MazeTester {
	public static void main(String[] args){
		MazePrinter p = new MazePrinter();
//		p.printTestMaze();
		
		ASCIIGrid a = new ASCIIGrid(new Maze(4));
		
		String[][] maze=a.generateASCIIGrid(); //Change the size with these numbers
		

//

//		
//		Maze m = new Maze(4, c);
//		ASCIIGrid b = new ASCIIGrid(m);
//
//		String[][] updatedMaze = b.updateASCIIGrid();
//		
//		p.printMaze(updatedMaze);
		
//		p.printMaze(maze);
		ArrayList<Cell> arr = new ArrayList<Cell>();
		Cell q = new Cell(0,0);
		Cell w = new Cell(0,1);
		Cell e = new Cell(1,1);
		Cell r = new Cell(1,2);
		Cell t = new Cell(2,2);
		Cell y = new Cell(3,2);
		Cell u = new Cell(3,3);
		Cell x = new Cell(0,3);
		Cell z = new Cell(3,0);

//		q.northPath();
//		q.eastPath();
//		w.southPath();
//		e.eastPath();
//		r.southPath();
//		t.southPath();
//		y.eastPath();
//		u.southPath();
		
		x.eastPath();
		z.westPath();
	
		arr.add(q);
		arr.add(w);
		arr.add(e);
		arr.add(r);
		arr.add(t);
		arr.add(y);
		arr.add(u);
		arr.add(x);
		arr.add(z);
		
		Cell[][] c= new Cell[4][4];
		
		for(int row=0; row<c.length; row++){
			for(int col=0; col<c[0].length; col++){
				c[row][col] = new Cell(row,col);
			}
		}
		
		c[0][0].northPath();
		c[0][0].southPath();
		c[1][0].eastPath();
		c[1][1].southPath();
		c[2][1].westPath();
		c[2][0].westPath();		
		
		Maze m = new Maze(4, c);
//		System.out.println(m.getNumVertices());
		ASCIIGrid grid = new ASCIIGrid(m);
		
		System.out.println("Grid with broken walls");
		String[][] test = grid.updateASCIIGridWalls();
		p.printMaze(test);

		System.out.println("Traversal Path");
		String[][] test1 = grid.updateASCIIGridWithTraversalPath(arr);
		p.printMaze(test1);
		
		System.out.println("Traversal Path");
		String[][] test2 = grid.updateASCIIGridWithShortestPath(arr);
		p.printMaze(test2);

		grid.printPath(arr);
		grid.printLength(arr); //Total length
		grid.printVisited(arr); //Total visited, should be different arrays
//		System.out.println(xSizeASCII + "x" + ySizeASCII + " ASCII Grid for a " + xSize + "x" + ySize + " Maze Grid");


		
	}
	
//	public String[][] updateASCIIGridWithTraversalPathOLDCODE(){
//		// No special cases, all walls can be broken down
//					if (visitOrder.get(i).getNorthWall() == false) {
//						// System.out.println("North, x: "+xASCII+", y: "+yASCII);
//						if ((updatedGrid[xASCII - 1][yASCII]) == "-") {
//							System.out.println("Truee");
//							updatedGrid[xASCII - 1][yASCII] = " ";
//						}
//					}
//					// Right Perimeter walls can't be broken down.
//					if (visitOrder.get(i).getEastWall() == false) {
//						// System.out.println(maze.getNumVertices());
//						if (visitOrder.get(i).getY() == xSize - 1) {
//							System.out.println("Yo that can't be broken down bro");
//							updatedGrid[xASCII][yASCII + 1] = "X";
//
//						} else if ((updatedGrid[xASCII][yASCII + 1]) == "|") {
//							updatedGrid[xASCII][yASCII + 1] = " ";
//						}
//					}
//					if (visitOrder.get(i).getSouthWall() == false) { // no special
//																		// cases, ""
//						if ((updatedGrid[xASCII + 1][yASCII]) == "-") {
//							updatedGrid[xASCII + 1][yASCII] = " ";
//						}
//					}
//					if (visitOrder.get(i).getWestWall() == false) { // Left perimeter
//																	// walls
//						// can't be broken down.
//						if (visitOrder.get(i).getY() == 0) {
//							System.out.println("Nope, not this wall");
//							updatedGrid[xASCII][yASCII - 1] = "X";
//						} else if ((updatedGrid[xASCII][yASCII - 1]) == "|") {
//							updatedGrid[xASCII][yASCII - 1] = " ";
//						}
//					}
//	}
}

