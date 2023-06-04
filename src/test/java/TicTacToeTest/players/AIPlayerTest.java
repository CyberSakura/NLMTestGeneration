package TicTacToeTest.players;

import TicTacToe.exceptions.GameException;
import TicTacToe.move.Move;
import TicTacToe.players.AIPlayer;
import TicTacToe.players.PLAYERTYPE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AIPlayerTest {

    private AIPlayer aiPlayerUnderTest;

    @BeforeEach
    void setUp() {
        aiPlayerUnderTest = new AIPlayer(0, 'a', PLAYERTYPE.HUMAN);
    }

    @Test
    void testPlay() {
        // Setup
        // Run the test
        aiPlayerUnderTest.play();

        // Verify the results
    }

    @Test
    void testGetAnyAvailableMove() {
        // Setup
        // Run the test
        final Move result = aiPlayerUnderTest.getAnyAvailableMove();

        // Verify the results
    }

    @Test
    void testGetRandomMove() throws Exception {
        // Setup
        // Run the test
        final Move result = aiPlayerUnderTest.getRandomMove();

        // Verify the results
    }

    @Test
    void testGetRandomMove_ThrowsGameException() {
        // Setup
        // Run the test
        assertThrows(GameException.class, () -> aiPlayerUnderTest.getRandomMove());
    }
}
