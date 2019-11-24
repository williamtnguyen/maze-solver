package cs146F19.zhu.project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

//Takes in a file and ultimately converts to a cell[][] that we can use to make a maze
//Tested in FileTester.java
public class TextFileReader {
	private File f;
	private String[][] ASCIIGrid;
	private Cell[][] cellGrid;
	private ArrayList<String> dataArray;
	private int xSize;
	private int ySize;
	
	//Constructor
	public TextFileReader(File f){
		this.f = f;
	}
	
	//Copies first line and grabs the dimensions of maze, then copies the rest into an ArrayList
	public ArrayList<String> copyToList() throws Exception {
		//initialize vars
		xSize=-1;
		ySize=-1;
		dataArray = new ArrayList<String>();
		String s;

		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String firstLine = br.readLine(); 
//		System.out.println("FirstLine: " + firstLine);
		
		//Grab dimensions
		String[] gridSize = firstLine.split(" ");
//		System.out.println("Size: "+gridSize.length);
		for(int row=0; row<gridSize.length; row++){
			if(xSize==-1){
				xSize=Integer.parseInt(gridSize[row]);
			}
			else if(ySize==-1){
				ySize=Integer.parseInt(gridSize[row]);
			}
		}
		
		//Copy all other lines into ArrayList
		while ((s = br.readLine()) != null) {
			dataArray.add(s);
		}
		
		fr.close();
		br.close();
		
		return dataArray;
	}
	
	//Takes the ArrayList of lines and converts to a String[][] ascii grid
	public String[][] listToASCIIGrid(){
		//initialize
		ASCIIGrid = new String[(xSize*2)+1][(ySize*2)+1];
		
		//Double for loop to traverse ArrayList then every character in each string
		for(int row=0; row<dataArray.size(); row++){
			String arrayElement =dataArray.get(row);
			String[] splitToSingleCharacters = arrayElement.split("");
			for(int col=0; col<splitToSingleCharacters.length; col++){
				ASCIIGrid[row][col] = splitToSingleCharacters[col];
			}
		}
		return ASCIIGrid;
	}
	
	//TO DO:
	//Converts the String[][] ascii grid to a cell grid
	public Cell[][] ASCIIGridToCellGrid(){
		cellGrid = new Cell[xSize][ySize];
		
		for(int x=0; x<xSize; x++){
			for(int y=0; y<ySize; y++){
				cellGrid[x][y] = new Cell(x,y);
			}
		}
		
		for(int x=0; x<xSize; x++){
			for(int y=0; y<ySize; y++){
				int xASCII = (x*2)+1;
				int yASCII = (y*2)+1;
//				System.out.println(xASCII + ", "+yASCII);
				if(cellGrid[x][y] == cellGrid[0][0]){ //Special case: first cell
					if(!ASCIIGrid[xASCII][yASCII+1].equals("|")){ //If no wall to the right, then add connection
						cellGrid[x][y].addEdge(cellGrid[x][y+1]);
					}
					if(!ASCIIGrid[xASCII+1][yASCII].equals("-")){ //If no wall below
						cellGrid[x][y].addEdge(cellGrid[x+1][y]);
					}
				}
				else if(cellGrid[x][y] == cellGrid[xSize-1][ySize-1]){ //Special case: last cell
					if(!ASCIIGrid[xASCII][yASCII-1].equals("|")){ //If no wall to left
						cellGrid[x][y].addEdge(cellGrid[x][y-1]);
					}
					if(!ASCIIGrid[xASCII-1][yASCII].equals("-")){ //If no wall above
						cellGrid[x][y].addEdge(cellGrid[x-1][y]);
					}
				}
				else{ //Base case
					if((x+1)<xSize){ //Within upper row dimension bounds
						if(!ASCIIGrid[xASCII+1][yASCII].equals("-")){ //If no wall below
							cellGrid[x][y].addEdge(cellGrid[x+1][y]);
						}
					}
					if((x-1)>0){ //Within lower row dimension bounds
						if(!ASCIIGrid[xASCII-1][yASCII].equals("-")){ //If no wall above
							cellGrid[x][y].addEdge(cellGrid[x-1][y]);
						}
					}
					if((y-1)>0){ //Within lower col dimension bounds
						if(!ASCIIGrid[xASCII][yASCII-1].equals("|")){ //If no wall to left
							cellGrid[x][y].addEdge(cellGrid[x][y-1]);
						}
					}
					if((y+1)<ySize){ //Within upper col dimension bounds
						if(!ASCIIGrid[xASCII][yASCII+1].equals("|")){ //If no wall to the right, then add connection
							cellGrid[x][y].addEdge(cellGrid[x][y+1]);
						}
					}
				}
			}
		}
		
		
		return cellGrid;
	}
	//And then from here, we can probably just create a new maze, and manually change the 
	//private variables (cell[][], vertices, all the other data needed) within the maze 
	//and run all the algorithms needed to solve the maze.
	
	//BFS and DFS require connections for each cell, starting point and finishing point
	
	
	public int getXSize(){
		return this.xSize;
	}
	public int getYSize(){
		return this.ySize;
	}
	
	public String[][] getASCIIGrid(){
		return this.ASCIIGrid;
	}
	
	public Cell[][] getCellGrid(){
		return this.cellGrid;
	}
}
