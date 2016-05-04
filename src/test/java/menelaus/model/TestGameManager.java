package menelaus.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import menelaus.model.basic.*;
import menelaus.model.board.Piece;
import menelaus.model.move.AroundBoardMove;
import menelaus.model.move.RotatePieceMove;

/**
 * 
 * @author mtmccarthy
 *
 */

public class TestGameManager {
	
	GameManager gm;
	Level level;
	
	@Before
	public void setUp() {
		level = new Level(LevelType.PUZZLE, 100, 100);
		gm = new GameManager(level);
	}
	@Test
	public void testGetters(){
		assertEquals(gm.getLevel(), level);
		assertEquals(gm.getMovesMade(), 0);
	}
	@Test(expected=IllegalStateException.class)
	public void testPerformNewMoveException(){
		gm.performNewMove(new RotatePieceMove(new Piece(new Point(1,1))));
	}
	@Test
	public void testPerfomNewMove(){
		gm.startGame();
		gm.performNewMove(new AroundBoardMove(new Piece(new Point(1,1)), new Point(2,2)));
		assertEquals(gm.getMovesMade(), 1);
		gm.userEndsGame();
		
	}
}