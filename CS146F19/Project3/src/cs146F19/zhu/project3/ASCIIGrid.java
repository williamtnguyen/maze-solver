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
	
	
	public ASCIIGrid(int xSize, int ySize){
		
	}
	
	public String[][] generateASCIIGrid(int xSize, int ySize){ //rows, cols
		int xSizeASCII=(xSize*2)+1;
		int ySizeASCII=(ySize*2)+1;
		array = new String[xSizeASCII][ySizeASCII]; // 9 x 9 ASCII Grid for a 4x4 Maze Grid
		System.out.println(xSizeASCII +"x"+ySizeASCII +" ASCII Grid for a "+ xSize+"x"+ySize
							+" Maze Grid");
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
}

