package menelaus.view.game;

import static org.junit.Assert.assertEquals;
import menelaus.model.Level;
import menelaus.model.basic.Color;
import menelaus.model.basic.LevelType;
import menelaus.model.board.Board;
import menelaus.model.board.BoardTileInfo;
import menelaus.model.board.ColoredSetItem;
import menelaus.model.basic.Point;
import menelaus.view.BoardView;

import org.junit.Before;
import org.junit.Test;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class TestBoardView {
	BoardView bv;
	BufferedImage bi;
	Graphics g;
	BoardTileInfo boardTileInfo;
	Board board;
	Level level;
	

	@Before
	public void setUp() throws Exception {
		if(_stopTest()){return;}
		board = new Board(6, 8);
		level = new Level(LevelType.PUZZLE, board.getWidth(), board.getHeight());
		bv = new BoardView(board, level);
		bi = ImageIO.read(this.getClass().getResource("/assets/secondary_back.png"));
		g = bi.createGraphics();
	}


	@Test
	public void testGetMinimumSize() {
		if(_stopTest()){return;}
		assertEquals(1464, bv.getMinimumSize().getWidth(), 1);
		assertEquals(1464, bv.getMinimumSize().getHeight(), 1);
	}
	@Test
	public void testPainting(){
		if(_stopTest()){return;}
		bv.paintComponent(g);
		bv.drawUnavailableTiles(g);
		bv.drawReleaseColorSets(g);
		bv.drawGrid(g);

		assertEquals(bv.calculateGridUnitSize(), 0);
		
	}
	
	private boolean _stopTest() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless();
	}

}
