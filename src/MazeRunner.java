import java.io.File;
import java.util.ArrayList;

/**
 * A class that writes to a file the specified output Professor Potika specified in prompt
 */
public class MazeRunner {

    /**
     * Will read a file, then output unsolved maze, DFS/BFS traversals, shortest path/coordinates,
     * length of path, and number of visited cells onto filepath "./outputs"
     * @param input the maze to be read an solved
     * @param filePath the file path to be written to
     */
    public void writeToFile(File input, String filePath) throws Exception {

        MazePrinter printer = new MazePrinter();
        MazeFileReader mazeReader = new MazeFileReader(input);

        // Storing the file grid into an ArrayList and printing dimensions
        ArrayList<String> gridList = mazeReader.copyToList();
//        System.out.println("\nX: "+ mazeReader.getXSize() + ", Y: " + mazeReader.getYSize());

        // Conversion from .txt to Cell[][] grid
        String[][] asciiGrid = mazeReader.listToASCIIGrid();
//        System.out.println("Grid to Solve:");
//        printer.printMaze(asciiGrid);
        Cell[][] cellGrid = mazeReader.ASCIIGridToCellGrid();

        // Generating a Cell[][] grid and solving with DFS
        Maze dfsTest = new Maze(mazeReader.getXSize());
        dfsTest.replaceCellGrid(cellGrid);
        dfsTest.replaceStartAndFinishCell(cellGrid);
        ArrayList<Cell> dfs = dfsTest.solveDFS();

        // Generating a Cell[][] grid and solving with BFS
        Maze bfsTest = new Maze(mazeReader.getXSize());
        bfsTest.replaceCellGrid(cellGrid);
        bfsTest.replaceStartAndFinishCell(cellGrid);
        ArrayList<ArrayList<Cell>> traversalBFS = bfsTest.solveBFS();
        ArrayList<Cell> bfs = traversalBFS.get(0);
        ArrayList<Cell> bfsShortestPath = traversalBFS.get(1);

        // Converting our generated and solved Cell[][] grids to ASCII so that we can write it to file
        ASCIIGrid dfsTestASCII = new ASCIIGrid(dfsTest, mazeReader.listToASCIIGrid());
        ASCIIGrid bfsTestASCII = new ASCIIGrid(bfsTest, mazeReader.listToASCIIGrid());

        // Printing DFS grid and solution
        String[][] gridSolvedDFS = dfsTestASCII.updateASCIIGridWithTraversalPath(dfs);
//        System.out.println("\nSolved DFS: ");
//        printer.printMaze(gridSolvedDFS);

        // Printing BFS grid and solution
        String[][] gridSolvedBFS = bfsTestASCII.updateASCIIGridWithTraversalPath(bfs);
//        System.out.println("\nSolved BFS: ");
//        printer.printMaze(gridSolvedBFS);

        // Printing shortest path
        String[][] gridSolvedShortest = bfsTestASCII.updateASCIIGridWithShortestPath(bfsShortestPath);
//        System.out.println("\nSolved ShortestPath: ");
//        printer.printMaze(gridSolvedShortest);

        // Replicating Professor Potika's sample output in guidelines:
        mazeReader.arrayToTxt(dfsTestASCII, bfsTestASCII, dfs, bfs, bfsShortestPath, filePath);
    }
}
