package TicTacToeTest.test.ChatGPT;

import TicTacToe.exceptions.InvalidConfigurationException;
import TicTacToe.exceptions.InvalidInputException;
import TicTacToe.move.Move;
import TicTacToe.players.AIPlayer;
import TicTacToe.players.HumanPlayer;
import TicTacToe.players.PLAYERTYPE;
import TicTacToe.players.Player;
import TicTacToe.tic.tac.toe.GameMaster;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class GameMasterTestGPT {

    HashMap<Integer, Player> playersMap = new HashMap<Integer, Player>();

    final String prefix = "src/test/java/TicTacToeTest/test/resources/";
    final String correct_three_different_types_players = "config.properties";
    final String simple_2_players = "config_simple_2_players.properties";
    final String invalid_board = "invalid_config_board.properties";
    final String invalid_board_string = "invalid_config_board2.properties";
    final String invalid_number_of_players_excess = "invalid_config_more_number_of_players.properties";
    final String invalid_player_mark = "invalid_config_player_character.properties";
    final String invalid_player_type = "invalid_config_player_type.properties";
    final String invalid_player_details = "invalid_config_player_details.properties";

    @Before
    public void setUpPlayers() {
        this.playersMap.put(1, new HumanPlayer(1, 'X', PLAYERTYPE.HUMAN));
        this.playersMap.put(2, new HumanPlayer(2, 'O', PLAYERTYPE.HUMAN));
    }

    @Test
    public void testGMConstructor() throws InvalidConfigurationException {
        // Set up the test parameters
        int boardSize = 5;
        HashMap<Integer, Player> playersMap = new HashMap<>();
        playersMap.put(1, new HumanPlayer(1, 'X', PLAYERTYPE.HUMAN));
        playersMap.put(2, new HumanPlayer(2, 'O', PLAYERTYPE.HUMAN));
        playersMap.put(3, new AIPlayer(3, 'P', PLAYERTYPE.AI));
        int numberOfPlayers = playersMap.size();

        // Create the GameMaster object
        GameMaster gameMaster = new GameMaster(boardSize, playersMap, numberOfPlayers);

        // Verify the GameMaster object
        Assertions.assertNotNull(gameMaster);
        Assertions.assertEquals(boardSize, gameMaster.getBoard().getSize());
        Assertions.assertEquals(playersMap, gameMaster.getPlayersMap());
        Assertions.assertEquals(numberOfPlayers, gameMaster.getPlayersMap().size());

    }

    @Test
    public void testLoadInvalidBoard() {
        // Set up the test parameters
        String configFile = prefix + invalid_board;

        // Perform the loadGameMaster() operation and catch the expected exception
        InvalidConfigurationException exception = Assertions.assertThrows(InvalidConfigurationException.class, () -> {
            GameMaster.loadGameMaster(configFile);
        });

        // Verify the exception message or any other assertions
        Assertions.assertEquals("Board size must be a number from 3 to 10", exception.getMessage());
    }

    @Test
    public void testInvalidBoardString() {
        // Set up the test parameters
        String configFile = prefix + invalid_board_string;

        // Perform the loadGameMaster() operation and catch the expected exception
        InvalidConfigurationException exception = Assertions.assertThrows(InvalidConfigurationException.class, () -> {
            GameMaster.loadGameMaster(configFile);
        });

        // Verify the exception message or any other assertions
        Assertions.assertEquals("Invalid board size number : bla", exception.getMessage());
    }

    @Test
    public void testInvalidMoreNumberOfPlayers() {
        // Set up the test parameters
        String configFile = prefix + invalid_number_of_players_excess;

        // Perform the loadGameMaster() operation and catch the expected exception
        InvalidConfigurationException exception = Assertions.assertThrows(InvalidConfigurationException.class, () -> {
            GameMaster.loadGameMaster(configFile);
        });

        // Verify the exception message or any other assertions
        Assertions.assertEquals("Number of players is larger than number of configured players in the file", exception.getMessage());
    }

    @Test
    public void testInvalidPlayerCharacter() {
        // Set up the test parameters
        String configFile = prefix + invalid_player_mark;

        // Perform the loadGameMaster() operation and catch the expected exception
        InvalidConfigurationException exception = Assertions.assertThrows(InvalidConfigurationException.class, () -> {
            GameMaster.loadGameMaster(configFile);
        });

        // Verify the exception message or any other assertions
        Assertions.assertEquals("Player mark must be one character !! player 1 has invalid mark : Xoxoxoxo", exception.getMessage());
    }

    @Test
    public void testInvalidPlayerType() {
        // Set up the test parameters
        String configFile = prefix + invalid_player_type;

        // Perform the loadGameMaster() operation and catch the expected exception
        InvalidConfigurationException exception = Assertions.assertThrows(InvalidConfigurationException.class, () -> {
            GameMaster.loadGameMaster(configFile);
        });

        // Verify the exception message or any other assertions
        Assertions.assertEquals("Player 1 has invalid type : HUMANOIDY. Player type must be HUMAN or AI ", exception.getMessage());
    }

    @Test
    public void testIncrementTurn() {
        // Set up the test parameters
        String configFile = prefix + correct_three_different_types_players;
        GameMaster gameMaster = null;
        try {
            gameMaster = GameMaster.loadGameMaster(configFile);
        } catch (InvalidConfigurationException ex) {
            Assertions.fail("InvalidConfigurationException occurred: " + ex.getMessage());
        }

        // Perform multiple moves and verify the turn order
        Player player1 = gameMaster.getPlayersMap().get(1);
        Player player2 = gameMaster.getPlayersMap().get(2);
        Player player3 = gameMaster.getPlayersMap().get(3);

        // Make moves and verify the turn order
        gameMaster.doMove(new Move(1, 1, player1));
        Assertions.assertEquals(player2, gameMaster.getPlayersMap().get(2));
        gameMaster.doMove(new Move(2, 2, player2));
        Assertions.assertEquals(player3, gameMaster.getPlayersMap().get(3));
        gameMaster.doMove(new Move(3, 3, player3));
        Assertions.assertEquals(player1, gameMaster.getPlayersMap().get(1));
    }

    @Test
    public void testIsThereMoreAvailableMoves() {
        // Set up the test parameters
        String configFile = prefix + simple_2_players;
        GameMaster gameMaster = null;
        try {
            gameMaster = GameMaster.loadGameMaster(configFile);
        } catch (InvalidConfigurationException ex) {
            Assertions.fail("InvalidConfigurationException occurred: " + ex.getMessage());
        }

        // Perform moves until no more available moves
        while (gameMaster.isThereMoreAvailableMoves()) {
            // Perform a move
            Player currentPlayer = gameMaster.getPlayersMap().get(gameMaster.getHistoryOfMoves().size() % gameMaster.getPlayersMap().size() + 1);
            gameMaster.doMove(new Move(1, 1, currentPlayer));
        }

        // Verify that there are no more available moves
        Assertions.assertFalse(gameMaster.isThereMoreAvailableMoves());
    }

    @Test
    public void testValidateInRange() {
        // Set up the test parameters
        String configFile = prefix + correct_three_different_types_players;
        GameMaster gameMaster = null;
        try {
            gameMaster = GameMaster.loadGameMaster(configFile);
        } catch (InvalidConfigurationException ex) {
            Assertions.fail("InvalidConfigurationException occurred: " + ex.getMessage());
        }

        // Test valid range
        try {
            int validNumber = gameMaster.validateInRange(2, 1, 3);
            Assertions.assertEquals(2, validNumber);
        } catch (InvalidInputException ex) {
            Assertions.fail("InvalidInputException occurred: " + ex.getMessage());
        }

        // Test invalid range
        try {
            gameMaster.validateInRange(5, 1, 3);
            Assertions.fail("InvalidInputException should have been thrown");
        } catch (InvalidInputException ex) {
            Assertions.assertEquals("Number 5 is out of range 1 and 3", ex.getMessage());
        }
    }
}
