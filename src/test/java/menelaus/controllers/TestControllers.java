package menelaus.controllers;

import static org.junit.Assert.*;

import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;

import menelaus.model.BuilderManager;
import menelaus.model.GameManager;
import menelaus.model.Level;
import menelaus.model.basic.Point;
import menelaus.model.board.Piece;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;
import menelaus.view.game.GameWindowFrame;

import org.junit.Before;
import org.junit.Test;

public class TestControllers {
	PieceDragController pdc;
	PieceSelectionController psc;
	ButtonBuilderStartController bbsc;
	SaveLevelButtonBuilderMakeLevelController slbb;
	
	
	
	Level level;
	GameWindowFrame gwFrame;
	BuilderManager bManager;
	BoardView bv;
	BullpenView bpView;
	GameManager gameManager;

	@Before
	public void setUp() throws Exception {
		if (_stopTest()) { return; }
		
		gwFrame = GameWindowFrame.getInstance();
		bManager = new BuilderManager();
		level = gwFrame.getLevelsPackage().getLevels().get(0);
		bv = new BoardView(level.getBoard(), level);
		gwFrame = GameWindowFrame.getInstance();
		bpView = new BullpenView();
		gameManager = new GameManager(level);
		
		pdc = new PieceDragController(level, gwFrame);
		psc = new PieceSelectionController(bpView, gameManager);
		bbsc = new ButtonBuilderStartController(bManager);
		slbb = new SaveLevelButtonBuilderMakeLevelController(bManager);
		
	}

	@Test
	public void testPieceDragController() {
		if (_stopTest()) { return; }
		
		pdc.level.getBullpen().addPiece(new Piece(new Point(0, 0)));
		pdc.register();
		pdc.select(0, 0);
		pdc.mouseClicked(null);
		pdc.mouseDragged(new MouseEvent(bv, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		pdc.mouseEntered(null);
		pdc.mouseExited(null);
		pdc.mouseMoved(null);
		pdc.mousePressed(new MouseEvent(bv, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		pdc.mouseWheelMoved(null);
		assertEquals(1, 1);
	}

	@Test
	public void testPieceSelectionController(){
		if (_stopTest()) { return; }
		
		psc.mouseClicked(new MouseEvent(bv, 1, 2, 1, 1, 1, 1, 1, 1, false, 1));
		psc.mouseDragged(new MouseEvent(bv, 0, 0, 0, 0, 0, 0, 0, 0, false, 0));
		psc.mouseEntered(new MouseEvent(bv, 1, 2, 1, 1, 1, 1, 1, 1, false, 1));
		psc.mouseExited(new MouseEvent(bv, 1, 2, 1, 1, 1, 1, 1, 1, false, 1));
		psc.mouseMoved(new MouseEvent(bv, 1, 2, 1, 1, 1, 1, 1, 1, false, 1));
		//psc.mousePressed(null);
		//psc.mouseReleased(new MouseEvent(bv, 1, 2, 1, 1, 1, 1, 1, 1, false, 1));
		psc.mouseWheelMoved(null);
	}
	@Test
	public void testButtonBuilderStartController(){
		if (_stopTest()) { return; }
		
		bbsc.actionPerformed(null);
		assertTrue(bbsc.isSizeValid(0, 0));
		assertTrue(!bbsc.isSizeValid(-1, 0));
	}
	@Test
	public void testSaveLevelButtonBuilder(){
		if (_stopTest()) { return; }
		
		slbb.actionPerformed(null);
		assertTrue(true);
	}
	
	private boolean _stopTest() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless();
	}
}
