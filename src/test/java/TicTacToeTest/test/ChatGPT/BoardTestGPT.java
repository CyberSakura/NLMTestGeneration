package TicTacToeTest.test.ChatGPT;

import TicTacToe.exceptions.InvalidConfigurationException;
import TicTacToe.exceptions.NotAvailableMoveException;
import TicTacToe.move.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TicTacToe.players.AIPlayer;
import TicTacToe.players.PLAYERTYPE;
import TicTacToe.players.Player;
import TicTacToe.tic.tac.toe.Board;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BoardTestGPT {

    private Board board;

    @BeforeEach
    public void setup() throws InvalidConfigurationException {
        int size = 3;
        board = new Board(size);
    }

    @Test
    public void testCheckCellAvailability() {
        int x = 1; // Valid range: [1, 3]
        int y = 1; // Valid range: [1, 3]

        // Check initial cell availability
        boolean initialAvailability = board.checkCellAvailability(x, y);
        Assertions.assertTrue(initialAvailability);

        // Mark the cell
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI); // Initialize a player object
        Move move = new Move(x, y, player);
        Assertions.assertDoesNotThrow(() -> board.mark(move));

        // Check marked cell availability
        boolean markedAvailability = board.checkCellAvailability(x, y);
        Assertions.assertTrue(markedAvailability);
    }

    @Test
    public void testCheckMoveAvailability() {
        int x = 1; // Valid range: [1, 3]
        int y = 1; // Valid range: [1, 3]

        // Create a player and mark the cell
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        Move move = new Move(x, y, player);
        Assertions.assertDoesNotThrow(() -> board.mark(move));

        // Check move availability for the marked cell
        Assertions.assertThrows(NotAvailableMoveException.class, () -> board.checkMoveAvailability(move));
    }

    @Test
    public void testMark() {
        int x = 1; // Valid range: [1, 3]
        int y = 1; // Valid range: [1, 3]

        // Create a player and mark the cell
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        Move move = new Move(x, y, player);
        Assertions.assertDoesNotThrow(() -> board.mark(move));

        // Verify that the cell is correctly marked
        char markedSymbol = board.getBoard()[x - 1][y - 1];
        char expectedSymbol = player.getMark();
        Assertions.assertEquals(expectedSymbol, markedSymbol);

        // Verify that the availability of the cell is updated
        boolean availability = board.checkCellAvailability(x - 1, y - 1);
        Assertions.assertFalse(availability);
    }

    @Test
    public void testCheckHorizontally() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 1, player));
        board.mark(new Move(2, 1, player));
        board.mark(new Move(3, 1, player));

        // Verify the horizontal check
        boolean isHorizontal = board.checkHorizontally(new Move(2, 1, player), 3);
        Assertions.assertTrue(isHorizontal);
    }

    @Test
    public void testCheckHorizontallyFalse() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 1, player));
        board.mark(new Move(2, 1, player));

        // Verify the horizontal check
        boolean isHorizontal = board.checkHorizontally(new Move(2, 1, player), 3);
        Assertions.assertFalse(isHorizontal);
    }

    @Test
    public void testCheckVertically() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 1, player));
        board.mark(new Move(1, 2, player));
        board.mark(new Move(1, 3, player));

        // Verify the vertical check
        boolean isVertical = board.checkVertically(new Move(1, 2, player), 3);
        Assertions.assertTrue(isVertical);
    }

    @Test
    public void testCheckVerticallyFalse() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 1, player));
        board.mark(new Move(1, 2, player));

        // Verify the vertical check
        boolean isVertical = board.checkVertically(new Move(1, 2, player), 3);
        Assertions.assertFalse(isVertical);
    }

    @Test
    public void testCheckDiagonallyRight() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 1, player));
        board.mark(new Move(2, 2, player));
        board.mark(new Move(3, 3, player));

        // Verify the diagonal right check
        boolean isDiagonalRight = board.checkDiagonallyRight(new Move(2, 2, player), 1);
        Assertions.assertTrue(isDiagonalRight);
    }

    @Test
    public void testCheckDiagonallyLeft() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 3, player));
        board.mark(new Move(2, 2, player));
        board.mark(new Move(3, 1, player));

        // Verify the diagonal left check
        boolean isDiagonalLeft = board.checkDiagonallyLeft(new Move(2, 2, player), 1);
        Assertions.assertTrue(isDiagonalLeft);
    }

    @Test
    public void testPrintBoard() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 1, player));
        board.mark(new Move(2, 2, player));
        board.mark(new Move(3, 3, player));

        // Verify the board printing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        board.printBoard();

        String expectedOutput = "X.." + System.lineSeparator() + ".X." + System.lineSeparator() + "..X" + System.lineSeparator(); // Expected board output
        String printedBoard = outputStream.toString();
        Assertions.assertEquals(expectedOutput, printedBoard);
    }

    @Test
    public void testGetNumberOfAvailableCells() throws InvalidConfigurationException {
        // Set up the board
        Board board = new Board(3);
        Player player = new AIPlayer(1, 'X', PLAYERTYPE.AI);
        board.mark(new Move(1, 1, player));
        board.mark(new Move(2, 2, player));

        // Verify the number of available cells
        int numberOfAvailableCells = board.getNumberOfAvailableCells();
        Assertions.assertEquals(7, numberOfAvailableCells);
    }
}
