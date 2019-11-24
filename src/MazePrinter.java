/**
 * This class handles the displaying of unsolved mazes,
 * -and the printing of paths for DFS/BFS solutions
 */
public class MazePrinter {

    /* Handles printing all mazes */
    public void printMaze(String[][] maze){
        String print = "";
        for(int x = 0; x < maze.length; x++){
            for(int y = 0; y < maze[0].length; y++){
                print += maze[x][y];
            }
            System.out.println(print);
            print = "";
        }
    }



    // ~~~~~~~~~~~~~~~~~~~~~~~~ //
    /*  IGNORE, random testing */
    // ~~~~~~~~~~~~~~~~~~~~~~~ //

    // All dots are the spots
    // Maze Grid Array [0][0] -> [3][3]
    // In the ASCII Grid Array, values are [1][1] -> [7][7]
    // ASCII Grid should be x2 +1 if 20 x 20 = 40+1
    // Wall above [x][y] [x][y+1]
    // Wall below [x][y] [x][y-1]
    // Wall left  [x][
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
        for(String s : maze){
            System.out.println(s);
        }
    }
}
