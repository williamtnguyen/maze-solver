package cs146F19.zhu.project3;


public class ASCIIGrid { 
	//In an ASCII Grid, you'll include all the walls, actual cells, and corners
	//Rows...if n%2 = 0, "+-+-+-+" top/bottom walls and corners
	//    ...if n%2 = 1, "|.|.|.|" left/right walls and cells
	//Cols...if n%2 = 0, "+|+|+|+" corners, left/right walls
	//    ...if n%2 = 1, "-.-.-.-" top/bottom walls, cells
	
	//Special case for first and last row/col cells. So if cell 1, can't remove left wall... etc.
	private int xSize;
	private int ySize;
	private String[][] array;
	private Cell[][] cellArray;
	private int xSizeASCII;
	private int ySizeASCII;
	private int xASCII;
	private int yASCII;
	
	public ASCIIGrid(int xSize, int ySize){ //Input number of rows and cols
		this.xSize=xSize;
		this.ySize=ySize;
		
		xSizeASCII=(xSize*2)+1;
		ySizeASCII=(ySize*2)+1;
		
	}
	
	public ASCIIGrid(int xSize, int ySize, Cell[][] cellArray){
		this.xSize=xSize;
		this.ySize=ySize;
		this.cellArray=cellArray;
		
		xSizeASCII=(xSize*2)+1;
		ySizeASCII=(ySize*2)+1;
	}
	
	
	public String[][] generateASCIIGrid(){ //rows, cols
		array = new String[xSizeASCII][ySizeASCII]; // 9 x 9 ASCII Grid for a 4x4 Maze Grid
//		System.out.println(xSizeASCII +"x"+ySizeASCII +" ASCII Grid for a "+ xSize+"x"+ySize
//							+" Maze Grid");
		for(int x=0; x<xSizeASCII; x++){
			for(int y=0; y<ySizeASCII; y++){
				if(x%2==0){ //Even Rows
					if(y%2==0){//Even Columns
						array[x][y] = "+";
					}
					else if(y%2==1){//Odd Columns
						array[x][y] = "-";
					}			
				}
				else if(x%2==1){//Odd Columns
					if(y%2==0){//Even Columns
						array[x][y] = "|";
					}
					else if(y%2==1){//Odd Columns
						array[x][y] = ".";
					}
				}
			}
		}
		return array;
	}
	
	public String[][] updateASCIIGrid(){
		String[][] updated = new String[xSizeASCII][ySizeASCII];
		updated = generateASCIIGrid(); // 9 x 9 ASCII Grid for a 4x4 Maze Grid
		System.out.println(xSizeASCII +"x"+ySizeASCII +" ASCII Grid for a "+ xSize+"x"+ySize
							+" Maze Grid");
		
		//Access the inputed cell[][] and check each cell's walls
		for(int x=0; x<cellArray.length; x++){
			for(int y=0; y<cellArray[x].length; y++){
				xASCII = (cellArray[x][y].getX()*2)+1;
				yASCII = (cellArray[x][y].getY()*2)+1;
				if(cellArray[x][y].getNorthWall()==false){ //no special cases, all walls can be broken down
//					System.out.println("North, x: "+xASCII+", y: "+yASCII);
					if((updated[xASCII-1][yASCII])=="-"){
						System.out.println("Truee");
						updated[xASCII-1][yASCII] = " ";	
					}
				}
				if(cellArray[x][y].getEastWall()==false){ //Right Perimeter walls can't be broken down.
					if(cellArray[x][y] == cellArray[x][cellArray[x].length-1]){
						System.out.println("Yo that can't be broken down bro");
					}
					if((updated[xASCII][yASCII+1])=="|"){
						updated[xASCII][yASCII+1] = " ";	
					}
				}
				if(cellArray[x][y].getSouthWall()==false){ //no special cases, ""
					if((updated[xASCII+1][yASCII])=="-"){
						updated[xASCII+1][yASCII] = " ";	
					}
				}
				if(cellArray[x][y].getWestWall()==false){ //Left perimeter walls can't be broken down.
					if(cellArray[x][y] == cellArray[x][0]){
						System.out.println("Nope, not this wall");
						updated[xASCII][yASCII-1] = "X";
					}
					else if((updated[xASCII][yASCII-1])=="|"){
						updated[xASCII][yASCII-1] = " ";	
					}
				}
			}
		}
		
		return array;
	}
}

