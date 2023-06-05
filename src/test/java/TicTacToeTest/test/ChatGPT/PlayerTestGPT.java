package TicTacToeTest.test.ChatGPT;

import TicTacToe.exceptions.GameException;
import TicTacToe.exceptions.InvalidConfigurationException;
import TicTacToe.exceptions.InvalidInputException;
import TicTacToe.move.Move;
import TicTacToe.tic.tac.toe.Board;
import TicTacToe.tic.tac.toe.GameMaster;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TicTacToe.players.AIPlayer;
import TicTacToe.players.HumanPlayer;
import TicTacToe.players.PLAYERTYPE;
import TicTacToe.players.Player;

import java.util.HashMap;

public class PlayerTestGPT {
    private HashMap<Integer, Player> playersMap;
    private HumanPlayer humanPlayer;
    private AIPlayer aiPlayer;

    @BeforeEach
    public void setUp() {
        playersMap = new HashMap<>();
        humanPlayer = new HumanPlayer(1, 'X', PLAYERTYPE.HUMAN);
        aiPlayer = new AIPlayer(2, 'O', PLAYERTYPE.AI);
        playersMap.put(1, humanPlayer);
        playersMap.put(2, aiPlayer);
    }

    @Test
    public void testValidateIsNumber() {
        // Set up the test player
        Player player = new HumanPlayer(1, 'X', PLAYERTYPE.HUMAN);

        // Test valid input
        try {
            player.validateIsNumber("5");
            // No exception thrown, test passed
        } catch (InvalidInputException ex) {
            Assertions.fail("Unexpected InvalidInputException occurred: " + ex.getMessage());
        }

        // Test invalid input
        try {
            player.validateIsNumber("abc");
            Assert.fail("Expected InvalidInputException");
        } catch (InvalidInputException ex) {
            Assert.assertEquals("Input must be a valid number", ex.getMessage());
        }
    }

    @Test
    public void testValidateMoveWithoutComma() {
        // Set up the test player
        Player player = new HumanPlayer(1, 'X', PLAYERTYPE.HUMAN);

        // Test move input without a comma
        try {
            player.validateMove("12");
            Assert.fail("Expected GameException");
        } catch (GameException ex) {
            Assert.assertEquals("A valid move should be in the format x,y ... please separate the 2 numbers with , and make sure they are only 2 numbers", ex.getMessage());
        }
    }

    @Test
    public void testValidateMoveWithDash() {
        // Set up the test player
        Player player = new HumanPlayer(1, 'X', PLAYERTYPE.HUMAN);

        // Test move input with a dash
        try {
            player.validateMove("1-2");
            Assert.fail("Expected GameException");
        } catch (GameException ex) {
            Assert.assertEquals("A valid move should be in the format x,y ... please separate the 2 numbers with , and make sure they are only 2 numbers", ex.getMessage());
        }
    }

    @Test
    public void testGetAnyAvailableMove() throws InvalidConfigurationException {
        // Set up the test game master and board
        GameMaster gameMaster = new GameMaster(3, playersMap, 2);
        Board board = gameMaster.getBoard();

        // Mark some moves on the board
        board.mark(new Move(1, 1, aiPlayer));
        board.mark(new Move(2, 2, aiPlayer));

        // Get any available move
        Move move = aiPlayer.getAnyAvailableMove();

        // Verify the move
        Assertions.assertNotNull(move);
        Assertions.assertTrue(board.checkCellAvailability(move.getX(), move.getY()));
    }

    @Test
    public void testGetRandomMove() throws InvalidConfigurationException {
        // Set up the test game master and board
        GameMaster gameMaster = new GameMaster(3, playersMap, 2);
        Board board = gameMaster.getBoard();

        // Mark some moves on the board
        board.mark(new Move(1, 1, aiPlayer));
        board.mark(new Move(2, 2, aiPlayer));

        try {
            // Get a random move
            Move move = aiPlayer.getRandomMove();

            // Verify the move
            Assertions.assertNotNull(move);
            Assertions.assertTrue(board.checkCellAvailability(move.getX(), move.getY()));
        } catch (GameException e) {
            Assert.fail("Shouldn't throw an GameException");
            e.printStackTrace();
        }
    }
}
