package menelaus.model.board;

import menelaus.model.basic.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author vouldjeff
 *
 */
public class PieceTest {
	Piece piece;
	Piece piece2;

	@Before
	public void setUp() throws Exception {
		piece = new Piece(new Point(0, 0));
		piece.addTile(new Tile(1, 1));
		piece.addTile(new Tile(2, 1));
		piece.addTile(new Tile(1, 2));
		
		piece2 = new Piece(new Point(0, 0));
		piece2.addTile(new Tile(0, 1));
		piece2.addTile(new Tile(0, 2));
		piece2.addTile(new Tile(0, 3));
		piece2.addTile(new Tile(1, 2));
	}
	
	@Test
	public void testBasics() {
		piece2.setPosition(new Point(4, 5));
		
		assertEquals(new Point(0, 0), piece.getPosition());
		assertEquals(new Point(4, 5), piece2.getPosition());
	}

	@Test
	public void testRotate() {
		piece.rotate();
		piece2.rotate();
		
		assertEquals(new Tile(0, 0), piece.getTiles().get(0));
		assertEquals(new Tile(1, -1), piece.getTiles().get(1));
		assertEquals(new Tile(1, -2), piece.getTiles().get(2));
		assertEquals(new Tile(2, -1), piece.getTiles().get(3));
		
		assertEquals(new Tile(0, 0), piece2.getTiles().get(0));
		assertEquals(new Tile(1, 0), piece2.getTiles().get(1));
		assertEquals(new Tile(2, 0), piece2.getTiles().get(2));
		assertEquals(new Tile(3, 0), piece2.getTiles().get(3));
		assertEquals(new Tile(2, -1), piece2.getTiles().get(4));
	}

	@Test
	public void testFlip() {
		piece.flip();
		piece2.flip();
		
		assertEquals(new Tile(2, 0), piece.getTiles().get(0));
		assertEquals(new Tile(1, 1), piece.getTiles().get(1));
		assertEquals(new Tile(0, 1), piece.getTiles().get(2));
		assertEquals(new Tile(1, 2), piece.getTiles().get(3));
		
		assertEquals(new Tile(1, 0), piece2.getTiles().get(0));
		assertEquals(new Tile(1, 1), piece2.getTiles().get(1));
		assertEquals(new Tile(1, 2), piece2.getTiles().get(2));
		assertEquals(new Tile(1, 3), piece2.getTiles().get(3));
		assertEquals(new Tile(0, 2), piece2.getTiles().get(4));
	}

	@Test
	public void testAddTile() {
		piece.addTile(new Tile(1, 0));
	
		assertEquals(new Tile(0, 0), piece.getTiles().get(0));
		assertEquals(new Tile(1, 0), piece.getTiles().get(piece.getTiles().size() - 1));
	}

	@Test
	public void testGetHeight(){
		assertEquals(piece.getHeight(), 2);
	}

	@Test
	public void testGetWidth(){
        System.out.println("width = " + piece.getWidth());
        assertEquals(piece.getWidth(), 2);
	}
}
