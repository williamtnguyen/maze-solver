import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    private Maze test = new Maze(4);

    @org.junit.jupiter.api.Test
    void getAdjMatrix() {
    }

    @org.junit.jupiter.api.Test
    void getNumVertices() {
    }

    @org.junit.jupiter.api.Test
    void findAdjacentNeighbors() {
        int[][] adjTest = test.getAdjMatrix();
        int ctr = 0;
        for(int i = 0; i < adjTest.length; i++) {
            for(int j = 0; j < adjTest[0].length; j++) {
                adjTest[i][j] = ++ctr;

                System.out.println(adjTest[i][j]);
            }
        }
    }
}