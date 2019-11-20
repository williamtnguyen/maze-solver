package cs146F19.zhu.project3;


public class MazeGrid {
	private int xSize;
	private int ySize;
	
	public MazeGrid(int xSize, int ySize){
		this.xSize=xSize;
		this.ySize=ySize;
	}
	
	public int getX(int val){
		return xSize;
	}
	
	public int getY(int val){
		return ySize;
	}
	
}

