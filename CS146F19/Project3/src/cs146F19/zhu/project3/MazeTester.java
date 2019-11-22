package cs146F19.zhu.project3;

import java.util.ArrayList;

public class MazeTester {
	public static void main(String[] args){
		MazePrinter p = new MazePrinter();
//		p.printTestMaze();
		
		ASCIIGrid a = new ASCIIGrid(new Maze(4));
		
		String[][] maze=a.generateASCIIGrid(); //Change the size with these numbers
		
//		Cell[][] c= new Cell[4][4];
//
//		
//		for(int x=0; x<c.length; x++){
//			for(int y=0; y<c[0].length; y++){
//				c[x][y] = new Cell(x,y);
//			}
//		}
//
//		c[0][0].northPath();
//		c[0][0].southPath();
//		c[1][0].eastPath();
//		c[1][1].southPath();
//		c[2][1].westPath();
//		c[2][0].westPath();
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
		
		Maze m = new Maze(4);
		System.out.println(m.getNumVertices());
		ASCIIGrid grid = new ASCIIGrid(m);
		String[][] test = grid.updateASCIIGridWithPath(arr);
//		String[][] test = grid.updateASCIIGrid();
		p.printMaze(test);
		
	}
}

