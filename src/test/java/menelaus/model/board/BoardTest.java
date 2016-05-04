package menelaus.model.board;

import java.util.Hashtable;

import menelaus.model.basic.Color;
import menelaus.model.basic.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 
 * @author vouldjeff
 *
 */
public class BoardTest {
	Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board(3, 3);
	}

	@Test
	public void testChopTileOut() {
		board.chopTileOut(new Point(0, 2));
		assertEquals(true, board.getTileInfo().get(new Point(0, 2)).isTileChopped());
	}

	@Test
	public void testPlacePieceProperly() {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 1));
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(1, 0));
		piece2.addTile(new Tile(1, 1));
		piece2.addTile(new Tile(1, 2));
		
		Piece piece3 = new Piece(new Point(0, 2));
		piece3.addTile(new Tile(0, 0));
		piece3.addTile(new Tile(1, 0));
		try {
			board.placePiece(piece1);
			board.placePiece(piece2);
			board.placePiece(piece3);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(piece1, board.getPieces().get(0));
		assertEquals(piece2, board.getPieces().get(1));
		assertEquals(piece3, board.getPieces().get(2));
		
		assertEquals(piece1, board.getTileInfo().get(new Point(1, 1)).getPiecePlaced());
		assertEquals(piece2, board.getTileInfo().get(new Point(2, 2)).getPiecePlaced());
		assertEquals(piece3, board.getTileInfo().get(new Point(0, 2)).getPiecePlaced());
	}
	
	@Test
	public void testCoverWithPiece() {
		assertEquals(false, board.isFull());
		
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 0));
		piece1.addTile(new Tile(1, 1));
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(0, 1));
		piece2.addTile(new Tile(1, 0));
		piece2.addTile(new Tile(1, 1));
		
		Piece piece3 = new Piece(new Point(0, 1));
		piece3.addTile(new Tile(0, 0));
		piece3.addTile(new Tile(0, 1));
		piece3.addTile(new Tile(1, 0));
		piece3.addTile(new Tile(1, 1));
		
		Piece piece4 = new Piece(new Point(1, 1));
		piece4.addTile(new Tile(0, 0));
		piece4.addTile(new Tile(0, 1));
		piece4.addTile(new Tile(1, 0));
		piece4.addTile(new Tile(1, 1));
		
		try {
			board.coverWithPiece(piece1);
			board.coverWithPiece(piece2);
			board.coverWithPiece(piece3);
			board.coverWithPiece(piece4);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(piece1, board.getPieces().get(0));
		assertEquals(piece2, board.getPieces().get(1));
		
		assertEquals(true, board.isFull());
	}
	
	@Test
	public void testIsFull() {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(0, 2));
		
		try {
			board.placePiece(piece1);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(false, board.isFull());
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(0, 1));
		piece2.addTile(new Tile(1, 0));
		piece2.addTile(new Tile(1, 1));
		piece2.addTile(new Tile(0, 2));
		piece2.addTile(new Tile(1, 2));
		
		try {
			board.placePiece(piece2);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(true, board.isFull());
	}
	
	@Test
	public void testIsFullWithColoredSetItem() {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(0, 2));
		
		try {
			board.placePiece(piece1);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(false, board.isFull());
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(0, 1));
		piece2.addTile(new Tile(1, 0));
		piece2.addTile(new Tile(1, 1));
		piece2.addTile(new Tile(0, 2));
		
		board.addColoredSetItem(new ColoredSetItem(Color.BLUE, 1), new Point(2, 2));
		
		try {
			board.placePiece(piece2);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(false, board.isFull());
	}
	
	@Test
	public void testIsFullWithCoppedOut() {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(0, 2));
		
		try {
			board.placePiece(piece1);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(false, board.isFull());
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(0, 1));
		piece2.addTile(new Tile(1, 0));
		piece2.addTile(new Tile(1, 1));
		piece2.addTile(new Tile(0, 2));
		
		board.chopTileOut(new Point(2, 2));
		
		try {
			board.placePiece(piece2);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(true, board.isFull());
	}
	
	@Test
	public void testGetNumberOfEmptyTiles() {
		assertEquals(board.getHeight() * board.getWidth(), board.getNumberOfEmptyTiles());
		
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(0, 2));
		
		try {
			board.placePiece(piece1);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(false, board.isFull());
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(0, 1));
		piece2.addTile(new Tile(1, 0));
		
		board.addColoredSetItem(new ColoredSetItem(Color.BLUE, 1), new Point(1, 2));
		board.chopTileOut(new Point(2, 2));
		
		try {
			board.placePiece(piece2);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(2, board.getNumberOfEmptyTiles());
	}
	
	@Test(expected=InvalidPiecePlacementException.class)
	public void testPlacePieceOutside() throws InvalidPiecePlacementException {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 1));
		piece1.addTile(new Tile(0, 2));
		piece1.addTile(new Tile(0, 3));
		
		board.placePiece(piece1);
	}
	
	@Test(expected=InvalidPiecePlacementException.class)
	public void testPlacePieceOutsideNegative() throws InvalidPiecePlacementException {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(-1, 1));
		
		board.placePiece(piece1);
	}
	
	@Test(expected=InvalidPiecePlacementException.class)
	public void testPlacePieceOnChoppedTile() throws InvalidPiecePlacementException {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 1));
		piece1.addTile(new Tile(0, 2));
		
		board.chopTileOut(new Point(0, 2));
		board.placePiece(piece1);
	}

	@Test
	public void testRemovePiece() {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 1));
		
		try {
			board.placePiece(piece1);
		} catch (InvalidPiecePlacementException e) {
			fail("Should not get an Exception here");
		}
		
		assertEquals(piece1, board.getTileInfo().get(new Point(1, 1)).getPiecePlaced());
		assertEquals(1, board.getPieces().size());
		
		board.removePiece(piece1);
		
		assertEquals(null, board.getTileInfo().get(new Point(1, 1)).getPiecePlaced());
		assertEquals(0, board.getPieces().size());
	}
	
	@Test
	public void testAddColoredSetItem() {
		ColoredSetItem item = new ColoredSetItem(Color.RED, 1);
		Point location = new Point(0, 0);
		
		board.addColoredSetItem(item, location);
		
		assertEquals(board.getTileInfo().get(new Point(0, 0)).getColoredSetItem(), item);
	}
	@Test
	public void testChopped(){
		board.unchopTile(new Point(0,0));
		assertTrue(!board.isChoppedOut(new Point(0, 0)));
	}
}
