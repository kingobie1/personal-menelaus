package menelaus.view;

import menelaus.model.basic.Point;
import menelaus.model.board.Piece;
import menelaus.view.KabasujiPanel;
import menelaus.view.PieceDrawer;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import static org.junit.Assert.assertEquals;


public class TestPieceDrawer {
	Graphics g;
	Piece piece;
	Point targetPoint;
	int tileSize;
	ArrayList<Point> points;
	KabasujiPanel kp;
	
	
	@Before
	public void setUp() throws Exception {
		
		if(_stopTest()){return;}
		targetPoint = new Point(0, 0);
		tileSize = 1;
		piece = new Piece(targetPoint);
		kp = new KabasujiPanel();
		g = ImageIO.read(this.getClass().getResource("/assets/secondary_back.png")).createGraphics();
		PieceDrawer.drawPiece(g, piece, targetPoint, tileSize);
		PieceDrawer.drawPieceToGrid(g, piece, tileSize);
		PieceDrawer.drawHintToGrid(g, piece, tileSize);
		points = new ArrayList<>();
		points.add(targetPoint);
		PieceDrawer.drawSelection(g, points, tileSize);
		
	}

	@Test
	public void testDrawPiece() {
		if(_stopTest()){return;}
		assertEquals(piece.getPosition(), targetPoint);
	}
	private boolean _stopTest() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless();
	}
}
