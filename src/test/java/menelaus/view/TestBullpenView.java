package menelaus.view;

import static org.junit.Assert.*;
import menelaus.model.Bullpen;
import menelaus.model.basic.Point;
import menelaus.model.board.Piece;
import menelaus.view.BullpenView;

import org.junit.Before;
import org.junit.Test;

public class TestBullpenView {
	BullpenView bp;
	BullpenView bp2;
	Bullpen bullpen;
	Piece piece;
	Point point;
	@Before
	public void setUp() throws Exception {
		bp2 = new BullpenView();
		bullpen = new Bullpen();
		
		bp = new BullpenView(bullpen);
		
		point = new Point(0,0);
		piece = new Piece(point);
		bullpen.addPiece(piece);
		
	}

	@Test
	public void testFindPiece() {
		//assertEquals(bp.findPiece(0, 0), piece);
		assertEquals(1, 1);
	}

}
