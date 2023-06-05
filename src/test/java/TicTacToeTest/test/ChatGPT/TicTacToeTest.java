package TicTacToeTest.test.ChatGPT;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import TicTacToe.tic.tac.toe.TicTacToe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

public class TicTacToeTest {

    final String prefix = "src/test/java/TicTacToeTest/test/resources/" ;
    final String simple_2_players = "config_simple_2_players.properties";

    public void feedStringToSystemIn(String s){
        InputStream in = new ByteArrayInputStream(s.getBytes());
        System.setIn(in);
    }


    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    private String getConsoleOutput() {
        System.setOut(new PrintStream(outputStream));
        return outputStream.toString().trim();
    }

    @After
    public void restoreSystemIn() {
        System.setIn(System.in) ;
    }

    @Test
    public void testPlayer1WinsVertical() {
        // Set up the input stream to provide moves
        String moves = "1,1\n2,1\n1,2\n2,2\n1,3\n";
        feedStringToSystemIn(moves);

        // Set up the output stream to capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the TicTacToe game
        TicTacToe.main(new String[]{prefix + simple_2_players});

        // Restore the original input and output streams
        restoreSystemIn();
        System.setOut(System.out);

        // Get the last output line
        String output = outContent.toString().trim().split("\n")[outContent.toString().trim().split("\n").length - 1];

        // Verify the output
        Assertions.assertEquals("Player 1 wins !!!!", output);
    }

    @Test
    public void testPlayer1WinsDiagonal() {
        // Set up the input stream to provide moves
        String moves = "1,1\n2,1\n2,2\n3,2\n3,3\n";
        feedStringToSystemIn(moves);

        // Set up the output stream to capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the TicTacToe game
        TicTacToe.main(new String[]{prefix + simple_2_players});

        // Restore the original input and output streams
        restoreSystemIn();
        System.setOut(System.out);

        // Get the last output line
        String output = outContent.toString().trim().split("\n")[outContent.toString().trim().split("\n").length - 1];

        // Verify the output
        Assertions.assertEquals("Player 1 wins !!!!", output);
    }

    @Test
    public void testPlayer2WinsHorizontal() {
        // Set up the input stream to provide moves
        String moves = "1,1\n2,1\n1,2\n2,2\n3,1\n2,3\n";
        feedStringToSystemIn(moves);

        // Set up the output stream to capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the TicTacToe game
        TicTacToe.main(new String[]{prefix + simple_2_players});

        // Restore the original input and output streams
        restoreSystemIn();
        System.setOut(System.out);

        // Get the last output line
        String output = outContent.toString().trim().split("\n")[outContent.toString().trim().split("\n").length - 1];

        // Verify the output
        Assertions.assertEquals("Player 2 wins !!!!", output);
    }

    @Test
    public void testPlayer2WinsDiagonal() {
        // Set up the input stream to provide moves
        String moves = "1,1\n1,3\n1,2\n2,2\n3,3\n3,1\n";
        feedStringToSystemIn(moves);

        // Set up the output stream to capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the TicTacToe game
        TicTacToe.main(new String[]{prefix + simple_2_players});

        // Restore the original input and output streams
        restoreSystemIn();
        System.setOut(System.out);

        // Get the last output line
        String output = outContent.toString().trim().split("\n")[outContent.toString().trim().split("\n").length - 1];

        // Verify the output
        Assertions.assertEquals("Player 2 wins !!!!", output);
    }

    @Test
    public void testInvalidMove() {
        // Set up the input stream to provide moves with invalid characters
        String moves = "1-1\n1+1\n1,1\n1,2\n3$2\n2,1\n2,2\n5,5\n3,1\n";
        feedStringToSystemIn(moves);

        // Set up the output stream to capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the TicTacToe game
        TicTacToe.main(new String[]{prefix + simple_2_players});

        // Restore the original input and output streams
        restoreSystemIn();
        System.setOut(System.out);

        // Get the last output line
        String output = outContent.toString().trim().split("\n")[outContent.toString().trim().split("\n").length - 1];

        // Verify the output
        Assertions.assertEquals("Player 1 wins !!!!", output);
    }
}
