package TicTacToeTest.test.tic.tac.toe;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TicTacToe.exceptions.GameException;
import TicTacToe.exceptions.InvalidConfigurationException;
import TicTacToe.exceptions.InvalidInputException;
import TicTacToe.move.Move;
import TicTacToe.players.AIPlayer;
import TicTacToe.players.HumanPlayer;
import TicTacToe.players.PLAYERTYPE;
import TicTacToe.players.Player;
import TicTacToe.tic.tac.toe.GameMaster;

public class PlayerTest extends BaseTest{

	HashMap<Integer, Player> playersMap = new HashMap<Integer, Player>();			
	HumanPlayer humanPlayer = null;
	AIPlayer aiPlayer = null;

	@Before
	public void setUpPlayer() throws InvalidConfigurationException {
		humanPlayer = new HumanPlayer(1, 'X', PLAYERTYPE.HUMAN);
		aiPlayer = new AIPlayer(2, 'O', PLAYERTYPE.AI);
		this.playersMap.put(1, humanPlayer);
		this.playersMap.put(2, aiPlayer);
		new GameMaster(3, this.playersMap, 2);
	}

	@Test
	public void testValidateIsNumber() {
		try{
			humanPlayer.validateIsNumber("xyz");
			Assert.fail("Expected InvalidInputException") ;
		}catch(InvalidInputException ex){
			Assert.assertEquals(ex.getMessage(), "Input must be a valid number");
		}
	}

	@Test
	public void testValidateMoveNoComma() {
		try{
			humanPlayer.validateMove("xyz");
			Assert.fail("Expected InvalidInputException") ;
		}catch(GameException ex){
			Assert.assertEquals(ex.getMessage(), "A valid move should be in the format x,y ... please separate the 2 numbers with , and make sure they are only 2 numbers");
		}
	}
	
	@Test
	public void testValidateMoveDash() {
		try{
			humanPlayer.validateMove("1-3");
			Assert.fail("Expected InvalidInputException") ;
		}catch(GameException ex){
			Assert.assertEquals(ex.getMessage(), "A valid move should be in the format x,y ... please separate the 2 numbers with , and make sure they are only 2 numbers");
		}
	}

	@Test
	public void testGetAnyAvailableMove() {
		Move move = aiPlayer.getAnyAvailableMove();
		Assert.assertEquals(move.getX(), 0);
		Assert.assertEquals(move.getY(), 0);
		
		aiPlayer.getGm().doMove(new Move(1,1, aiPlayer));
		aiPlayer.getGm().doMove(new Move(1,2, aiPlayer));
		move = aiPlayer.getAnyAvailableMove();
		Assert.assertEquals(move.getX(), 0);
		Assert.assertEquals(move.getY(), 2);

		aiPlayer.getGm().doMove(new Move(1,3, aiPlayer));
		move = aiPlayer.getAnyAvailableMove();
		Assert.assertEquals(move.getX(), 1);
		Assert.assertEquals(move.getY(), 0);

	}

	@Test
	public void testGetRandomMove() {
		Move move;
		try {
			move = aiPlayer.getRandomMove();
			Assert.assertTrue(move.getX() < 10 && move.getX() > -1);
			Assert.assertTrue(move.getY() < 10 && move.getY() > -1);
		} catch (GameException e) {
			Assert.fail("Shouldn't throw an GameException");			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHumanPlayerPLay() {
		// TODO
	}

	@Test
	public void testAIPlayerPLay() {
		// TODO
	}

}