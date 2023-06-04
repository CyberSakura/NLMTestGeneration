package TicTacToeTest.toe;

import TicTacToe.exceptions.InvalidConfigurationException;
import TicTacToe.exceptions.InvalidInputException;
import TicTacToe.exceptions.NotAvailableMoveException;
import TicTacToe.move.Move;
import TicTacToe.tic.tac.toe.Board;
import TicTacToe.tic.tac.toe.GameMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameMasterTest {

    private GameMaster gameMasterUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        gameMasterUnderTest = new GameMaster(0, new HashMap<>(), 0);
    }

    @Test
    void testLoadGameMaster() throws Exception {
        // Run the test
        final GameMaster result = GameMaster.loadGameMaster("configFilePath");
        assertFalse(result.isThereMoreAvailableMoves());
        assertEquals(0, result.validateInRange(0, 0, 0));
        assertFalse(result.checkCellAvailability(0, 0));
        assertFalse(result.isGameEnded());
        assertEquals(new Board(0), result.getBoard());
        assertEquals(new HashMap<>(), result.getPlayersMap());
        assertEquals(new ArrayList<>(Arrays.asList(new Move(0, 0, null))), result.getHistoryOfMoves());
        assertEquals(new Scanner(new ByteArrayInputStream("content".getBytes()), "UTF-8"), result.getScanner());
    }

    @Test
    void testLoadGameMaster_ThrowsInvalidConfigurationException() {
        // Setup
        // Run the test
        assertThrows(InvalidConfigurationException.class, () -> GameMaster.loadGameMaster("configFilePath"));
    }

    @Test
    void testPlayTheTurn() {
        // Setup
        // Run the test
        gameMasterUnderTest.playTheTurn();

        // Verify the results
    }

    @Test
    void testDoMove() {
        // Setup
        final Move move = new Move(0, 0, null);

        // Run the test
        gameMasterUnderTest.doMove(move);

        // Verify the results
    }

    @Test
    void testIsThereMoreAvailableMoves() {
        // Setup
        // Run the test
        final boolean result = gameMasterUnderTest.isThereMoreAvailableMoves();

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testValidateInRange() throws Exception {
        assertEquals(0, gameMasterUnderTest.validateInRange(0, 0, 0));
        assertThrows(InvalidInputException.class, () -> gameMasterUnderTest.validateInRange(0, 0, 0));
    }

    @Test
    void testCheckMoveAvailability() throws Exception {
        // Setup
        final Move move = new Move(0, 0, null);

        // Run the test
        gameMasterUnderTest.checkMoveAvailability(move);

        // Verify the results
    }

    @Test
    void testCheckMoveAvailability_ThrowsNotAvailableMoveException() {
        // Setup
        final Move move = new Move(0, 0, null);

        // Run the test
        assertThrows(NotAvailableMoveException.class, () -> gameMasterUnderTest.checkMoveAvailability(move));
    }

    @Test
    void testCheckCellAvailability() {
        // Setup
        // Run the test
        final boolean result = gameMasterUnderTest.checkCellAvailability(0, 0);

        // Verify the results
        assertFalse(result);
    }
}
