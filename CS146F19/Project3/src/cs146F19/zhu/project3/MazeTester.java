package cs146F19.zhu.project3;

public class MazeTester {
	public static void main(String[] args){
		MazePrinter p = new MazePrinter();
//		p.printTestMaze();
		
		ASCIIGrid a = new ASCIIGrid(4,4);
		
		String[][] maze=a.generateASCIIGrid(); //Change the size with these numbers
		
		Cell[][] c= new Cell[4][4];

		
		for(int x=0; x<c.length; x++){
			for(int y=0; y<c[0].length; y++){
				c[x][y] = new Cell(x,y);
			}
		}

		c[0][0].northPath();
		c[0][0].southPath();
		c[1][0].eastPath();
		c[1][1].southPath();
		c[2][1].westPath();
		c[2][0].westPath();
		
		ASCIIGrid b = new ASCIIGrid(4,4,c);

		String[][] updatedMaze = b.updateASCIIGrid();
		
		p.printMaze(updatedMaze);
		
//		p.printMaze(maze);
	}
}

