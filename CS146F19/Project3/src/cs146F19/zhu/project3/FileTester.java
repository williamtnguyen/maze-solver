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
//		TextFileReader t = new TextFileReader(m6);
//		TextFileReader t = new TextFileReader(m8);
		TextFileReader t = new TextFileReader(m10);
//		TextFileReader t = new TextFileReader(m20);

		
		ArrayList<String> gridList= t.copyToList();
		System.out.println("X: "+ t.getXSize()+", Y: " + t.getYSize());
		
		String[][] s = t.listToASCIIGrid();
		System.out.println("Grid to Solve:");
		m.printMaze(t.listToASCIIGrid());
		Cell[][] c = t.ASCIIGridToCellGrid();
		Maze dfsTest = new Maze(t.getXSize());
		dfsTest.replaceCellGrid(c);
		dfsTest.replaceStartAndFinishCell(c);
		ArrayList<Cell> dfs=dfsTest.solveDFS();

		Maze bfsTest = new Maze(t.getXSize());
		bfsTest.replaceCellGrid(c);
		bfsTest.replaceStartAndFinishCell(c);
		ArrayList<ArrayList<Cell>> traversalBFS = bfsTest.solveBFS();
		ArrayList<Cell> bfs = traversalBFS.get(0);
		ArrayList<Cell> bfsShortestPath = traversalBFS.get(1);

		ASCIIGrid dfsTestASCII = new ASCIIGrid(dfsTest, t.listToASCIIGrid());
		ASCIIGrid bfsTestASCII = new ASCIIGrid(bfsTest, t.listToASCIIGrid());

		String[][] gridSolvedDFS = dfsTestASCII.updateASCIIGridWithTraversalPath(dfs);
		System.out.println("Solved DFS: ");
		m.printMaze(gridSolvedDFS);
//		
		String[][] gridSolvedBFS = bfsTestASCII.updateASCIIGridWithTraversalPath(bfs);
		System.out.println("Solved BFS: ");
		m.printMaze(gridSolvedBFS);
//		
		String[][] gridSolvedShortest = bfsTestASCII.updateASCIIGridWithShortestPath(bfsShortestPath);
		System.out.println("Solved ShortestPath: ");
		m.printMaze(gridSolvedShortest);
		
		//Professor Potika's Sample Output in guidelines:
		t.arrayToTxt(dfsTestASCII, bfsTestASCII);
		
	}
}
