package menelaus.util;

import static org.junit.Assert.*;
import menelaus.model.basic.Point;
import menelaus.model.board.Piece;
import menelaus.model.board.Tile;

import org.junit.Before;
import org.junit.Test;

public class TestPieceBank {
	
	Piece p1;
	Piece p2;
	Piece p14;
	Piece p35;

	@Before
	public void setUp() throws Exception {
		p1 = new Piece(new Point(0, 0));
        p1.addTile(new Tile(0, 0));
        p1.addTile(new Tile(0, 1));
        p1.addTile(new Tile(0, 2));
        p1.addTile(new Tile(0, 3));
        p1.addTile(new Tile(0, 4));
        p1.addTile(new Tile(0, 5));
		
		 p2 = new Piece(new Point(0, 0));
	     p2.addTile(new Tile(0, 0));
	     p2.addTile(new Tile(0, 1));
	     p2.addTile(new Tile(0, 2));
	     p2.addTile(new Tile(0, 3));
	     p2.addTile(new Tile(0, 4));
	     p2.addTile(new Tile(1, 0));
	     
	     p14 = new Piece(new Point(0, 0));
	     p14.addTile(new Tile(0, 0));
	     p14.addTile(new Tile(1, -2));
	     p14.addTile(new Tile(1, -1));
	     p14.addTile(new Tile(1, 0));
	     p14.addTile(new Tile(1, 1));
	     p14.addTile(new Tile(2, -2));
	     
	     p35 = new Piece(new Point(0, 0));
	     p35.addTile(new Tile(0, 0));
	     p35.addTile(new Tile(0, 1));
	     p35.addTile(new Tile(1, -1));
	     p35.addTile(new Tile(1, 0));
	     p35.addTile(new Tile(2, -2));
	     p35.addTile(new Tile(2, -1));
	}

	@Test
	public void testGetPiece() {
		assertEquals(p1.getPosition(), PieceBank.getPiece(1).getPosition());
		assertEquals(p1.getTiles().size(), PieceBank.getPiece(1).getTiles().size());

	}

}
