import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.*;

public class MazeRunnerTest {

    /**
     * ~ WORKS AS EXPECTED ~
     * Writes to "actualOutput" files and tests to see if they match "expectedOutput" files
     * To check results, please go to "./outputs" folder in the workspace
     */
    @Test
    public void writeToFile() throws Exception {
        File maze4 = new File("./sample inputs/maze4.txt");
        File maze6 = new File("./sample inputs/maze6.txt");
        File maze8 = new File("./sample inputs/maze8.txt");
        File maze10 = new File("./sample inputs/maze10.txt");
        File maze20 = new File("./sample inputs/maze20.txt");

        // This will write to all the respective "actualOutput" files in "./outputs"
        MazeRunner mazeRunner = new MazeRunner();
        mazeRunner.writeToFile(maze4, "./outputs/actualOutput4.txt");
        mazeRunner.writeToFile(maze6, "./outputs/actualOutput6.txt");
        mazeRunner.writeToFile(maze8, "./outputs/actualOutput8.txt");
        mazeRunner.writeToFile(maze10, "./outputs/actualOutput10.txt");
        mazeRunner.writeToFile(maze20, "./outputs/actualOutput20.txt");

        // 4x4 testing
        FileReader fr4Actual = new FileReader("./outputs/actualOutput4.txt");
        FileReader fr4Expected = new FileReader("./outputs/expectedOutput4.txt");
        BufferedReader br4Actual = new BufferedReader(fr4Actual);
        BufferedReader br4Expected = new BufferedReader(fr4Expected);
        // 6x6 testing
        FileReader fr6Actual = new FileReader("./outputs/actualOutput6.txt");
        FileReader fr6Expected = new FileReader("./outputs/expectedOutput6.txt");
        BufferedReader br6Actual = new BufferedReader(fr6Actual);
        BufferedReader br6Expected = new BufferedReader(fr6Expected);
        // 8x8 testing
        FileReader fr8Actual = new FileReader("./outputs/actualOutput8.txt");
        FileReader fr8Expected = new FileReader("./outputs/expectedOutput8.txt");
        BufferedReader br8Actual = new BufferedReader(fr8Actual);
        BufferedReader br8Expected = new BufferedReader(fr8Expected);
        // 10x10 testing
        FileReader fr10Actual = new FileReader("./outputs/actualOutput10.txt");
        FileReader fr10Expected = new FileReader("./outputs/expectedOutput10.txt");
        BufferedReader br10Actual = new BufferedReader(fr10Actual);
        BufferedReader br10Expected = new BufferedReader(fr10Expected);
        // 20x20 testing
        FileReader fr20Actual = new FileReader("./outputs/actualOutput20.txt");
        FileReader fr20Expected = new FileReader("./outputs/expectedOutput20.txt");
        BufferedReader br20Actual = new BufferedReader(fr20Actual);
        BufferedReader br20Expected = new BufferedReader(fr20Expected);

        // Now we test if actual and expected files are exactly the same line by line
        boolean flag = false;
        while(!flag) {
            String expected = br4Expected.readLine();
            String actual = br4Actual.readLine();
            if(expected == null || actual == null) {
                flag = true;
            }
            assertEquals(expected, actual);
        }
        flag = false;
        while(!flag) {
            String expected = br6Expected.readLine();
            String actual = br6Actual.readLine();
            if(expected == null || actual == null) {
                flag = true;
            }
            assertEquals(expected, actual);
        }
        flag = false;
        while(!flag) {
            String expected = br8Expected.readLine();
            String actual = br8Actual.readLine();
            if(expected == null || actual == null) {
                flag = true;
            }
            assertEquals(expected, actual);
        }
        flag = false;
        while(!flag) {
            String expected = br10Expected.readLine();
            String actual = br10Actual.readLine();
            if(expected == null || actual == null) {
                flag = true;
            }
            assertEquals(expected, actual);
        }
        flag = false;
        while(!flag) {
            String expected = br20Expected.readLine();
            String actual = br20Actual.readLine();
            if(expected == null || actual == null) {
                flag = true;
            }
            assertEquals(expected, actual);
        }
    }
}