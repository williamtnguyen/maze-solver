package cs146F19.zhu.project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class TextFileReader {
	private File f;
	private String[][] ASCIIGrid;
	private Cell[][] cellGrid;
	private ArrayList<String> dataArray;
	private int xSize;
	private int ySize;
	
	
	public TextFileReader(File f){
		this.f = f;
	}
	
	public ArrayList<String> copyToList() throws Exception { //Copy the data in txt file to an ArrayList
		xSize=-1;
		ySize=-1;
		dataArray = new ArrayList<String>();
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String s;
		String firstLine = br.readLine(); 
		System.out.println("FirstLine: " + firstLine);

		String[] gridSize = firstLine.split(" ");
		System.out.println("Size: "+gridSize.length);
		for(int i=0; i<gridSize.length; i++){
			if(xSize==-1){
				xSize=Integer.parseInt(gridSize[i]);
			}
			else if(ySize==-1){
				ySize=Integer.parseInt(gridSize[i]);
			}
		}
		while ((s = br.readLine()) != null) {
			dataArray.add(s);
		}
//		System.out.println("Read: "+System.currentTimeMillis());
		fr.close();
		br.close();
		return dataArray;
	}
	
	public String[][] listToASCIIGrid(){
//		for(int )
		return null;
	}
	
	public int getXSize(){
		return this.xSize;
	}
	public int getYSize(){
		return this.ySize;
	}
}
