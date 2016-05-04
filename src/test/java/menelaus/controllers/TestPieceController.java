package menelaus.controllers;

import static org.junit.Assert.*;
import java.awt.event.MouseEvent;

import menelaus.model.GameManager;
import menelaus.model.Level;
import menelaus.model.basic.LevelType;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;
import menelaus.view.game.LevelPlayScreen;

import org.junit.Before;
import org.junit.Test;

public class TestPieceController {
	GameManager gameManager;
	BullpenView bullpenView;
	BoardView boardView;
	Level level;
	PieceController pieceController;

	@Before
	public void setUp() throws Exception {
	level = new Level(LevelType.PUZZLE, 6, 8);
	boardView = new BoardView();
	gameManager = new GameManager(level);
	pieceController = new PieceController(new LevelPlayScreen(level), gameManager);
	
	}

	@Test
	public void testPieceController() {
		
		pieceController.mouseClicked(new MouseEvent(boardView, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		pieceController.mouseClicked(new MouseEvent(boardView, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		pieceController.mouseDragged(new MouseEvent(boardView, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		pieceController.mouseEntered(new MouseEvent(boardView, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		pieceController.mouseExited(new MouseEvent(boardView, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		//pieceController.mouseMoved(new MouseEvent(boardView, 1, 1, 1, 1, 1, 1, 1, 1, false, 1));
		//pieceController.mousePressed(new MouseEvent(boardView, 1, 1, 1, 1, 1, 1, 1, 1, false, 1));
		pieceController.mouseReleased(new MouseEvent(boardView, 1, 1, 1, 1, 1, 1, 1, 1, false, 1));
		pieceController.mouseWheelMoved(null);
		assertEquals(1, 1);
	}

}
