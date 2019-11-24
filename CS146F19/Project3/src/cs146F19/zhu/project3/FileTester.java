package cs146F19.zhu.project3;

import java.io.File;
import java.util.ArrayList;

//Test class for TextFileReader
public class FileTester {
	public static void main(String[] args) throws Exception{
		MazePrinter m = new MazePrinter();
		File m4 =new File("sample-inputs/maze4.txt");
		File m6 =new File("sample-inputs/maze6.txt");
		File m8 =new File("sample-inputs/maze8.txt");
		File m10 =new File("sample-inputs/maze10.txt");
		File m20 =new File("sample-inputs/maze20.txt");
//		TextFileReader t = new TextFileReader(m4);
		TextFileReader t = new TextFileReader(m6);
//		TextFileReader t = new TextFileReader(m8);
//		TextFileReader t = new TextFileReader(m10);
//		TextFileReader t = new TextFileReader(m20);

		
		ArrayList<String> gridList= t.copyToList();
		System.out.println("X: "+ t.getXSize()+", Y: " + t.getYSize());
		
//		for(int x=0; x<gridList.size(); x++){
//			System.out.println(gridList.get(x));
//		}
		m.printMaze(t.listToASCIIGrid());

	}
}
