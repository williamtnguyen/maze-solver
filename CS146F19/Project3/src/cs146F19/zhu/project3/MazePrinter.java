package cs146F19.zhu.project3;

/**
 * This class handles the displaying of unsolved mazes,
 * -and the printing of paths for DFS/BFS solutions
 */
public class MazePrinter {

    public MazePrinter(Maze maze) {

    }
    
    public MazePrinter(){
    	
    }

    /**
     * Handles displaying the unsolved maze
     */
    public void displayGraph() {

    }

    /**
     * Prints DFS visit order on the simulated maze
     */
    public void printVisitOrderDFS() {

    }

    /**
     * Prints BFS visit order on the simulated maze
     */
    public void printVisitOrderBFS() {

    }

    /**
     * Prints the shortest-path from start to finish
     */
    public void printPath() {

    }
    
	//All dots are the spots
	//Maze Grid Array [0][0] -> [3][3]
	//In the ASCII Grid Array, values are [1][1] -> [7][7] 
	//ASCII Grid should be x2 +1 if 20 x 20 = 40+1
	//Wall above [x][y] [x][y+1]
	//Wall below [x][y] [x][y-1]
	//Wall left  [x][
	public static final String[] maze = {
		 "+-+-+-+-+",
		 "|.|.|.|.|",
		 "+-+-+-+-+",
		 "|.|.|.|.|",
		 "+-+-+-+-+",
		 "|.|.|.|.|",
		 "+-+-+-+-+",
		 "|.|.|.|.|",
		 "+-+-+-+-+"
	};
	
	public void printTestMaze(){
		for(String s: maze){
			System.out.println(s);
		}
	}
	public static void printMaze(String[][] maze){
		String print="";
		for(int x=0; x<maze.length; x++){
			for(int y=0; y<maze[0].length; y++){
				print+=maze[x][y];
			}
			System.out.println(print);
			print="";
		}
	}
}
